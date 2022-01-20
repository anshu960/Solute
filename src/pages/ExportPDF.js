import * as React from 'react';
import {
 Button,
} from '@mui/material';
import { createStyles, makeStyles } from '@mui/styles';
import BusinessProviderLogo from '../images/Indian_Oil_Logo.png';
import { SendEvent } from '../socket/SocketHandler';
import SocketEvent from '../socket/SocketEvent';
import {useCallback,useState} from 'react';
import { getBusiness, getBusinessId, getUserId } from '../services/authService';

const useStyles = makeStyles((theme)=>createStyles({
  addNewGroupBulk: {
    width: '165px',
    [theme.breakpoints.between('1024', '1400')]: {
        marginLeft: '0px',
    },
  },
}))

function ExportPDF({ refsToPrint,sales ,totalAmount, customer, invoiceDate, startDate, endDate ,setLoading}) {
    const classes = useStyles();
    const deepCloneWithStyles = (node) => {
        const style = document.defaultView.getComputedStyle(node, null);
        const clone = node.cloneNode(false);
        if (clone.style && style.cssText) clone.style.cssText = style.cssText;
        for (let child of node.childNodes) {
        if (child.nodeType === 1) clone.appendChild(deepCloneWithStyles(child));
        else clone.appendChild(child.cloneNode(false));
        }
        return clone;
    };

    const generateInvoice=()=>{
      setLoading(true);
      global.setLoading = setLoading;
      global.selectedCustomer = customer;
      const Business = getBusiness();
      const UserID = getUserId();
      const BusinessID = getBusinessId();
      var SalesID = [];
      sales.forEach(item => {
        SalesID.push(item._id);
      });
      let request = {UserID,BusinessID,SalesID,Sales:sales,Business:Business,TotalAmount:totalAmount,Customer:customer}
      SendEvent(SocketEvent.GENERATE_CUSTOMER_INVOICE,request,handleGenerateInvoiceEvent)
      
    }
    const handleGenerateInvoiceEvent = useCallback((data) => {
      console.log("handleGenerateInvoiceEvent",data);
      if(data && data.Payload && data.Payload._id){
        printInvoice(data.Payload);
      }else{
        setLoading(false);
        console.log("Unable to find products, please try after some time",data);
      }
    }, []);
    const getDateToDisplay=(dateStr)=>{
      const dateObj = new Date(dateStr);
      return (dateObj.getDate() + "-" + (dateObj.getMonth() + 1) + "-" + dateObj.getFullYear());
  }
  const printInvoice = (invoice) => {
    console.log("invoice",invoice);
    let Business = getBusiness();
    const printWindow = window.open("", "", "height=400,width=800");
    printWindow.document.title = 'invoice_'+invoice.InvoiceNumber;
    printWindow.document.write(
      `<html><head><title>${"Invoice Number " + invoice.InvoiceNumber}</title></head><body  style="border: 1px solid #000; text-align: center" id='print-body'>
        <div style="display: flex; justify-content: space-around; margin: 4px 0px">
          <div>
          <img src=${BusinessProviderLogo} style="width="100px" height="100px" />
          </div>
          <div>
            <div><b style="font-size:30px">${Business.Name}</b></div>
            <div>${Business.Address}</div>
          </div>
          <div>
          <img src=${BusinessProviderLogo} style="width="100px" height="100px" />
          </div>
        </div>

        <div style="display: flex; justify-content: space-between; border-bottom: 1px solid; margin: 4px 0px">
          <div style="text-align: left; margin-left: 14px;">
            <div><b>GST NO : ${Business.GSTNumber}</b></div>
          </div>
          <div style="text-align: left; margin-right: 50px;">
            <div><b>MOB NO :${Business.MobileNumber}</b></div>
          </div>
        </div>
        
        <div style="display: flex; justify-content: space-between; border-bottom: 1px solid; margin: 4px 0px">
          <div style="text-align: left; margin-left: 10px;">
            <div><b>TO :</b> ${global.selectedCustomer.Name}</div>
            <div><b>Bill No :</b> ${invoice ? invoice.InvoiceNumber : 0}</div>
          </div>
          <div style="text-align: left; margin-right: 10px;">
            <div><b>DATE :</b> ${startDate} To ${endDate} </div>
            <div><b>Bill date :</b> ${getDateToDisplay(invoice.InvoiceDate)} </div>
          </div>
        </div>
      `
    );
    const body = printWindow.document.getElementById("print-body");
    refsToPrint.map((ref) => {
      const clone = deepCloneWithStyles(ref.current);
      return body.appendChild(clone);
    });
    printWindow.document.write(`
      <table style="bottom:5px; position:absolute; width:100%; border-top: 1px solid;">
        <tr>
          <td style="text-align: right;  padding:4px 30px;">
            Sub Total: ${invoice.TotalAmount > 0 ? invoice.TotalAmount : 0}
          </td>
        </tr>
        <tr>
          <td style="text-align: right; padding:4px 30px;">
            Cleared: ${invoice.ClearedAmount}
          </td>
        </tr>
        <tr>
          <td style="text-align: right; padding:4px 30px;">
            Total: ${invoice.PendingAmount}
          </td>
        </tr>
      </table>
    `);
    printWindow.document.write("</body></html>");
    printWindow.document.close();
    setTimeout(()=>{
      global.setLoading(false);
      printWindow.print();
    }
    ,1000);
  };

  return (
  <Button
        className={classes.addNewGroupBulk}
        variant="outlined"
        onClick={generateInvoice}
    >
        Print
    </Button>)
}

export default ExportPDF;
