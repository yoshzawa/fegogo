<%@page import="jp.ac.jc21.t.yoshizawa.datastore.CommonEntity"%>
<%@page import="jp.ac.jc21.t.yoshizawa.datastore.Exam"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
List<Exam> exams= 	(List<Exam>)	request.getAttribute("exams");
%>
	<table border=1>
		<%
	for(Exam e:exams){
%>
		<tr>
			<th><%= e.getYYYYMM() %></th>
			<td><%= e.getName()%></td>
			<td><%= CommonEntity.getDateString(e.getCreated()) %></td>
		</tr>
		<%
	}
%>
	</table>
</body>
</html>