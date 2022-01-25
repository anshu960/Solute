import { createSlice } from '@reduxjs/toolkit'
import { getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

// Slice

const slice = createSlice({
  name: 'BusinessAnalytics',
  initialState: {
    saleToday: [],
  },
  reducers: {
    retriveSaleTodaySuccess: (state, action) => {
      state.saleToday = action.payload;
    }
  },
});

export default slice.reducer

// Actions

const { retriveSaleTodaySuccess } = slice.actions

export const retriveTodaysSaleAnalytics = (request,callback) => async dispatch => {
  try {
    console.log("XXXXXXXXX Business Analytics request ",request)
    SendEvent(SocketEvent.BUSINESS_ANALYTICS_BUSINESS_SALE,request,(data)=>{
        console.log("XXXXXXXXX Business Analytics response ",data)
      if(data.Payload && data.Payload._id){ 
        dispatch(retriveSaleTodaySuccess([data.Payload]));
      }
      if(callback){
        callback(data)
      }
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const getBusinessAnalytics = (allBusiness,index,oldAnalytics) => async dispatch => {
    try {
        const UserID = getUserId()
        let allSaleToday = oldAnalytics || []
        if(allBusiness.length){
            var currentIndex = index || 0
            const request = {UserID,BusinessID:allBusiness[currentIndex]._id}
            dispatch(retriveTodaysSaleAnalytics(request,(data)=>{
                if(data && data.Payload && data.Payload._id){
                    allSaleToday.push({
                        _id : data.Payload._id,
                        TotalSale : data.Payload.TotalSales
                    })
                }
                if(currentIndex+1 < allBusiness.length){
                    dispatch(getBusinessAnalytics(allBusiness,currentIndex+1,allSaleToday))
                }else{
                    dispatch(retriveSaleTodaySuccess(allSaleToday));
                }
            }));       
        }
    } catch (e) {
      return console.error(e.message);
    }
  }