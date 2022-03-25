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
import { CourseAction } from '../../components/courses';
import { useStyles } from '../customer/Style';

const Courses = () => {
  const classes = useStyles();
  const [loading, setLoading] = useState(false);
  
  useEffect(()=>{  
    
  },[])

  return (
    <Page title="Courses">
        <Fragment>
        <Container>
        <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                Courses
                </Typography>
                <CourseAction />
            </Stack>
            <Alert variant="outlined" severity="info">
              No Courses found, please add one or try to refresh
          </Alert>
        </Container>
         </Fragment>
    </Page>

  )
}

export default Courses;
