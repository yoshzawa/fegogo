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
<H1>登録されている試験の一覧</H1>
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
	<TD>試験名</TD>
	</TR>		<%
			for (Exam e : examList) {
		%>
		<tr>
			<td><%=e.getName()%></td>
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
</html>