<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.*"%>
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
		Exam parent = (Exam) request.getAttribute("parent");
		TreeMap<Long, Toi> toiMap = (TreeMap<Long, Toi>) request.getAttribute("toiMap");
		String parentId = (String) request.getAttribute("parentId");
		UserService userService = (UserService)
				request.getAttribute("userService");
	%>
		<%
		if ((userService!=null) && (userService.isUserAdmin() == true) ) {
	%>
	<h4 align="right">login as <%= userService.getCurrentUser().getNickname() %>(Admin)
	(<a href="<%= userService.createLogoutURL("/")%>">logout</a>)</h4>
	<%
		} 
	%>
	<H1>登録されている問の一覧</H1>
	
	<p>選択された試験：<%= parent.getName() %> <a href="/admin/exam/list">(選択解除する)</a>
	</p>

	<%
		if (toiMap == null || toiMap.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>ToiId</TD>
			<TD>No</TD>
			<TD>Name</TD>
		</TR>
		<%
			Set<Long> toiKeySet = toiMap.keySet();
				for (Long l : toiKeySet) {
					Toi t = toiMap.get(l);
		%>
		<tr>
			<td><%=t.getId()%></td>
			<td><%=t.getNo()%></td>
			<td><a href="/admin/question/list?parentId=<%=t.getId()%>"><%=t.getName()%></a></td>
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
		<label>No</label> <input type="text" name="No" /> 
		<label>ToiName</label>
		<input type="text" name="toiName" /> 
		<input type="hidden"
			name="parentId" value='<%=parentId%>' /> 
			<input type="submit"
			name="追加" />
	</form>
</body>
</html>