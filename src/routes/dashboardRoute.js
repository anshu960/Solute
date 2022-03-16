import { lazy } from 'react';
import { Redirect } from 'react-router-dom';
import DashboardLayout from '../layout/dashboard';
import { PATH_DASHBOARD, PATH_PAGE } from './path';
import ProtectedRoute from './ProtectRoute';

export const DashboardRoutes = {
    path: PATH_DASHBOARD.root,
    guard: ProtectedRoute,
    layout: DashboardLayout,
    routes: [
      {
        exact: true,
        path: PATH_DASHBOARD.fee.fee,
        component: lazy(() => import('../pages/Fee'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.sale.sale,
        component: lazy(() => import('../pages/sale/Sale'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.sale.businessSale,
        component: lazy(() => import('../pages/sale/BusinessSale'))
      },
      {
        exact: true,
        path: PATH_PAGE.profile,
        component: lazy(() => import('../pages/Profile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.product.product,
        component: lazy(() => import('../pages/product/Products'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.product.productProfile,
        component: lazy(() => import('../pages/product/ProductProfile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.product.add,
        component: lazy(() => import('../pages/product/AddEditProduct'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.room.rooms,
        component: lazy(() => import('../pages/room/Rooms'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.room.roomProfile,
        component: lazy(() => import('../pages/room/RoomProfile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.room.add,
        component: lazy(() => import('../pages/room/AddRoom'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.hsn.hsn,
        component: lazy(() => import('../pages/hsn/HSNs'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.hsn.add,
        component: lazy(() => import('../pages/hsn/AddEditHSN'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.delivery.shipments,
        component: lazy(() => import('../pages/delivery/Shipments'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.delivery.add,
        component: lazy(() => import('../pages/delivery/AddEditShipment'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.delivery.profile,
        component: lazy(() => import('../pages/delivery/Profile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.customer.add,
        component: lazy(() => import('../pages/customer/AddCustomer'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.customer.customers,
        component: lazy(() => import('../pages/customer/Customers'))
      },
      // {
      //   exact: true,
      //   path: PATH_DASHBOARD.employee.add,
      //   component: lazy(() => import('../pages/employee/AddEmployee'))
      // },
      {
        exact: true,
        path: PATH_DASHBOARD.employee.employees,
        component: lazy(() => import('../pages/employee/Employees'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.employee.profile,
        component: lazy(() => import('../pages/Profile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.employee.attendance,
        component: lazy(() => import('../pages/Attendance'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.vehicle,
        component: lazy(() => import('../pages/Vehicle'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.class.class,
        component: lazy(() => import('../pages/Class'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.teacher.teacher,
        component: lazy(() => import('../pages/Teacher'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.businessProfile,
        component: lazy(() => import('../pages/businessProfile/BusinessProfile'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.invoice.invoice,
        component: lazy(() => import('../pages/invoice/Invoice'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.sale.history,
        component: lazy(() => import('../pages/invoice/History'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.sale.receipt,
        component: lazy(() => import('../pages/sale/Receipt'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.membership.add,
        component: lazy(() => import('../pages/membership/AddMembership'))
      },
      {
        exact: true,
        path: PATH_DASHBOARD.root,
        component: () => <Redirect to="/" />
      },
    ]
  }