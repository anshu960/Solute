import { createSlice } from '@reduxjs/toolkit'
import { socket } from '../context/socket';
import { getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import {JoinRoom, SendEvent } from "./../socket/SocketHandler";
import { syncPlatformStatistics } from './Statistics';

const isConnected = false

const slice = createSlice({
  name: 'isConnected',
  initialState: {
    isConnected: isConnected,
  },
  reducers: {
    connectRoomSuccess: (state, action) => {
        console.log("XXXXXXX connectRoomSuccess payload ",action.payload)
      state.isConnected = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { connectRoomSuccess } = slice.actions

export const connect = () => async dispatch => {
  try {
    const userId = getUserId();
    socket.on("connect",()=>{
      console.log("XXXX RoomConnected");
      dispatch(connectRoomSuccess(true));
      JoinRoom(SocketEvent.JOIN_ROOM,{"ROOM_ID":userId},()=>{
        dispatch(syncPlatformStatistics()) 
      })
    });
    socket.connect();
  } catch (e) {
    return console.error(e.message);
  }
}