import React, { useState } from 'react';
// @mui
import { alpha,styled, useTheme } from '@mui/material/styles';
import { Avatar, Box, Card, Grid, Typography } from '@mui/material';
import BusinessProfileImage from './BusinessProfileImage';
import { getBusiness, getBusinessId } from '../../services/authService';
import { useSelector } from 'react-redux';
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
}));

// ----------------------------------------------------------------------

export default function BusinessProfileCover() {
  const [files, setFiles] = useState([]);
  const myBusiness = getBusiness();
  const businessTypes = useSelector(state => state.businessTypes.businessTypes)
  const selectedBusiness = businessTypes.filter((business) => business._id === getBusiness().BusinessTypeID);
  console.log(selectedBusiness, businessTypes, getBusinessId())
  const business = (selectedBusiness && selectedBusiness.length && selectedBusiness[0])||{};
  const businessImg = business.Image && business.Image[0];
  return (
    <RootStyle>
      <InfoStyle>
        {myBusiness.ProfilePicture && myBusiness.ProfilePicture.length ? 
        <Avatar
        src={myBusiness.ProfilePicture[0]}
        sx={{
            mx: 'auto',
            borderWidth: 2,
            borderStyle: 'solid',
            borderColor: 'common.white',
            width: { xs: 100, md: 128 },
            height: { xs: 100, md: 128 },
            padding: 0,
        }}
    /> :
         businessImg ? (
          <Avatar
          src={businessImg}
          sx={{
              mx: 'auto',
              borderWidth: 2,
              borderStyle: 'solid',
              borderColor: 'common.white',
              width: { xs: 100, md: 128 },
              height: { xs: 100, md: 128 },
              padding: 2,
          }}
      />
        ):<BusinessProfileImage files={files} setFiles={setFiles}/>}
        <Grid container spacing={3}>
            <Grid item xs={12} md={12} lg={6} xl={6}>
              <Card
                sx={{
                  marginTop: 2,
                  marginLeft: 2,
                  height: 100,
                  background: 'transparent',
                  color: 'common.white',
                  padding: 3,
                  fontSize: '20px',
                }}
              >
              Income 
              </Card>
            </Grid>
            <Grid item xs={12} md={12} lg={6} xl={6}>
            <Card
                sx={{
                  marginTop: 2,
                  marginLeft: 2,
                  height: 100,
                  background: 'transparent',
                  color: 'common.white',
                  padding: 3,
                  fontSize: '20px',
                }}
              >
              Expanses 
              </Card>
            </Grid>
        </Grid>
      </InfoStyle>
      
    </RootStyle>
  );
}
