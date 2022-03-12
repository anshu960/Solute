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

// InvoicePDF.propTypes = {
//   invoice: PropTypes.object.isRequired
// };

export default function InvoicePDF({saleData}) {
  let canvas;
  // For QR Code
  canvas = document.createElement('canvas');
  QRCode.toCanvas(canvas, saleData.invoice._id);
  const qr = canvas.toDataURL();
  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={[styles.gridContainer, styles.mb40]}>
          <View>
          <Image
            src={qr}
            style={{ height: 60, }} />
          <Text style={styles.noBorder}>{"Invoice No: "+saleData.invoice.InvoiceNumber}</Text>
          </View>
          {/* <View style={{ alignItems: 'right', flexDirection: 'column' }}>
            <Text style={styles.h3}>{'Paid'}</Text>
            <Text>INV-12345</Text>
          </View> */}
            <View style={styles.col4}>
              <Text style={[styles.overline, styles.mb8]}>Invoice from</Text>
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
        <Text style={[styles.overline, styles.mb8]}>Invoice Details</Text>
        <View style={styles.table}>
          <View style={styles.tableHeader}>
            <View style={styles.tableRow}>
              <View style={styles.tableCell_1}>
                <Text style={styles.subtitle2}>#</Text>
              </View>
              <View style={styles.tableCell_2}>
                <Text style={styles.subtitle2}>Product</Text>
              </View>
              <View style={styles.tableCell_3}>
                <Text style={styles.subtitle2}>Unit price</Text>
              </View>
              <View style={styles.tableCell_3}>
                <Text style={styles.subtitle2}>Tax</Text>
              </View>
              <View style={styles.tableCell_3}>
                <Text style={styles.subtitle2}>Qty</Text>
              </View>
              <View style={[styles.tableCell_3, styles.alignRight]}>
                <Text style={styles.subtitle2}>Total</Text>
              </View>
            </View>
          </View>

          <View style={styles.tableBody}>
              {saleData.sale && saleData.sale.length ? saleData.sale.map((sale,index) => (<View style={styles.tableRow}>
                <View style={styles.tableCell_1}>
                  <Text>{index+1}</Text>
                </View>
                <View style={styles.tableCell_2}>
                  <Text style={styles.subtitle2}>{sale.ProductName}</Text>
                  {/* <Text>{'Diesel'}</Text> */}
                </View>
                <View style={styles.tableCell_3}>
                  <Text>{sale.Price}</Text>
                </View>
                <View style={styles.tableCell_3}>
                  <Text>{sale.Tax}</Text>
                </View>
                <View style={styles.tableCell_3}>
                  <Text>{sale.Quantity}</Text>
                </View>
                <View style={[styles.tableCell_3, styles.alignRight]}>
                  <Text>{sale.FinalPrice}</Text>
                </View>
              </View>)): null
              }

            {/* <View style={[styles.tableRow, styles.noBorder]}>
              <View style={styles.tableCell_1} />
              <View style={styles.tableCell_2} />
              <View style={styles.tableCell_3} />
              <View style={styles.tableCell_3}>
                <Text>Subtotal</Text>
              </View>
              <View style={[styles.tableCell_3, styles.alignRight]}>
                <Text>{saleData.invoice.TotalAmount}</Text>
              </View>
            </View> */}

            {/* <View style={[styles.tableRow, styles.noBorder]}>
              <View style={styles.tableCell_1} />
              <View style={styles.tableCell_2} />
              <View style={styles.tableCell_3} />
              <View style={styles.tableCell_3}>
                <Text>Discount</Text>
              </View>
              <View style={[styles.tableCell_3, styles.alignRight]}>
                <Text>{1000}</Text>
              </View>
            </View> */}

            {/* <View style={[styles.tableRow, styles.noBorder]}>
              <View style={styles.tableCell_1} />
              <View style={styles.tableCell_2} />
              <View style={styles.tableCell_3} />
              <View style={styles.tableCell_3}>
                <Text>Taxes</Text>
              </View>
              <View style={[styles.tableCell_3, styles.alignRight]}>
                <Text>{1000}</Text>
              </View>
            </View> */}

            <View style={[styles.tableRow, styles.noBorder]}>
              <View style={styles.tableCell_1} />
              <View style={styles.tableCell_2} />
              <View style={styles.tableCell_3} />
              <View style={styles.tableCell_3}>
                <Text style={styles.h4}>Total</Text>
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
