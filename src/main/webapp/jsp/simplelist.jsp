<%--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>

<% 
final boolean DEBUG = true;
final String TAG = "JSP";
%>

<% 
final java.util.Date begin1 = new java.util.Date();
if (true) {
    StackTraceElement t = (new Throwable()).getStackTrace()[0];
    String format = "(%s:%d) %s: doGet: begin1 is %s";
    com.adapterj.logging.Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), begin1.toString()));
}
%>

<% 
java.util.List<com.adapterj.example.pojo.Source> list = com.adapterj.example.db.SourceListQuery.getInstance().findAllItems();

java.util.Map<Integer, String> types    = com.adapterj.example.pojo.Source.getTypes();
java.util.Map<Integer, String> subtypes = com.adapterj.example.pojo.Source.getSubtypes();
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-CN"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>LIST</title>
<style type="text/css">
body { font-family:sans-serif; }
a:link { color:blue; }
a:visited { color:blue; }
/**
 * DIV table
 */
.div-table {
    display: table;
    width: auto;
}
.div-table-body {
    display: table-row-group;
}
.div-table-heading {
    display: table-header-group;
    background-color: #F4F4F4;
    font-weight: bold;
}
.div-table-head {
    display: table-cell;
    padding: 0px 5px;
    border: 1px solid #F4F4F4;
    border-bottom: 1px solid #F4F4F4;
    vertical-align: top;
    font-size:13px;
}
.div-table-row {
    display: table-row; 
}
.div-table-cell {
    display: table-cell;
    padding: 0px 5px;
    border: 1px solid #FFFFFF;
    border-bottom: 1px solid #F4F4F4;
    vertical-align: top;
    font-family: monospace;
    font-size:13px;
}
.div-table-foot {
    display: table-footer-group;
    background-color: #FFF;
    font-weight: bold;
}
.div-table-cell, .div-table-head p {
    word-break: keep-all;
    white-space: nowrap;
}
</style>
</head>
<body>
<div><h1>SIMPLE LIST:</h1></div>

<!-- table begin -->
<div class="div-table">

<!-- table head begin -->
<div class="div-table-heading">
<div class="div-table-head"><p></p></div>
<div class="div-table-head"><p></p></div>
<div class="div-table-head"><p></p></div>
<div class="div-table-head"><p>ID</p></div>
<div class="div-table-head"><p>Name</p></div>
<div class="div-table-head"><p>Abbr</p></div>
<div class="div-table-head"><p>Logo</p></div>
<div class="div-table-head"><p>Domain</p></div>
<div class="div-table-head"><p>WWW</p></div>
<div class="div-table-head"><p>Description</p></div>
<div class="div-table-head"><p>Type</p></div>
<div class="div-table-head"><p>Encoding</p></div>
<div class="div-table-head"><p>Country</p></div>
<div class="div-table-head"><p>Specific</p></div>
<div class="div-table-head"><p>Priority</p></div>
<div class="div-table-head"><p>Latency</p></div>
<div class="div-table-head"><p>Label1</p></div>
<div class="div-table-head"><p>URL1</p></div>
<div class="div-table-head"><p>Type1</p></div>
<div class="div-table-head"><p>Language1</p></div>
<div class="div-table-head"><p>Label2</p></div>
<div class="div-table-head"><p>URL2</p></div>
<div class="div-table-head"><p>Type2</p></div>
<div class="div-table-head"><p>Encoding2</p></div>
<div class="div-table-head"><p>Label3</p></div>
<div class="div-table-head"><p>URL3</p></div>
<div class="div-table-head"><p>Type3</p></div>
<div class="div-table-head"><p>Encoding3</p></div>
<div class="div-table-head"><p>Label4</p></div>
<div class="div-table-head"><p>URL4</p></div>
<div class="div-table-head"><p>Type4</p></div>
<div class="div-table-head"><p>Encoding4</p></div>
<div class="div-table-head"><p>Label5</p></div>
<div class="div-table-head"><p>URL5</p></div>
<div class="div-table-head"><p>Type5</p></div>
<div class="div-table-head"><p>Encoding5</p></div>
<div class="div-table-head"><p>Label6</p></div>
<div class="div-table-head"><p>URL6</p></div>
<div class="div-table-head"><p>Type6</p></div>
<div class="div-table-head"><p>Encoding6</p></div>
<div class="div-table-head"><p>Label7</p></div>
<div class="div-table-head"><p>URL7</p></div>
<div class="div-table-head"><p>Type7</p></div>
<div class="div-table-head"><p>Encoding7</p></div>
<div class="div-table-head"><p>Label8</p></div>
<div class="div-table-head"><p>URL8</p></div>
<div class="div-table-head"><p>Type8</p></div>
<div class="div-table-head"><p>Encoding8</p></div>
<div class="div-table-head"><p>Label9</p></div>
<div class="div-table-head"><p>URL9</p></div>
<div class="div-table-head"><p>Type9</p></div>
<div class="div-table-head"><p>Encoding9</p></div>
<div class="div-table-head"><p>Version</p></div>
<div class="div-table-head"><p>Insert Time</p></div>
<div class="div-table-head"><p>Update Time</p></div>
<div class="div-table-head"><p>State</p></div>
</div>
<!-- table head begin -->

