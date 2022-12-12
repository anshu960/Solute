//
//  AuthenticationHandler.swift
//  Solute
//
//  Created by Vivek Singh on 11/12/22.
//

import Foundation
import SocketIO
import UtilityKit
class AuthenticationHandler{
    public static let shared = AuthenticationHandler()
    
    func addFeature(manager: SocketManager?) {
        manager?.defaultSocket.on(AuthenticationSocketEvent.AUHTENTICATE.rawValue, callback: onAuthenticateEvent)
    }
    
    func onAuthenticateEvent(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        if let data = data.first as? [String:Any]{
            if let payload = data[Key.payload] as? [String:Any],let id = payload[Key._id] as? String,!id.isEmpty{
                LocalStorage.shared.store(value: payload, key: Key.loginDetails)
                AuthenticationViewModel.shared.setHomeScreen()
            }else{
                AuthenticationViewModel.shared.setRegisterScreen()
            }
        }else{
            AuthenticationViewModel.shared.setRegisterScreen()
        }
    }
}
