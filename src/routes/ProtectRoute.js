import * as React from 'react';
import { Redirect } from 'react-router-dom';
import { getUser } from '../services/authService';


const ProtectedRoute = ({ children }) => {

    const isAuth = getUser() ? true : false;
    return (
        isAuth ? <React.Fragment>{children}</React.Fragment> : <Redirect to={{ pathname: '/' }} />    
    );
};

export default ProtectedRoute;
