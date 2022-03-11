import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';
import { uploadProductImage } from './fileUpload';

const defaultProductFields = {
  UserID:"",
  BusinessID:"",
  ProductID:"",
  Name: "",
  Price: '',
  PlatformCharge: '',
  FinalPrice: '',
  CostPrice: '',
  IGST: '',
  CGST: '',
  SGST: '',
  VAT:  '',
  CESS: '',
  IGSTValue: '',
  CGSTValue: '',
  SGSTValue: '',
  VATValue:  '',
  CESSValue: '',
  Tax:  '',
  MRP:  '',
  Description: "",
  ManageInventory: false,
  TaxIncluded:false,
  HSN: "",
}


const slice = createSlice({
  name: 'allProduct',
  initialState: {
    allProduct: [],
    selectedProduct:{},
    updateProduct:{},
    newProduct:defaultProductFields
  },
  reducers: {
    retriveProductSuccess: (state, action) => {
      state.allProduct = action.payload;
    },
    updateProductSuccess: (state, action) => {
      state.updateProduct = action.payload;
    },
    setSelectedProductSuccess: (state, action) => {
      state.selectedProduct = action.payload;
    },
    setSelectedProductFieldSuccess: (state, action) => {
      state.selectedProduct = {...state.selectedProduct,...action.payload};
    },
    setNewProductSuccess: (state, action) => {
      state.newProduct = action.payload;
    },
    setNewProductFieldSuccess: (state, action) => {
      state.newProduct = {...state.newProduct,...action.payload};
    },
  },
});

export default slice.reducer

// Actions

const { retriveProductSuccess,setSelectedProductSuccess,updateProductSuccess,setNewProductSuccess,setNewProductFieldSuccess ,setSelectedProductFieldSuccess} = slice.actions

export const retriveProduct = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_PRODUCT,request,(data)=>{
      console.log("RETRIVE_PRODUCT",data)
      dispatch(retriveProductSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const updateProduct = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.UPDATE_PRODUCT,request,(data)=>{
      console.log("UPDATE_PRODUCT",data)
      if(data.Success){
        dispatch(retriveProduct(request));
        toast("Updated Successfully");
      }else{
        toast("Oops!, please try after sometime");
      }
      dispatch(updateProductSuccess(data.Payload));
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
export const updateProductImage = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.UPDATE_PRODUCT_IMAGE,request,(data)=>{
      console.log("UPDATE_PRODUCT_IMAGE",data)
      if(data.Success){
        toast("Image Uploaded Successfully");
      }else{
        toast("Oops!, please try after sometime");
      }
      dispatch(updateProductSuccess(data.Payload));
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

export const createProduct = (fields,files,callback) => async dispatch => {
  try {
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {...fields}
    request.UserID = UserID
    request.BusinessID = BusinessID
    request.Price=request.Price ? parseFloat(request.Price): 0.00
    request.Tax=request.Tax ? parseFloat(request.Tax): 0.00
    request.CostPrice=request.CostPrice ? parseFloat(request.CostPrice): 0.00
    request.MRP=request.MRP ? parseFloat(request.MRP): 0.00
    request.FinalPrice=request.FinalPrice ? parseFloat(request.FinalPrice): request.Price
    request.HSN=request.HSN && request.HSN !== "" ? request.HSN : undefined
    console.log(request);
    SendEvent(SocketEvent.CREATE_PRODUCT,request,(data)=>{
      if(data && data.Payload && data.Payload._id){
        toast.success("Product created successfully");
      }else{
        toast.error("Unable to create product, please try after some time");
      }
      if(callback){
        callback(data);
      }
      if(files.length){
        files.forEach(file => {
          dispatch(uploadProductImage(file,data.Payload))  
        });
      }else{
        toast("No Images")
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
      dispatch(setSelectedProductSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setNewProduct = (product) => async dispatch => {
  try {
      dispatch(setNewProductSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const setNewProductProperty = (product) => async dispatch => {
  try {
      dispatch(setNewProductFieldSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const updateSelectedProductProperty = (product) => async dispatch => {
  try {
      dispatch(setSelectedProductFieldSuccess(product));
  } catch (e) {
    return console.error(e.message);
  }
}

export const calculateProductCost = (product) => async dispatch=>{
  try {
    let newProduct = product;
    newProduct.FinalPrice = product.Price
    dispatch(setNewProductSuccess(newProduct));
} catch (e) {
  return console.error(e.message);
}
}