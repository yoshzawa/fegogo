<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	Toi toi = (Toi) request.getAttribute("parent");
List<String[]> datas = (List<String[]>) request.getAttribute("datas");
%>

<html>
<head>
<meta charset="UTF-8">

<title><%=toi.getExam().getYYYYMM()%> 問<%=toi.getNo()%> <%=toi.getName()%></title>

<style>
html, body {
  height: 100%;
}
body {
  margin: 0;
  padding: 10px;
}
* {
  box-sizing: border-box;
}
.tab-wrap{
  width:500px;
}
.tab-wrap {
  height: 60%;
  min-height: 300px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
}
.tab-label {
  width: 50px;
  color: White;
  background: LightGray;
  font-weight: bold;
  white-space: nowrap;
  text-align: center;
  border:1px solid Gray;
  border-radius: 4px 0 0 4px;
  margin: 5px -1px 5px 1px;
  padding: 10px .5em;
  order: -1;
  position: relative;
  z-index: 1;
  cursor: pointer;
  float:left;
}
.tab-content {
  display:none;
}
/* アクティブなタブ */
.tab-switch:checked+.tab-label {
  color: Gray;
  background: White;
  border-right-color: White;
}
.tab-switch:checked+.tab-label+.tab-content {
  width: calc(500px - 50px); /* - ボタン幅 */
  height: 100%;
  padding: 15px;
  border-left: 1px solid Gray;
  display:block;
}
/* ラジオボタン非表示 */
.tab-switch {
  display: none;
}
</style>




</head>


<body translate="no" >





  <div class="tab-wrap">
    <input id="TAB-01" type="radio" name="TAB" class="tab-switch" checked="checked" />
    <label class="tab-label" for="TAB-01">1</label>
    <div class="tab-content">
        画面 1
    </div>
    <input id="TAB-02" type="radio" name="TAB" class="tab-switch" /><label class="tab-label" for="TAB-02">2</label>
    <div class="tab-content">
        画面 2
    </div>
    <input id="TAB-03" type="radio" name="TAB" class="tab-switch" /><label class="tab-label" for="TAB-03">3</label>
    <div class="tab-content">
        画面 3
    </div>
</div>

  
  
  
  

</body>

</html>
 

</body>
</html>