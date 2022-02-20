import React from 'react';
// material
import { alpha, useTheme, styled } from '@mui/material/styles';
import { Box, Grid, Card, Container, Typography, useMediaQuery } from '@mui/material';
// ----------------------------------------------------------------------

const images = [
  'https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fhome_tiles%2Fsecure.png?alt=media&token=199fe594-3a35-425a-bcbc-58ebe65f224a',
  'https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fhome_tiles%2Freliable.png?alt=media&token=97fb9217-4663-4d9e-a7bb-1818a6529c7f',
  'https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fhome_tiles%2Fhappy_customer.png?alt=media&token=ed8df6e6-23a3-40c8-9cb8-1435e9fdd1f7',
]

const CARDS = [
  {
    icon: images[0],
    title: 'Secured',
    description:
    'Completely secured online, no access to anyone else but you.'
      //'Register wide range of business at one platform and enjoy the hassle free experience.'
  },
  {
    icon: images[1],
    title: 'Reliable',
    description: 
    'Manage multiple businesses from any device throughout the world.',
    //'Manage the complete business from sale, delivery, invoice, sharable receipt to reports with analytics'
  },
  {
    icon: images[2],
    title: 'Happy Customer',
    description: `5000+ happy customers and counting.
    Your first 3 months is on us.No payment required at all.`
  }
];

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

export default function WhySolute() {
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
              Solute
            </Typography>
          </div>
          <div 
          //variants={varFadeInDown}
          >
            <Typography variant="h2" sx={{ textAlign: 'center' }}>
              Why Solute?
            </Typography>
          </div>
          <div //variants={varFadeInDown}
          >
          <Typography
            sx={{
              color: (theme) => (theme.palette.mode === 'light' ? 'text.secondary' : 'text.primary')
            }}
          >
            Solute provides everything you need to grow your business with trust.
          </Typography>
        </div>
        </Box>

        <Grid container spacing={isDesktop ? 10 : 5}>
          {CARDS.map((card, index) => (
            <Grid key={card.title} item xs={12} md={4}>
              <div 
              //variants={varFadeInUp}
              >
                <CardStyle className={(index === 0 && 'cardLeft') || (index === 1 && 'cardCenter')}>
                  <CardIconStyle
                    src={card.icon}
                    alt={card.title}
                    sx={{
                      ...(index === 0 && {
                        filter: (theme) => shadowIcon(theme.palette.info.main)
                      }),
                      ...(index === 1 && {
                        filter: (theme) => shadowIcon(theme.palette.error.main)
                      })
                    }}
                  />
                  <Typography variant="h5" paragraph>
                    {card.title}
                  </Typography>
                  <Typography sx={{ color: isLight ? 'text.secondary' : 'common.white' }}>
                    {card.description}
                  </Typography>
                </CardStyle>
              </div>
            </Grid>
          ))}
        </Grid>
      </Container>
    </RootStyle>
  );
}
