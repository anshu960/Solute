import { Avatar, Button, DialogActions, DialogContent, DialogContentText, Grid, ListItemAvatar, ListItemButton, ListItemText } from '@mui/material';
import * as React from 'react';
import {useEffect, useRef} from 'react';
import { PATH_AUTH } from '../../routes/path';
// ----------------------------------------------------------------------
const activeBusiness = {
  backgroundColor: 'aliceblue',
  color: 'info.main',
}
// ----------------------------------------------------------------------

export default function ChooseBusiness({history,businessTypes,businessId, setBusinessId, handleClose, open}) {
  const descriptionElementRef = useRef(null);
  const confirm = () => history.push({ pathname: PATH_AUTH.register, search: `?id=${businessId}` });
  useEffect(() => {
    if (open) {
      const { current: descriptionElement } = descriptionElementRef;
      if (descriptionElement !== null) {
        descriptionElement.focus();
      }
    }
  }, [open]);
  return (
    <React.Fragment>
      <DialogContent dividers={true}>
        <DialogContentText id="scroll-dialog-description" ref={descriptionElementRef} tabIndex={-1}>
        <Grid container>
          {businessTypes.map((business) => {
          return(
            <Grid item xs={12} sm={6} md={4}>
            <ListItemButton onClick={() => setBusinessId(business._id)}
            sx={business._id === businessId ? activeBusiness : {}}
            >
              <ListItemAvatar>
                <Avatar
                  sx={business._id === businessId ? {...activeBusiness, backgroundColor: 'info.lighter'} : {}}
                >
                  <img
                  style={{height: '20px', width: '20px'}}
                  src={business.Image[0]}
                  alt={business.Name}
                  />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary={business.Name} />
            </ListItemButton>
            </Grid>
          )})}
          </Grid>
      </DialogContentText>
    </DialogContent>
    <DialogActions>
        <Button variant="contained" color="warning" onClick={handleClose}>Cancel</Button>
        <Button variant="contained" onClick={confirm} disabled={!businessId}>Proceed</Button>
    </DialogActions> 
    </React.Fragment>
  );
}
