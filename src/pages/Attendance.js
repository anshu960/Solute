import * as React from 'react';
import {
  Box, Checkbox, Container,
  Stack,
  TableContainer,
  Typography,
  TextField,
  Card,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import {
  useState, useEffect,Fragment
} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../components/Page';
import { SendEvent } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import AppLoader from '../components/Loader';
import DatePicker from 'react-datepicker';
import { ToastContainer, toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';

const useStyles = makeStyles((theme)=>createStyles({
  rightSection: {
      width: '96%',
  },
  inRightSection: {
      padding: '25px 70px 20px 32px',
      // [breakpoints.between('1024', '1400')]: {
      //     padding: '18px',
      // },
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
      marginLeft: '70px',
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
        [theme.breakpoints.between('1024', '1400')]: {
            width: '18%',
        },
        '& input': {
            padding: '10px 0px 10px 10px',
            border: '1px solid #cccccc',
            borderRadius: '4px',
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
      width: '150px',
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
      width: '150px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[200]
  },
}));

const defaulState = {
  Name:'',
  Contact: ''
}

const Attendance = () => {
  const classes = useStyles();
  const [loading, setLoading] = useState(false);
  const [isAttendanceRefreshed, setIsAttendanceRefreshed] = useState(false);
  const [employeeList, setEmployeeList] = useState([])
  const [attendanceDate, setAttendanceDate] = useState(new Date());

  useEffect(()=>{
    refreshEmployeeList(); 
  },[attendanceDate])

  const refreshEmployeeList=()=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    SendEvent(SocketEvent.RETRIVE_EMPLOYEE,request,handleRetriveEmployeeEvent)
  }
  const handleRetriveEmployeeEvent = ((data) => {
    setLoading(false);
    console.log("handleRetriveEmployeeEvent",data)
    if(data && data.Payload && data.Payload.length){
        setEmployeeList([...data.Payload]);
        global.employeeList = data.Payload
        refreshEmployeeAttendance()
    }else{
        console.log("Unable to find employee, please try after some time");
    }
  });

  const refreshEmployeeAttendance=()=>{
      setLoading(true);
      console.log("refreshEmployeeAttendance attendanceDate",attendanceDate);
    if(global.employeeList && global.employeeList.length > 0){
        let employeeIds = [];
        global.employeeList.forEach((employee)=>{
            employeeIds.push(employee._id);
        })
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {UserID,BusinessID,EmployeeIds:employeeIds,AttendanceDate:attendanceDate.toISOString().substring(0, 10)}
        console.log("request refreshEmployeeAttendance",request)
        SendEvent(SocketEvent.RETRIVE_EMPLOYEE_ATTENDANCE,request,handleRetriveEmployeeAttendanceEvent)
    }else{
        console.log("refreshEmployeeAttendance Empty",employeeList);
        setLoading(false); 
    }
  }
  const handleRetriveEmployeeAttendanceEvent = ((data) => {
    setLoading(false);
    console.log("handleRetriveEmployee Attendance Event",data)
    if(data && data.Payload && data.Payload.length){
        let allEmployees = global.employeeList;
        let index = 0
        global.employeeList.forEach((emp)=> {
            let newEmployee = emp;
            data.Payload.forEach((attendance)=>{
                if(newEmployee._id === attendance.EmployeeID){
                newEmployee.IsPresent = attendance.IsPresent;
                allEmployees[index] = newEmployee;
              }           
            })
            index = index+1
        })
        if(allEmployees.length){
            setEmployeeList([...allEmployees]);
        }
    }else{
        console.log("Unable to find employee, please try after some time");
    }
  });

  const handleChange = (e, _id) => {
      employeeList.some((emp)=> {if(emp._id === _id){
        emp.Comment = e.target.value;
      }})
      setEmployeeList([...employeeList]);
  } 
  const handleAttendance = (e, _id) => {
      let employee = {};
      employeeList.some((emp)=> {if(emp._id === _id){
        emp.IsPresent = e.target.checked;
        employee = emp;
      }})
      setEmployeeList([...employeeList]);
      updateEmployeeAttendance(employee);
  } 

  const updateEmployeeAttendance=(employee)=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {Employee:employee,UserID,BusinessID,EmployeeID:employee._id,IsPresent:employee.IsPresent,AttendanceDate:attendanceDate.toISOString().substring(0, 10)}
    console.log("request",request)
    SendEvent(SocketEvent.ADD_EMPLOYEE_ATTENDACE,request,handleUpdateEmployeeAttendance)
  }
  const handleUpdateEmployeeAttendance = ((data) => {
    setLoading(false);
    console.log("handleUpdateEmployeeAttendance",data)
    if(data && data.Payload && data.Payload.length){
        toast("Attendance Updated for ",data.Employee.Name);
    }else{
        console.log("Unable to find employee, please try after some time");
    }
  });



  const renderList = () => 
    employeeList.map((employee) => (
        <TableRow >
            <TableCell><Typography component="span">{employee.Name}</Typography></TableCell>
            <TableCell>
                <Checkbox
                    Name="IsPresent"
                    color="primary"
                    checked={employee.IsPresent || false}
                    onChange = {(event)=>handleAttendance(event, employee._id)}
                />
            </TableCell>
            <TableCell>
                <TextField
                    multiline={true}
                    minRows={1}
                    onChange={(event) => handleChange(event, employee._id)}
                    value={employee.Comment}
                    Name="comment"
                    placeholder="Reason"
                />
            </TableCell>
        </TableRow>
     ) )
  console.log("Render EmployeeList ",employeeList);
  return (
    <Page title="Employee">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Attendance
                </Typography>
                <Box className={classes.actionList}>
                    <Box component="span" className={classes.selctAutTarDate}>
                        <DatePicker
                            placeholderText="Select Date"
                            selected={attendanceDate}
                            onChange={(date) => setAttendanceDate(date)}
                        />
                    </Box>
                </Box>
            </Stack>

            <Card>
                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Employee Name</TableCell>
                                    <TableCell>Present</TableCell>
                                    <TableCell>Comment</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {renderList()}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
        </Container>
        </Fragment>
    </Page>)
}

export default Attendance;
