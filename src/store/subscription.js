import { createSlice } from '@reduxjs/toolkit'
import { getBusiness, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';
import ImageConfig from "./../config/image_config";
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
            showRazorpay(razorpayOrder)
        }));
    } catch (e) {
      return console.error(e.message);
    }
  }

  const showRazorpay=(transaction)=>{
    const { amount, id: order_id, currency } = transaction;
    const business = getBusiness()
    const options = {
      key: 'rzp_test_iABvXOSIa0Dv3B', // Enter the Key ID generated from the Dashboard
      amount: amount.toString(),
      currency: currency,
      name: 'Solute',
      description: 'Membership',
      image: { uri:ImageConfig.Logo },
      order_id: order_id,
      handler: (response) =>{
        const data = {
          orderCreationId: order_id,
          razorpayPaymentId: response.razorpay_payment_id,
          razorpayOrderId: response.razorpay_order_id,
          razorpaySignature: response.razorpay_signature,
        };

        // const result = await axios.post('/payment/success', data);

        alert(data.msg);
      },
      
      prefill: {
        name: business.Name,
        email: business.Email,
        contact: business.MobileNumber,
      },
      notes: {
        address: business.Address,
      },
      theme: {
        color: '#61dafb',
      },
    };

    const paymentObject = new window.Razorpay(options);
    paymentObject.open();
  }