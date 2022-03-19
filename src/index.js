import * as React from 'react';
import ReactDOM from "react-dom";
import { HashRouter } from 'react-router-dom';
import 'react-datepicker/dist/react-datepicker.css';
import 'react-toastify/dist/ReactToastify.css';
// slick-carousel
import 'slick-carousel/slick/slick.css'
import 'slick-carousel/slick/slick-theme.css'
import { Provider } from 'react-redux'
import store from './store'
import App from './App';
import './i18n';
import "regenerator-runtime/runtime";

ReactDOM.render(
  <Provider store={store}>
    <HashRouter>
      <App />
    </HashRouter>
  </Provider>
  , 
  document.getElementById("root")
);


// ReactDOM.render(<App/>, document.getElementById('app'));