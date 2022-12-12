//
//  ScannerView.swift
//  UtilityKit
//
//  Created by Vivek Kumar on 31/12/18.
//
public enum ScannerType {
    case Barcode,QRcode,BarcodeAndQRcode
}
public protocol ScannerDelegate {
    func didScanned(code:String)
    func cameraAcceddDenied()
}

import UIKit
import AVFoundation
import WebKit
@IBDesignable public class ScannerView: UtilityView {
    public override func prepareForInterfaceBuilder() {
        super.prepareForInterfaceBuilder()
    }
    
    public var delegate : ScannerDelegate?
    public static let barcodeMetadata = [AVMetadataObject.ObjectType.ean13,AVMetadataObject.ObjectType.ean8, AVMetadataObject.ObjectType.pdf417,AVMetadataObject.ObjectType.upce,AVMetadataObject.ObjectType.aztec,AVMetadataObject.ObjectType.itf14,AVMetadataObject.ObjectType.code39,AVMetadataObject.ObjectType.code93,AVMetadataObject.ObjectType.code128,AVMetadataObject.ObjectType.code39Mod43]
    public static let qrCodeMetadata = [AVMetadataObject.ObjectType.qr]
    
    public var messageToRequestForCameraAccess = "Please allow access to use camera"
    public var deniedMessage = "You have denied access to camera, please allow access to scan"
    private var scannerMetadata :[AVMetadataObject.ObjectType] = []
    var errorLabel = UILabel()
    let view = WKWebView()
    var scanDetectorView : UIView = UIView()
    var themeColor = UIColor.lightGray
    public override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    var orientation : AVCaptureVideoOrientation = AVCaptureVideoOrientation.portrait{
        didSet{
            self.previewLayer?.connection?.videoOrientation = orientation
        }
    }
    
    public var scannerOrientation : AVCaptureVideoOrientation{
        get{
            return self.orientation
        }
        set{
            self.orientation = newValue
        }
    }
    
    private var type : ScannerType = ScannerType.Barcode
    public var scannerType:ScannerType{
        get{
            return self.type
        }
        set{
            self.type = newValue
            self.setMetadata()
        }
    }
    
    func setMetadata(){
        switch self.type {
        case .Barcode:
            self.scannerMetadata = ScannerView.barcodeMetadata
            break
        case .QRcode:
            self.scannerMetadata = ScannerView.qrCodeMetadata
            break
        case .BarcodeAndQRcode:
            self.scannerMetadata = ScannerView.barcodeMetadata + ScannerView.qrCodeMetadata
            break
        }
    }
    
    //Device
    var videoCaptureDevice: AVCaptureDevice? = AVCaptureDevice.default(for: AVMediaType.video)
    var output = AVCaptureMetadataOutput()
    var previewLayer: AVCaptureVideoPreviewLayer?
    var captureSession = AVCaptureSession()
    
    
    func requestForCamera() {
        let alert = UIAlertController(title: "Alert", message: self.messageToRequestForCameraAccess, preferredStyle: .alert)
        let settingAction = UIAlertAction(title: "Settings", style: .default) { (action) in
            guard let settingsUrl = URL(string: UIApplication.openSettingsURLString) else {
                return
            }
            if UIApplication.shared.canOpenURL(settingsUrl) {
                UIApplication.shared.open(settingsUrl, completionHandler: { (success) in
                    print("Settings opened: \(success)")
                })
            }
        }
        let okAction = UIAlertAction(title: "Later", style: .default) { (action) in
            
        }
        alert.addAction(settingAction)
        alert.addAction(okAction)
        if let controller = App.presentedController(){
            controller.present(alert, animated: true, completion: nil)
        }
    }
    var isCameraAccessGranted : Bool{
        get{
            if AVCaptureDevice.authorizationStatus(for: AVMediaType.video) ==  AVAuthorizationStatus.authorized {
                return true
            }else{
                return false
            }
        }
    }
    
    func enableCamera() {
        
        if !self.isCameraAccessGranted{
            AVCaptureDevice.requestAccess(for: AVMediaType.video, completionHandler: { (granted: Bool) -> Void in
                if granted == true {
                    DispatchQueue.main.async {
                        self.startScanner()
                    }
                } else{
                    self.requestForCamera()
                }
            })
        }else{
            DispatchQueue.main.async {
                self.startScanner()
            }
        }
    }
    
    func showDeniedMessage() {
        
    }
    
    public override func removeFromSuperview() {
        super.removeFromSuperview()
        self.captureSession.stopRunning()
        self.previewLayer?.removeFromSuperlayer()
    }
    
