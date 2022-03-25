import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { ScrollDialog } from '../../dialog';
import { AddSubject } from './addSubject';
import AppLoader from '../Loader';

 const AddSubjectButton = () => {
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const handleClose = () => {
        setOpen(false);
    }
    const contentBody = () => <AddSubject setOpen={setOpen} setLoading={setLoading}/>
    return(
        <Fragment>
            { loading ? <AppLoader/> :null}
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Subject" open={open} dialogWidth="xl"/>
            <Button
                variant="outlined"
                onClick={()=>{setOpen({open: true})}}
                startIcon={<AddOutlinedIcon />}
            >
                Add Subject
            </Button>
        </Fragment>
    )
}

export default AddSubjectButton