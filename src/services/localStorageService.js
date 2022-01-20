const isEncryptionEnabled = true;

/**
 * Function to encode data
 * @param {Object} data
 */
 const dataEncode = (data) => {
    if (isEncryptionEnabled) return btoa(data);
    return data;
};

/**
 * Function to decode data
 * @param {Object} data
 */
 const dataDecode = (data) => {
    if (isEncryptionEnabled) return atob(data);
    return data;
};

/**
 * Function to set key in localstorage
 * @param {String} key
 * @param {String} value
 */
 const localStorageSetItem = (key, value) => {
    const keyValue = dataEncode(JSON.stringify(value));
    localStorage.setItem(key, keyValue);
};

/**
 * Function to get from local storage
 * @param {String} key
 */
const localStorageGetItem = (key) => {
    let value = localStorage.getItem(key);
    if (value) value = JSON.parse(dataDecode(value));
    return value;
};

/**
 * Function to get key from local storage
 * @param {String} key
 */
const sessionStorageGetItem = (key) => {
    let value = sessionStorage.getItem(key);
    if (value) value = JSON.parse(dataDecode(value));
    return value;
};

/**
 * Function to set key in localstorage
 * @param {String} key
 * @param {String} value
 */
const sessionStorageSetItem = (key, value) => {
    const keyValue = dataEncode(JSON.stringify(value));
    sessionStorage.setItem(key, keyValue);
};

export {
    localStorageSetItem,
    localStorageGetItem,
    sessionStorageSetItem,
    sessionStorageGetItem,
};
