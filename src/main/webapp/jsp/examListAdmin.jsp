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
	<%
		if (examList == null || examList.size() == 0) {
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
	</TR>
		<%
			for (Exam e : examList) {
		%>
		<tr>
			<td><%=e.getId()%></td>
			<td><%=e.getYYYYMM()%></td>
			<td><a href="/admin/toi/list?parentId=<%= e.getId()%>"><%=e.getName()%></a></td>
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
</html>