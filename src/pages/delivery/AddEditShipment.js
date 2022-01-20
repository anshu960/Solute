import * as React from 'react';
import {
  Box, Container,
  Stack,
  Typography,
} from '@mui/material';

import DatePicker from 'react-datepicker';
import  {
  useState,
} from 'react';
import Page from '../../components/Page';
import { ShipmentCreate } from '../../components/shipment';
import { useStyles } from './Style';

const AddEditShipment = () => {
  const classes = useStyles();
  const [saleDate, setSaleDate] = useState(new Date());
      
    return (
    <Page title="HSN">
        <React.Fragment>
          <Container>
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Add Shipment
                </Typography>
                <Box className={classes.actionList}>
                    <Box component="span" className={classes.selctAutTarDate}>
                        <DatePicker
                            placeholderText="Select Date"
                            className={classes.butonScrmenuSe}
                            selected={saleDate}
                            onChange={(date) => setSaleDate(date)}
                        />
                    </Box>
                </Box> 
            </Stack>
            <Box>
                <ShipmentCreate />
            </Box>
        </Container>
        </React.Fragment>
    </Page>

  )
}

export default AddEditShipment;
