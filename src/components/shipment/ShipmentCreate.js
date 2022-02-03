import React, { useState } from 'react';
// @mui
import { Button, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../InputTextField';
import {useDispatch} from 'react-redux'
import { getBusinessId, getUserId } from '../../services/authService';
import { defaultFields, deliveryBoyDetails, receiverDetails, senderDetails, shipmentDetails } from './FieldConfig';
import ShipentImage from './ShipentImage';
import { createShipment } from '../../store/shipment';

export default function ShipmentCreate() {
  const dispatch = useDispatch()
  const [fields, setFields] = useState(defaultFields);
  const [files, setFiles] = useState([]);
  const onChange = (event)=> setFields({...fields,[event.target.name]:event.target.value})
  const handleSave = () => {
    const UserID = getUserId();
    const BusinessID = getBusinessId();
      let request = fields
      request.UserID = UserID
      request.BusinessID = BusinessID
      dispatch(createShipment(request,(data)=>{
        if(data._id){
          setFields(defaultFields)
        }
      }));
  }
  const shipmentFields = [...shipmentDetails, ...senderDetails, ...receiverDetails, ...deliveryBoyDetails];
  const prepareInputFields = (iFields) => iFields.map((field) =>
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
      {prepareInputFields(shipmentFields)}
      <Grid item xs={12} md={12} lg={12} xl={12}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          Shipment Image
        </Typography>
        <ShipentImage files={files} setFiles={setFiles}/>
      </Stack>
    </Grid>
    </Grid>
      <Stack spacing={3}>
        <Button variant="contained" onClick={handleSave} >Create Shipment</Button>  
      </Stack>
    </React.Fragment>
  );
}
