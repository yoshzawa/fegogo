<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
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
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
		UserService userService = (UserService) request.getAttribute("userService");
		String redirectTo = (String) request.getAttribute("redirectTo");
	%>
	<%
		if ((userService!=null) && (userService.isUserAdmin() == true) ) {
	%>
	<h4 align="right">login as <%= userService.getCurrentUser().getNickname() %>(Admin)
	(<a href="<%= userService.createLogoutURL("/")%>">logout</a>)</h4>
	<%
		} 
	%>
<H1>登録されている解答の一覧</H1>
	<%
		if (answerSumList == null || answerSumList.size() == 0) {
	%>
	試験が解答されていませんされていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>getId</TD>
			<TD>getName</TD>
			<TD>getNoOfAnswer</TD>
			<TD>getNoOfSeikai</TD>
			<TD>getAnswered</TD>
			<TD>getMapAnswer</TD>
			<TD>getRefMember</TD>
			<TD>EXAM</TD>
			<TD>TOI</TD>
		</TR>
		<%
			for (AnswerSum as : answerSumList) {
				Toi toi = as.getRefToi().get();
		%>
		<tr>
			<td><%= as.getId() %></td>
			<td><%= as.getName() %></td>
			<td><%= as.getNoOfAnswer() %></td>
			<td><%= as.getNoOfSeikai() %></td>
			<td><%= as.getAnswered() %></td>
			<td><%= as.getMapRefAnswer() %></td>
			<td><%= as.getRefMember() %>
			<%  if ((as.getRefMember() == null )||(as.getRefMember().get().containsRef(as) == false)) {%>
			<a href='/admin/answerSum/reChain?answerSumId=<%= as.getId() %>&memberId=<%= as.getName() %>&redirectTo=<%= redirectTo %>'>
			reChain　</a></td>
			<% } %>
			<td><%= toi.getParent().getName() %></td>
			<td><%= toi.getName() %></td>
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