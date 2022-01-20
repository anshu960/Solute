import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

import { retriveProduct } from './product';
import { retriveCustomer } from './customer';
import { retriveHSN } from './hsn';
import { retriveEmployee } from './employee';
import { getBusinessId, getUserId } from '../services/authService';

const defaultSelectedTransaction = {
  Quantity: 0,
  vehicleNo: '',
  mode: 'CASH',
  FinalPrice: 0,
    CostPrice: 0,
    IGST:0,
    CGST:0,
    SGST:0,
    VAT:0,
    CESS:0,
    Tax: 0,
    MRP: 0,
  selectedProduct: {
    UserID:"",
    BusinessID:"",
    ProductID:"",
    Name:"",
    Price: 0,
    PlatformCharge: 0,
    FinalPrice: 0,
    CostPrice: 0,
    IGST:0,
    CGST:0,
    SGST:0,
    VAT:0,
    CESS:0,
    Tax: 0,
    MRP: 0,
    Description: "",
    ManageInventory: false,
    TaxIncluded:false,
    HSN: "",
  }
}

const slice = createSlice({
  name: 'Sale',
  initialState: {
    allSale: [],
    selectedSale: {},
    selectedSaleType: {},
    totalSaleAmount: 0,
    totalExpenseAmount: 0,
    selectedTransaction:defaultSelectedTransaction
  },
  reducers: {
    retriveSaleSuccess: (state, action) => {
      state.allSale = action.payload;
    },
    setSelectedSaleSuccess: (state, action) => {
      state.selectedSale = action.payload;
    },
    setSelectedSaleTypeSuccess: (state, action) => {
      state.selectedSaleType = action.payload;
    },
    setSelectedTransactionSuccess: (state, action) => {
      state.selectedTransaction = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { retriveSaleSuccess,setSelectedSaleSuccess,setSelectedSaleTypeSuccess ,setSelectedTransactionSuccess} = slice.actions

export const retriveSale = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_Sale,request,(data)=>{
      console.log(data)
      dispatch(retriveSaleSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const setSelectedSale = (Sale,SaleTypes,callback) => async dispatch => {
  try {
      dispatch(setSelectedSaleSuccess(Sale));
      if(SaleTypes && SaleTypes.length){
        const selectedSaleType = SaleTypes.filter((SaleType)=>SaleType._id === Sale.SaleTypeID)
        if(selectedSaleType && selectedSaleType.length && selectedSaleType[0]._id === Sale.SaleTypeID){
          setSelectedSaleTypeSuccess(selectedSaleType[0]);
          if(callback){
            callback(selectedSaleType[0]);
          }
        }  
      }
      dispatch(syncSaleData());
  } catch (e) {
    return console.error(e.message);
  }
}

export const syncSaleData = () => async dispatch => {
  try {
      const UserID = getUserId();
      const BusinessID = getBusinessId()
      const request = {UserID,BusinessID}
      dispatch(retriveProduct(request));
      dispatch(retriveCustomer(request));
      dispatch(retriveHSN(request));
      dispatch(retriveEmployee(request));
  } catch (e) {
    return console.error(e.message);
  }
}


export const setSelectedSaleType = (SaleType) => async dispatch => {
  try {
      dispatch(setSelectedSaleTypeSuccess(SaleType));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setSelectedTransaction = (transaction) => async dispatch => {
  try {
      if(transaction){
        dispatch(setSelectedTransactionSuccess(transaction));
      }else{
        dispatch(setSelectedTransactionSuccess(defaultSelectedTransaction));
      }
      
  } catch (e) {
    return console.error(e.message);
  }
}

