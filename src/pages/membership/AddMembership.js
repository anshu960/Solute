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
    padding: theme.spacing(10, 5, 0),
    boxShadow: `-40px 40px 80px 0 ${shadowCard(0.48)}`,
    [theme.breakpoints.up('md')]: {
      boxShadow: 'none',
      backgroundColor: theme.palette.grey[theme.palette.mode === 'light' ? 200 : 800]
    },
    '&.cardLeft': {
      [theme.breakpoints.up('md')]: { marginTop: -40 }
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

const CARDS = [
  {
    id: 1,
    plan: '14 Days',
    title: 'Free',
    description:
    `It is a trial pack and completely free of cost. 
    It is applied only first time.`
  },
  {
    id: 2,
    plan: 'Monthly',
    title: '199',
    description: 
    'It is monthly rental plan.',
  },
  {
    id: 3,
    plan: 'Quarterly',
    title: '499',
    description: `It is quarterly rental plan.`
  },
  {
    id: 4,
    plan: 'Half Yearly',
    title: '799',
    description: 
    `It is half yearly paid plan.`,
    //'Manage the complete business from sale, delivery, invoice, sharable receipt to reports with analytics'
  },
  {
    id: 5,
    plan: '9 Month',
    title: '1099',
    description: `It is 9 month paid plan.`
  },
  {
    id: 6,
    plan: 'Yearly',
    title: '1399',
    description: `It is yearly rental plan.`
  }
];

  const AddMembership = () => {
    const classes = useStyles();
    const history = useHistory();
    const [loading, setLoading] = useState(false);
    const [files, setFiles] = useState([]);
    const [users, setUsers] = useState([]);
    
    useEffect(
        ()=>{
            if(files.length){
                readFile(files[0],(userList)=>{
                    console.log(userList)
                    setUsers(userList);
                });
            }
        },
        [files.length]
    )
    console.log(users)

    const handleActive = (id) => {
      console.log(id)
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
                {CARDS.map((card, index) => (
                    <Grid key={card.title} sx={{px: 2, pb: 5,}} item xs={12} md={4}>
                      <div 
                      //variants={varFadeInUp}
                      >
                        <CardStyle className="cardLeft">
                        <Typography variant="h3" paragraph>
                            {card.plan}
                          </Typography>
                          {/* <CardIconStyle
                            src={card.icon}
                            alt={card.title}
                            sx={{
                              ...({ filter: (theme) => shadowIcon(theme.palette.info.main)})
                            }}
                          /> */}
                          <Typography variant="h5" paragraph>
                            {card.title}
                          </Typography>
                          <Typography paragraph sx={{ color: true ? 'text.secondary' : 'common.white' }}>
                            {card.description}
                          </Typography>
                          <Box>
                            <Button
                              style={{cursor:'pointer',width: '165px',}}
                              variant="outlined"
                              onClick={()=>handleActive(card.id)}
                            >
                                Active
                            </Button>
                          </Box>
                          
                        </CardStyle>
                      </div>
                    </Grid>
                  ))}
                    {/* {
                        (users && users.length) 
                        ? users.map((user)=> user.Name ? <MembershipCard user={user}/> : null)
                        : (<Grid item xs={12}>
                            <Box>
                              <Alert variant="outlined" severity="info">
                                  No Card to Display, Please export
                              </Alert>
                            </Box>
                          </Grid>)
                    } */}
                </Grid>
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default AddMembership;  