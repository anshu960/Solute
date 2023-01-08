//
//  CustomerViewModel.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import UIKit
class ProductViewModel : ObservableObject{
    public static let businessId = "6364c00fb25244fcf46425f0"
    public static let shared = ProductViewModel()
    @Published var products : [Product] = []
    @Published var isLoading : Bool = false
    
    func loadProducts(){
        isLoading = true
        var request : [String:Any] = [:]
        let user = AuthenticationHandler.shared.fetchUserCredentials()
        if let id = user[Key._id] as? String,let mobile = user[Key.mobileNumber]{
            request[Key.userId] = id
            request[Key.businessID] = ProductViewModel.businessId
            request[Key.mobileNumber] = mobile
        }
        SocketService.shared.send(eventName: ProductSocketEvent.RETRIEVE.rawValue, payload: request)
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
}


