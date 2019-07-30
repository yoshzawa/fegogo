<%@page import="com.googlecode.objectify.Ref"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
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
	List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");

	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>登録されている分野の一覧</H1>
	<%
	if (genreList == null || genreList.size() == 0) {
	%>
	分野が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>ID</TD>
			<TD>YYYYMM</TD>
			<TD>NAME</TD>
			<TD>問題登録</TD>
		</TR>
		<%
		for (Genre g : genreList) {
		%>
		<tr>
			<td><%=g.getId()%></td>
			<td><%=g.getName()%></td>
			<td>
			<% for(Ref<Toi> rt : g.getToiRefList()){
					Toi t = rt.get();%>
				<%= t.getParent().getName() %>
				<%= t.getName() %> <br />
			
			<% }%>
			
			</td>
			
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<hr />
	<form method='post' action='/admin/genre/add'>
		<label>GenreName</label>
		<input type="text" name="GenreName" /> <input type="submit" name="追加" />
	</form>
</body>
　<%@ include file="common/footer.jsp"%>
</html>