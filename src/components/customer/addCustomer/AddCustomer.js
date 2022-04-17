import * as React from 'react';
import { useState, useRef } from 'react';
// material
import { Box, Button, DialogActions, DialogContent, DialogContentText, Grid, Stack, Typography } from '@mui/material';
import AddCustomerAction from './AddCustomerAction';
import Select from 'react-select';
import InputTextField from '../../InputTextField';
import { customerFields } from './FieldConfig';
import {useDispatch} from 'react-redux'
import { SendEvent } from '../../../socket/SocketHandler';
import SocketEvent from '../../../socket/SocketEvent';
import { getBusinessId, getUserId } from '../../../services/authService';
import { addNewCustomer, findCustomerByMobile, setSelectedCustomer } from '../../../store/customer';
import { addStyles } from './Style';
import { toast } from 'react-toastify';

// ----------------------------------------------------------------------

export default function AddCustomer({setOpen, setLoading}) {
  const dispatch = useDispatch();
  const classes = addStyles();
  const [fields, setFields] = useState({});
  const descriptionElementRef = useRef(null);
  React.useEffect(()=>{
  },[]);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }

  const onClickSearch=()=>{
    if(fields.MobileNumber && fields.MobileNumber.length >= 10){
      console.log("Entered Number = ",fields.MobileNumber)
      dispatch(findCustomerByMobile(fields.MobileNumber,(customer)=>{
        setFields({...fields,...customer})
      }))
    }else{
      console.log("Please enter valid number")
      toast("Please enter valid number")
    }
  }

  const handleConfirm=()=>{
    setOpen(false);
    if(fields._id && fields._id !== ""){
      dispatch(setSelectedCustomer(fields))
    }else{
      setLoading(true);
      const request = {...fields};
      request.UserID = getUserId();
      request.BusinessID = getBusinessId();
      console.log("CREATE_CUSTOMER REQUEST",request);
      SendEvent(SocketEvent.CREATE_CUSTOMER,request,handleUpdateCustomerEvent);
    }
}
const handleUpdateCustomerEvent = React.useCallback((data) => {
  setLoading(false);
  console.log("handleUpdateCustomerEvent",data)
  if(data && data.status && data.status === "success"){
    dispatch(addNewCustomer(data.Payload))
    setTimeout(()=>{
      dispatch(setSelectedCustomer(data.Payload))
    },500)
    
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
            <Grid container spacing={3} py={2}>
              <Grid item xs={12} md={12} lg={6} xl={4}>
                <Stack spacing={3}>
                <Box component="span" className={classes.selctAutTarDate}>
                  <InputTextField
                    fullWidth
                    placeholder="Search customer by contact"
                    onChange={onChange}
                    name="MobileNumber"
                    autoComplete={false}
                    value={fields["MobileNumber"]}
                    type="text"
                    className={classes.search}
                  />
                </Box>
                </Stack>
              </Grid>
              <Grid item xs={12} md={12} lg={6} xl={4}>
                <Stack spacing={3}>
                  <Box component="span" className={classes.selctAutTarDate}>
                      <Button
                      onClick={onClickSearch}
                      variant="contained" 
                      color="info" 
                      >Search</Button>
                  </Box>
                </Stack>
              </Grid>
              </Grid>
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