import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const defaultProductStock = {
  UserID:"",
  BusinessID:"",
  ProductID:"",
  Quantity:"",
  AddedQuantity: 0,
  RemovedQuantity: 0,
  Comment: 0,
}


const slice = createSlice({
  name: 'ProductStock',
  initialState: {
    allStock: [],
    selectedProductStock:{},
    updateProductStock:{},
    newProductStock:defaultProductStock
  },
  reducers: {
    retriveProductStockSuccess: (state, action) => {
      state.allProduct = action.payload;
    },
    updateProductStockSuccess: (state, action) => {
      state.updateProduct = action.payload;
    },
    setSelectedProductStockSuccess: (state, action) => {
      state.selectedProduct = action.payload;
    },
    setSelectedProductStockFieldSuccess: (state, action) => {
      state.selectedProduct = {...state.selectedProduct,...action.payload};
    },
    setNewProductStockSuccess: (state, action) => {
      state.newProduct = action.payload;
    },
    setNewProductStockFieldSuccess: (state, action) => {
      state.newProduct = {...state.newProduct,...action.payload};
    },
  },
});

export default slice.reducer

// Actions

const { retriveProductStockSuccess,setSelectedProductStockSuccess,updateProductStockSuccess,setNewProductStockSuccess,setNewProductStockFieldSuccess ,setSelectedProductStockFieldSuccess} = slice.actions

export const retriveProductStock = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_PRODUCT_STOCK,request,(data)=>{
      console.log("RETRIVE_PRODUCT_STOCK",data)
      dispatch(retriveProductStockSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const createProductStock = (fields,callback) => async dispatch => {
  try {
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {...fields,...{UserID,BusinessID}}
    SendEvent(SocketEvent.CREATE_PRODUCT_STOCK,request,(data)=>{
      if(data && data.Payload && data.Payload._id){
        toast.success("Stock updated successfully");
      }else{
        toast.error("Unable to add Stock, please try after some time");
      }
      if(callback){
        callback(data);
      }
    });
      
  } catch (e) {
    if(callback){
      callback({error:e.message});
    }
    return console.error(e.message);
  }
}

export const setSelectedProduct = (product) => async dispatch => {
  try {
      dispatch(setSelectedProductStockSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setNewProduct = (product) => async dispatch => {
  try {
      dispatch(setNewProductStockSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setNewProductProperty = (product) => async dispatch => {
  try {
      dispatch(setNewProductStockFieldSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const updateSelectedProductProperty = (product) => async dispatch => {
  try {
      dispatch(setSelectedProductStockFieldSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const calculateProductCost = (product) => async dispatch=>{
  try {
    let newProduct = product;
    newProduct.FinalPrice = product.Price
    dispatch(setNewProductStockSuccess(newProduct));
} catch (e) {
  return console.error(e.message);
}
}