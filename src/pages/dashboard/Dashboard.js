import * as React from 'react';
import {
  Alert,
  Box,
  Container, Grid,
  Stack,
  Typography
} from '@mui/material';
import {
  useState,Fragment, useEffect
} from 'react';

import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { ToastContainer ,toast} from 'react-toastify';
import { useStyles } from '../customer/Style';

const Dashboard = () => {
  const classes = useStyles();
  return (
    <Page title="Dashboard">
        <Fragment>
        <Container>
        <ToastContainer />
          {/* { loading ? <AppLoader/> :null} */}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                  Dashboard
                </Typography>
            </Stack>
            <Alert variant="outlined" severity="info">
              No Dashboard found, please add one or try to refresh
          </Alert>
        </Container>
         </Fragment>
    </Page>
  )
}

export default Dashboard;
