import * as React from 'react';
import {
    Box, Card,Container,Stack, Typography,Tabs,Tab,
} from '@mui/material';
import { styled } from '@mui/material/styles';
import {
  useState,Fragment
} from 'react';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../../components/Page';
import {useDispatch, useSelector} from 'react-redux'
import AppLoader from '../../components/Loader';
import { ToastContainer, toast } from 'react-toastify';
import { retriveShipment } from '../../store/shipment';
import { getParameterByName } from '../../common/Utils';
import { SenderInformation, ProfileCover, DeliveryBoyInformation, ShipmentInformation, ReceiverInformation } from '../../components/shipment/profile';

// ----------------------------------------------------------------------

const TabsWrapperStyle = styled('div')(({ theme }) => ({
    zIndex: 9,
    bottom: 0,
    width: '100%',
    display: 'flex',
    position: 'absolute',
    backgroundColor: theme.palette.background.paper,
    [theme.breakpoints.up('sm')]: {
      justifyContent: 'center',
    },
    [theme.breakpoints.up('md')]: {
      justifyContent: 'flex-end',
      paddingRight: theme.spacing(3),
    },
  }));
  
  // ----------------------------------------------------------------------
  

const Profile = () => {
  const history = useHistory();
  const dispatch = useDispatch()
  const [loading, setLoading] = useState(false);
  const [currentTab, setCurrentTab] = useState('shipment');
  const allShipment= useSelector(state=>state.shipment.allShipment)
  const {id} = getParameterByName("id");
  const handleChangeTab = (newValue) => {
    setCurrentTab(newValue);
  };
  

  React.useEffect(()=>{
    const startDate = new Date()
    const endDate = new Date()
    if(!allShipment.length){
      dispatch(retriveShipment(startDate,endDate))
    }
},[])
const shipment = allShipment.length ? allShipment.filter((shipment)=> shipment.ShipmentID == id) : []
console.log(shipment,id)
  const PROFILE_TABS = [
    {
      value: 'shipment',
      //icon: ,
      component: <ShipmentInformation shipment={shipment}/>,
    },
    {
        value: 'delivery boy',
        //icon: ,
        component: <DeliveryBoyInformation shipment={shipment}/>,
    },
    {
        value: 'receiver',
        //icon: ,
        component: <ReceiverInformation shipment={shipment}/>,
    },
    {
      value: 'sender',
      //icon: ,
      component: <SenderInformation shipment={shipment}/>,
    },
  ];

  
  return (
    <Page title="Shipment">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Shipment
                </Typography>
            </Stack>
            <Card
          sx={{
            mb: 3,
            height: 280,
            position: 'relative',
          }}
        >
          <ProfileCover shipment={shipment}/>

          <TabsWrapperStyle>
            <Tabs
              value={currentTab}
              scrollButtons="auto"
              variant="scrollable"
              allowScrollButtonsMobile
              onChange={(e, value) => handleChangeTab(value)}
            >
              {PROFILE_TABS.map((tab) => (
                <Tab
                  disableRipple
                  key={tab.value}
                  value={tab.value}
                  //icon={tab.icon}
                  label={tab.value}
                />
              ))}
            </Tabs>
          </TabsWrapperStyle>
        </Card>

        {PROFILE_TABS.map((tab) => {
          const isMatched = tab.value === currentTab;
          return isMatched && <Box key={tab.value}>{tab.component}</Box>;
        })}
        </Container>
        </Fragment>
    </Page>)
}

export default Profile;
