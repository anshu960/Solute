import React, { useEffect, useState } from 'react';
// @mui
import { alpha,styled, useTheme } from '@mui/material/styles';
import { Avatar, Box, Typography } from '@mui/material';
import ProfileImage from './ProductProfileImage';
import { useDispatch, useSelector } from 'react-redux';
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

export default function ProductProfileCover() {
  const [files, setFiles] = useState([]);
  const dispatch = useDispatch();
  const product = useSelector(state => state.product.selectedProduct);
  
  return (
    <RootStyle>
      <InfoStyle>
      {
        product.Image && product.Image.length 
        ?(product.Image.map((img)=>
          <Box mt={2}>
              <Avatar
              src={img}
              sx={{
                  mx: 'auto',
                  borderWidth: 2,
                  borderStyle: 'solid',
                  borderColor: 'common.white',
                  width: { xs: 80, md: 128 },
                  height: { xs: 80, md: 128 },
              }}
          /></Box>)
        )
        : null
      }
      <ProfileImage files={files} setFiles={setFiles}/>
        <Box
          sx={{
            ml: { md: 3 },
            mt: { xs: 1, md: 0 },
            color: 'common.white',
            textAlign: { xs: 'center', md: 'left' },
          }}
        >
        </Box>
      </InfoStyle>
      <Box
        component="img"
        //alt="profile cover"
        //src={cover}
        sx={{ position: 'absolute', top: 0, left: 0, right: 0, bottom: 0 }}
      />
    </RootStyle>
  );
}
