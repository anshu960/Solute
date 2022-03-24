const path = (root, subLink) => `${root}${subLink}`;
const ROOTS_DASHBOARD = '/dashboard';

export const PATH_AUTH = {
    login: '/login',
    signup: '/signup',
    register: '/register',
    customerDetails: '/customerDetails',
}

export const PATH_PAGE = {
    landing: '/',
    home: "/home",
    profile: '/profile',
    receipt: '/receipt',
    deliveryReceipt: '/deliveryReceipt',
    shipmentTrack: '/shipmentTrack',
}

export const PATH_DASHBOARD = {
    root: ROOTS_DASHBOARD,
    sale: {
        root: path(ROOTS_DASHBOARD, '/sale'),
        sale: path(ROOTS_DASHBOARD, '/sale/sale'),
        businessSale: path(ROOTS_DASHBOARD, '/sale/businessSale'),
        receipt: path(ROOTS_DASHBOARD, '/sale/receipt'),
        history: path(ROOTS_DASHBOARD, '/sale/history'),
    },
    product: {
        root: path(ROOTS_DASHBOARD, '/product'),
        product: path(ROOTS_DASHBOARD, '/product/products'),
        add: path(ROOTS_DASHBOARD, '/product/add'),
        productProfile: path(ROOTS_DASHBOARD, '/product/profile'),
    },
    hsn: {
        root: path(ROOTS_DASHBOARD, '/hsn'),
        hsn: path(ROOTS_DASHBOARD, '/hsn/hsn'),
        add: path(ROOTS_DASHBOARD, '/hsn/add'),
    },
    customer: {
        root: path(ROOTS_DASHBOARD, '/customer'),
        customers: path(ROOTS_DASHBOARD, '/customer/customers'),
        //add: path(ROOTS_DASHBOARD, '/customer/add'),
    },
    tenant: {
        root: path(ROOTS_DASHBOARD, '/tenant'),
        tenants: path(ROOTS_DASHBOARD, '/tenant/tenants'),
        //add: path(ROOTS_DASHBOARD, '/customer/add'),
    },
    employee: {
        root: path(ROOTS_DASHBOARD, '/employee'),
        employees: path(ROOTS_DASHBOARD, '/employee/employees'),
        //add: path(ROOTS_DASHBOARD, '/employee/add'),
        attendance: path(ROOTS_DASHBOARD,'/employee/attendance'),
        profile: path(ROOTS_DASHBOARD,'/employee/profile'),
    },
    businessProfile: path(ROOTS_DASHBOARD, '/businessProfile'),
    invoice: {
        root: path(ROOTS_DASHBOARD, '/invoice'),
        invoice: path(ROOTS_DASHBOARD, '/invoice/invoice'),
        history: path(ROOTS_DASHBOARD, '/invoice/history'),
    },
    vehicle: path(ROOTS_DASHBOARD,'/vehicle'),
    class:{
        root: path(ROOTS_DASHBOARD,'/class'),
        class: path(ROOTS_DASHBOARD, '/class/class'),
        add: path(ROOTS_DASHBOARD, '/class/add'),
        attendance: path(ROOTS_DASHBOARD,'/class/attendance'),
    } ,
    teacher: {
        root: path(ROOTS_DASHBOARD,'/teacher'),
        teacher: path(ROOTS_DASHBOARD, '/teacher/teacher'),
        add: path(ROOTS_DASHBOARD, '/teacher/add'),
        attendance: path(ROOTS_DASHBOARD,'/teacher/attendance'),
    },
    fee:{
        root:path(ROOTS_DASHBOARD,'/fee'),
        fee: path(ROOTS_DASHBOARD,'/fee/fee'),
    },
    membership:{
        root:path(ROOTS_DASHBOARD,'/membership'),
        add:path(ROOTS_DASHBOARD,'/membership/add'),
    },
    delivery: {
        root:path(ROOTS_DASHBOARD,'/delivery'),
        add:path(ROOTS_DASHBOARD,'/delivery/add'),
        profile:path(ROOTS_DASHBOARD,'/delivery/profile'),
        shipments:path(ROOTS_DASHBOARD,'/delivery/shipments'),
    },
    hotel: {
        root:path(ROOTS_DASHBOARD,'/hotel'),
        rooms:path(ROOTS_DASHBOARD,'/hotel/rooms'),
        roomProfile: path(ROOTS_DASHBOARD, '/hotel/profile'),
    },
    flat: {
        root:path(ROOTS_DASHBOARD,'/flat'),
        rooms:path(ROOTS_DASHBOARD,'/flat/rooms'),
        roomProfile: path(ROOTS_DASHBOARD, '/flat/profile'),
    },
    pg: {
        root:path(ROOTS_DASHBOARD,'/pg'),
        rooms:path(ROOTS_DASHBOARD,'/pg/rooms'),
        roomProfile: path(ROOTS_DASHBOARD, '/pg/profile'),
    },
    bookings: {
        root:path(ROOTS_DASHBOARD,'/bookings'),
        hotel:path(ROOTS_DASHBOARD,'/bookings/hotel'),
        flat: path(ROOTS_DASHBOARD, '/bookings/flat'),
        pg: path(ROOTS_DASHBOARD, '/bookings/pg'),
        all: path(ROOTS_DASHBOARD, '/bookings/all'),
    },
    student: {
        root: path(ROOTS_DASHBOARD, '/student'),
        students: path(ROOTS_DASHBOARD, '/student/students'),
    },
}