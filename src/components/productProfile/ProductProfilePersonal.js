import React, { useEffect, useState } from 'react';
// @mui
import { Box,Button, Grid, Stack, Typography,  } from '@mui/material';
import InputTextField from '../InputTextField';
import { productFields } from './FieldConfig';
import CheckboxField from '../CheckboxField';
import { ProductImage } from './../product/ProductImage';
// ----------------------------------------------------------------------
import Select from 'react-select';
import { useDispatch, useSelector } from 'react-redux';
import { setNewProductProperty, updateProduct, updateSelectedProductProperty } from '../../store/product';
import { getUserId } from '../../services/authService';
import { syncBusinessData } from '../../store/business';

// ----------------------------------------------------------------------
const defaultFields = {
  UserID:"",
  BusinessID:"",
  ProductID:"",
  Name:"",
  Price: 0,
  PlatformCharge: 0,
  FinalPrice: 0,
  CostPrice: 0,
  Tax: 0,
  MRP: 0,
  Description: "",
  ManageInventory: false,
  HSN: "",
}

export default function ProductProfilePersonal() {
  const dispatch = useDispatch()
  const fields = useSelector(state => state.product.selectedProduct)
  const allHSN = useSelector(state => state.hsn.allHSN)
  const hsnList = (allHSN && allHSN.length) ? allHSN.map((hsn) => ({label: hsn.Name, value: hsn._id})): [];

  useEffect(()=>{
    if(allHSN && allHSN.length){

    }else{
      dispatch(syncBusinessData())
    }
  },[])

  const onChange = (event)=>{
    const update = {[event.target.name]:event.target.value}
    dispatch(updateSelectedProductProperty(update));
    updateTax({...fields,...update});
  }

  const onChecked = (event)=>{
    const update = {[event.target.name]:event.target.checked}
    dispatch(updateSelectedProductProperty(update))
    updateTax({...fields,...update});
  }
  const onSelected = (option, { name }) => {
    const value = (option && option.value) || '';
    const update = {[name]:value}
    dispatch(updateSelectedProductProperty(update))
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
  


  const handleSave = () => {
    let request = {...fields};
    if(request.ProductID){
      request.ProductID=undefined;
    }
    request.Price=request.Price ? parseFloat(request.Price): 0.00
    request.Tax=request.Tax ? parseFloat(request.Tax): 0.00
    request.CostPrice=request.CostPrice ? parseFloat(request.CostPrice): 0.00
    request.MRP=request.MRP ? parseFloat(request.MRP): 0.00
    request.FinalPrice=request.FinalPrice ? parseFloat(request.FinalPrice): request.Price
    dispatch(updateProduct(request));
    // handleAddProduct(fields)
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

  const prepareInputFields = () => productFields.map((field) =>
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
      {prepareInputFields()}
      <Grid item xs={12} md={12} lg={12} xl={12}>
      <Stack spacing={3}>
        <Typography variant='subtitle2'>
          Product Image
        </Typography>
        {/* <ProductImage files={files} setFiles={setFiles}/> */}
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
          Update
      </Button>
    </Box>
    </React.Fragment>
  );
}