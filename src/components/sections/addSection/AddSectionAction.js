import * as React from 'react';
// material
import { Button } from '@mui/material';
// ----------------------------------------------------------------------

export default function AddSectionAction({handleConfirm, setOpen}) {
  
  return (
    <React.Fragment>
      <Button variant="contained" color="info" onClick={()=>{setOpen(false)}}>Cancel</Button>
      <Button variant="contained" color="success" onClick={handleConfirm} >Confirm</Button>
    </React.Fragment>
    );
}
