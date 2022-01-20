import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

const slice = createSlice({
  name: 'BusinessTypes',
  initialState: {
    businessTypes: [],
  },
  reducers: {
    retriveBusinessTypesSuccess: (state, action) => {
      state.businessTypes = action.payload;
    },
  },
});

export default slice.reducer

// Actions

const { retriveBusinessTypesSuccess } = slice.actions

export const retriveBusinessTypes = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_BUSINESS_TYPE,request,(data)=>{
      console.log(data)
      dispatch(retriveBusinessTypesSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}