import * as React from 'react';
import {
  Box, Button, Card, Container, Grid,
  Stack,
  TableContainer,
  Typography,
  Link, Paper, CardActionArea,
} from '@mui/material';
import Select from 'react-select';
import { createStyles, makeStyles } from '@mui/styles';
import AddCircleOutlinedIcon from '@mui/icons-material/AddCircleOutlined';
import RemoveCircleIcon from '@mui/icons-material//RemoveCircle';
import {
  useState,useCallback, useEffect,Fragment
} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import ArrowCircleUpIcon from '@mui/icons-material/ArrowCircleUp';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../components/Page';
import { SendEvent } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import AppLoader from '../components/Loader';
import DatePicker from 'react-datepicker';
import BusinessCenterOutlinedIcon from '@mui/icons-material/BusinessCenterOutlined';
import { ToastContainer, toast } from 'react-toastify';
import { getBusinessId, getUserId } from '../services/authService';

const useStyles = makeStyles((theme)=>createStyles({
  rightSection: {
      width: '96%',
  },
  inRightSection: {
      padding: '25px 70px 20px 32px',
      // [breakpoints.between('1024', '1400')]: {
      //     padding: '18px',
      // },
  },
  adminButton: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      color: '#fff',
  },
  addNewGroup: {
      fontSize: '18px',
      color: '#fff',
      border: '1px solid #428BCA',
      borderRadius: '2px',
      backgroundColor: '#428BCA',
      height: '48px',
      marginLeft: '15px',
      width: '205px',
      '&:hover': {
          backgroundColor: '#428BCA',
      },
  },
  inAdminButton: {
      display: 'flex',
  },
  addNewGroupBulk: {
    //   fontSize: '18px',
    //   color: '#428BCA',
    //   border: '1px solid #428BCA',
    //   borderRadius: '2px',
    //   //backgroundColor: '#fff',
    //   height: '48px',
      width: '165px',
      marginLeft: '70px',
      // [breakpoints.between('1024', '1400')]: {
      //     marginLeft: '0px',
      // },
  },
  bottomButtonExpert: {
      textAlign: 'right',
  },
  table: {
      width: '100%',
      borderSpacing: '0px 0px',
      border: '1px solid #7070704D',
      '& tr': {
          '&:nth-child(1)': {
              '& th': {
                  textAlign:'center',
                  borderBottom: '1px solid #7070704D',
                  fontSize: '15px',
                  color: '#1e1e1f',
                  fontFamily: 'Gilroy-Semibold',
                  padding: '5px 10px',
                  backgroundColor: '#b0b0b1',
                  '& span': {
                      padding: '0px',
                      color: '#000',
                      '& svg': {
                          verticalAlign: 'top',
                      },
                  },
              },
          },
      },
      '& td': {
          textAlign:'center',
          '&:nth-child(2)': {
              color: '#428BCA',
          },
          '&:last-child': {
              color: '#428BCA',
              cursor: 'pointer',
          },
      },
  },
  selectBoxStyle: {
      '& span': {
          display: 'none',
      },
  },
  selectBoxSectionTarget: {
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'flex-end',
  },
  selctAutTar: {
      width: '14%',
      marginRight: '7px',
      position: 'relative',
      // [breakpoints.between('1024', '1400')]: {
      //     width: '22%',
      // },
      '& div': {
          width: '100%',
          borderRadius: '2px',
      },
      '& input': {
          padding: '15px 14px',
          fontSize: '15px',
      },
  },
  actionList: {
      display: 'flex',
      justifyContent: 'flex-end',
      flexGrow: '1',
  },
  selctAutTarDate: {
        color:'#fff',
        marginRight: '7px',
        position: 'relative',
        [theme.breakpoints.between('1024', '1400')]: {
            width: '18%',
        },
        '& input': {
            padding: '10px 0px 10px 10px',
            border: '1px solid #cccccc',
            borderRadius: '4px',
        },
    },
  calendarIcon: {
      position: 'absolute',
      right: '40px',
      top: '14px',
      color: '#8F8FB3',
      fontSize: '14px',
      zIndex: '99',
  },
  datePicker: {
      height: '48px',
  },
  textField: {
      color: '#428BCA',
      border: '1px solid #428BCA',
      fontSize: '15px',
      fontFamily: 'Gilroy-Semibold',
      padding: '5px 10px',
      '& input' : {
          color: '#428BCA',
      },
  },
  saleRate:{
      //color:'#4289c7',
      border:'none',
      textAlign:'center',
      width: '100px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[300]
  },
  saleRateActive:{
      //color:'#fff',
      border:'none',
      textAlign:'center',
      width: '100px',
      height: '40px',
      display: 'inline-block',
      lineHeight: '26px',
      borderRadius: '4px',
      backgroundColor: theme.palette.grey[200]
  },
}));

