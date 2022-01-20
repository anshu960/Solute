import * as React from 'react';
import { Link as RouterLink } from 'react-router-dom';
// material
import { styled } from '@mui/material/styles';
import { Box, Card, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';
import { MHidden } from '../components/@material-extend';
import { RegisterBusinessForm } from '../components/authentication/registerBusiness';
import image_config from '../config/image_config';
import { useSelector} from 'react-redux'


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

export default function RegisterBusiness() {
  const businessTypes = useSelector(state => state.businessTypes.businessTypes)
  return (
    <RootStyle title="Register">
      <MHidden width="mdDown">
      <SectionStyle>
           <img src={image_config.Logo} alt="Fuel Me" />
        </SectionStyle>
      </MHidden>

      <Container maxWidth="sm">
        <ContentStyle>
          <Box sx={{ mb: 5 }}>
            <Typography variant="h4" gutterBottom>
              Register Business to Fuel Me
            </Typography>
            <Typography sx={{ color: 'text.secondary' }}>
              Enjoy the best experience.
            </Typography>
          </Box>
          <RegisterBusinessForm businessTypes={businessTypes}/>
        </ContentStyle>
      </Container>
    </RootStyle>
  );
}
