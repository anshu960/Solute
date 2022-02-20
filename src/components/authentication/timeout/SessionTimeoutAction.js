import * as React from 'react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
// material
import { Button } from '@mui/material';
import { PATH_PAGE } from '../../../routes/path';
// ----------------------------------------------------------------------

export default function SessionTimeoutAction({handleConfirm}) {
  const history = useHistory();

   const handleLogout = (event) => {
    event.preventDefault();
    localStorage.clear();
    sessionStorage.clear();
    history.push(PATH_PAGE.home);
  };
  
  return (
    <React.Fragment>
      <Button variant="contained" color="info" onClick={handleLogout}>Logout</Button>
      <Button variant="contained" color="success" onClick={handleConfirm} >Confirm</Button>
    </React.Fragment>
    );
}
