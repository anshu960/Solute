import React, { useEffect, useState } from 'react';
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


  const onChange = (event)=>{
    const update = {[event.target.name]:event.target.value}
    dispatch(setNewProductProperty(update));
    updateTax({...fields,...update});
  }

  const onChecked = (event)=>{
    const update = {[event.target.name]:event.target.checked}
    dispatch(setNewProductProperty(update))
    updateTax({...fields,...update});
  }
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    const update = {[name]:value}
    dispatch(setNewProductProperty(update))
    updateTax({...fields,...update});
  }


  const updateTax = (product)=>{
    let newProductToUpdate = {...product}
    let basePrice = parseFloat(product.Price)
    let allAppliedTaxAmount = parseFloat(0)
    let hsn = {}
    if(product.HSN && allHSN){
      allHSN.forEach(element => {
          if(element._id === product.HSN){
            hsn = element;
          }
      });
    }
    console.log("Selected HSN  = ",hsn);
    if(hsn.SGST){
      let percent = parseFloat(hsn.SGST)
      let percentDivider = percent/100
      let newTaxAmount = parseFloat(basePrice * percentDivider)
      newProductToUpdate.SGST = newTaxAmount.toFixed(2);
      allAppliedTaxAmount = parseFloat(allAppliedTaxAmount + newTaxAmount)
    }
    if(hsn.CGST){
      let percent = parseFloat(hsn.CGST)
      let percentDivider = percent/100
      let newTaxAmount = parseFloat(basePrice * percentDivider)
      newProductToUpdate.CGST = newTaxAmount.toFixed(2);
      allAppliedTaxAmount = parseFloat(allAppliedTaxAmount + newTaxAmount)
    }

    if(hsn.IGST){
      let percent = parseFloat(hsn.IGST)
      let percentDivider = percent/100
      let newTaxAmount = parseFloat(basePrice * percentDivider)
      newProductToUpdate.IGST = newTaxAmount.toFixed(2);
      allAppliedTaxAmount = parseFloat(allAppliedTaxAmount + newTaxAmount)
    }

    if(hsn.CESS){
      let percent = parseFloat(hsn.CESS)
      let percentDivider = percent/100
      let newTaxAmount = parseFloat(basePrice * percentDivider)
      newProductToUpdate.CESS = newTaxAmount.toFixed(2);
      allAppliedTaxAmount = parseFloat(allAppliedTaxAmount + newTaxAmount)
    }

    if(hsn.VAT){
      let percent = parseFloat(hsn.VAT)
      let percentDivider = percent/100
      let newTaxAmount = parseFloat(basePrice * percentDivider)
      newProductToUpdate.VAT = newTaxAmount.toFixed(2);
      allAppliedTaxAmount = parseFloat(allAppliedTaxAmount + newTaxAmount)
    }
    console.log("allAppliedTaxAmount = ",allAppliedTaxAmount)


    console.log("updateTax Product = ",product);
      let taxAmount = parseFloat(allAppliedTaxAmount.toFixed(2));
      let productPrice = parseFloat(product.Price);
      let finalPrice = parseFloat(productPrice + taxAmount).toFixed(2);
      console.log("productPrice = ",productPrice);
      console.log("taxAmount = ",taxAmount);
      console.log("finalPrice = ",finalPrice);
     if(product.Price){
        if(product.TaxIncluded){
          dispatch(setNewProductProperty({
            Tax:product.HSN ? taxAmount : product.Tax,
            FinalPrice:productPrice
          }))
        }else{
          dispatch(setNewProductProperty({Tax:product.HSN ? taxAmount : product.Tax 
            ,FinalPrice: product.HSN ? finalPrice : parseFloat(product.Tax) + parseFloat(finalPrice),
            IGST:newProductToUpdate.IGST,
            CGST:newProductToUpdate.CGST,
            SGST:newProductToUpdate.SGST,
            VAT:newProductToUpdate.VAT,
            CESS:newProductToUpdate.CESS,
          }))
        }
      }
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
        {prepareInputFields(taxFields)}
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
