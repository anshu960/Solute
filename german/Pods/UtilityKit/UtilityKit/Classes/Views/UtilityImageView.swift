//
//  UtilityImageView.swift
//  Pods-UtilityKit_Example
//
//  Created by Vivek Kumar 12/09/18.
//

import UIKit
public protocol UtilityImageViewDelegate {
    func loadSucceded()
    func loadFailed()
}

open class UtilityImageView: UIImageView {
    
    public var activityIndicator = UIActivityIndicatorView()
    
    public var delegate : UtilityImageViewDelegate?
    var remoteUrl : URL!
    public func download(from url:URL){
        self.remoteUrl = self.getUrl(url: url)
        DispatchQueue.main.async {
            self.image = nil
            self.activityIndicator = UIActivityIndicatorView()
            self.activityIndicator.style = .whiteLarge
            self.activityIndicator.stopAnimating()
            self.activityIndicator.frame = self.bounds
            self.addSubview(self.activityIndicator)
            self.activityIndicator.startAnimating()
            DispatchUtill.concurrentBackgroundQueue.async {
                if let data = try? Data(contentsOf: self.remoteUrl) {
                    DispatchQueue.main.async {
                        if let image = UIImage(data: data) {
                            self.image = image
                            if(self.remoteUrl == url){
                                self.storeImage(remoteUrl: url, image: image)
                            }
                            self.loadActionCompleted()
                        }else{
                            self.loadActionCompleted()
                        }
                    }
                }else{
                    self.loadActionCompleted()
                }
            }
        }
    }
    private func loadActionCompleted(){
        DispatchQueue.main.async {
            self.activityIndicator.stopAnimating()
            self.activityIndicator.removeFromSuperview()
            if(self.image == nil){
                self.delegate?.loadFailed()
            }else{
                
                self.delegate?.loadSucceded()
            }
        }
    }
    private func storeImage(remoteUrl:URL,image:UIImage) {
        var filePath = FileUtility.shared.defaultPath
        let fileName = Date().formattedTimeStamp.description + ".png"
        filePath = filePath.appendingPathComponent(fileName)
        let data = image.pngData()!
        FileUtility.shared.writeFile(directory: filePath, file: data)
        let defaults = UserDefaults()
        defaults.set(fileName, forKey: remoteUrl.absoluteString)
        defaults.synchronize()
    }
    
    
    
    private func getUrl(url:URL)-> URL{
        let defaults = UserDefaults()
        if let urlStr = defaults.string(forKey: url.absoluteString){
            let filePath = FileUtility.shared.defaultPath.appendingPathComponent(urlStr)
            
            return filePath
        }else{
            return url
        }
    }
}

