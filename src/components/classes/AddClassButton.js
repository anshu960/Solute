import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { ScrollDialog } from '../../dialog';
import { AddClass } from './addClass';
import AppLoader from '../Loader';

 const AddClassButton = () => {
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const handleClose = () => {
        setOpen(false);
    }
    const contentBody = () => <AddClass setOpen={setOpen} setLoading={setLoading}/>
    return(
        <Fragment>
            { loading ? <AppLoader/> :null}
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Class" open={open} dialogWidth="xl"/>
            <Button
                variant="outlined"
                onClick={()=>{setOpen({open: true})}}
                startIcon={<AddOutlinedIcon />}
            >
                Add Class
            </Button>
        </Fragment>
    )
}

export default AddClassButton