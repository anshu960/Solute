import * as React from 'react';
import {
    Box, Button, Card, Container,
    Stack,
    TableContainer,
    TextField,
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
import { SendEvent } from '../../socket/SocketHandler';
import SocketEvent from '../../socket/SocketEvent';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import { getBusinessId, getUserId } from '../../services/authService';
import Page from '../../components/Page';
import { PATH_DASHBOARD, PATH_PAGE } from '../../routes/path';
import {useDispatch, useSelector} from 'react-redux'
import { ScrollDialog } from '../../dialog';
import { AddStop } from '../../components/shipment/stop';
import { retriveShipment } from '../../store/shipment';
const getShipmentStatusToDispaly=(stauts)=>{
    var statusToDisplay = ""
    switch(stauts) {
        case 1:
            statusToDisplay = "Picked"
          break;
        case 2:
            statusToDisplay = "In Transit"
          break;
          case 3:
            statusToDisplay = "Delivered"
          break;
          case 4:
            statusToDisplay = "Dropped"
          break;
        default:
            statusToDisplay = "Delivered"
          break;
      }
      return statusToDisplay
}
  const useStyles = makeStyles((theme)=>createStyles({
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
    search:{
        '& input': {
            height: '17px',
        }
    }
  }));
  
  
const Shipments = () => {
    const classes = useStyles();
    const dispatch = useDispatch()
    const history = useHistory();
    const tableRef = React.useRef();
    const allShipment= useSelector(state=>state.shipment.allShipment)
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [addStopOpen, setAddStopOpen] = useState({
        open: false,
        shipment: null
    });
    useEffect(()=>{
        dispatch(retriveShipment(startDate,endDate))
    },[startDate,endDate])
        
    const getDateToDisplay=(dateStr)=>{
        const dateObj = new Date(dateStr);
        return (dateObj.getDate() + "-" + (dateObj.getMonth() + 1) + "-" + dateObj.getFullYear());
    }
    const handleClose = () => {
        setAddStopOpen({open: false, shipment:{}});
      }
    const contentBody = () => <AddStop shipment={addStopOpen.shipment} setOpen={setAddStopOpen}/>
    return (
      <Page title="History">
          <Fragment>
            <Container>
            <ToastContainer />
            <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Add Stopage" open={addStopOpen.open} dialogWidth="xl"/>
              <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                  <Typography variant="h4" gutterBottom>
                    Shipments
                  </Typography>
                  <Box className={classes.actionList}>
                        <Box component="span" className={classes.selctAutTarDate}>
                            <TextField
                                className={classes.search}
                                fullWidth
                                placeholder="Search"
                                //onChange={onChange}
                                name="search"
                                autoComplete={false}
                                //value={fields[field.id]}
                                type="text"
                                />
                        </Box>
                      <Box component="span" className={classes.selctAutTarDate}>
                          <DatePicker
                              placeholderText="Start Date"
                              className={classes.butonScrmenuSe}
                              selected={startDate}
                              onChange={(date) => {
                                  if(date){
                                    setStartDate(date)
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
                                      <TableCell>ID</TableCell>
                                      <TableCell>From</TableCell>
                                      <TableCell>To</TableCell>
                                      <TableCell>Delivery Date</TableCell>
                                      <TableCell>Status</TableCell>
                                      <TableCell>Stopage</TableCell>
                                      <TableCell>Details</TableCell>
                                      <TableCell>Track</TableCell>      
                                  </TableRow>
                              </TableHead>
                              <TableBody>
                              { allShipment && allShipment.length ? allShipment.map((shipment) => (
                                    <TableRow key={shipment.ShipmentID}>
                                        <TableCell>{(shipment.ShipmentID)}</TableCell>
                                        <TableCell>{shipment.SenderAddress}</TableCell>
                                        <TableCell>{shipment.ReceiverAddress}</TableCell>
                                        <TableCell>{getDateToDisplay(shipment.ShipmentDeliveryDate)}</TableCell>
                                        <TableCell>{getShipmentStatusToDispaly(shipment.Status)}</TableCell>
                                        <TableCell><Button variant="text" onClick={()=>{setAddStopOpen({open: true,shipment})}}>Add</Button></TableCell>
                                        <TableCell><Button variant="text" onClick={()=>{history.push({ pathname: PATH_DASHBOARD.delivery.profile, search: `?id=${shipment.ShipmentID}` })}}>View</Button></TableCell>
                                        <TableCell><Button variant="text" onClick={()=>{window.open(`/#${PATH_PAGE.shipmentTrack}?id=${shipment.ShipmentID}`, "_blank");}}>Locate</Button></TableCell>
                                    </TableRow>
                                )) : <TableRow><TableCell colSpan={8}>{"Unable to find shipments for selected date range"}</TableCell></TableRow> }
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Box>
            </Card>
          </Container>
          </Fragment>
      </Page>
  
    )
    
    
  }
  
  export default Shipments;  