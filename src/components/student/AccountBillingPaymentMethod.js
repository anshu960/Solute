import * as React from 'react';
import PropTypes from 'prop-types';
// material
import { Box, Paper, Stack, Card, Button, Collapse, TextField, IconButton, Typography } from '@mui/material';
import { Timeline, TimelineDot, TimelineItem, TimelineContent, TimelineConnector, TimelineSeparator, TimelineOppositeContent } from '@mui/lab';
import Select from 'react-select';
import { useState ,useCallback,useEffect} from 'react';
import SocketEvent from "./../../socket/SocketEvent";
import { SendEvent } from './../../socket/SocketHandler';
import AppLoader from '../Loader';
import { getBusinessId, getUserId } from '../../services/authService';
// ----------------------------------------------------------------------

AccountBillingPaymentMethod.propTypes = {
  formik: PropTypes.object,
  cards: PropTypes.array,
  isOpen: PropTypes.bool,
  onOpen: PropTypes.func,
  onCancel: PropTypes.func
};

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
        width: '100% !important',
        marginRight: '10px',
        '&:last-child': {
            marginRight: '0px',
        },
        zIndex: 5,
    }),
    indicatorsContainer: (base) => ({
        ...base,
        color:'#fff',
        alignItems: 'baseline',
    }),
  };

