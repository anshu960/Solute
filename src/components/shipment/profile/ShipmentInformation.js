import React, { useEffect, useState } from 'react';
// @mui
import { Button, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../../InputTextField';
import { shipmentDetails } from '../FieldConfig';
import Select from 'react-select';
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
const defaultFields = {
  Name: '',
  Weight: '',
  From: '',
  To: '',
  ShipmentDate: '',
  ShipmentDeliveryDate: '',
  Description: '',
};

export default function ShipmentInformation({shipment}) {
  const [fields, setFields] = useState(defaultFields);
  const onChange = (event)=> setFields({...fields,[event.target.name]:event.target.value})
  useEffect(()=>{
    const ship = shipment.length ? shipment[0] : null;
    if(ship){
      setFields(ship);
    }
  },[shipment])
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    setFields({...fields,[name]:value})
  }

  const handleSave = () => {
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

  const getField = (field) => {
    switch (field.type) {
      case 'select': return getSelectField(field);
      default: return getTextField(field);
    }
  }

  const prepareInputFields = () => shipmentDetails.map((field) =>
  (
    <Grid item xs={12} md={12} lg={6} xl={6}>
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
    <Grid container spacing={3} py={2}>
      {prepareInputFields()}
    </Grid>
      <Stack spacing={3}>
        <Button variant="contained" onClick={handleSave} >Save</Button>  
      </Stack>
    </React.Fragment>
  );
}
