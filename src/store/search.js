import { createSlice } from '@reduxjs/toolkit'
import { getBusiness, getUserId } from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
    name: 'Search',
    initialState: {
      allSearchResults: [],
      users: [],
    },
    reducers: {
      searchSuccess: (state, action) => {
        state.allSearchResults = action.payload;
      },
      searchUserSuccess: (state, action) => {
        state.users = action.payload;
      },
    },
  });
  
  export default slice.reducer
  
  const { searchSuccess,searchUserSuccess } = slice.actions

export const findUser = (MobileNumber) => async dispatch => {
    try {
      let business = getBusiness();
      let BusinessID = business._id
      let UserID = getUserId()
      let request = {MobileNumber,UserID,BusinessID}
      SendEvent(SocketEvent.FIND_USER,request,(data)=>{
        console.log("FIND_USER \n",data)
        dispatch(searchUserSuccess(data.Payload));
      })
    } catch (e) {
      return console.error(e.message);
    }
  }
