import * as React from 'react';
import { useEffect} from "react"
import {routes, router} from './routes';
import ThemeConfig from './theme';
import ScrollToTop from './components/ScrollToTop';
import {useDispatch, useSelector} from 'react-redux'
import {connect} from './store/room';
import { loadRazorpay } from './helper/payment_helper';

// ----------------------------------------------------------------------
export default function App() {
  const dispatch = useDispatch()
  const isConnected = useSelector(state => state.room.isConnected)
  console.log("isConnected",isConnected);

  useEffect(()=>{
    dispatch(connect());
    loadRazorpay();
    
  },[])

  return (
      <ThemeConfig>
        <ScrollToTop />
        {router(routes)}
      </ThemeConfig>
  );
}