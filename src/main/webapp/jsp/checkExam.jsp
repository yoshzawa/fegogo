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
	ArrayList<String[]> list = 
			(ArrayList<String[]>)request.getAttribute("list");
	Exam e=
			(Exam)request.getAttribute("e");
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>試験に関するチェック</H1>
	<%
	if (list == null || list.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<p>Exam id=<%=e.getId() %></p>
	<p>Exam name=<%=e.getName() %></p>
	<TABLE border=1>
		<TR>
			<TD>toi.key</TD>
			<TD>toi.id</TD>
			<TD>toi 内容確認</TD>
			<TD>toi.name</TD>
			<TD>toi.examId</TD>
		</TR>
		<%
		for (String[]  s : list) {
			
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td> <a href='http://localhost:8080/admin/check/toi?toiId=<%=s[1]%>'>チェック</a></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<hr />
</body>
　<%@ include file="common/footer.jsp"%>
</html>