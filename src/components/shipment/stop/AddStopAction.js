import * as React from 'react';
// material
import { Button } from '@mui/material';
// ----------------------------------------------------------------------

export default function AddStopAction({handleConfirm, setOpen}) {
  
  return (
    <React.Fragment>
      <Button variant="contained" color="info" onClick={()=>{setOpen({open:false, shipment:{}})}}>Cancel</Button>
      <Button variant="contained" color="success" onClick={handleConfirm} >Confirm</Button>
    </React.Fragment>
    );
}
