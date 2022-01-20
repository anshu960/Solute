import * as React from 'react';
import {
  Box, Button, Card, Container, Grid,
  Stack,
  TableContainer,
  Typography
} from '@mui/material';
import {
  useState,Fragment,useCallback, useEffect
} from 'react';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Page from '../../components/Page';
import {SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { getBusinessId, getUserId } from '../../services/authService';
import { useStyles } from './Style';

const defaulState = {
  Name:'',
  MobileNumber: '',
  WhatsApp: '',
  EmailID:'',
  Address: '',
}

const AddCustomer = () => {
  const classes = useStyles();
  const [selectedCustomer, setSelectedCustomer] = useState(defaulState);
  const [customerList, setCustomerList] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(()=>{  
    refreshCustomerList();
  },[])

  const refreshCustomerList=()=>{
    setLoading(true);
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

  const handleCreateCustomerEvent = useCallback((data) => {
    console.log("handleCreateCustomerEvent",data.Payload);
    setLoading(false);
    if(data && data.Payload && data.Payload._id){
        refreshCustomerList();
    }else{
        alert("Unable to create customer, please try after some time");
    }
  }, []);

  const handleInputChange = (e) => {
    setSelectedCustomer({
          ...selectedCustomer,
          [e.target.name]: e.target.value,
      })
  }
  
  const handleAddCustomer = (event) => {
    event.preventDefault();
    console.log("Save clicked");
    // alert("Selected Customer = " + selectedCustomer.Name);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
      if(selectedCustomer.Name){ 
          const request = {
            UserID,
            BusinessID,
            Name:selectedCustomer.Name,
            MobileNumber: selectedCustomer.MobileNumber,
            WhatsApp: selectedCustomer.WhatsApp,
            EmailID: selectedCustomer.EmailID,
            Address: selectedCustomer.Address,
          }
          setLoading(true);
          SendEvent(SocketEvent.CREATE_CUSTOMER,request,handleCreateCustomerEvent);
      }else{
          alert("Please enter valid input");
      }
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
            </Stack>
            <Card>
                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Name</TableCell>
                                    <TableCell>Contact</TableCell>
                                    <TableCell>What's App</TableCell>
                                    <TableCell>Email</TableCell>
                                    <TableCell>Address</TableCell>
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow >
                                    <TableCell><Box component="span"><input name="Name" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Name" value={selectedCustomer.Name}/></Box></TableCell>
                                    <TableCell><Box component="span"><input name="MobileNumber" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Mobile" value={selectedCustomer.MobileNumber} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="WhatsApp" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Whatsapp" value={selectedCustomer.WhatsApp} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="EmailID" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Email" value={selectedCustomer.EmailID} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="Address" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Address" value={selectedCustomer.Address} /></Box></TableCell>
                                    <TableCell>
                                        <Button style={{}}
                                            color="primary" variant="contained"
                                            onClick={handleAddCustomer}>
                                            Save
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
        </Container>
         </Fragment>
          
    </Page>

  )
}

export default AddCustomer;
