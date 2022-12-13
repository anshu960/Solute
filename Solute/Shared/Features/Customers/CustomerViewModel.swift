//
//  CustomerViewModel.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import UIKit
class CustomerViewModel : ObservableObject{
    public static let shared = CustomerViewModel()
    @Published var membershipId = ""
    @Published var barcode : UIImage? = nil
    
    func loadMembership(){
        let data = UserDefaults.standard.string(forKey: Key.membershipDetails) ?? ""
        let object = data.toDictionary()
        if let barcodeStr = object[Key.barcode] as? String{
            membershipId = barcodeStr
            createBarcode()
        }else{
            customerForBusiness(businessId: "6364c00fb25244fcf46425f0")
        }
    }
    
    func customerForBusiness(businessId:String){
        var request : [String:Any] = [:]
        let user = AuthenticationHandler.shared.fetchUserCredentials()
        if let id = user[Key._id] as? String,let mobile = user[Key.mobileNumber]{
            request[Key.userId] = id
            request[Key.businessID] = businessId
            request[Key.mobileNumber] = mobile
        }
        DispatchQueue.main.asyncAfter(deadline: .now() + 3){
            SocketService.shared.send(eventName: CustomerSocketEvent.FIND_CUSTOMER_BY_MOBILE.rawValue, payload: request)
        }
    }
    
    
    func createBarcode(){
        if(membershipId != ""){
            let barcodeImage = generateBarcode(from: membershipId)
            barcode = barcodeImage
            self.objectWillChange.send()
        }
    }
    func generateBarcode(from string: String) -> UIImage? {
        let data = string.data(using: String.Encoding.ascii)

        if let filter = CIFilter(name: "CICode128BarcodeGenerator") {
            filter.setValue(data, forKey: "inputMessage")
            let transform = CGAffineTransform(scaleX: 3, y: 3)

            if let output = filter.outputImage?.transformed(by: transform) {
                return UIImage(ciImage: output)
            }
        }

        return nil
    }
}

extension UIImage {
    
    convenience init?(barcode: String) {
        let data = barcode.data(using: .ascii)
        guard let filter = CIFilter(name: "CICode128BarcodeGenerator") else {
            return nil
        }
        filter.setValue(data, forKey: "inputMessage")
        guard let ciImage = filter.outputImage else {
            return nil
        }
        self.init(ciImage: ciImage)
    }
    
}
