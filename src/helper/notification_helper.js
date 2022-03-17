import NoitficartionActions from "./../config/notification_action"
export const acceptRequest = (notification) => {
    switch(notification.Action){
        case NoitficartionActions.ACCEPT_EMPLOYEE_CONNECTION_REQUEST:
            console.log("ACCEPT_EMPLOYEE_CONNECTION_REQUEST")
            break;
        default:
            console.log("No Notification actions found at the moment",notification.ActionType)
            break;
    }
}

export const rejectRequest = (notification) => {

}