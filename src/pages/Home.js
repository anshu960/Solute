import * as React from 'react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
// material
import { styled } from '@mui/material/styles';
import {
  Box,
  Container,
  Grid,
  Typography,
  Link, Paper, CardActionArea, Avatar,
} from '@mui/material';
// components
import Page from '../components/Page';
import { MHidden } from '../components/@material-extend';
import { useEffect } from 'react';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import PhoneIcon from '@mui/icons-material/Phone';
import { ToastContainer } from 'react-toastify';
import { ScrollDialog } from '../dialog';
import { JoinRoom } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import { ChooseBusiness } from '../components/home';
import HomeNavbar from '../layout/home/HomeNavbar';
import {useDispatch, useSelector} from 'react-redux'
import {retriveBusiness, setSelectedBusiness} from './../store/business';
import {retriveBusinessTypes} from './../store/businessTypes';
import { getUniqueId } from '../common/Utils';
import { getBusinessId, getUser, getUserId, setBusiness, setBusinessId } from '../services/authService';
import { PATH_AUTH, PATH_DASHBOARD } from '../routes/path';
import Room from '@mui/icons-material/Room';
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';
// ----------------------------------------------------------------------

const RootStyle = styled(Page)(({ theme }) => ({
  minHeight: '100%',
  paddingTop: theme.spacing(15),
  paddingBottom: theme.spacing(10)
}));

// ----------------------------------------------------------------------

export default function Home() {
  const dispatch = useDispatch()
  const history = useHistory();
  const [open, setOpen] = React.useState(false);
  const [businessId, setBusId] = React.useState();
  const allBusiness = useSelector(state => state.business.allBusiness)
  const saleToday = useSelector(state => state.business_analytics.saleToday)
  const businessTypes = useSelector(state => state.businessTypes.businessTypes)
  const isConnected = useSelector(state => state.room.isConnected)  
  const validateUserLogin=()=>{
    if(!getUserId() || !getUser()){
      history.push(PATH_AUTH.login)
    }else{

    }
  }
 const getSaleAmountForBusiness=(businessId)=>{
   let amount = 0 
    if(saleToday && saleToday.length){
      saleToday.forEach(sale => {
        if(sale._id === businessId){
          amount = sale.TotalSale;
          console.log("id not matching business matched",sale,amount,businessId,saleToday)
        }
      });
    }else{
      console.log("id not matching business saletoday is empty",saleToday)
    }
    return amount;
  }

  useEffect(()=>{
    let UserID = getUserId();
    const BusinessID = getBusinessId();
    if(!UserID){
      UserID = getUniqueId();
      JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID},handleJoinRoomEvent)
    }
    console.log('useEffect', UserID,BusinessID)
    if(isConnected){
      dispatch(retriveBusiness({UserID,BusinessID}));
      dispatch(retriveBusinessTypes({UserID}));
    }
    validateUserLogin()
  },[isConnected])

  const handleJoinRoomEvent = React.useCallback((data) => {
    console.log("handleJoinRoomEvent Home",data);
    let UserID = getUserId();
    dispatch(retriveBusinessTypes({UserID}));
  }, []);

const displayAddCard = () => {
  return(<Grid item xs={12} sm={6} md={3}>
    <Link component={RouterLink} 
      onClick={()=>setOpen(true)} 
      underline="none">
      <Paper
        sx={{
          p: 1,
          boxShadow: (theme) => theme.customShadows.z8,
          '&:hover img': { transform: 'scale(1.1)' },
          height:'240px', overflow: 'hidden'
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
            height:'225px',
          }}
        >
          <AddOutlinedIcon />
          <Typography sx={{marginLeft: '5px',}} variant="h6"> Add </Typography>
        </CardActionArea> 
      </Paper>
    </Link>
    </Grid>)
}

const getBusinessImageUrl=(id)=>{
  const businessType = businessTypes.filter((bType)=>bType._id === id)
   if(businessType && businessType.length && businessType[0].Image){
    return businessType[0].Image[0];
   }
}

const displayBusinessCard = () => {
  return(allBusiness.map((business) => (
          <Grid item xs={12} sm={6} md={3}>
          <Link component={RouterLink} underline="none">
          <Paper
        sx={{
          p: 1,
          boxShadow: (theme) => theme.customShadows.z8,
          '&:hover img': { transform: 'scale(1.1)' },
          height:'240px', overflow: 'hidden',
        }}
        onClick={()=>{
          setBusinessId(business._id);
          setBusiness(business);
          dispatch(setSelectedBusiness(business,businessTypes,(selectedBusinessType)=>{
            if(selectedBusinessType.BusinessTypeID === 27){
              history.push(PATH_DASHBOARD.fee.fee);
            }else{
              history.push(PATH_DASHBOARD.sale.sale);
            }
          }));
        }} 
      >
        <CardActionArea
          sx={{
            p: 3,
            borderRadius: 1,
            color: 'primary.main',
            bgcolor: 'background.neutral',
            justifyContent:'space-between',
            alignItems: 'flex-start',
            display: 'flex',
          }}
        > 
          <Avatar alt={"business"} src={getBusinessImageUrl(business.BusinessTypeID)} sx={{ width: 30, height: 30, padding: '3px' }} />  
          
          <Typography variant="h5" gutterBottom sx={{display: 'flex', color: 'primary.darker'}}>
            <CurrencyRupeeIcon sx={{width: 20, color: 'primary.darker'}}/>
            {getSaleAmountForBusiness(business._id)}
          </Typography>
        </CardActionArea> 
          <Box sx={{display: 'flex', alignItems: 'center'}}>
            <img alt={"business"} src={getBusinessImageUrl(business.BusinessTypeID)} style={{ width: 20 }} />
            <Typography variant="subtitle2" sx={{ p: 1, color: 'primary.darker',}}>
              {business.Name}
            </Typography>
          </Box>
          <Box sx={{display: 'flex', alignItems: 'center'}}>
            <PhoneIcon sx={{width: 20, color: '#0433ff'}}/>
            <Typography variant="subtitle2" sx={{ p: 1, color: 'primary.darker',}}>
              {business.MobileNumber}
            </Typography>
          </Box>
          <Box sx={{display: 'flex', alignItems: 'center'}}>
            <Room sx={{width: 20, color: '#0433ff'}}/>
            <Typography variant="subtitle2" sx={{ p: 1, color: 'primary.darker',}}>
              {business.Address}
            </Typography>
          </Box>
      </Paper>
      </Link>
    </Grid>
        ))
    )
}
  console.log(allBusiness)
  const handleClose = () => setOpen(false);
  const bodyContent = () => <ChooseBusiness history={history} businessTypes={businessTypes} businessId={businessId} setBusinessId={setBusId} handleClose={handleClose} open={open}/>
  return (
    <RootStyle title="Home">
      <ToastContainer></ToastContainer>
      <HomeNavbar />
      <Container maxWidth="lg">
        <Box sx={{ mb: 5 }}>
          <Typography variant="h3" align="center" paragraph>
            Let's start powering you up!
          </Typography>
          <Typography align="center" sx={{ color: 'text.secondary' }}>
            Choose/Register business to FuelMe.
          </Typography>
        </Box>
        <Grid container spacing={3} sx={{ my: 10 }}>
          <ScrollDialog body={bodyContent()} handleClose = {handleClose} scroll={'paper'} title="Choose Business" open={open} dialogWidth="xl"/>
          {displayAddCard()}
          {(allBusiness && allBusiness.length) ? displayBusinessCard(): null}
        </Grid>
      </Container>
    </RootStyle>
  );
}
