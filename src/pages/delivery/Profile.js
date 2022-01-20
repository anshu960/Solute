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
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { ToastContainer, toast } from 'react-toastify';
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
  const [loading, setLoading] = useState(false);
  const [currentTab, setCurrentTab] = useState('shipment');
  const handleChangeTab = (newValue) => {
    setCurrentTab(newValue);
  };

  const PROFILE_TABS = [
    {
      value: 'shipment',
      //icon: ,
      component: <ShipmentInformation />,
    },
    {
        value: 'delivery boy',
        //icon: ,
        component: <DeliveryBoyInformation />,
    },
    {
        value: 'receiver',
        //icon: ,
        component: <ReceiverInformation />,
    },
    {
      value: 'sender',
      //icon: ,
      component: <SenderInformation />,
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
          <ProfileCover />

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
