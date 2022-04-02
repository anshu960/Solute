import * as React from 'react';
import PropTypes from 'prop-types';
// material
import { alpha, styled } from '@mui/material/styles';
import { Box, Stack, BottomNavigation, Toolbar, Typography } from '@mui/material';
import { Link } from "react-router-dom";
import { ScrollDialog } from '../../dialog';
import TnC from './TncView';
import PrivacyPolicy from './PrivacyPolicyView';

// ----------------------------------------------------------------------

const RootStyle = styled(BottomNavigation)(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  boxShadow: 'none',
  backdropFilter: 'blur(6px)',
  WebkitBackdropFilter: 'blur(6px)', // Fix on Mobile
  //backgroundColor: theme.palette.common.black,
  height: 'auto',
  // [theme.breakpoints.up('lg')]: {
  //   width: `calc(100% - ${DRAWER_WIDTH + 1}px)`
  // }
}));

const ToolbarStyle = styled(Toolbar)(({ theme }) => ({
//   minHeight: APPBAR_MOBILE,
//   [theme.breakpoints.up('lg')]: {
//     minHeight: APPBAR_DESKTOP,
//     padding: theme.spacing(0, 5)
//   }
}));

const LinkStyle = styled(Link)(({ theme }) => ({
  textDecoration: 'none',
  //color: theme.palette.common.white,
  '&:hover': {
    opacity: 0.5,
  },
}));

// ----------------------------------------------------------------------

export default function Appfooter() {
  const [localState, setLocalState] = React.useState({
    open: false,
    title: '',
    content: '',
  })

  const handleClose = () => {
    setLocalState({
      ...localState,
      open: false
    })
  }

  const handleTerms = () => {
    setLocalState({
      ...localState,
      open: true,
      title: "Solute's Terms of Use",
      content: <TnC handleClose={handleClose}/>
    })
  }
  
  const handlePolicy = () => {
    setLocalState({
      ...localState,
      open: true,
      title: "Solute's Privacy Policy",
      content: <PrivacyPolicy handleClose={handleClose}/>
    })
  }

  return (
    <RootStyle>
        <ScrollDialog body={localState.content} handleClose={handleClose} scroll={'paper'} title={localState.title} open={localState.open} dialogWidth="xl"/>
        <ToolbarStyle>
            <Stack direction="row" alignItems="center" spacing={{ xs: 0.5, sm: 1.5 }}>
                <Typography component="h1" sx={{ mb: 2, margin:'0px', 
                //color: (theme) => theme.palette.common.white 
                }}>
                © Solute
                </Typography>
            </Stack>
            <Box sx={{ flexGrow: 1 }} />
            <Stack direction="row" alignItems="center" spacing={{ xs: 0.5, sm: 1.5 }}>
                <LinkStyle onClick={handleTerms}>
                  Terms of Use |
                </LinkStyle>
                <LinkStyle onClick={handlePolicy}>
                  Privacy Policy |
                </LinkStyle>
                <LinkStyle to="/">
                  Customer Care |
                </LinkStyle>
                <LinkStyle to="/">
                  FAQ
                </LinkStyle>
            </Stack>
        </ToolbarStyle>
    </RootStyle>
    );
  }