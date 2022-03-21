export const productFields = [
    {
      id: 'Available',
      label: 'Available',
      type: 'checkbox',
      placeholder: 'Select Available',
    },
    {
      id: 'Number',
      label: 'Room Number',
      type: 'text',
      placeholder: 'Enter Room Number'
    },
    {
      id: 'Name',
      label: 'Room Name',
      type: 'text',
      placeholder: 'Enter Room Name'
    },
    {
      id: 'Bed',
      label: 'Extra Bed',
      type: 'select',
      placeholder: 'Select Extra Bed',
      options: [{label:'1',value: 1},{label:'2',value: 2},{label:'3',value: 3}]
    },
    {
      id: 'Description',
      label: 'Description',
      type: 'text',
      placeholder: 'Enter Description',
      multiline: true,
    },
    {
      id: 'MRP',
      label: 'MRP',
      type: 'Number',
      placeholder: 'Enter MRP'
    },
    {
      id: 'CostPrice',
      label: 'Cost Price/Price you paid to buy (Will not show anywhere)',
      type: 'Number',
      placeholder: 'Enter Cost Price'
    },
    {
      id: 'Price',
      label: 'Price',
      type: 'Number',
      placeholder: 'Enter Price'
    },
    {
      id: 'FinalPrice',
      label: 'Final Price',
      type: 'Number',
      placeholder: 'Final Price'
    },
    {
      id: 'Reserved',
      label: 'Reserved',
      type: 'checkbox',
      placeholder: 'Select Reserved',
    },
    {
      id: 'CheckIn',
      label: 'Check In',
      type: 'date',
      placeholder: 'Enter Check In'
    },
    {
      id: 'CheckOut',
      label: 'Check Out',
      type: 'date',
      placeholder: 'Enter Check Out'
    },
  ]

  export const taxFields = [
    {
      id: 'TaxIncluded',
      label: 'Tax Included',
      type: 'checkbox',
      placeholder: 'Select Tax Included',
    },
    {
      id: 'HSN',
      label: 'HSN',
      type: 'select',
      placeholder: 'Select HSN',
      options: []
    },
    {
      id: 'IGST',
      label: 'IGST',
      type: 'Number',
      placeholder: 'Enter IGST Amount in %',
      isResult: true
    },
    {
      id: 'CGST',
      label: 'CGST',
      type: 'Number',
      placeholder: 'Enter CGST Amount in %',
      isResult: true
    },
    {
      id: 'SGST',
      label: 'SGST',
      type: 'Number',
      placeholder: 'Enter SGST Amount in %',
      isResult: true
    },
    {
      id: 'VAT',
      label: 'VAT',
      type: 'Number',
      placeholder: 'Enter VAT Amount in %',
      isResult: true
    },
    {
      id: 'CESS',
      label: 'CESS',
      type: 'Number',
      placeholder: 'Enter CESS Amount in %',
      isResult: true
    },
    {
      id: 'Tax',
      label: 'Total Tax Amount',
      type: 'Number',
      placeholder: 'Enter Tax'
    },
  ]
  
  export const AmenitiesFields = [
    {
      id: 'RestRoom',
      label: 'Rest Room',
      type: 'select',
      placeholder: 'Select Rest Room',
      options: [{label:'Western',value: 1},{label:'Indian',value: 2},{label:'Both',value: 3}]
    },
    {
      id: 'RestRoomAttached',
      label: 'Rest Room Attached',
      type: 'checkbox',
      placeholder: 'Select Rest Room Attached',
    },
    {
      id: 'Hall',
      label: 'Hall',
      type: 'checkbox',
      placeholder: 'Select Hall',
    },
    {
      id: 'Wifi',
      label: 'Wifi',
      type: 'checkbox',
      placeholder: 'Select Wifi',
    },
    {
      id: 'Balcony',
      label: 'Balcony',
      type: 'checkbox',
      placeholder: 'Select Balcony',
    },
    {
      id: 'AC',
      label: 'AC',
      type: 'checkbox',
      placeholder: 'Select AC',
    },
    {
      id: 'TV',
      label: 'TV',
      type: 'checkbox',
      placeholder: 'Select TV',
    },
    {
      id: 'Fridge',
      label: 'Fridge',
      type: 'checkbox',
      placeholder: 'Select Fridge',
    },
  ]