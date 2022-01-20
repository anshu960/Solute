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
    shipment
})
const store = configureStore({
  reducer,
})
export default store;