import * as React from 'react';
import { useState,useEffect, useRef } from 'react';
// material
import { Button, DialogActions, DialogContent, DialogContentText, Grid, Stack, Typography } from '@mui/material';
import StockInAction from './AddStopAction';
import InputTextField from '../../InputTextField';
import { stopFields } from './FieldConfig';

const defaultFields = {
  SenderAddress: '',
  ReceiverAddress: '',
  stop1Address:'',
  stop1DeliveryBoyName:'',
  stop1DeliveryBoyContact:'',
  stop1DeliveryBoyAddress:'',
};
// ----------------------------------------------------------------------

export default function AddStop({open, setOpen}) {
  const [fields, setFields] = useState(defaultFields);
  const [stopage, setStopage] = useState({  });
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
  
  const createStopageFields = (index) => ({
   ['stopage'+index]: [
    {
      id: `Stop${index}Address`,
      label: `Stopage${index} Address`,
      type: 'text',
      placeholder: `Enter Stopage${index} Address`
    },
    {
      id: `Stop${index}DeliveryBoyName`,
      label: 'Delivery Boy Name',
      type: 'text',
      placeholder: 'Enter Delivery Boy Name'
    },
    {
      id: `Stop${index}DeliveryBoyContact`,
      label: 'Delivery Boy Contact',
      type: 'text',
      placeholder: 'Enter Delivery Boy Contact'
    },
    {
      id: `Stop${index}DeliveryBoyAddress`,
      label: 'Delivery Boy Address',
      type: 'text',
      placeholder: 'Enter Delivery Boy Address'
    },
   ]
    
  })
  
  const handleAddStopage = () => {
    let elements = Object.keys(stopage).length+2;
    const stopageFields = createStopageFields(elements);
    setStopage({
      ...stopage, ...stopageFields,
    })
  }
  
  const getStopage = () => {
    let resultFields = [];
    for(let key in stopage){
      resultFields = [...resultFields,...stopage[key]];
    }
    return resultFields;
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
  
  const getButtonField = (field) =>(
    <Button variant="contained" color="info" onClick={handleAddStopage} >Add Stopage</Button>
  )

  const getField = (field) => {
    switch (field.type) {
      case 'button': return getButtonField(field);
      default: return getTextField(field);
    }
  }
  const stopFieldsWithStopage = [...stopFields, ...getStopage()]
  const prepareInputFields = () => stopFieldsWithStopage.map((field) =>
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
  
  console.log(stopage);
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
          <StockInAction handleConfirm={handleConfirm} setOpen={setOpen} />
      </DialogActions>  
    </React.Fragment>
  );
}