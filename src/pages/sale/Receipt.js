import * as React from 'react';
import {
    Box, Card, Container,
    Stack,
    Typography
  } from '@mui/material';

  import { createStyles, makeStyles } from '@mui/styles';
  import {
      useEffect,
    useState,Fragment
  } from 'react';
  import { useHistory, Link as RouterLink } from 'react-router-dom';
  import ExportPDF from "../ExportPDF";
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { getBusiness, getBusinessId, getUserId } from '../../services/authService';
import { InvoicePDF } from '../../components/invoice';
import Page from '../../components/Page';
import { PDFViewer } from '@react-pdf/renderer';
import { useDispatch, useSelector } from 'react-redux';
import { createReceipt } from '../../store/receipt';
  
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
  
  const Receipt = () => {
    const classes = useStyles();
    const history = useHistory();
    const dispatch = useDispatch()
    const saleData = useSelector(state => state.receipt.newReceipt)
    const [loading, setLoading] = useState(false);

    useEffect(()=>{
        if(sessionStorage.getItem("sale")){
            //setLoading(true);
            const totalSales = JSON.parse(sessionStorage.getItem("sale")).Payload;
            dispatch(createReceipt(totalSales));
            sessionStorage.removeItem("sale");
            sessionStorage.removeItem("selectedDate");
        }else{
            toast("Sale record not found");
        }
    },[])

    console.log('saleData', saleData);
    return (
      <Page title="Receipt">
          <Fragment>
            <Container>
            <ToastContainer />
            { loading ? <AppLoader/> :null}
              <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                  <Typography variant="h4" gutterBottom>
                    Receipt
                  </Typography>
                  <Box className={classes.actionList}>
                    {/* <InvoiceToolbar /> */}
                  </Box> 
              </Stack>
                <Card>
                {saleData && saleData.invoice && saleData.sale && saleData.sale.length ?
                    <PDFViewer width="100%" height="100%" style={{ border: 'none' }}>
                        <InvoicePDF saleData={saleData}/>
                    </PDFViewer>
                : null}
            </Card>
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default Receipt;