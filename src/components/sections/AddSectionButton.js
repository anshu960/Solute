import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { ScrollDialog } from '../../dialog';
import { AddSection } from './addSection';
import AppLoader from '../Loader';

 const AddSectionButton = () => {
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const handleClose = () => {
        setOpen(false);
    }
    const contentBody = () => <AddSection setOpen={setOpen} setLoading={setLoading}/>
    return(
        <Fragment>
            { loading ? <AppLoader/> :null}
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Class" open={open} dialogWidth="xl"/>
            <Button
                variant="outlined"
                onClick={()=>{setOpen({open: true})}}
                startIcon={<AddOutlinedIcon />}
            >
                Add Section
            </Button>
        </Fragment>
    )
}

export default AddSectionButton