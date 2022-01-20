import { Card, CardActionArea, Grid, Typography } from '@mui/material';
import React from 'react';
import { BarcodeGenrator } from '../barcode';

export default function MembershipCard ({user}) {
    return(
        <Grid item xs={12} sm={6} md={3}>
            <Card sx={{
                height: 250,
            }}>
                    
            <CardActionArea
                sx={{
                p: 3,
                borderRadius: 1,
                color: 'primary.main',
                bgcolor: 'background.neutral',
                justifyContent:'center', 
                display: 'flex',
                    alignItems: 'center',
                }}
            >
                <BarcodeGenrator barcode={user['Barcode Number']}/>
            </CardActionArea>
            <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                {user.Name}
            </Typography>
            {/* <BarcodeGenrator barcode={user['Barcode Number']}/> */}
            </Card>
        </Grid>
    )
}