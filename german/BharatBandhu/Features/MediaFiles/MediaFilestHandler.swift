//
//  CustomerHandler.swift
//  Solute
//
//  Created by Vivek Singh on 13/12/22.
//

import Foundation
import SocketIO
class MediaFilesHandler{
    public static let shared =  MediaFilesHandler()
    
    func addFeature(manager: SocketManager?) {
        manager?.defaultSocket.on(MediaFilesSocketEvent.RETRIEVE.rawValue, callback: onRetrieve)
    }
    
    func onRetrieve(_ data : Array<Any>,_ emitter : SocketAckEmitter){
        MediaFilesViewModel.shared.isLoading = false
        let decoder = JSONDecoder()
        if let data = data.first as? [String:Any]{
            if let payload = data[Key.payload] as? [[String:Any]]{
                payload.forEach { item in
                    do {
                        let data = try JSONSerialization.data(withJSONObject: item, options: .prettyPrinted)
                        let dataObj = try decoder.decode(MediaFiles.self, from: data)
                        MediaFilesViewModel.shared.allData.append(dataObj)
                    } catch {
                        print(error.localizedDescription)
                    }
                }
                MediaFilesViewModel.shared.onChange?()
            }
        }else{
            //            AuthenticationViewModel.shared.setRegisterScreen()
        }
    }
}
