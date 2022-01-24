import * as React from 'react';
import { useState,useEffect, useRef } from 'react';
// material
import { DialogActions, DialogContent, DialogContentText, Grid, Stack, Typography } from '@mui/material';
import StockAdjustmentAction from './StockAdjustmentAction';
import InputTextField from '../../InputTextField';
import { adjustmentFields } from './FieldConfig';

const defaultFields = {
  ProductName: '',
  Quantity: '',
  AdjustmentQuantity: '',
  StockQuantity: '',
};
// ----------------------------------------------------------------------

export default function StockAdustment({open, setOpen}) {
  const [fields, setFields] = useState(defaultFields);
  const descriptionElementRef = useRef(null);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
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
    console.log(fields)
    
  }

  const getTextField = (field) =>(
    <InputTextField
      fullWidth
      placeholder={field.placeholder}
      onChange={onChange}
      name={field.id}
      autoComplete={field.id}
      value={fields[field.id]}
      type={field.type}
      multiline={!!(field.multiline)}
    />
  )

  const getField = (field) => {
    return getTextField(field);
  }

  const prepareInputFields = () => adjustmentFields.map((field) =>
  (
    <Grid item xs={12} md={12} lg={12} xl={12}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          {field.label}
        </Typography>
        {getField(field)}
      </Stack>
      </Grid>
  ))
  
  
  return (
    <React.Fragment>
      <DialogContent dividers={true}>
        <DialogContentText id="scroll-dialog-description" ref={descriptionElementRef} tabIndex={-1}>
          <form action="">
            <Stack spacing={3}>
              <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
              <Grid container spacing={3} py={2}>
                {prepareInputFields()}
                </Grid>
              </Stack>
            </Stack>
          </form> 
        </DialogContentText>
      </DialogContent>
      <DialogActions sx={{justifyContent: 'space-around'}}>
          <StockAdjustmentAction handleConfirm={handleConfirm} setOpen={setOpen} />
      </DialogActions>  
    </React.Fragment>
  );
}
