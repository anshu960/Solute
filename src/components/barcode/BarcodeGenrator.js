import React, { useEffect } from "react";
import JsBarcode from "jsbarcode";

const BarcodeGenrator = ({barcode}) => {
    useEffect(()=>{
        JsBarcode('.barcode').init();
    },[])
    console.log(barcode)
    return(
        <svg
            class="barcode"
            jsbarcode-format="CODE128"
            jsbarcode-value={barcode}
            jsbarcode-textmargin="0"
            jsbarcode-fontoptions="bold"
        />
    )
}

export default BarcodeGenrator;