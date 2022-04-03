import { createSlice } from '@reduxjs/toolkit'
import { socket } from '../context/socket';
import { getUserId } from '../services/authService';
import SocketEvent, { connect } from '../socket/SocketEvent';
import { SendEvent } from "./../socket/SocketHandler";

const slice = createSlice({
  name: 'WhatsApp',
  initialState: {
    WhatsApp: "",
    QR:""
  },
  reducers: {
    connectRoomSuccess: (state, action) => {
        state.QR = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { connectWhatsappRoomSuccess,connectRoomSuccess } = slice.actions

export const connectWhatsapp = () => dispatch => {
  try {
    socket.on(SocketEvent.JOIN_WHATSAPP_ROOM,(data)=>{
      console.log("socket.on JOIN_WHATSAPP_ROOM \n",data)
      dispatch(connectRoomSuccess(data.QRCode || ""));
    })
    setTimeout(()=>{
      const userId = getUserId();
      SendEvent(SocketEvent.JOIN_WHATSAPP_ROOM,{UserID:userId},(data)=>{
        console.log("JOIN_WHATSAPP_ROOM \n",data)
        dispatch(connectRoomSuccess(data.QRCode || ""));
      })
    },2000)

  } catch (e) {
    return console.error(e.message);
  }
}