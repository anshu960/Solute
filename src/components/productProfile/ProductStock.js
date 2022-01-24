import React, { useState } from 'react';
// @mui
import { Button, Grid, Stack, Typography, } from '@mui/material';
import { stockFields } from './FieldConfig';
import InputTextField from '../InputTextField';
import { ScrollDialog } from '../../dialog';
import { StockAdjustment } from '../stock/adjustment';
import { StockIn } from '../stock/stockin';
// ----------------------------------------------------------------------


// ----------------------------------------------------------------------
const defaultFields = {
  StockIn:'',
  StockAdjustment: '',
  StockQuantity: '',
}

export default function ProductStock() {
  const [fields, setFields] = useState(defaultFields);
  const [stockAdjustmentOpen, setStockAdjustmentOpen] = useState(false);
  const [stockInOpen, setStockInOpen] = useState(false);
  const onChange = (event)=> setFields({...fields,[event.target.name]:event.target.value})
  const handleSave = () => {
    console.log(fields)
  }

  const getTextField = (field) =>(
    <InputTextField
      fullWidth
      placeholder={field.placeholder}
      onChange={onChange}
      name={field.id}
      autoComplete={field.id}
      value={fields[field.id]}
      type={field.type}
      multiline={!!(field.multiline)}
    />
  )

  const getField = (field) => {
    return getTextField(field);
  }
  
  const handleClose = () => {
    setStockAdjustmentOpen(false);
  }

  const prepareInputFields = () => stockFields.map((field) =>
  (
    <Grid item xs={12} md={12} lg={6} xl={6}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          {field.label}
        </Typography>
        {getField(field)}
      </Stack>
    </Grid>
  ))
  const contentBody = () => <StockAdjustment open={stockAdjustmentOpen} setOpen={setStockAdjustmentOpen}/>
  const contentBodyStockIn = () => <StockIn open={stockInOpen} setOpen={setStockInOpen}/>
  return (
    <React.Fragment>
    <ScrollDialog body={contentBody()} handleClose={handleClose} scroll={'paper'} title="Stock Adjustment" open={stockAdjustmentOpen} dialogWidth="xs"/>
    <ScrollDialog body={contentBodyStockIn()} handleClose={handleClose} scroll={'paper'} title="Stock In" open={stockInOpen} dialogWidth="xs"/>
    <Grid container spacing={3} py={2}>
      {prepareInputFields()}
    </Grid>
      <Stack spacing={3} py={3}>
        <Button variant="contained" onClick={()=>{setStockAdjustmentOpen(true)}} >StockAdjsutment</Button>  
      </Stack>
      <Stack spacing={3} py={3}>
        <Button variant="contained" onClick={()=>{setStockInOpen(true)}} >StockIn</Button>  
      </Stack>
      <Stack spacing={3}>
        <Button variant="contained" onClick={handleSave} >Save</Button>  
      </Stack>
    </React.Fragment>
  );
}
