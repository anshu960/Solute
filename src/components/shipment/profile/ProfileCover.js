import React, { useState } from 'react';
// @mui
import { alpha,styled, useTheme } from '@mui/material/styles';
import { Avatar, Box, Typography } from '@mui/material';
import ProfileImage from './ProfileImage';
// ----------------------------------------------------------------------

const RootStyle = styled('div')(({ theme }) => ({
  '&:before': {
    backgroundColor: alpha(theme.palette.primary.darker, 0.72),
    top: 0,
    zIndex: 9,
    content: "''",
    width: '100%',
    height: '100%',
    position: 'absolute',
  },
}));

const InfoStyle = styled('div')(({ theme }) => ({
  left: 0,
  right: 0,
  zIndex: 99,
  position: 'absolute',
  marginTop: theme.spacing(5),
  [theme.breakpoints.up('md')]: {
    right: 'auto',
    display: 'flex',
    alignItems: 'center',
    left: theme.spacing(3),
    bottom: theme.spacing(3),
  },
}));

// ----------------------------------------------------------------------

export default function ProfileCover({shipment}) {
  const [files, setFiles] = useState([]);
  return (
    <RootStyle>
      <InfoStyle>
      <ProfileImage files={files} setFiles={setFiles}/>
        <Box
          sx={{
            ml: { md: 3 },
            mt: { xs: 1, md: 0 },
            color: 'common.white',
            textAlign: { xs: 'center', md: 'left' },
          }}
        >
          <Typography variant="h4">{shipment && shipment[0] && shipment[0].Name}</Typography>
          <Typography sx={{ opacity: 0.72 }}>{shipment && shipment[0] && shipment[0].Description}</Typography>
        </Box>
      </InfoStyle>
      <Box
        component="img"
        sx={{ position: 'absolute', top: 0, left: 0, right: 0, bottom: 0 }}
      />
    </RootStyle>
  );
}
