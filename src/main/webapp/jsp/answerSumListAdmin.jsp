<%@page import="java.text.SimpleDateFormat"%>
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
	%>
	<%
		if ((userService!=null) && (userService.isUserAdmin() == true) ) {
	%>
	<h4 align="right">login as <%= userService.getCurrentUser().getNickname() %>(Admin)
	(<a href="<%= userService.createLogoutURL("/")%>">logout</a>)</h4>
	<%
		} 
	%>
	<%@ include file="common/header.jsp"%><br>
	
<H1>登録されている解答の一覧</H1>
	<%
		if (answerSumList == null || answerSumList.size() == 0) {
	%>
	試験が解答されていません
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			for (AnswerSum as : answerSumList) {
				Toi toi = as.getRefToi().get();
				String tName="null";
				if(toi!=null){
					tName=toi.getName();
				}
				Exam e = toi.getExam();
				String eName="null";
				if(e!=null){
					eName=e.getName();
				}
				Member member = as.getRefMember().get();
				String mMail="null";
				if(member!=null){
					mMail=member.geteMail();
				}
				
				
		%>
		<tr>
			<td><%= as.getId() %><a href="/admin/answerSum/delete?memberId=<%= mMail %>&AnswerSumId=<%= as.getId()%>">削除</a></td>
			<td><%= as.getName() %></td>
			<td><%= as.getNoOfAnswer() %></td>
			<td><%= as.getNoOfSeikai() %></td>
			<td><%= sdf.format(as.getAnswered()) %></td>
			<td><%= as.getMapAnswer() %></td>
			<td><%= as.getRefMember() %><a href='/admin/answerSum/reChain?answerSumId=<%= as.getId() %>&memberId=<%= as.getName() %>'>reChain</a></td>
			<td><%= eName %></td>
			<td><%= tName %></td>
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