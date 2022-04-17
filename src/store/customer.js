import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

// Slice

const slice = createSlice({
  name: 'Customer',
  initialState: {
    allCustomer: [],
    selectedCustomer:{}
  },
  reducers: {
    retriveCustomerSuccess: (state, action) => {
      state.allCustomer = action.payload;
    },
    setSelectedCustomerSuccess: (state, action) => {
      state.selectedCustomer = action.payload;
    },
    addNewCustomerSuccess: (state, action) => {
      state.allCustomer = [...state.allCustomer,action.payload] 
    },
  },
});

export default slice.reducer

// Actions

const { retriveCustomerSuccess,setSelectedCustomerSuccess,addNewCustomerSuccess } = slice.actions

export const retriveCustomer = (request) => async dispatch => {
  try {
    console.log("RETRIVE_CUSTOMER request",request)
    SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,(data)=>{
      console.log("RETRIVE_CUSTOMER",data)
      dispatch(retriveCustomerSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const addNewCustomer = (customer) => async dispatch => {
  try {
    dispatch(addNewCustomerSuccess(customer));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setSelectedCustomer = (customer) => async dispatch => {
  try {
    dispatch(setSelectedCustomerSuccess(customer));
  } catch (e) {
    return console.error(e.message);
  }
}

export const findCustomerByMobile = (mobile,callback) => async dispatch => {
  try {
    let request = {
      UserID:getUserId(),
      BusinessID:getBusinessId(),
      MobileNumber:mobile
    }
    console.log("FIND_CUSTOMER_BY_MOBILE request",request)
    SendEvent(SocketEvent.FIND_CUSTOMER_BY_MOBILE,request,(data)=>{
      console.log("FIND_CUSTOMER_BY_MOBILE",data)
      let customer = {}
      if(data.Payload && data.Payload.length){
        customer = data.Payload[0]
        // dispatch(setSelectedCustomerSuccess(customer));
      }else{
        toast("Customer Not Found")
      }
      if(callback){
        callback(customer)
      }
      
    })
  } catch (e) {
    return console.error(e.message);
  }
}