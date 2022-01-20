import React, { useState } from 'react';
// @mui
import { Button, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../../InputTextField';
import { shipmentDetails } from '../FieldConfig';
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

export default function ShipmentInformation() {
  const [fields, setFields] = useState(defaultFields);
  const onChange = (event)=> setFields({...fields,[event.target.name]:event.target.value})
  
  const handleSave = () => {
    console.log(fields)
  }
  const prepareInputFields = () => shipmentDetails.map((field) =>
  (
    <Grid item xs={12} md={12} lg={6} xl={6}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          {field.label}
        </Typography>
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
