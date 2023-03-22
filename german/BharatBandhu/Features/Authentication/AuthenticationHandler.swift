//
//  AuthenticationHandler.swift
//  Solute
//
//  Created by Vivek Singh on 11/12/22.
//

import Foundation
import SocketIO
import UtilityKit
import CoreData
class AuthenticationHandler{
    public static let shared = AuthenticationHandler()
    var onStateChange : ((_ msg:String)->Void)? = nil
    
    func addFeature() {
        SocketService.shared.manager?.defaultSocket.on(AuthenticationSocketEvent.AUHTENTICATE.rawValue, callback: onAuthenticateEvent)
    }
    
    func onAuthenticateEvent(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        AuthenticationViewModel.shared.isLoading = false
        if let data = data.first as? [String:Any]{
            if let payload = data[Key.payload] as? [String:Any],let id = payload[Key._id] as? String,!id.isEmpty{
                Preferences.shared.store(value: payload.toJSONStr(), key: Key.userCredentials)
                onStateChange?("")
            }else{
                onStateChange?("No Account")
            }
        }else{
            onStateChange?("Oops! Something went wrong, please try after some time")
        }
    }
}
