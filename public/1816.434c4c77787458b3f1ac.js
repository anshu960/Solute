"use strict";(self.webpackChunkSolute=self.webpackChunkSolute||[]).push([[1816],{1816:function(e,t,n){n.r(t);var r=n(67294),l=n(54065),a=n(26447),o=n(2658),u=n(65295),i=n(44656),c=n(62640),m=n(99226),s=n(29602),f=n(5977),p=n(64062),b=n(42694),v=n(72132),y=n(19055);function d(e,t){return function(e){if(Array.isArray(e))return e}(e)||function(e,t){var n=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=n){var r,l,a=[],o=!0,u=!1;try{for(n=n.call(e);!(o=(r=n.next()).done)&&(a.push(r.value),!t||a.length!==t);o=!0);}catch(e){u=!0,l=e}finally{try{o||null==n.return||n.return()}finally{if(u)throw l}}return a}}(e,t)||function(e,t){if(e){if("string"==typeof e)return h(e,t);var n=Object.prototype.toString.call(e).slice(8,-1);return"Object"===n&&e.constructor&&(n=e.constructor.name),"Map"===n||"Set"===n?Array.from(e):"Arguments"===n||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)?h(e,t):void 0}}(e,t)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}()}function h(e,t){(null==t||t>e.length)&&(t=e.length);for(var n=0,r=new Array(t);n<t;n++)r[n]=e[n];return r}function E(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}n(39704);var g=(0,s.ZP)("div")((function(e){var t,n=e.theme;return E(t={zIndex:9,bottom:0,width:"100%",display:"flex",position:"absolute",backgroundColor:n.palette.background.paper},n.breakpoints.up("sm"),{justifyContent:"center"}),E(t,n.breakpoints.up("md"),{justifyContent:"flex-end",paddingRight:n.spacing(3)}),t}));t.default=function(){(0,f.k6)();var e=d((0,r.useState)(!1),2),t=e[0],n=(e[1],d((0,r.useState)("profile"),2)),s=n[0],h=n[1],E=[{value:"profile",component:r.createElement(y.N5,null)},{value:"stock",component:r.createElement(y.uE,null)},{value:"history",component:r.createElement(y.O,null)}];return r.createElement(p.Z,{title:"Profile"},r.createElement(r.Fragment,null,r.createElement(l.Z,null,t?r.createElement(b.Z,null):null,r.createElement(v.Ix,null),r.createElement(a.Z,{direction:"row",alignItems:"center",justifyContent:"space-between",mb:5},r.createElement(o.Z,{variant:"h4",gutterBottom:!0},"Product Profile")),r.createElement(u.Z,{sx:{mb:3,height:280,position:"relative"}},r.createElement(y.st,null),r.createElement(g,null,r.createElement(i.Z,{value:s,scrollButtons:"auto",variant:"scrollable",allowScrollButtonsMobile:!0,onChange:function(e,t){h(t)}},E.map((function(e){return r.createElement(c.Z,{disableRipple:!0,key:e.value,value:e.value,label:e.value})}))))),E.map((function(e){return e.value===s&&r.createElement(m.Z,{key:e.value},e.component)})))))}}}]);