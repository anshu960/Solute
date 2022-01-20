import * as React from 'react';
import {
  Alert,
    Box, Button, Card, Container,
    Grid,
    Stack,
    Typography
  } from '@mui/material';
  import {
      useEffect,
    useState,useCallback,Fragment
  } from 'react';
import { useHistory, Link as RouterLink } from 'react-router-dom';
import AppLoader from '../../components/Loader';
import { toast, ToastContainer } from 'react-toastify';
import Page from '../../components/Page';
import { useStyles } from './Style';
import { MembershipUpload } from '../../components/membership';
import { readFile } from '../../components/excel';
import MembershipCard from '../../components/membership/MembershipCard';
  
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
                  <MembershipUpload setFiles={setFiles}/>
              </Stack>
                <Grid container spacing={3} sx={{ my: 10 }}>
                    {
                        (users && users.length) 
                        ? users.map((user)=> user.Name ? <MembershipCard user={user}/> : null)
                        : (<Grid item xs={12}>
                            <Box>
                              <Alert variant="outlined" severity="info">
                                  No Card to Display, Please export
                              </Alert>
                            </Box>
                          </Grid>)
                    }
                </Grid>
          </Container>
          </Fragment>
      </Page>
  
    )
  }
  
  export default AddMembership;  