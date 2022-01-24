"use strict";(self.webpackChunkfuelme=self.webpackChunkfuelme||[]).push([[5086],{55748:function(e,t,n){var r=n(15725),l=n(2658),a=n(51732),o=n(45697),i=n.n(o),c=n(67294);function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},u.apply(this,arguments)}function p(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var s=(0,c.forwardRef)((function(e,t){var n,o,i=(n=(0,c.useState)(null),o=2,function(e){if(Array.isArray(e))return e}(n)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(n,o)||function(e,t){if(e){if("string"==typeof e)return p(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?p(e,t):void 0}}(n,o)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),s=i[0],d=i[1],m=e.classes,f=null,h=function(){var t=e.validationType,n=(e.value,e.name);t&&(d("Invalid"),e.setErrorValue("".concat(n)))},g=function(){d(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,c.useImperativeHandle)(t,(function(){return{validateField:h,resetErrorCode:g}})),(s||e.errorMessage)&&(f=c.createElement(r.ZP,{item:!0,md:12,lg:12,className:m.alignCenter},c.createElement(l.Z,{color:"error",variant:"subtitle2"},s?s.errorMessage:e.errorMessage))),c.createElement(c.Fragment,null,c.createElement("div",null,c.createElement(a.Z,u({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:h,onFocus:g},e)),f))}));s.propTypes={type:i().string,name:i().string.isRequired,id:i().string,multiline:i().bool,placeholder:i().string,value:i().string,validationType:i().oneOf(["","mandatory","email","password"]),onChange:i().func,setErrorValue:i().func},s.defaultProps={type:"text",value:"",validationType:""},t.Z=s},64062:function(e,t,n){var r=n(67294),l=n(45697),a=n.n(l),o=n(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},c.apply(this,arguments)}var u=(0,r.forwardRef)((function(e,t){var n=e.children,l=e.title,a=void 0===l?"":l,u=function(e,t){if(null==e)return{};var n,r,l=function(e,t){if(null==e)return{};var n,r,l={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(l[n]=e[n]);return l}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(l[n]=e[n])}return l}(e,i);return r.createElement(o.Z,c({ref:t},u),r.createElement(o.Z,null,r.createElement("title",null,a)),n)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},75086:function(e,t,n){n.r(t);var r=n(67294),l=n(54065),a=n(26447),o=n(2658),i=n(99226),c=n(51732),u=n(65295),p=n(97896),s=n(76914),d=n(9198),m=n.n(d),f=n(44290),h=n(73327),g=n(9573),b=n(38732),y=n(46926),x=n(56011),v=n(3694),E=n(5977),w=(n(75113),n(28006),n(42694),n(72132)),Z=n(64062),S=n(96910),A=(n(55748),n(39704)),C=n(35220);function k(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,i=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){i=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(i)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return I(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?I(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function I(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function T(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var O=(0,f.Z)((function(e){var t,n;return(0,h.Z)({rightSection:{width:"96%"},inRightSection:T({padding:"25px 70px 20px 32px"},e.breakpoints.between("1024","1400"),{padding:"18px"}),adminButton:{display:"flex",justifyContent:"space-between",alignItems:"center",color:"#fff"},addNewGroup:{color:"#fff",border:"1px solid #428BCA",borderRadius:"2px",backgroundColor:"#428BCA",height:"48px",marginLeft:"15px",width:"205px","&:hover":{backgroundColor:"#428BCA"}},inAdminButton:{display:"flex"},addNewGroupBulk:T({width:"165px"},e.breakpoints.between("1024","1400"),{marginLeft:"0px"}),bottomButtonExpert:{cursor:"pointer",textAlign:"right"},table:{width:"100%",borderSpacing:"0px 0px",border:"1px solid #7070704D","& tr":{"&:nth-child(1)":{"& th":{textAlign:"center",borderBottom:"1px solid #7070704D",fontSize:"15px",color:"#1e1e1f",fontFamily:"Gilroy-Semibold",padding:"5px 10px",backgroundColor:"#b0b0b1","& span":{padding:"0px",color:"#000","& svg":{verticalAlign:"top"}}}}},"& td":{textAlign:"center","&:nth-child(2)":{color:"#428BCA"},"&:last-child":{color:"#428BCA",cursor:"pointer"}}},selectBoxStyle:{"& span":{display:"none"}},selectBoxSectionTarget:{display:"flex",justifyContent:"space-between",alignItems:"flex-end"},selctAutTar:(t={width:"14%",marginRight:"7px",position:"relative"},T(t,e.breakpoints.between("1024","1400"),{width:"22%"}),T(t,"& div",{width:"100%",borderRadius:"2px"}),T(t,"& input",{padding:"15px 14px",fontSize:"15px"}),t),actionList:{display:"flex",justifyContent:"flex-end",flexGrow:"1"},selctAutTarDate:(n={color:"#fff",marginRight:"7px",position:"relative"},T(n,e.breakpoints.between("1024","1400"),{width:"18%"}),T(n,"& input",{padding:"10px 0px 10px 10px",border:"1px solid #cccccc",borderRadius:"4px"}),n),calendarIcon:{position:"absolute",right:"40px",top:"14px",color:"#8F8FB3",fontSize:"14px",zIndex:"99"},datePicker:{height:"48px"},textField:{color:"#428BCA",border:"1px solid #428BCA",fontSize:"15px",fontFamily:"Gilroy-Semibold",padding:"5px 10px","& input":{color:"#428BCA"}},saleRate:{border:"none",textAlign:"center",width:"85px",height:"48px",display:"inline-block",lineHeight:"26px",borderRadius:"4px",backgroundColor:e.palette.grey[300]},saleRateActive:{border:"none",textAlign:"center",width:"85px",height:"48px",display:"inline-block",lineHeight:"26px",borderRadius:"4px",backgroundColor:e.palette.grey[200]},search:{"& input":{height:"17px"}}})}));t.default=function(){var e=O(),t=(0,A.I0)(),n=(0,E.k6)(),d=r.useRef(),f=(0,A.v9)((function(e){return e.invoice.allInvoice})),h=k((0,r.useState)(new Date),2),I=h[0],T=h[1],j=k((0,r.useState)(new Date),2),B=j[0],D=j[1];return(0,r.useEffect)((function(){t((0,C.k9)(I,B))}),[I,B]),r.createElement(Z.Z,{title:"History"},r.createElement(r.Fragment,null,r.createElement(l.Z,null,r.createElement(w.Ix,null),r.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(o.Z,{variant:"h4",gutterBottom:!0},"Shipments"),r.createElement(i.Z,{className:e.actionList},r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(c.Z,{className:e.search,fullWidth:!0,placeholder:"Search",name:"search",autoComplete:!1,type:"text"})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"Start Date",className:e.butonScrmenuSe,selected:I,onChange:function(e){e&&T(e)}})),r.createElement(i.Z,{component:"span",className:e.selctAutTarDate},r.createElement(m(),{placeholderText:"End Date",className:e.butonScrmenuSe,selected:B,onChange:function(e){e&&D(e)}})))),r.createElement(u.Z,null,r.createElement(i.Z,null,r.createElement(p.Z,{sx:{minWidth:800}},r.createElement(g.Z,{style:{width:"100%",textAlign:"center"},ref:d},r.createElement(x.Z,null,r.createElement(v.Z,null,r.createElement(y.Z,null,"ID"),r.createElement(y.Z,null,"Name"),r.createElement(y.Z,null,"DATE"),r.createElement(y.Z,null,"From"),r.createElement(y.Z,null,"To"),r.createElement(y.Z,{onClick:function(){window.open("/#".concat(S.ko.shipmentTrack,"?id=","123"),"_blank")}},"click"),r.createElement(y.Z,{onClick:function(){n.push({pathname:S.vB.delivery.profile,search:"?id=".concat("123")})}},"click"))),r.createElement(b.Z,null,f&&f.length?f.map((function(e,t){return r.createElement(v.Z,{key:t},r.createElement(y.Z,null,e.InvoiceNumber),r.createElement(y.Z,null,e.TotalPrice),r.createElement(y.Z,null,(n=e.InvoiceDate,(l=new Date(n)).getDate()+"-"+(l.getMonth()+1)+"-"+l.getFullYear())),r.createElement(y.Z,null,e.IGST),r.createElement(y.Z,null,e.CGST),r.createElement(y.Z,null,r.createElement(s.Z,{variant:"text",onClick:function(){window.open("/#".concat(S.ko.shipmentTrack,"?id=").concat(e.InvoiceNumber),"_blank")}},"Show")));var n,l})):r.createElement(v.Z,null,r.createElement(y.Z,{colSpan:8},"Unable to find shipments for selected date range"))))))))))}}}]);