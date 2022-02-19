import * as React from 'react';
import PropTypes from 'prop-types';
// material
import { alpha, styled } from '@mui/material/styles';
import { Link as RouterLink, useHistory } from 'react-router-dom';
import { Box, Stack, AppBar, Toolbar, IconButton, Button } from '@mui/material';

import { PATH_AUTH } from '../../routes/path';
import Logo from '../../components/Logo/Logo';

// ----------------------------------------------------------------------

const DRAWER_WIDTH = 280;
const APPBAR_MOBILE = 64;
const APPBAR_DESKTOP = 92;

const RootStyle = styled(AppBar)(({ theme }) => ({
  boxShadow: 'none',
  backdropFilter: 'blur(6px)',
  WebkitBackdropFilter: 'blur(6px)', // Fix on Mobile
  backgroundColor: alpha(theme.palette.background.default, 0.72),
}));

const ToolbarStyle = styled(Toolbar)(({ theme }) => ({
  [theme.breakpoints.up('lg')]: {
    minHeight: APPBAR_DESKTOP,
  }
}));

// ----------------------------------------------------------------------

LandingNavbar.propTypes = {
  onOpenSidebar: PropTypes.func
};

export default function LandingNavbar() {
  const history = useHistory();
  return (
    <RootStyle>
      <ToolbarStyle>
        <Logo />
        <Box sx={{ flexGrow: 1 }} />
        <Stack direction="row" alignItems="center" spacing={{ xs: 0.5, sm: 1.5 }}>
          {/* <AccountPopover /> */}
          <Button onClick={()=>{history.push(PATH_AUTH.login)}}>Login</Button>
          <Button onClick={()=>{history.push(PATH_AUTH.login)}}>Signup</Button>
        </Stack>
      </ToolbarStyle>
    </RootStyle>
  );
}
