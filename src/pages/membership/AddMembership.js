import * as React from 'react';
import {
  Alert,
    Avatar,
    Box, Button, Card, Container,
    Grid,
    Stack,
    Typography,
    useMediaQuery
  } from '@mui/material';
  import {
      useEffect,
    useState,useCallback,Fragment
  } from 'react';
import { useHistory, Link as RouterLink, Link } from 'react-router-dom';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import Page from '../../components/Page';
import { useStyles } from './Style';
import { MembershipUpload } from '../../components/membership';
import { readFile } from '../../components/excel';
import MembershipCard from '../../components/membership/MembershipCard';
import { alpha, useTheme, styled } from '@mui/material/styles';
import { useDispatch, useSelector } from 'react-redux';
import { retriveActiveSubscription, subscribeToPlan } from '../../store/subscription';
import { getBusiness } from '../../services/authService';

const CardStyle = styled(Card)(({ theme }) => {
  const shadowCard = (opacity) =>
    theme.palette.mode === 'light'
      ? alpha(theme.palette.grey[500], opacity)
      : alpha(theme.palette.common.black, opacity);

  return {
    maxWidth: 380,
    minHeight: 350,
    margin: 'auto',
    textAlign: 'center',
    padding: theme.spacing(10, 5, 3),
    boxShadow: `-40px 40px 80px 0 ${shadowCard(0.48)}`,
    [theme.breakpoints.up('md')]: {
      boxShadow: 'none',
      backgroundColor: theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800]
    },
    '&.cardLeft': {
      [theme.breakpoints.up('md')]: { marginTop: -40 },
    },
    '&.cardCenter': {
      [theme.breakpoints.up('md')]: {
        marginTop: -80,
        backgroundColor: theme.palette.background.paper,
        boxShadow: `-40px 40px 80px 0 ${shadowCard(0.4)}`,
        '&:before': {
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          zIndex: -1,
          content: "''",
          margin: 'auto',
          position: 'absolute',
          width: 'calc(100% - 40px)',
          height: 'calc(100% - 40px)',
          borderRadius: theme.shape.borderRadiusMd,
          backgroundColor: theme.palette.background.paper,
          boxShadow: `-20px 20px 40px 0 ${shadowCard(0.12)}`
        }
      }
    }
  };
});

const CARDS = [{
  "_id":  "6245335d808ed504a6e83874",
  "Description": "It is a trial pack and completely free of cost. It is applied only first time.",
  "Title": "14 Days",
  "Days": 14,
  "Amount": 0,
  "Currency": "INR",
},{
  "_id":  "62453547808ed504a6e83875",
  "Description": "It is monthly rental plan.",
  "Title": "Monthly",
  "Days": 30,
  "Amount": 199,
  "Currency": "INR"
},{
  "_id": "62453576808ed504a6e83876",
  "Description": "It is quarterly rental plan.",
  "Title": "Quarterly",
  "Days": 90,
  "Amount": 499,
  "Currency": "INR"
},{
  "_id":"62453606808ed504a6e83877",
  "Description": "It is half yearly paid plan",
  "Title": "Half Yearly",
  "Days": 180,
  "Amount": 799,
  "Currency": "INR"
},{
  "_id": "62453654808ed504a6e83878",
  "Description": "It is 9 month paid plan.",
  "Title": "9 Month",
  "Days": 270,
  "Amount": 1099,
  "Currency": "INR"
},{
  "_id": "62453698808ed504a6e83879",
  "Description": "It is yearly rental plan.",
  "Title": "Yearly",
  "Days": 270,
  "Amount": 1399,
  "Currency": "INR"
}];

  const AddMembership = () => {
    const dispatch = useDispatch()
    const classes = useStyles();
    const history = useHistory();
    const [loading, setLoading] = useState(false);
    const [files, setFiles] = useState([]);
    const [users, setUsers] = useState([]);
    const currentSubscription = useSelector(state=>state.Subscription.activeSubscription)
    
    // useEffect(
    //     ()=>{
    //         if(files.length){
    //             readFile(files[0],(userList)=>{
    //                 console.log(userList)
    //                 setUsers(userList);
    //             });
    //         }
    //     },
    //     [files.length]
    // )

    useEffect(()=>{
      dispatch(retriveActiveSubscription())
    },[])

    const handleActive = (plan) => {
      console.log(plan)
      dispatch(subscribeToPlan(plan))
    }

    const getPlanColor = (plan) => {
      if(Object.keys(currentSubscription).length && currentSubscription.SubscriptionPlanID === plan._id){
          return currentSubscription.isActive ? 'green': 'red';
      }
      return 'orange';
    }

    const getCardDetails = (plan) => {
      const business = getBusiness();
      const days = (new Date().getTime() - new Date(business.CreatedAt).getTime())/(24*60*60*1000)
      let colorCode = '', text = 'Purchase';
      if(plan.Amount){
        colorCode = getPlanColor(plan);
        text = colorCode === 'green' ? 'Renewal': 'Purchase';
      }
      else{
        colorCode = ((plan.Days - days) > 0) ? 'green' : 'red';
        text = colorCode === 'green' ? 'Active': 'Expired';
      }
      return {
        css: {border: `3px solid ${colorCode}`},
        text
      }
    }

    return (
      <Page title="Membership">
          <Fragment>
            <Container>
            <ToastContainer />
            { loading ? <AppLoader/> :null}
              <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                  <Typography variant="h4" gutterBottom>
                    Membership
                  </Typography>
                  {/* <MembershipUpload setFiles={setFiles}/> */}
              </Stack>
                <Grid container spacing={3} sx={{ my: 10 }}>
                {CARDS.map((card, index) => {
                    const cardDetails = getCardDetails(card)
                    return (<Grid key={card.Title} sx={{px: 2, pb: 5}} item xs={12} md={4} >
                        <CardStyle className="cardLeft" sx={cardDetails.css}>
                          <Typography variant="h3" paragraph>
                            {card.Title}
                          </Typography>
                          <Typography variant="h5" paragraph>
                            {card.Description}
                          </Typography>
                          <Typography paragraph sx={{ color: true ? 'text.secondary' : 'common.white' }}>
                            {card.Currency + " " + card.Amount}
                          </Typography>
                          <Box>
                            <Button
                              color={cardDetails.text === 'Expired' ? 'error': (cardDetails.text === 'Renewal' ? 'success': 'info')}
                              style={{cursor:'pointer',width: '165px',}}
                              disabled={cardDetails.text === 'Expired' ? true: false}
                              variant="outlined"
                              onClick={()=>handleActive(card)}
                            >
                                {cardDetails.text}
                            </Button>
                          </Box>
                        </CardStyle>
                    </Grid>)
                })}
                </Grid>
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default AddMembership;  