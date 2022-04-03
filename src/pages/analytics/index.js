import * as React from 'react';
import { styled } from '@mui/material/styles';
import Page from '../../components/Page';
import { useDispatch, useSelector } from 'react-redux';
import { connectWhatsapp } from '../../store/WhatsApp';
import QRCode from "react-qr-code";
const RootStyle = styled(Page)(({ theme }) => ({
  minHeight: '100%',
  paddingTop: theme.spacing(12),
  [theme.breakpoints.up('md')]: {
    paddingTom: theme.spacing(10)
  }
}));

export default function Analytics() {
  const dispatch = useDispatch()
  const qrCode = useSelector(state=>state.WhatsApp.QR)
  React.useEffect(()=>{
      dispatch(connectWhatsapp())
  },[])
  return (
    <RootStyle title="Home">
      <QRCode
      style={{marginaleft:200}}
      size={1024}
      level={'H'}
      sx={{
        color: 'common.white',
        textAlign: 'center',
        minHeight:300,
        minWidth:300,
        marginLeft:500
      }}
       value={qrCode || ""} />
    </RootStyle>
  );
}
