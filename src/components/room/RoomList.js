import { Alert, Box, Grid } from '@mui/material';
import React from 'react';
import RoomItem from './RoomItem';

export default function RoomList ({productList, selectedDate}) {
    return(<Grid container spacing={3} sx={{ my: 10 }}>
            { productList.length 
            ? productList.map((product) =>  <RoomItem product={product} selectedDate={selectedDate} />)
            : <Grid item xs={12}>
                <Box>
                    <Alert variant="outlined" severity="info">
                        No Product to Display Please Add Product
                    </Alert>
                </Box>
            </Grid> }
        </Grid>)
}