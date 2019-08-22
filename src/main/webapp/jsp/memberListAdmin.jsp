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
	<%
		if (memberList == null || memberList.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>geteMail</TD>
			<TD>getCreated</TD>
			<TD>getModified</TD>
			<TD>getRefAnswerSumList</TD>
			<TD>数</TD>
		</TR>
		<%
			for (Member m : memberList) {
		%>
		<tr>
			<td><%=m.geteMail()%></td>
			<td><%=m.getCreated()%></td>
			<td><%=m.getModified()%></td>
			<td>
				<%
					List<Ref<AnswerSum>> l = m.getRefAnswerSumList();
				%>
				<table>
					<%
						for (Ref<AnswerSum> ras : l) {
									AnswerSum as = ras.get();
									float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
					%>
					<tr>
						<td><%=as.getId()%> 
						[<%=as.getRefToi().get().getExam().getName()%>]
						[<%= as.getRefToi().get().getNo() %>]
						(<%= String.format("%1$.1f", point) %>)
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</td>
			<td><a href='/admin/answerSum/list?memberId=<%=m.geteMail()%>'><%=m.getRefAnswerSumListCount()%></a></td>
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