import { getUniqueId } from "../common/Utils";
import { localStorageGetItem, localStorageSetItem } from "./localStorageService";

/**
 * @description check user is authenticate or not based in UserID as device id.
 * @return {boolean} [isAuthenticated]
*/
const authCentralState = () => !!(localStorageGetItem('UserID'));

/**
 * @description set device id
*/
const setUserId = (id) => {
    localStorageSetItem("UserID",id);
}

/**
 * @description check device id
 * @return {boolean} [UserID as device id]
*/
const getUserId = () => {
    if(!authCentralState()){
        setUserId(getUniqueId())
    }
    return localStorageGetItem('UserID');
}

/**
 * @description set user
*/
const setUser = (user) => {
    localStorageSetItem('User',user);
}

/**
 * @description get user
 * @return {object} [user]
*/
const getUser = () => localStorageGetItem('User');

/**
 * @description set pin
*/
const setPin = (pin) => {
    localStorageSetItem('pin',pin);
}

/**
 * @description get pin
 * @return {Integer} [pin]
*/
const getPin = () => localStorageGetItem('pin');


/**
 * @description set mobile number
*/
const setMobileNumber = (contact) => {
    localStorageSetItem('MobileNumber',contact);
}

/**
 * @description get mobile number
 * @return {integer} [mobile number]
*/
const getMobileNumber = () => localStorageGetItem('MobileNumber');


/**
 * @description set business id
*/
const setBusinessId = (id) => {
    localStorageSetItem('BusinessID',id);
}

/**
 * @description get business id
 * @return {integer} [mobile number]
*/
const getBusinessId = () => localStorageGetItem('BusinessID');


/**
 * @description set business
*/
const setBusiness = (business) => {
    localStorageSetItem('Business',business);
}

/**
 * @description get business
 * @return {object} [business]
*/
const getBusiness = () => localStorageGetItem('Business');

export {
    setUserId,
    setUser,
    setBusinessId,
    setBusiness,
    setMobileNumber,
    setPin,
    getUserId,
    getUser,
    getBusiness,
    getBusinessId,
    getMobileNumber,
    getPin,
}