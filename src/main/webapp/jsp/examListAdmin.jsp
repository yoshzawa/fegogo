<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
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
	Map<Long, Exam> examMap = (Map<Long, Exam>) request.getAttribute("examMap");

	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>登録されている試験の一覧</H1>
	<%
	if (examMap == null || examMap.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>ID</TD>
			<TD>YYYYMM</TD>
			<TD>NAME</TD>
			<TD>問題登録</TD>
			<TD>内容確認</TD>
		</TR>
		<%
		for (Long k : examMap.keySet()) {
			Exam e = examMap.get(k);		
		%>
		<tr>
			<td><%=e.getId()%></td>
			<td><%=e.getYYYYMM()%></td>
			<td><a href="/admin/toi/list?parentId=<%=e.getId()%>"><%=e.getName()%></a></td>
			<td>
				<%= e.getToiListSize() %>
			</td>
			<td><a href="/admin/check/exam?examId=<%=e.getId()%>">チェック</a></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<hr />
	<form method='post' action='/admin/exam/add'>
		<label>YYYYMM</label> <input type="text" name="YYYYMM" /> <label>ExamName</label>
		<input type="text" name="ExamName" /> <input type="submit" name="追加" />
	</form>
</body>
　<%@ include file="common/footer.jsp"%>
</html>