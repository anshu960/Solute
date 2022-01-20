import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';

import DatePicker from 'react-datepicker';
import { createStyles, makeStyles } from '@mui/styles';
import  {
    useEffect,
  useState,
} from 'react';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { HSNCreate } from '../../components/hsn';

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

const AddEditHSN = () => {
  const classes = useStyles();
  const history = useHistory();
  const [loading, setLoading] = useState(false);
  const [saleDate, setSaleDate] = useState(new Date());
  

    useEffect(()=>{
    },[]);
      
    return (
    <Page title="HSN">
        <React.Fragment>
          <Container>
          <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Add HSN
                </Typography>
                <Box className={classes.actionList}>
                    <Box component="span" className={classes.selctAutTarDate}>
                        <DatePicker
                            placeholderText="Select Date"
                            className={classes.butonScrmenuSe}
                            selected={saleDate}
                            onChange={(date) => setSaleDate(date)}
                        />
                    </Box>
                </Box> 
            </Stack>
            <Box>
                <HSNCreate />
            </Box>
        </Container>
        </React.Fragment>
    </Page>

  )
}

export default AddEditHSN;
