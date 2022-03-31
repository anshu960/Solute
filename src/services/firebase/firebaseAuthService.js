import firebase from "firebase/app";
import "firebase/auth";
import firebase_config from '../../config/firebase_config';
import { PATH_AUTH } from '../../routes/path';

const app = firebase.initializeApp(firebase_config.firebaseConfig);
const auth = app.auth();


const registerAuthObserver = handleAuthObserverCallback => {
  if (!firebase.apps.length) {
    firebase.initializeApp(firebase_config.firebaseConfig);
  }else {
    firebase.app(); // if already initialized, use that one
  }
  firebase.auth().onAuthStateChanged(user => {
    if(user && user.uid){
      handleAuthObserverCallback(true,user)
    }
    else{
      handleAuthObserverCallback(false)
    }
  })
}

const logout = (history) => {
  if (!firebase.apps.length) {
    firebase.initializeApp(firebase_config.firebaseConfig);
  }else {
    firebase.app(); // if already initialized, use that one
  }
  firebase.auth().signOut().then(function() {
      localStorage.clear();
      sessionStorage.clear();
      history.push(PATH_AUTH.login)
  }).catch(function(error) {
      alert(error);
  });
}

export {
  auth,
  logout,
  registerAuthObserver,
}
