import * as React from 'react';
import PropTypes from 'prop-types';
import { Box, Stack, Card, Button, Collapse, TextField, Typography } from '@mui/material';
import AppLoader from '../Loader';
import {
  useState,useCallback
} from 'react';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';

EmployeeDetials.propTypes = {
  formik: PropTypes.object,
  cards: PropTypes.array,
  isOpen: PropTypes.bool,
  onOpen: PropTypes.func,
  onCancel: PropTypes.func
};
export default function EmployeeDetials({ employee, isOpen, onOpen, onCancel,onRefresh }) {
  const [employeeToEdit, setEmployeeToEdit] = useState(employee);
  const [loading, setLoading] = useState(false);

  const onClickSaveEmployee=()=>{
    setLoading(true);
    let request = {...employeeToEdit};
    console.log("UPDATE_EMPLOYEE REQUEST",request);
    console.log("UPDATE_EMPLOYEE employeeToEdit",employeeToEdit);
    SendEvent(SocketEvent.UPDATE_EMPLOYEE,request,handleUpdateEmployeeEvent);
}
const handleUpdateEmployeeEvent = useCallback((data) => {
  setLoading(false);
  console.log("handleUpdateEmployeeEvent",data)
  if(data && data.status && data.status === "success"){
    onRefresh();
  }else{
      console.log("Unable to find employee, please try after some time");
  }
}, []);
const handleEmployeeUpdateInputChange = (event) => {
  event.preventDefault()
  setEmployeeToEdit({
      ...employeeToEdit,
      [event.target.name]: event.target.value,
  })
}
  return (
    <Card sx={{ p: 3 }}>
      { loading ? <AppLoader/> :null}
      <Typography variant="overline" sx={{ mb: 3, display: 'block', color: 'text.secondary' }}>
        Employee Information
      </Typography>
      <Box sx={{ mt: 3 }}>
        <Button size="small" onClick={onOpen}>
            View/Edit Details
        </Button>
      </Box>

      <Collapse in={isOpen}>
        <Box
          sx={{
            padding: 3,
            marginTop: 3,
            borderRadius: 1,
            bgcolor: 'background.neutral'
          }}
        >
            <form onSubmit={()=>{}}>
              <Stack spacing={3}>
                <Typography variant="subtitle1">Employee Details</Typography>
                  <TextField
                    fullWidth
                    label="Name"
                    name="Name"
                    value={employeeToEdit.Name}
                    onChange={handleEmployeeUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Contact"
                    name="MobileNumber"
                    value={employeeToEdit.MobileNumber}
                    onChange={handleEmployeeUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="What's App"
                    name="WhatsaApp"
                    value={employeeToEdit.WhatsApp}
                    onChange={handleEmployeeUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Email"
                    name="EmailID"
                    value={employeeToEdit.EmailID}
                    onChange={handleEmployeeUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Address"
                    name="Address"
                    value={employeeToEdit.Address}
                    multiline minRows={2}
                    onChange={handleEmployeeUpdateInputChange}
                  />
                <Stack direction="row" justifyContent="flex-end" spacing={1.5}>
                  <Button type="button" color="inherit" variant="outlined" onClick={onCancel}>
                    Cancel
                  </Button>
                  <Button
                    disabled = {false}
                    style={{cursor:'pointer'}}
                    variant="outlined"     
                    onClick={onClickSaveEmployee}   
                >
                    Update
                </Button>
                </Stack>
              </Stack>
            </form>
        </Box>
      </Collapse>
    </Card>
  );
}
