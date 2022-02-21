import React, { useEffect, useState } from 'react';
// @mui
import { Button, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../InputTextField';
import {useDispatch, useSelector} from 'react-redux'
import { createHSN } from '../../store/hsn';
import { getBusinessId, getUserId } from '../../services/authService';
import { personalDetails } from './FieldConfig';
const defaultFields = {
  HSN_CODE: '',
  Name: '',
  SGST: '',
  CGST: '',
  IGST: '',
  Type: '',
  UQC: '',
  CESS: '',
  VAT: '',
};

export default function HSNCreate() {
  const dispatch = useDispatch()
  const [fields, setFields] = useState(defaultFields);
  const selectedHSN = useSelector(state => state.hsn.selectedHSN)
  const onChange = (event)=> setFields({...fields,[event.target.name]:event.target.value})

  useEffect(()=>{
    if(Object.keys(selectedHSN).length){
      setFields(selectedHSN)
    }
  },[selectedHSN])

  const handleSave = () => {
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    if(fields.HSN_CODE){
      let request = fields
      request.UserID = UserID
      request.BusinessID = BusinessID
      dispatch(createHSN(request,()=>{
        setFields(defaultFields)
      }));
    }else{
      console.log(fields)
    }
  }
  const prepareInputFields = () => personalDetails.map((field) =>
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
        <Button variant="contained" onClick={handleSave} >Create</Button>  
      </Stack>
    </React.Fragment>
  );
}
