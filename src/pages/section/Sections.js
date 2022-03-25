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
import { SectionAction } from '../../components/sections';
import { useStyles } from '../customer/Style';

const Sections = () => {
  const classes = useStyles();
  const [loading, setLoading] = useState(false);
  
  useEffect(()=>{  
    
  },[])

  return (
    <Page title="Sections">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                  Sections
                </Typography>
                <SectionAction />
            </Stack>
            <Alert variant="outlined" severity="info">
              No Sections found, please add one or try to refresh
          </Alert>
        </Container>
         </Fragment>
    </Page>

  )
}

export default Sections;
