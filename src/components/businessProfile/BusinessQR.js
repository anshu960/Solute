import * as React from 'react';

import { Box } from '@mui/material';
import QRCode from "react-qr-code";
import { getBusinessId } from '../../services/authService';

export default function BusinessQR() {
  
  return (
        <Box
          sx={{
            color: 'common.white',
            textAlign: 'center',
          }}
        >
          <QRCode value={getBusinessId()} />
        </Box>
  );
}
