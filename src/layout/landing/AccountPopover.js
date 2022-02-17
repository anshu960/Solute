import * as React from 'react';
import { useEffect, useRef, useState } from 'react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
// material
import { alpha } from '@mui/material/styles';
import { Button, Box, Divider, Typography, IconButton } from '@mui/material';
// components
import MenuPopover from '../../components/MenuPopover';
//
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import { PATH_AUTH } from '../../routes/path';

// ----------------------------------------------------------------------

export default function AccountPopover() {
  const history = useHistory();
  const anchorRef = useRef(null);
  const [open, setOpen] = useState(false);

  useEffect(()=>{
    
  },[])
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <IconButton
        ref={anchorRef}
        onClick={handleOpen}
        sx={{
          padding: 0,
          width: 44,
          height: 44,
          ...(open && {
            '&:before': {
              zIndex: 1,
              content: "''",
              width: '100%',
              height: '100%',
              borderRadius: '50%',
              position: 'absolute',
              bgcolor: (theme) => alpha(theme.palette.grey[900], 0.72)
            }
          })
        }}
      >
        <PersonOutlinedIcon />
      </IconButton>

      <MenuPopover
        open={open}
        onClose={handleClose}
        anchorEl={anchorRef.current}
        sx={{ width: 220 }}
      >
        <Box sx={{ my: 1.5, px: 2.5 }}>
          <Typography variant="subtitle1" noWrap>
            {('Solute')}
          </Typography>
        </Box>
        <Divider sx={{ my: 1 }} />
        <Box sx={{ p: 2, pt: 1.5 }}>
          <Button fullWidth color="inherit" variant="outlined" onClick={()=>{history.push(PATH_AUTH.login)}}>
            Login
          </Button>
        </Box>
      </MenuPopover>
    </>
  );
}
