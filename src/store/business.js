import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

import { retriveProduct } from './product';
import { retriveCustomer } from './customer';
import { retriveHSN } from './hsn';
import { retriveEmployee } from './employee';
import { getBusinessId, getUserId, setBusiness } from '../services/authService';
import { toast } from 'react-toastify';
import { getBusinessAnalytics } from './business_analytics';
import { retriveNotification } from './notification';

const slice = createSlice({
  name: 'Business',
  initialState: {
    allBusiness: [],
    selectedBusiness: {},
    selectedBusinessType: {},
  },
  reducers: {
    retriveBusinessSuccess: (state, action) => {
      state.allBusiness = action.payload;
    },
    setSelectedBusinessSuccess: (state, action) => {
      state.selectedBusiness = action.payload;
    },
    setSelectedBusinessTypeSuccess: (state, action) => {
      state.selectedBusinessType = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { retriveBusinessSuccess,setSelectedBusinessSuccess,setSelectedBusinessTypeSuccess } = slice.actions

export const retriveBusiness = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_BUSINESS,request,(data)=>{
      dispatch(retriveBusinessSuccess(data.Payload));
      dispatch(getBusinessAnalytics(data.Payload))
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const setSelectedBusiness = (business,businessTypes,callback) => async dispatch => {
  try {
      dispatch(setSelectedBusinessSuccess(business));
      if(businessTypes && businessTypes.length){
        const selectedBusinessType = businessTypes.filter((businessType)=>businessType._id === business.BusinessTypeID)
        if(selectedBusinessType && selectedBusinessType.length && selectedBusinessType[0]._id === business.BusinessTypeID){
          setSelectedBusinessTypeSuccess(selectedBusinessType[0]);
          if(callback){
            callback(selectedBusinessType[0]);
          }
        }  
      }
      dispatch(syncBusinessData());
  } catch (e) {
    return console.error(e.message);
  }
}

export const syncBusinessData = () => async dispatch => {
  try {
      const UserID = getUserId();
      const BusinessID = getBusinessId();
      const request = {UserID,BusinessID}
      dispatch(retriveProduct(request));
      dispatch(retriveCustomer(request));
      dispatch(retriveHSN(request));
      dispatch(retriveEmployee(request));
      dispatch(retriveNotification())
  } catch (e) {
    return console.error(e.message);
  }
}


export const setSelectedBusinessType = (businessType) => async dispatch => {
  try {
      dispatch(setSelectedBusinessTypeSuccess(businessType));
  } catch (e) {
    return console.error(e.message);
  }
}


export const updateBusinessImage = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.UPDATE_BUSINESS_PROFILE_IMAGE,request,(data)=>{
      console.log("UPDATE_BUSINESS_IMAGE",data)
      if(data.Success){
        toast("Image Uploaded Successfully");
      }else{
        toast("Oops!, please try after sometime");
      }
      setBusiness(request);
      dispatch(setSelectedBusinessSuccess(data.Payload));
      if(callback){
        callback(data);
      }
    })
  } catch (e) {
    if(callback){
      callback({error:e.message});
    }
    return console.error(e.message);
  }
}