import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

// Slice

const slice = createSlice({
  name: 'allHSN',
  initialState: {
    allHSN: [],
    newHSN:{},
    selectedHSN:{}
  },
  reducers: {
    retriveHSNSuccess: (state, action) => {
      state.allHSN = action.payload;
    },
    createHSNSuccess: (state, action) => {
      state.newHSN = action.payload;
    },
    setSelectHSNSuccess: (state, action) => {
      state.selectedHSN = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { retriveHSNSuccess,createHSNSuccess ,setSelectHSNSuccess} = slice.actions

export const retriveHSN = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_HSN,request,(data)=>{
      console.log("RETRIVE_HSN",data)
      dispatch(retriveHSNSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const createHSN = (request,callback) => async dispatch => {
  try {
    SendEvent(SocketEvent.CREATE_HSN,request,(data)=>{
      console.log("CREATE_HSN",data)
      dispatch(createHSNSuccess(data.Payload));
      if(data.Payload && data.Payload._id){
        toast("HSN Created Successfully")
      }else{
        toast("Oops, HSN couldn't be create, please check all the imputs and try again")
      }
      if(callback){
        callback(data.Payload)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const setSelectedHSN = (hsn) => async dispatch => {
  try {
      dispatch(setSelectHSNSuccess(hsn));
  } catch (e) {
    return console.error(e.message);
  }
}