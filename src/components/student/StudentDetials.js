import * as React from 'react';
import PropTypes from 'prop-types';
import { Box, Stack, Card, Button, Collapse, TextField, Typography } from '@mui/material';
import AppLoader from '../Loader';
import {
  useState,useCallback
} from 'react';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';

StudentDetials.propTypes = {
  formik: PropTypes.object,
  cards: PropTypes.array,
  isOpen: PropTypes.bool,
  onOpen: PropTypes.func,
  onCancel: PropTypes.func
};
export default function StudentDetials({ customer, isOpen, onOpen, onCancel,onRefresh }) {
  const [customerToEdit, setCustomerToEdit] = useState(customer);
  const [loading, setLoading] = useState(false);

  const onClickSaveCustomer=()=>{
    setLoading(true);
    let request = {...customerToEdit};
    console.log("UPDATE_CUSTOMER REQUEST",request);
    console.log("UPDATE_CUSTOMER customerToEdit",customerToEdit);
    SendEvent(SocketEvent.UPDATE_CUSTOMER,request,handleUpdateCustomerEvent);
}
const handleUpdateCustomerEvent = useCallback((data) => {
  setLoading(false);
  console.log("handleUpdateCustomerEvent",data)
  if(data && data.status && data.status === "success"){
    onRefresh();
  }else{
      console.log("Unable to find customer, please try after some time");
  }
}, []);
const handleCustomerUpdateInputChange = (event) => {
  event.preventDefault()
  setCustomerToEdit({
      ...customerToEdit,
      [event.target.name]: event.target.value,
  })
}
  return (
    <Card sx={{ p: 3 }}>
      { loading ? <AppLoader/> :null}
      <Typography variant="overline" sx={{ mb: 3, display: 'block', color: 'text.secondary' }}>
        Customer Information
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
                <Typography variant="subtitle1">Customer Details</Typography>
                  <TextField
                    fullWidth
                    label="Name"
                    name="Name"
                    value={customerToEdit.Name}
                    onChange={handleCustomerUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Contact"
                    name="MobileNumber"
                    value={customerToEdit.MobileNumber}
                    onChange={handleCustomerUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="What's App"
                    name="WhatsaApp"
                    value={customerToEdit.WhatsApp}
                    onChange={handleCustomerUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Email"
                    name="EmailID"
                    value={customerToEdit.EmailID}
                    onChange={handleCustomerUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Address"
                    name="Address"
                    value={customerToEdit.Address}
                    multiline minRows={2}
                    onChange={handleCustomerUpdateInputChange}
                  />
                <Stack direction="row" justifyContent="flex-end" spacing={1.5}>
                  <Button type="button" color="inherit" variant="outlined" onClick={onCancel}>
                    Cancel
                  </Button>
                  <Button
                    disabled = {false}
                    style={{cursor:'pointer'}}
                    variant="outlined"     
                    onClick={onClickSaveCustomer}   
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
