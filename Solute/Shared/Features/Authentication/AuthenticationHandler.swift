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
    
    func addFeature(manager: SocketManager?) {
        manager?.defaultSocket.on(AuthenticationSocketEvent.AUHTENTICATE.rawValue, callback: onAuthenticateEvent)
    }
    
    func onAuthenticateEvent(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        if let data = data.first as? [String:Any]{
            if let payload = data[Key.payload] as? [String:Any],let id = payload[Key._id] as? String,!id.isEmpty{
                storeUserCredentials(userData: payload)
                SocketService.shared.joinRoom(roomId: id)
                AuthenticationViewModel.shared.setHomeScreen()
            }else{
                AuthenticationViewModel.shared.setRegisterScreen()
            }
        }else{
            AuthenticationViewModel.shared.setRegisterScreen()
        }
    }
    
    func storeUserCredentials(userData:[String:Any]){
        UserDefaults.standard.set(userData.toJSONStr(), forKey: Key.loginDetails)
        UserDefaults.standard.synchronize()
//        let newUser = LoginDetails(context: PersistenceController.shared.container.viewContext)
//        if let name = userData[Key.name] as? String{
//            newUser.setValue(name, forKey: "name")
//        }
//        if let mobile = userData[Key.mobileNumber] as? String{
//            newUser.setValue(mobile, forKey: "mobile")
//        }
//        if let userId = userData[Key.userId] as? String{
//            newUser.setValue(userId, forKey: "use_id")
//        }
//        if let _id = userData[Key._id] as? String{
//            newUser.setValue(_id, forKey: "id")
//        }
//        do {
//            try PersistenceController.shared.container.viewContext.save()
//        } catch {
//            // Replace this implementation with code to handle the error appropriately.
//            // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
//            let nsError = error as NSError
//            fatalError("Unresolved error \(nsError), \(nsError.userInfo)")
//        }
    }
    func fetchUserCredentials()->[String:Any]{
        var details : [String:Any] = [:]
        let data = UserDefaults.standard.string(forKey: Key.loginDetails) ?? "{}"
        details = data.toDictionary()
        
//        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "LoginDetails")
//        request.returnsObjectsAsFaults = false
//        do {
//            let result = try PersistenceController.shared.container.viewContext.fetch(request)
//            for data in result as! [NSManagedObject] {
//                if let name = data.value(forKey: "name") as? String{
//                    details[Key.name] = name
//                }
//                if let mobile = data.value(forKey:"mobile_number") as? String{
//                    details[Key.mobileNumber] = mobile
//                }
//                if let userId = data.value(forKey:"use_id") as? String{
//                    details[Key.userId] = userId
//                }
//                if let _id = data.value(forKey:"id") as? String{
//                    details[Key._id] = _id
//                }
//            }
//        } catch {
//            print("Fetching data Failed")
//        }
        return details
    }
}
