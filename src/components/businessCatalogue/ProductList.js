import React from 'react';
// material
import { alpha, useTheme, styled } from '@mui/material/styles';
import { Box, Grid, Card, Container, Typography, useMediaQuery, Link } from '@mui/material';
import { useSelector } from 'react-redux';
import { Link as RouterLink, useLocation, useHistory } from 'react-router-dom';
import BusinessCenterOutlinedIcon from '@mui/icons-material/BusinessCenterOutlined';
// ----------------------------------------------------------------------


const shadowIcon = (color) => `drop-shadow(2px 2px 2px ${alpha(color, 0.48)})`;

const RootStyle = styled('div')(({ theme }) => ({
  //paddingTop: theme.spacing(5),
  [theme.breakpoints.up('md')]: {
    paddingBottom: theme.spacing(15)
  }
}));

const AccountStyle = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'space-between',
  padding: theme.spacing(2, 2.5),
  borderRadius: theme.shape.borderRadiusSm,
  backgroundColor: theme.palette.grey[200]
}));

// ----------------------------------------------------------------------

export default function ProductList() {
  const statistics = useSelector(state=>state.Statistics.PlatformStatistics)
  const PRODUCTS = [{
    name: 'prd1',
    price: '500'
  },{
    name: 'prd2',
    price: '200'
  },{
  name: 'prd3',
  price: '500'
  },
  {
    name: 'prd4',
    price: '200'
  },
  {
    name: 'prd5',
    price: '200'
  }];
  const theme = useTheme();
  
  const isLight = theme.palette.mode === 'light';
  const isDesktop = useMediaQuery(theme.breakpoints.up('lg'));

  return (
    <RootStyle>
      <Container maxWidth="lg">
        <Box sx={{ mb: { xs: 5, md: 15, textAlign: 'center' } }}>
          <div 
          //variants={varFadeInDown}
          >
            <Typography variant="h3" sx={{ textAlign: 'center' }}>
              Products
            </Typography>
          </div>
        </Box>

        <Grid container >
          {PRODUCTS.map((prd, index) => (
            <Grid key={index} item xs={6} md={4} lg={3} xl={3}>
            <Box sx={{ mb: 5, mx: 2.5 }}>
            <Link underline="none" component={RouterLink} to="#">
              <AccountStyle>
                <Box sx={{ display: 'flex', }}>
                  <BusinessCenterOutlinedIcon sx={{background: '#eee', border: '2px solid',borderRadius: '7px'}} />
                  <Typography variant="subtitle2" sx={{ color: 'text.primary', ml: 2 }}>
                    {prd.name}
                  </Typography>
                  {/* <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                    {"₹"}{prd.price }
                  </Typography> */}
                </Box>
                <Box>
                  <Typography variant="body2" sx={{ color: 'text.secondary' }}>
                    {"₹"}{prd.price }
                  </Typography>
                </Box>
              </AccountStyle>
            </Link>
          </Box>
            {/* <Grid key={index} item xs={6} md={4} lg={3} xl={3}>
              <div>
                <Typography variant="h3" paragraph>
                  {prd.name}
                </Typography>
                <Typography variant="h4" paragraph>
                  {"₹"}{prd.price }
                </Typography>
              </div> */}
            </Grid>
          ))}
        </Grid>
      </Container>
    </RootStyle>
  );
}
