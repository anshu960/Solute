import * as React from 'react';
import {
    Box, Card,Container,Stack, Typography, Grid,
} from '@mui/material';
import {
  useState,Fragment
} from 'react';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { ToastContainer, toast } from 'react-toastify';
import BusinessProfilePersonal from './../../components/businessprofile/BusinessProfilePersonal';
import BusinessProfileCover from './../../components/businessprofile/BusinessProfileCover';
import BusinessQR from './../../components/businessprofile/BusinessQR';

import { useSelector } from 'react-redux';
import { getBusiness } from '../../services/authService';
  
// ----------------------------------------------------------------------

const Bookings = () => {
    const history = useHistory();
    const [loading, setLoading] = useState(false);
    const selectedBusiness = getBusiness();
  return (
    <div title="Profile">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                   Business Profile
                </Typography>
            </Stack>
        <Grid container spacing={3}>
            <Grid item xs={12} md={12} lg={8} xl={8}>
               <Card
                sx={{
                    mb: 3,
                    height: 280,
                    position: 'relative',
                }}
                >
                    <BusinessProfileCover />
                </Card>
            </Grid>
            <Grid item xs={12} md={12} lg={4} xl={4}>
                <BusinessQR />
            </Grid>
        </Grid>
        <BusinessProfilePersonal />
        </Container>
        </Fragment>
    </div>
    )
}

export default Bookings;