    public func startScanner() {
        self.setMetadata()
        if self.videoCaptureDevice == nil{
            print("device unavialable")
            self.errorLabel.text = "Camera Not Avialable"
            self.errorLabel.backgroundColor = UIColor.white
            self.errorLabel.textColor = UIColor.black
            self.errorLabel.frame = self.bounds
            self.errorLabel.textAlignment = .center
            self.addSubview(self.errorLabel)
        }else{
            if let input = try? AVCaptureDeviceInput(device: videoCaptureDevice!){
                if self.captureSession.canAddInput(input) {
                    self.captureSession.addInput(input)
                }
            }
            self.previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
            if let videoPreviewLayer = self.previewLayer {
                videoPreviewLayer.videoGravity = AVLayerVideoGravity.resizeAspectFill
                videoPreviewLayer.frame = self.bounds
                self.layer.addSublayer(videoPreviewLayer)
            }
            
            let metadataOutput = AVCaptureMetadataOutput()
            if self.captureSession.canAddOutput(metadataOutput) {
                self.captureSession.addOutput(metadataOutput)
                metadataOutput.setMetadataObjectsDelegate(self, queue: DispatchQueue.main)
                metadataOutput.metadataObjectTypes = self.scannerMetadata
                print("Add metadata output")
                self.previewLayer?.connection?.videoOrientation = orientation
                self.captureSession.startRunning()
            } else {
                print("Could not add metadata output")
            }
        }
    }
    
    public override func layoutSubviews() {
        super.layoutSubviews()
        
        self.previewLayer?.connection?.videoOrientation = orientation
        self.previewLayer?.frame = self.bounds
        
    }
    
    func layoutScanDetector() {
        //Code detection view
        switch self.type {
        case .Barcode:
            self.scanDetectorView.frame = CGRect(x: 0, y: 0, width: self.frame.width*0.8, height: 5)
            self.scanDetectorView.center = self.center
            self.scanDetectorView.backgroundColor = self.themeColor
            self.scanDetectorView.layer.cornerRadius = 2.5
        case .QRcode:
            self.scanDetectorView.frame = CGRect(x: 0, y: 0, width: self.frame.width*0.8, height: self.frame.width*0.8)
            self.scanDetectorView.center = self.center
            self.scanDetectorView.backgroundColor = UIColor.clear
            self.scanDetectorView.layer.borderWidth = 5
            self.scanDetectorView.layer.borderColor = self.themeColor.cgColor
        case .BarcodeAndQRcode:
            self.scanDetectorView.frame = CGRect(x: 0, y: 0, width: self.frame.width*0.8, height: self.frame.width*0.8)
            self.scanDetectorView.center = self.center
            self.scanDetectorView.backgroundColor = UIColor.clear
            self.scanDetectorView.layer.borderWidth = 5
            self.scanDetectorView.layer.borderColor = self.themeColor.cgColor
        }
        if(!self.scanDetectorView.isDescendant(of: self)){
            self.addSubview(scanDetectorView)
            self.bringSubviewToFront(self.scanDetectorView)
        }
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
    }
    var isPresented = false
    public override func willMove(toSuperview newSuperview: UIView?) {
        super.willMove(toSuperview: newSuperview)
        DispatchQueue.main.async {
            if(self.isPresented){
                self.isPresented = false
                self.captureSession.stopRunning()
                self.previewLayer?.removeFromSuperlayer()
            }else{
                self.isPresented = true
                self.enableCamera()
            }
        }
    }
    
    public override func didMoveToSuperview() {
        super.didMoveToSuperview()
    }
}

extension ScannerView:AVCaptureMetadataOutputObjectsDelegate {
    public func metadataOutput(_ output: AVCaptureMetadataOutput, didOutput metadataObjects: [AVMetadataObject], from connection: AVCaptureConnection){
        for metadata in metadataObjects {
            let readableObject = metadata as! AVMetadataMachineReadableCodeObject
            let codeObject = self.previewLayer?.transformedMetadataObject(for: readableObject)
            
            if let redableCode = readableObject.stringValue,let objectFrame = codeObject?.bounds{
                if(redableCode != ""){
                    var codeFrame = CGRect.zero
                    switch self.type{
                    case .Barcode:
                        codeFrame = CGRect(x: objectFrame.origin.x, y: (objectFrame.maxY-(objectFrame.height/2))-2.5, width: objectFrame.width, height: 5)
                        break
                    case .QRcode:
                        codeFrame = objectFrame
                        break
                    case .BarcodeAndQRcode:
                        codeFrame = objectFrame
                        break
                    }
                    self.captureSession.stopRunning()
                    UIView.animate(withDuration: 0.5, delay: 0.0, usingSpringWithDamping:
                        0.8, initialSpringVelocity: 1.2, options: [], animations: {
                            self.scanDetectorView.frame = codeFrame
                    }, completion: { finished in
                        //code that runs after the transition is complete here
                        if(finished){
                            self.delegate?.didScanned(code: redableCode)
                        }
                    })
                }
            }
        }
    }
}
