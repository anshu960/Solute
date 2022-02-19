"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[1029],{55748:function(e,t,r){var n=r(15725),o=r(2658),a=r(22620),l=r(45697),i=r.n(l),c=r(67294);function u(){return u=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},u.apply(this,arguments)}function p(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var f=(0,c.forwardRef)((function(e,t){var r,l,i=(r=(0,c.useState)(null),l=2,function(e){if(Array.isArray(e))return e}(r)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,i=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(i)throw o}}return a}}(r,l)||function(e,t){if(e){if("string"==typeof e)return p(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?p(e,t):void 0}}(r,l)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),f=i[0],s=i[1],d=e.classes,m=null,y=function(){var t=e.validationType,r=(e.value,e.name);t&&(s("Invalid"),e.setErrorValue("".concat(r)))},b=function(){s(null);var t=e.name;e.setErrorValue&&e.setErrorValue("".concat(t))};return(0,c.useImperativeHandle)(t,(function(){return{validateField:y,resetErrorCode:b}})),(f||e.errorMessage)&&(m=c.createElement(n.ZP,{item:!0,md:12,lg:12,className:d.alignCenter},c.createElement(o.Z,{color:"error",variant:"subtitle2"},f?f.errorMessage:e.errorMessage))),c.createElement(c.Fragment,null,c.createElement("div",null,c.createElement(a.Z,u({fullWidth:!0,multiline:e.multiline,type:e.type,placeholder:e.placeholder,variant:"outlined",id:e.id,name:e.name,value:e.value,onChange:function(t){e.onChange(t)},onBlur:y,onFocus:b},e)),m))}));f.propTypes={type:i().string,name:i().string.isRequired,id:i().string,multiline:i().bool,placeholder:i().string,value:i().string,validationType:i().oneOf(["","mandatory","email","password"]),onChange:i().func,setErrorValue:i().func},f.defaultProps={type:"text",value:"",validationType:""},t.Z=f},64062:function(e,t,r){var n=r(67294),o=r(45697),a=r.n(o),l=r(99226),i=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},c.apply(this,arguments)}var u=(0,n.forwardRef)((function(e,t){var r=e.children,o=e.title,a=void 0===o?"":o,u=function(e,t){if(null==e)return{};var r,n,o=function(e,t){if(null==e)return{};var r,n,o={},a=Object.keys(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||(o[r]=e[r]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(n=0;n<a.length;n++)r=a[n],t.indexOf(r)>=0||Object.prototype.propertyIsEnumerable.call(e,r)&&(o[r]=e[r])}return o}(e,i);return n.createElement(l.Z,c({ref:t},u),n.createElement(l.Z,null,n.createElement("title",null,a)),r)}));u.propTypes={children:a().node.isRequired,title:a().string},t.Z=u},51029:function(e,t,r){r.r(t),r.d(t,{default:function(){return oe}});var n=r(67294),o=r(54065),a=r(26447),l=r(2658),i=r(65295),c=r(44656),u=r(62640),p=r(99226),f=r(29602),s=r(5977),d=r(64062),m=(r(75113),r(28006),r(42694)),y=r(72132),b=r(15725),g=r(76914),v=r(55748);function h(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function w(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?h(Object(r),!0).forEach((function(t){O(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):h(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function O(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function E(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var j={name:"",email:"",contact:"",whatsapp:"",address:""},x=[{id:"name",label:"Name",type:"text",placeholder:"Enter name"},{id:"email",label:"Email",type:"email",placeholder:"Enter email"},{id:"contact",label:"Contact",type:"text",placeholder:"Enter contact"},{id:"whatsapp",label:"What's App",type:"text",placeholder:"Enter what's app"},{id:"address",label:"Address",type:"textarea",placeholder:"Enter address",multiline:!0}];function P(){var e,t,r=(e=(0,n.useState)(j),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,i=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return E(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?E(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),o=r[0],i=r[1],c=function(e){return i(w(w({},o),{},O({},e.target.name,e.target.value)))};return n.createElement(n.Fragment,null,n.createElement(b.ZP,{container:!0,spacing:3,py:2},x.map((function(e){return n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:6,xl:6},n.createElement(a.Z,{spacing:3},n.createElement(l.Z,{variant:"subtitle2"},e.label),n.createElement(v.Z,{fullWidth:!0,placeholder:e.placeholder,onChange:c,name:e.id,autoComplete:e.id,value:o[e.id],type:e.type,multiline:!!e.multiline})))}))),n.createElement(a.Z,{spacing:3},n.createElement(g.Z,{variant:"contained",onClick:function(){console.log(o)}},"Save")))}var S=r(22620),Z=r(80270),C=r(6867),A=r(22961),k=r(72450);function I(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function D(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?I(Object(r),!0).forEach((function(t){R(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):I(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function R(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function T(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,i=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return M(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?M(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function M(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var F={pin:(0,r(98401).Hh)()||"",showPassword:!1};function z(){var e=T((0,n.useState)(F),2),t=e[0],r=e[1],o=T((0,n.useState)({}),2),i=o[0],c=o[1];return n.createElement(b.ZP,{container:!0,spacing:3},n.createElement(b.ZP,{item:!0,xs:12,md:12,lg:4,xl:4},n.createElement(a.Z,{spacing:3},n.createElement(l.Z,{variant:"subtitle2"},"Pin"),n.createElement(S.Z,{fullWidth:!0,placeholder:"Pin",onChange:function(e){r(D(D({},t),{},R({},e.target.name,e.target.value))),c({pin:""})},name:"pin",autoComplete:"pin",type:t.showPassword?"text":"password",value:t.pin,error:i.pin,helperText:i.pin,InputProps:{endAdornment:n.createElement(Z.Z,{position:"end"},n.createElement(C.Z,{onClick:function(){r(D(D({},t),{},{showPassword:!t.showPassword}))}},t.showPassword?n.createElement(k.Z,null):n.createElement(A.Z,null)))}}),n.createElement(g.Z,{variant:"contained",onClick:function(){console.log(t.pin)}},"Save"))))}var B=r(41796),U=r(67109),W=r(13356);function H(){return H=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},H.apply(this,arguments)}function L(e){var t=e.files,r=e.setFiles;return n.createElement(W.ZP,{onDrop:function(e){return function(e){e.length&&r(e)}(e)},accept:"image/*",multiple:!1},(function(e){var r=e.getRootProps,o=e.getInputProps;return n.createElement(p.Z,H({mt:2},r()),n.createElement("input",o()),n.createElement(U.Z,{src:t.length&&URL.createObjectURL(t[0]),sx:{mx:"auto",borderWidth:2,borderStyle:"solid",borderColor:"common.white",width:{xs:80,md:128},height:{xs:80,md:128}}}))}))}function N(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}var V=(0,f.ZP)("div")((function(e){var t=e.theme;return{"&:before":{backgroundColor:(0,B.Fq)(t.palette.primary.darker,.72),top:0,zIndex:9,content:"''",width:"100%",height:"100%",position:"absolute"}}})),$=(0,f.ZP)("div")((function(e){var t,r,n,o=e.theme;return t={left:0,right:0,zIndex:99,position:"absolute",marginTop:o.spacing(5)},r=o.breakpoints.up("md"),n={right:"auto",display:"flex",alignItems:"center",left:o.spacing(3),bottom:o.spacing(3)},r in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t}));function q(){var e,t,r=(e=(0,n.useState)([]),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,i=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return N(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?N(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),o=r[0],a=r[1];return n.createElement(V,null,n.createElement($,null,n.createElement(L,{files:o,setFiles:a}),n.createElement(p.Z,{sx:{ml:{md:3},mt:{xs:1,md:0},color:"common.white",textAlign:{xs:"center",md:"left"}}},n.createElement(l.Z,{variant:"h4"},"Profile"),n.createElement(l.Z,{sx:{opacity:.72}},"Desc"))),n.createElement(p.Z,{component:"img",sx:{position:"absolute",top:0,left:0,right:0,bottom:0}}))}var Y=r(306),G=r(38658);function J(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function K(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?J(Object(r),!0).forEach((function(t){Q(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):J(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function Q(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}var X=(0,f.ZP)("div")((function(e){var t=e.theme;return{width:"calc(100% + 2px)",marginLeft:-1,marginBottom:-1,"& .fc":{"--fc-list-event-dot-width":"8px","--fc-border-color":t.palette.divider,"--fc-event-border-color":t.palette.info.light,"--fc-now-indicator-color":t.palette.error.main,"--fc-today-bg-color":t.palette.action.selected,"--fc-page-bg-color":t.palette.background.default,"--fc-neutral-bg-color":t.palette.background.neutral,"--fc-list-event-hover-bg-color":t.palette.action.hover,"--fc-highlight-color":(0,B.Fq)(t.palette.primary.main,.08)},"& .fc .fc-license-message":{display:"none"},"& .fc a":{color:t.palette.text.primary},"& .fc .fc-col-header ":{boxShadow:"inset 0 -1px 0 ".concat(t.palette.divider),"& th":{borderColor:"transparent"},"& .fc-col-header-cell-cushion":K(K({},t.typography.subtitle2),{},{padding:"13px 0"})},"& .fc .fc-event":{borderColor:"transparent",backgroundColor:"transparent"},"& .fc .fc-event .fc-event-main":{padding:"2px 4px",borderRadius:4,backgroundColor:t.palette.common.white,transition:t.transitions.create("filter"),"&:hover":{filter:"brightness(0.92)"},"&:before,&:after":{top:0,left:0,width:"100%",height:"100%",content:"''",borderRadius:4,position:"absolute",boxSizing:"border-box"},"&:before":{zIndex:8,opacity:.32,border:"solid 1px currentColor"},"&:after":{zIndex:7,opacity:.24,backgroundColor:"currentColor"}},"& .fc .fc-event .fc-event-main-frame":{fontSize:13,lineHeight:"20px",filter:"brightness(0.24)"},"& .fc .fc-daygrid-event .fc-event-title":{overflow:"hidden",whiteSpace:"nowrap",textOverflow:"ellipsis"},"& .fc .fc-event .fc-event-time":{padding:0,overflow:"unset",fontWeight:t.typography.fontWeightBold},"& .fc .fc-popover":{border:0,overflow:"hidden",boxShadow:t.customShadows.z20,borderRadius:t.shape.borderRadius,backgroundColor:t.palette.background.paper},"& .fc .fc-popover-header":K(K({},t.typography.subtitle2),{},{padding:t.spacing(1),backgroundColor:t.palette.grey[50012],borderBottom:"solid 1px ".concat(t.palette.divider)}),"& .fc .fc-popover-close":{opacity:.48,transition:t.transitions.create("opacity"),"&:hover":{opacity:1}},"& .fc .fc-more-popover .fc-popover-body":{padding:t.spacing(1.5)},"& .fc .fc-popover-body":{"& .fc-daygrid-event.fc-event-start, & .fc-daygrid-event.fc-event-end":{margin:"2px 0"}},"& .fc .fc-day-other .fc-daygrid-day-top":{opacity:1,"& .fc-daygrid-day-number":{color:t.palette.text.disabled}},"& .fc .fc-daygrid-day-number":K(K({},t.typography.body2),{},{padding:t.spacing(1,1,0)}),"& .fc .fc-daygrid-event":{marginTop:4},"& .fc .fc-daygrid-event.fc-event-start, & .fc .fc-daygrid-event.fc-event-end":{marginLeft:4,marginRight:4},"& .fc .fc-daygrid-more-link":K(K({},t.typography.caption),{},{color:t.palette.text.secondary}),"& .fc .fc-timegrid-axis-cushion":K(K({},t.typography.body2),{},{color:t.palette.text.secondary}),"& .fc .fc-timegrid-slot-label-cushion":K({},t.typography.body2),"& .fc-direction-ltr .fc-list-day-text, .fc-direction-rtl .fc-list-day-side-text, .fc-direction-ltr .fc-list-day-side-text, .fc-direction-rtl .fc-list-day-text":K({},t.typography.subtitle2),"& .fc .fc-list-event":K(K({},t.typography.body2),{},{"& .fc-list-event-time":{color:t.palette.text.secondary}}),"& .fc .fc-list-table":{"& th, td":{borderColor:"transparent"}}}}));function _(){var e,t,r,o=[{title:"Sick Leave",start:("YEAR-MONTH-10",e=new Date,t=e.getFullYear().toString(),r=(e.getMonth()+1).toString(),1===r.length&&(r="0"+r),"YEAR-MONTH-10".replace("YEAR",t).replace("MONTH",r))}];return n.createElement(b.ZP,{container:!0,spacing:3},n.createElement(b.ZP,{item:!0,xs:12,md:4},n.createElement(a.Z,{spacing:3},n.createElement(l.Z,{variant:"h6"},"Sick Leave : 1"))),n.createElement(b.ZP,{item:!0,xs:12,md:8},n.createElement(a.Z,{spacing:3},n.createElement(X,null,n.createElement(Y.ZPm,{defaultView:"dayGridMonth",plugins:[G.ZP],events:o})))))}function ee(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],l=!0,i=!1;try{for(r=r.call(e);!(l=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);l=!0);}catch(e){i=!0,o=e}finally{try{l||null==r.return||r.return()}finally{if(i)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return te(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?te(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function te(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n}function re(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}(0,f.ZP)("div")((function(e){var t,r,n,o=e.theme;return t={display:"flex",alignItems:"center",flexDirection:"column",padding:o.spacing(3,0)},r=o.breakpoints.up("sm"),n={flexDirection:"row",padding:o.spacing(1.75,3),justifyContent:"space-between"},r in t?Object.defineProperty(t,r,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[r]=n,t}));var ne=(0,f.ZP)("div")((function(e){var t,r=e.theme;return re(t={zIndex:9,bottom:0,width:"100%",display:"flex",position:"absolute",backgroundColor:r.palette.background.paper},r.breakpoints.up("sm"),{justifyContent:"center"}),re(t,r.breakpoints.up("md"),{justifyContent:"flex-end",paddingRight:r.spacing(3)}),t})),oe=function(){(0,s.k6)();var e=ee((0,n.useState)(!1),2),t=e[0],r=(e[1],ee((0,n.useState)("profile"),2)),f=r[0],b=r[1],g=[{value:"profile",component:n.createElement(P,null)},{value:"leaves",component:n.createElement(_,null)},{value:"account",component:n.createElement(z,null)}];return n.createElement(d.Z,{title:"Profile"},n.createElement(n.Fragment,null,n.createElement(o.Z,null,t?n.createElement(m.Z,null):null,n.createElement(y.Ix,null),n.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},n.createElement(l.Z,{variant:"h4",gutterBottom:!0},"Profile")),n.createElement(i.Z,{sx:{mb:3,height:280,position:"relative"}},n.createElement(q,null),n.createElement(ne,null,n.createElement(c.Z,{value:f,scrollButtons:"auto",variant:"scrollable",allowScrollButtonsMobile:!0,onChange:function(e,t){b(t)}},g.map((function(e){return n.createElement(u.Z,{disableRipple:!0,key:e.value,value:e.value,label:e.value})}))))),g.map((function(e){return e.value===f&&n.createElement(p.Z,{key:e.value},e.component)})))))}}}]);