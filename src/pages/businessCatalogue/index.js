import React from 'react';
import {
  Alert,
    Container,
    Stack,
    Typography
  } from '@mui/material';
  import {
    Fragment,
  } from 'react';
import Page from '../../components/Page';
import { useStyles } from '../customer/Style';
import { ProductList } from '../../components/businessCatalogue';
import { getParameterByName } from '../../common/Utils';

const BusinessCatalogue = () => {
    const classes = useStyles();
    const {id} = getParameterByName("id");
        
    return (
        <Page title="Catalogue">
            <Fragment>
            <Container>
              {/* { loading ? <AppLoader/> :null} */}
                <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                    <Typography variant="h4" gutterBottom>
                    Catalogue
                    </Typography>
                </Stack>
                <ProductList />
                {/* <Alert variant="outlined" severity="info">
                    No Catalogue found for the business
                </Alert> */}
            </Container>
             </Fragment>
        </Page>
      )
}

export default BusinessCatalogue;