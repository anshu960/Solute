import PropTypes from 'prop-types';
import React, { useState } from 'react';
import { PDFDownloadLink, PDFViewer } from '@react-pdf/renderer';
// material
import { Box, Tooltip, IconButton, DialogActions, Stack, Button } from '@mui/material';
import CloseOutlinedIcon from '@mui/icons-material/CloseOutlined';
//
import InvoicePDF from './InvoicePDF';
import { DialogAnimate } from '../animate';

// ----------------------------------------------------------------------

InvoiceToolbar.propTypes = {
  invoice: PropTypes.object.isRequired
};

export default function InvoiceToolbar() {
  const [openPDF, setOpenPDF] = useState(false);

  const handleOpenPreview = () => {
    setOpenPDF(true);
  };

  const handleClosePreview = () => {
    setOpenPDF(false);
  };

  return (
    <>
      <Stack mb={5} direction="row" justifyContent="flex-end" spacing={1.5}>
        <Button color="error" size="small" variant="contained">
          Share
        </Button>

        <Button
          color="info"
          size="small"
          variant="contained"
          onClick={handleOpenPreview}
          sx={{ mx: 1 }}
        >
          Preview
        </Button>

        <PDFDownloadLink
          document={<InvoicePDF />}
          fileName={`INVOICE-12345`}
          style={{ textDecoration: 'none' }}
        >
          {({ loading }) => (
            <Button
              size="small"
              loading={loading}
              variant="contained"
              loadingPosition="end"
            >
              Download
            </Button>
          )}
        </PDFDownloadLink>
      </Stack>

      <DialogAnimate fullScreen open={openPDF}>
        <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
          <DialogActions
            sx={{
              zIndex: 9,
              padding: '12px !important',
              boxShadow: (theme) => theme.customShadows.z8
            }}
          >
            <Tooltip title="Close">
              <IconButton color="inherit" onClick={handleClosePreview}>
                <CloseOutlinedIcon />
              </IconButton>
            </Tooltip>
          </DialogActions>
          <Box sx={{ flexGrow: 1, height: '100%', overflow: 'hidden' }}>
            <PDFViewer width="100%" height="100%" style={{ border: 'none' }}>
              <InvoicePDF />
            </PDFViewer>
          </Box>
        </Box>
      </DialogAnimate>
    </>
  );
}
