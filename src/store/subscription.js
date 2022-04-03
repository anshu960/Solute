import { createSlice } from '@reduxjs/toolkit'
import { toast } from 'react-toastify';
import { getBusiness, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';
import ImageConfig from "./../config/image_config";
const slice = createSlice({
    name: 'Subscription',
    initialState: {
      allSubscriptionResults: [],
      users: [],
      subscriptionOrder:{},
      activeSubscription:{}
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
      retriveActiveSubscriptionSuccess:(state,action)=>{
        state.activeSubscription = action.payload
      }
    },
  });
  
  export default slice.reducer
  
  const { subscriptionOrderSuccess,retriveActiveSubscriptionSuccess} = slice.actions
  export const retriveActiveSubscription=()=>dispatch=>{
    let business = getBusiness();
      let BusinessID = business._id
      let UserID = getUserId()
      let request = {UserID,BusinessID}
      SendEvent(SocketEvent.RETRIVE_ACTIVE_SUBSCRIPTION_PLAN,request,(data)=>{
        console.log("RETRIVE_ACTIVE_SUBSCRIPTION_PLAN \n",JSON.stringify(data))
        dispatch(retriveActiveSubscriptionSuccess(data.Payload));
      })
  } 
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
    console.log('subscribeToPlan ::: ', plan)
    try {
        dispatch(createRazorpayOrder(plan.Amount,(razorpayOrder)=>{
            showRazorpay(razorpayOrder,plan,()=>{
              dispatch(retriveActiveSubscription())
            })
        }));
    } catch (e) {
      return console.error(e.message);
    }
  }

const updateSubscriptionPayment = (paymentOrder,paymentResponse,plan,callBack) => {
    try {
        if(paymentResponse.razorpay_payment_id && paymentResponse.razorpay_order_id && paymentResponse.razorpay_signature){
          let business = getBusiness();
      let BusinessID = business._id
      let UserID = getUserId()
      let request = {...paymentResponse,BusinessID,UserID,
        ...{OrderDetails:paymentOrder}
        ,...{PlanDetails:plan}}
      SendEvent(SocketEvent.UPDATE_SUBSCRIPTION_PAYMENT_RESPONSE,request,(data)=>{
        console.log("UPDATE_SUBSCRIPTION_PAYMENT_RESPONSE \n",JSON.stringify(data))
        if(data.Payload && data.ResponseCode === 200){
          toast("Congrats!, Subscribed successfully")
        }else{
          toast("Oops! Something went wrong")
        }
        if(callBack){
          callBack()
        }
      })
        }else{
          toast("Oops!, Someting went wrong with the payment")
        }
    } catch (e) {
      return console.error(e.message);
    }
  }

  const showRazorpay=(transaction,plan,callBack)=>{
    const business = getBusiness()
    const options = {
      key: transaction.PaymentKey, // Enter the Key ID generated from the Dashboard
      amount: transaction.Amount,
      currency: transaction.Currency,
      name: 'Solute',
      description: 'Membership',
      image: { uri:ImageConfig.Logo },
      order_id: transaction.PaymentGatewayOrderID,
      handler: (response) =>{
        console.log("Payment Response ",response);
        console.log("Payment Response ",JSON.stringify(response));
        updateSubscriptionPayment(transaction,response,plan,callBack)
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