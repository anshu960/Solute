import * as React from 'react';
import { useState, useRef } from 'react';
// material
import { DialogActions, DialogContent, DialogContentText, Grid, Stack, Typography } from '@mui/material';
import AddCustomerAction from './AddCourseAction';
import Select from 'react-select';
import InputTextField from '../../InputTextField';
import { customerFields } from './FieldConfig';
import {useDispatch} from 'react-redux'
import { SendEvent } from '../../../socket/SocketHandler';
import SocketEvent from '../../../socket/SocketEvent';

// ----------------------------------------------------------------------

export default function AddCourse({setOpen, setLoading}) {
  const dispatch = useDispatch();
  const [fields, setFields] = useState({});
  const descriptionElementRef = useRef(null);
  React.useEffect(()=>{
  },[]);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }

  const handleConfirm=()=>{
    setLoading(true);
    setOpen(false);
    const request = {...fields};
    console.log("UPDATE_CUSTOMER REQUEST",request);
    SendEvent(SocketEvent.UPDATE_CUSTOMER,request,handleUpdateCustomerEvent);
}
const handleUpdateCustomerEvent = React.useCallback((data) => {
  setLoading(false);
  console.log("handleUpdateCustomerEvent",data)
  if(data && data.status && data.status === "success"){
    //onRefresh();
  }else{
      console.log("Unable to find customer, please try after some time");
  }
}, []);
  
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    setFields({...fields,[name]:value})
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
      disabled={field.disabled}
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
  const prepareInputFields = () => customerFields.map((field) =>
  (
    <Grid item xs={12} md={12} lg={6} xl={4}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          {field.label}
        </Typography>
        {getField(field)}
      </Stack>
    </Grid>
  ))

  return (
    <React.Fragment style={{outerHeight:800}}>
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
      <DialogActions sx={{justifyContent: 'end'}}>
          <AddCustomerAction handleConfirm={handleConfirm} setOpen={setOpen} />
      </DialogActions>  
    </React.Fragment>
  );
}