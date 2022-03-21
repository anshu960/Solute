import React, { Fragment, useState } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { Button } from '@mui/material';
import { useHistory } from 'react-router-dom';
import AppLoader from '../Loader';
import { AddHotel } from './addHotelRoom';
import { ScrollDialog } from '../../dialog';

export default function AddRoomButton () {
    const history = useHistory();
    const [open, setOpen] = useState(false);
    const [loading, setLoading] = useState(false);
    const handleClose = () => {
        setOpen(false);
    }
    const contentBody = () => <AddHotel setOpen={setOpen} setLoading={setLoading}/>
    return(
        <Fragment>
            { loading ? <AppLoader/> :null}
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Hotel" open={open} dialogWidth="xl"/>
            <Button
                variant="outlined"
                onClick={()=>{setOpen({open: true})}}
                startIcon={<AddOutlinedIcon />}>
                Add Room
            </Button>
        </Fragment>
    )
}