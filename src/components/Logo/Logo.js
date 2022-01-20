import React from 'react';
// material
import { Box } from '@mui/material';
//import logoSingle from '../../Common/images/logo_single.svg'
import ImageConfig from "../../config/image_config";

// ----------------------------------------------------------------------

const Logo = ({ ...other }) => {
  return (
    <Box component="img" alt="logo" src={ImageConfig.Logo} height={40} {...other} />
  )
}

export default Logo
