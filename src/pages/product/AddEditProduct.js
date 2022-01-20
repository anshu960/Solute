import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import {
  useState,useCallback, Fragment
} from 'react';
import Page from '../../components/Page';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer, toast } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../../services/authService';
import { ProductCreate } from '../../components/product';
import { DatePickerComponent } from '../../components/Picker';
import { useDispatch } from 'react-redux';
import { uploadProductImage, uploadProfileImage } from '../../store/fileUpload';
import { createProduct } from '../../store/product';

const useStyles = makeStyles((theme)=>createStyles({
  actionList: {
      display: 'flex',
      justifyContent: 'flex-end',
      flexGrow: '1',
  },
  datePicker: {
      height: '48px',
  },
}));

const AddEditProduct = () => {
  const dispatch = useDispatch()
  const classes = useStyles();
  const [loading, setLoading] = useState(false);
  const [priceDate, setPriceDate] = useState(new Date());  
  const handleAddProduct = (fields,images) => {
    dispatch(createProduct(fields,images,(data)=>{
    }));
  }

  return (
    <Page title="Product">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Add Product
                </Typography>
                <Box className={classes.actionList}>
                    <DatePickerComponent selectedDate={priceDate} setDate={setPriceDate}/>
                </Box>
            </Stack>
            <ProductCreate handleAddProduct={handleAddProduct}/>
        </Container>
        </Fragment>
    </Page>

  )
}

export default AddEditProduct;
