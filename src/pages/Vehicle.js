import * as React from 'react';
import {
  Box, Button, Card, Container, Grid,
  Stack,
  TableContainer,
  Typography
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import {
  useState,Fragment,useCallback, useEffect,Text
} from 'react';

import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { useHistory } from 'react-router-dom';
import Page from '../components/Page';
import {SendEvent } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import AppLoader from '../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { CustomerCard } from '../components/customer';
import { getBusinessId, getUserId } from '../services/authService';

const useStyles = makeStyles((theme)=>createStyles({
  rightSection: {
      width: '96%',
  },
  inRightSection: {
      padding: '25px 70px 20px 32px',
  },
  adminButton: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      color: '#fff',
  },
  addNewGroup: {
      fontSize: '18px',
      color: '#fff',
      border: '1px solid #428BCA',
      borderRadius: '2px',
      backgroundColor: '#428BCA',
      height: '48px',
      marginLeft: '15px',
      width: '205px',
      '&:hover': {
          backgroundColor: '#428BCA',
      },
  },
  inAdminButton: {
      display: 'flex',
  },
  addNewGroupBulk: {
    width: '165px',
  },
  bottomButtonExpert: {
      textAlign: 'right',
  },
  table: {
      width: '100%',
      borderSpacing: '0px 0px',
      border: '1px solid #7070704D',
      '& tr': {
          '&:nth-child(1)': {
              '& th': {
                  textAlign:'center',
                  borderBottom: '1px solid #7070704D',
                  fontSize: '15px',
                  color: '#1e1e1f',
                  fontFamily: 'Gilroy-Semibold',
                  padding: '5px 10px',
                  backgroundColor: '#b0b0b1',
                  '& span': {
                      padding: '0px',
                      color: '#000',
                      '& svg': {
                          verticalAlign: 'top',
                      },
                  },
              },
          },
      },
      '& td': {
          textAlign:'center',
          '&:nth-child(2)': {
              color: '#428BCA',
          },
          '&:last-child': {
              color: '#428BCA',
              cursor: 'pointer',
          },
      },
  },
  selectBoxStyle: {
      '& span': {
          display: 'none',
      },
  },
  selectBoxSectionTarget: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'flex-end',
  },
  selctAutTar: {
      width: '14%',
      marginRight: '7px',
      position: 'relative',
      '& div': {
          width: '100%',
          borderRadius: '2px',
      },
      '& input': {
          padding: '15px 14px',
          fontSize: '15px',
      },
  },
  actionList: {
      display: 'flex',
      justifyContent: 'flex-end',
      flexGrow: '1',
  },
  selctAutTarDate: {
      color:'#fff',
      marginRight: '7px',
      position: 'relative',
      '& input': {
          padding: '14px 0px 12px 10px',
          fontSize: '15px',
      },
  },
  calendarIcon: {
      position: 'absolute',
      right: '40px',
      top: '14px',
      color: '#8F8FB3',
      fontSize: '14px',
      zIndex: '99',
  },
  datePicker: {
      height: '48px',
  },
  textField: {
      color: '#428BCA',
      border: '1px solid #428BCA',
      fontSize: '15px',
      fontFamily: 'Gilroy-Semibold',
      padding: '5px 10px',
      '& input' : {
          color: '#428BCA',
      },
  },
  saleRate:{
      //color:'#4289c7',
      border:'none',
      textAlign:'center',
      width: '100px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[300]
  },
  saleRateActive:{
      //color:'#fff',
      border:'none',
      textAlign:'center',
      width: '100px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[200]
  },
}));

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

const Vehicle = () => {
  const classes = useStyles();
  const [selectedVehicle, setSelectedVehicle] = useState(defaulState);
  const [employeeList, setVehicleList] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(()=>{  
    refreshVehicleList();
  },[])

  const refreshVehicleList=()=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,handleRetriveVehicleEvent)
  }
  const handleRetriveVehicleEvent = ((data) => {
    setLoading(false);
    console.log("handleRetriveVehicleEvent",data)
    if(data && data.Payload && data.Payload.length){
          setVehicleList(data.Payload);
          setSelectedVehicle(defaulState);
    }else{
        console.log("Unable to find employee, please try after some time");
    }
  });

  const handleCreateVehicleEvent = useCallback((data) => {
    console.log("handleCreateVehicleEvent",data.Payload);
    setLoading(false);
    if(data && data.Payload && data.Payload._id){
        refreshVehicleList();
    }else{
        alert("Unable to create employee, please try after some time");
    }
  }, []);

  const handleInputChange = (e) => {
    setSelectedVehicle({
          ...selectedVehicle,
          [e.target.name]: e.target.value,
      })
  }
  
  const handleAddVehicle = (event) => {
    event.preventDefault();
    console.log("Save clicked");
    const UserID = getUserId();
    const BusinessID = getBusinessId();;
    const request = {...selectedVehicle};
      if(request.Name){
            request.UserID = UserID;
            request.BusinessID = BusinessID;
          setLoading(true);
          SendEvent(SocketEvent.CREATE_CUSTOMER,request,handleCreateVehicleEvent);
      }else{
          alert("Please enter valid input");
      }
  }

const onClickEdit = (employee,index)=>{
    //setCustomerToEdit(employee);
}

  return (
    <Page title="Vehicle">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Vehicle
                </Typography>
            </Stack>
            <Card>
                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Vehicle Name</TableCell>
                                    <TableCell>Vehicle Number</TableCell>
                                    <TableCell>Chassi Number</TableCell>
                                    <TableCell>Purchase Date</TableCell>
                                    <TableCell>Fuel Capacity</TableCell>
                                    <TableCell>Fuel Type</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow >
                                    <TableCell><Box component="span"><input name="Name" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Name" value={selectedVehicle.Name}/></Box></TableCell>
                                    <TableCell><Box component="span"><input name="MobileNumber" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Mobile" value={selectedVehicle.MobileNumber} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="WhatsApp" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Whatsapp" value={selectedVehicle.WhatsApp} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="EmailID" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Email" value={selectedVehicle.EmailID} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="Address" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Address" value={selectedVehicle.Address} /></Box></TableCell>
                                    <TableCell>
                                        <Button style={{}}
                                            color="primary" variant="contained"
                                            onClick={handleAddVehicle}>
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

export default Vehicle;
