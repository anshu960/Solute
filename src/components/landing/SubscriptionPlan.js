import React from 'react';
// material
import { alpha, useTheme, styled } from '@mui/material/styles';
import { Box, Grid, Card, Container, Typography, useMediaQuery } from '@mui/material';
// ----------------------------------------------------------------------

const CARDS = [{
  "_id":  "6245335d808ed504a6e83874",
  "Description": [
    "14 Days trial pack",
    "It is a trial pack and completely free of cost.", 
    "It is applied only first time.",
    ],
  "Title": "14 Days",
  "Days": 14,
  "Amount": 0,
  "Currency": "INR",
},{
  "_id":  "62453547808ed504a6e83875",
  "Description": [
    "It is monthly rental plan.",
    "30 days plan."
    ],
  "Title": "Monthly",
  "Days": 30,
  "Amount": 199,
  "Currency": "INR"
},{
  "_id": "62453576808ed504a6e83876",
  "Description": [
    "It is quarterly rental plan.",
    "90 days plan."
  ],
  "Title": "Quarterly",
  "Days": 90,
  "Amount": 499,
  "Currency": "INR"
},{
  "_id":"62453606808ed504a6e83877",
  "Description": [
    "It is half yearly paid plan",
    "180 days plan."
    ],
  "Title": "Half Yearly",
  "Days": 180,
  "Amount": 799,
  "Currency": "INR"
},{
  "_id": "62453654808ed504a6e83878",
  "Description": [
    "It is 9 month paid plan.",
    "270 days plan."
  ],
  "Title": "9 Month",
  "Days": 270,
  "Amount": 1099,
  "Currency": "INR"
},{
  "_id": "62453698808ed504a6e83879",
  "Description": [
    "It is yearly rental plan.",
    "365 days plan."
    ],
  "Title": "Yearly",
  "Days": 365,
  "Amount": 1399,
  "Currency": "INR"
}];

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

export default function SubscriptionPlan() {
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
              Membership
            </Typography>
          </div>
          <div 
          //variants={varFadeInDown}
          >
            <Typography variant="h2" sx={{ textAlign: 'center' }}>
              Affordable Plans?
            </Typography>
          </div>
          <div //variants={varFadeInDown}
          >
          <Typography
            sx={{
              color: (theme) => (theme.palette.mode === 'light' ? 'text.secondary' : 'text.primary')
            }}
          >
            Solute offers the various plans which easily affordable.
          </Typography>
        </div>
        </Box>

        <Grid container spacing={isDesktop ? 10 : 5}>
          {CARDS.map((card, index) => (
            <Grid key={card.title} item xs={12} md={4}>
              <div 
              //variants={varFadeInUp}
              >
                <CardStyle className="cardLeft">
                    <Typography variant="h3" paragraph>
                    {"₹" + " " + card.Amount}
                      {/* {card.Title} */}
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
