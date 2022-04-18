import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

import { retriveProduct } from './product';
import { retriveCustomer } from './customer';
import { retriveHSN } from './hsn';
import { retriveEmployee } from './employee';
import { getBusinessId, getUserId } from '../services/authService';
import { toast } from 'react-toastify';


const slice = createSlice({
  name: 'Stock',
  initialState: {
    allStock: [],
    selectedStock: {}
  },
  reducers: {
    retriveStockSuccess: (state, action) => {
      state.allStock = action.payload;
    },
    setSelectedStockSuccess: (state, action) => {
      state.selectedStock = action.payload;
    },
    setSelectedStockTypeSuccess: (state, action) => {
      state.selectedStockType = action.payload;
    }
  },
});

export default slice.reducer

// Actions

const { retriveStockSuccess,setSelectedStockSuccess,setSelectedStockTypeSuccess } = slice.actions

export const createStockEntry = (entry,callback) => async dispatch => {
  try {
    let request = {...entry,UserID:getUserId(),BusinessID:getBusinessId(),_id:undefined,ProductID:entry._id}
    if(request.DecreaseQuantity){
        request.DecreaseQuantity = parseInt(request.DecreaseQuantity)
      }else{
        request.DecreaseQuantity = 0
      }
      if(request.IncreaseQuantity){
        request.IncreaseQuantity = parseInt(request.IncreaseQuantity)
      }else{
        request.IncreaseQuantity = 0
      }
      if(request.TotalQuantity){
        request.TotalQuantity = parseInt(request.TotalQuantity)
      }
    SendEvent(SocketEvent.CREATE_STOCK_ENTRY,request,(data)=>{
      console.log(data)
      if(data.Payload && data.Payload._id){
          toast("Stock Entry Updated Successfully")
        if(callback){
            dispatch(retriveStock(entry));
            callback()
        }
      }else{
        toast("Oops, Something went wrong, please try after sometime")
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}


export const retriveStock = (product) => async dispatch => {
    try {
    let request = {ProductID:product._id,UserID:getUserId(),BusinessID:getBusinessId()}
    console.log("RETRIVE_STOCK_ENTRY request = ",request)
      SendEvent(SocketEvent.RETRIVE_STOCK_ENTRY,request,(data)=>{
        console.log(data)
        dispatch(retriveStockSuccess(data.Payload));
      })
    } catch (e) {
      return console.error(e.message);
    }
  }


export const syncStockData = () => async dispatch => {
  try {
      const UserID = getUserId();
      const BusinessID = getBusinessId()
      const request = {UserID,BusinessID}
      dispatch(retriveStock(request));
  } catch (e) {
    return console.error(e.message);
  }
}


