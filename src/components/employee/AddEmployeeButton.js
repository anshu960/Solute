import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { useHistory } from 'react-router-dom';
import { ScrollDialog } from '../../dialog';
import { Add } from './addEmployee';

export default function AddEmployeeButton () {
    const history = useHistory();
    const [open, setOpen] = useState(false);
    const handleClose = () => {
        setOpen(false);
      }
    const contentBody = () => <Add setOpen={setOpen}/>
    return(
    <Fragment>
        <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Employee" open={open} dialogWidth="xl"/>
    <Button
        variant="outlined"
        onClick={() => setOpen(true)}
        startIcon={<AddOutlinedIcon />}
    >
        Add Employee
    </Button>
    </Fragment>)
}