import React from 'react';
// material
import { Dialog, DialogTitle } from '@mui/material';
// ----------------------------------------------------------------------

export default function ScrollDialog({title, body, handleClose, open, scroll, dialogWidth }) {
  return (
    <div>
      <Dialog 
      fullWidth
      maxWidth={dialogWidth}
      open={open} onClose={handleClose} scroll={scroll}>
        <DialogTitle sx={{ pb: 2 }}>{title || 'Dialog'}</DialogTitle>
        {body}
      </Dialog>
    </div>
  );
}