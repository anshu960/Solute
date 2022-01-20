import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
  name: 'Menu',
  initialState: {
    allMenu: [],
    selectedMenu:{}
  },
  reducers: {
    retriveMenuSuccess: (state, action) => {
      state.allMenu = action.payload;
    },
    setMenuSuccess: (state, action) => {
        state.selectedMenu = action.payload;
      },
  },
});

export default slice.reducer

const { retriveMenuSuccess,setMenuSuccess } = slice.actions

export const retriveMenu = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_Menu,request,(data)=>{
      console.log("RETRIVE_MENU",data)
      dispatch(retriveMenuSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const setMenu = (menu) => async dispatch => {
    try {
        dispatch(setMenuSuccess(menu));
    } catch (e) {
      return console.error(e.message);
    }
  }