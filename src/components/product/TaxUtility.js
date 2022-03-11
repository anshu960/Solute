export const getTax = (product, hsn)=>{
    let newProductToUpdate = {...product}
    let basePrice = parseFloat(product.Price)
    let allAppliedTaxAmount = parseFloat(0)
    
    console.log("Selected HSN  = ",hsn);
    let taxKey = '';
    if(hsn.SGST){
        taxKey = 'SGST';
    }
    else if(hsn.CGST){
        taxKey = 'CGST';
    }
    else if(hsn.IGST){
        taxKey = 'IGST';
    }
    else if(hsn.CESS){
        taxKey = 'CESS';
    }
    else if(hsn.VAT){
        taxKey = 'VAT';
    }

    if(taxKey){
        let percent = parseFloat(hsn[taxKey])
        let percentDivider = percent/100;
        let newTaxAmount = parseFloat(basePrice * percentDivider)
        newProductToUpdate[taxKey+"Result"] = newTaxAmount.toFixed(2);
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
            return ({
                Tax:product.HSN ? taxAmount : product.Tax,
                FinalPrice:productPrice
            });
        }else{
            return({
                Tax:product.HSN ? taxAmount : product.Tax,
                FinalPrice: product.HSN ? finalPrice : parseFloat(product.Tax) + parseFloat(finalPrice),
                IGST:newProductToUpdate.IGST,
                CGST:newProductToUpdate.CGST,
                SGST:newProductToUpdate.SGST,
                VAT:newProductToUpdate.VAT,
                CESS:newProductToUpdate.CESS,
            })
        }
    }
  }

  export const calculateTax = (name, value, basePrice) => parseFloat((parseFloat(value)/100) * basePrice).toFixed(2);

  export const getAppliedTax = (params,fields,name) => {
    let value = 0;
    console.log(params, name)
    params.forEach((param)=>{
        if(param !== name){
            value += fields[param+"Value"] ? parseFloat(fields[param+"Value"]): 0;
        }
    })
    return value;
  };