const defaulState = {
  Name:'',
  Price: ''
}

const Class = () => {
  const classes = useStyles();
  const history = useHistory();
  const [selectedProduct, setselectedProduct] = useState(defaulState);
  const [productList, setProductList] = useState([]);
  const [updatedProduct, setUpdatedProduct] = useState([]);
  const [recieve, setRecieve] = useState(0);
  const [loading, setLoading] = useState(false);
  const [priceDate, setPriceDate] = useState(new Date());

  useEffect(()=>{
    //refreshProductList();
  },[priceDate])

  const refreshProductList=()=>{
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID,Date:priceDate}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    SendEvent(SocketEvent.RETRIVE_PRODUCT,request,handleRetriveProductEvent)
  }
  const handleRetriveProductEvent = useCallback((data) => {
    setLoading(false);
    console.log("handleRetriveProductsEvent",data,typeof(data.Payload))
    if(data && data.Payload && data.Payload.length > 0){
        setProductList(data.Payload);
        setselectedProduct(defaulState);
    }else{
        console.log("Unable to find customer, please try after some time");
    }
  }, []);

  const handleCreateProductEvent = useCallback((data) => {
    console.log("handleCreateCustomerEvent",data.Payload);
    setLoading(false);
    if(data && data.Payload && data.Payload._id){
        refreshProductList();
    }else{
        alert("Unable to create customer, please try after some time");
    }
  }, []);

  const handleInputChange = (e) => {
      setselectedProduct({
          ...selectedProduct,
          [e.target.name]: e.target.value,
      })
  }
  
  const handleAddProduct = (event) => {
    event.preventDefault();
    const UserID = getUserId();
    const BusinessID = getBusinessId();
      if(selectedProduct.Name && selectedProduct.Price){ 
          const request = {
            UserID,
            BusinessID,
            Name:selectedProduct.Name,
            Price: selectedProduct.Price
          }
          setLoading(true);
          SendEvent(SocketEvent.CREATE_PRODUCT,request,handleCreateProductEvent);
      }
  }

const handleChangeProduct = (e,index) => {
    e.preventDefault();
  let productObj = {...productList[index]};
  let productListTemp = [...productList];
  let updatedProductTemp = [...updatedProduct];
  productObj[e.target.name] = e.target.value;
  productListTemp[index] = productObj;
  setProductList(productListTemp);
  if(updatedProductTemp.indexOf(productObj._id) === -1){
      updatedProductTemp.push(productObj._id);
      setUpdatedProduct(updatedProductTemp)
  }
}



const handleUpdateProduct = (product) => {
    console.log("Product update started = ",product);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    let request = {
        UserID,BusinessID,
        Name:product.Name,
        Price:product.Price,
        ProductID:product._id,
        PriceDate:priceDate
    }
    if(product.Price > 0 && product.Name !== ""){
        setLoading(true);
        SendEvent(SocketEvent.UPDATE_PRODUCT,request,handleUpdateProductEvent);
    }
}

