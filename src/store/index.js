import { configureStore } from '@reduxjs/toolkit'
import { combineReducers } from 'redux'
import business from "./business";
import businessTypes from "./businessTypes";
import room from "./room"
import product from "./product"
import customer from "./customer"
import hsn from './hsn';
import receipt from './receipt';
import employee from './employee';
import invoice from "./invoice";
import sale from "./sale";
import shipment from "./shipment";
import business_analytics from './business_analytics';
import search from "./search";
const reducer = combineReducers({
    business,
    businessTypes,
    room,
    product,
    customer,
    employee,
    hsn,
    receipt,
    invoice,
    sale,
    shipment,
    business_analytics,
    search
})
const store = configureStore({
  reducer,
})
export default store;