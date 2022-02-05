  export const stopFields = [
    {
      id: 'SenderAddress',
      label: 'Sender Address',
      type: 'text',
      placeholder: 'Enter Sender Address'
    },
    {
      id: 'ReceiverAddress',
      label: 'Receiver Address',
      type: 'text',
      placeholder: 'Enter Receiver Address'
    },
    {
      id: `Status`,
      label: 'Status',
      type: 'select',
      placeholder: 'Select Status',
      options: [
        {label: "Picked", value: 1},
        {label: "In Transit", value: 2},
        {label: "Delivered", value: 3},
        {label: "Dropped", value: 4},
      
      ]
    },
  ]

  export const addButton = {
    id: 'AddStop',
    label: 'Add Stopage',
    type: 'button',
  }