export default function AccountBillingPaymentMethod({ customer, isOpen, onOpen, onCancel ,onRefresh,setLoading,toast}) {
  const [isMakePayment, setIsMakePayment] = useState(true);
  const [payment, setPayment] = useState({PaymentReference:"",PaymentMode:"CASH",Amount:0});
  const [payments, setPayments] = useState([]);
  
  const retrivePaymentHistory=()=>{
      const UserID = getUserId();
      const BusinessID = getBusinessId();
      let request = {...payment};
      request.CustomerID = customer._id;
      request.UserID = UserID;
      request.BusinessID = BusinessID;
      console.log("RETRIVE_CUSTOMER_PAYMENT REQUEST",request);
      console.log("RETRIVE_CUSTOMER_PAYMENT customer",customer);
      SendEvent(SocketEvent.RETRIVE_CUSTOMER_PAYMENT,request,handleRetrivePayemntEvent);
  }
  const handleRetrivePayemntEvent = useCallback((data) => {
    console.log("handleUpdateCustomerEvent",data)
    if(data && data.status && data.status === "success"){
      setPayments(data.Payload);
    }else{
        console.log("Unable to find payment, please try after some time");
    }
  }, []);

  const handleUpdateInputChange = (event) => {
    event.preventDefault()
    setPayment({
        ...payment,
        [event.target.name]: event.target.value,
    })
    console.log("customerToEdit",JSON.stringify(payment))
    console.log("e.target.value",event.target.value)
    console.log("e.target.name",event.target.name)   
  }
  const handleUpdateSelectChange = (option, {name}) => {
    const value = (option && option.value) || '';
    setPayment({
      ...payment,
      [name]: value,
  })
  }
  const onClickAddPayment=(event)=>{
    event.preventDefault()
    setLoading(true);
    const UserID = getUserId();
    const BusinessID = getBusinessId();
    let request = {...payment};
    request.CustomerID = customer._id;
    request.UserID = UserID;
    request.BusinessID = BusinessID;
    // request.Amount = request.Amount.toFixed(2);
    console.log("ADD_CUSTOMER_PAYMENT REQUEST",request);
    console.log("ADD_CUSTOMER_PAYMENT payment Edit",payment);
    SendEvent(SocketEvent.ADD_CUSTOMER_PAYMENT,request,handleUpdatePayemntEvent);
}
const handleUpdatePayemntEvent = useCallback((data) => {
  setLoading(false);
  console.log("handleUpdateCustomerEvent",data)
  if(data && data.status && data.status === "success"){
    toast("Payment Entry added");
    handleMakePaymentCollapse();
    onRefresh();
  }else{
      toast("Unable to add Payment Entry at the moment, Please try after some time");
      console.log("Unable to find customer, please try after some time");
  }
}, []);


  const handleMakePaymentCollapse = () => {
    setIsMakePayment(true);
    onOpen();
  }

  const handlePaymentHistoryCollapse = () => {
    setIsMakePayment(false);
    onOpen();
    if(isMakePayment){
      retrivePaymentHistory();
    }
  }

  return (
    <Card sx={{ p: 3 }}>
      <Typography variant="overline" sx={{ mb: 3, display: 'block', color: 'text.secondary' }}>
        Payments
      </Typography>
      <Box sx={{ mt: 3 }}>
        <Button size="small" onClick={handleMakePaymentCollapse}>
          Make Payment
        </Button>
        <Button style={{float:'right'}} size="small" onClick={handlePaymentHistoryCollapse}>
          Payment History
        </Button>
      </Box>
      <Collapse in={isOpen}>
        <Box
          sx={{
            padding: 3,
            marginTop: 3,
            borderRadius: 1,
            bgcolor: 'background.neutral'
          }}
        >
            {isMakePayment ? 
            (<form onSubmit={()=>{}}>
              <Stack spacing={3}>
                <Typography variant="subtitle1">Payment Details</Typography>
                    <Select
                        options={[{label:'CASH',value: 'CASH'},{label:'RTGS',value: 'RTGS'},{label:'IMPS',value: 'IMPS'},{label:'UPI',value: 'UPI'},{label:'CHEQUE',value: 'CHEQUE'},{label:'CARD',value: 'CARD'}]}
                        placeholder="Mode"
                        name="PaymentMode"
                        styles={customSeverityStyle}
                        onChange={handleUpdateSelectChange}
                    />
                  <TextField
                    fullWidth
                    label="Reference"
                    name="PaymentReference"
                    value={payment.PaymentReference}
                    onChange={handleUpdateInputChange}
                  />
                  <TextField
                    fullWidth
                    label="Amount"
                    name="Amount"
                    value={payment.Amount}
                    onChange={handleUpdateInputChange}
                  />
                <Stack direction="row" justifyContent="flex-end" spacing={1.5}>
                  <Button type="button" color="inherit" variant="outlined" onClick={onCancel}>
                    Cancel
                  </Button>
                  <Button
                    disabled = {false}
                    style={{cursor:'pointer'}}
                    variant="outlined"
                    onClick={onClickAddPayment}        
                >
                    Update
                </Button>
                </Stack>
              </Stack>
            </form>)
            : (
              payments && payments.length ? (
                <Timeline>
                  {payments.length ? (
                    <TimelineItem>
                    <TimelineOppositeContent color="text.secondary">
                      <Typography variant="subtitle2">{payments[0].PaymentMode}</Typography>
                      <Typography variant="caption" sx={{ color: 'text.secondary' }}>{payments[0].PaymentReference}</Typography>
                    </TimelineOppositeContent>
                    <TimelineSeparator>
                      <TimelineDot/>
                      <TimelineConnector />
                    </TimelineSeparator>
                    <TimelineContent>
                      <Typography variant="subtitle2">{payments[0].Amount}</Typography>
                      <Typography variant="caption" sx={{ color: 'text.secondary' }}>
                      {new Date(payments[0].PaymentDate).toLocaleDateString()}
                      </Typography>
                    </TimelineContent>
                  </TimelineItem>
                  ) : null}
                {payments.length>=2 ? (
                  <TimelineItem>
                  <TimelineOppositeContent color="text.secondary">
                    <Typography variant="subtitle2">{payments[1].PaymentReference}</Typography>
                  </TimelineOppositeContent>
                  <TimelineSeparator>
                    <TimelineDot/>
                    <TimelineConnector />
                  </TimelineSeparator>
                  <TimelineContent>
                    <Typography variant="subtitle2">{payments[1].Amount}</Typography>
                    <Typography variant="caption" sx={{ color: 'text.secondary' }}>
                    {new Date(payments[1].PaymentDate).toLocaleDateString()}
                    </Typography>
                  </TimelineContent>
                </TimelineItem>
                ):null}
                {payments.length>=3 ? (
                  <TimelineItem>
                  <TimelineOppositeContent color="text.secondary">
                    <Typography variant="subtitle2">{payments[2].PaymentReference}</Typography>
                  </TimelineOppositeContent>
                  <TimelineSeparator>
                    <TimelineDot/>
                  </TimelineSeparator>
                  <TimelineContent>
                    <Typography variant="subtitle2">{payments[2].Amount}</Typography>
                    <Typography variant="caption" sx={{ color: 'text.secondary' }}>
                    {new Date(payments[2].PaymentDate).toLocaleDateString()}
                    </Typography>
                  </TimelineContent>
                </TimelineItem>
                ):null}
              </Timeline>
              ) : null
              
            ) }
        </Box>
      </Collapse>
    </Card>
  );
}
