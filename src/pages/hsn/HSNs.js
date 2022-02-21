import * as React from 'react';
import {
    Alert,
  Box, Button, Card, CardActionArea, Container, Grid,
  Paper,
  Stack,
  TableContainer,
  Typography
} from '@mui/material';

import DatePicker from 'react-datepicker';
import { createStyles, makeStyles } from '@mui/styles';
import  {
    useEffect,
  useState,
} from 'react';
import { useHistory, Link as RouterLink, Link } from 'react-router-dom';
import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { retriveHSN } from '../../store/hsn';
import { getBusinessId, getUserId } from '../../services/authService';
import { useDispatch, useSelector } from 'react-redux';
import { PATH_DASHBOARD } from '../../routes/path';
import { setSelectedHSN } from '../../store/hsn';

const useStyles = makeStyles((theme)=>createStyles({
    actionList: {
        display: 'flex',
        justifyContent: 'flex-end',
        flexGrow: '1',
    },
    selctAutTarDate: {
        color:'#fff',
        marginRight: '7px',
        position: 'relative',
        [theme.breakpoints.between('1024', '1400')]: {
            width: '18%',
        },
        '& input': {
            padding: '10px 0px 10px 10px',
            border: '1px solid #cccccc',
            borderRadius: '4px',
        },
    },
    calendarIcon: {
        position: 'absolute',
        right: '40px',
        top: '14px',
        color: '#8F8FB3',
        fontSize: '14px',
        zIndex: '99',
    },
    datePicker: {
        height: '48px',
    },
  }));

const HSNs = () => {
    const dispatch = useDispatch()
  const classes = useStyles();
  const history = useHistory();
  const [loading, setLoading] = useState(false);
  const [saleDate, setSaleDate] = useState(new Date());
  const allHSN = useSelector(state => state.hsn.allHSN)

    useEffect(()=>{
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        dispatch(retriveHSN({UserID,BusinessID}));
        
    },[]);

    const getHSN = (hsn) => {
        return(
            <Grid item xs={12} sm={6} md={3}>
            <Link component={RouterLink}
            onClick={()=>{
              dispatch(setSelectedHSN(hsn));
              history.push({ pathname: PATH_DASHBOARD.hsn.add, search: `?id=${hsn.HSNID}` })
            }}
            underline="none">
              <Paper
                sx={{
                  p: 1,
                  boxShadow: (theme) => theme.customShadows.z8,
                  '&:hover img': { transform: 'scale(1.1)' }
                }}
              >
                <CardActionArea
                  sx={{
                    p: 3,
                    borderRadius: 1,
                    color: 'primary.main',
                    bgcolor: 'background.neutral',
                    justifyContent:'center', 
                    display: 'flex',
                      alignItems: 'center',
                  }}
                >
                    HSN
                </CardActionArea>
                <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                  {hsn.Name}
                </Typography>
                <Box sx={{display: 'flex', alignItems: 'center'}}>
                </Box>
              </Paper>
            </Link>
        </Grid>
        )
    }

    return (
    <Page title="HSN">
        <React.Fragment>
          <Container>
          <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    HSNs
                </Typography>
                
            </Stack>
            <Grid container spacing={3} sx={{ my: 10 }}>
            { allHSN.length 
            ? allHSN.map((hsn) =>  getHSN(hsn))
            : <Grid item xs={12}>
                <Box>
                    <Alert variant="outlined" severity="info">
                        No HSN to Display Please Add Add
                    </Alert>
                </Box>
            </Grid> }
        </Grid>
        </Container>
        </React.Fragment>
    </Page>

  )
}

export default HSNs;
