import * as React from 'react';
import { useState, useRef } from 'react';
// material
import { Box, Button, DialogActions, DialogContent, DialogContentText, Grid, Stack, TextField, Typography } from '@mui/material';
import AddAction from './AddAction';
import Select from 'react-select';
import InputTextField from '../../InputTextField';
import { addButton, stopFields } from './FieldConfig';
import { createShipmentStatus} from '../../../store/shipment';
import {useDispatch, useSelector} from 'react-redux'
import { getBusinessId, getUserId } from '../../../services/authService';
import { addStyles } from './Style';
import { findUser } from '../../../store/search';

const defaultFields = {
  SenderAddress: '',
  ReceiverAddress: '',
};
// ----------------------------------------------------------------------
export default function Add({setOpen}) {
  const dispatch = useDispatch();
  const classes = addStyles();
  const [fields, setFields] = useState(defaultFields);
  const descriptionElementRef = useRef(null);
  const users = useSelector(state => state.search.users);

  const onChange = (event)=>{
    setFields({...fields,[event.target.name]:event.target.value})
  }

  const handleConfirm = () => {
    console.log("Users",users)
  }
  const search = () => {
    dispatch(findUser(fields.MobileNumber))
  }
   
  return (
    <React.Fragment style={{outerHeight:800}}>
      <DialogContent dividers={true}>
        <DialogContentText id="scroll-dialog-description" ref={descriptionElementRef} tabIndex={-1}>
          <form action="">
            <Stack spacing={3}>
            <Grid container spacing={3} py={2}>
              <Grid item xs={12} md={12} lg={6} xl={4}>
                <Stack spacing={3}>
                  <Box component="span" className={classes.selctAutTarDate}>
                    <TextField
                      className={classes.search}
                      fullWidth
                      placeholder="Search employee by contact"
                      onChange={onChange}
                      name="MobileNumber"
                      autoComplete={false}
                      value={fields["MobileNumber"]}
                      type="text"
                      />
                  </Box>
                </Stack>
              </Grid>
              <Grid item xs={12} md={12} lg={6} xl={4}>
                <Stack spacing={3}>
                  <Box component="span" className={classes.selctAutTarDate}>
                      <Button 
                      onClick={search}
                      variant="contained" 
                      color="info" 
                      >Search</Button>
                  </Box>
                </Stack>
              </Grid>
              </Grid>
            </Stack>
          </form> 
        </DialogContentText>
      </DialogContent>
      <DialogActions sx={{justifyContent: 'end'}}>
          <AddAction handleConfirm={handleConfirm} setOpen={setOpen} />
      </DialogActions>  
    </React.Fragment>
  );
}