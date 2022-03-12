import React, {Suspense, lazy, Fragment} from 'react';
import {
    Redirect, Route, Switch,
} from 'react-router-dom';

// layout
import DashboardLayout from '../layout/dashboard';
import ProtectedRoute from "./ProtectRoute";
//
//import LoadingScreen from '../components/LoadingScreen/LoadingScreen';
import AppLoader from '../components/Loader';
import AuthLayout from '../layout/AuthLayout';
import { DashboardRoutes } from './dashboardRoute';
import { PATH_AUTH, PATH_PAGE } from './path';

// const AdditionalRoutes = {
//   guard: ProtectedRoute,
//   layout: AuthLayout,
//   routes: [
//     {
//       exact: true,
//       path: '/addEditProduct',
//       component: lazy(() => import('../pages/AddEditProduct'))
//     },
//   ]
// }

const routes = [
    {
        exact: true,
        path: PATH_AUTH.login,
        component: lazy(() => import('../pages/Login'))
    },
    {
        exact: true,
        path: PATH_AUTH.signup,
        component: lazy(() => import('../pages/Register'))
    },
    {
        exact: true,
        path: PATH_AUTH.register,
        component: lazy(() => import('../pages/RegisterBusiness'))
    },
    {
        exact: true,
        path: PATH_AUTH.customerDetails,
        component: lazy(() => import('../pages/customer/CustomerDetails'))
    },
    {
        exact: true,
        path: PATH_PAGE.receipt,
        component: lazy(() => import('../pages/receipt/Receipt'))
    },
    {
        exact: true,
        path: PATH_PAGE.deliveryReceipt,
        component: lazy(() => import('../pages/deliveryReceipt/DeliveryReceipt'))
    },
    {
        exact: true,
        path: PATH_PAGE.profile,
        component: lazy(() => import('../pages/Profile'))
    },
    {
        exact: true,
        path: PATH_PAGE.shipmentTrack,
        component: lazy(() => import('../pages/delivery/Track'))
    },
    {
        exact: true,
        path: PATH_PAGE.landing,
        component: lazy(() => import('../pages/Landing'))
    },
    {
      exact: true,
      path: PATH_PAGE.home,
      component: lazy(() => import('../pages/Home'))
    },
    DashboardRoutes,
    //AdditionalRoutes
]

function RouteProgress(props) {
    return <Route {...props} />
  }

const router = (routes) => (
    <Suspense fallback={(<AppLoader />)}>
      <Switch>
        {routes.map((route, index) => {
          const Component = route.component
          const Guard = route.guard || Fragment
          const Layout = route.layout || Fragment
          return (
            <RouteProgress
              key={`routes-${index}`}
              path={route.path}
              exact={route.exact}
              render={(props) => (
                <Guard>
                  <Layout>
                    {route.routes ? (
                      router(route.routes)
                    ) : (
                      <Component {...props} />
                    )}
                  </Layout>
                </Guard>
              )}
            />
          )
        })}
      </Switch>
    </Suspense>
);
export {
  router,
  routes,
};