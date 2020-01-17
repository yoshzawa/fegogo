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
	
<H1>問に関するチェック</H1>
<a href="/admin/check/toi?toiId=<%=t.getId() %>">問とリンク</a>　問と学生のリンク

	<%
	if (list == null || list.size() == 0) {
	%>
	問が登録されていません
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
			<TD>answerSum id</TD>
			<TD>answerSum check</TD>
			<TD>answerSum created</TD>
			<TD>link Toi</TD>
			<TD>answerSum toiのid</TD>
			<TD>answerSum memberId</TD>
			<TD>link Member</TD>
			<TD>answerSum memberのanswerSumlistに含まれる</TD>
		</TR>
		<%
		for (String[]  s : list) {
			if(s[0].equals("null")){
				%>
				<tr>
				<td>deadlink</td>
		</tr>
		<%
			} else {
				String answerSumAndToi="<-->";
				if(s[1].equals("null")){
					 answerSumAndToi="->";
				}
				String answerSumAndMember="<-->";
				if(s[4].equals("null")){
					answerSumAndMember="-><a href='/admin/delete/toi?toiId="+t.getId()+"&answerSumId="+s[0]+"'>delete answerSum</a>";
				}
			
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><a href="/admin/check/answerSum?answerSumId=<%=s[0]%>">check</a></td>
			<td><%=s[6]%></td>
			<td><%=answerSumAndToi%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
			<td><%=answerSumAndMember%></td>
			<td><%=s[5]%></td>
		</tr>
		<%
			}
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