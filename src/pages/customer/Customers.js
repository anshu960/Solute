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
import AppLoader from '../../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { AddCustomerButton, CustomerCard } from '../../components/customer';
import { getBusinessId, getUserId } from '../../services/authService';
import { useStyles } from './Style';
import { useDispatch, useSelector } from 'react-redux';
import { retriveCustomer } from '../../store/customer';

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

const Customers = () => {
  const dispatch = useDispatch()
  const classes = useStyles();
  const customerList = useSelector(state => state.customer.allCustomer)
  const [selectedCustomer, setSelectedCustomer] = useState(defaulState);
  const [loading, setLoading] = useState(false);
  const [customerToEdit, setCustomerToEdit] = useState(defaulUpdateCustomer);

  useEffect(()=>{  
    if(!customerList.length){
      setLoading(true);
      refreshCustomerList();
    }else{
      setLoading(false);
    }
  },[customerList.length])

  const refreshCustomerList=()=>{
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    dispatch(retriveCustomer(request))
  }

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
                    Customer
                </Typography>
                <Box className={classes.actionList}>
                    <AddCustomerButton />
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
                        <CustomerCard customer={customer} index={index} onRefresh={refreshCustomerList} setLoading={setLoading} toast={toast}/>
                    ))
                }
            </Grid>
        )
    }
    return (<Box>
      <Alert variant="outlined" severity="info">
          No Customer found, please add one or try to refresh
      </Alert>
    </Box>)  
}

export default Customers;
