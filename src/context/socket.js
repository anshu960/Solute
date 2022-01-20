import * as React from 'react';
import server_config from "./../config/server_config";
import socketio from "socket.io-client";
import environment from "./../../../environment"
// const socket = socketio.connect(process.env.IS_PRODUCTION ? server_config.releaseSocketServer : server_config.devSocketServer);
const socket = socketio.connect(environment.socket);

const SocketContext = React.createContext();
export {socket,SocketContext};

