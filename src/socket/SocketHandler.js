import {
    useCallback
} from 'react';
import {
    socket
} from "./../context/socket";
import SocketEvent from './SocketEvent';

function AddAllEventListener() {
    global.socket = socket
    if(!global.socketListeners){
        global.socketListeners = {};
    }
    socket.on(SocketEvent.connect,useCallback((data) => {
        alert("Connected");
        console.log("Socket Callback",SocketEvent.connect,"data", data);
    }, []))
    socket.on(SocketEvent.JOIN_ROOM,useCallback((data) => {
        console.log("Socket Callback",SocketEvent.JOIN_ROOM,"data", data);
        
    }, []))
}

 function SendEvent(event, data, callback){
    socket.once(event,callback)
    socket.emit(event, data)
}

function JoinRoom(event, data, callback){
    socket.once(event,callback)
    socket.emit(event, data)
}

export {
    AddAllEventListener,
    SendEvent,
    JoinRoom
};