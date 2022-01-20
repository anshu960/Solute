import * as React from 'react';
import { useState,useEffect, useRef } from 'react';
// material
import { DialogActions, DialogContent, DialogContentText, Stack, TextField } from '@mui/material';
import SessionTimeoutAction from './SessionTimeoutAction';
import { getPin } from '../../../services/authService';

const defaultFields = {
  pin: ''
};
// ----------------------------------------------------------------------

export default function SessionTimeout({open, setOpen}) {
  const [fields, setFields] = useState(defaultFields);
  const [errors, setErrors] = useState({});
  const descriptionElementRef = useRef(null);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
    setErrors({
      pin: ''
    })
  } 
  
  useEffect(() => {
    if (open) {
      const { current: descriptionElement } = descriptionElementRef;
      if (descriptionElement !== null) {
        descriptionElement.focus();
      }
    }
  }, [open]);

  const handleConfirm = () => {
    if(getPin() === fields.pin){
      sessionStorage.setItem('pin', fields.pin);
      setOpen(false)
    }else if(!getPin()){
      sessionStorage.setItem('pin', fields.pin);
      sessionStorage.setItem('pin', fields.pin);
      setOpen(false)
    }
    else{
      setErrors({
        pin: 'Invalid Pin'
      })
    }
  }
  
  
  return (
    <React.Fragment>
      <DialogContent dividers={true}>
        <DialogContentText id="scroll-dialog-description" ref={descriptionElementRef} tabIndex={-1}>
          <form action="">
            <Stack spacing={3}>
              <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                <TextField
                  fullWidth
                  placeholder="Pin"
                  onChange={onChange}
                  name="pin"
                  autoComplete="pin"
                  type="password"
                  value={fields.pin}
                  error={errors.pin}
                  helperText={errors.pin}
                />
              </Stack>
            </Stack>
          </form> 
        </DialogContentText>
      </DialogContent>
      <DialogActions sx={{justifyContent: 'space-around'}}>
          <SessionTimeoutAction handleConfirm={handleConfirm}  />
      </DialogActions>  
    </React.Fragment>
  );
}
