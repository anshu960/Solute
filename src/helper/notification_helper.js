import { acceptConnectionRequest, rejectConnectionRequest } from "../store/employee";
import NoitficartionActions from "./../config/notification_action"
export const acceptRequest = (dispatch,notification,callback) => {
    switch(notification.Action){
        case NoitficartionActions.ACCEPT_EMPLOYEE_CONNECTION_REQUEST:
            console.log("ACCEPT_EMPLOYEE_CONNECTION_REQUEST")
            dispatch(acceptConnectionRequest(notification,callback))
            break;
        default:
            console.log("No Notification actions found at the moment",notification)
            break;
    }
}

export const rejectRequest = (dispatch,notification,callback) => {
    switch(notification.Action){
        case NoitficartionActions.ACCEPT_EMPLOYEE_CONNECTION_REQUEST:
            console.log("ACCEPT_EMPLOYEE_CONNECTION_REQUEST")
            dispatch(rejectConnectionRequest(notification,callback))
            break;
        default:
            console.log("No Notification actions found at the moment",notification.ActionType)
            break;
    }
}