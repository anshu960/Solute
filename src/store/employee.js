import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';

// Slice

const slice = createSlice({
  name: 'Employee',
  initialState: {
    allEmployee: [],
    selectedEmployee:{}
  },
  reducers: {
    retriveEmployeeSuccess: (state, action) => {
      state.allEmployee = action.payload;
    },
    setSelectedEmployeeSuccess: (state, action) => {
      state.selectedEmployee = action.payload;
    },
  },
});

export default slice.reducer

const { retriveEmployeeSuccess,setSelectedEmployeeSuccess } = slice.actions

export const retriveEmployee = (request) => async dispatch => {
  try {
    SendEvent(SocketEvent.RETRIVE_EMPLOYEE,request,(data)=>{
      dispatch(retriveEmployeeSuccess(data.Payload));
    })
  } catch (e) {
    return console.error(e.message);
  }
}

export const setSelectedEmployee = (employee) => async dispatch => {
  try {
      dispatch(setSelectedEmployeeSuccess(employee));
  } catch (e) {
    return console.error(e.message);
  }
}