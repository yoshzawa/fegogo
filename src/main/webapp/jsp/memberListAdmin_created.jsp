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
		List<Member> memberList = (List<Member>) request.getAttribute("memberList");
	UserService userService = (UserService) request.getAttribute("userService");
	Map<String, Integer> answerSumIdMap = (Map<String, Integer>) request.getAttribute("answerSumIdMap");
	%>
	<%
		if ((userService != null) && (userService.isUserAdmin() == true)) {
	%>
	<h4 align="right">
		login as
		<%=userService.getCurrentUser().getNickname()%>(Admin) (<a
			href='<%=userService.createLogoutURL("/")%>'>logout</a>)
	</h4>
	<%
		}
	%>
	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>登録されている学生の一覧</H1>
	<%
		if (memberList == null || memberList.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border="1">
		<TR>
			<TD><a href="./list?Order=id">geteMail</a></TD>
			<TD>getCreated</TD>
			<TD><a href="./list?Order=modified">getModified</a></TD>
			<TD>数</TD>
		</TR>
		<%
			for (Member m : memberList) {
		%>
		<tr>
			<td><%=m.geteMail()%></td>
			<td><%=CommonFunction.dateFormat(m.getCreated())%></td>
			<td><%=CommonFunction.dateFormat(m.getModified())%></td>
			<td><a href='/admin/answerSum/list?memberId=<%=m.geteMail()%>'><%=answerSumIdMap.get(m.geteMail())%></a></td>
			<td><a href='/admin/member2/list?email=<%=m.geteMail()%>'>解答リスト</a></td>
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