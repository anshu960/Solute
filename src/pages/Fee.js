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
import Page from '../components/Page';
import { SendEvent } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import AppLoader from '../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { getBusinessId, getUserId, setBusiness, setBusinessId } from '../services/authService';

const useStyles = makeStyles((theme)=>createStyles({
    rightSection: {
        width: '96%',
    },
    inRightSection: {
        padding: '25px 70px 20px 32px',
        [theme.breakpoints.between('1024', '1400')]: {
            padding: '18px',
        },
    },
    adminButton: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        color: '#fff',
    },
    addNewGroup: {
        //fontSize: '18px',
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
        //fontSize: '18px',
        //color: '#428BCA',
        //border: '1px solid #428BCA',
        //borderRadius: '2px',
        //backgroundColor: '#fff',
        //height: '48px',
        width: '165px',
        //marginLeft: '70px',
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
        //color:'#4289c7',
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
        //height: '48px',
        //overflowY: 'scroll',
        //color:'#fff',
        //backgroundColor: '#D5DEE112',
        //padding: '4px 12px',
        //borderRadius: '2px',
        fontSize: '15px',
        // [breakpoints.between('1024', '1400')]: {
        //     fontSize: '12px',
        //     padding: '4px 4px',
        // },
        '& div':{
            //color: '#fff'
        }
  
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
        // [breakpoints.between('1024', '1400')]: {
        //     marginRight: '5px',
        // },
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
        //height: '48px',
        //overflowY: 'scroll',
        //color:'#fff',
        //backgroundColor: '#D5DEE112',
        //padding: '4px 12px',
        //borderRadius: '2px',
        fontSize: '15px',
        // [breakpoints.between('1024', '1400')]: {
        //     fontSize: '12px',
        //     padding: '4px 4px',
        // },
        '& div':{
          //  color: '#fff'
        }
  
    }),
    menu: (base) => ({
        ...base,
    //    color: '#000',
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
    //    marginRight: '10px',
        // [breakpoints.between('1024', '1400')]: {
        //     marginRight: '5px',
        // },
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

const defaulState = {
  qty: '',
  vehicleNo: '',
  mode: 'credit',
  selectedProduct: {
      _id:"",
      Name: "",
      Price: 0,
      tax: 0,
  },
  selectedCustomer: {
    _id:"",
    Name: ""
},
}

const Fee = () => {
  const classes = useStyles();
  const history = useHistory();
  const [selectedTransaction, setSelectedTransaction] = useState(defaulState);
  const [selectedCustomer, setSelectedCustomer] = useState({});
  const [transactionList, setTransactionList] = useState([]);
  const [recieve, setRecieve] = useState(0);
  const [isCustomerListSynced, setCustomerListSynced] = useState(false);
  const [isProductListSynced, setProductListSynced] = useState(false);
  const [isBusinessValidated, setBusinessValidated] = useState(false);
  const [customerList, setCustomerList] = useState([]);
  const [productList, setProductList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [saleDate, setSaleDate] = useState(new Date());
  const [err, setErr] = useState({
    Name: '', 
    Customer: '', 
  })

  const syncRequiredData=()=>{
    const BusinessID = getBusinessId();
    if(BusinessID){
        refreshProductList();
        refreshCustomerList();
        console.log("Sales , Business bunk exist, trying to get customer and products")
    }else{
        console.log("Sales , Business bunk doesn't exist, trying to get Business bunk before trying to get customer and products")
        refreshBusiness();
    }
  }
  const checkAndRemoveInitialSyncLoader=()=>{
    if(isBusinessValidated && isCustomerListSynced && isProductListSynced){
        setLoading(false);
    }
  }
  const refreshProductList=()=>{
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    console.log("request",request)
    // JoinRoom(SocketEvent.JOIN_ROOM,{ROOM_ID:UserID})
    SendEvent(SocketEvent.RETRIVE_PRODUCT,request,handleRetriveProductEvent)
  }
  const handleRetriveProductEvent = useCallback((data) => {
    setProductListSynced(true);
    checkAndRemoveInitialSyncLoader();
    console.log("handleRetriveProductsEvent",data)
    if(data && data.Payload && data.Payload.length > 0){
        setProductList(data.Payload);
    }else{
        console.log("Unable to find products, please try after some time");
    }
  }, []);

  const refreshBusiness=()=>{
    const UserID = getUserId();
    const request = {UserID}
    console.log("request",request)
    SendEvent(SocketEvent.RETRIVE_BUSINESS,request,handleRetriveBusinessEvent)
  }
  const handleRetriveBusinessEvent = useCallback((data) => {
    console.log("handleRetriveBusinessEvent in Sale",data)
    if(data && data.Payload && data.Payload._id){
        setBusinessId(data.Payload._id);
        setBusiness(data.Payload);
        refreshCustomerList();   
        refreshProductList();
    }else{
        console.log("Unable to find Business bunk in Sales, please try after some time");
    }
  }, []);

  const refreshCustomerList=()=>{
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    const request = {UserID,BusinessID}
    SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,handleRetriveCustomerEvent)
  }
  const handleRetriveCustomerEvent = useCallback((data) => {
    setCustomerListSynced(true);
    checkAndRemoveInitialSyncLoader();
    if(data && data.Payload && data.Payload.length > 0){
          console.log("handleRetriveCustomerEvent",data)
          setCustomerList(data.Payload);
    }else{
        console.log("Unable to find customer, please try after some time");
    }
  }, []);

    const handleCreateSaleEvent = useCallback((data) => {
        setLoading(false);
        if(data && data.Payload && data.Payload.length && global.selectedCustomer){
            sessionStorage.setItem("sale",JSON.stringify(data));
            sessionStorage.setItem("selectedCustomer",JSON.stringify(global.selectedCustomer));
            sessionStorage.setItem("selectedDate",global.saleDate);
            toast("Sales Record Created Successfully");
            history.push('/dashboard/invoice')
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
        SendEvent(SocketEvent.CREATE_BUSINESS_SALE,request,handleCreateSaleEvent)
    }

    useEffect(()=>{
        sessionStorage.removeItem('sale');
        sessionStorage.removeItem('selectedCustomer');
        syncRequiredData();
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

  const productOptions = Object.keys(productList).map((prd) => {
      console.log("prd",productList[prd].Name);
      return {
          label: productList[prd].Name,
          value: productList[prd]._id,
      }
  });

  const customerOptions = Object.keys(customerList).map((prd) => {
    console.log("prd",prd);
    return {
        label: customerList[prd].Name,
        value: customerList[prd]._id,
    }
});
  
  const modeOptions = [{label: 'Cash',value: 'CASH'},{label: 'Credit',value: 'CREDIT'}]

  const handleChange = (option, {name}) => {
      const value = (option && option.value) || '';
      const product = productList.filter((prd)=>prd._id === value)
      if(product.length){
      setSelectedTransaction({
          ...selectedTransaction,
          selectedProduct: product[0]
      })
      
    }
  }

  const handleChangeCustomer = (option, {name}) => {
    const value = (option && option.value) || '';
    const customer = customerList.filter((prd)=>prd._id === value)
    if(customer.length){
    setSelectedTransaction({
        ...selectedTransaction,
        selectedCustomer: customer[0]
    })
    setSelectedCustomer(customer[0])
    global.selectedCustomer = customer[0]
    }
  }
  
  const handleModeChange = (option, {name}) => {
      const value = (option && option.value) || '';
      if(value){
      setSelectedTransaction({
          ...selectedTransaction,
          mode: value, 
      })}
  }

  const handleInputChange = (e) => {
      setSelectedTransaction({
          ...selectedTransaction,
          [e.target.name]: e.target.value,
      })
  }
  
  const handleAddTransaction = (event) => {
    event.preventDefault();
      console.log("selectedTransaction",selectedTransaction);
      global.saleDate = saleDate;
      if(selectedCustomer._id){
        if(selectedTransaction.qty && selectedTransaction.qty>0){ 
            const transArr = [...transactionList];
            transArr.push({
                productName: selectedTransaction["selectedProduct"].Name,
                vehicle: selectedTransaction.vehicleNo,
                rate: selectedTransaction["selectedProduct"].Price,
                qty: selectedTransaction.qty,
                tax: selectedTransaction["selectedProduct"].tax,
                amt: (parseFloat(selectedTransaction["selectedProduct"].Price) * parseFloat(selectedTransaction.qty)).toFixed(2),
                mode: selectedTransaction.mode,
                Product:selectedTransaction.selectedProduct,
                Customer:selectedCustomer,
                SaleDate:saleDate
            });
            setTransactionList(transArr);
            setSelectedTransaction(defaulState);
        }
      }else{
        alert("Please select customer")
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
          amount += parseFloat(transactions.amt);
      });
      return amount.toFixed(2);
  }
  
  const handleRecieved = (e) =>   {
      const amt = (parseFloat(getTotalAmount()) - parseFloat(e.target.value)).toFixed(2);
      setRecieve(amt)
  };

  const getAmount = () => {
    let amt = (parseFloat(selectedTransaction["selectedProduct"].Price) * parseFloat(selectedTransaction.qty)).toFixed(2);
    if(!isNaN(amt)){
        return amt;
    } 
    return 0;
  }

  
      
  const handleSummary = (e) => {saveTransactionToServer();};
      
  console.log("Selected Transaction ",selectedTransaction);

  return (
    <Page title="Fee">
        <Fragment>
          <Container>
          <ToastContainer />
          { loading ? <AppLoader/> :null}
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                <Typography variant="h4" gutterBottom>
                    Fee
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

            <Card>
                <Box>
                    <TableContainer sx={{ minWidth: 800 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Product</TableCell>
                                    <TableCell>Mode</TableCell>
                                    <TableCell>Vehicle No.</TableCell>
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
                                            name="mode"
                                            value={modeOptions.filter((prd)=> prd.value === selectedTransaction.mode)}
                                            className={classes.selectBoxStyle}
                                            styles={customProductStyle}
                                            onChange={handleModeChange}
                                        />
                                    </TableCell>
                                    <TableCell><Box component="span"><input name="vehicleNo" onChange={handleInputChange} className={classes.saleRateActive} placeholder="Vehicle No" value={selectedTransaction.vehicleNo}/></Box></TableCell>
                                    {(selectedTransaction["selectedProduct"].Name === "MS" || selectedTransaction["selectedProduct"].Name === "HSD") 
                                    ? 
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={selectedTransaction["selectedProduct"].Price} disabled/></Box></TableCell>
                                    :
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={selectedTransaction["selectedProduct"].Price}/></Box></TableCell>
                                    }
                                    
                                    <TableCell><Box component="span"><input name="qty" onChange={handleInputChange} className={classes.saleRateActive} placeholder="0" value={selectedTransaction.qty} /></Box></TableCell>
                                    <TableCell><Box component="span"><input className={classes.saleRate} placeholder="0" value={selectedTransaction["selectedProduct"].tax} disabled/></Box></TableCell>
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
                                        <TableCell>{transaction.productName}</TableCell>
                                        <TableCell>{transaction.mode}</TableCell>
                                        <TableCell><Box component="span"><input name="vehicleNo" className={classes.saleRateActive} placeholder="Vehicle No" value={transaction.vehicle} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.rate} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input name="qty" className={classes.saleRateActive} placeholder="0" value={transaction.qty} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.tax} disabled/></Box></TableCell>
                                        <TableCell><Box component="span"><input className={classes.saleRateActive} placeholder="0" value={transaction.amt} disabled/></Box></TableCell>
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
                        Save
                    </Button>
                </Box>
        </Container>
        </Fragment>
    </Page>

  )
}

export default Fee;
