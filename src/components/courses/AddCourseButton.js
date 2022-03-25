import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { ScrollDialog } from '../../dialog';
import { AddCourse } from './addCourse';
import AppLoader from '../Loader';

 const AddCourseButton = () => {
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const handleClose = () => {
        setOpen(false);
    }
    const contentBody = () => <AddCourse setOpen={setOpen} setLoading={setLoading}/>
    return(
        <Fragment>
            { loading ? <AppLoader/> :null}
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Course" open={open} dialogWidth="xl"/>
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

export default AddCourseButton