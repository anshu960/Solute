(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[48],{64062:function(e,t,n){"use strict";var r=n(67294),o=n(45697),a=n.n(o),l=n(99226),c=["children","title"];function i(){return i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},i.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,o=e.title,a=void 0===o?"":o,u=function(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}(e,c);return r.createElement(l.Z,i({ref:t},u),r.createElement(l.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},34973:function(e,t,n){"use strict";n.d(t,{Z:function(){return s}});var r=n(67294),o=n(45697),a=n.n(o),l=n(99226);function c(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function i(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?c(Object(n),!0).forEach((function(t){u(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):c(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function u(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function s(e){var t=e.src,n=e.color,o=void 0===n?"inherit":n,a=e.sx;return r.createElement(l.Z,{component:"span",sx:i(i(i(i(i({width:24,height:24,mask:"url(".concat(t,") no-repeat center / contain"),WebkitMask:"url(".concat(t,") no-repeat center / contain"),bgcolor:"".concat(o,".main")},"inherit"===o&&{bgcolor:"currentColor"}),"action"===o&&{bgcolor:"action.active"}),"disabled"===o&&{bgcolor:"action.disabled"}),"paper"===o&&{bgcolor:"background.paper"}),a)})}s.propTypes={src:a().string.isRequired,color:a().string,sx:a().object}},36949:function(e,t,n){"use strict";n.d(t,{S:function(){return ne},u:function(){return K}});var r=n(67294),o=n(45697),a=n.n(o),l=n(29602),c=n(41796),i=n(15725),u=n(65295),s=n(67109),m=n(2658),f=n(99226),p=n(67720),b=n(34973),d=n(76914),y=n(12981),g=n(26447),E=n(22620),v=n(87550),h=n(39660),O=n(54123),Z=n(21314),P=n(65148),j=n(56775),S=n(72162),w=n(12639),C=n(28006),A=n.n(C),x=n(75113),D=n(42694),R=n(98401);function I(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,c=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){c=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(c)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return T(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?T(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function T(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function k(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function U(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?k(Object(n),!0).forEach((function(t){M(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):k(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function M(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}L.propTypes={formik:a().object,cards:a().array,isOpen:a().bool,onOpen:a().func,onCancel:a().func};var _={option:function(e){return U({},e)},control:function(e){return U(U({},e),{},{width:"100%",fontSize:"15px","& div":{}})},menu:function(e){return U(U({},e),{},{borderRadius:0})},menuList:function(e){return U(U({},e),{},{fontSize:"18px",fontFamily:"Gilroy-Semibold"})},container:function(e){return U(U({},e),{},{width:"100% !important",marginRight:"10px","&:last-child":{marginRight:"0px"},zIndex:5})},indicatorsContainer:function(e){return U(U({},e),{},{color:"#fff",alignItems:"baseline"})}};function L(e){var t=e.customer,n=e.isOpen,o=e.onOpen,a=e.onCancel,l=e.onRefresh,c=e.setLoading,i=e.toast,s=I((0,r.useState)(!0),2),p=s[0],b=s[1],C=I((0,r.useState)({PaymentReference:"",PaymentMode:"CASH",Amount:0}),2),D=C[0],T=C[1],k=I((0,r.useState)([]),2),L=k[0],N=k[1],W=(0,r.useCallback)((function(e){console.log("handleUpdateCustomerEvent",e),e&&e.status&&"success"===e.status?N(e.Payload):console.log("Unable to find payment, please try after some time")}),[]),z=function(e){e.preventDefault(),T(U(U({},D),{},M({},e.target.name,e.target.value))),console.log("customerToEdit",JSON.stringify(D)),console.log("e.target.value",e.target.value),console.log("e.target.name",e.target.name)},F=(0,r.useCallback)((function(e){c(!1),console.log("handleUpdateCustomerEvent",e),e&&e.status&&"success"===e.status?(i("Payment Entry added"),Y(),l()):(i("Unable to add Payment Entry at the moment, Please try after some time"),console.log("Unable to find customer, please try after some time"))}),[]),Y=function(){b(!0),o()};return r.createElement(u.Z,{sx:{p:3}},r.createElement(m.Z,{variant:"overline",sx:{mb:3,display:"block",color:"text.secondary"}},"Payments"),r.createElement(f.Z,{sx:{mt:3}},r.createElement(d.Z,{size:"small",onClick:Y},"Make Payment"),r.createElement(d.Z,{style:{float:"right"},size:"small",onClick:function(){var e,n,r;b(!1),o(),p&&(e=(0,R.n5)(),n=(0,R.vI)(),(r=U({},D)).CustomerID=t._id,r.UserID=e,r.BusinessID=n,console.log("RETRIVE_CUSTOMER_PAYMENT REQUEST",r),console.log("RETRIVE_CUSTOMER_PAYMENT customer",t),(0,x.SL)(A().RETRIVE_CUSTOMER_PAYMENT,r,W))}},"Payment History")),r.createElement(y.Z,{in:n},r.createElement(f.Z,{sx:{padding:3,marginTop:3,borderRadius:1,bgcolor:"background.neutral"}},p?r.createElement("form",{onSubmit:function(){}},r.createElement(g.Z,{spacing:3},r.createElement(m.Z,{variant:"subtitle1"},"Payment Details"),r.createElement(w.ZP,{options:[{label:"CASH",value:"CASH"},{label:"RTGS",value:"RTGS"},{label:"IMPS",value:"IMPS"},{label:"UPI",value:"UPI"},{label:"CHEQUE",value:"CHEQUE"},{label:"CARD",value:"CARD"}],placeholder:"Mode",name:"PaymentMode",styles:_,onChange:function(e,t){var n=t.name,r=e&&e.value||"";T(U(U({},D),{},M({},n,r)))}}),r.createElement(E.Z,{fullWidth:!0,label:"Reference",name:"PaymentReference",value:D.PaymentReference,onChange:z}),r.createElement(E.Z,{fullWidth:!0,label:"Amount",name:"Amount",value:D.Amount,onChange:z}),r.createElement(g.Z,{direction:"row",justifyContent:"flex-end",spacing:1.5},r.createElement(d.Z,{type:"button",color:"inherit",variant:"outlined",onClick:a},"Cancel"),r.createElement(d.Z,{disabled:!1,style:{cursor:"pointer"},variant:"outlined",onClick:function(e){e.preventDefault(),c(!0);var n=(0,R.n5)(),r=(0,R.vI)(),o=U({},D);o.CustomerID=t._id,o.UserID=n,o.BusinessID=r,console.log("ADD_CUSTOMER_PAYMENT REQUEST",o),console.log("ADD_CUSTOMER_PAYMENT payment Edit",D),(0,x.SL)(A().ADD_CUSTOMER_PAYMENT,o,F)}},"Update")))):L&&L.length?r.createElement(v.Z,null,L.length?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},L[0].PaymentMode),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},L[0].PaymentReference)),r.createElement(Z.Z,null,r.createElement(P.Z,null),r.createElement(j.Z,null)),r.createElement(S.Z,null,r.createElement(m.Z,{variant:"subtitle2"},L[0].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(L[0].PaymentDate).toLocaleDateString()))):null,L.length>=2?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},L[1].PaymentReference)),r.createElement(Z.Z,null,r.createElement(P.Z,null),r.createElement(j.Z,null)),r.createElement(S.Z,null,r.createElement(m.Z,{variant:"subtitle2"},L[1].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(L[1].PaymentDate).toLocaleDateString()))):null,L.length>=3?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},L[2].PaymentReference)),r.createElement(Z.Z,null,r.createElement(P.Z,null)),r.createElement(S.Z,null,r.createElement(m.Z,{variant:"subtitle2"},L[2].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(L[2].PaymentDate).toLocaleDateString()))):null):null)))}function N(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function W(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?N(Object(n),!0).forEach((function(t){z(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):N(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function z(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function F(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,c=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){c=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(c)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return Y(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?Y(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function Y(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function H(e){var t=e.customer,n=e.isOpen,o=e.onOpen,a=e.onCancel,l=e.onRefresh,c=F((0,r.useState)(t),2),i=c[0],s=c[1],p=F((0,r.useState)(!1),2),b=p[0],v=p[1],h=(0,r.useCallback)((function(e){v(!1),console.log("handleUpdateCustomerEvent",e),e&&e.status&&"success"===e.status?l():console.log("Unable to find customer, please try after some time")}),[]),O=function(e){e.preventDefault(),s(W(W({},i),{},z({},e.target.name,e.target.value)))};return r.createElement(u.Z,{sx:{p:3}},b?r.createElement(D.Z,null):null,r.createElement(m.Z,{variant:"overline",sx:{mb:3,display:"block",color:"text.secondary"}},"Customer Information"),r.createElement(f.Z,{sx:{mt:3}},r.createElement(d.Z,{size:"small",onClick:o},"View/Edit Details")),r.createElement(y.Z,{in:n},r.createElement(f.Z,{sx:{padding:3,marginTop:3,borderRadius:1,bgcolor:"background.neutral"}},r.createElement("form",{onSubmit:function(){}},r.createElement(g.Z,{spacing:3},r.createElement(m.Z,{variant:"subtitle1"},"Customer Details"),r.createElement(E.Z,{fullWidth:!0,label:"Name",name:"Name",value:i.Name,onChange:O}),r.createElement(E.Z,{fullWidth:!0,label:"Contact",name:"MobileNumber",value:i.MobileNumber,onChange:O}),r.createElement(E.Z,{fullWidth:!0,label:"What's App",name:"WhatsaApp",value:i.WhatsApp,onChange:O}),r.createElement(E.Z,{fullWidth:!0,label:"Email",name:"EmailID",value:i.EmailID,onChange:O}),r.createElement(E.Z,{fullWidth:!0,label:"Address",name:"Address",value:i.Address,multiline:!0,minRows:2,onChange:O}),r.createElement(g.Z,{direction:"row",justifyContent:"flex-end",spacing:1.5},r.createElement(d.Z,{type:"button",color:"inherit",variant:"outlined",onClick:a},"Cancel"),r.createElement(d.Z,{disabled:!1,style:{cursor:"pointer"},variant:"outlined",onClick:function(){v(!0);var e=W({},i);console.log("UPDATE_CUSTOMER REQUEST",e),console.log("UPDATE_CUSTOMER customerToEdit",i),(0,x.SL)(A().UPDATE_CUSTOMER,e,h)}},"Update")))))))}H.propTypes={formik:a().object,cards:a().array,isOpen:a().bool,onOpen:a().func,onCancel:a().func};var Q=n(45063),q=n.n(Q);function B(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function V(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?B(Object(n),!0).forEach((function(t){G(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):B(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function G(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function $(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}(0,l.ZP)("img")({top:0,width:"100%",height:"100%",objectFit:"cover",position:"absolute"});var J=(0,l.ZP)("div")((function(e){var t=e.theme;return{display:"flex",position:"relative",justifyContent:"center",paddingTop:"calc(100% * 9 / 24)","&:before":{top:0,zIndex:9,content:"''",width:"100%",height:"100%",position:"absolute",backdropFilter:"blur(3px)",WebkitBackdropFilter:"blur(3px)",borderTopLeftRadius:t.shape.borderRadiusMd,borderTopRightRadius:t.shape.borderRadiusMd,backgroundColor:(0,c.Fq)(t.palette.primary.darker,.72)}}}));function K(e){var t,n,o=e.customer,a=(e.index,e.onRefresh),l=e.setLoading,c=e.toast,d=(t=(0,r.useState)({customerInformation:!1,payment:!1}),n=2,function(e){if(Array.isArray(e))return e}(t)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,c=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){c=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(c)throw o}}return a}}(t,n)||function(e,t){if(e){if("string"==typeof e)return $(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?$(e,t):void 0}}(t,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),y=d[0],g=d[1],E=o.Name;return o.MobileNumber,o.WhatsApp,o.EmailID,o.Address,r.createElement(r.Fragment,null,r.createElement(i.ZP,{item:!0,xs:12,sm:6,md:4},r.createElement(u.Z,null,r.createElement(J,null,r.createElement(b.Z,{color:"paper",src:q().Logo,sx:{width:144,height:62,zIndex:10,bottom:-26,position:"absolute"}}),r.createElement(s.Z,{alt:E,sx:{width:64,height:64,zIndex:11,position:"absolute",left:"40%",transform:"translateY(-50%)"}})),r.createElement(m.Z,{variant:"subtitle1",align:"center",sx:{mt:6}},E),r.createElement(f.Z,{sx:{textAlign:"center",mt:2,mb:2.5}}),r.createElement(p.Z,null),r.createElement(H,{customer:o,isOpen:y.customerInformation,onOpen:function(){return g(V(V({},y),{},{customerInformation:!y.customerInformation}))},onCancel:function(){return g(V(V({},y),{},{customerInformation:!1}))},onRefresh:a,setLoading:l,toast:c}),r.createElement(p.Z,null),r.createElement(L,{customer:o,isOpen:y.payment,onOpen:function(){return g(V(V({},y),{},{payment:!y.payment}))},onCancel:function(){return g(V(V({},y),{},{payment:!1}))},onRefresh:a,setLoading:l,toast:c}))))}K.propTypes={post:a().object.isRequired,index:a().number};var X=n(4431),ee=n(5977),te=n(96910);function ne(){var e=(0,ee.k6)();return r.createElement(d.Z,{variant:"outlined",onClick:function(){return e.push({pathname:te.vB.customer.add})},startIcon:r.createElement(X.Z,null)},"Add Customer")}},45063:function(e){e.exports=Object.freeze({Indian_Oil_Logo:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2FIndian_Oil_Logo.png?alt=media&token=2d64356b-9f1f-471b-b37b-0c61ab7caae2",Favicon:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Ffavicon.png?alt=media&token=f6b83bef-b0a2-4198-a8b6-a1b180308c51",Logo:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fsolute.jpeg?alt=media&token=5c25200b-fb73-4522-b557-68a9575142bf"})}}]);