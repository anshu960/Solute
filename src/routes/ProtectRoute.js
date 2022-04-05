import * as React from 'react';
import { Redirect } from 'react-router-dom';
import { getUser } from '../services/authService';
import { PATH_DASHBOARD } from './path';


const ProtectedRoute = ({ children }) => {

    const isAuth = getUser() ? true : false;
    const isActiveMember = true;
    return (
        isAuth ? isActiveMember ? <React.Fragment>{children}</React.Fragment> : <Redirect to={{ pathname: PATH_DASHBOARD.membership.root }} /> : <Redirect to={{ pathname: '/' }} />
    );
};

export default ProtectedRoute;
