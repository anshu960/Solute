import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import RemoveCircleIcon from '@mui/icons-material//RemoveCircle';
import {
  useState,useCallback, useEffect,Fragment
} from 'react';
import Page from '../../components/Page';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer, toast } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../../services/authService';
import { AddProductButton, ProductList } from '../../components/product';
import { DatePickerComponent } from '../../components/Picker';
// dropzone
//import Dropzone from 'react-dropzone';

const useStyles = makeStyles((theme)=>createStyles({
  rightSection: {
      width: '96%',
  },
  inRightSection: {
      padding: '25px 70px 20px 32px',
      // [breakpoints.between('1024', '1400')]: {
      //     padding: '18px',
      // },
  },
  adminButton: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      color: '#fff',
  },
  addNewGroup: {
      fontSize: '18px',
      color: '#fff',
      border: '1px solid #428BCA',
      borderRadius: '2px',
      backgroundColor: '#428BCA',
      height: '48px',
      marginLeft: '15px',
      width: '205px',
      '&:hover': {
          backgroundColor: '#428BCA',
      },
  },
  inAdminButton: {
      display: 'flex',
  },
  addNewGroupBulk: {
    //   fontSize: '18px',
    //   color: '#428BCA',
    //   border: '1px solid #428BCA',
    //   borderRadius: '2px',
    //   //backgroundColor: '#fff',
    //   height: '48px',
      width: '165px',
      marginLeft: '70px',
      // [breakpoints.between('1024', '1400')]: {
      //     marginLeft: '0px',
      // },
  },
  bottomButtonExpert: {
      textAlign: 'right',
  },
  selectBoxStyle: {
      '& span': {
          display: 'none',
      },
  },
  selectBoxSectionTarget: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'flex-end',
  },
  selctAutTar: {
      width: '14%',
      marginRight: '7px',
      position: 'relative',
      // [breakpoints.between('1024', '1400')]: {
      //     width: '22%',
      // },
      '& div': {
          width: '100%',
          borderRadius: '2px',
      },
      '& input': {
          padding: '15px 14px',
          fontSize: '15px',
      },
  },
  actionList: {
      display: 'flex',
      justifyContent: 'flex-end',
      flexGrow: '1',
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
  textField: {
      color: '#428BCA',
      border: '1px solid #428BCA',
      fontSize: '15px',
      fontFamily: 'Gilroy-Semibold',
      padding: '5px 10px',
      '& input' : {
          color: '#428BCA',
      },
  },
  saleRate:{
      //color:'#4289c7',
      border:'none',
      textAlign:'center',
      width: '100px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[300]
  },
}));

const defaulState = {
  Name:'',
  Price: ''
}

const Products = () => {
  const classes = useStyles();
  const [selectedProduct, setselectedProduct] = useState(defaulState);
  const [productList, setProductList] = useState([]);
  const [recieve, setRecieve] = useState(0);
  const [loading, setLoading] = useState(false);
  const [priceDate, setPriceDate] = useState(new Date());

  useEffect(()=>{
    refreshProductList();
  },[priceDate])

  const refreshProductList=()=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID,Date:priceDate}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    SendEvent(SocketEvent.RETRIVE_PRODUCT,request,handleRetriveProductEvent)
  }
  const handleRetriveProductEvent = useCallback((data) => {
    setLoading(false);
    console.log("handleRetriveProductsEvent",data,typeof(data.Payload))
    if(data && data.Payload && data.Payload.length > 0){
        setProductList(data.Payload);
        setselectedProduct(defaulState);
    }else{
        console.log("Unable to find customer, please try after some time");
    }
  }, []);

  return (
    <Page title="Product">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Product
                </Typography>
                <Box className={classes.actionList}>
                    <DatePickerComponent selectedDate={priceDate} setDate={setPriceDate}/>
                    <AddProductButton />
                </Box>
            </Stack>
            <ProductList productList={productList} selectedDate={priceDate}/>
        </Container>
        </Fragment>
    </Page>

  )
}

export default Products;
