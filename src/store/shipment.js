import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
  name: 'Shipment',
  initialState: {
    allShipment: [],
    newShipment:{},
    selectedShipment:{}
  },
  reducers: {
    retriveShipmentSuccess: (state, action) => {
      state.allShipment = action.payload;
    },
    createShipmentSuccess: (state, action) => {
      state.newShipment = action.payload;
    },
    setSelectShipmentSuccess: (state, action) => {
      state.selectedShipment = action.payload;
    },
  },
});

export default slice.reducer

const { retriveShipmentSuccess,createShipmentSuccess ,setSelectShipmentSuccess} = slice.actions

export const retriveShipment = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.SHIPMENT_RETRIVE,request,(data)=>{
      console.log("RETRIVE_Shipment",data)
      dispatch(retriveShipmentSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const createShipment = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.SHIPMENT_CREATE,request,(data)=>{
      console.log("SHIPMENT_CREATE",data)
      dispatch(createShipmentSuccess(data.Payload));
      if(data.Payload && data.Payload._id){
        toast("Shipment Created Successfully")
      }else{
        toast("Oops, Shipment couldn't be create, please check all the imputs and try again")
      }
      if(callback){
        callback(data.Payload)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const selectShipment = (Shipment) => async dispatch => {
  try {
      dispatch(setSelectShipmentSuccess(Shipment));
  } catch (e) {
    return console.error(e.message);
  }
}