import * as React from 'react';
import {
  Box, Button, Card, Container, Grid,
  Stack,
  TableContainer,
  Typography
} from '@mui/material';
import Select from 'react-select';
import DatePicker from 'react-datepicker';
import { createStyles, makeStyles } from '@mui/styles';
import AddCircleOutlinedIcon from '@mui/icons-material/AddCircleOutlined';
import RemoveCircleIcon from '@mui/icons-material//RemoveCircle';
import  {
    Fragment,
    useCallback,
    useEffect,
  useState,
} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import CalendarIcon from '@mui/icons-material/CalendarToday';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import Page from '../../components/Page';
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../../services/authService';
import { PATH_DASHBOARD, PATH_PAGE } from '../../routes/path';
import {useDispatch, useSelector} from 'react-redux'
import { retriveProduct } from '../../store/product';
import { retriveCustomer } from '../../store/customer';
import { setSelectedTransaction } from '../../store/sale';

const useStyles = makeStyles((theme)=>createStyles({
    addNewGroupBulk: {
        width: '165px',
        [theme.breakpoints.between('1024', '1400')]: {
            marginLeft: '0px',
        },
    },
    bottomButtonExpert: {
        cursor: 'pointer',
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
        [theme.breakpoints.between('1024', '1400')]: {
            width: '22%',
        },
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
        border:'none',
        textAlign:'center',
        width: '85px',
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
        width: '85px',
        height: '40px',
        display: 'inline-block',
        lineHeight: '26px',
        borderRadius: '4px',
        backgroundColor: theme.palette.grey[200]
    },
  }));
  
  const customSeverityStyle = {
    option: (provided) => ({
        ...provided,
    }),
    control: (base) => ({
        ...base,
        width: '100%',
        fontSize: '15px',
    }),
    menu: (base) => ({
        ...base,
        borderRadius: 0,
    }),
    menuList: (base) => ({
        ...base,
        minWidth:200,
        fontSize: '18px',
        fontFamily: 'Gilroy-Semibold',
    }),
    container: (base) => ({
        ...base,
        width: '19% !important',
        marginRight: '10px',
        '&:last-child': {
            marginRight: '0px',
        },
    }),
    indicatorsContainer: (base) => ({
        ...base,
        color:'#fff',
        alignItems: 'baseline',
    }),
  
  };
  
  const customProductStyle = {
    option: (provided) => ({
        ...provided,
    }),
    control: (base) => ({
        ...base,
        width: '100%',
        fontSize: '15px',
    }),
    menu: (base) => ({
        ...base,
        borderRadius: 0,
    }),
    menuList: (base) => ({
        ...base,
        fontSize: '18px',
        fontFamily: 'Gilroy-Semibold',
    }),
    container: (base) => ({
        ...base,
        width: '100% !important',
        '&:last-child': {
            marginRight: '0px',
        },
    }),
    indicatorsContainer: (base) => ({
        ...base,
     //   color:'#fff',
        alignItems: 'baseline',
    }),
  
  };


