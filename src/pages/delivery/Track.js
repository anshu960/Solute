import * as React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import StepContent from '@mui/material/StepContent';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Page from '../../components/Page';
import { Container, Stack } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { retriveShipmentStatus } from '../../store/shipment';
import { getParameterByName } from '../../common/Utils';

const steps = [
  {
    label: 'From',
    address: 'Sec - 1, Noida, UP-201301',
    description: `Delivered`,
  },
  {
    label: 'Stopage 1',
    address:`Address of Stoage 1`,
    description: `Delivered`,
  },
  {
    label: 'Stopage 2',
    address:`Address of Stoage 2`,
    description: `Picked`,
  },
  {
    label: 'Stopage 3',
    address:`Address of Stoage 3`,
    description: `Waiting`,
  },
  {
    label: 'To',
    address:`Address of Desitnation`,
    description: `Delivered Successfully`,
  },
];

export default function Track() {
  const dispatch = useDispatch();
  const allStatus = useSelector(state=>state.shipment.allShipmentStatus)
  const [activeStep, setActiveStep] = React.useState(0);
  const {id} = getParameterByName("id");
  React.useEffect(()=>{
    setTimeout(()=>{
      dispatch(retriveShipmentStatus(id,(data)=>{
          if(data && data.length){
            data.forEach(element => {
              if(element.Status === 4){
                setActiveStep(data.length)
              }
            });
          }
      }))
    },500)
  },[])
  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  const handleReset = () => {
    setActiveStep(0);
  };

  return (
    <Page title="Track">
        <React.Fragment>
            <Container>
            <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                  <Typography variant="h4" gutterBottom>
                    Track
                  </Typography> 
              </Stack>
            <Box sx={{ maxWidth: 400 }}>
            <Stepper activeStep={activeStep} orientation="vertical">
                {allStatus.map((step, index) => (
                <Step key={step.label}>
                    <StepLabel
                    optional={
                        <Typography variant="caption">{step.Address}</Typography>}
                    >
                    {step.label}
                    </StepLabel>
                    <StepContent>
                    <Typography>{step.description}</Typography>
                    {/* <Box sx={{ mb: 2 }}>
                        <div>
                        <Button
                            variant="contained"
                            onClick={handleNext}
                            sx={{ mt: 1, mr: 1 }}
                        >
                            {index === steps.length - 1 ? 'Finish' : 'Continue'}
                        </Button>
                        <Button
                            disabled={index === 0}
                            onClick={handleBack}
                            sx={{ mt: 1, mr: 1 }}
                        >
                            Back
                        </Button>
                        </div>
                    </Box> */}
                    </StepContent>
                </Step>
                ))}
            </Stepper>
            {/* {activeStep === steps.length && (
                <Paper square elevation={0} sx={{ p: 3 }}>
                <Typography>Delivered Successfully</Typography>
                <Button onClick={handleReset} sx={{ mt: 1, mr: 1 }}>
                    Reset
                </Button>
                </Paper>
            )} */}
            </Box>
            </Container>
        </React.Fragment>
    </Page>
  );
}
