//
//  SocketService.swift
//  Solute
//
//  Created by Vivek Singh on 07/12/22.
//

import Foundation
import SocketIO
class SocketService:ObservableObject{
    
    public static let shared = SocketService()
    var socket: SocketIOClient? = nil
    var manager: SocketManager? = nil
    
    @Published var connectionStatus = 0
    var delegate : SocketServiceDelegate? = nil
    let config : SocketIOClientConfiguration = [
        .log(true),
        //        .compress,
        //        .forceNew(true),
        //        .forceWebsockets(true),
            .forcePolling(false),
    ]
    init() {
        manager = SocketManager(socketURL: URL(string: Server.shared.servers[0])!, config: config)
    }
    
    func connect() {
        socket = manager?.defaultSocket
        addEventListener()
        socket?.manager?.connect()
    }
    
    func addEventListener() {
        socket?.on(clientEvent: .connect, callback: { any, socketAckEmitter in
            self.delegate?.didConnect()
            self.connectionStatus = 1
            if let userId = AuthenticationHandler.shared.fetchUserCredentials()[Key._id] as? String{
                self.joinRoom(roomId: userId)
            }else{
                self.joinRoom(roomId: AuthenticationViewModel.shared.deviceId)
            }
        })
        socket?.on(clientEvent: .disconnect, callback: { any, socketAckEmitter in
            self.delegate?.didConnect()
            self.connectionStatus = 0
        })
        socket?.on(clientEvent: .error, callback: { data, socketAckEmitter in
            self.delegate?.didConnect()
            self.connectionStatus = 0
        })
        AuthenticationHandler.shared.addFeature(manager: self.manager)
    }
    
    func removeEventListener(){
        
    }
    func joinRoom(roomId:String) {
        self.socket?.emit(SocketEvent.joinRoom.rawValue, with: [[Key.roomId:roomId]])
    }
    func send(eventName:String,payload:[String:Any]) {
        self.socket?.emit(eventName, with: [payload])
    }
    
    
}
