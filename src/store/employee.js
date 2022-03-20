import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';
import { syncBusinessData } from './business';

// Slice

const slice = createSlice({
  name: 'Employee',
  initialState: {
    allEmployee: [],
    selectedEmployee:{},
    allEmployeeConnectionsRequest:[],
    selectedEmployeeConnectionsRequest:{}
  },
  reducers: {
    retriveEmployeeSuccess: (state, action) => {
      state.allEmployee = action.payload;
    },
    setSelectedEmployeeSuccess: (state, action) => {
      state.selectedEmployee = action.payload;
    },
    setAllEmployeeConnectionsRequest: (state, action) => {
      state.allEmployeeConnectionsRequest = action.payload;
    },
    setSelectedEmployeeConnectionsRequest: (state, action) => {
      state.selectedEmployeeConnectionsRequest = action.payload;
    },
  },
});

export default slice.reducer

const { 
  retriveEmployeeSuccess,
  setSelectedEmployeeSuccess,
  setAllEmployeeConnectionsRequest,
  setSelectedEmployeeConnectionsRequest
 } = slice.actions

export const retriveEmployee = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_EMPLOYEE,request,(data)=>{
      dispatch(retriveEmployeeSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const setSelectedEmployee = (employee) => async dispatch => {
  try {
      dispatch(setSelectedEmployeeSuccess(employee));
  } catch (e) {
    return console.error(e.message);
  }
}


export const sendConnectionRequest = (user,callback) => async dispatch => {
  try {
    const request = {...user}
    request.EmployeeUserID = user._id
    request._id = undefined
    request.UserID = getUserId()
    request.BusinessID = getBusinessId()
    request.Business = getBusiness()
    SendEvent(SocketEvent.CREATE_EMPLOYEE_CONNECTION_REQUEST,request,(data)=>{
      console.log("Response  CREATE_EMPLOYEE_CONNECTION_REQUEST data = ",data)
      if(data.Payload && data.Payload._id){
        toast("Connection Request sent successfully")
      }else{
        toast("Oops, Something went wrong, please try after sometime")
      }
      if(callback){
        callback(data.Payload)
      }
      dispatch(setSelectedEmployeeConnectionsRequest(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const acceptConnectionRequest = (notificaioon,callback) => async dispatch => {
  try {
    const request = {Notification:notificaioon}
    request.UserID = getUserId()
    SendEvent(SocketEvent.ACCEPT_EMPLOYEE_CONNECTION_REQUEST,request,(data)=>{
      console.log("Response  ACCEPT_EMPLOYEE_CONNECTION_REQUEST data = ",data)
      if(data.Payload && data.Payload._id){
        toast("Connection Request Accepted successfully")
        dispatch(syncBusinessData());
      }else{
        toast("Oops, Something went wrong, please try after sometime")
      }
      if(callback){
        callback(data.Payload)
      }
      dispatch(setSelectedEmployeeConnectionsRequest(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const rejectConnectionRequest = (notificaioon,callback) => async dispatch => {
  try {
    const request = {Notification:notificaioon}
    request.UserID = getUserId()
    SendEvent(SocketEvent.DELETE_EMPLOYEE_CONNECTION_REQUEST,request,(data)=>{
      console.log("Response  DELETE_EMPLOYEE_CONNECTION_REQUEST data = ",data)
      if(data.Payload && data.Payload.deletedCount){
        toast("Connection Request Rejected successfully")
        dispatch(syncBusinessData());
      }else{
        toast("Oops, Something went wrong, please try after sometime")
      }
      if(callback){
        callback(data.Payload)
      }
      dispatch(setSelectedEmployeeConnectionsRequest({}));
    })
  } catch (e) {
    return console.error(e.message);
  }
}