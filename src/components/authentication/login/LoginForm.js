import * as React from 'react';
import { useEffect, useState,useCallback } from 'react';
import { useHistory } from 'react-router-dom';
// material
import {
  Container
} from '@mui/material';
import SocketEvent from '../../../socket/SocketEvent';
import {JoinRoom,SendEvent} from "./../../../socket/SocketHandler";
import AppLoader from '../../Loader';
import { ToastContainer } from 'react-toastify';
import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth';
import { registerAuthObserver } from '../../../services/firebase/firebaseAuthService';
import { setMobileNumber, setUser, setUserId } from '../../../services/authService';
import firebase from '@firebase/app';
import { PATH_AUTH, PATH_DASHBOARD } from '../../../routes/path';
require('firebase/auth');

const uiConfig = {
  signInFlow: 'popup',
  signInOptions: [
    {
      provider: 'phone',
      defaultCountry: 'IN',
   }
  ],
  callbacks: {
    signInSuccessWithAuthResult: () => false,
  }
};
export default function LoginForm(props) {
  const history = useHistory();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const handleAuthObserverCallback = (flag, user) => {
      if(flag){
        setLoading(true);
        console.log("Firebase User = ",JSON.stringify(user));
        var socketRequest = {UserID:user.uid}
        setUserId(user.uid);
        setMobileNumber(user.phoneNumber);
        SendEvent(SocketEvent.JOIN_ROOM,{"ROOM_ID":user.uid},handleJoinRoomEvent);
        SendEvent(SocketEvent.AUHTENTICATE,socketRequest,handleAuthenticationEvent);
      }
    }
    registerAuthObserver(handleAuthObserverCallback); // Make sure we un-register Firebase observers when the component unmounts.
  }, []);
  
  const handleAuthenticationEvent = useCallback((data) => {
    console.log("handleAuthenticationEvent",data);
    setLoading(false);
    if(data.Payload && data.Payload._id){
      setUserId(data.Payload._id)
      setUser(data.Payload)
      JoinRoom(SocketEvent.JOIN_ROOM,{"ROOM_ID":data.Payload._id},handleJoinRoomEvent)
      console.log("SocketEvent.JOIN_ROOM if");
      history.push(PATH_DASHBOARD.home);
    }else{
      console.log("SocketEvent.JOIN_ROOM else");
      history.push(PATH_AUTH.signup);
    }
  }, []);
  const handleJoinRoomEvent = useCallback((data) => {
    console.log("handleAuthenticationEvent",data);
  }, []);

  return (
    <Container>
      { loading ? <AppLoader/> :null}
      <ToastContainer />
        <StyledFirebaseAuth uiConfig={uiConfig} firebaseAuth={firebase.auth()} />
      </Container>
  );
}
