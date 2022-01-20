export const productFields =  [
  {
    id: 'Name',
    label: 'Product Name',
    type: 'text',
    placeholder: 'Enter Product Name'
  },
  {
    id: 'ManageInventory',
    label: 'Manage Inventory',
    type: 'checkbox',
    placeholder: 'Select Is Inventory',
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
    placeholder: 'Enter IGST Amount'
  },
  {
    id: 'CGST',
    label: 'CGST',
    type: 'Number',
    placeholder: 'Enter CGST Amount'
  },
  {
    id: 'SGST',
    label: 'SGST',
    type: 'Number',
    placeholder: 'Enter SGST Amount'
  },
  {
    id: 'VAT',
    label: 'VAT',
    type: 'Number',
    placeholder: 'Enter VAT Amount'
  },
  {
    id: 'CESS',
    label: 'CESS',
    type: 'Number',
    placeholder: 'Enter CESS Amount'
  },
  {
    id: 'Tax',
    label: 'Total Tax Amount',
    type: 'Number',
    placeholder: 'Enter Tax'
  },
  {
    id: 'FinalPrice',
    label: 'Final Price',
    type: 'Number',
    placeholder: 'Final Price'
  },
]

  export const stockFields = [
    {
      id: 'StockIn',
      label: 'Stock In',
      type: 'text',
      placeholder: 'Enter Stock In'
    },
    {
      id: 'StockAdjustment',
      label: 'Stock Adjustment',
      type: 'text',
      placeholder: 'Enter Stock Adjustment'
    },
    {
      id: 'StockQuantity',
      label: 'Total Quantity',
      type: 'text',
      placeholder: 'Enter Total Quantity'
    },
  ]