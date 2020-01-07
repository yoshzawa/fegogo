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
	ArrayList<String[]> list = 
			(ArrayList<String[]>)request.getAttribute("list");
	Toi t=
			(Toi)request.getAttribute("t");
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>試験に関するチェック</H1>
	<%
	if (list == null || list.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<p>Toi id=<%=t.getId() %></p>
	<p>Toi name=<%=t.getName() %></p>
	
	<p>Exam id=<%=t.getExamId() %></p>
	<p>Exam id=<%=t.getExam().getId() %></p>
	<p>Genre id=<%=t.getRefGenre().get().getId() %></p>
	<p>Genre name=<%=t.getRefGenre().get().getName() %></p>
	
	<TABLE border=1>
		<TR>
			<TD>answerSum.id</TD>
			<TD>answerSum.toi</TD>
			<TD>answerSum.toi.id</TD>
			<TD>answerSum.memberId</TD>
			<TD>answerSum.member</TD>
			<TD>answerSum.member.answerSumlist.contain</TD>
		</TR>
		<%
		for (String[]  s : list) {
			
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
			<td><%=s[4]%></td>
			<td><%=s[5]%></td>
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