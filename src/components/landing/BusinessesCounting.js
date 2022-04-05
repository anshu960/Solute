import React from 'react';
// material
import { alpha, useTheme, styled } from '@mui/material/styles';
import { Box, Grid, Card, Container, Typography, useMediaQuery } from '@mui/material';
import { useSelector } from 'react-redux';
// ----------------------------------------------------------------------


const shadowIcon = (color) => `drop-shadow(2px 2px 2px ${alpha(color, 0.48)})`;

const RootStyle = styled('div')(({ theme }) => ({
  paddingTop: theme.spacing(15),
  [theme.breakpoints.up('md')]: {
    paddingBottom: theme.spacing(15)
  }
}));

const CardStyle = styled(Card)(({ theme }) => {
  const shadowCard = (opacity) =>
    theme.palette.mode === 'light'
      ? alpha(theme.palette.grey[500], opacity)
      : alpha(theme.palette.common.black, opacity);

  return {
    maxWidth: 380,
    minHeight: 440,
    margin: 'auto',
    textAlign: 'center',
    padding: theme.spacing(10, 5, 0),
    boxShadow: `-40px 40px 80px 0 ${shadowCard(0.48)}`,
    [theme.breakpoints.up('md')]: {
      boxShadow: 'none',
      backgroundColor: theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800]
    },
    '&.cardLeft': {
      [theme.breakpoints.up('md')]: { marginTop: -40 }
    },
    '&.cardCenter': {
      [theme.breakpoints.up('md')]: {
        marginTop: -80,
        backgroundColor: theme.palette.background.paper,
        boxShadow: `-40px 40px 80px 0 ${shadowCard(0.4)}`,
        '&:before': {
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          zIndex: -1,
          content: "''",
          margin: 'auto',
          position: 'absolute',
          width: 'calc(100% - 40px)',
          height: 'calc(100% - 40px)',
          borderRadius: theme.shape.borderRadiusMd,
          backgroundColor: theme.palette.background.paper,
          boxShadow: `-20px 20px 40px 0 ${shadowCard(0.12)}`
        }
      }
    }
  };
});

const CardIconStyle = styled('img')(({ theme }) => ({
  width: 100,
  height: 100,
  margin: 'auto',
  marginBottom: theme.spacing(10),
  filter: shadowIcon(theme.palette.primary.main)
}));

// ----------------------------------------------------------------------

export default function BusinessesCounting() {
  const statistics = useSelector(state=>state.Statistics.PlatformStatistics)
  const CARDS = [{
    "Description": [
      
      ],
    "Title": "Happy Clients",
    "count": statistics ? statistics.UserCount : 0
  },{
    "Description": [
     
      ],
      "Title": "Happy Businesses",
    "count": statistics ? statistics.BusinessCount : 0
  }];
  const theme = useTheme();
  
  const isLight = theme.palette.mode === 'light';
  const isDesktop = useMediaQuery(theme.breakpoints.up('lg'));

  return (
    <RootStyle>
      <Container maxWidth="lg">
        <Box sx={{ mb: { xs: 10, md: 25, textAlign: 'center' } }}>
          <div 
          //variants={varFadeInUp}
          >
            <Typography component="p" variant="overline" sx={{ mb: 2, color: 'text.secondary', textAlign: 'center' }}>
              Businesses
            </Typography>
          </div>
          <div 
          //variants={varFadeInDown}
          >
            <Typography variant="h2" sx={{ textAlign: 'center' }}>
              Serving businesses and clients
            </Typography>
          </div>
          <div //variants={varFadeInDown}
          >
          <Typography
            sx={{
              color: (theme) => (theme.palette.mode === 'light' ? 'text.secondary' : 'text.primary')
            }}
          >
            Happy Businesses and clients.
          </Typography>
        </div>
        </Box>

        <Grid container spacing={isDesktop ? 10 : 5}>
          {CARDS.map((card, index) => (
            <Grid key={card.title} item xs={6} md={6}>
              <div>
                <CardStyle className="cardLeft">
                    <Typography variant="h3" paragraph>
                      {"😍"}
                    </Typography>
                    <Typography variant="h3" paragraph>
                      {card.Title}
                    </Typography>
                    <Typography variant="h4" paragraph>
                      {card.count } and counting
                    </Typography>
                    <Box sx={{textAlign: 'left'}}>
                    {card.Description.map((desc, index) => 
                      <Typography variant="h6" paragraph>
                      <li>{desc}</li>
                      </Typography>
                    )}
                    </Box>
                  </CardStyle>
              </div>
            </Grid>
          ))}
        </Grid>
      </Container>
    </RootStyle>
  );
}
