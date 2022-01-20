import * as React from 'react';
import { Alert, Box, Container, Grid,
  Stack,
  Typography
} from '@mui/material';
import {
  useState,Fragment, useEffect
} from 'react';

import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { AddEmployeeButton, EmployeeCard } from '../../components/employee';
import { getBusinessId, getUserId } from '../../services/authService';
import { useDispatch, useSelector } from 'react-redux';
import { retriveEmployee } from '../../store/employee';
import { useStyles } from './Style';

const Employees = () => {
  const dispatch = useDispatch();
  const classes = useStyles();
  const UserID = getUserId();
    const BusinessID = getBusinessId();
  const employeeList = useSelector(state => state.employee.allEmployee)
  const [loading, setLoading] = useState(false);

  useEffect(()=>{  
    
  },[])

  const refreshEmployeeList = ()=>{
    dispatch(retriveEmployee({UserID,BusinessID}));
  }

const onClickEdit = (employee,index)=>{
    
}

  return (
    <Page title="Employees">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Employees
                </Typography>
                <Box className={classes.actionList}>
                    <AddEmployeeButton />
                </Box>
            </Stack>
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
    return (<Box>
        <Alert variant="outlined" severity="info">
            No Employee found, please add one or try to refresh
        </Alert>
    </Box>)   
}

export default Employees;
