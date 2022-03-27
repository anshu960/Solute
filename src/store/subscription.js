import { createSlice } from '@reduxjs/toolkit'
import { getBusiness, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
    name: 'Subscription',
    initialState: {
      allSubscriptionResults: [],
      users: [],
      subscriptionOrder:{}
    },
    reducers: {
      SubscriptionSuccess: (state, action) => {
        state.allSubscriptionResults = action.payload;
      },
      SubscriptionUserSuccess: (state, action) => {
        state.users = action.payload;
      },
      subscriptionOrderSuccess: (state, action) => {
        state.users = action.payload;
      },
    },
  });
  
  export default slice.reducer
  
  const { SubscriptionSuccess,SubscriptionUserSuccess ,subscriptionOrderSuccess} = slice.actions

export const createRazorpayOrder = (amount,callBack) => async dispatch => {
    try {
      let business = getBusiness();
      let BusinessID = business._id
      let UserID = getUserId()
      let request = {Amount:amount,UserID,BusinessID}
      SendEvent(SocketEvent.CREATE_SUBSCRIPTION_ORDER,request,(data)=>{
        console.log("CREATE_SUBSCRIPTION_ORDER \n",JSON.stringify(data))
        dispatch(subscriptionOrderSuccess(data.Payload));
        if(callBack){
            callBack(data.Payload);
        }
      })
    } catch (e) {
      return console.error(e.message);
    }
  }

  export const subscribeToPlan = (plan) => async dispatch => {
    try {
        dispatch(createRazorpayOrder(plan.amount,(razorpayOrder)=>{

        }));
    } catch (e) {
      return console.error(e.message);
    }
  }
