import * as React from 'react';
import {
  Alert,
  Box,
  Container, Grid,
  Stack,
  Typography
} from '@mui/material';
import {
  useState,Fragment,useCallback, useEffect
} from 'react';

import Page from '../../components/Page';
import {SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { AddTenantButton, TenantCard } from '../../components/tenant';
import { getBusinessId, getUserId } from '../../services/authService';
import { useStyles } from './Style';

const defaulState = {
  Name:'',
  MobileNumber: '',
  WhatsApp: '',
  EmailID:'',
  Address: '',
}
const defaulUpdateCustomer = {
        "ProfilePicture": [],
        "LastSeen": "",
        "CreatedAt": "",
        "UpdatedAt": "",
        "_id": "",
        "UserID": "",
        "BusinessID": "",
        "Name": "",
        "Address": "",
        "MobileNumber": "",
        "EmailID":"",
        "WhatsApp": "",
        "Gender": 0,
        "DeviceID": "",
        "FCMToken": "",
        "__v": 0      
  }

const Tenants = () => {
  const classes = useStyles();
  const [selectedCustomer, setSelectedCustomer] = useState(defaulState);
  const [customerList, setCustomerList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [customerToEdit, setCustomerToEdit] = useState(defaulUpdateCustomer);

  useEffect(()=>{  
    refreshCustomerList();
  },[])

  const refreshCustomerList=()=>{
    //setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,handleRetriveCustomerEvent)
  }
  const handleRetriveCustomerEvent = useCallback((data) => {
    setLoading(false);
    console.log("handleRetriveCustomerEvent",data)
    if(data && data.Payload && data.Payload.length){
          setCustomerList(data.Payload);
          setSelectedCustomer(defaulState);
    }else{
        console.log("Unable to find customer, please try after some time");
    }
  }, []);

const onClickEdit = (customer,index)=>{
    setCustomerToEdit(customer);
}

  return (
    <Page title="Customer">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Tenant
                </Typography>
                <Box className={classes.actionList}>
                    <AddTenantButton />
                </Box>
            </Stack>
            {renderUsers(customerList,onClickEdit,refreshCustomerList,setLoading,toast)}
        </Container>
         </Fragment>
          
    </Page>

  )
}

const renderUsers = (customerList,onClickEdit,refreshCustomerList,setLoading,toast)=> {
    if(customerList.length){
        return(
            <Grid container spacing={3} py={3}>
                { 
                    customerList.map((customer, index) => (
                        <TenantCard customer={customer} index={index} onRefresh={refreshCustomerList} setLoading={setLoading} toast={toast}/>
                    ))
                }
            </Grid>
        )
    }
    return (<Box>
      <Alert variant="outlined" severity="info">
          No Tenant found, please add one or try to refresh
      </Alert>
    </Box>)  
}

export default Tenants;