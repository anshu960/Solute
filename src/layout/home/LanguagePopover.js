import React, { useRef, useState } from 'react'
// material
import { Box, MenuItem, ListItemIcon, ListItemText } from '@mui/material'
// hooks
//import useLocales from '../../Hooks/useLocales'
// components
import MenuPopover from '../../components/MenuPopover';
import LanguageIcon from '@mui/icons-material/Language';
import useLocales from '../../Hooks/useLocales';

// ----------------------------------------------------------------------

export default function LanguagePopover() {
  const anchorRef = useRef(null)
  const [open, setOpen] = useState(false)
  const { allLang, currentLang, onChangeLang } = useLocales()

  return (
    <>
      {/* <Box
        ref={anchorRef}
        onClick={() => setOpen(true)}
        sx={{
          padding: 0,
          width: 44,
          height: 44,
          ...(open && { backgroundColor: 'action.selected' })
        }}
        color="default"
      > */}
        {/* @ts-ignore */}
        <LanguageIcon 
        color={open ? 'primary' : 'info'}
        ref={anchorRef}
        onClick={() => setOpen(true)}
        />
        {/* <img src={LanguageIcon} alt={currentLang.label} /> */}
      {/* </Box> */}

      <MenuPopover
        open={open}
        onClose={() => setOpen(false)}
        anchorEl={anchorRef.current}
      >
        <Box sx={{ py: 1 }}>
          {allLang.map(option => (
            <MenuItem
              key={option.value}
              selected={option.value === currentLang.value}
              onClick={() => {
                onChangeLang(option.value)
                setOpen(false)
              }}
              sx={{ py: 1, px: 2.5 }}
            >
              <ListItemIcon>
                {/* <Box component="img" alt={option.label} src={option.icon} /> */}
              </ListItemIcon>
              <ListItemText primaryTypographyProps={{ variant: 'body2' }}>
                {option.label}
              </ListItemText>
            </MenuItem>
          ))}
        </Box>
      </MenuPopover>
    </>
  )
}
