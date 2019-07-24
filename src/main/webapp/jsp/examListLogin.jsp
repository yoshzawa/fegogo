<%@page import="java.text.SimpleDateFormat"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
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
		List<Exam> examList = (List<Exam>) request.getAttribute("examList");
		String email = (String) request.getAttribute("email");
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
		
	%>
<h4 align="right">
<%= email %>としてサインイン（<a href="/openidSignOut">Sign out</a>）
</h4>
<H1>登録されている試験の一覧</H1>

	<%
		if (examList == null || examList.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TH>試験名</TH>
			<TH>問題登録</TH>
		</TR>
		<%
			for (Exam e : examList) {
		%>
		<tr>
			<td><a href="/toi/list?parentId=<%=e.getId()%>"><%=e.getName()%></a></td>
			<td>
				<%= e.getToiRefListSize() %>
			</td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

<H1>解答済み試験の一覧</H1>

<% if ((answerSumList == null) || (answerSumList == null)){ %>
	試験が解答されていません
<% } else {%>
	<TABLE border=1>
		<TR>
			<TH>試験名</TH>
			<TH>問</TH>
			<TH>内容</TH>
			<TH>解答日</TH>
			<TH>正解率</TH>
		</TR>
	<% 		for (AnswerSum as : answerSumList) {
									float point=(100.0f * as.getNoOfSeikai() / as.getNoOfAnswer());
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	%>
				<tr>
				<td><%= as.getRefToi().get().getParent().getName() %></td>
				<td><%= as.getRefToi().get().getNo() %></td>
				<td><%= as.getRefToi().get().getName() %></td>
				<td><%= sdf.format(as.getAnswered()) %></td>
				<td>						<%= String.format("%1$.1f", point) %>
				</td>
				</tr>
			<%}%>
<% }%>

</body>
</html>