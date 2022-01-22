"use strict";(self.webpackChunkfuelme=self.webpackChunkfuelme||[]).push([[8733],{55748:function(e,t,r){var n=r(15725),l=r(2658),a=r(51732),o=r(45697),i=r.n(o),c=r(67294);function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},u.apply(this,arguments)}function p(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var s=(0,c.forwardRef)((function(e,t){var r,o,i=(r=(0,c.useState)(null),o=2,function(e){if(Array.isArray(e))return e}(r)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(r,o)||function(e,t){if(e){if("string"==typeof e)return p(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?p(e,t):void 0}}(r,o)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),s=i[0],f=i[1],m=e.classes,y=null,d=function(){var t=e.validationType,r=(e.value,e.name);t&&(f("Invalid"),e.setErrorValue("".concat(r)))},b=function(){f(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,c.useImperativeHandle)(t,(function(){return{validateField:d,resetErrorCode:b}})),(s||e.errorMessage)&&(y=c.createElement(n.ZP,{item:!0,md:12,lg:12,className:m.alignCenter},c.createElement(l.Z,{color:"error",variant:"subtitle2"},s?s.errorMessage:e.errorMessage))),c.createElement(c.Fragment,null,c.createElement("div",null,c.createElement(a.Z,u({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:d,onFocus:b},e)),y))}));s.propTypes={type:i().string,name:i().string.isRequired,id:i().string,multiline:i().bool,placeholder:i().string,value:i().string,validationType:i().oneOf(["","mandatory","email","password"]),onChange:i().func,setErrorValue:i().func},s.defaultProps={type:"text",value:"",validationType:""},t.Z=s},64062:function(e,t,r){var n=r(67294),l=r(45697),a=r.n(l),o=r(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},c.apply(this,arguments)}var u=(0,n.forwardRef)((function(e,t){var r=e.children,l=e.title,a=void 0===l?"":l,u=function(e,t){if(null==e)return{};var r,n,l=function(e,t){if(null==e)return{};var r,n,l={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(l[r]=e[r]);return l}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(l[r]=e[r])}return l}(e,i);return n.createElement(o.Z,c({ref:t},u),n.createElement(o.Z,null,n.createElement("title",null,a)),r)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},66309:function(e,t,r){r.d(t,{Jf:function(){return n},r8:function(){return l},mR:function(){return a},G1:function(){return o},he:function(){return i}});var n=[{id:"Name",label:"Shipment Name",type:"text",placeholder:"Enter Shipment Name"},{id:"Weight",label:"Shipment Weight",type:"text",placeholder:"Enter Shipment Weight"},{id:"ShipmentDate",label:"Shipment Date",type:"date",placeholder:"Enter Shipment Date"},{id:"ShipmentDeliveryDate",label:"Shipment Delivery Date",type:"date",placeholder:"Enter Shipment Delivery Date"},{id:"Description",label:"Description",type:"text",placeholder:"Enter Description",multiline:!0}],l=[{id:"Sender",label:"Sender Name",type:"text",placeholder:"Enter Sender Name"},{id:"SenderContact",label:"Sender Contact",type:"text",placeholder:"Enter Sender Contact"},{id:"SenderAddress",label:"Sender Address",type:"text",placeholder:"Enter Sender Address"}],a=[{id:"Receiver",label:"Receiver Name",type:"text",placeholder:"Enter Receiver Name"},{id:"ReceiverContact",label:"Receiver Contact",type:"text",placeholder:"Enter Receiver Contact"},{id:"ReceiverAddress",label:"Receiver Address",type:"text",placeholder:"Enter Receiver Address"}],o=[{id:"DeliveryBoy",label:"Delivery Boy Name",type:"text",placeholder:"Enter Delivery Boy Name"},{id:"DeliveryBoyContact",label:"Delivery Boy Contact",type:"text",placeholder:"Enter Delivery Boy Contact"},{id:"DeliveryBoyAddress",label:"Delivery Boy Address",type:"text",placeholder:"Enter Delivery Boy Address"}],i={Name:"",Weight:"",From:"",To:"",ShipmentDate:"",ShipmentDeliveryDate:"",Description:"",Sender:"",SenderContact:"",SenderAddress:"",Receiver:"",ReceiverContact:"",ReceiverAddress:"",DeliveryBoy:"",DeliveryBoyContact:"",DeliveryBoyAddress:""}},28733:function(e,t,r){r.r(t),r.d(t,{default:function(){return ne}});var n=r(67294),l=r(54065),a=r(26447),o=r(2658),i=r(65295),c=r(44656),u=r(62640),p=r(99226),s=r(29602),f=r(5977),m=r(64062),y=(r(75113),r(28006),r(42694)),d=r(72132),b=r(15725),v=r(76914),h=r(55748),g=r(66309);function O(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function E(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?O(Object(r),!0).forEach((function(t){S(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):O(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function S(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function j(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var w={Name:"",Weight:"",From:"",To:"",ShipmentDate:"",ShipmentDeliveryDate:"",Description:""};function P(){var e,t,r=(e=(0,n.useState)(w),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return j(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?j(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=r[0],i=r[1],c=function(e){return i(E(E({},l),{},S({},e.target.name,e.target.value)))};return n.createElement(n.Fragment,null,n.createElement(b.ZP,{container:!0,spacing:3,py:2},g.Jf.map((function(e){return n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:6,xl:6},n.createElement(a.Z,{spacing:3},n.createElement(o.Z,{variant:"subtitle2"},e.label),n.createElement(h.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:c,name:e.id,autoComplete:e.id,value:l[e.id],type:e.type,multiline:!!e.multiline})))}))),n.createElement(a.Z,{spacing:3},n.createElement(v.Z,{variant:"contained",onClick:function(){console.log(l)}},"Save")))}function A(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function Z(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?A(Object(r),!0).forEach((function(t){D(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):A(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function D(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function x(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var C={Receiver:"",ReceiverContact:"",ReceiverAddress:""};function I(){var e,t,r=(e=(0,n.useState)(C),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return x(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?x(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=r[0],i=r[1],c=function(e){return i(Z(Z({},l),{},D({},e.target.name,e.target.value)))};return n.createElement(n.Fragment,null,n.createElement(b.ZP,{container:!0,spacing:3,py:2},g.mR.map((function(e){return n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:6,xl:6},n.createElement(a.Z,{spacing:3},n.createElement(o.Z,{variant:"subtitle2"},e.label),n.createElement(h.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:c,name:e.id,autoComplete:e.id,value:l[e.id],type:e.type,multiline:!!e.multiline})))}))),n.createElement(a.Z,{spacing:3},n.createElement(v.Z,{variant:"contained",onClick:function(){console.log(l)}},"Save")))}function R(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function k(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?R(Object(r),!0).forEach((function(t){B(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):R(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function B(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function T(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var F={DeliveryBoy:"",DeliveryBoyContact:"",DeliveryBoyAddress:""};function N(){var e,t,r=(e=(0,n.useState)(F),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return T(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?T(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=r[0],i=r[1],c=function(e){return i(k(k({},l),{},B({},e.target.name,e.target.value)))};return n.createElement(n.Fragment,null,n.createElement(b.ZP,{container:!0,spacing:3,py:2},g.G1.map((function(e){return n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:6,xl:6},n.createElement(a.Z,{spacing:3},n.createElement(o.Z,{variant:"subtitle2"},e.label),n.createElement(h.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:c,name:e.id,autoComplete:e.id,value:l[e.id],type:e.type,multiline:!!e.multiline})))}))),n.createElement(a.Z,{spacing:3},n.createElement(v.Z,{variant:"contained",onClick:function(){console.log(l)}},"Save")))}function M(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function W(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?M(Object(r),!0).forEach((function(t){U(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):M(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function U(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function $(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var V={Sender:"",SenderContact:"",SenderAddress:""};function q(){var e,t,r=(e=(0,n.useState)(V),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return $(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?$(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=r[0],i=r[1],c=function(e){return i(W(W({},l),{},U({},e.target.name,e.target.value)))};return n.createElement(n.Fragment,null,n.createElement(b.ZP,{container:!0,spacing:3,py:2},g.r8.map((function(e){return n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:6,xl:6},n.createElement(a.Z,{spacing:3},n.createElement(o.Z,{variant:"subtitle2"},e.label),n.createElement(h.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:c,name:e.id,autoComplete:e.id,value:l[e.id],type:e.type,multiline:!!e.multiline})))}))),n.createElement(a.Z,{spacing:3},n.createElement(v.Z,{variant:"contained",onClick:function(){console.log(l)}},"Save")))}var z=r(41796),G=r(67109),J=r(13356);function L(){return L=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},L.apply(this,arguments)}function H(e){var t=e.files,r=e.setFiles;return n.createElement(J.ZP,{onDrop:function(e){return function(e){e.length&&r(e)}(e)},accept:"image/*",multiple:!1},(function(e){var r=e.getRootProps,l=e.getInputProps;return n.createElement(p.Z,L({mt:2},r()),n.createElement("input",l()),n.createElement(G.Z,{src:t.length&&URL.createObjectURL(t[0]),sx:{mx:"auto",borderWidth:2,borderStyle:"solid",borderColor:"common.white",width:{xs:80,md:128},height:{xs:80,md:128}}}))}))}function K(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var Q=(0,s.ZP)("div")((function(e){var t=e.theme;return{"&:before":{backgroundColor:(0,z.Fq)(t.palette.primary.darker,.72),top:0,zIndex:9,content:"''",width:"100%",height:"100%",position:"absolute"}}})),X=(0,s.ZP)("div")((function(e){var t,r,n,l=e.theme;return t={left:0,right:0,zIndex:99,position:"absolute",marginTop:l.spacing(5)},r=l.breakpoints.up("md"),n={right:"auto",display:"flex",alignItems:"center",left:l.spacing(3),bottom:l.spacing(3)},r in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t}));function Y(){var e,t,r=(e=(0,n.useState)([]),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return K(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?K(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=r[0],a=r[1];return n.createElement(Q,null,n.createElement(X,null,n.createElement(H,{files:l,setFiles:a}),n.createElement(p.Z,{sx:{ml:{md:3},mt:{xs:1,md:0},color:"common.white",textAlign:{xs:"center",md:"left"}}},n.createElement(o.Z,{variant:"h4"},"Shipment"),n.createElement(o.Z,{sx:{opacity:.72}},"Desc"))),n.createElement(p.Z,{component:"img",sx:{position:"absolute",top:0,left:0,right:0,bottom:0}}))}function _(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,l,a=[],o=!0,i=!1;try{for(r=r.call(e);!(o=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==r.return||r.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return ee(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?ee(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function ee(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}function te(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}var re=(0,s.ZP)("div")((function(e){var t,r=e.theme;return te(t={zIndex:9,bottom:0,width:"100%",display:"flex",position:"absolute",backgroundColor:r.palette.background.paper},r.breakpoints.up("sm"),{justifyContent:"center"}),te(t,r.breakpoints.up("md"),{justifyContent:"flex-end",paddingRight:r.spacing(3)}),t})),ne=function(){(0,f.k6)();var e=_((0,n.useState)(!1),2),t=e[0],r=(e[1],_((0,n.useState)("shipment"),2)),s=r[0],b=r[1],v=[{value:"shipment",component:n.createElement(P,null)},{value:"delivery boy",component:n.createElement(N,null)},{value:"receiver",component:n.createElement(I,null)},{value:"sender",component:n.createElement(q,null)}];return n.createElement(m.Z,{title:"Shipment"},n.createElement(n.Fragment,null,n.createElement(l.Z,null,t?n.createElement(y.Z,null):null,n.createElement(d.Ix,null),n.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},n.createElement(o.Z,{variant:"h4",gutterBottom:!0},"Shipment")),n.createElement(i.Z,{sx:{mb:3,height:280,position:"relative"}},n.createElement(Y,null),n.createElement(re,null,n.createElement(c.Z,{value:s,scrollButtons:"auto",variant:"scrollable",allowScrollButtonsMobile:!0,onChange:function(e,t){b(t)}},v.map((function(e){return n.createElement(u.Z,{disableRipple:!0,key:e.value,value:e.value,label:e.value})}))))),v.map((function(e){return e.value===s&&n.createElement(p.Z,{key:e.value},e.component)})))))}}}]);