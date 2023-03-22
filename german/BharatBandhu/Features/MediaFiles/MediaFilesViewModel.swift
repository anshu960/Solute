//
//  CustomerViewModel.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import UtilityKit
class MediaFilesViewModel {
    public static let businessId = "6364c00fb25244fcf46425f0"
    public static let shared = MediaFilesViewModel()
    var allData : [MediaFiles] = []
    var isLoading : Bool = false
    var onChange : (()->Void)?
    

    func search(query:String) {
        
    }
    
    func findByFeatureId(id:String)->MediaFiles? {
        var fileToReturn : MediaFiles? = nil
        allData.forEach { file in
            if(file.featureObjectID == id){
                fileToReturn = file
            }
        }
        return fileToReturn
    }
    
    
    func fetchData(){
        isLoading = true
        var request : [String:Any] = [:]
        let user = Preferences.shared.dictionary(forKey: Key.userCredentials)
        if let id = user[Key._id] as? String,let mobile = user[Key.mobileNumber]{
            request[Key.userId] = id
            request[Key.businessID] = AuthenticationViewModel.shared.businessId
            request[Key.mobileNumber] = mobile
        }
        SocketService.shared.send(eventName: MediaFilesSocketEvent.RETRIEVE.rawValue, payload: request)
    }
}


