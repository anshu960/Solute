import { createSlice } from '@reduxjs/toolkit'
import SocketEvent from '../socket/SocketEvent';
import { SendEvent } from '../socket/SocketHandler';
import firebase from '@firebase/app';
import { updateProduct, updateProductImage } from './product';
import { getBusiness, getUserId } from '../services/authService';
import { updateBusinessImage } from './business';

const slice = createSlice({
  name: 'FileUpload',
  initialState: {
    localFile: {},
    remoteFile:{},
    uploadProgress:0,
    downloadProgress:0,
    isUploaded:false,
    isDownloaded:false
  },
  reducers: {
    setLocalFileSuccess: (state, action) => {
      state.localFile = action.payload;
    },
    setRemoteFileSuccess: (state, action) => {
      state.localFile = action.payload;
    },
    setUploadFileProgress: (state, action) => {
      state.uploadProgress = action.payload;
    },
    setUploadFileSuccess: (state, action) => {
        state.isUploaded = action.payload;
      },
  },
});

export default slice.reducer

const { setLocalFileSuccess,setRemoteFileSuccess,setUploadFileProgress,setUploadFileSuccess } = slice.actions

export const uploadFile = (file) => async dispatch => {
  try {
    var storage = firebase.storage();
    var storageRef = storage.ref();
    var uploadTask = storageRef.child('folder/' + file.name).put(file);
  } catch (e) {
    return console.error(e.message);
  }
}

export const uploadProductImage = (file,prd) => async dispatch => {
  try {
    let business = getBusiness();
    let product = prd
    product.UserID = getUserId();
    console.log("trying to upload images",file)
    var path = "product/"+ business._id + "/" + product._id + "/";
    var storage = firebase.storage();
    var storageRef = storage.ref();
    var uploadTask = storageRef.child(path + file.name).put(file);
    uploadTask.on('state_changed', 
    (snapShot) => {
      //takes a snap shot of the process as it is happening
      console.log(snapShot)
    }, (err) => {
      //catches the errors
      console.log(err)
    }, () => {
      // gets the functions from storage refences the image storage in firebase by the children
      // gets the download url then sets the image from firebase as the value for the imgUrl key:
      storage.ref(path).child(file.name).getDownloadURL()
       .then(fireBaseUrl => {
         console.log("fireBaseUrl",fireBaseUrl);
         product.Image = [...product.Image,fireBaseUrl]
         dispatch(updateProductImage(product))
       })
    })
  } catch (e) {
    return console.error(e.message);
  }
}
export const uploadBusinessImage = (file) => async dispatch => {
  try {
    let business = getBusiness();
    business.UserID = getUserId();
    console.log("trying to upload images for business",file)
    var path = "business/" + business._id + "/";
    var storage = firebase.storage();
    var storageRef = storage.ref();
    var uploadTask = storageRef.child(path + file.name).put(file);
    uploadTask.on('state_changed', 
    (snapShot) => {
      //takes a snap shot of the process as it is happening
      console.log(snapShot)
    }, (err) => {
      //catches the errors
      console.log(err)
    }, () => {
      // gets the functions from storage refences the image storage in firebase by the children
      // gets the download url then sets the image from firebase as the value for the imgUrl key:
      storage.ref(path).child(file.name).getDownloadURL()
       .then(fireBaseUrl => {
         console.log("fireBaseUrl",fireBaseUrl);
         business.ProfilePicture = [...business.Image || [],fireBaseUrl]
         dispatch(updateBusinessImage(business))
       })
    })
  } catch (e) {
    return console.error(e.message);
  }
}


export const uploadProfileImage = (file,user) => async dispatch => {
    try {
        dispatch(uploadFile(file,"destination"));
    } catch (e) {
      return console.error(e.message);
    }
  }