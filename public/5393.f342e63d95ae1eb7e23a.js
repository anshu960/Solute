"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[5393],{22887:function(e,t,n){n.d(t,{Z:function(){return L}});var o=n(63366),r=n(87462),a=n(67294),i=n(27192),l=n(41796),c=n(86010),d=n(98216),s=n(29602),u=n(72893),p=n(74423),m=n(76637),f=n(28979),h=n(76087);function x(e){return(0,f.Z)("PrivateSwitchBase",e)}(0,h.Z)("PrivateSwitchBase",["root","checked","disabled","input","edgeStart","edgeEnd"]);var g=n(85893);const b=["autoFocus","checked","checkedIcon","className","defaultChecked","disabled","disableFocusRipple","edge","icon","id","inputProps","inputRef","name","onBlur","onChange","onFocus","readOnly","required","tabIndex","type","value"],y=(0,s.ZP)(m.Z)((({ownerState:e})=>(0,r.Z)({padding:9,borderRadius:"50%"},"start"===e.edge&&{marginLeft:"small"===e.size?-3:-12},"end"===e.edge&&{marginRight:"small"===e.size?-3:-12}))),v=(0,s.ZP)("input")({cursor:"inherit",position:"absolute",opacity:0,width:"100%",height:"100%",top:0,left:0,margin:0,padding:0,zIndex:1});var E=a.forwardRef((function(e,t){const{autoFocus:n,checked:a,checkedIcon:l,className:s,defaultChecked:m,disabled:f,disableFocusRipple:h=!1,edge:E=!1,icon:Z,id:k,inputProps:S,inputRef:w,name:I,onBlur:C,onChange:A,onFocus:R,readOnly:P,required:B,tabIndex:j,type:O,value:L}=e,z=(0,o.Z)(e,b),[D,W]=(0,u.Z)({controlled:a,default:Boolean(m),name:"SwitchBase",state:"checked"}),F=(0,p.Z)();let N=f;F&&void 0===N&&(N=F.disabled);const M="checkbox"===O||"radio"===O,G=(0,r.Z)({},e,{checked:D,disabled:N,disableFocusRipple:h,edge:E}),T=(e=>{const{classes:t,checked:n,disabled:o,edge:r}=e,a={root:["root",n&&"checked",o&&"disabled",r&&`edge${(0,d.Z)(r)}`],input:["input"]};return(0,i.Z)(a,x,t)})(G);return(0,g.jsxs)(y,(0,r.Z)({component:"span",className:(0,c.Z)(T.root,s),centerRipple:!0,focusRipple:!h,disabled:N,tabIndex:null,role:void 0,onFocus:e=>{R&&R(e),F&&F.onFocus&&F.onFocus(e)},onBlur:e=>{C&&C(e),F&&F.onBlur&&F.onBlur(e)},ownerState:G,ref:t},z,{children:[(0,g.jsx)(v,(0,r.Z)({autoFocus:n,checked:a,defaultChecked:m,className:T.input,disabled:N,id:M&&k,name:I,onChange:e=>{if(e.nativeEvent.defaultPrevented)return;const t=e.target.checked;W(t),A&&A(e,t)},readOnly:P,ref:w,required:B,ownerState:G,tabIndex:j,type:O},"checkbox"===O&&void 0===L?{}:{value:L},S)),D?l:Z]}))})),Z=n(82066),k=(0,Z.Z)((0,g.jsx)("path",{d:"M19 5v14H5V5h14m0-2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2z"}),"CheckBoxOutlineBlank"),S=(0,Z.Z)((0,g.jsx)("path",{d:"M19 3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.11 0 2-.9 2-2V5c0-1.1-.89-2-2-2zm-9 14l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"}),"CheckBox"),w=(0,Z.Z)((0,g.jsx)("path",{d:"M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-2 10H7v-2h10v2z"}),"IndeterminateCheckBox"),I=n(89130);function C(e){return(0,f.Z)("MuiCheckbox",e)}var A=(0,h.Z)("MuiCheckbox",["root","checked","disabled","indeterminate","colorPrimary","colorSecondary"]);const R=["checkedIcon","color","icon","indeterminate","indeterminateIcon","inputProps","size"],P=(0,s.ZP)(E,{shouldForwardProp:e=>(0,s.FO)(e)||"classes"===e,name:"MuiCheckbox",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,n.indeterminate&&t.indeterminate,"default"!==n.color&&t[`color${(0,d.Z)(n.color)}`]]}})((({theme:e,ownerState:t})=>(0,r.Z)({color:e.palette.text.secondary},!t.disableRipple&&{"&:hover":{backgroundColor:(0,l.Fq)("default"===t.color?e.palette.action.active:e.palette[t.color].main,e.palette.action.hoverOpacity),"@media (hover: none)":{backgroundColor:"transparent"}}},"default"!==t.color&&{[`&.${A.checked}, &.${A.indeterminate}`]:{color:e.palette[t.color].main},[`&.${A.disabled}`]:{color:e.palette.action.disabled}}))),B=(0,g.jsx)(S,{}),j=(0,g.jsx)(k,{}),O=(0,g.jsx)(w,{});var L=a.forwardRef((function(e,t){var n,l;const c=(0,I.Z)({props:e,name:"MuiCheckbox"}),{checkedIcon:s=B,color:u="primary",icon:p=j,indeterminate:m=!1,indeterminateIcon:f=O,inputProps:h,size:x="medium"}=c,b=(0,o.Z)(c,R),y=m?f:p,v=m?f:s,E=(0,r.Z)({},c,{color:u,indeterminate:m,size:x}),Z=(e=>{const{classes:t,indeterminate:n,color:o}=e,a={root:["root",n&&"indeterminate",`color${(0,d.Z)(o)}`]},l=(0,i.Z)(a,C,t);return(0,r.Z)({},t,l)})(E);return(0,g.jsx)(P,(0,r.Z)({type:"checkbox",inputProps:(0,r.Z)({"data-indeterminate":m},h),icon:a.cloneElement(y,{fontSize:null!=(n=y.props.fontSize)?n:x}),checkedIcon:a.cloneElement(v,{fontSize:null!=(l=v.props.fontSize)?l:x}),ownerState:E,ref:t},b,{classes:Z}))}))},54065:function(e,t,n){n.d(t,{Z:function(){return x}});var o=n(63366),r=n(87462),a=n(67294),i=n(86010),l=n(27192),c=n(89130),d=n(29602),s=n(28979);function u(e){return(0,s.Z)("MuiContainer",e)}(0,n(76087).Z)("MuiContainer",["root","disableGutters","fixed","maxWidthXs","maxWidthSm","maxWidthMd","maxWidthLg","maxWidthXl"]);var p=n(98216),m=n(85893);const f=["className","component","disableGutters","fixed","maxWidth"],h=(0,d.ZP)("div",{name:"MuiContainer",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[`maxWidth${(0,p.Z)(String(n.maxWidth))}`],n.fixed&&t.fixed,n.disableGutters&&t.disableGutters]}})((({theme:e,ownerState:t})=>(0,r.Z)({width:"100%",marginLeft:"auto",boxSizing:"border-box",marginRight:"auto",display:"block"},!t.disableGutters&&{paddingLeft:e.spacing(2),paddingRight:e.spacing(2),[e.breakpoints.up("sm")]:{paddingLeft:e.spacing(3),paddingRight:e.spacing(3)}})),(({theme:e,ownerState:t})=>t.fixed&&Object.keys(e.breakpoints.values).reduce(((t,n)=>{const o=e.breakpoints.values[n];return 0!==o&&(t[e.breakpoints.up(n)]={maxWidth:`${o}${e.breakpoints.unit}`}),t}),{})),(({theme:e,ownerState:t})=>(0,r.Z)({},"xs"===t.maxWidth&&{[e.breakpoints.up("xs")]:{maxWidth:Math.max(e.breakpoints.values.xs,444)}},t.maxWidth&&"xs"!==t.maxWidth&&{[e.breakpoints.up(t.maxWidth)]:{maxWidth:`${e.breakpoints.values[t.maxWidth]}${e.breakpoints.unit}`}})));var x=a.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiContainer"}),{className:a,component:d="div",disableGutters:s=!1,fixed:x=!1,maxWidth:g="lg"}=n,b=(0,o.Z)(n,f),y=(0,r.Z)({},n,{component:d,disableGutters:s,fixed:x,maxWidth:g}),v=(e=>{const{classes:t,fixed:n,disableGutters:o,maxWidth:r}=e,a={root:["root",r&&`maxWidth${(0,p.Z)(String(r))}`,n&&"fixed",o&&"disableGutters"]};return(0,l.Z)(a,u,t)})(y);return(0,m.jsx)(h,(0,r.Z)({as:d,ownerState:y,className:(0,i.Z)(v.root,a),ref:t},b))}))},64062:function(e,t,n){var o=n(67294),r=n(45697),a=n.n(r),i=n(99226),l=["children","title"];function c(){return c=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var o in n)Object.prototype.hasOwnProperty.call(n,o)&&(e[o]=n[o])}return e},c.apply(this,arguments)}var d=(0,o.forwardRef)((function(e,t){var n=e.children,r=e.title,a=void 0===r?"":r,d=function(e,t){if(null==e)return{};var n,o,r=function(e,t){if(null==e)return{};var n,o,r={},a=Object.keys(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(o=0;o<a.length;o++)n=a[o],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}(e,l);return o.createElement(i.Z,c({ref:t},d),o.createElement(i.Z,null,o.createElement("title",null,a)),n)}));d.propTypes={children:a().node.isRequired,title:a().string},t.Z=d},85393:function(e,t,n){n.r(t);var o=n(67294),r=n(2658),a=n(22887),i=n(22620),l=n(54065),c=n(26447),d=n(99226),s=n(65295),u=n(97896),p=n(44290),m=n(73327),f=n(9573),h=n(38732),x=n(46926),g=n(56011),b=n(3694),y=n(64062),v=n(75113),E=n(28006),Z=n.n(E),k=n(42694),S=n(9198),w=n.n(S),I=n(72132),C=n(98401);function A(e){return function(e){if(Array.isArray(e))return B(e)}(e)||function(e){if("undefined"!=typeof Symbol&&null!=e[Symbol.iterator]||null!=e["@@iterator"])return Array.from(e)}(e)||P(e)||function(){throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function R(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var o,r,a=[],i=!0,l=!1;try{for(n=n.call(e);!(i=(o=n.next()).done)&&(a.push(o.value),!t||a.length!==t);i=!0);}catch(e){l=!0,r=e}finally{try{i||null==n.return||n.return()}finally{if(l)throw r}}return a}}(e,t)||P(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function P(e,t){if(e){if("string"==typeof e)return B(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?B(e,t):void 0}}function B(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,o=new Array(t);n<t;n++)o[n]=e[n];return o}function j(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}var O=(0,p.Z)((function(e){var t;return(0,m.Z)({rightSection:{width:"96%"},inRightSection:{padding:"25px 70px 20px 32px"},adminButton:{display:"flex",justifyContent:"space-between",alignItems:"center",color:"#fff"},addNewGroup:{fontSize:"18px",color:"#fff",border:"1px solid #428BCA",borderRadius:"2px",backgroundColor:"#428BCA",height:"48px",marginLeft:"15px",width:"205px","&:hover":{backgroundColor:"#428BCA"}},inAdminButton:{display:"flex"},addNewGroupBulk:{width:"165px",marginLeft:"70px"},bottomButtonExpert:{textAlign:"right"},table:{width:"100%",borderSpacing:"0px 0px",border:"1px solid #7070704D","& tr":{"&:nth-child(1)":{"& th":{textAlign:"center",borderBottom:"1px solid #7070704D",fontSize:"15px",color:"#1e1e1f",fontFamily:"Gilroy-Semibold",padding:"5px 10px",backgroundColor:"#b0b0b1","& span":{padding:"0px",color:"#000","& svg":{verticalAlign:"top"}}}}},"& td":{textAlign:"center","&:nth-child(2)":{color:"#428BCA"},"&:last-child":{color:"#428BCA",cursor:"pointer"}}},selectBoxStyle:{"& span":{display:"none"}},selectBoxSectionTarget:{display:"flex",justifyContent:"space-between",alignItems:"flex-end"},selctAutTar:{width:"14%",marginRight:"7px",position:"relative","& div":{width:"100%",borderRadius:"2px"},"& input":{padding:"15px 14px",fontSize:"15px"}},actionList:{display:"flex",justifyContent:"flex-end",flexGrow:"1"},selctAutTarDate:(t={color:"#fff",marginRight:"7px",position:"relative"},j(t,e.breakpoints.between("1024","1400"),{width:"18%"}),j(t,"& input",{padding:"10px 0px 10px 10px",border:"1px solid #cccccc",borderRadius:"4px"}),t),calendarIcon:{position:"absolute",right:"40px",top:"14px",color:"#8F8FB3",fontSize:"14px",zIndex:"99"},datePicker:{height:"48px"},textField:{color:"#428BCA",border:"1px solid #428BCA",fontSize:"15px",fontFamily:"Gilroy-Semibold",padding:"5px 10px","& input":{color:"#428BCA"}},saleRate:{border:"none",textAlign:"center",width:"150px",height:"40px",display:"inline-block",lineHeight:"26px",borderRadius:"4px",backgroundColor:e.palette.grey[300]},saleRateActive:{border:"none",textAlign:"center",width:"150px",height:"40px",display:"inline-block",lineHeight:"26px",borderRadius:"4px",backgroundColor:e.palette.grey[200]}})}));t.default=function(){var e=O(),t=R((0,o.useState)(!1),2),p=t[0],m=t[1],E=R((0,o.useState)(!1),2),S=(E[0],E[1],R((0,o.useState)([]),2)),P=S[0],B=S[1],j=R((0,o.useState)(new Date),2),L=j[0],z=j[1];(0,o.useEffect)((function(){D()}),[L]);var D=function(){m(!0);var e={UserID:(0,C.n5)(),BusinessID:(0,C.vI)()};console.log("request",e),(0,v.SL)(Z().RETRIVE_EMPLOYEE,e,W)},W=function(e){m(!1),console.log("handleRetriveEmployeeEvent",e),e&&e.Payload&&e.Payload.length?(B(A(e.Payload)),n.g.employeeList=e.Payload,F()):console.log("Unable to find employee, please try after some time")},F=function(){if(m(!0),console.log("refreshEmployeeAttendance attendanceDate",L),n.g.employeeList&&n.g.employeeList.length>0){var e=[];n.g.employeeList.forEach((function(t){e.push(t._id)}));var t={UserID:(0,C.n5)(),BusinessID:(0,C.vI)(),EmployeeIds:e,AttendanceDate:L.toISOString().substring(0,10)};console.log("request refreshEmployeeAttendance",t),(0,v.SL)(Z().RETRIVE_EMPLOYEE_ATTENDANCE,t,N)}else console.log("refreshEmployeeAttendance Empty",P),m(!1)},N=function(e){if(m(!1),console.log("handleRetriveEmployee Attendance Event",e),e&&e.Payload&&e.Payload.length){var t=n.g.employeeList,o=0;n.g.employeeList.forEach((function(n){var r=n;e.Payload.forEach((function(e){r._id===e.EmployeeID&&(r.IsPresent=e.IsPresent,t[o]=r)})),o+=1})),t.length&&B(A(t))}else console.log("Unable to find employee, please try after some time")},M=function(e){m(!1),console.log("handleUpdateEmployeeAttendance",e),e&&e.Payload&&e.Payload.length?(0,I.Am)("Attendance Updated for ",e.Employee.Name):console.log("Unable to find employee, please try after some time")};return console.log("Render EmployeeList ",P),o.createElement(y.Z,{title:"Employee"},o.createElement(o.Fragment,null,o.createElement(l.Z,null,p?o.createElement(k.Z,null):null,o.createElement(I.Ix,null),o.createElement(c.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},o.createElement(r.Z,{variant:"h4",gutterBottom:!0},"Attendance"),o.createElement(d.Z,{className:e.actionList},o.createElement(d.Z,{component:"span",className:e.selctAutTarDate},o.createElement(w(),{placeholderText:"Select Date",selected:L,onChange:function(e){return z(e)}})))),o.createElement(s.Z,null,o.createElement(d.Z,null,o.createElement(u.Z,{sx:{minWidth:800}},o.createElement(f.Z,null,o.createElement(g.Z,null,o.createElement(b.Z,null,o.createElement(x.Z,null,"Employee Name"),o.createElement(x.Z,null,"Present"),o.createElement(x.Z,null,"Comment"))),o.createElement(h.Z,null,P.map((function(e){return o.createElement(b.Z,null,o.createElement(x.Z,null,o.createElement(r.Z,{component:"span"},e.Name)),o.createElement(x.Z,null,o.createElement(a.Z,{Name:"IsPresent",color:"primary",checked:e.IsPresent||!1,onChange:function(t){return function(e,t){var n={};P.some((function(o){o._id===t&&(o.IsPresent=e.target.checked,n=o)})),B(A(P)),function(e){m(!0);var t={Employee:e,UserID:(0,C.n5)(),BusinessID:(0,C.vI)(),EmployeeID:e._id,IsPresent:e.IsPresent,AttendanceDate:L.toISOString().substring(0,10)};console.log("request",t),(0,v.SL)(Z().ADD_EMPLOYEE_ATTENDACE,t,M)}(n)}(t,e._id)}})),o.createElement(x.Z,null,o.createElement(i.Z,{multiline:!0,minRows:1,onChange:function(t){return n=t,o=e._id,P.some((function(e){e._id===o&&(e.Comment=n.target.value)})),void B(A(P));var n,o},value:e.Comment,Name:"comment",placeholder:"Reason"})))}))))))))))}}}]);