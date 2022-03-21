import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import {
  useState,useCallback, useEffect,Fragment
} from 'react';
import Page from '../../components/Page';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer } from 'react-toastify';
import { getBusinessId, getUserId } from '../../services/authService';
import { AddRoomButton, RoomList } from '../../components/hotel';
import { DatePickerComponent } from '../../components/Picker';

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

const defaulState = {
  Name:'',
  Price: ''
}

const Rooms = () => {
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
    <Page title="Rooms">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Rooms
                </Typography>
                <Box className={classes.actionList}>
                    <DatePickerComponent selectedDate={priceDate} setDate={setPriceDate}/>
                    <AddRoomButton />
                </Box>
            </Stack>
            <RoomList productList={productList} selectedDate={priceDate}/>
        </Container>
        </Fragment>
    </Page>

  )
}

export default Rooms;
