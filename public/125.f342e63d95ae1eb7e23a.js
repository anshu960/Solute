"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[125],{55748:function(e,t,n){var r=n(15725),l=n(2658),a=n(22620),o=n(45697),i=n.n(o),c=n(67294);function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},u.apply(this,arguments)}function s(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var p=(0,c.forwardRef)((function(e,t){var n,o,i=(n=(0,c.useState)(null),o=2,function(e){if(Array.isArray(e))return e}(n)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(n,o)||function(e,t){if(e){if("string"==typeof e)return s(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?s(e,t):void 0}}(n,o)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),p=i[0],d=i[1],m=e.classes,f=null,y=function(){var t=e.validationType,n=(e.value,e.name);t&&(d("Invalid"),e.setErrorValue("".concat(n)))},h=function(){d(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,c.useImperativeHandle)(t,(function(){return{validateField:y,resetErrorCode:h}})),(p||e.errorMessage)&&(f=c.createElement(r.ZP,{item:!0,md:12,lg:12,className:m.alignCenter},c.createElement(l.Z,{color:"error",variant:"subtitle2"},p?p.errorMessage:e.errorMessage))),c.createElement(c.Fragment,null,c.createElement("div",null,c.createElement(a.Z,u({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:y,onFocus:h},e)),f))}));p.propTypes={type:i().string,name:i().string.isRequired,id:i().string,multiline:i().bool,placeholder:i().string,value:i().string,validationType:i().oneOf(["","mandatory","email","password"]),onChange:i().func,setErrorValue:i().func},p.defaultProps={type:"text",value:"",validationType:""},t.Z=p},64062:function(e,t,n){var r=n(67294),l=n(45697),a=n.n(l),o=n(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,l=e.title,a=void 0===l?"":l,u=function(e,t){if(null==e)return{};var n,r,l=function(e,t){if(null==e)return{};var n,r,l={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(l[n]=e[n]);return l}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(l[n]=e[n])}return l}(e,i);return r.createElement(o.Z,c({ref:t},u),r.createElement(o.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},70125:function(e,t,n){n.r(t),n.d(t,{default:function(){return Y}});var r=n(67294),l=n(54065),a=n(26447),o=n(2658),i=n(99226),c=n(22620),u=n(65295),s=n(97896),p=n(76914),d=n(9198),m=n.n(d),f=n(44290),y=n(73327),h=n(9573),v=n(38732),g=n(46926),b=n(56011),E=n(3694),S=n(5977),x=(n(75113),n(28006),n(42694),n(72132)),Z=n(64062),A=n(96910),w=n(39704),O=n(77512),C=n(15725),D=n(77750),j=n(56408),k=n(88979);function I(e){var t=e.handleConfirm,n=e.setOpen;return r.createElement(r.Fragment,null,r.createElement(p.Z,{variant:"contained",color:"info",onClick:function(){n({open:!1,shipment:{}})}},"Cancel"),r.createElement(p.Z,{variant:"contained",color:"success",onClick:t},"Confirm"))}var P=n(12639),B=n(55748),T=[{id:"SenderAddress",label:"Sender Address",type:"text",placeholder:"Enter Sender Address",disabled:!0},{id:"ReceiverAddress",label:"Receiver Address",type:"text",placeholder:"Enter Receiver Address",disabled:!0}];function R(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function F(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?R(Object(n),!0).forEach((function(t){z(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):R(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function N(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return M(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?M(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function M(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function z(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var V={SenderAddress:"",ReceiverAddress:""},W=function(e){return z({},"stopage"+e,[{id:"Stop".concat(e,"Address"),label:"Stopage".concat(e," Address"),type:"text",placeholder:"Enter Stopage".concat(e," Address")},{id:"Stop".concat(e,"DeliveryBoyName"),label:"Delivery Boy Name",type:"text",placeholder:"Enter Delivery Boy Name"},{id:"Stop".concat(e,"DeliveryBoyContact"),label:"Delivery Boy Contact",type:"text",placeholder:"Enter Delivery Boy Contact"},{id:"Stop".concat(e,"DeliveryBoyAddress"),label:"Delivery Boy Address",type:"text",placeholder:"Enter Delivery Boy Address"},{id:"Stop".concat(e,"Status"),label:"Status",type:"select",placeholder:"Select Status",options:[{label:"Picked",value:1},{label:"In Transit",value:2},{label:"Delivered",value:3},{label:"Dropped",value:4}]}])};function U(e){var t=e.shipment,n=e.setOpen,l=N((0,r.useState)(V),2),o=l[0],i=l[1],c=N((0,r.useState)(W(1)),2),u=c[0],s=c[1],d=(0,r.useRef)(null);console.log(t),r.useEffect((function(){var e=Object.keys(t).length?t:null;e&&i(e)}),[t]);var m=function(e){i(F(F({},o),{},z({},e.target.name,e.target.value)))},f=function(e,t){var n=t.name,r=e&&e.value||"";i(F(F({},o),{},z({},n,r)))},y=function(){var e=Object.keys(u).length+1,t=W(e);s(F(F({},u),t))},h=function(e){switch(e.type){case"button":return r.createElement(p.Z,{variant:"contained",color:"info",onClick:y},"Add Stopage");case"select":return function(e){return r.createElement(P.ZP,{name:e.id,options:e.options,placeholder:e.placeholder,isClearable:!0,value:e.options.filter((function(t){return t.value===o[e.id]})),onChange:f})}(e);default:return function(e){return r.createElement(B.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:m,name:e.id,autoComplete:e.id,value:o[e.id],type:e.type,multiline:!!e.multiline,disabled:e.disabled})}(e)}};return r.createElement(r.Fragment,{style:{outerHeight:800}},r.createElement(D.Z,{dividers:!0},r.createElement(j.Z,{id:"scroll-dialog-description",ref:d,tabIndex:-1},r.createElement("form",{action:""},r.createElement(a.Z,{spacing:3},r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2},r.createElement(C.ZP,{container:!0,spacing:3,py:2},T.map((function(e){return r.createElement(C.ZP,{item:!0,xs:12,md:12,lg:6,xl:4},r.createElement(a.Z,{spacing:3},h(e)))}))))),r.createElement(a.Z,{spacing:3},r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2})),r.createElement(a.Z,{spacing:3},function(){var e=[];for(var t in u)e.push(r.createElement(a.Z,{direction:{xs:"column",sm:"row"},spacing:2},r.createElement(C.ZP,{container:!0,spacing:3,py:2},u[t].map((function(e){return r.createElement(C.ZP,{item:!0,xs:12,md:12,lg:6,xl:4},r.createElement(a.Z,{spacing:3},h(e)))})))));return e}())))),r.createElement(k.Z,{sx:{justifyContent:"end"}},r.createElement(I,{handleConfirm:function(){console.log(o)},setOpen:n})))}var G=n(83428);function H(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return L(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?L(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function L(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function $(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var q=(0,f.Z)((function(e){var t,n;return(0,y.Z)({table:{width:"100%",borderSpacing:"0px 0px",border:"1px solid #7070704D","& tr":{"&:nth-child(1)":{"& th":{textAlign:"center",borderBottom:"1px solid #7070704D",fontSize:"15px",color:"#1e1e1f",fontFamily:"Gilroy-Semibold",padding:"5px 10px",backgroundColor:"#b0b0b1","& span":{padding:"0px",color:"#000","& svg":{verticalAlign:"top"}}}}},"& td":{textAlign:"center","&:nth-child(2)":{color:"#428BCA"},"&:last-child":{color:"#428BCA",cursor:"pointer"}}},selctAutTar:(t={width:"14%",marginRight:"7px",position:"relative"},$(t,e.breakpoints.between("1024","1400"),{width:"22%"}),$(t,"& div",{width:"100%",borderRadius:"2px"}),$(t,"& input",{padding:"15px 14px",fontSize:"15px"}),t),actionList:{display:"flex",justifyContent:"flex-end",flexGrow:"1"},selctAutTarDate:(n={color:"#fff",marginRight:"7px",position:"relative"},$(n,e.breakpoints.between("1024","1400"),{width:"18%"}),$(n,"& input",{padding:"10px 0px 10px 10px",border:"1px solid #cccccc",borderRadius:"4px"}),n),calendarIcon:{position:"absolute",right:"40px",top:"14px",color:"#8F8FB3",fontSize:"14px",zIndex:"99"},datePicker:{height:"48px"},textField:{color:"#428BCA",border:"1px solid #428BCA",fontSize:"15px",fontFamily:"Gilroy-Semibold",padding:"5px 10px","& input":{color:"#428BCA"}},search:{"& input":{height:"17px"}}})})),Y=function(){var e=q(),t=(0,w.I0)(),n=(0,S.k6)(),d=r.useRef(),f=(0,w.v9)((function(e){return e.shipment.allShipment})),y=H((0,r.useState)(new Date),2),C=y[0],D=y[1],j=H((0,r.useState)(new Date),2),k=j[0],I=j[1],P=H((0,r.useState)({open:!1,shipment:null}),2),B=P[0],T=P[1];return(0,r.useEffect)((function(){t((0,G.n)(C,k))}),[C,k]),r.createElement(Z.Z,{title:"History"},r.createElement(r.Fragment,null,r.createElement(l.Z,null,r.createElement(x.Ix,null),r.createElement(O.r,{body:r.createElement(U,{shipment:B.shipment,setOpen:T}),handleClose:function(){T({open:!1,shipment:{}})},scroll:"paper",title:"Add Stopage",open:B.open,dialogWidth:"xl"}),r.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(o.Z,{variant:"h4",gutterBottom:!0},"Shipments"),r.createElement(i.Z,{className:e.actionList},r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(c.Z,{className:e.search,fullWidth:!0,placeholder:"Search",name:"search",autoComplete:!1,type:"text"})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"Start Date",className:e.butonScrmenuSe,selected:C,onChange:function(e){e&&D(e)}})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"End Date",className:e.butonScrmenuSe,selected:k,onChange:function(e){e&&I(e)}})))),r.createElement(u.Z,null,r.createElement(i.Z,null,r.createElement(s.Z,{sx:{minWidth:800}},r.createElement(h.Z,{style:{width:"100%",textAlign:"center"},ref:d},r.createElement(b.Z,null,r.createElement(E.Z,null,r.createElement(g.Z,null,"ID"),r.createElement(g.Z,null,"From"),r.createElement(g.Z,null,"To"),r.createElement(g.Z,null,"Delivery Date"),r.createElement(g.Z,null,"Status"),r.createElement(g.Z,null,"Stopage"),r.createElement(g.Z,null,"Details"),r.createElement(g.Z,null,"Track"))),r.createElement(v.Z,null,f&&f.length?f.map((function(e){return r.createElement(E.Z,{key:e.ShipmentID},r.createElement(g.Z,null,e.ShipmentID),r.createElement(g.Z,null,e.SenderAddress),r.createElement(g.Z,null,e.ReceiverAddress),r.createElement(g.Z,null,(t=e.ShipmentDeliveryDate,(l=new Date(t)).getDate()+"-"+(l.getMonth()+1)+"-"+l.getFullYear())),r.createElement(g.Z,null,e.ShipmentDelivered?"Delivered":"In-progress"),r.createElement(g.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){T({open:!0,shipment:e})}},"Add")),r.createElement(g.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){n.push({pathname:A.vB.delivery.profile,search:"?id=".concat(e.ShipmentID)})}},"View")),r.createElement(g.Z,null,r.createElement(p.Z,{variant:"text",onClick:function(){window.open("/#".concat(A.ko.shipmentTrack,"?id=").concat(e.ShipmentID),"_blank")}},"Locate")));var t,l})):r.createElement(E.Z,null,r.createElement(g.Z,{colSpan:8},"Unable to find shipments for selected date range"))))))))))}}}]);