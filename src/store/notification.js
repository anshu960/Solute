import { createSlice } from '@reduxjs/toolkit'
import { getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
  name: 'Notification',
  initialState: {
    allNotification: [],
    selectedNotification:{}
  },
  reducers: {
    retriveNotificationSuccess: (state, action) => {
      state.allNotification = action.payload;
    },
    setNotificationSuccess: (state, action) => {
        state.selectedNotification = action.payload;
      },
  },
});

export default slice.reducer

const { retriveNotificationSuccess,setNotificationSuccess } = slice.actions

export const retriveNotification = () => async dispatch => {
  try {
    let UserID = getUserId()
    const request = {UserID}
    SendEvent(SocketEvent.RETRIEVE_USER_NOTIFICATION,request,(data)=>{
      console.log("RETRIEVE_USER_NOTIFICATION",data)
      dispatch(retriveNotificationSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const setNotification = (Notification) => async dispatch => {
    try {
        dispatch(setNotificationSuccess(Notification));
    } catch (e) {
      return console.error(e.message);
    }
  }