import React, { Fragment, useEffect, useState } from 'react';
// @mui
import { Box, Button, Collapse, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../InputTextField';
import { ProductImage } from '.';
import CheckboxField from '../CheckboxField';
import Select from 'react-select';
import { productFields, taxFields } from './FieldConfig';
import { useDispatch, useSelector } from 'react-redux';
import { setNewProduct, setNewProductProperty } from '../../store/product';
import { syncBusinessData } from '../../store/business';
import { calculateTax, getAppliedTax, getTax } from './TaxUtility';

export default function ProductCreate({handleAddProduct}) {
  const dispatch = useDispatch()
  const fields = useSelector(state => state.product.newProduct)
  const [files, setFiles] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const allHSN = useSelector(state => state.hsn.allHSN)
  const hsnList = (allHSN && allHSN.length) ? allHSN.map((hsn) => ({label: hsn.Name, value: hsn._id})): [];
  useEffect(()=>{
    if(allHSN && allHSN.length){

    }else{
      dispatch(syncBusinessData())
    }
  },[])
  const handleSave = () => {
    console.log("hsnList ",hsnList);
    console.log(fields)
    handleAddProduct(fields,files)
  }

  const updateTax = (product) => {
    let hsn = {}
    if(product.HSN && allHSN){
      allHSN.forEach(element => {
          if(element._id === product.HSN){
            hsn = element;
          }
      });
    }
    const result = getTax(product, hsn);
    dispatch(setNewProductProperty(result))
  }

  const onChange = (event)=>{
    const name = event.target.name;
    const value = event.target.value;
    const update = {[name]:value};
    const taxParam = ['IGST','CGST','SGST','VAT','CESS'];
    if(taxParam.includes(name)){
      let taxValue = calculateTax(name, value, fields.Price);
      taxValue = isNaN(taxValue) ? 0 : taxValue;  
        update[name+'Value'] = parseFloat(taxValue);
        update["Tax"] = getAppliedTax(taxParam,fields, name) + parseFloat(taxValue);
        if(!fields.TaxIncluded){
          update["FinalPrice"] = parseFloat(parseFloat(fields.Price) + parseFloat(update["Tax"])).toFixed(2);
        }
    }
    
    console.log('update :: ',update, fields)
    dispatch(setNewProductProperty(update));
    //updateTax({...fields,...update});
  }

  const onChecked = (event)=>{
    const update = {[event.target.name]:event.target.checked}
    if(fields.TaxIncluded){
      update["FinalPrice"] = parseFloat(parseFloat(fields.Price) + parseFloat(update["Tax"])).toFixed(2);
    }
    dispatch(setNewProductProperty(update))
    //updateTax({...fields,...update});
  }
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    const update = {[name]:value}
    dispatch(setNewProductProperty(update))

    //updateTax({...fields,...update});
  }

  const getTextField = (field) =>(
      field.isResult ? (
        <Box sx={{display: 'flex'}}>
          <InputTextField
            fullWidth
            sx={{width:'110%'}}
            placeholder={field.placeholder}
            onChange={onChange}
            name={field.id}
            autoComplete={field.id}
            value={fields[field.id]}
            type={field.type}
            multiline={!!(field.multiline)}
          />
          <Box sx={{marginLeft: '35px'}}>
            <InputTextField
              fullWidth
              placeholder="Total"
              onChange={onChange}
              name={field.id+"Value"}
              value={fields[field.id+"Value"]}
              type={field.type}
            />
          </Box>
        </Box>
      ): (
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
  )

  const getCheckboxField = (field) =>(
    <CheckboxField
      name={field.id}
      color="primary"
      checked={fields[field.id]}
      onChange={onChecked}
      label={field.label}
      autoComplete={field.id}
    />
  )
  const getSelectField = (field) =>{
    if(field.id === 'HSN'){
      field.options = hsnList;
    }
    return(
    <Select
      name={field.id}
      options={field.options}
      placeholder={field.placeholder}
      isClearable
      value={field.options.filter((v) => v.value === fields[field.id])}
      onChange={onSelected}
    />
  )}

  const getField = (field) => {
    switch (field.type) {
      case 'checkbox': return getCheckboxField(field);
      case 'select': return getSelectField(field);
      default: return getTextField(field);
      }
  }

  const prepareInputFields = (fields) => fields.map((field) =>
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
  return (
    <React.Fragment>
    <Grid container spacing={3} py={2}>
      {prepareInputFields(productFields)}
      <Grid item xs={12} md={12} lg={12} xl={12}>
        <Stack spacing={3}>
      <Box sx={{ mt: 3 }}>
        <Button size="small" 
        onClick={()=>setIsOpen(!isOpen)}>
            Tax Details
        </Button>
      </Box>
      </Stack>
      </Grid>
      <Grid item xs={12} md={12} lg={12} xl={12}>
        <Stack spacing={3}>
          <Collapse in={isOpen}>
            <Grid container spacing={3} py={2}>
              {prepareInputFields(taxFields)}
            </Grid>
          </Collapse>
        </Stack>
        </Grid>
      <Grid item xs={12} md={12} lg={12} xl={12}>
        <Stack spacing={3}>
          <Typography variant='subtitle2'>
            Product Image
          </Typography>
          <ProductImage files={files} setFiles={setFiles}/>
        </Stack>
      </Grid>
    </Grid>
    <Box py={3}
      sx={{textAlign: 'right'}}
    >
      <Button
          style={{cursor:'pointer',width: '165px',}}
          variant="outlined"
          onClick={handleSave}        
      >
          Save
      </Button>
    </Box>
    </React.Fragment>
  );
}
