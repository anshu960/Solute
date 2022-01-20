import * as React from 'react';
import {
  Box, Button, Card, Container,
  Stack,
  TableContainer,
  Typography
} from '@mui/material';
import {
  useState,Fragment,useCallback, useEffect} from 'react';

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
import { useDispatch, useSelector } from 'react-redux';
import { useStyles } from './Style';

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

const AddEmployee = () => {
  const dispatch = useDispatch()
  const classes = useStyles();
  const [selectedEmployee, setSelectedEmployee] = useState(defaulState);
  const employeeList = useSelector(state => state.employee.allEmployee)
  const [loading, setLoading] = useState(false);

  useEffect(()=>{  
    
  },[])

  const handleCreateEmployeeEvent = useCallback((data) => {
    console.log("handleCreateEmployeeEvent",data.Payload);
    setLoading(false);
    if(data && data.Payload && data.Payload._id){
        //refreshEmployeeList()
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

  return (
    <Page title="Employee">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Add Employee
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
        </Container>
         </Fragment>
    </Page>
  )
}

export default AddEmployee;
