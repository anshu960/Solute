import * as React from 'react';
import {
    Box, Button, Card, Container,
    Stack,
    TableContainer,
    Typography
  } from '@mui/material';
  import Select from 'react-select';
  import DatePicker from 'react-datepicker';
  import { createStyles, makeStyles } from '@mui/styles';
  import {
      useEffect,
    useState,useCallback,Fragment
  } from 'react';
  import Table from '@mui/material/Table';
  import TableBody from '@mui/material/TableBody';
  import TableCell from '@mui/material/TableCell';
  import TableHead from '@mui/material/TableHead';
  import TableRow from '@mui/material/TableRow';
  import CalendarIcon from '@mui/icons-material/CalendarToday';
  import { useHistory, Link as RouterLink } from 'react-router-dom';
  import ExportPDF from "../ExportPDF";
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { getBusinessId, getUserId } from '../../services/authService';
import Page from '../../components/Page';
import { PATH_DASHBOARD } from '../../routes/path';
  
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
        height: '48px',
        display: 'inline-block',
        lineHeight: '26px',
        borderRadius: '4px',
        backgroundColor: theme.palette.grey[300]
    },
    saleRateActive:{
        border:'none',
        textAlign:'center',
        width: '85px',
        height: '48px',
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
        '& div':{}
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
  
  const Invoice = () => {
    const classes = useStyles();
    const history = useHistory();
    const tableRef = React.useRef();
    const [recieve, setRecieve] = useState(0);
    const [sales, setSales] = useState([]);
    const [selectedCustomer, setSelectedCustomer] = useState({});
    const [customerList, setCustomerList] = useState([]);
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [loading, setLoading] = useState(false);
    const [isFromCurrentSale, setFromCurrentSale] = useState(false);
    const [totalAmount, setTotalAmount] = useState(0);
    useEffect(()=>{
        if(sessionStorage.getItem("sale") && sessionStorage.getItem("selectedCustomer")){
            const totalSales = JSON.parse(sessionStorage.getItem("sale")).Payload;
            setSales(totalSales);
            const customer = JSON.parse(sessionStorage.getItem("selectedCustomer"))
            setSelectedCustomer(customer);
            setCustomerList([customer]);
            if(sessionStorage.getItem("selectedDate") && sessionStorage.getItem("selectedDate")){
                setStartDate(new Date(sessionStorage.getItem("selectedDate")))
                setEndDate(new Date(sessionStorage.getItem("selectedDate")));
            }else{
                setStartDate(new Date());
                setEndDate(new Date());
            }
            console.log("sale data",sales);
            console.log("selectedCustomer",customer);
            setFromCurrentSale(true);
            var totalSaleAmount = 0;
            totalSales.forEach((sale)=>{
                totalSaleAmount = totalSaleAmount + sale.Amount
            })
            setTotalAmount(totalSaleAmount);
            toast("Sales Record Saved");
            sessionStorage.removeItem("sale");
            sessionStorage.removeItem("selectedCustomer");
            sessionStorage.removeItem("selectedDate");
            global.selectedCustomer = undefined;
        }else{
            console.log("sessionStorage.getItem(id)",sessionStorage.getItem("sale"));
            console.log("sessionStorage.getItem selectedCustomer",sessionStorage.getItem("selectedCustomer"));
            toast("Please select Customer to see sales record");
            refreshCustomerList();
            
        }
    },[startDate,endDate])

    const refreshCustomerList=()=>{
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {UserID,BusinessID}
        setLoading(true);
        SendEvent(SocketEvent.RETRIVE_CUSTOMER,request,handleRetriveCustomerEvent)
      }
      const handleRetriveCustomerEvent = useCallback((data) => {
        setLoading(false);
        console.log("handleRetriveCustomerEvent",data)
        if(data && data.Payload && data.Payload.length > 0){
              setCustomerList(data.Payload);
        }else{
            console.log("Unable to find customer, please try after some time");
        }
      }, []);

      const refreshSalesList=(customer)=>{
        const UserID = getUserId();
        const BusinessID = getBusinessId();
        const request = {
            UserID,
            BusinessID,
            CustomerID:customer ? customer._id : selectedCustomer._id,
            StartDate:startDate.toISOString().substring(0, 10),
            EndDate:endDate.toISOString().substring(0, 10)
        }
        setLoading(true);
        SendEvent(SocketEvent.RETRIVE_BUSINESS_SALE,request,handleRetriveSalesEvent)
      }

      const handleRetriveSalesEvent = useCallback((data) => {
        setLoading(false);
        console.log("handleRetriveSalesEvent",data)
        if(data && data.Payload && data.Payload.length > 0){
              setSales([]);
              setSales(data.Payload);
              let totalSaleAmount = 0;
              data.Payload.forEach((sale)=>{
                totalSaleAmount = totalSaleAmount + sale.Amount
            })
            setTotalAmount(totalSaleAmount);
        }else{
            console.log("Unable to find sales, please try after some time");
        }
      }, []);



      const customerOptions = Object.keys(customerList).map((prd) => {
        console.log("prd",prd);
        return {
            label: customerList[prd].Name,
            value: customerList[prd]._id,
        }
    });

    
    const handleChangeCustomer = (option, {name}) => {
        const value = (option && option.value) || '';
        const customer = customerList.filter((prd)=>prd._id === value)
        if(customer.length){
            setSelectedCustomer(customer[0])
            console.log("Customer set",customer[0]);
        }else{
            console.log("Customer not found",customer)
            console.log("Customer not found customerList ",customerList)
        }
        checkAndRefreshSale(customer[0]);
      }

    const checkAndRefreshSale=(customer)=>{
        if(customer){
            refreshSalesList(customer);
        }else if(selectedCustomer && selectedCustomer._id){
            refreshSalesList(selectedCustomer);
        }else{
            console.log("Skiping refresh sale coz customer is not selected yet : ",selectedCustomer);
        }
    }
        
    const getDateToDisplay=(dateStr)=>{
        const dateObj = new Date(dateStr);
        return (dateObj.getDate() + "-" + (dateObj.getMonth() + 1) + "-" + dateObj.getFullYear());
    }

    return (
      <Page title="Invoice">
          <Fragment>
            <Container>
            <ToastContainer />
            { loading ? <AppLoader/> :null}
              <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                  <Typography variant="h4" gutterBottom>
                      Invoice
                  </Typography>
                  <Box className={classes.actionList}>
                      <Select
                          options={customerOptions}
                          placeholder="Party Name"
                          className={classes.selectBoxStyle}
                          styles={customSeverityStyle}
                          isClearable
                          onChange={handleChangeCustomer}
                      />
                      <Box component="span" className={classes.selctAutTarDate}>
                          <DatePicker
                              placeholderText="Start Date"
                              className={classes.butonScrmenuSe}
                              selected={startDate}
                              onChange={(date) => {
                                  if(date){
                                    setStartDate(date)
                                    checkAndRefreshSale()
                                  }
                            }}
                          />
                      </Box>
                      <Box component="span" className={classes.selctAutTarDate}>
                          <DatePicker
                              placeholderText="End Date"
                              className={classes.butonScrmenuSe}
                              selected={endDate}
                              onChange={(date) =>{
                                if(date){
                                checkAndRefreshSale();
                                setEndDate(date);
                                }
                              }}
                          />
                      </Box>
                  </Box> 
              </Stack>
                <Card>
                  <Box>
                      <TableContainer sx={{ minWidth: 800 }}>
                          <Table style={{width:'100%', textAlign:'center'}} ref={tableRef}>
                              <TableHead>
                                  <TableRow>
                                      <TableCell>SR NO.</TableCell>
                                      <TableCell>DATE</TableCell>
                                      <TableCell>Vehicle No.</TableCell>
                                      {/* <TableCell>Description</TableCell> */}
                                      <TableCell>M.S</TableCell>
                                      <TableCell>H.S.D</TableCell>
                                      <TableCell>LUBS</TableCell>
                                      <TableCell>Rate</TableCell>
                                      <TableCell>Amount</TableCell>
                                  </TableRow>
                              </TableHead>
                              <TableBody>
                              { sales.length ? sales.map((sale, index) => (
                                    <TableRow key={index}>
                                      <TableCell>{(index+1)}</TableCell>
                                      <TableCell>{getDateToDisplay(sale.SaleDate)}</TableCell>
                                      <TableCell>{sale.VehicleNumber}</TableCell>
                                      {/* <TableCell>{sale.ProductName}</TableCell> */}
                                      <TableCell>{sale.ProductName === "MS" ? sale.Quantity : 0}</TableCell>
                                      <TableCell>{sale.ProductName === "HSD" ? sale.Quantity : 0}</TableCell>
                                      <TableCell>{(sale.ProductName != "MS" && sale.ProductName != "HSD") ? sale.Quantity : 0}</TableCell>
                                      <TableCell>{sale.Price}</TableCell>
                                      <TableCell>{sale.Amount}</TableCell>
                                    </TableRow>
                                )) : <TableRow><TableCell colSpan={8}>{"Sales to Display for " + selectedCustomer.Name + " from " + startDate.toLocaleDateString() + " to " + endDate.toLocaleDateString()}</TableCell></TableRow> }
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
            <Box py={3} className={classes.bottomButtonExpert}>
            <Button
                className={classes.addNewGroupBulk}
                variant="outlined"
                onClick={()=>{
                    sessionStorage.setItem("sale", (JSON.stringify({Payload:sales})));
                    history.push(PATH_DASHBOARD.sale.receipt);
                }}
            >
                Print
            </Button>
            </Box>
            {/* <Box py={3} className={classes.bottomButtonExpert}>
                <ExportPDF refsToPrint={[tableRef]} sales={sales} totalAmount={totalAmount} customer = {selectedCustomer} invoiceDate={getDateToDisplay(new Date().toISOString())} startDate={getDateToDisplay(startDate)} endDate={getDateToDisplay(endDate)} setLoading={setLoading}/>
            </Box> */}
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default Invoice;  