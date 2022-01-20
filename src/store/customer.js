import { createSlice } from '@reduxjs/toolkit'
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
  },
});

export default slice.reducer

// Actions

const { retriveCustomerSuccess,setSelectedCustomerSuccess } = slice.actions

export const retriveCustomer = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,(data)=>{
      console.log("RETRIVE_CUSTOMER",data)
      dispatch(retriveCustomerSuccess(data.Payload));
    })
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