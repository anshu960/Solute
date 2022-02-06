"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[897],{54065:function(e,t,n){n.d(t,{Z:function(){return f}});var r=n(63366),o=n(87462),a=n(67294),i=n(86010),l=n(27192),s=n(89130),c=n(29602),d=n(28979);function p(e){return(0,d.Z)("MuiContainer",e)}(0,n(76087).Z)("MuiContainer",["root","disableGutters","fixed","maxWidthXs","maxWidthSm","maxWidthMd","maxWidthLg","maxWidthXl"]);var u=n(98216),m=n(85893);const v=["className","component","disableGutters","fixed","maxWidth"],b=(0,c.ZP)("div",{name:"MuiContainer",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[`maxWidth${(0,u.Z)(String(n.maxWidth))}`],n.fixed&&t.fixed,n.disableGutters&&t.disableGutters]}})((({theme:e,ownerState:t})=>(0,o.Z)({width:"100%",marginLeft:"auto",boxSizing:"border-box",marginRight:"auto",display:"block"},!t.disableGutters&&{paddingLeft:e.spacing(2),paddingRight:e.spacing(2),[e.breakpoints.up("sm")]:{paddingLeft:e.spacing(3),paddingRight:e.spacing(3)}})),(({theme:e,ownerState:t})=>t.fixed&&Object.keys(e.breakpoints.values).reduce(((t,n)=>{const r=e.breakpoints.values[n];return 0!==r&&(t[e.breakpoints.up(n)]={maxWidth:`${r}${e.breakpoints.unit}`}),t}),{})),(({theme:e,ownerState:t})=>(0,o.Z)({},"xs"===t.maxWidth&&{[e.breakpoints.up("xs")]:{maxWidth:Math.max(e.breakpoints.values.xs,444)}},t.maxWidth&&"xs"!==t.maxWidth&&{[e.breakpoints.up(t.maxWidth)]:{maxWidth:`${e.breakpoints.values[t.maxWidth]}${e.breakpoints.unit}`}})));var f=a.forwardRef((function(e,t){const n=(0,s.Z)({props:e,name:"MuiContainer"}),{className:a,component:c="div",disableGutters:d=!1,fixed:f=!1,maxWidth:h="lg"}=n,x=(0,r.Z)(n,v),Z=(0,o.Z)({},n,{component:c,disableGutters:d,fixed:f,maxWidth:h}),S=(e=>{const{classes:t,fixed:n,disableGutters:r,maxWidth:o}=e,a={root:["root",o&&`maxWidth${(0,u.Z)(String(o))}`,n&&"fixed",r&&"disableGutters"]};return(0,l.Z)(a,p,t)})(Z);return(0,m.jsx)(b,(0,o.Z)({as:c,ownerState:Z,className:(0,i.Z)(S.root,a),ref:t},x))}))},64062:function(e,t,n){var r=n(67294),o=n(45697),a=n.n(o),i=n(99226),l=["children","title"];function s(){return s=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},s.apply(this,arguments)}var c=(0,r.forwardRef)((function(e,t){var n=e.children,o=e.title,a=void 0===o?"":o,c=function(e,t){if(null==e)return{};var n,r,o=function(e,t){if(null==e)return{};var n,r,o={},a=Object.keys(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||(o[n]=e[n]);return o}(e,t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);for(r=0;r<a.length;r++)n=a[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(o[n]=e[n])}return o}(e,l);return r.createElement(i.Z,s({ref:t},c),r.createElement(i.Z,null,r.createElement("title",null,a)),n)}));c.propTypes={children:a().node.isRequired,title:a().string},t.Z=c},70897:function(e,t,n){n.r(t),n.d(t,{default:function(){return de}});var r=n(67294),o=n(99226),a=n(63366),i=n(87462),l=n(86010),s=n(27192),c=n(89130),d=n(29602),p=n(28979),u=n(76087);function m(e){return(0,p.Z)("MuiStepper",e)}(0,u.Z)("MuiStepper",["root","horizontal","vertical","alternativeLabel"]);var v=n(98216),b=r.createContext({}),f=r.createContext({});function h(e){return(0,p.Z)("MuiStepConnector",e)}(0,u.Z)("MuiStepConnector",["root","horizontal","vertical","alternativeLabel","active","completed","disabled","line","lineHorizontal","lineVertical"]);var x=n(85893);const Z=["className"],S=(0,d.ZP)("div",{name:"MuiStepConnector",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[n.orientation],n.alternativeLabel&&t.alternativeLabel,n.completed&&t.completed]}})((({ownerState:e})=>(0,i.Z)({flex:"1 1 auto"},"vertical"===e.orientation&&{marginLeft:12},e.alternativeLabel&&{position:"absolute",top:12,left:"calc(-50% + 20px)",right:"calc(50% + 20px)"}))),g=(0,d.ZP)("span",{name:"MuiStepConnector",slot:"Line",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.line,t[`line${(0,v.Z)(n.orientation)}`]]}})((({ownerState:e,theme:t})=>(0,i.Z)({display:"block",borderColor:"light"===t.palette.mode?t.palette.grey[400]:t.palette.grey[600]},"horizontal"===e.orientation&&{borderTopStyle:"solid",borderTopWidth:1},"vertical"===e.orientation&&{borderLeftStyle:"solid",borderLeftWidth:1,minHeight:24})));var L=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStepConnector"}),{className:o}=n,d=(0,a.Z)(n,Z),{alternativeLabel:p,orientation:u="horizontal"}=r.useContext(b),{active:m,disabled:L,completed:y}=r.useContext(f),w=(0,i.Z)({},n,{alternativeLabel:p,orientation:u,active:m,completed:y,disabled:L}),C=(e=>{const{classes:t,orientation:n,alternativeLabel:r,active:o,completed:a,disabled:i}=e,l={root:["root",n,r&&"alternativeLabel",o&&"active",a&&"completed",i&&"disabled"],line:["line",`line${(0,v.Z)(n)}`]};return(0,s.Z)(l,h,t)})(w);return(0,x.jsx)(S,(0,i.Z)({className:(0,l.Z)(C.root,o),ref:t,ownerState:w},d,{children:(0,x.jsx)(g,{className:C.line,ownerState:w})}))}));const y=["activeStep","alternativeLabel","children","className","connector","nonLinear","orientation"],w=(0,d.ZP)("div",{name:"MuiStepper",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[n.orientation],n.alternativeLabel&&t.alternativeLabel]}})((({ownerState:e})=>(0,i.Z)({display:"flex"},"horizontal"===e.orientation&&{flexDirection:"row",alignItems:"center"},"vertical"===e.orientation&&{flexDirection:"column"},e.alternativeLabel&&{alignItems:"flex-start"}))),C=(0,x.jsx)(L,{});var M=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStepper"}),{activeStep:o=0,alternativeLabel:d=!1,children:p,className:u,connector:v=C,nonLinear:f=!1,orientation:h="horizontal"}=n,Z=(0,a.Z)(n,y),S=(0,i.Z)({},n,{alternativeLabel:d,orientation:h}),g=(e=>{const{orientation:t,alternativeLabel:n,classes:r}=e,o={root:["root",t,n&&"alternativeLabel"]};return(0,s.Z)(o,m,r)})(S),L=r.Children.toArray(p).filter(Boolean),M=L.map(((e,t)=>r.cloneElement(e,(0,i.Z)({index:t,last:t+1===L.length},e.props)))),R=r.useMemo((()=>({activeStep:o,alternativeLabel:d,connector:v,nonLinear:f,orientation:h})),[o,d,v,f,h]);return(0,x.jsx)(b.Provider,{value:R,children:(0,x.jsx)(w,(0,i.Z)({ownerState:S,className:(0,l.Z)(g.root,u),ref:t},Z,{children:M}))})}));function R(e){return(0,p.Z)("MuiStep",e)}(0,u.Z)("MuiStep",["root","horizontal","vertical","alternativeLabel","completed"]);const j=["active","children","className","completed","disabled","expanded","index","last"],N=(0,d.ZP)("div",{name:"MuiStep",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[n.orientation],n.alternativeLabel&&t.alternativeLabel,n.completed&&t.completed]}})((({ownerState:e})=>(0,i.Z)({},"horizontal"===e.orientation&&{paddingLeft:8,paddingRight:8},e.alternativeLabel&&{flex:1,position:"relative"})));var W=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStep"}),{active:o,children:d,className:p,completed:u,disabled:m,expanded:v=!1,index:h,last:Z}=n,S=(0,a.Z)(n,j),{activeStep:g,connector:L,alternativeLabel:y,orientation:w,nonLinear:C}=r.useContext(b);let[M=!1,W=!1,k=!1]=[o,u,m];g===h?M=void 0===o||o:!C&&g>h?W=void 0===u||u:!C&&g<h&&(k=void 0===m||m);const P=r.useMemo((()=>({index:h,last:Z,expanded:v,icon:h+1,active:M,completed:W,disabled:k})),[h,Z,v,M,W,k]),E=(0,i.Z)({},n,{active:M,orientation:w,alternativeLabel:y,completed:W,disabled:k,expanded:v}),$=(e=>{const{classes:t,orientation:n,alternativeLabel:r,completed:o}=e,a={root:["root",n,r&&"alternativeLabel",o&&"completed"]};return(0,s.Z)(a,R,t)})(E),I=(0,x.jsxs)(N,(0,i.Z)({className:(0,l.Z)($.root,p),ref:t,ownerState:E},S,{children:[L&&y&&0!==h?L:null,d]}));return(0,x.jsx)(f.Provider,{value:P,children:L&&!y&&0!==h?(0,x.jsxs)(r.Fragment,{children:[L,I]}):I})})),k=n(82066),P=(0,k.Z)((0,x.jsx)("path",{d:"M12 0a12 12 0 1 0 0 24 12 12 0 0 0 0-24zm-2 17l-5-5 1.4-1.4 3.6 3.6 7.6-7.6L19 8l-9 9z"}),"CheckCircle"),E=(0,k.Z)((0,x.jsx)("path",{d:"M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z"}),"Warning"),$=n(33502);function I(e){return(0,p.Z)("MuiStepIcon",e)}var z,O=(0,u.Z)("MuiStepIcon",["root","active","completed","error","text"]);const T=["active","className","completed","error","icon"],A=(0,d.ZP)($.Z,{name:"MuiStepIcon",slot:"Root",overridesResolver:(e,t)=>t.root})((({theme:e})=>({display:"block",transition:e.transitions.create("color",{duration:e.transitions.duration.shortest}),color:e.palette.text.disabled,[`&.${O.completed}`]:{color:e.palette.primary.main},[`&.${O.active}`]:{color:e.palette.primary.main},[`&.${O.error}`]:{color:e.palette.error.main}}))),G=(0,d.ZP)("text",{name:"MuiStepIcon",slot:"Text",overridesResolver:(e,t)=>t.text})((({theme:e})=>({fill:e.palette.primary.contrastText,fontSize:e.typography.caption.fontSize,fontFamily:e.typography.fontFamily})));var D=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStepIcon"}),{active:r=!1,className:o,completed:d=!1,error:p=!1,icon:u}=n,m=(0,a.Z)(n,T),v=(0,i.Z)({},n,{active:r,completed:d,error:p}),b=(e=>{const{classes:t,active:n,completed:r,error:o}=e,a={root:["root",n&&"active",r&&"completed",o&&"error"],text:["text"]};return(0,s.Z)(a,I,t)})(v);if("number"==typeof u||"string"==typeof u){const e=(0,l.Z)(o,b.root);return p?(0,x.jsx)(A,(0,i.Z)({as:E,className:e,ref:t,ownerState:v},m)):d?(0,x.jsx)(A,(0,i.Z)({as:P,className:e,ref:t,ownerState:v},m)):(0,x.jsxs)(A,(0,i.Z)({className:e,ref:t,ownerState:v},m,{children:[z||(z=(0,x.jsx)("circle",{cx:"12",cy:"12",r:"12"})),(0,x.jsx)(G,{className:b.text,x:"12",y:"16",textAnchor:"middle",ownerState:v,children:u})]}))}return u}));function F(e){return(0,p.Z)("MuiStepLabel",e)}var B=(0,u.Z)("MuiStepLabel",["root","horizontal","vertical","label","active","completed","error","disabled","iconContainer","alternativeLabel","labelContainer"]);const H=["children","className","componentsProps","error","icon","optional","StepIconComponent","StepIconProps"],X=(0,d.ZP)("span",{name:"MuiStepLabel",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,t[n.orientation]]}})((({ownerState:e})=>(0,i.Z)({display:"flex",alignItems:"center",[`&.${B.alternativeLabel}`]:{flexDirection:"column"},[`&.${B.disabled}`]:{cursor:"default"}},"vertical"===e.orientation&&{textAlign:"left",padding:"8px 0"}))),q=(0,d.ZP)("span",{name:"MuiStepLabel",slot:"Label",overridesResolver:(e,t)=>t.label})((({theme:e})=>(0,i.Z)({},e.typography.body2,{display:"block",transition:e.transitions.create("color",{duration:e.transitions.duration.shortest}),[`&.${B.active}`]:{color:e.palette.text.primary,fontWeight:500},[`&.${B.completed}`]:{color:e.palette.text.primary,fontWeight:500},[`&.${B.alternativeLabel}`]:{textAlign:"center",marginTop:16},[`&.${B.error}`]:{color:e.palette.error.main}}))),U=(0,d.ZP)("span",{name:"MuiStepLabel",slot:"IconContainer",overridesResolver:(e,t)=>t.iconContainer})((()=>({flexShrink:0,display:"flex",paddingRight:8,[`&.${B.alternativeLabel}`]:{paddingRight:0}}))),V=(0,d.ZP)("span",{name:"MuiStepLabel",slot:"LabelContainer",overridesResolver:(e,t)=>t.labelContainer})((({theme:e})=>({width:"100%",color:e.palette.text.secondary}))),J=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStepLabel"}),{children:o,className:d,componentsProps:p={},error:u=!1,icon:m,optional:v,StepIconComponent:h,StepIconProps:Z}=n,S=(0,a.Z)(n,H),{alternativeLabel:g,orientation:L}=r.useContext(b),{active:y,disabled:w,completed:C,icon:M}=r.useContext(f),R=m||M;let j=h;R&&!j&&(j=D);const N=(0,i.Z)({},n,{active:y,alternativeLabel:g,completed:C,disabled:w,error:u,orientation:L}),W=(e=>{const{classes:t,orientation:n,active:r,completed:o,error:a,disabled:i,alternativeLabel:l}=e,c={root:["root",n,a&&"error",i&&"disabled",l&&"alternativeLabel"],label:["label",r&&"active",o&&"completed",a&&"error",i&&"disabled",l&&"alternativeLabel"],iconContainer:["iconContainer",l&&"alternativeLabel"],labelContainer:["labelContainer"]};return(0,s.Z)(c,F,t)})(N);return(0,x.jsxs)(X,(0,i.Z)({className:(0,l.Z)(W.root,d),ref:t,ownerState:N},S,{children:[R||j?(0,x.jsx)(U,{className:W.iconContainer,ownerState:N,children:(0,x.jsx)(j,(0,i.Z)({completed:C,active:y,error:u,icon:R},Z))}):null,(0,x.jsxs)(V,{className:W.labelContainer,ownerState:N,children:[o?(0,x.jsx)(q,(0,i.Z)({className:W.label,ownerState:N},p.label,{children:o})):null,v]})]}))}));J.muiName="StepLabel";var K=J,Q=n(12981);function Y(e){return(0,p.Z)("MuiStepContent",e)}(0,u.Z)("MuiStepContent",["root","last","transition"]);const _=["children","className","TransitionComponent","transitionDuration","TransitionProps"],ee=(0,d.ZP)("div",{name:"MuiStepContent",slot:"Root",overridesResolver:(e,t)=>{const{ownerState:n}=e;return[t.root,n.last&&t.last]}})((({ownerState:e,theme:t})=>(0,i.Z)({marginLeft:12,paddingLeft:20,paddingRight:8,borderLeft:`1px solid ${"light"===t.palette.mode?t.palette.grey[400]:t.palette.grey[600]}`},e.last&&{borderLeft:"none"}))),te=(0,d.ZP)(Q.Z,{name:"MuiStepContent",slot:"Transition",overridesResolver:(e,t)=>t.transition})({});var ne=r.forwardRef((function(e,t){const n=(0,c.Z)({props:e,name:"MuiStepContent"}),{children:o,className:d,TransitionComponent:p=Q.Z,transitionDuration:u="auto",TransitionProps:m}=n,v=(0,a.Z)(n,_),{orientation:h}=r.useContext(b),{active:Z,last:S,expanded:g}=r.useContext(f),L=(0,i.Z)({},n,{last:S}),y=(e=>{const{classes:t,last:n}=e,r={root:["root",n&&"last"],transition:["transition"]};return(0,s.Z)(r,Y,t)})(L);let w=u;return"auto"!==u||p.muiSupportAuto||(w=void 0),(0,x.jsx)(ee,(0,i.Z)({className:(0,l.Z)(y.root,d),ref:t,ownerState:L},v,{children:(0,x.jsx)(te,(0,i.Z)({as:p,in:Z||g,className:y.transition,ownerState:L,timeout:w,unmountOnExit:!0},m,{children:o}))}))})),re=n(2658),oe=n(64062),ae=n(54065),ie=n(26447),le=n(39704),se=n(83428);function ce(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function de(){var e,t,n=(0,le.I0)(),a=(0,le.v9)((function(e){return e.shipment.allShipmentStatus})),i=(e=r.useState(0),t=2,function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,o,a=[],i=!0,l=!1;try{for(n=n.call(e);!(i=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);i=!0);}catch(e){l=!0,o=e}finally{try{i||null==n.return||n.return()}finally{if(l)throw o}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return ce(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?ce(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()),l=i[0],s=i[1];return r.useEffect((function(){setTimeout((function(){n((0,se.m2)(1,(function(e){e&&e.length&&e.forEach((function(t){4===t.Status&&s(e.length)}))})))}),500)}),[]),r.createElement(oe.Z,{title:"Track"},r.createElement(r.Fragment,null,r.createElement(ae.Z,null,r.createElement(ie.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(re.Z,{variant:"h4",gutterBottom:!0},"Track")),r.createElement(o.Z,{sx:{maxWidth:400}},r.createElement(M,{activeStep:l,orientation:"vertical"},a.map((function(e,t){return r.createElement(W,{key:e.label},r.createElement(K,{optional:r.createElement(re.Z,{variant:"caption"},e.Address)},e.label),r.createElement(ne,null,r.createElement(re.Z,null,e.description)))})))))))}}}]);