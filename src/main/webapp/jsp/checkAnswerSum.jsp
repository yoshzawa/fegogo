<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
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
	AnswerSum answerSum=
			(AnswerSum)request.getAttribute("answerSum");
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>解答に関するチェック</H1>
	<%
	if (list == null || list.size() == 0) {
	%>
	解答が登録されていません
	<%
		} else {
	%>
	<p>AnswerSum id=<%=answerSum.getId() %></p>
	<TABLE border=1>
		<TR>
			<TD>answer id</TD>
			<TD>answer 作成日付</TD>
			<TD>answer no</TD>
			<TD>answer name</TD>
			<TD>answer answers</TD>
			<TD>answer answerAumId</TD>
		</TR>
			
		<%
		for (String[]  s : list) {
			if(s[0] == null){
				%>
				<tr>
					<td><%=s[0]%></td>
				</tr>
				<%
				
			} else {
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