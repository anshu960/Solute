//
//  CustomerHandler.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import Foundation
import SocketIO
class ProductHandler{
    public static let shared =  ProductHandler()
    
    func addFeature(manager: SocketManager?) {
        manager?.defaultSocket.on(ProductSocketEvent.RETRIEVE.rawValue, callback: onRetrieveProduct)
    }
    
    func onRetrieveProduct(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        ProductViewModel.shared.isLoading = false
        let decoder = JSONDecoder()
        if let data = data.first as? [String:Any]{
            if let payload = data[Key.payload] as? [[String:Any]]{
                payload.forEach { item in
                    do {
                        let data = try JSONSerialization.data(withJSONObject: item, options: .prettyPrinted)
                        let prd = try decoder.decode(Product.self, from: data)
                        ProductViewModel.shared.products.append(prd)
                    } catch {
                        print(error.localizedDescription)
                    }
                }
                ProductViewModel.shared.products = ProductViewModel.shared.products.sorted { $0.name ?? "" < $1.name ?? "" }
                ProductViewModel.shared.allData = ProductViewModel.shared.products
                ProductViewModel.shared.onChange?(ProductViewModel.shared.products)
            }
        }else{
            //            AuthenticationViewModel.shared.setRegisterScreen()
        }
    }
}
