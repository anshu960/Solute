import { createSlice } from '@reduxjs/toolkit'
import { socket } from '../context/socket';
import { getUserId } from '../services/authService';
import SocketEvent, { connect } from '../socket/SocketEvent';
import { SendEvent } from "./../socket/SocketHandler";

const slice = createSlice({
  name: 'Statistics',
  initialState: {
    PlatformStatistics: {},
  },
  reducers: {
    platformStatisticsSuccess: (state, action) => {
        state.PlatformStatistics = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const {platformStatisticsSuccess } = slice.actions

export const syncPlatformStatistics = () => dispatch => {
  try {
    setTimeout(()=>{
      const userId = getUserId();
      console.log("RETRIVE_PLATFORM_STATISTICS Request \n",userId)
      SendEvent(SocketEvent.RETRIVE_PLATFORM_STATISTICS,{UserID:userId},(data)=>{
        console.log("RETRIVE_PLATFORM_STATISTICS \n",data)
        dispatch(platformStatisticsSuccess(data.Payload || ""));
      })
    },2000)

  } catch (e) {
    return console.error(e.message);
  }
}