<!-- table body begin -->
<div class="div-table-body">

<% for (int i = 0; i < list.size(); i ++) { %>
<!-- list begin  -->
<div class="div-table-row" id="_list.item">
<div class="div-table-cell"><p style="font-family:sans-serif;"><a id="link[j][0].url" href="../source/disable?source.id=<%= list.get(i).getId() %>>" target="_blank">Disable</a></p></div>
<div class="div-table-cell"><p style="font-family:sans-serif;"><a id="link[j][1].url" href="../source/form?source.id=<%= list.get(i).getId() %>>"    target="_blank">Edit</a></p></div>
<div class="div-table-cell"><p style="font-family:sans-serif;"></div>
<div class="div-table-cell"><p id="source[j].id"><%= list.get(i).getId() %></p></div>
<div class="div-table-cell"><p id="source[j].name"><%= list.get(i).getName() %></p></div>
<div class="div-table-cell"><p id="source[j].abbr"><%= list.get(i).getAbbr() %></p></div>
<div class="div-table-cell"><p id="source[j].logo" style="max-width:20480px;overflow:hidden; text-overflow:ellipsis;"><%= list.get(i).getLogo() %></p></div>
<div class="div-table-cell"><p id="source[j].domain"><%= list.get(i).getDomain() %></p></div>
<div class="div-table-cell"><p id="source[j].www"><%= list.get(i).getWWW() %></p></div>
<div class="div-table-cell"><p id="source[j].description" style="max-width:20480px;overflow:hidden; text-overflow:ellipsis;"><%= list.get(i).getDescription() %></p></div>
<div class="div-table-cell"><p id="source[j].type"><%= list.get(i).getType() == null ? "N/A" : types.get(list.get(i).getType()) %></p></div>
<div class="div-table-cell"><p id="source[j].country"><%= list.get(i).getCountry() %></p></div>
<div class="div-table-cell"><p id="source[j].language"><%= list.get(i).getLanguage() %></p></div>
<div class="div-table-cell"><p id="source[j].specific"><%= list.get(i).getSpecific() %></p></div>
<div class="div-table-cell"><p id="source[j].priority"><%= list.get(i).getPriority() %></p></div>
<div class="div-table-cell"><p id="source[j].latency"><%= list.get(i).getLatency() %></p></div>
<div class="div-table-cell"><p id="source[j].label1"></p><%= list.get(i).getLabel1() %></div>
<div class="div-table-cell"><p id="source[j].url1"><%= list.get(i).getURL1() %></p></div>
<div class="div-table-cell"><p id="source[j].type1"><%= list.get(i).getType1() == null ? "N/A" : subtypes.get(list.get(i).getType1()) %></p></div>
<div class="div-table-cell"><p id="source[j].encoding1"><%= list.get(i).getEncoding1() %></p></div>
<div class="div-table-cell"><p id="source[j].label2"><%= list.get(i).getLabel2() %></p></div>
<div class="div-table-cell"><p id="source[j].url2"><%= list.get(i).getURL2() %></p></div>
<div class="div-table-cell"><p id="source[j].type2"><%= list.get(i).getType2() == null ? "N/A" : subtypes.get(list.get(i).getType2()) %></p></div>
<div class="div-table-cell"><p id="source[j].encoding2"><%= list.get(i).getEncoding2() %></p></div>
<div class="div-table-cell"><p id="source[j].label3"></p></div>
<div class="div-table-cell"><p id="source[j].url3"></p></div>
<div class="div-table-cell"><p id="source[j].type3"></p></div>
<div class="div-table-cell"><p id="source[j].encoding3"></p></div>
<div class="div-table-cell"><p id="source[j].label4"></p></div>
<div class="div-table-cell"><p id="source[j].url4"></p></div>
<div class="div-table-cell"><p id="source[j].type4"></p></div>
<div class="div-table-cell"><p id="source[j].encoding4"></p></div>
<div class="div-table-cell"><p id="source[j].label5"></p></div>
<div class="div-table-cell"><p id="source[j].url5"></p></div>
<div class="div-table-cell"><p id="source[j].type5"></p></div>
<div class="div-table-cell"><p id="source[j].encoding5"></p></div>
<div class="div-table-cell"><p id="source[j].label6"></p></div>
<div class="div-table-cell"><p id="source[j].url6"></p></div>
<div class="div-table-cell"><p id="source[j].type6"></p></div>
<div class="div-table-cell"><p id="source[j].encoding6"></p></div>
<div class="div-table-cell"><p id="source[j].label7"></p></div>
<div class="div-table-cell"><p id="source[j].url7"></p></div>
<div class="div-table-cell"><p id="source[j].type7"></p></div>
<div class="div-table-cell"><p id="source[j].encoding7"></p></div>
<div class="div-table-cell"><p id="source[j].label8"></p></div>
<div class="div-table-cell"><p id="source[j].url8"></p></div>
<div class="div-table-cell"><p id="source[j].type8"></p></div>
<div class="div-table-cell"><p id="source[j].encoding8"></p></div>
<div class="div-table-cell"><p id="source[j].label9"></p></div>
<div class="div-table-cell"><p id="source[j].url9"></p></div>
<div class="div-table-cell"><p id="source[j].type9"></p></div>
<div class="div-table-cell"><p id="source[j].encoding9"></p></div>
<div class="div-table-cell"><p id="source[j].version">v2019.02.19</p></div>
<div class="div-table-cell"><p id="source[j].insertTime">2019-02-19 18:00</p></div>
<div class="div-table-cell"><p id="source[j].updateTime">2019-02-19 18:00</p></div>
<div class="div-table-cell"><p id="source[j].state">1</p></div>
</div>
<!-- list end  -->
<% }; %>

</div>
<!-- table body begin -->

</div>
<!-- table begin -->

<br />

<div style="padding:3px 5px;"><p><a href="form" target="_blank">Create a SOURCE record</a></p></div>

<br /><br />

</body>
</html>

<%
final java.util.Date end1 = new java.util.Date();
final long cost1 = end1.getTime() - begin1.getTime();
if (DEBUG) {
    StackTraceElement t = (new Throwable()).getStackTrace()[0];
    String format = "(%s:%d) %s: doGet: end1 is %s, cost1 is %d";
    com.adapterj.logging.Log.i(TAG, String.format(format, t.getFileName(), t.getLineNumber(), t.getMethodName(), end1.toString(), cost1));
}
%>
