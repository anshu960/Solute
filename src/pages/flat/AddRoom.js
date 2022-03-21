import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import {
  useState, Fragment
} from 'react';
import Page from '../../components/Page';
import AppLoader from '../../components/Loader';
import { ToastContainer } from 'react-toastify';
import { RoomCreate } from '../../components/room';
import { DatePickerComponent } from '../../components/Picker';
import { useDispatch } from 'react-redux';
import { createProduct } from '../../store/product';

const useStyles = makeStyles(()=>createStyles({
  actionList: {
      display: 'flex',
      justifyContent: 'flex-end',
      flexGrow: '1',
  },
  datePicker: {
      height: '48px',
  },
}));

const AddRoom = () => {
  const dispatch = useDispatch()
  const classes = useStyles();
  const [loading, setLoading] = useState(false);
  const [priceDate, setPriceDate] = useState(new Date());  
  const handleAddProduct = (fields,images) => {
    dispatch(createProduct(fields,images,(data)=>{
    }));
  }

  return (
    <Page title="Room">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Add Room
                </Typography>
                <Box className={classes.actionList}>
                    <DatePickerComponent selectedDate={priceDate} setDate={setPriceDate}/>
                </Box>
            </Stack>
            <RoomCreate handleAddProduct={handleAddProduct}/>
        </Container>
        </Fragment>
    </Page>

  )
}

export default AddRoom;