const handleUpdateProductPrice = (product) => {
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    let request = {
        UserID,BusinessID,
        Name:product.Name,
        Price:product.Price,
        ProductID:product._id,
        PriceDate:priceDate.toISOString().substring(0, 10)
    }
    console.log("Product update request = ",request);
    if(product.Price > 0 && product.Name !== ""){
        setLoading(true);
        SendEvent(SocketEvent.UPDATE_PRODUCT_PRICE,request,handleUpdateProductEvent);
    }
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
  
  const handleRemoveProduct = (index) => {
      const transArr = [...productList];
      transArr.splice(index, 1);
      setProductList(transArr);
  }
  
  const displayProduct = (product, index) => {
      return (
        <Grid item xs={12} sm={6} md={3}>
          <Link component={RouterLink} 
          //</Grid>to={''} 
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
                  p: 3,
                  borderRadius: 1,
                  color: 'primary.main',
                  bgcolor: 'background.neutral',
                  justifyContent:'center', 
                  display: 'flex',
                    alignItems: 'center',
                }}
              >
                    <BusinessCenterOutlinedIcon/>
              </CardActionArea>
  
              <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                {product.Name}
              </Typography>
              <Box sx={{display: 'flex', alignItems: 'center'}}>
                <Typography variant="subtitle2" sx={{ mt: 1, p: 1 }}>
                    <input name="Price" onChange={(e)=>handleChangeProduct(e,index)} className={classes.saleRateActive} placeholder="0" value={product.Price} />
                </Typography>
                {(updatedProduct.indexOf(product._id) > -1) ? (<Box
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

  return (
    <Page title="Class">
        <Fragment>
        <Container>
          { loading ? <AppLoader/> :null}
          <ToastContainer />
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Class
                </Typography>
                {/* <Box className={classes.actionList}>
                    <Button
                        variant="outlined"
                        //onClick={handleSummary}
                        startIcon={<AddOutlinedIcon />}
                    >
                        Add Product
                    </Button>
                </Box> */}
                <Box className={classes.actionList}>
                    <Box component="span" className={classes.selctAutTarDate}>
                        <DatePicker
                            placeholderText="Select Date"
                            className={classes.butonScrmenuSe}
                            selected={priceDate}
                            onChange={(date) => setPriceDate(date)}
                        />
                    </Box>
                </Box>
            </Stack>

            <Card>
                {/* <UserListToolbar
                    numSelected={selected.length}
                    filterName={filterName}
                    onFilterName={handleFilterByName}
                /> */}

                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Product Name</TableCell>
                                    <TableCell>Price</TableCell>
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow >
                                    <TableCell><Box component="span"><input name="Name" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Product Name" value={selectedProduct.Name}/></Box></TableCell>
                                    <TableCell><Box component="span"><input name="Price" onChange={handleInputChange} className={classes.saleRateActive} placeholder="0" value={selectedProduct.Price} /></Box></TableCell>
                                    <TableCell>
                                        <Box
                                            style={{cursor:'pointer'}}
                                            component="span"
                                            color="primary.dark"
                                            onClick={handleAddProduct}
                                        >
                                            <AddCircleOutlinedIcon />
                                        </Box>
                                    </TableCell>
                                </TableRow>
                            </TableBody>
                            { productList.length ? null : <TableRow><TableCell colSpan={8}>No Product to Display</TableCell></TableRow>}
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
            <Grid container spacing={3} sx={{ my: 10 }}>
                { productList.length ? productList.map((product, index) =>  displayProduct(product, index)): null }
            </Grid>
            <Box py={3} className={classes.bottomButtonExpert}>
                <Button
                    disabled = {!updatedProduct.length}
                    style={{cursor:'pointer'}}
                    className={classes.addNewGroupBulk}
                    variant="outlined"        
                >
                    Update
                </Button>
            </Box>
        </Container>
        </Fragment>
    </Page>

  )
}

export default Class;
