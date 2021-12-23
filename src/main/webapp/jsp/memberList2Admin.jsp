<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.googlecode.objectify.Ref"%>
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
		Map<Long, List<AnswerSum>> map = (Map<Long, List<AnswerSum>>) request.getAttribute("map");
	UserService userService = (UserService) request.getAttribute("userService");

	%>
	
	<%
		if ((userService != null) && (userService.isUserAdmin() == true)) {
	%>
	<h4 align="right">
		login as
		<%=userService.getCurrentUser().getNickname()%>(Admin) (<a
			href="<%=userService.createLogoutURL("/")%>">logout</a>)
	</h4>
	<%
		}
	%>
	<%@ include file="common/headerAdmin.jsp"%><br>
	
	<H1>登録されている学生の一覧</H1>
<table border=1>
<tr><th>toiId</th><th>answered(seikai/answer)</th></tr>
	<%
		for(Long toiId : map.keySet()){
			List<AnswerSum> aSumList = map.get(toiId);
	%>
		<tr><th><%= toiId %></th><td>
	<% 
			for(AnswerSum aSum : aSumList){
		
	%>
	<p>
	(<a href="/admin/check/answerSum?answerSumId=<%= aSum.getId() %>"><%= aSum.getId() %></a> )
	<%= CommonFunction.dateFormat(aSum.getAnswered()) %>
	(<%= aSum.getNoOfSeikai() %>/<%= aSum.getNoOfAnswer() %>)</p>
	<%
		}
	%>
	</td></tr>
	<%
		}
	%>
</table>	

</body>
<%@ include file="common/footer.jsp"%>
</html>