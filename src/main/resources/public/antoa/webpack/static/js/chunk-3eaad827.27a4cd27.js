(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3eaad827"],{"33d5":function(o,e,t){"use strict";t.r(e);var n=function(){var o=this,e=o.$createElement,t=o._self._c||e;return t("a-card",[o.isLoadOk?t("a-form",[o._l(o.gridCreateObject.createColumnCollection,(function(e,n){return["CreateColumnCascader"==e.type?t("CreateColumnCascader",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnText"==e.type?t("CreateColumnText",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnChildrenChoose"==e.type?t("CreateColumnChildrenChoose",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnDisplay"==e.type?t("CreateColumnDisplay",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnDivideNumber"==e.type?t("CreateColumnDivideNumber",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnEnum"==e.type?t("CreateColumnEnum",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnEnumCheckBox"==e.type?t("CreateColumnEnumCheckBox",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnEnumRadio"==e.type?t("CreateColumnEnumRadio",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnEnumTreeCheckBox"==e.type?t("CreateColumnEnumTreeCheckBox",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnFile"==e.type?t("CreateColumnFile",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnFiles"==e.type?t("CreateColumnFiles",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnFileLocal"==e.type?t("CreateColumnFileLocal",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnFilesLocal"==e.type?t("CreateColumnFilesLocal",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnPassword"==e.type?t("CreateColumnPassword",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnPicture"==e.type?t("CreateColumnPicture",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnPictures"==e.type?t("CreateColumnPictures",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnPictureLocal"==e.type?t("CreateColumnPictureLocal",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnPicturesLocal"==e.type?t("CreateColumnPicturesLocal",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnRichText"==e.type?t("CreateColumnRichText",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnTextarea"==e.type?t("CreateColumnTextarea",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),"CreateColumnTimestamp"==e.type?t("CreateColumnTimestamp",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e(),e.type.startsWith("PluginCreateColumn")?t("CreateColumnPlugin",{directives:[{name:"show",rawName:"v-show",value:o.displayColumns.includes(e.col),expression:"displayColumns.includes(column.col)"}],attrs:{column:e,"grid-api-object":o.gridApiObject},on:{input:function(t){o.onFormItemChange(e.col),o.$forceUpdate()}},model:{value:o.form[e.col],callback:function(t){o.$set(o.form,e.col,t)},expression:"form[column.col]"}},[o.getApiButtonByColumn(e.col)?t("RowButton",{attrs:{form:o.form,button:o.getApiButtonByColumn(e.col)},on:{formchange:o.onFormChange}}):o._e()],1):o._e()]})),t("a-form-item",{staticStyle:{display:"flex","justify-content":"center"}},[t("a-button",{attrs:{type:"primary"},on:{click:o.submit}},[o._v("创建")]),t("a-button",{staticStyle:{"margin-left":"8px"},attrs:{type:"primary"},on:{click:o.reset}},[o._v("重置")])],1)],2):o._e(),t("confirm-dialog",{ref:"confirmDialog"})],1)},l=[],r=t("1da1"),a=(t("96cf"),t("d81d"),t("4de4"),t("d3b7"),t("c1df"),t("e10a")),c=t("fc12"),u=t("1cd0"),i=t("5cf6"),m=t("ca0d"),s=t("6994"),C=t("cc81"),p=t("8b01"),d=t("821e"),f=t("9547"),g=t("2445"),h=t("9489"),b=t("5548"),y=t("b3a2"),B=t("d9ac"),v=t("0df2"),w=t("51e7"),x=t("72cb"),A=t("3142"),j=t("d2a1"),$=t("a6a8"),k=t("f367"),_=t("a5e1"),F=t("129a"),O={data:function(){return{isLoadOk:!1,gridPath:"",gridConfigUrl:"",gridApiObject:{api_column_change:"",create:"",create_page:"",delete:"",detail:"",detail_column_list:"",edit_page:"",list:"",list_page:"",path:"",save:""},gridCreateObject:{primaryKey:"id",createColumnCollection:[],createRowButtonBaseCollection:[],createOrEditColumnChangeHookCollection:null},displayColumns:[],form:{}}},components:{CreateColumnCascader:c["a"],CreateColumnChildrenChoose:u["a"],CreateColumnDisplay:m["a"],CreateColumnDivideNumber:s["a"],CreateColumnText:i["a"],CreateColumnEnum:C["a"],CreateColumnEnumCheckBox:p["a"],CreateColumnEnumRadio:d["a"],CreateColumnEnumTreeCheckBox:f["a"],CreateColumnFile:g["a"],CreateColumnFiles:h["a"],CreateColumnFileLocal:b["a"],CreateColumnFilesLocal:y["a"],CreateColumnPassword:B["a"],CreateColumnPicture:v["a"],CreateColumnPictures:w["a"],CreateColumnPictureLocal:x["a"],CreateColumnPicturesLocal:A["a"],CreateColumnRichText:j["a"],CreateColumnTextarea:$["a"],CreateColumnTimestamp:k["a"],CreateColumnPlugin:_["a"],ConfirmDialog:F["a"],RowButton:a["a"]},mounted:function(){var o=this;return Object(r["a"])(regeneratorRuntime.mark((function e(){var t,n;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,o.gridPath=o.$route.path.substring(0,o.$route.path.length-"/create".length),o.gridConfigUrl="/api"+o.gridPath+"/grid_config",e.next=5,o.$api(o.gridConfigUrl).param(o.$route.query).method("POST").call();case 5:if(t=e.sent,t.status){e.next=11;break}if("登录失效"!=t.msg){e.next=10;break}return o.$message.error("登录失效，请重新登录",5),e.abrupt("return",o.$router.push("/login"));case 10:throw t.msg;case 11:return Object.assign(o.gridApiObject,t.api),Object.assign(o.gridCreateObject,t.grid.create),o.gridCreateObject.createColumnCollection.map((function(e){o.form[e.col]="",o.displayColumns.push(e.col)})),e.next=16,o.reset();case 16:n=0;case 17:if(!(n<o.gridCreateObject.createOrEditColumnChangeHookCollection.length)){e.next=23;break}return e.next=20,o.onHookCall(o.gridCreateObject.createOrEditColumnChangeHookCollection[n].col);case 20:n++,e.next=17;break;case 23:o.isLoadOk=!0,e.next=29;break;case 26:e.prev=26,e.t0=e["catch"](0),o.$message.error("配置加载错误："+e.t0,5);case 29:case"end":return e.stop()}}),e,null,[[0,26]])})))()},methods:{getApiButtonByColumn:function(o){var e=this.gridCreateObject.createRowButtonBaseCollection.filter((function(e){return e.bindCol==o.col}));return 0==e.length?null:e[0]},onFormItemChange:function(o){var e=this;this.gridCreateObject.createOrEditColumnChangeHookCollection.filter((function(e){return o===e.col})).map((function(o){e.onHookCall(o.col)}))},onHookCall:function(o){var e=this;return Object(r["a"])(regeneratorRuntime.mark((function t(){var n;return regeneratorRuntime.wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,e.$api(e.gridApiObject.api_column_change).method("POST").param({type:"create",form:e.form,page:e.$route.query,col:o}).call();case 3:if(n=t.sent,!n.status){t.next=8;break}e.onFormChange(n),t.next=9;break;case 8:throw n.msg;case 9:t.next=14;break;case 11:t.prev=11,t.t0=t["catch"](0),e.$message.error(t.t0+"",5);case 14:case"end":return t.stop()}}),t,null,[[0,11]])})))()},onFormChange:function(o){Object.assign(this.form,o.data),o.displayColumns&&o.displayColumns.length>0&&(this.displayColumns=o.displayColumns)},reset:function(){var o=this;return Object(r["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:try{for(t in o.gridCreateObject.createColumnCollection)o.form[o.gridCreateObject.createColumnCollection[t].col]=o.gridCreateObject.createColumnCollection[t].default?o.gridCreateObject.createColumnCollection[t].default:""}catch(n){o.$message.error(n+"",5)}case 1:case"end":return e.stop()}}),e)})))()},submit:function(){var o=this;return Object(r["a"])(regeneratorRuntime.mark((function e(){var t,n,l,r;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return t={},o.gridCreateObject.createColumnCollection.map((function(e){"CreateColumnDisplay"!==e.type&&(t[e.col]=o.form[e.col])})),e.prev=2,e.next=5,o.$api(o.gridApiObject.create).method("POST").param(t).call();case 5:if(n=e.sent,!n.status){e.next=29;break}if(o.$message.success(n.data,5),o.reset(),e.prev=9,l=localStorage.beforePage,l){e.next=15;break}throw"";case 15:if(l=JSON.parse(l),console.log(l,o.$route.fullPath),r=l.filter((function(e){return e.after==o.$route.fullPath||e.after==o.$route.fullPath+"?"||e.after+"?"==o.$route.fullPath})),0!=r.length){e.next=20;break}throw"";case 20:o.$closePage(o.$route.path,r[0].before);case 21:e.next=27;break;case 23:e.prev=23,e.t0=e["catch"](9),o.$closePage(o.$route.path),o.$router.go(-1);case 27:e.next=30;break;case 29:throw n.msg;case 30:e.next=35;break;case 32:e.prev=32,e.t1=e["catch"](2),o.$message.error(e.t1+"",5);case 35:case"end":return e.stop()}}),e,null,[[2,32],[9,23]])})))()}}},R=O,P=(t("d7e4"),t("2877")),N=Object(P["a"])(R,n,l,!1,null,"7421d9b5",null);e["default"]=N.exports},c620:function(o,e,t){},d7e4:function(o,e,t){"use strict";t("c620")}}]);