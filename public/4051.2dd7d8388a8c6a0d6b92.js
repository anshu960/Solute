"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[4051],{94051:function(e,t,n){n.r(t);var r=n(67294),o=n(54065),l=n(26447),i=n(2658),a=n(99226),u=n(44290),c=n(73327),s=n(64062),p=n(75113),d=n(28006),f=n.n(d),y=n(42694),m=n(72132),x=n(98401),b=n(91999),g=n(5041);function h(e){return h="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},h(e)}function S(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,l=[],i=!0,a=!1;try{for(n=n.call(e);!(i=(r=n.next()).done)&&(l.push(r.value),!t||l.length!==t);i=!0);}catch(e){a=!0,o=e}finally{try{i||null==n.return||n.return()}finally{if(a)throw o}}return l}}(e,t)||function(e,t){if(e){if("string"==typeof e)return w(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?w(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function w(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}var v=(0,u.Z)((function(e){return(0,c.Z)({rightSection:{width:"96%"},inRightSection:{padding:"25px 70px 20px 32px"},adminButton:{display:"flex",justifyContent:"space-between",alignItems:"center",color:"#fff"},addNewGroup:{fontSize:"18px",color:"#fff",border:"1px solid #428BCA",borderRadius:"2px",backgroundColor:"#428BCA",height:"48px",marginLeft:"15px",width:"205px","&:hover":{backgroundColor:"#428BCA"}},inAdminButton:{display:"flex"},addNewGroupBulk:{width:"165px",marginLeft:"70px"},bottomButtonExpert:{textAlign:"right"},selectBoxStyle:{"& span":{display:"none"}},selectBoxSectionTarget:{display:"flex",justifyContent:"space-between",alignItems:"flex-end"},selctAutTar:{width:"14%",marginRight:"7px",position:"relative","& div":{width:"100%",borderRadius:"2px"},"& input":{padding:"15px 14px",fontSize:"15px"}},actionList:{display:"flex",justifyContent:"flex-end",flexGrow:"1"},calendarIcon:{position:"absolute",right:"40px",top:"14px",color:"#8F8FB3",fontSize:"14px",zIndex:"99"},datePicker:{height:"48px"},textField:{color:"#428BCA",border:"1px solid #428BCA",fontSize:"15px",fontFamily:"Gilroy-Semibold",padding:"5px 10px","& input":{color:"#428BCA"}},saleRate:{border:"none",textAlign:"center",width:"100px",height:"40px",display:"inline-block",lineHeight:"26px",borderRadius:"4px",backgroundColor:e.palette.grey[300]}})})),C={Name:"",Price:""};t.default=function(){var e=v(),t=S((0,r.useState)(C),2),n=(t[0],t[1]),u=S((0,r.useState)([]),2),c=u[0],d=u[1],w=S((0,r.useState)(0),2),E=(w[0],w[1],S((0,r.useState)(!1),2)),A=E[0],B=E[1],I=S((0,r.useState)(new Date),2),k=I[0],P=I[1];(0,r.useEffect)((function(){R()}),[k]);var R=function(){B(!0);var e={UserID:(0,x.n5)(),BusinessID:(0,x.vI)(),Date:k};console.log("request",e),(0,p.SL)(f().RETRIVE_PRODUCT,e,D)},D=(0,r.useCallback)((function(e){B(!1),console.log("handleRetriveProductsEvent",e,h(e.Payload)),e&&e.Payload&&e.Payload.length>0?(d(e.Payload),n(C)):console.log("Unable to find customer, please try after some time")}),[]);return r.createElement(s.Z,{title:"Product"},r.createElement(r.Fragment,null,r.createElement(o.Z,null,A?r.createElement(y.Z,null):null,r.createElement(m.Ix,null),r.createElement(l.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(i.Z,{variant:"h4",gutterBottom:!0},"Product"),r.createElement(a.Z,{className:e.actionList},r.createElement(g.L,{selectedDate:k,setDate:P}),r.createElement(b.wx,null))),r.createElement(b.cm,{productList:c,selectedDate:k}))))}}}]);