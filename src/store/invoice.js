import {
    createSlice
} from '@reduxjs/toolkit'
import {
    toast
} from 'react-toastify';
import {
    getBusiness,
    getBusinessId,
    getUserId
} from '../services/authService';
import SocketEvent from '../socket/SocketEvent';
import {
    SendEvent
} from '../socket/SocketHandler';

// Slice

const slice = createSlice({
    name: 'Invoice',
    initialState: {
        allInvoice: [],
        newReceipt: {
            sale: [],
            invoice: {},
            business: {}
        },
        selectedReceipt: {}
    },
    reducers: {
        retriveReceiptSuccess: (state, action) => {
            state.newReceipt = action.payload;
        },
        retriveAllInvoiceSuccess: (state, action) => {
            state.allInvoice = action.payload;
        },
        setSelectReceiptSuccess: (state, action) => {
            state.selectedReceipt = action.payload;
        },
    },
});

export default slice.reducer

// Actions

const {
    retriveReceiptSuccess,
    setSelectReceiptSuccess,
    retriveAllInvoiceSuccess
} = slice.actions

export const retriveAllInvoice = (startDate, endDate, callback) => async dispatch => {
    try {

        const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {
            UserID,
            BusinessID,
            StartDate: startDate.toISOString().substring(0, 10),
            EndDate: endDate.toISOString().substring(0, 10)
        }
        SendEvent(SocketEvent.RETRIVE_INVOICE, request, (data) => {
            console.log("RETRIVE_INVOICE", data)
            if (data && data.Payload) {
                dispatch(retriveAllInvoiceSuccess(data.Payload));
            } else {
                console.log("Unable to find invoices, please try after some time", data);
            }
            if (callback) {
                callback(data.Payload)
            }
        })

    } catch (e) {
        return console.error(e.message);
    }
}
export const selectReceipt = (Receipt) => async dispatch => {
    try {
        dispatch(setSelectReceiptSuccess(Receipt));
    } catch (e) {
        return console.error(e.message);
    }
}