(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[8592],{46191:function(e,t,n){"use strict";var r=n(67294),o=n(99226),a=n(45063),l=n.n(a);function i(){return i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},i.apply(this,arguments)}t.Z=function(e){var t=i({},e);return r.createElement(o.Z,i({component:"img",alt:"logo",src:l().Logo,height:40},t))}},64062:function(e,t,n){"use strict";var r=n(67294),o=n(45697),a=n.n(o),l=n(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,o=e.title,a=void 0===o?"":o,u=function(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}(e,i);return r.createElement(l.Z,c({ref:t},u),r.createElement(l.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},60943:function(e){e.exports=Object.freeze({firebaseConfig:{apiKey:"AIzaSyBKhBttWEglXe0EQ3dB_Ui1P-lj9rGi0ps",authDomain:"fuelme-20ef9.firebaseapp.com",projectId:"fuelme-20ef9",storageBucket:"fuelme-20ef9.appspot.com",messagingSenderId:"666336350240",appId:"1:666336350240:web:2a600baa192d49386d2c45",measurementId:"G-B3V9MZNFB9"}})},19180:function(e){e.exports=Object.freeze({ACCEPT_EMPLOYEE_CONNECTION_REQUEST:"ACCEPT_EMPLOYEE_CONNECTION_REQUEST",REJECT_EMPLOYEE_CONNECTION_REQUEST:"REJECT_EMPLOYEE_CONNECTION_REQUEST",EMPLOYEE_CONNECTION_REQUEST_ACCEPTED:"EMPLOYEE_CONNECTION_REQUEST_ACCEPTED",EMPLOYEE_CONNECTION_REQUEST_REJECTED:"EMPLOYEE_CONNECTION_REQUEST_REJECTED",CREATE_EMPLOYEE_CONNECTION_REQUEST:"CREATE_EMPLOYEE_CONNECTION_REQUEST"})},31095:function(e){e.exports=Object.freeze({REQUEST:"REQUEST"})},48592:function(e,t,n){"use strict";n.r(t),n.d(t,{default:function(){return et}});var r=n(67294),o=n(73727),a=n(5977),l=n(29602),i=n(15725),c=n(6634),u=n(36501),s=n(57797),f=n(2658),p=n(99226),m=n(54065),E=n(64062),d=(n(78185),n(4431)),b=n(11145),y=n(72132),g=n(77512),h=n(75113),v=n(28006),O=n.n(v),x=n(77750),Z=n(56408),C=n(98619),w=n(70417),P=n(67109),j=n(59334),S=n(88979),T=n(76914),I=n(96910);function k(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function _(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?k(Object(n),!0).forEach((function(t){N(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):k(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function N(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var A={backgroundColor:"aliceblue",color:"info.main"};function R(e){var t=e.history,n=e.businessTypes,o=e.businessId,a=e.setBusinessId,l=e.handleClose,c=e.open,u=(0,r.useRef)(null);return(0,r.useEffect)((function(){if(c){var e=u.current;null!==e&&e.focus()}}),[c]),r.createElement(r.Fragment,null,r.createElement(x.Z,{dividers:!0},r.createElement(Z.Z,{id:"scroll-dialog-description",ref:u,tabIndex:-1},r.createElement(i.ZP,{container:!0},n.map((function(e){return r.createElement(i.ZP,{item:!0,xs:12,sm:6,md:4},r.createElement(C.Z,{onClick:function(){return a(e._id)},sx:e._id===o?A:{}},r.createElement(w.Z,null,r.createElement(P.Z,{sx:e._id===o?_(_({},A),{},{backgroundColor:"info.lighter"}):{}},r.createElement("img",{style:{height:"20px",width:"20px"},src:e.Image[0],alt:e.Name}))),r.createElement(j.Z,{primary:e.Name})))}))))),r.createElement(S.Z,null,r.createElement(T.Z,{variant:"contained",color:"warning",onClick:l},"Cancel"),r.createElement(T.Z,{variant:"contained",onClick:function(){return t.push({pathname:I.EE.register,search:"?id=".concat(o)})},disabled:!o},"Proceed")))}var D=n(76887),U={curveType:"function",legend:{position:"none"},backgroundColor:{fill:"transparent"},hAxis:{textPosition:"none",gridlines:{color:"transparent"}},vAxis:{baselineColor:"none",textPosition:"none",gridlines:{color:"transparent"}}};function L(e){var t=e.sale,n=[["instance","Sales"]];return n.push(["1",t/4]),n.push(["2",3*t]),n.push(["3",t/2]),n.push(["4",t]),r.createElement(D.kL,{chartType:"LineChart",width:"80",height:"80",data:n,options:U})}var M=n(45697),B=n.n(M),Q=n(41796),Y=n(33720),z=n(54386),F=n(26447),J=n(6867),W=n(67720),q=n(33797),H=n(62206),G=["children","sx"];function $(){return $=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},$.apply(this,arguments)}function K(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function V(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?K(Object(n),!0).forEach((function(t){X(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):K(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function X(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var ee=(0,l.ZP)("span")((function(e){var t=e.theme;return X({},t.breakpoints.up("sm"),{top:-7,zIndex:1,width:12,right:20,height:12,content:"''",position:"absolute",borderRadius:"0 0 4px 0",transform:"rotate(-135deg)",background:t.palette.background.paper,borderRight:"solid 1px ".concat((0,Q.Fq)(t.palette.grey[500],.12)),borderBottom:"solid 1px ".concat((0,Q.Fq)(t.palette.grey[500],.12))})}));function te(e){var t=e.children,n=e.sx,o=function(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}(e,G);return r.createElement(H.ZP,$({anchorOrigin:{vertical:"bottom",horizontal:"right"},transformOrigin:{vertical:"top",horizontal:"right"},PaperProps:{sx:V({mt:1.5,ml:.5,overflow:"inherit",boxShadow:function(e){return e.customShadows.z20},border:function(e){return"solid 1px ".concat(e.palette.grey[5008])},width:200},n)}},o),r.createElement(ee,{className:"arrow"}),t)}te.propTypes={children:B().node.isRequired,sx:B().object};var ne=n(10402),re=n(92464),oe=n(98401);function ae(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function le(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?ae(Object(n),!0).forEach((function(t){ie(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):ae(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function ie(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function ce(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return ue(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?ue(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function ue(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var se=[{label:"Profile",linkTo:I.ko.profile}];function fe(){var e=(0,a.k6)(),t=(0,r.useRef)(null),n=ce((0,r.useState)(!1),2),l=n[0],i=n[1],c=ce((0,r.useState)({}),2),u=(c[0],c[1]);(0,r.useEffect)((function(){var e=(0,oe.tB)();u(e)}),[]);var s=function(){i(!1)};return r.createElement(r.Fragment,null,r.createElement(J.Z,{ref:t,onClick:function(){i(!0)},sx:le({padding:0,width:44,height:44},l&&{"&:before":{zIndex:1,content:"''",width:"100%",height:"100%",borderRadius:"50%",position:"absolute",bgcolor:function(e){return(0,Q.Fq)(e.palette.grey[900],.72)}}})},r.createElement(ne.Z,null)),r.createElement(te,{open:l,onClose:s,anchorEl:t.current,sx:{width:220}},r.createElement(p.Z,{sx:{my:1.5,px:2.5}},r.createElement(f.Z,{variant:"subtitle1",noWrap:!0},"Solute")),r.createElement(W.Z,{sx:{my:1}}),se.map((function(e){return r.createElement(q.Z,{key:e.label,to:e.linkTo,component:o.rU,onClick:s,sx:{typography:"body2",py:1,px:2.5}},e.label)})),r.createElement(W.Z,{sx:{my:1}}),r.createElement(p.Z,{sx:{p:2,pt:1.5}},r.createElement(T.Z,{fullWidth:!0,color:"inherit",variant:"outlined",onClick:function(){(0,re.kS)(e)}},"Logout"))))}var pe=n(46191),me=n(12484),Ee=n(39704),de=n(79793),be=n(31095),ye=n.n(be),ge=n(27036),he=n(63343),ve=n(27856),Oe=n(19180),xe=n.n(Oe),Ze=n(70576),Ce=n(71196),we=n(12815),Pe=n(42440),je=n(48277),Se=n(84185);function Te(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function Ie(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function ke(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?Ie(Object(n),!0).forEach((function(t){_e(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):Ie(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function _e(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function Ne(e){var t=e.notification,n=function(e){var t=r.createElement(p.Z,null,r.createElement(f.Z,{variant:"subtitle2"},e.Title),r.createElement(f.Z,{component:"span",variant:"body2",sx:{color:"text.secondary"}},e.Message));return{avatar:r.createElement("img",{alt:e.Title,src:e.Image}),title:t}}(t),a=n.avatar,l=n.title,i=t.id,c=t.isUnRead;return r.createElement(r.Fragment,null,r.createElement(Ze.ZP,{button:!0,to:"#",disableGutters:!0,key:i,component:o.rU,sx:ke({py:1.5,px:2.5,"&:not(:last-of-Type)":{mb:"1px"}},c&&{backgroundColor:"action.selected"})},r.createElement(w.Z,null,r.createElement(P.Z,{sx:{backgroundColor:"background.neutral"}},a)),r.createElement(j.Z,{primary:l})),r.createElement(j.Z,{secondary:r.createElement(p.Z,{sx:{display:"flex",alignItems:"center",flexDirection:"column"}},t.Type===ye().REQUEST?r.createElement(p.Z,{sx:{display:"flex",justifyContent:"space-between",width:"70%"}},r.createElement(T.Z,{color:"info",variant:"contained",onClick:function(){!function(e,t,n){t.Action===xe().ACCEPT_EMPLOYEE_CONNECTION_REQUEST?(console.log("ACCEPT_EMPLOYEE_CONNECTION_REQUEST"),e((0,ve.D4)(t,void 0))):console.log("No Notification actions found at the moment",t)}(t)},startIcon:r.createElement(ge.Z,null)},"Accept"),r.createElement(T.Z,{color:"error",variant:"outlined",onClick:function(){!function(e,t,n){t.Action===xe().ACCEPT_EMPLOYEE_CONNECTION_REQUEST?(console.log("ACCEPT_EMPLOYEE_CONNECTION_REQUEST"),e((0,ve.yn)(t,void 0))):console.log("No Notification actions found at the moment",t.ActionType)}(t)},startIcon:r.createElement(he.Z,null)},"Reject")):null,(0,me.Z)(new Date(t.CreatedAt)))}))}function Ae(){var e,t,n=(0,r.useRef)(null),a=(0,Ee.I0)(),l=(e=(0,r.useState)(!1),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return Te(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?Te(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),i=l[0],c=l[1],u=(0,Ee.v9)((function(e){return e.Notification.allNotification})),s=u?u.slice().sort((function(e,t){return new Date(e.CreatedAt).getTime()-new Date(t.CreatedAt).getTime()})):u,m=s.filter((function(e){return!e.IsRead})).length;return(0,r.useEffect)((function(){a((0,Se.am)())}),[]),r.createElement(r.Fragment,null,r.createElement(Ce.Z,{badgeContent:m,color:"error"},r.createElement(de.Z,{width:20,height:20,ref:n,onClick:function(){c(!0),a((0,Se.am)())},color:i?"primary":"info"})),r.createElement(te,{open:i,onClose:function(){return c(!1)},anchorEl:n.current,sx:{width:360}},r.createElement(p.Z,{sx:{display:"flex",alignItems:"center",py:2,px:2.5}},r.createElement(p.Z,{sx:{flexGrow:1}},r.createElement(f.Z,{variant:"subtitle1"},"Notifications"),r.createElement(f.Z,{variant:"body2",sx:{color:"text.secondary"}},"You have ",m," unread messages")),m>0&&r.createElement(we.Z,{title:" Mark all as read"},r.createElement(de.Z,null))),r.createElement(W.Z,null),r.createElement(p.Z,{sx:{height:{xs:340,sm:"auto"}}},r.createElement(Pe.Z,{disablePadding:!0,subheader:r.createElement(je.Z,{disableSticky:!0,sx:{py:1,px:2.5,typography:"overline"}},"New")},s.slice(0,2).map((function(e){return r.createElement(Ne,{key:e._id,notification:e})}))),r.createElement(Pe.Z,{disablePadding:!0,subheader:r.createElement(je.Z,{disableSticky:!0,sx:{py:1,px:2.5,typography:"overline"}},"Before that")},s.slice(2,5).map((function(e){return r.createElement(Ne,{key:e._id,notification:e})})))),r.createElement(W.Z,null),r.createElement(p.Z,{sx:{p:1}},r.createElement(T.Z,{fullWidth:!0,disableRipple:!0,component:o.rU,to:"#"},"View All"))))}var Re=n(48885),De=n(80087),Ue=n(64478),Le=(n.p,n.p,[{value:"en",label:"English"},{value:"hi",label:"Hindi"}]);function Me(){var e=(0,Ue.$)(),t=e.i18n,n=e.t,r=localStorage.getItem("i18nextLng");return{onChangeLang:function(e){console.log(e),t.changeLanguage(e)},t:n,currentLang:Le.find((function(e){return e.value===r}))||Le[0],allLang:Le}}function Be(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function Qe(){var e,t,n=(0,r.useRef)(null),o=(e=(0,r.useState)(!1),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return Be(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?Be(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),a=o[0],l=o[1],i=Me(),c=i.allLang,u=i.currentLang,s=i.onChangeLang;return r.createElement(r.Fragment,null,r.createElement(De.Z,{color:a?"primary":"info",ref:n,onClick:function(){return l(!0)}}),r.createElement(te,{open:a,onClose:function(){return l(!1)},anchorEl:n.current},r.createElement(p.Z,{sx:{py:1}},c.map((function(e){return r.createElement(q.Z,{key:e.value,selected:e.value===u.value,onClick:function(){s(e.value),l(!1)},sx:{py:1,px:2.5}},r.createElement(Re.Z,null),r.createElement(j.Z,{primaryTypographyProps:{variant:"body2"}},e.label))})))))}function Ye(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var ze=(0,l.ZP)(Y.Z)((function(e){var t=e.theme;return Ye({boxShadow:"none",backdropFilter:"blur(6px)",WebkitBackdropFilter:"blur(6px)",backgroundColor:(0,Q.Fq)(t.palette.background.default,.72)},t.breakpoints.up("lg"),{width:"calc(100% - ".concat(281,"px)")})})),Fe=(0,l.ZP)(z.Z)((function(e){var t=e.theme;return Ye({minHeight:64},t.breakpoints.up("lg"),{minHeight:92,padding:t.spacing(0,5)})}));function Je(){return r.createElement(ze,null,r.createElement(Fe,null,r.createElement(pe.Z,null),r.createElement(p.Z,{sx:{flexGrow:1}}),r.createElement(F.Z,{direction:"row",alignItems:"center",spacing:{xs:.5,sm:1.5}},r.createElement(Qe,null),r.createElement(Ae,null),r.createElement(fe,null))))}Je.propTypes={onOpenSidebar:B().func};var We=n(19711),qe=n(38513),He=n(26517),Ge=n(71983),$e=n(96885);function Ke(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],l=!0,i=!1;try{for(n=n.call(e);!(l=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==n.return||n.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return Ve(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?Ve(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function Ve(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var Xe=(0,l.ZP)(E.Z)((function(e){var t=e.theme;return{minHeight:"100%",paddingTop:t.spacing(15),paddingBottom:t.spacing(10)}}));function et(){var e=(0,Ee.I0)(),t=(0,a.k6)(),n=Ke(r.useState(!1),2),l=n[0],E=n[1],v=Ke(r.useState(),2),x=v[0],Z=v[1],C=(0,Ee.v9)((function(e){return e.business.allBusiness})),w=(0,Ee.v9)((function(e){return e.business_analytics.saleToday})),P=(0,Ee.v9)((function(e){return e.businessTypes.businessTypes})),j=(0,Ee.v9)((function(e){return e.room.isConnected})),S=function(e){var t=0;return w&&w.length?w.forEach((function(n){n._id===e&&(t=n.TotalSale,console.log("id not matching business matched",n,t,e,w))})):console.log("id not matching business saletoday is empty",w),t};(0,r.useEffect)((function(){var n=(0,oe.n5)(),r=(0,oe.vI)();n||(n=(0,He.K)(),(0,h.zU)(O().JOIN_ROOM,{ROOM_ID:n},T)),console.log("useEffect",n,r),j&&(e((0,We.cf)({UserID:n,BusinessID:r})),e((0,qe.J)({UserID:n}))),(0,oe.n5)()&&(0,oe.PR)()||t.push(I.EE.login)}),[j]);var T=r.useCallback((function(t){console.log("handleJoinRoomEvent Home",t);var n=(0,oe.n5)();e((0,qe.J)({UserID:n}))}),[]),k=function(e){var t=P.filter((function(t){return t._id===e}));if(t&&t.length&&t[0].Image)return t[0].Image[0]};console.log(C);var _=Me().t,N=function(){return E(!1)};return r.createElement(Xe,{title:"Home"},r.createElement(y.Ix,null),r.createElement(Je,null),r.createElement(m.Z,{maxWidth:"lg"},r.createElement(p.Z,{sx:{mb:5}},r.createElement(f.Z,{variant:"h3",align:"center",paragraph:!0},_("page_title")),r.createElement(f.Z,{align:"center",sx:{color:"text.secondary"}},_("page_subtitle"))),r.createElement(i.ZP,{container:!0,spacing:3,sx:{my:10}},r.createElement(g.r,{body:r.createElement(R,{history:t,businessTypes:P,businessId:x,setBusinessId:Z,handleClose:N,open:l}),handleClose:N,scroll:"paper",title:"Choose Business",open:l,dialogWidth:"xl"}),r.createElement(i.ZP,{item:!0,xs:12,sm:6,md:3},r.createElement(c.Z,{component:o.rU,onClick:function(){return E(!0)},underline:"none"},r.createElement(u.Z,{sx:{p:1,boxShadow:function(e){return e.customShadows.z8},"&:hover img":{transform:"scale(1.1)"},height:"240px",overflow:"hidden"}},r.createElement(s.Z,{sx:{p:3,borderRadius:1,color:"primary.main",bgcolor:"background.neutral",justifyContent:"center",display:"flex",alignItems:"center",height:"225px"}},r.createElement(d.Z,null),r.createElement(f.Z,{sx:{marginLeft:"5px"},variant:"h6"}," Add "))))),C&&C.length?C.map((function(n){return r.createElement(i.ZP,{item:!0,xs:12,sm:6,md:3},r.createElement(c.Z,{component:o.rU,underline:"none"},r.createElement(u.Z,{sx:{p:1,boxShadow:function(e){return e.customShadows.z8},"&:hover img":{transform:"scale(1.1)"},height:"240px",overflow:"hidden"},onClick:function(){(0,oe.XJ)(n._id),(0,oe.nK)(n),e((0,We.Yb)(n,P,(function(e){27===e.BusinessTypeID?t.push(I.vB.fee.fee):t.push(I.vB.sale.sale)})))}},r.createElement(s.Z,{sx:{p:1,borderRadius:1,color:"primary.main",bgcolor:"background.neutral",justifyContent:"space-between",alignItems:"center",display:"flex"}},r.createElement(p.Z,null,r.createElement(L,{sale:S(n._id)})),r.createElement(f.Z,{variant:"h5",gutterBottom:!0,sx:{display:"flex",color:"primary.darker"}},r.createElement($e.Z,{sx:{width:18,color:"primary.darker"}}),r.createElement(f.Z,null," ",S(n._id)))),r.createElement(p.Z,{sx:{display:"flex",alignItems:"center"}},r.createElement("img",{alt:"business",src:k(n.BusinessTypeID),style:{width:20}}),r.createElement(f.Z,{variant:"subtitle2",sx:{p:1,color:"primary.darker"}},n.Name)),r.createElement(p.Z,{sx:{display:"flex",alignItems:"center"}},r.createElement(b.Z,{sx:{width:20,color:"#0433ff"}}),r.createElement(f.Z,{variant:"subtitle2",sx:{p:1,color:"primary.darker"}},n.MobileNumber)),r.createElement(p.Z,{sx:{display:"flex",alignItems:"center"}},r.createElement(Ge.Z,{sx:{width:20,color:"#0433ff"}}),r.createElement(f.Z,{variant:"subtitle2",sx:{p:1,color:"primary.darker"}},n.Address)))))})):null)))}},92464:function(e,t,n){"use strict";n.d(t,{kS:function(){return c},s5:function(){return i}});var r=n(15503),o=(n(77397),n(60943)),a=n.n(o),l=n(96910),i=(r.Z.initializeApp(a().firebaseConfig).auth(),function(e){r.Z.apps.length?r.Z.app():r.Z.initializeApp(a().firebaseConfig),r.Z.auth().onAuthStateChanged((function(t){t&&t.uid?e(!0,t):e(!1)}))}),c=function(e){r.Z.apps.length?r.Z.app():r.Z.initializeApp(a().firebaseConfig),r.Z.auth().signOut().then((function(){localStorage.clear(),sessionStorage.clear(),e.push(l.EE.login)})).catch((function(e){alert(e)}))}}}]);