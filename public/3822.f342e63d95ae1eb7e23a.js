(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[3822],{64062:function(e,t,n){"use strict";var r=n(67294),o=n(45697),a=n.n(o),l=n(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,o=e.title,a=void 0===o?"":o,u=function(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}(e,i);return r.createElement(l.Z,c({ref:t},u),r.createElement(l.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},34973:function(e,t,n){"use strict";n.d(t,{Z:function(){return s}});var r=n(67294),o=n(45697),a=n.n(o),l=n(99226);function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function c(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){u(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function u(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function s(e){var t=e.src,n=e.color,o=void 0===n?"inherit":n,a=e.sx;return r.createElement(l.Z,{component:"span",sx:c(c(c(c(c({width:24,height:24,mask:"url(".concat(t,") no-repeat center / contain"),WebkitMask:"url(".concat(t,") no-repeat center / contain"),bgcolor:"".concat(o,".main")},"inherit"===o&&{bgcolor:"currentColor"}),"action"===o&&{bgcolor:"action.active"}),"disabled"===o&&{bgcolor:"action.disabled"}),"paper"===o&&{bgcolor:"background.paper"}),a)})}s.propTypes={src:a().string.isRequired,color:a().string,sx:a().object}},51318:function(e,t,n){"use strict";n.d(t,{R:function(){return ne},J:function(){return K}});var r=n(67294),o=n(45697),a=n.n(o),l=n(29602),i=n(41796),c=n(15725),u=n(65295),s=n(67109),m=n(2658),p=n(99226),f=n(67720),b=n(34973),y=n(76914),d=n(12981),E=n(26447),g=n(22620),v=n(87550),h=n(39660),O=n(54123),P=n(21314),Z=n(65148),j=n(56775),w=n(72162),S=n(12639),A=n(28006),x=n.n(A),D=n(75113),C=n(42694),I=n(98401);function R(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return k(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?k(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function k(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function T(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function M(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?T(Object(n),!0).forEach((function(t){_(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):T(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function _(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}U.propTypes={formik:a().object,cards:a().array,isOpen:a().bool,onOpen:a().func,onCancel:a().func};var L={option:function(e){return M({},e)},control:function(e){return M(M({},e),{},{width:"100%",fontSize:"15px","& div":{}})},menu:function(e){return M(M({},e),{},{borderRadius:0})},menuList:function(e){return M(M({},e),{},{fontSize:"18px",fontFamily:"Gilroy-Semibold"})},container:function(e){return M(M({},e),{},{width:"100% !important",marginRight:"10px","&:last-child":{marginRight:"0px"},zIndex:5})},indicatorsContainer:function(e){return M(M({},e),{},{color:"#fff",alignItems:"baseline"})}};function U(e){var t=e.employee,n=e.isOpen,o=e.onOpen,a=e.onCancel,l=e.onRefresh,i=e.setLoading,c=e.toast,s=R((0,r.useState)(!0),2),f=s[0],b=s[1],A=R((0,r.useState)({PaymentReference:"",PaymentMode:"CASH",Amount:0}),2),C=A[0],k=A[1],T=R((0,r.useState)([]),2),U=T[0],Y=T[1],N=(0,r.useCallback)((function(e){console.log("handleUpdateEmployeeEvent",e),e&&e.status&&"success"===e.status?Y(e.Payload):console.log("Unable to find payment, please try after some time")}),[]),W=function(e){e.preventDefault(),k(M(M({},C),{},_({},e.target.name,e.target.value))),console.log("employeeToEdit",JSON.stringify(C)),console.log("e.target.value",e.target.value),console.log("e.target.name",e.target.name)},z=(0,r.useCallback)((function(e){i(!1),console.log("handleUpdateEmployeeEvent",e),e&&e.status&&"success"===e.status?(c("Payment Entry added"),F(),l()):(c("Unable to add Payment Entry at the moment, Please try after some time"),console.log("Unable to find employee, please try after some time"))}),[]),F=function(){b(!0),o()};return r.createElement(u.Z,{sx:{p:3}},r.createElement(m.Z,{variant:"overline",sx:{mb:3,display:"block",color:"text.secondary"}},"Payments"),r.createElement(p.Z,{sx:{mt:3}},r.createElement(y.Z,{size:"small",onClick:F},"Make Payment"),r.createElement(y.Z,{style:{float:"right"},size:"small",onClick:function(){var e,n,r;b(!1),o(),f&&(e=(0,I.n5)(),n=(0,I.vI)(),(r=M({},C)).EmployeeID=t._id,r.UserID=e,r.BusinessID=n,console.log("RETRIVE_EMPLOYEE_PAYMENT REQUEST",r),console.log("RETRIVE_EMPLOYEE_PAYMENT employee",t),(0,D.SL)(x().RETRIVE_EMPLOYEE_PAYMENT,r,N))}},"Payment History")),r.createElement(d.Z,{in:n},r.createElement(p.Z,{sx:{padding:3,marginTop:3,borderRadius:1,bgcolor:"background.neutral"}},f?r.createElement("form",{onSubmit:function(){}},r.createElement(E.Z,{spacing:3},r.createElement(m.Z,{variant:"subtitle1"},"Payment Details"),r.createElement(S.ZP,{options:[{label:"CASH",value:"CASH"},{label:"RTGS",value:"RTGS"},{label:"IMPS",value:"IMPS"},{label:"UPI",value:"UPI"},{label:"CHEQUE",value:"CHEQUE"},{label:"CARD",value:"CARD"}],placeholder:"Mode",name:"PaymentMode",styles:L,onChange:function(e,t){var n=t.name,r=e&&e.value||"";k(M(M({},C),{},_({},n,r)))}}),r.createElement(g.Z,{fullWidth:!0,label:"Reference",name:"PaymentReference",value:C.PaymentReference,onChange:W}),r.createElement(g.Z,{fullWidth:!0,label:"Amount",name:"Amount",value:C.Amount,onChange:W}),r.createElement(E.Z,{direction:"row",justifyContent:"flex-end",spacing:1.5},r.createElement(y.Z,{type:"button",color:"inherit",variant:"outlined",onClick:a},"Cancel"),r.createElement(y.Z,{disabled:!1,style:{cursor:"pointer"},variant:"outlined",onClick:function(e){e.preventDefault(),i(!0);var n=(0,I.n5)(),r=(0,I.vI)(),o=M({},C);o.EmployeeID=t._id,o.UserID=n,o.BusinessID=r,console.log("ADD_EMPLOYEE_PAYMENT REQUEST",o),console.log("ADD_EMPLOYEE_PAYMENT payment Edit",C),(0,D.SL)(x().ADD_EMPLOYEE_PAYMENT,o,z)}},"Update")))):U&&U.length?r.createElement(v.Z,null,U.length?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},U[0].PaymentMode),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},U[0].PaymentReference)),r.createElement(P.Z,null,r.createElement(Z.Z,null),r.createElement(j.Z,null)),r.createElement(w.Z,null,r.createElement(m.Z,{variant:"subtitle2"},U[0].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(U[0].PaymentDate).toLocaleDateString()))):null,U.length>=2?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},U[1].PaymentReference)),r.createElement(P.Z,null,r.createElement(Z.Z,null),r.createElement(j.Z,null)),r.createElement(w.Z,null,r.createElement(m.Z,{variant:"subtitle2"},U[1].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(U[1].PaymentDate).toLocaleDateString()))):null,U.length>=3?r.createElement(h.Z,null,r.createElement(O.Z,{color:"text.secondary"},r.createElement(m.Z,{variant:"subtitle2"},U[2].PaymentReference)),r.createElement(P.Z,null,r.createElement(Z.Z,null)),r.createElement(w.Z,null,r.createElement(m.Z,{variant:"subtitle2"},U[2].Amount),r.createElement(m.Z,{variant:"caption",sx:{color:"text.secondary"}},new Date(U[2].PaymentDate).toLocaleDateString()))):null):null)))}function Y(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function N(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?Y(Object(n),!0).forEach((function(t){W(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):Y(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function W(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function z(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return F(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?F(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function F(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function H(e){var t=e.employee,n=e.isOpen,o=e.onOpen,a=e.onCancel,l=e.onRefresh,i=z((0,r.useState)(t),2),c=i[0],s=i[1],f=z((0,r.useState)(!1),2),b=f[0],v=f[1],h=(0,r.useCallback)((function(e){v(!1),console.log("handleUpdateEmployeeEvent",e),e&&e.status&&"success"===e.status?l():console.log("Unable to find employee, please try after some time")}),[]),O=function(e){e.preventDefault(),s(N(N({},c),{},W({},e.target.name,e.target.value)))};return r.createElement(u.Z,{sx:{p:3}},b?r.createElement(C.Z,null):null,r.createElement(m.Z,{variant:"overline",sx:{mb:3,display:"block",color:"text.secondary"}},"Employee Information"),r.createElement(p.Z,{sx:{mt:3}},r.createElement(y.Z,{size:"small",onClick:o},"View/Edit Details")),r.createElement(d.Z,{in:n},r.createElement(p.Z,{sx:{padding:3,marginTop:3,borderRadius:1,bgcolor:"background.neutral"}},r.createElement("form",{onSubmit:function(){}},r.createElement(E.Z,{spacing:3},r.createElement(m.Z,{variant:"subtitle1"},"Employee Details"),r.createElement(g.Z,{fullWidth:!0,label:"Name",name:"Name",value:c.Name,onChange:O}),r.createElement(g.Z,{fullWidth:!0,label:"Contact",name:"MobileNumber",value:c.MobileNumber,onChange:O}),r.createElement(g.Z,{fullWidth:!0,label:"What's App",name:"WhatsaApp",value:c.WhatsApp,onChange:O}),r.createElement(g.Z,{fullWidth:!0,label:"Email",name:"EmailID",value:c.EmailID,onChange:O}),r.createElement(g.Z,{fullWidth:!0,label:"Address",name:"Address",value:c.Address,multiline:!0,minRows:2,onChange:O}),r.createElement(E.Z,{direction:"row",justifyContent:"flex-end",spacing:1.5},r.createElement(y.Z,{type:"button",color:"inherit",variant:"outlined",onClick:a},"Cancel"),r.createElement(y.Z,{disabled:!1,style:{cursor:"pointer"},variant:"outlined",onClick:function(){v(!0);var e=N({},c);console.log("UPDATE_EMPLOYEE REQUEST",e),console.log("UPDATE_EMPLOYEE employeeToEdit",c),(0,D.SL)(x().UPDATE_EMPLOYEE,e,h)}},"Update")))))))}H.propTypes={formik:a().object,cards:a().array,isOpen:a().bool,onOpen:a().func,onCancel:a().func};var Q=n(45063),q=n.n(Q);function B(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function V(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?B(Object(n),!0).forEach((function(t){G(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):B(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function G(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function $(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}(0,l.ZP)("img")({top:0,width:"100%",height:"100%",objectFit:"cover",position:"absolute"});var J=(0,l.ZP)("div")((function(e){var t=e.theme;return{display:"flex",position:"relative",justifyContent:"center",paddingTop:"calc(100% * 9 / 24)","&:before":{top:0,zIndex:9,content:"''",width:"100%",height:"100%",position:"absolute",backdropFilter:"blur(3px)",WebkitBackdropFilter:"blur(3px)",borderTopLeftRadius:t.shape.borderRadiusMd,borderTopRightRadius:t.shape.borderRadiusMd,backgroundColor:(0,i.Fq)(t.palette.primary.darker,.72)}}}));function K(e){var t,n,o=e.employee,a=(e.index,e.onRefresh),l=e.setLoading,i=e.toast,y=(t=(0,r.useState)({employeeInformation:!1,payment:!1}),n=2,function(e){if(Array.isArray(e))return e}(t)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(t,n)||function(e,t){if(e){if("string"==typeof e)return $(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?$(e,t):void 0}}(t,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),d=y[0],E=y[1],g=o.Name;return o.MobileNumber,o.WhatsApp,o.EmailID,o.Address,r.createElement(r.Fragment,null,r.createElement(c.ZP,{item:!0,xs:12,sm:6,md:4},r.createElement(u.Z,null,r.createElement(J,null,r.createElement(b.Z,{color:"paper",src:q().Logo,sx:{width:144,height:62,zIndex:10,bottom:-26,position:"absolute"}}),r.createElement(s.Z,{alt:g,sx:{width:64,height:64,zIndex:11,position:"absolute",left:"40%",transform:"translateY(-50%)"}})),r.createElement(m.Z,{variant:"subtitle1",align:"center",sx:{mt:6}},g),r.createElement(p.Z,{sx:{textAlign:"center",mt:2,mb:2.5}}),r.createElement(f.Z,null),r.createElement(H,{employee:o,isOpen:d.employeeInformation,onOpen:function(){return E(V(V({},d),{},{employeeInformation:!d.employeeInformation}))},onCancel:function(){return E(V(V({},d),{},{employeeInformation:!1}))},onRefresh:a,setLoading:l,toast:i}),r.createElement(f.Z,null),r.createElement(U,{employee:o,isOpen:d.payment,onOpen:function(){return E(V(V({},d),{},{payment:!d.payment}))},onCancel:function(){return E(V(V({},d),{},{payment:!1}))},onRefresh:a,setLoading:l,toast:i}))))}K.propTypes={post:a().object.isRequired,index:a().number};var X=n(4431),ee=n(5977),te=n(96910);function ne(){var e=(0,ee.k6)();return r.createElement(y.Z,{variant:"outlined",onClick:function(){return e.push({pathname:te.vB.employee.add})},startIcon:r.createElement(X.Z,null)},"Add Employee")}},45063:function(e){e.exports=Object.freeze({Indian_Oil_Logo:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2FIndian_Oil_Logo.png?alt=media&token=2d64356b-9f1f-471b-b37b-0c61ab7caae2",Favicon:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Ffavicon.png?alt=media&token=f6b83bef-b0a2-4198-a8b6-a1b180308c51",Logo:"https://firebasestorage.googleapis.com/v0/b/fuelme-20ef9.appspot.com/o/fuel_me_website_content%2Fsolute.jpeg?alt=media&token=5c25200b-fb73-4522-b557-68a9575142bf"})}}]);