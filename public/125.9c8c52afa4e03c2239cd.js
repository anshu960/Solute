"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[125],{55748:function(e,t,n){var r=n(15725),l=n(2658),a=n(22620),o=n(45697),i=n.n(o),c=n(67294);function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},u.apply(this,arguments)}function s(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var p=(0,c.forwardRef)((function(e,t){var n,o,i=(n=(0,c.useState)(null),o=2,function(e){if(Array.isArray(e))return e}(n)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(n,o)||function(e,t){if(e){if("string"==typeof e)return s(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?s(e,t):void 0}}(n,o)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),p=i[0],d=i[1],m=e.classes,f=null,y=function(){var t=e.validationType,n=(e.value,e.name);t&&(d("Invalid"),e.setErrorValue("".concat(n)))},v=function(){d(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,c.useImperativeHandle)(t,(function(){return{validateField:y,resetErrorCode:v}})),(p||e.errorMessage)&&(f=c.createElement(r.ZP,{item:!0,md:12,lg:12,className:m.alignCenter},c.createElement(l.Z,{color:"error",variant:"subtitle2"},p?p.errorMessage:e.errorMessage))),c.createElement(c.Fragment,null,c.createElement("div",null,c.createElement(a.Z,u({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:y,onFocus:v},e)),f))}));p.propTypes={type:i().string,name:i().string.isRequired,id:i().string,multiline:i().bool,placeholder:i().string,value:i().string,validationType:i().oneOf(["","mandatory","email","password"]),onChange:i().func,setErrorValue:i().func},p.defaultProps={type:"text",value:"",validationType:""},t.Z=p},64062:function(e,t,n){var r=n(67294),l=n(45697),a=n.n(l),o=n(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,l=e.title,a=void 0===l?"":l,u=function(e,t){if(null==e)return{};var n,r,l=function(e,t){if(null==e)return{};var n,r,l={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(l[n]=e[n]);return l}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(l[n]=e[n])}return l}(e,i);return r.createElement(o.Z,c({ref:t},u),r.createElement(o.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},70125:function(e,t,n){n.r(t),n.d(t,{default:function(){return q}});var r=n(67294),l=n(54065),a=n(26447),o=n(2658),i=n(99226),c=n(22620),u=n(65295),s=n(97896),p=n(76914),d=n(9198),m=n.n(d),f=n(9573),y=n(38732),v=n(46926),h=n(56011),b=n(3694),E=n(5977),g=n(72132),Z=n(64062),S=n(96910),w=n(39704),x=n(77512),O=n(15725),A=n(77750),D=n(56408),j=n(88979);function C(e){var t=e.handleConfirm,n=e.setOpen;return r.createElement(r.Fragment,null,r.createElement(p.Z,{variant:"contained",color:"info",onClick:function(){n({open:!1,shipment:{}})}},"Cancel"),r.createElement(p.Z,{variant:"contained",color:"success",onClick:t},"Confirm"))}var I=n(50006),k=n(55748),P=[{id:"SenderAddress",label:"Sender Address",type:"text",placeholder:"Enter Sender Address",disabled:!0},{id:"ReceiverAddress",label:"Receiver Address",type:"text",placeholder:"Enter Receiver Address",disabled:!0}],T=n(83428),B=n(98401);function R(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function N(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?R(Object(n),!0).forEach((function(t){U(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):R(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function F(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return M(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?M(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function M(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function U(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var V={SenderAddress:"",ReceiverAddress:""},W=function(e){return U({},"stopage"+e,[{id:"Address",label:"Stopage Address",type:"text",placeholder:"Enter Stopage Address"},{id:"Stop".concat(e,"DeliveryBoyName"),label:"Delivery Boy Name",type:"text",placeholder:"Enter Delivery Boy Name"},{id:"Stop".concat(e,"DeliveryBoyContact"),label:"Delivery Boy Contact",type:"text",placeholder:"Enter Delivery Boy Contact"},{id:"Stop".concat(e,"DeliveryBoyAddress"),label:"Delivery Boy Address",type:"text",placeholder:"Enter Delivery Boy Address"},{id:"Status",label:"Status",type:"select",placeholder:"Select Status",options:[{label:"Picked",value:1},{label:"In Transit",value:2},{label:"Dropped",value:3},{label:"Delivered",value:4}]}])};function _(e){var t=e.shipment,n=e.setOpen,l=(0,w.I0)(),o=F((0,r.useState)(V),2),i=o[0],c=o[1],u=F((0,r.useState)(W(1)),2),s=u[0],d=u[1],m=(0,r.useRef)(null);console.log(t),r.useEffect((function(){var e=Object.keys(t).length?t:null;e&&c(e)}),[t]);var f=function(e){c(N(N({},i),{},U({},e.target.name,e.target.value)))},y=function(e,t){var n=t.name,r=e&&e.value||"";c(N(N({},i),{},U({},n,r)))},v=function(){var e=Object.keys(s).length+1,t=W(e);d(N(N({},s),t))},h=function(e){switch(e.type){case"button":return r.createElement(p.Z,{variant:"contained",color:"info",onClick:v},"Add Stopage");case"select":return function(e){return r.createElement(I.ZP,{name:e.id,options:e.options,placeholder:e.placeholder,isClearable:!0,value:e.options.filter((function(t){return t.value===i[e.id]})),onChange:y})}(e);default:return function(e){return r.createElement(k.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:f,name:e.id,autoComplete:e.id,value:i[e.id],type:e.type,multiline:!!e.multiline,disabled:e.disabled})}(e)}};return r.createElement(r.Fragment,{style:{outerHeight:800}},r.createElement(A.Z,{dividers:!0},r.createElement(D.Z,{id:"scroll-dialog-description",ref:m,tabIndex:-1},r.createElement("form",{action:""},r.createElement(a.Z,{spacing:3},r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2},r.createElement(O.ZP,{container:!0,spacing:3,py:2},P.map((function(e){return r.createElement(O.ZP,{item:!0,xs:12,md:12,lg:6,xl:4},r.createElement(a.Z,{spacing:3},h(e)))}))))),r.createElement(a.Z,{spacing:3},r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2})),r.createElement(a.Z,{spacing:3},function(){var e=[];for(var t in s)e.push(r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2},r.createElement(O.ZP,{container:!0,spacing:3,py:2},s[t].map((function(e){return r.createElement(O.ZP,{item:!0,xs:12,md:12,lg:6,xl:4},r.createElement(a.Z,{spacing:3},h(e)))})))));return e}())))),r.createElement(j.Z,{sx:{justifyContent:"end"}},r.createElement(C,{handleConfirm:function(){console.log(i);var e=N(N({},i),{UserID:(0,B.n5)(),BusinessID:(0,B.vI)(),ShipmentID:t._id,_id:void 0,ShipmentNumber:t.ShipmentID});l((0,T.BE)(e,(function(){n({open:!1,shipment:{}})})))},setOpen:n})))}var H=n(95481);function L(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return $(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?$(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function $(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var q=function(){var e=(0,H.y)(),t=(0,w.I0)(),n=(0,E.k6)(),d=r.useRef(),O=(0,w.v9)((function(e){return e.shipment.allShipment})),A=L((0,r.useState)(new Date),2),D=A[0],j=A[1],C=L((0,r.useState)(new Date),2),I=C[0],k=C[1],P=L((0,r.useState)({open:!1,shipment:null}),2),B=P[0],R=P[1];return(0,r.useEffect)((function(){t((0,T.n)(D,I))}),[D,I]),r.createElement(Z.Z,{title:"History"},r.createElement(r.Fragment,null,r.createElement(l.Z,null,r.createElement(g.Ix,null),r.createElement(x.r,{body:r.createElement(_,{shipment:B.shipment,setOpen:R}),handleClose:function(){R({open:!1,shipment:{}})},scroll:"paper",title:"Add Stopage",open:B.open,dialogWidth:"xl"}),r.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(o.Z,{variant:"h4",gutterBottom:!0},"Shipments"),r.createElement(i.Z,{className:e.actionList},r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(c.Z,{className:e.search,fullWidth:!0,placeholder:"Search",name:"search",autoComplete:!1,type:"text"})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"Start Date",className:e.butonScrmenuSe,selected:D,onChange:function(e){e&&j(e)}})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"End Date",className:e.butonScrmenuSe,selected:I,onChange:function(e){e&&k(e)}})))),r.createElement(u.Z,null,r.createElement(i.Z,null,r.createElement(s.Z,{sx:{minWidth:800}},r.createElement(f.Z,{style:{width:"100%",textAlign:"center"},ref:d},r.createElement(h.Z,null,r.createElement(b.Z,null,r.createElement(v.Z,null,"ID"),r.createElement(v.Z,null,"From"),r.createElement(v.Z,null,"To"),r.createElement(v.Z,null,"Delivery Date"),r.createElement(v.Z,null,"Status"),r.createElement(v.Z,null,"Stopage"),r.createElement(v.Z,null,"Details"),r.createElement(v.Z,null,"Track"))),r.createElement(y.Z,null,O&&O.length?O.map((function(e){return r.createElement(b.Z,{key:e.ShipmentID},r.createElement(v.Z,null,e.ShipmentID),r.createElement(v.Z,null,e.SenderAddress),r.createElement(v.Z,null,e.ReceiverAddress),r.createElement(v.Z,null,(t=e.ShipmentDeliveryDate,(l=new Date(t)).getDate()+"-"+(l.getMonth()+1)+"-"+l.getFullYear())),r.createElement(v.Z,null,function(e){var t="";switch(e){case 1:t="Picked";break;case 2:t="In Transit";break;case 3:default:t="Delivered";break;case 4:t="Dropped"}return t}(e.Status)),r.createElement(v.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){R({open:!0,shipment:e})}},"Add")),r.createElement(v.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){n.push({pathname:S.vB.delivery.profile,search:"?id=".concat(e.ShipmentID)})}},"View")),r.createElement(v.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){window.open("/#".concat(S.ko.shipmentTrack,"?id=").concat(e.ShipmentID),"_blank")}},"Locate")),r.createElement(v.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){window.open("/#".concat(S.ko.deliveryReceipt,"?id=").concat(e.ShipmentID),"_blank")}},"Receipt")));var t,l})):r.createElement(b.Z,null,r.createElement(v.Z,{colSpan:8},"Unable to find shipments for selected date range"))))))))))}},95481:function(e,t,n){n.d(t,{y:function(){return o}});var r=n(44290),l=n(73327);function a(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var o=(0,r.Z)((function(e){var t;return(0,l.Z)({actionList:{display:"flex",justifyContent:"flex-end",flexGrow:"1"},selctAutTarDate:(t={color:"#fff",marginRight:"7px",position:"relative"},a(t,e.breakpoints.between("1024","1400"),{width:"18%"}),a(t,"& input",{padding:"10px 0px 10px 10px",border:"1px solid #cccccc",borderRadius:"4px"}),t),calendarIcon:{position:"absolute",right:"40px",top:"14px",color:"#8F8FB3",fontSize:"14px",zIndex:"99"},datePicker:{height:"48px"},search:{"& input":{height:"17px"}}})}))}}]);