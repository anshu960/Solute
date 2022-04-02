import * as React from 'react';
// material
import { styled } from '@mui/material/styles';
// components
import Page from '../../components/Page';
import { ToastContainer } from 'react-toastify';
import { JoinRoom } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import LandingNavbar from '../../layout/landing/LandingNavbar';
import { MainCarousel, WhySolute, WhyChose, Appfooter} from '../../components/landing';
// ----------------------------------------------------------------------

const RootStyle = styled(Page)(({ theme }) => ({
  minHeight: '100%',
  paddingTop: theme.spacing(12),
  [theme.breakpoints.up('md')]: {
    //paddingTop: theme.spacing(10)
  }
  //paddingBottom: theme.spacing(10)
}));

// ----------------------------------------------------------------------

export default function Landing() {
  
  return (
    <RootStyle title="Home">
      <ToastContainer></ToastContainer>
      <LandingNavbar />
      <MainCarousel />
      <WhySolute />
      <WhyChose />
      <Appfooter />
    </RootStyle>
  );
}
