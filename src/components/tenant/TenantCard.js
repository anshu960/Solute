import * as React from 'react';
import { useState } from 'react';

import PropTypes from 'prop-types';
import { Link as RouterLink } from 'react-router-dom';
import { alpha, styled } from '@mui/material/styles';
import { Box, Link, Card, Grid, Avatar, Typography, CardContent, IconButton, Tooltip, Divider, CardMedia } from '@mui/material';
import SvgIconStyle from '../SvgIconStyle';
import AccountBillingPaymentMethod from './AccountBillingPaymentMethod';
import CustomerDetials from './CustomerDetails';
import ImageConfig from "../../config/image_config";
const CoverImgStyle = styled('img')({
  top: 0,
  width: '100%',
  height: '100%',
  objectFit: 'cover',
  position: 'absolute'
});

const CardMediaStyle = styled('div')(({ theme }) => ({
    display: 'flex',
    position: 'relative',
    justifyContent: 'center',
    paddingTop: 'calc(100% * 9 / 24)',
    '&:before': {
      top: 0,
      zIndex: 9,
      content: "''",
      width: '100%',
      height: '100%',
      position: 'absolute',
      backdropFilter: 'blur(3px)',
      WebkitBackdropFilter: 'blur(3px)', // Fix on Mobile
      borderTopLeftRadius: theme.shape.borderRadiusMd,
      borderTopRightRadius: theme.shape.borderRadiusMd,
      backgroundColor: alpha(theme.palette.primary.darker, 0.72)
    }
  }));

  TenantCard.propTypes = {
  post: PropTypes.object.isRequired,
  index: PropTypes.number
};

export default function TenantCard({ customer, index,onRefresh,setLoading, toast }) {
    const [open, setOpen] = useState({
        customerInformation: false,
        payment: false,
    });
    const { Name, MobileNumber, WhatsApp, EmailID, Address } = customer;

  return (
      <>
      <Grid item xs={12} sm={6} md={4}>
        
      <Card>
      <CardMediaStyle>
      <SvgIconStyle
          color="paper"
          src={ImageConfig.Logo}
          sx={{
            width: 144,
            height: 62,
            zIndex: 10,
            bottom: -26,
            position: 'absolute'
          }}
        />
        <Avatar
          alt={Name}
          sx={{
            width: 64,
            height: 64,
            zIndex: 11,
            position: 'absolute',
            left:"40%",
            transform: 'translateY(-50%)'
          }}
        />
      </CardMediaStyle>
    <Typography variant="subtitle1" align="center" sx={{ mt: 6 }}>
        {Name}
      </Typography>
      <Box sx={{ textAlign: 'center', mt: 2, mb: 2.5 }}>
      </Box>
      <Divider />
      <CustomerDetials
            customer={customer}
            isOpen={open.customerInformation}
            onOpen={()=>setOpen({...open, customerInformation: !open.customerInformation})}
            onCancel={()=>setOpen({...open, customerInformation: false})}
            onRefresh={onRefresh}
            setLoading={setLoading} toast={toast}
        />
      <Divider />
        <AccountBillingPaymentMethod
            customer={customer}
            isOpen={open.payment}
            onOpen={()=>setOpen({...open, payment: !open.payment})}
            onCancel={()=>setOpen({...open, payment: false})}
            onRefresh={onRefresh}
            setLoading={setLoading} toast={toast}
        />
        </Card>
    </Grid>
    </>
  );
}