const Sale = () => {
  const dispatch = useDispatch()
  const allProduct = useSelector(state => state.product.allProduct)
  const allCustomer = useSelector(state => state.customer.allCustomer)
  const [selectedCustomer, setSelectedCustomer] = useState({});
  const classes = useStyles();
  const history = useHistory();
  const selectedTransaction = useSelector(state => state.sale.selectedTransaction)
  const [transactionList, setTransactionList] = useState([]);
  const [recieve, setRecieve] = useState(0);
  const [loading, setLoading] = useState(false);
  const [saleDate, setSaleDate] = useState(new Date());
  const [err, setErr] = useState({
    Name: '', 
    Customer: '', 
  })
  console.log("selectedTransaction",selectedTransaction);


    const handleCreateSaleEvent = useCallback((data) => {
        setLoading(false);
        if(data && data.Payload && data.Payload.length){
            sessionStorage.setItem("sale",JSON.stringify(data));
            sessionStorage.setItem("selectedDate",global.saleDate);
            toast("Sales Record Created Successfully");
            history.push(PATH_DASHBOARD.sale.receipt);
        }
    }, []);
    const saveTransactionToServer = ()=>{
        sessionStorage.setItem("transactions", JSON.stringify(transactionList));
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {
            UserID,
            BusinessID,
            Transactions:transactionList
        }
        setLoading(true);
        SendEvent(SocketEvent.CREATE_SALE,request,handleCreateSaleEvent)
    }

    useEffect(()=>{
        global.selectedCustomer = undefined;
        sessionStorage.removeItem('sale');
        sessionStorage.removeItem('selectedCustomer');
        const UserID = getUserId();
        const BusinessID = getBusinessId();
    if(!allProduct.length){
        dispatch(retriveProduct({UserID,BusinessID}));
    }
    },[])

  /**
   * Function to get month name
   * @param {String} date
   */
  const getMonthName = (date) => {
      const monthNames = ['January', 'February', 'March', 'April', 'May', 'June',
          'July', 'August', 'September', 'October', 'November', 'December'];

      return monthNames[new Date(date).getMonth()];
  };

  const productOptions = Object.keys(allProduct).map((prd) => {
      console.log("prd",allProduct[prd].Name);
      return {
          label: allProduct[prd].Name,
          value: allProduct[prd]._id,
      }
  });
  
  const modeOptions = [{label:'CASH',value: 'CASH'},{label:'RTGS',value: 'RTGS'},{label:'IMPS',value: 'IMPS'},{label:'UPI',value: 'UPI'},{label:'CHEQUE',value: 'CHEQUE'},{label:'CARD',value: 'CARD'}]

  const handleChange = (option, {name}) => {
      const value = (option && option.value) || '';
      const product = allProduct.filter((prd)=>prd._id === value)
      if(product.length){
      dispatch(setSelectedTransaction({
        ...selectedTransaction,
        selectedProduct: product[0]
    }))
    }
  }
  
  const handleModeChange = (option, {name}) => {
      const value = (option && option.value) || '';
      if(value){
      dispatch(setSelectedTransaction({
        ...selectedTransaction,
        Mode: value, 
    }))
    }
  }

  const handleInputChange = (e) => {
      let updatedTransaction = {
        ...selectedTransaction,
        [e.target.name]: e.target.value,
    }
      dispatch(setSelectedTransaction(updatedTransaction))
      updateSaleTax(updatedTransaction)
  }

  const updateSaleTax=(transaction)=>{
      dispatch(
        setSelectedTransaction({
            ...transaction,
            Tax: (parseFloat(transaction.selectedProduct.Tax || 0) * parseFloat(transaction.Quantity)).toFixed(2),
        }) 
      )
  }
  
  const handleAddTransaction = (event) => {
    event.preventDefault();
      console.log("selectedTransaction",selectedTransaction);
      global.saleDate = saleDate;
        if(selectedTransaction.Quantity && selectedTransaction.Quantity>0){ 
            const transArr = [...transactionList];
            transArr.push({
                ProductName: selectedTransaction["selectedProduct"].Name,
                Vehicle: selectedTransaction.vehicleNo,
                Price: selectedTransaction["selectedProduct"].Price,
                Quantity: selectedTransaction.Quantity,
                Customer:selectedCustomer && selectedCustomer._id ? selectedCustomer : undefined,
                CostPrice: (parseFloat(selectedTransaction["selectedProduct"].CGST || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                IGST:(parseFloat(selectedTransaction["selectedProduct"].IGST || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                CGST:(parseFloat(selectedTransaction["selectedProduct"].CGST || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                SGST:(parseFloat(selectedTransaction["selectedProduct"].SGST || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                VAT:(parseFloat(selectedTransaction["selectedProduct"].VAT || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                CESS:(parseFloat(selectedTransaction["selectedProduct"].CESS || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                Tax: (parseFloat(selectedTransaction["selectedProduct"].Tax || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                MRP: selectedTransaction["selectedProduct"].MRP ? ((parseFloat(selectedTransaction["selectedProduct"].MRP || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2)) : 0,
                FinalPrice: (parseFloat(selectedTransaction["selectedProduct"].FinalPrice || 0) * parseFloat(selectedTransaction.Quantity)).toFixed(2),
                Mode: selectedTransaction.Mode || "CASH",
                Product:selectedTransaction.selectedProduct,
                SaleDate:saleDate
            });
            setTransactionList(transArr);
            dispatch(setSelectedTransaction());            
        }
  }
  
  const handleRemoveTransaction = (index) => {
      const transArr = [...transactionList];
      transArr.splice(index, 1);
      setTransactionList(transArr);
  }
  
  const getTotalAmount = () => {
      let amount = 0.00;
      transactionList.forEach(transactions => {
          amount += parseFloat(transactions.FinalPrice);
      });
      return amount.toFixed(2);
  }
  
  const handleRecieved = (e) =>   {
      const amt = (parseFloat(getTotalAmount()) - parseFloat(e.target.value)).toFixed(2);
      setRecieve(amt)
  };

  const getAmount = () => {
    let amt = (parseFloat(selectedTransaction["selectedProduct"].FinalPrice) * parseFloat(selectedTransaction.Quantity)).toFixed(2);
    if(!isNaN(amt)){
        return amt;
    } 
    return 0;
  }
  const customerOptions = Object.keys(allCustomer).map((prd) => {
    console.log("prd",prd);
    return {
        label: allCustomer[prd].Name,
        value: allCustomer[prd]._id,
    }
});
const handleChangeCustomer = (option, {name}) => {
    const value = (option && option.value) || '';
    const customer = allCustomer.filter((prd)=>prd._id === value)
    if(customer.length){
    setSelectedTransaction({
        ...selectedTransaction,
        selectedCustomer: customer[0]
    })
    setSelectedCustomer(customer[0])
    global.selectedCustomer = customer[0]
    }else{
        global.selectedCustomer = undefined;
        setSelectedCustomer({})
        setSelectedTransaction({
            ...selectedTransaction,
            selectedCustomer: undefined
        })
    }
  }
      
  const handleSummary = (e) => {saveTransactionToServer();};
      
  console.log("Selected Transaction ",selectedTransaction);

  return (
    <Page title="Sale">
        <Fragment>
          <Container>
          <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Sale
                </Typography>
                <Box className={classes.actionList}>
                <Select
                        options={customerOptions}
                        placeholder="Party Name"
                        name="partyName"
                        className={classes.selectBoxStyle}
                        styles={customSeverityStyle}
                        isClearable
                        onChange={handleChangeCustomer}
                    />
                </Box>
            </Stack>

            <Card>
            
                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Product</TableCell>
                                    <TableCell>Mode</TableCell>
                                    {getBusinessId() === "61ac91ed68b40d644a588608" &&
                                    (
                                        <TableCell>Vehicle No.</TableCell>
                                    )
                                    }
                                    
                                    <TableCell>Rate</TableCell>
                                    <TableCell>Qty</TableCell>
                                    <TableCell>Sale Tax</TableCell>
                                    <TableCell>Amount</TableCell>
                                    <TableCell>Action</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow >
                                    <TableCell>
                                        <Select
                                            options={productOptions}
                                            placeholder="Select Product"
                                            name="product"
                                            value={
                                                productOptions.filter((prd)=> 
                                                prd.label === selectedTransaction["selectedProduct"].Name
                                                )}
                                            className={classes.selectBoxStyle}
                                            styles={customProductStyle}
                                            onChange={handleChange}
                                        />
                                        <Box>{err.Name && err.Name}</Box>
                                    </TableCell>
                                    <TableCell>
                                        <Select
                                            options={modeOptions}
                                            placeholder="Select Mode"
                                            name="Mode"
                                            value={modeOptions.filter((prd)=> prd.value === selectedTransaction.Mode)}
                                            className={classes.selectBoxStyle}
                                            styles={customProductStyle}
                                            onChange={handleModeChange}
                                        />
                                    </TableCell>
                                    {/* {getBusinessId() === "61ac91ed68b40d644a588608" &&
                                            (
                                                <TableCell><Box component="span"><input name="vehicleNo" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Vehicle No" value={selectedTransaction.vehicleNo}/></Box></TableCell>
                                            )
                                        } */}
                                    
                                    
                                    
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={selectedTransaction["selectedProduct"].FinalPrice ? selectedTransaction["selectedProduct"].FinalPrice : '' }/></Box></TableCell>
                                    
                                    <TableCell><Box component="span"><input name="Quantity" onChange={handleInputChange} className={classes.saleRateActive} placeholder="0" value={selectedTransaction.Quantity ? selectedTransaction.Quantity : ''} /></Box></TableCell>
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={selectedTransaction.Tax ? selectedTransaction.Tax : ''} disabled/></Box></TableCell>
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={getAmount()} disabled/></Box></TableCell>
                                    <TableCell>
                                        <Box
                                            style={{cursor:'pointer'}}
                                            component="span"
                                            color="primary.dark"
                                            onClick={handleAddTransaction}
                                        >
                                            <AddCircleOutlinedIcon />
                                        </Box>
                                    </TableCell>
                                </TableRow>
                                { transactionList.length ? transactionList.map((transaction, index) => (
                                    <TableRow key={index}>
                                        <TableCell>{transaction.ProductName}</TableCell>
                                        <TableCell>{transaction.Mode}</TableCell>
                                        {getBusinessId() === "61ac91ed68b40d644a588608" &&
                                            (
                                                <TableCell><Box component="span"><input name="vehicleNo" className={classes.saleRateActive} placeholder="Vehicle No" value={transaction.vehicle} disabled/></Box></TableCell>
                                            )
                                        }
                                        
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.Price} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input name="Quantity" className={classes.saleRateActive} placeholder="0" value={transaction.Quantity} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.Tax} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.FinalPrice} disabled/></Box></TableCell>
                                        <TableCell>
                                        <Box
                                            style={{cursor:'pointer'}}
                                            component="span"
                                            color="error.dark"
                                            onClick={handleRemoveTransaction}
                                        >
                                            <RemoveCircleIcon />
                                        </Box>
                                    </TableCell>
                                    </TableRow>
                                )) : <TableRow><TableCell colSpan={8}>No Transaction to Display</TableCell></TableRow> }
                                <TableRow>
                                    <TableCell>Total</TableCell>
                                    <TableCell colSpan={5}></TableCell>
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={getTotalAmount()} disabled/></Box></TableCell>
                                    <TableCell colSpan={1}/>
                                </TableRow>
                                <TableRow>
                                    <TableCell>Recieved</TableCell>
                                    <TableCell colSpan={5}></TableCell>
                                    <TableCell><Box component="span"><input name="recieve" onChange={handleRecieved} className={classes.saleRateActive} placeholder="0" /></Box></TableCell>
                                    <TableCell />
                                </TableRow>
                                <TableRow>
                                    <TableCell>Return</TableCell>
                                    <TableCell colSpan={5}></TableCell>
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={recieve} disabled/></Box></TableCell>
                                    <TableCell />
                                </TableRow>
                            
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
            <Box py={3} className={classes.bottomButtonExpert}>
                    <Button
                        variant="outlined"
                        className={classes.addNewGroupBulk}
                        onClick={handleSummary}
                        disabled={!(transactionList && transactionList.length)}
                    >
                        Sale
                    </Button>
                </Box>
        </Container>
        </Fragment>
    </Page>

  )
}

export default Sale;
