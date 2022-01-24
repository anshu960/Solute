import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

// Slice

const slice = createSlice({
  name: 'receipt',
  initialState: {
    allReceipt: [],
    receipt:{
      sale: [],
      invoice: {},
      allInvoices:[],
      business: {},
      Customer:{}
  },
    newReceipt:{
        sale: [],
        invoice: {},
        allInvoices:[],
        business: {},
        Customer:{},
    },
    selectedReceipt:{}
  },
  reducers: {
    retriveReceiptSuccess: (state, action) => {
      state.allReceipt = action.payload;
    },
    createReceiptSuccess: (state, action) => {
      state.newReceipt = action.payload;
    },
    retriveAllReceiptSuccess: (state, action) => {
      state.allInvoices = action.payload;
    },
    retriveSingleReceiptSuccess: (state, action) => {
      state.receipt = action.payload;
    },
    setSelectReceiptSuccess: (state, action) => {
      state.selectedReceipt = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { retriveReceiptSuccess,createReceiptSuccess ,setSelectReceiptSuccess,retriveSingleReceiptSuccess} = slice.actions

export const retriveReceipt = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_Receipt,request,(data)=>{
      console.log("RETRIVE_Receipt",data)
      dispatch(retriveReceiptSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const createReceipt = (sales,callback) => async dispatch => {
  try {

    const Business = getBusiness();
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    let SalesID = [];
    let TotalAmount = 0;
    let selectedCustomer = global.selectedCustomer;
    sales.forEach(item => {
        SalesID.push(item._id);
        TotalAmount += item.FinalPrice;
    });
    let request = {
      UserID,
      BusinessID,
      SalesID,
      Customer:selectedCustomer,
      Sales:sales,
      Business:Business,
      TotalAmount,
    }

    SendEvent(SocketEvent.GENERATE_CUSTOMER_INVOICE,request,(data)=>{
      console.log("GENERATE_CUSTOMER_INVOICE",data)
      if(data && data.Payload && data.Sales && data.Payload._id){
            dispatch(createReceiptSuccess({
                sale: data.Sales,
                invoice: data.Payload,
                business: Business,
                Customer:data.Customer
            }));
        }else{
        console.log("Unable to find products, please try after some time",data);
        }
      if(callback){
        callback(data.Payload)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const retriveAllReceipt = (startDate,endDate,callback) => async dispatch => {
  try {

    const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {UserID,BusinessID,
            StartDate:startDate.toISOString().substring(0, 10),
            EndDate:endDate.toISOString().substring(0, 10)
        }
        SendEvent(SocketEvent.RETRIVE_INVOICE,request,(data)=>{
          console.log("RETRIVE_INVOICE",data)
          if(data && data.Payload && data.Sales && data.Payload._id){
            dispatch(createReceiptSuccess({
                invoice: data.Payload,
            }));
        }else{
        console.log("Unable to find products, please try after some time",data);
        }
      if(callback){
        callback(data.Payload)
      }
        })
    
  } catch (e) {
    return console.error(e.message);
  }
}

export const retriveSingleReceipt = (InvoiceNumber,callback) => async dispatch => {
  try {

    const UserID = getUserId();
    let request = {InvoiceNumber,UserID}
    SendEvent(SocketEvent.RETRIVE_SINGLE_INVOICE,request,(data)=>{
          console.log("RETRIVE_SINGLE_INVOICE",data)
          if(data && data.Payload && data.Sales && data.Payload._id){
            dispatch(retriveSingleReceiptSuccess({
              sale: data.Sales,
              invoice: data.Payload,
              business: data.Business,
              Customer:data.Customer
            }));
        }else{
        console.log("Unable to find receipt, please try after some time",data);
        }
      if(callback){
        callback(data.Payload)
      }
        })
    
  } catch (e) {
    return console.error(e.message);
  }
}
export const selectReceipt = (Receipt) => async dispatch => {
  try {
      dispatch(setSelectReceiptSuccess(Receipt));
  } catch (e) {
    return console.error(e.message);
  }
}