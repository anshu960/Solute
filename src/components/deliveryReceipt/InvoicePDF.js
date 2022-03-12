import React from 'react'
import PropTypes from 'prop-types';
import { Page, View, Text, Font, Image, Document, StyleSheet, Svg } from '@react-pdf/renderer';
//
//import BusinessProviderLogo from '../../images/Indian_Oil_Logo.png';
import image_config from '../../config/image_config';
import QRCode from "qrcode";

// ----------------------------------------------------------------------

// Font.register({
//   family: 'Roboto',
//   fonts: [{ src: '/fonts/Roboto-Regular.ttf' }, { src: '/fonts/Roboto-Bold.ttf' }]
// });

const styles = StyleSheet.create({
  col4: { width: '25%' },
  col8: { width: '75%' },
  col6: { width: '50%' },
  mb8: { marginBottom: 8 },
  mb40: { marginBottom: 40 },
  overline: {
    fontSize: 8,
    marginBottom: 8,
    fontWeight: 700,
    letterSpacing: 1.2,
    textTransform: 'uppercase'
  },
  h3: { fontSize: 16, fontWeight: 700 },
  h4: { fontSize: 13, fontWeight: 700 },
  body1: { fontSize: 10 },
  subtitle2: { fontSize: 9, fontWeight: 700 },
  alignRight: { textAlign: 'right' },
  page: {
    padding: '40px 24px 0 24px',
    fontSize: 9,
    lineHeight: 1.6,
    //fontFamily: 'Roboto',
    backgroundColor: '#fff',
    textTransform: 'capitalize'
  },
  footer: {
    left: 0,
    right: 0,
    bottom: 0,
    padding: 24,
    margin: 'auto',
    borderTopWidth: 1,
    borderStyle: 'solid',
    position: 'absolute',
    borderColor: '#DFE3E8'
  },
  gridContainer: { flexDirection: 'row', justifyContent: 'space-between' },
  table: { display: 'flex', width: 'auto' },
  tableHeader: {},
  tableBody: {},
  tableRow: {
    padding: '8px 0',
    flexDirection: 'row',
    borderBottomWidth: 1,
    borderStyle: 'solid',
    borderColor: '#DFE3E8'
  },
  noBorder: { paddingTop: 8, paddingBottom: 0, borderBottomWidth: 0 },
  tableCell_1: { width: '5%' },
  tableCell_2: { width: '50%', paddingRight: 16 },
  tableCell_3: { width: '15%' }
});

// ----------------------------------------------------------------------

export default function InvoicePDF({saleData})  {
  let canvas;
  // For QR Code
  canvas = document.createElement('canvas');
  QRCode.toCanvas(canvas, '123');
  const qr = canvas.toDataURL();
  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={[styles.gridContainer, styles.mb40]}>
          <View>
          <Image
            src={qr}
            style={{ height: 60, }} />
          <Text style={styles.noBorder}>{"Invoice No: 123"}</Text>
          </View>
            <View style={styles.col4}>
              <Text style={styles.body1}>{saleData.business.Name}</Text>
              <Text style={styles.body1}>{saleData.business.Address}</Text>
              <Text style={styles.body1}>{saleData.business.MobileNumber}</Text>
            </View>
            {saleData.Customer && saleData.Customer.Name &&
            <View style={styles.col4}>
            <Text style={[styles.overline, styles.mb8]}>Invoice to</Text>
            <Text style={styles.body1}>{saleData.Customer.Name}</Text>
            <Text style={styles.body1}>{saleData.Customer.MobileNumber || ""}</Text>
            <Text style={styles.body1}>{saleData.Customer.Address || "" }</Text>
          </View>
            }
          </View>
        <Text style={[styles.overline, styles.mb8]}>Delivery Details</Text>
        <View style={styles.table}>
          <View style={styles.tableHeader}>
            <View style={styles.tableRow}>
              <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>Sender's</Text>
              </View>
              <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>Receiver's</Text>
              </View>
            </View>
          </View>

          <View style={styles.tableBody}>
                {(<View style={styles.tableRow}> 
                <View style={styles.tableCell_2}>
                  <Text style={styles.subtitle2}>{'Name : '}</Text>
                  <Text style={styles.subtitle2}>{'Contact : '}</Text>
                  <Text style={styles.subtitle2}>{'Address : '}</Text>
                  <Text style={styles.subtitle2}>{'Contents : '}</Text>
                  <Text style={styles.subtitle2}>{'Volumentric Weight : '}</Text>
                </View>
                <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>{'Name : '}</Text>
                  <Text style={styles.subtitle2}>{'Contact : '}</Text>
                  <Text style={styles.subtitle2}>{'Address : '}</Text>
                  <Text style={styles.subtitle2}>{'Mode : AIR | SF'}</Text>
                  <Text style={styles.subtitle2}>{'Declared Value : '}</Text>
                </View>
              </View>)
              }
              <View style={styles.table}>
          <View style={styles.tableHeader}>
            <View style={styles.tableRow}>
              <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>RECEIVED IN GOOD CONDITION</Text>
              </View>
              <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>CONSIGNOR COPY</Text>
              </View>
            </View>
          </View>
                {(<View style={styles.tableRow}> 
                <View style={styles.tableCell_2}>
                  <Text style={styles.subtitle2}>{'Sign : '}</Text>
                  <Text style={styles.subtitle2}>{'Name : '}</Text>
                  <Text style={styles.subtitle2}>{'Relation : '}</Text>
                  <Text style={styles.subtitle2}>{'Mobile No : '}</Text>
                </View>
                <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>{'Date : '}</Text>
                  <Text style={styles.subtitle2}>{'Sign : '}</Text>
                  <Text style={styles.subtitle2}>{'Seal : '}</Text>
                  <Text style={styles.subtitle2}>{'Place : '}</Text>
                </View>
              </View>)
              }
              </View>
            <View style={[styles.tableRow, styles.noBorder]}>
              <View>
                <Text >We declare that this consignment does not contain personal mail, cash, jewellery, contraband, illegal drugs,
                  any prohibited items and commodities which can cause safety hazards while transported by air and surface
                  Non Negotible Consignment Not/Subject to Varanasi Jurisdiction. Please refer to all the terms & conditions
                  printed overleaf of this Consignment note.
                  This is a non negotiable note subject to standard condition of carriage.Carrier's liability is limited to Rs. 100/-
                  per Consignment for any cause. If not covered by risk surcharge, claim value on this shipper shall in no
                  circumstancesexceed Rs. 100/- (One hundred only) for parcels and documents.
                  Read the terms & Condition overleaf. I declare that this Consignmentdontain any contraband, cash, jewelry etc.
                </Text>
              </View>
            </View>
            <View style={[styles.tableRow, styles.noBorder]}>
              <View style={styles.tableCell_1} />
              <View style={styles.tableCell_2} />
              <View style={styles.tableCell_3} />
              <View style={styles.tableCell_3} >
              <Text >Sender's Sign : </Text>
                </View>
              <View style={[styles.tableCell_3, styles.alignRight]}>
              <Text>{saleData.invoice.FinalPrice}</Text>
              </View>
            </View>
          </View>
        </View>

        <View style={[styles.gridContainer, styles.footer]}>
          <View style={styles.col8}>
            <Text style={styles.subtitle2}>NOTES</Text>
            <Text>We appreciate your business.</Text>
          </View>
          <View style={[styles.col8, styles.alignRight]}>
            <Text style={styles.subtitle2}>Have a Question?</Text>
            <Text>support@parmartechnologies.com</Text>
          </View>
        </View>
      </Page>
    </Document>
  );
}