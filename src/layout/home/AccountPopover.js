import * as React from 'react';
import { useEffect, useRef, useState } from 'react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
// material
import { alpha } from '@mui/material/styles';
import { Button, Box, Divider, MenuItem, Typography, IconButton } from '@mui/material';
// components
import MenuPopover from '../../components/MenuPopover';
//
import PersonOutlinedIcon from '@mui/icons-material/PersonOutlined';
import { logout } from '../../services/firebase/firebaseAuthService';
import { getBusiness } from '../../services/authService';
import { PATH_PAGE } from '../../routes/path';

// ----------------------------------------------------------------------

const MENU_OPTIONS = [
  {
    label: 'Profile',
    linkTo: PATH_PAGE.profile
  },
];

// ----------------------------------------------------------------------

export default function AccountPopover() {
  const history = useHistory();
  const anchorRef = useRef(null);
  const [open, setOpen] = useState(false);
  const [Business, setBusiness] = useState({});

  useEffect(()=>{
    const petro = getBusiness();
    setBusiness(petro);
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
            {/* {(Business && Business.Name) || ""} */}
            {"Solute"}
          </Typography>
          {/* <Typography variant="body2" sx={{ color: 'text.secondary' }} noWrap>
            {account.email}
          </Typography> */}
        </Box>

        <Divider sx={{ my: 1 }} />

        {MENU_OPTIONS.map((option) => (
          <MenuItem
            key={option.label}
            to={option.linkTo}
            component={RouterLink}
            onClick={handleClose}
            sx={{ typography: 'body2', py: 1, px: 2.5 }}
          >
            {/* <Box
              component={Icon}
              icon={option.icon}
              sx={{
                mr: 2,
                width: 24,
                height: 24
              }}
            /> */}

            {option.label}
          </MenuItem>
        ))}
        <Divider sx={{ my: 1 }} />
        <Box sx={{ p: 2, pt: 1.5 }}>
          <Button fullWidth color="inherit" variant="outlined" onClick={()=>{logout(history)}}>
            Logout
          </Button>
        </Box>
      </MenuPopover>
    </>
  );
}
