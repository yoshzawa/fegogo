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
<H1>登録されている試験の一覧</H1>
	<%
		List<Toi> examList = (List<Toi>) request.getAttribute("toiList");
		String parentId = (String) request.getAttribute("parentId");
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
	<TD>ExamId</TD>
	<TD>No</TD>
	<TD>Name</TD>
	</TR>		<%
			for (Toi t : examList) {
		%>
		<tr>
			<td><%=t.getId()%></td>
			<td><%=t.getNo()%></td>
			<td><%=t.getName()%></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>
	
		<hr />
	<form method='post' action='/admin/toi/add'>
		<label>No</label> 
		<input type="text" name="No" /> 
		<label>ToiName</label>
		<input type="text" name="toiName" /> 
		<input type="hidden" name="parentId" value='<%= parentId %>'/> 
		<input type="submit" name="追加" />
	</form>
</body>
</html>