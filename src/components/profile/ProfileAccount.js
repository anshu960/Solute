import React, { useState } from 'react';
// @mui
import { Button, Grid, Stack, TextField, Typography, InputAdornment, IconButton, } from '@mui/material';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { getPin } from '../../services/authService';
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
const defaultFields = {
  pin: getPin() || '',
  showPassword: false,
};

export default function ProfileAccount() {
  const [fields, setFields] = useState(defaultFields);
  const [errors, setErrors] = useState({});
  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
    setErrors({
      pin: ''
    })
  }
  const handleSave = () => {
    console.log(fields.pin)
  }

  return (
    <Grid container spacing={3}>
      <Grid item xs={12} md={12} lg={4} xl={4}>
        <Stack spacing={3}>
        <Typography variant='subtitle2'>
          Pin
        </Typography>  
        <TextField
        fullWidth
        placeholder="Pin"
        onChange={onChange}
        name="pin"
        autoComplete="pin"
        type={fields.showPassword ? 'text' : 'password'}
        value={fields.pin}
        error={errors.pin}
        helperText={errors.pin}
        InputProps={{
          endAdornment: (
            <InputAdornment  position="end">
              <IconButton
                onClick={()=>{
                  setFields({
                    ...fields,
                    showPassword: !fields.showPassword,
                  });
                }}
              >
                {fields.showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          )
        }}
      />
      <Button variant="contained" onClick={handleSave} >Save</Button>
        </Stack>
      </Grid>
    </Grid>
  );
}
