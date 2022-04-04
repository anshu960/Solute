"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[4203],{27036:function(e,t,r){var n=r(95318);t.Z=void 0;var l=n(r(64938)),a=r(85893),i=(0,l.default)((0,a.jsx)("path",{d:"M9 16.17 4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z"}),"Check");t.Z=i},55748:function(e,t,r){var n=r(15725),l=r(2658),a=r(22620),i=r(45697),o=r.n(i),u=r(67294);function s(){return s=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},s.apply(this,arguments)}function c(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var m=(0,u.forwardRef)((function(e,t){var r,i,o=(r=(0,u.useState)(null),i=2,function(e){if(Array.isArray(e))return e}(r)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],i=!0,o=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){o=!0,l=e}finally{try{i||null==r.return||r.return()}finally{if(o)throw l}}return a}}(r,i)||function(e,t){if(e){if("string"==typeof e)return c(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?c(e,t):void 0}}(r,i)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),m=o[0],f=o[1],d=e.classes,p=null,b=function(){var t=e.validationType,r=(e.value,e.name);t&&(f("Invalid"),e.setErrorValue("".concat(r)))},y=function(){f(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,u.useImperativeHandle)(t,(function(){return{validateField:b,resetErrorCode:y}})),(m||e.errorMessage)&&(p=u.createElement(n.ZP,{item:!0,md:12,lg:12,className:d.alignCenter},u.createElement(l.Z,{color:"error",variant:"subtitle2"},m?m.errorMessage:e.errorMessage))),u.createElement(u.Fragment,null,u.createElement("div",null,u.createElement(a.Z,s({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:b,onFocus:y},e)),p))}));m.propTypes={type:o().string,name:o().string.isRequired,id:o().string,multiline:o().bool,placeholder:o().string,value:o().string,validationType:o().oneOf(["","mandatory","email","password"]),onChange:o().func,setErrorValue:o().func},m.defaultProps={type:"text",value:"",validationType:""},t.Z=m},64062:function(e,t,r){var n=r(67294),l=r(45697),a=r.n(l),i=r(99226),o=["children","title"];function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},u.apply(this,arguments)}var s=(0,n.forwardRef)((function(e,t){var r=e.children,l=e.title,a=void 0===l?"":l,s=function(e,t){if(null==e)return{};var r,n,l=function(e,t){if(null==e)return{};var r,n,l={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(l[r]=e[r]);return l}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(l[r]=e[r])}return l}(e,o);return n.createElement(i.Z,u({ref:t},s),n.createElement(i.Z,null,n.createElement("title",null,a)),r)}));s.propTypes={children:a().node.isRequired,title:a().string},t.Z=s},94203:function(e,t,r){r.r(t),r.d(t,{default:function(){return F}});var n=r(67294),l=r(29602),a=r(65295),i=r(54065),o=r(99226),u=r(2658),s=r(64062),c=r(78185),m=r(5977),f=r(26447),d=r(67109),p=r(76914),b=r(28006),y=r.n(b),v=r(75113),h=r(50450),g=r(42694),E=r(26517),O=(0,r(61354).Z)(),S=r(27036),w=r(98401),N=r(96910),j=[{id:"BusinessName",label:"Business Name",type:"text",placeholder:"Enter Business Name"},{id:"DealerName",label:"Dealer Name",type:"text",placeholder:"Enter Dealer Name"},{id:"email",label:"Email",type:"email",placeholder:"Enter Email"},{id:"GSTNumber",label:"GST Number",type:"text",placeholder:"Select GST Number"},{id:"BusinessMobileNumber",label:"Business Mobile Number",type:"number",placeholder:"Select Business Mobile Number"},{id:"Address",label:"Address",type:"text",placeholder:"Enter Address",multiline:!0}],Z=r(55748);function x(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function P(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?x(Object(r),!0).forEach((function(t){C(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):x(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function C(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function D(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],i=!0,o=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){o=!0,l=e}finally{try{i||null==r.return||r.return()}finally{if(o)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return I(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?I(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function I(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var T={FirstName:"",LastName:"",MobileNumber:"",EmailID:"",BusinessName:"",DealerName:"",GSTNumber:"",BusinessMobileNumber:"",Address:"",otp:""};function A(e){var t=e.businessTypes,r=D((0,n.useState)(T),2),l=r[0],i=r[1],o=D((0,n.useState)(!1),2),s=o[0],c=o[1],b=D((0,n.useState)(!1),2),x=b[0],I=b[1],A=(0,m.k6)();(0,n.useEffect)((function(){var e=(0,w.Vq)("MobileNumber");i(P(P({},l),{},{BusinessMobileNumber:e}));var r=(0,E.a)("id").id;I(r),t.length}),[]);var B=(0,n.useCallback)((function(e){console.log("handleCreateBusinessEvent",e),c(!1),e.Payload&&e.Payload._id&&((0,w.XJ)(e.Payload._id),(0,w.nK)(e.Payload),A.push(N.ko.home))}),[]),M=function(e){i(P(P({},l),{},C({},e.target.name,e.target.value)))},k=t.filter((function(e){return e._id===x}));console.log(k);var G=k&&k.length&&k[0]||{};return n.createElement(h.W2,null,s?n.createElement(g.Z,null):null,n.createElement(a.Z,{sx:{display:"flex",alignItems:"center",p:3,mb:3}},n.createElement(d.Z,{alt:"business",src:G.Image&&G.Image[0],sx:{width:30,height:30,padding:"3px"}}),n.createElement(O,{sx:{flexGrow:1,minWidth:0,pl:2,pr:1}},n.createElement(u.Z,{variant:"subtitle2",noWrap:!0},G.Name)),n.createElement(p.Z,{size:"small",variant:"text",color:"primary",startIcon:n.createElement(S.Z,null)},"Selected")),n.createElement("form",{action:""},n.createElement(f.Z,{spacing:3},j.map((function(e){return n.createElement(f.Z,{spacing:3},n.createElement(u.Z,{variant:"subtitle2"},e.label),function(e){return n.createElement(Z.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:M,name:e.id,autoComplete:e.id,value:l[e.id],type:e.type,multiline:!!e.multiline})}(e))})),n.createElement(p.Z,{onClick:function(e){console.log(e),e.preventDefault(),function(){console.log("User Input = ",l);var e={UserID:(0,w.n5)(),Name:l.BusinessName,Address:l.Address,BusinessTypeID:x,DealerName:l.DealerName,ProductTypes:[],GSTNumber:l.GSTNumber,Status:"active",EmailID:l.EmailID||"",MobileNumber:l.BusinessMobileNumber,DialCode:"",Gender:0,DeviceID:"",FCMToken:"",ProfilePicture:[]};c(!0),console.log("Create business request = ",e),(0,v.SL)(y().CREATE_BUSINESS,e,B)}()},style:{width:"100%",height:40,backgroundColor:"blue",color:"white"}},"Create Business"))))}var B=r(45063),M=r.n(B),k=r(39704),G=(0,l.ZP)(s.Z)((function(e){var t,r,n;return t={},n={display:"flex"},(r=e.theme.breakpoints.up("md"))in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t})),W=(0,l.ZP)(a.Z)((function(e){return{width:"100%",maxWidth:464,display:"flex",flexDirection:"column",justifyContent:"center",margin:e.theme.spacing(2,0,2,2)}})),R=(0,l.ZP)("div")((function(e){return{maxWidth:480,margin:"auto",display:"flex",minHeight:"100vh",flexDirection:"column",justifyContent:"center",padding:e.theme.spacing(12,0)}}));function F(){var e=(0,k.v9)((function(e){return e.businessTypes.businessTypes}));return n.createElement(G,{title:"Register"},n.createElement(c.J,{width:"mdDown"},n.createElement(W,null,n.createElement("img",{src:M().Logo,alt:"Solute"}))),n.createElement(i.Z,{maxWidth:"sm"},n.createElement(R,null,n.createElement(o.Z,{sx:{mb:5}},n.createElement(u.Z,{variant:"h4",gutterBottom:!0},"Register Business to Solute"),n.createElement(u.Z,{sx:{color:"text.secondary"}},"Enjoy the best experience.")),n.createElement(A,{businessTypes:e}))))}}}]);