import * as React from 'react';
import { useEffect, useState,useCallback } from 'react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
// material
import { Avatar, Button,Card,Stack, TextField, Typography } from '@mui/material';
// socket
import SocketEvent from '../../socket/SocketEvent';
import {SendEvent} from "../../socket/SocketHandler";
import { Container } from 'reactstrap';
import AppLoader from '../Loader';
import { getParameterByName } from '../../common/Utils';
import { Box } from '@mui/system';
import CheckIcon from '@mui/icons-material/Check';
import { getMobileNumber, getUserId, getBusiness, setBusiness, setBusinessId } from '../../services/authService';
import { PATH_DASHBOARD, PATH_PAGE } from '../../routes/path';
import { registerFields } from './FieldConfig';
import InputTextField from '../InputTextField';

const defaultFields = {
  FirstName:"",
  LastName:"",
  MobileNumber:"",
  EmailID:"",
  BusinessName:"",
  DealerName:"",
  GSTNumber:"",
  BusinessMobileNumber:"",
  Address:"",
  otp:""
};

// ----------------------------------------------------------------------

export default function BusinessProfilePersonal() {
  const [fields, setFields] = useState(defaultFields);
  const [loading, setLoading] = useState(false);
  
  const history = useHistory();
  useEffect(()=>{
    const business = getBusiness() || {};
    if(Object.keys(business).length){
      setFields({...business, BusinessName: business.Name});
    }
  },[])

   const handleSubmit = (event) => {
    console.log(event);
    event.preventDefault();
    createBusiness();
  };

const handleCreateBusinessEvent = useCallback((data) => {
  console.log("handleCreateBusinessEvent",data);
  setLoading(false);
  if(data.Payload && data.Payload._id){
      setBusinessId(data.Payload._id);
      setBusiness(data.Payload);
      history.push(PATH_PAGE.home);
  }
}, []);

const createBusiness = () => {
      console.log("User Input = ",fields);
      let UserID = getUserId();
      let req = {
          UserID,
          Name: fields.BusinessName,
          Address: fields.Address,
          BusinessTypeID: fields.BusinessTypeID,
          DealerName: fields.DealerName,
          ProductTypes: [],
          GSTNumber: fields.GSTNumber,
          Status: "active",
          EmailID: fields.EmailID || "",
          MobileNumber: fields.BusinessMobileNumber,
          DialCode:"",
          Gender: 0,
          DeviceID: "",
          FCMToken: "",
          ProfilePicture: []
      }
      setLoading(true);
      console.log("Create business request = ",req);
      SendEvent(SocketEvent.CREATE_BUSINESS,req,handleCreateBusinessEvent)
}
  
  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }
  
  const getField = (field) => 
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
  
  const prepareInputFields = () => registerFields.map((field) =>
  (
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          {field.label}
        </Typography>
        {getField(field)}
      </Stack>
  ))
  return (
    <Container>
      { loading ? <AppLoader/> :null}
      <form action="">
          <Stack spacing={3}>
          {prepareInputFields()}
        <Button 
        onClick={handleSubmit}
          style={{width:'100%',height:40,backgroundColor:"blue",color:"white"}}
        >
          Update
        </Button>
      </Stack>
      </form>
  </Container>);
}
