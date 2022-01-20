import * as React from 'react';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import PersonAddOutlinedIcon from '@mui/icons-material/PersonAddOutlined';
import BusinessCenterOutlinedIcon from '@mui/icons-material/BusinessCenterOutlined';
import ReceiptRoundedIcon from '@mui/icons-material/ReceiptRounded';
import FactCheckRoundedIcon from '@mui/icons-material/FactCheckRounded';
import PersonAddAltIcon from '@mui/icons-material/PersonAddAlt';
import DirectionsCarFilledOutlinedIcon from '@mui/icons-material/DirectionsCarFilledOutlined';
import ClassIcon from '@mui/icons-material/Class';
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import PaidIcon from '@mui/icons-material/Paid';
import { PATH_DASHBOARD } from '../../routes/path';
// ----------------------------------------------------------------------

const EducationConfig = {
  isMultiMenu: true,
  menuConfig:[
    {
      //subheader: 'sale management',
      items: [{
        title: 'fee',
        icon: <PaidIcon />,
        path: PATH_DASHBOARD.fee.root,
        items:[
          {
            title: 'fee',
            path: PATH_DASHBOARD.fee.fee,
          },
        ]
      }
      ]
    },
  {
    title: 'invoice',
    path: PATH_DASHBOARD.invoice,
    icon: <ReceiptRoundedIcon />
  },
  {
    title: 'customer',
    path: PATH_DASHBOARD.customer.customers,
    icon: <PersonAddOutlinedIcon />
  },
  {
    //subheader: 'employee',
    items: [
      {
        title: 'employee',
        icon: <PersonAddAltIcon />,
        path: PATH_DASHBOARD.employee.root,
        items: [
          {
            title: 'employees',
            path: PATH_DASHBOARD.employee.employees,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.employee.add,
          },
          {
            title: 'attendance',
            path: PATH_DASHBOARD.employee.attendance,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'employee',
    items: [
      {
        title: 'class',
        icon: <ClassIcon />,
        path: PATH_DASHBOARD.class.root,
        items: [
          {
            title: 'class',
            path: PATH_DASHBOARD.class.class,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.class.add,
          },
          {
            title: 'attendance',
            path: PATH_DASHBOARD.class.attendance,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'employee',
    items: [
      {
        title: 'teacher',
        icon: <AccountBoxIcon />,
        path: PATH_DASHBOARD.teacher.root,
        items: [
          {
            title: 'teacher',
            path: PATH_DASHBOARD.teacher.teacher,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.teacher.add,
          },
          {
            title: 'attendance',
            path: PATH_DASHBOARD.teacher.attendance,
          }
        ]
      },
    ]
  }
]
};

export default EducationConfig;
