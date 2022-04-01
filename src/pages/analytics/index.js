import * as React from 'react';
// material
import { styled } from '@mui/material/styles';
// components
import Page from '../../components/Page';
// ----------------------------------------------------------------------

const RootStyle = styled(Page)(({ theme }) => ({
  minHeight: '100%',
  paddingTop: theme.spacing(12),
  [theme.breakpoints.up('md')]: {
    paddingTom: theme.spacing(10)
  }
  //paddingBottom: theme.spacing(10)
}));

// ----------------------------------------------------------------------

export default function Analytics() {
  
  return (
    <RootStyle title="Home">
      Hello
    </RootStyle>
  );
}
