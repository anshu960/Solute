//
//  CustomerHandler.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import Foundation
import SocketIO
class CustomerHandler{
    public static let shared =  CustomerHandler()
    
    func addFeature(manager: SocketManager?) {
        manager?.defaultSocket.on(CustomerSocketEvent.FIND_CUSTOMER_BY_MOBILE.rawValue, callback: onFindCustomer)
    }
    
    func onFindCustomer(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        if let data = data.first as? [String:Any]{
            if let payloads = data[Key.payload] as? [[String:Any]],let payload = payloads.first,let id = payload[Key._id] as? String,!id.isEmpty{
                UserDefaults.standard.set(payload.toJSONStr(), forKey: Key.membershipDetails)
                UserDefaults.standard.synchronize()
            }
        }else{
//            AuthenticationViewModel.shared.setRegisterScreen()
        }
    }
}
