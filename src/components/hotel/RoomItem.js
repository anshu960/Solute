import {Avatar, Box, CardActionArea, Grid, Link, Paper, Typography } from '@mui/material';
import React, { useCallback, useState } from 'react';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import BusinessCenterOutlinedIcon from '@mui/icons-material/BusinessCenterOutlined';
import { InputStyle } from './Style';
import { getBusinessId, getUserId } from '../../services/authService';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import { toast } from 'react-toastify';
import { PATH_DASHBOARD } from '../../routes/path';
import { setSelectedProduct } from '../../store/product';
import { useDispatch } from 'react-redux';

export default function RoomItem ({product, selectedDate}) {
    const dispatch = useDispatch()
    const history = useHistory();
    const [loading, setLoading] = useState(false);
    const [localState, setLocalState] = useState({
        price: '',
    });
    const handleChangeProduct = (e) => {
        e.preventDefault();
        setLocalState({
            ...localState,
            [e.target.name]: e.target.value,
        });
    }

    const handleUpdateProductEvent = useCallback((data) => {
        setLoading(false);
        console.log("handleUpdateProductEvent",data)
        if(data && data.status){
            toast("Product Updated in server");
        }else{
            console.log("Unable to update product, please try after some time");
            toast("Unable to update Product in server");
        }
      }, []);

    const handleUpdateProductPrice = (product) => {
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        let request = {
            UserID,BusinessID,
            Name:product.Name,
            Price:localState.Price,
            ProductID:product._id,
            PriceDate:selectedDate.toISOString().substring(0, 10)
        }
        console.log("Product update request = ",request);
        if(product.Price > 0 && product.Name !== ""){
            setLoading(true);
            SendEvent(SocketEvent.UPDATE_PRODUCT_PRICE,request,handleUpdateProductEvent);
        }
    }

    return(
        <Grid item xs={12} sm={6} md={3}>
            <Link component={RouterLink}
            onClick={()=>{
              dispatch(setSelectedProduct(product));
              history.push({ pathname: PATH_DASHBOARD.product.productProfile, search: `?id=${product._id}` })
            }}
            underline="none">
              <Paper
                sx={{
                  p: 1,
                  boxShadow: (theme) => theme.customShadows.z8,
                  '&:hover img': { transform: 'scale(1.1)' }
                }}
              >
                <CardActionArea
                  sx={{
                    borderRadius: 1,
                    color: 'primary.main',
                    bgcolor: 'background.neutral',
                    justifyContent:'center', 
                    display: 'flex',
                      alignItems: 'center',
                  }}
                >
                  {product.Image && product.Image.length 
                    ? product.Image.map((url, index)=>
                      <Avatar
                        sx={{
                          marginLeft: index ? '-30px': '0px',
                          height: '75px', width: '75px'
                        }}
                      >
                        <img
                          style={{height: '100px', width: '100px'}}
                          src={url}
                          alt={product.Name}
                        />
                      </Avatar>)
                    :<BusinessCenterOutlinedIcon sx={{height: '75px', width: '75px'}}/>
                  }
                    
                </CardActionArea>
                <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                  {product.Name}
                </Typography>
                <Box sx={{display: 'flex', alignItems: 'center'}}>
                  <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                      <InputStyle name="Price" onChange={(e)=>handleChangeProduct(e)} placeholder="0" value={localState.price ? localState.price : product.FinalPrice} />
                  </Typography>
                  {localState.price ? (<Box
                          style={{cursor:'pointer'}}
                          component="span"
                          color="primary.dark"
                          onClick={()=>{handleUpdateProductPrice(product)}}
                      >
                          <ArrowCircleUpIcon />
                      </Box>):null}
                </Box>
              </Paper>
            </Link>
        </Grid>
    )
}