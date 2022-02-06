import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
  name: 'Shipment',
  initialState: {
    allShipment: [],
    newShipment:{},
    selectedShipment:{},
    newShipmentStatus:{},
    allShipmentStatus:[],
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
    createShipmentStatusSuccess: (state, action) => {
      state.newShipmentStatus = action.payload;
    },
    retriveShipmentStatusSuccess: (state, action) => {
      state.allShipmentStatus = action.payload;
    },
  },
});

export default slice.reducer

const { retriveShipmentSuccess,createShipmentSuccess ,setSelectShipmentSuccess,createShipmentStatusSuccess,retriveShipmentStatusSuccess} = slice.actions

export const retriveShipment = (startDate,endDate) => async dispatch => {
  try {
    const request = {
      BusinessID:getBusinessId(),
      UserID:getUserId(),
      startDate,endDate
    }
    SendEvent(SocketEvent.SHIPMENT_RETRIVE,request,(data)=>{
      console.log("SHIPMENT_RETRIVE",data)
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


export const createShipmentStatus = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.SHIPMENT_STATUS_CREATE,request,(data)=>{
      console.log("SHIPMENT_STATUS_CREATE",data)
      dispatch(createShipmentStatusSuccess(data.Payload));
      if(data.Payload && data.Payload._id){
        toast("Shipment Stopage Created Successfully")
      }else{
        toast("Oops, Shipment Stopage couldn't be create, please check all the imputs and try again")
      }
      if(callback){
        callback(data.Payload)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const retriveShipmentStatus = (ShipmentNumber,callback) => async dispatch => {
  try {
    const request = {
      ShipmentNumber:ShipmentNumber,
      UserID:getUserId(),
    }
    SendEvent(SocketEvent.SHIPMENT_STATUS_RETRIVE,request,(data)=>{
      console.log("SHIPMENT_STATUS_RETRIVE",data)
      dispatch(retriveShipmentStatusSuccess(data.Payload));
      if(callback){
        callback(data.Payload)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}