import * as React from 'react';
import { useEffect, useState,useCallback } from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Stack, TextField } from '@mui/material';
import Select from 'react-select';
import SocketEvent from '../../../socket/SocketEvent';
import {JoinRoom,SendEvent} from "./../../../socket/SocketHandler";
import { Container } from 'reactstrap';
import AppLoader from '../../Loader';
import { registerAuthObserver } from '../../../services/firebase/firebaseAuthService';
import { getUserId, setBusiness, setBusinessId, setUser, setUserId } from '../../../services/authService';
import { PATH_AUTH, PATH_DASHBOARD, PATH_PAGE } from '../../../routes/path';
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
const customSeverityStyle = {
  option: (provided) => ({
      ...provided,
  }),
  control: (base) => ({
      ...base,
      width: '100%',
      fontSize: '15px',
      '& div':{}
  }),
  menu: (base) => ({
      ...base,
      borderRadius: 0,
  }),
  menuList: (base) => ({
      ...base,
      fontSize: '18px',
      fontFamily: 'Gilroy-Semibold',
  }),
  container: (base) => ({
      ...base,
      width: '100% !important',
      marginRight: '10px',
      '&:last-child': {
          marginRight: '0px',
      },
      zIndex: 5,
  }),
  indicatorsContainer: (base) => ({
      ...base,
      color:'#fff',
      alignItems: 'baseline',
  }),
};

export default function RegisterForm() {
  const [fields, setFields] = useState(defaultFields);
  const [UserID, setUserID] = useState('');
  const [roleTypes, setRoleTypes] = useState([]);
  const [selectedRole, setSelectedRole] = useState({});
  const [loading, setLoading] = useState(false);
  const history = useHistory();

  const handleAuthObserverCallback = (flag, user) => {
    if(flag){
      setUserID(user.uid);
      setFields({...fields,MobileNumber:user.phoneNumber})
      SendEvent(SocketEvent.JOIN_ROOM,{"ROOM_ID":user.uid},handleJoinRoomEvent);
      retriveRoleTypes()
    }
    else{
      history.push(PATH_AUTH.login);
    }
  }

  useEffect(()=>{
     registerAuthObserver(handleAuthObserverCallback);
  },[])


const retriveRoleTypes = ()=>{
  setLoading(true);
  let UserID = getUserId('UserID');
  SendEvent(SocketEvent.RETRIVE_ROLE_TYPE,{UserID},handleRetriveRoleTypeEvent)
}
const handleRetriveRoleTypeEvent = React.useCallback((data) => {
  setLoading(false);
  console.log("handleRetriveBusinessTypeEvent",data);
  var allRoleTypes = [];
  data.Payload.forEach(element => {
    console.log("XXXXXXX",element);
    var newRole = element;
    newRole.label = newRole.Name;
    newRole.value = newRole._id;
    allRoleTypes.push(newRole);
  });
  setRoleTypes(allRoleTypes);
}, []);

  const handleAuthenticationEvent = useCallback((data) => {
    console.log("handleAuthenticationEvent",data);
    console.log("handleAuthenticationEvent fields",global.inputFields);
    if(data.Payload && data.Payload._id){
      setUserId(data.Payload._id)
      setUser(data.Payload)
      JoinRoom(SocketEvent.JOIN_ROOM,{"ROOM_ID":data.Payload._id},handleJoinRoomEvent)
      history.push(PATH_DASHBOARD.home);
    }else{
      setLoading(false);
      alert("Oops Something went wrong, please try after some time");
    }
  }, []);
  const handleJoinRoomEvent = useCallback((data) => {
    console.log("handleJoinRoomEvent",data);
  }, []);

   const handleSubmit = (event) => {
    console.log(event);
    event.preventDefault();
    createUser();
  };

const createUser = () => {
      global.inputFields = fields;
      var isValidInput = true;
      if(!roleTypes && !roleTypes._id){
        isValidInput = false;
      }
      if(!fields.FirstName && !fields.LastName){
        isValidInput = false;
      }

      console.log("now trying to create with fields",fields);
      console.log("selectedRole",selectedRole);
      if(UserID && isValidInput){
        console.log("user exit now trying to create with UserID",UserID);
        var socketRequest = {
          UserID,
          Name:fields.FirstName + fields.LastName,
          MobileNumber:fields.MobileNumber,
          DialCode:"+91",
          RoleType:selectedRole
        }
        SendEvent(SocketEvent.JOIN_ROOM,{"ROOM_ID":UserID},handleJoinRoomEvent)
        SendEvent(SocketEvent.AUHTENTICATE,socketRequest,handleAuthenticationEvent)
      }else{
        alert("Please verify number again");
      }
}

const handleCreateBusinessEvent = useCallback((data) => {
  console.log("handleCreateBusinessEvent",data);
  if(data.Payload && data.Payload._id){
      setBusinessId(data.Payload._id);
      setBusiness(data.Payload);
      setLoading(false);
      history.push(PATH_DASHBOARD.sale.sale);
  }
}, []);
const createBusiness = (inputFields) => {
      console.log("User Input = ",inputFields);
      let UserID = getUserId();
      let req = {
          UserID,
          Name: inputFields.BusinessName,
          Address: inputFields.Address,
          DealerName: inputFields.DealerName,
          ProductTypes: ["DIESEL","Business","LUBS","OTHER"],
          GSTNumber: inputFields.GSTNumber,
          Status: "active",
          EmailID: inputFields.EmailID || "",
          MobileNumber: inputFields.BusinessMobileNumber,
          DialCode:"91",
          Gender: 0,
          DeviceID: "",
          FCMToken: "",
          ProfilePicture: []
      }
      setLoading(true);
      SendEvent(SocketEvent.CREATE_BUSINESS,req,handleCreateBusinessEvent)
}



  
  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }
  const handleUpdateSelectChange = (option, {name}) => {
    const value = (option && option.value) || '';
    setSelectedRole(option);
    console.log("Selected Role = ",option);
  }
  return (
    <Container>
      { loading ? <AppLoader/> :null}
      <form action="">
          <Stack spacing={3}>
            <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
              <TextField
                fullWidth
                placeholder="First Name"
                onChange={onChange}
                name="FirstName"
                autoComplete="firstName"
                type="text"
                value={fields.FirstName}
              />

              <TextField
                fullWidth
                placeholder="Last Name"
                onChange={onChange}
                name="LastName"
                autoComplete="lastName"
                type="text"
                value={fields.LastName}
              />
            
            </Stack>
            <Select
                        options={roleTypes}
                        placeholder="Account Type"
                        name="Name"
                        styles={customSeverityStyle}
                        onChange={handleUpdateSelectChange}
                    />
            <Button 
            onClick={handleSubmit}
             style={{width:'100%',height:40,backgroundColor:"blue",color:"white"}}
            >
              Create Account
            </Button>
          </Stack>
          </form>
      </Container>
  );
}
