import * as React from 'react';
import ShoppingCartOutlinedIcon from '@mui/icons-material/ShoppingCartOutlined';
import PersonAddOutlinedIcon from '@mui/icons-material/PersonAddOutlined';
import BusinessCenterOutlinedIcon from '@mui/icons-material/BusinessCenterOutlined';
import ReceiptRoundedIcon from '@mui/icons-material/ReceiptRounded';
import FactCheckRoundedIcon from '@mui/icons-material/FactCheckRounded';
import PersonAddAltIcon from '@mui/icons-material/PersonAddAlt';
import DirectionsCarFilledOutlinedIcon from '@mui/icons-material/DirectionsCarFilledOutlined';
import CardMembershipIcon from '@mui/icons-material/CardMembership';
import LocalShippingOutlinedIcon from '@mui/icons-material/LocalShippingOutlined';
import NightShelterIcon from '@mui/icons-material/NightShelter';
import SingleBedIcon from '@mui/icons-material/SingleBed';
import BusinessIcon from '@mui/icons-material/Business';
import ApartmentIcon from '@mui/icons-material/Apartment';
import ApartmentRoundedIcon from '@mui/icons-material/ApartmentRounded';
import AddBusinessIcon from '@mui/icons-material/AddBusiness';
import PersonAddAlt1RoundedIcon from '@mui/icons-material/PersonAddAlt1Rounded';
import { PATH_DASHBOARD } from '../../routes/path';
// ----------------------------------------------------------------------

const sidebarConfig = {
  isMultiMenu: true,
  menuConfig: [
  {
    //subheader: 'sale management',
    items: [
      {
        title: 'sale',
        icon: <ShoppingCartOutlinedIcon />,
        path: PATH_DASHBOARD.sale.root,
        items: [
          {
            title: 'sale',
            path: PATH_DASHBOARD.sale.sale,
          },
          // {
          //   title: 'business sale',
          //   path: PATH_DASHBOARD.sale.businessSale,
          // },
          {
            title: 'history',
            path: PATH_DASHBOARD.sale.history,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'product management',
    items: [
      {
        title: 'product',
        icon: <BusinessCenterOutlinedIcon />,
        path: PATH_DASHBOARD.product.root,
        items: [
          {
            title: 'products',
            path: PATH_DASHBOARD.product.product,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.product.add,
          }
        ]
      },
      {
        title: 'hsn',
        path: PATH_DASHBOARD.hsn.root,
        icon: <FactCheckRoundedIcon />,
        items: [
          {
            title: 'HSNs',
            path: PATH_DASHBOARD.hsn.hsn,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.hsn.add,
          },
        ]
      },
    ]
  },
  {
    //subheader: 'customer',
    items: [
      {
        title: 'customer',
        icon: <PersonAddOutlinedIcon />,
        path: PATH_DASHBOARD.customer.root,
        items: [
          {
            title: 'customers',
            path: PATH_DASHBOARD.customer.customers,
          },
          // {
          //   title: 'add',
          //   path: PATH_DASHBOARD.customer.add,
          // },
        ]
      },
    ]
  },
  {
    //subheader: 'tenant',
    items: [
      {
        title: 'tenant',
        icon: <PersonAddAlt1RoundedIcon />,
        path: PATH_DASHBOARD.tenant.root,
        items: [
          {
            title: 'tenants',
            path: PATH_DASHBOARD.tenant.tenants,
          },
          // {
          //   title: 'add',
          //   path: PATH_DASHBOARD.customer.add,
          // },
        ]
      },
    ]
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
          // {
          //   title: 'add',
          //   path: PATH_DASHBOARD.employee.add,
          // },
          {
            title: 'attendance',
            path: PATH_DASHBOARD.employee.attendance,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'membership',
    items: [
      {
        title: 'membership',
        icon: <CardMembershipIcon />,
        path: PATH_DASHBOARD.membership.root,
        items: [
          {
            title: 'add',
            path: PATH_DASHBOARD.membership.add,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'vehicle',
    items: [
      {
        title: 'vehicle',
        path: PATH_DASHBOARD.vehicle,
        icon: <DirectionsCarFilledOutlinedIcon />,
      },
    ]
  },
  // {
  //   //subheader: 'invoice',
  //   items: [
  //     {
  //       title: 'invoice',
  //       icon: <ReceiptRoundedIcon />,
  //       path: PATH_DASHBOARD.invoice.root,
  //       items: [
  //         {
  //           title: 'invoice',
  //           path: PATH_DASHBOARD.invoice.invoice,
  //         },
  //         {
  //           title: 'history',
  //           path: PATH_DASHBOARD.invoice.history,
  //         }
  //       ]
  //     },
  //   ]
  // },
  {
    //subheader: 'delivery',
    items: [
      {
        title: 'delivery',
        icon: <LocalShippingOutlinedIcon />,
        path: PATH_DASHBOARD.delivery.root,
        items: [
          {
            title: 'shipments',
            path: PATH_DASHBOARD.delivery.shipments,
          },
          {
            title: 'add',
            path: PATH_DASHBOARD.delivery.add,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'hotel',
    items: [
      {
        title: 'hotel',
        icon: <BusinessIcon />,
        path: PATH_DASHBOARD.hotel.root,
        items: [
          {
            title: 'rooms',
            path: PATH_DASHBOARD.hotel.rooms,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'flat',
    items: [
      {
        title: 'flat',
        icon: <ApartmentIcon />,
        path: PATH_DASHBOARD.flat.root,
        items: [
          {
            title: 'rooms',
            path: PATH_DASHBOARD.flat.rooms,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'pg',
    items: [
      {
        title: 'p g',
        icon: <ApartmentRoundedIcon />,
        path: PATH_DASHBOARD.pg.root,
        items: [
          {
            title: 'rooms',
            path: PATH_DASHBOARD.pg.rooms,
          }
        ]
      },
    ]
  },
  {
    //subheader: 'bookings',
    items: [
      {
        title: 'bookings',
        icon: <AddBusinessIcon />,
        path: PATH_DASHBOARD.bookings.root,
        items: [
          {
            title: 'hotel',
            path: PATH_DASHBOARD.bookings.hotel,
          },
          {
            title: 'flat',
            path: PATH_DASHBOARD.bookings.flat,
          },
          {
            title: 'p g',
            path: PATH_DASHBOARD.bookings.pg,
          },
          {
            title: 'all bookings',
            path: PATH_DASHBOARD.bookings.all,
          },
        ]
      },
    ]
  },
  {
    //subheader: 'student',
    items: [
      {
        title: 'student',
        icon: <PersonAddAlt1RoundedIcon />,
        path: PATH_DASHBOARD.student.root,
        items: [
          {
            title: 'students',
            path: PATH_DASHBOARD.student.students,
          },
        ]
      },
    ]
  },
  {
    //subheader: 'class room',
    items: [
      {
        title: 'class room',
        icon: <PersonAddAlt1RoundedIcon />,
        path: PATH_DASHBOARD.classRoom.root,
        items: [
          {
            title: 'classes',
            path: PATH_DASHBOARD.classRoom.classes,
          },
          {
            title: 'section',
            path: PATH_DASHBOARD.classRoom.section,
          },
        ]
      },
    ]
  },
]
};

export default sidebarConfig;
