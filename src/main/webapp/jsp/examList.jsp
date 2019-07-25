<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<%
		List<Exam> examList = (List<Exam>) request.getAttribute("examList");
	%>
<%@ include file="common/header.jsp"%>
	<div class="card">
    <div class="card-header">登録されている試験の一覧</div>

	<%
		if (examList == null || examList.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<div class="container">
	  <div class="row">
	    <div class="col-sm"></div>
	
    <div class="col-sm">
	<TABLE border=1 class="table table-striped table-hover table-responsive">
		<thead class="thead-dark"><tr>
			<TH>試験名</TH>
			<TH>問題登録</TH>
			</TR>
		</thead>
		<%
			for (Exam e : examList) {
		%>
		<tr>
			<td><a href="/toi/list?parentId=<%=e.getId()%>"><%=e.getName()%></a></td>
			<td>
				<%= e.getToiRefListSize() %>
			</td>
		</tr>
		<%
			}
		%>
	</TABLE>	</div>
		    <div class="col-sm"></div>
	
	</div>
	<%
		}
	%>
	</div>


</body>
　<%@ include file="common/footer.jsp"%>

</html>