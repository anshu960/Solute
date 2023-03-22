//
//  CustomerViewModel.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import UtilityKit
class ProductViewModel {
    public static let businessId = "6364c00fb25244fcf46425f0"
    public static let shared = ProductViewModel()
    var products : [Product] = []
    var allData : [Product] = []
    var isLoading : Bool = false
    var onChange : (([Product])->Void)?
    

    func search(query:String?) {
        products = []
        if let search = query,!search.isEmpty{
            allData.forEach { prd in
                if let prdName = prd.name,prdName.contains(search){
                    products.append(prd)
                }
            }
        }else{
            products = allData
        }
        onChange?(products)
    }
    
    
    func loadProducts(){
        CustomerViewModel.shared.loadMembership()
        isLoading = true
        var request : [String:Any] = [:]
        let user = Preferences.shared.dictionary(forKey: Key.userCredentials)
        if let id = user[Key._id] as? String,let mobile = user[Key.mobileNumber]{
            request[Key.userId] = id
            request[Key.businessID] = AuthenticationViewModel.shared.businessId
            request[Key.mobileNumber] = mobile
        }
        SocketService.shared.send(eventName: ProductSocketEvent.RETRIEVE.rawValue, payload: request)
    }
}


