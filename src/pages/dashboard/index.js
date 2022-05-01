import * as React from 'react';
import {
  Container,
  Stack,
  Typography
} from '@mui/material';
import {
  Fragment,
} from 'react';

import Page from '../../components/Page';
import { ToastContainer} from 'react-toastify';
import { useStyles } from '../customer/Style';
import { SaleCounting } from '../../components/dashboard';

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
            <SaleCounting />
            {/*
            
            <WhySolute />
            <WhyChose /> */}
            {/* <Alert variant="outlined" severity="info">
              No Dashboard found, please add one or try to refresh
          </Alert> */}
        </Container>
         </Fragment>
    </Page>
  )
}

export default Dashboard;