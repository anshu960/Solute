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
import { EmployeeCard } from '../components/employee';
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
const defaulUpdateEmployee = {
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

const Teacher = () => {
  const classes = useStyles();
  const [selectedEmployee, setSelectedEmployee] = useState(defaulState);
  const [employeeList, setEmployeeList] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(()=>{  
    refreshEmployeeList();
  },[])

  const refreshEmployeeList=()=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    SendEvent(SocketEvent.RETRIVE_EMPLOYEE,request,handleRetriveEmployeeEvent)
  }
  const handleRetriveEmployeeEvent = useCallback((data) => {
    setLoading(false);
    console.log("handleRetriveEmployeeEvent",data)
    if(data && data.Payload && data.Payload.length){
          setEmployeeList(data.Payload);
          setSelectedEmployee(defaulState);
    }else{
        console.log("Unable to find employee, please try after some time");
    }
  }, []);

  const handleCreateEmployeeEvent = useCallback((data) => {
    console.log("handleCreateEmployeeEvent",data.Payload);
    setLoading(false);
    if(data && data.Payload && data.Payload._id){
        refreshEmployeeList();
    }else{
        alert("Unable to create employee, please try after some time");
    }
  }, []);

  const handleInputChange = (e) => {
    setSelectedEmployee({
          ...selectedEmployee,
          [e.target.name]: e.target.value,
      })
  }
  
  const handleAddEmployee = (event) => {
    event.preventDefault();
    console.log("Save clicked");
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {...selectedEmployee};
      if(request.Name){
            request.UserID = UserID;
            request.BusinessID = BusinessID;
          setLoading(true);
          SendEvent(SocketEvent.CREATE_EMPLOYEE,request,handleCreateEmployeeEvent);
      }else{
          alert("Please enter valid input");
      }
  }

const onClickEdit = (employee,index)=>{
    //setEmployeeToEdit(employee);
}

  return (
    <Page title="Teacher">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                Teacher
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
                                    <TableCell><Box component="span"><input name="Name" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Name" value={selectedEmployee.Name}/></Box></TableCell>
                                    <TableCell><Box component="span"><input name="MobileNumber" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Mobile" value={selectedEmployee.MobileNumber} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="WhatsApp" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Whatsapp" value={selectedEmployee.WhatsApp} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="EmailID" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Email" value={selectedEmployee.EmailID} /></Box></TableCell>
                                    <TableCell><Box component="span"><input name="Address" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Address" value={selectedEmployee.Address} /></Box></TableCell>
                                    <TableCell>
                                        <Button style={{}}
                                            color="primary" variant="contained"
                                            onClick={handleAddEmployee}>
                                            Save
                                        </Button>
                                    </TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
            {renderUsers(employeeList,onClickEdit,refreshEmployeeList,setLoading,toast)}
        </Container>
         </Fragment>
    </Page>
  )
}

const renderUsers = (employeeList,onClickEdit,refreshEmployeeList,setLoading,toast)=> {
    if(employeeList.length){
        return(
            <Grid container spacing={3} py={3}>
                { 
                    employeeList.map((employee, index) => (
                        <EmployeeCard employee={employee} index={index} onRefresh={refreshEmployeeList} setLoading={setLoading} toast={toast}/>
                    ))
                }
            </Grid>
        )
    }
    return (<Fragment>{"No Teacher found, please add one or try to refresh"}</Fragment>)   
}

export default Teacher;
