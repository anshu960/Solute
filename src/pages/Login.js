import * as React from 'react';
import { Link as RouterLink } from 'react-router-dom';
// material
import { styled } from '@mui/material/styles';
import { Card, Stack, Link, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';
import { MHidden } from '../components/@material-extend';
import { LoginForm } from '../components/authentication/login';
import ImageConfig from "./../config/image_config"
import { useEffect } from 'react';


import { toast, ToastContainer } from 'react-toastify';

// ----------------------------------------------------------------------

const RootStyle = styled(Page)(({ theme }) => ({
  [theme.breakpoints.up('md')]: {
    display: 'flex'
  }
}));

const SectionStyle = styled(Card)(({ theme }) => ({
  width: '100%',
  maxWidth: 464,
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  margin: theme.spacing(2, 0, 2, 2)
}));

const ContentStyle = styled('div')(({ theme }) => ({
  maxWidth: 480,
  margin: 'auto',
  display: 'flex',
  minHeight: '100vh',
  flexDirection: 'column',
  justifyContent: 'center',
  padding: theme.spacing(12, 0)
}));

// ----------------------------------------------------------------------


export default function Login() {
  const getParameterByName = (...name) => {
    const paramObj = {};
    name.map((key) => {
        const match = RegExp(`[?&]${key}=([^&]*)`).exec(window.location.hash);
        paramObj[key] = match && decodeURIComponent(match[1].replace(/\+/g, ' '));
    });
    return paramObj;
}
  const {
    mobileNumber
} = getParameterByName('mobileNumber');
 
  
  return (
    <RootStyle title="Login">
      <ToastContainer></ToastContainer>
      

      <MHidden width="mdDown">
        <SectionStyle>
          {/* <Typography variant="h3" sx={{ px: 5, mt: 10, mb: 5 }}>
            Solute
          </Typography> */}
           <img src={ImageConfig.Logo} alt="Solute" />
        </SectionStyle>
      </MHidden>

      <Container maxWidth="sm">
        <ContentStyle>
          <Stack sx={{ mb: 5 }}>
            <Typography variant="h4" gutterBottom>
              Verify Your mobile number to continue
            </Typography>
          </Stack>
          {/* <AuthSocial /> */}
          <LoginForm mobileNumber={mobileNumber}/>
        </ContentStyle>
      </Container>
    </RootStyle>
  );
}
