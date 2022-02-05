import * as React from 'react';
import { useState, useRef } from 'react';
// material
import { Button, DialogActions, DialogContent, DialogContentText, Grid, Stack, Typography } from '@mui/material';
import StockInAction from './AddStopAction';
import Select from 'react-select';
import InputTextField from '../../InputTextField';
import { addButton, stopFields } from './FieldConfig';

const defaultFields = {
  SenderAddress: '',
  ReceiverAddress: '',
};
// ----------------------------------------------------------------------

export default function AddStop({open, setOpen}) {
  const [fields, setFields] = useState(defaultFields);
  const [stopage, setStopage] = useState({  });
  const descriptionElementRef = useRef(null);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }

  const handleConfirm = () => {
    console.log(fields)
  }
  
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    setFields({...fields,[name]:value})
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
    {
      id: `Stop${index}Status`,
      label: 'Status',
      type: 'select',
      placeholder: 'Select Status',
      options: [
        {label: "Picked", value: 1},
        {label: "In Transit", value: 2},
        {label: "Delivered", value: 3},
        {label: "Dropped", value: 4},
      
      ]
    },
   ]
  })
  
  const handleAddStopage = () => {
    let elements = Object.keys(stopage).length+1;
    const stopageFields = createStopageFields(elements);
    setStopage({
      ...stopage, ...stopageFields,
    })
  }
  
  const getStopage = () => {
    const resultFields = [];
    for(let key in stopage){
      resultFields.push(<Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
          <Grid container spacing={3} py={2}>
          {stopage[key].map((field)=>{
            return <Grid item xs={12} md={12} lg={6} xl={4}>
              <Stack spacing={3}>
                {/* <Typography variant='subtitle2'>
                  {field.label}
                </Typography> */}
                {getField(field)}
              </Stack>
            </Grid>
          })}
          </Grid>
        </Stack>)
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

  const getSelectField = (field) =>{
    return(
    <Select
      name={field.id}
      options={field.options}
      placeholder={field.placeholder}
      isClearable
      value={field.options.filter((v) => v.value === fields[field.id])}
      onChange={onSelected}
    />
  )}
  
  const getButtonField = (field) =>(
    <Button variant="contained" color="info" onClick={handleAddStopage} >Add Stopage</Button>
  )

  const getField = (field) => {
    switch (field.type) {
      case 'button': return getButtonField(field);
      case 'select': return getSelectField(field);
      default: return getTextField(field);
    }
  }
  const prepareInputFields = () => stopFields.map((field) =>
  (
    <Grid item xs={12} md={12} lg={6} xl={4}>
      <Stack spacing={3}>
        {/* <Typography variant='subtitle2'>
          {field.label}
        </Typography> */}
        {getField(field)}
      </Stack>
    </Grid>
  ))

  const prepareStopageFields = () => {
    return getStopage();
  }
  
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
            <Stack spacing={3}>
              <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
              <Grid container spacing={3} py={2}>
                <Grid item xs={12} md={12} lg={6} xl={4}>
                  <Typography variant='subtitle2'>
                    {addButton.label}
                  </Typography>
                  {getField(addButton)}
                </Grid>
              </Grid>
              </Stack>
            </Stack>
            <Stack spacing={3}>
              {prepareStopageFields()}
            </Stack>
          </form> 
        </DialogContentText>
      </DialogContent>
      <DialogActions sx={{justifyContent: 'end'}}>
          <StockInAction handleConfirm={handleConfirm} setOpen={setOpen} />
      </DialogActions>  
    </React.Fragment>
  );
}