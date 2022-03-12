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
import { InvoicePDF } from '../../components/deliveryReceipt';
import Page from '../../components/Page';
import { PDFViewer } from '@react-pdf/renderer';
import { useDispatch, useSelector } from 'react-redux';
import { createReceipt, retriveSingleReceipt } from '../../store/receipt';
import { getParameterByName } from '../../common/Utils';
  
  const useStyles = makeStyles((theme)=>createStyles({
    actionList: {
        display: 'flex',
        justifyContent: 'flex-end',
        flexGrow: '1',
    },
  }));
  
  const DeliveryReceipt = () => {
    const classes = useStyles();
    const history = useHistory();
    const dispatch = useDispatch()
    const saleData = useSelector(state => state.receipt.receipt)
    const [loading, setLoading] = useState(false);

    useEffect(()=>{
        const {id} = getParameterByName("id");
        if(id){
            dispatch(retriveSingleReceipt(id))
        }
        else{
            toast("Receipt id not found");
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
              </Stack>
                <Card>
                {/* {saleData && saleData.invoice && saleData.sale && saleData.sale.length ? */}
                    <PDFViewer width="100%" height="100%" style={{ border: 'none' }}>
                        <InvoicePDF saleData={saleData}/>
                    </PDFViewer>
                {/* : null} */}
            </Card>
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default DeliveryReceipt;