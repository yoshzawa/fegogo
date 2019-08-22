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
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");
		List<String[]> datas2 = (List<String[]>) request.getAttribute("datas2");
		String ExamName = (String) request.getAttribute("ExamName");
	%>
	<%@ include file="../common/headerLogin.jsp"%>

	<H1>登録されている問の一覧</H1>

	<p>
		選択された試験：<%=ExamName%>
		<a href="/exam/list">(選択解除する)</a>
	</p>


	<%
		if (datas == null || datas.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1 class="table table-striped table-hover ">
		<thead class="thead-dark">
			<TR>
				<TH>問番号</TH>
				<TH>分野</TH>
				<TH>内容</TH>
				<TH>設問数</TH>
				<TH>過去の解答</TH>
			</TR>
		</thead>
		<%
			for (String[] s : datas) {
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
			<td><%=s[4]%></td>

		</tr>

		<%
			}
		%>
	</TABLE>

	<%
		}
	%>

	<H1>解答済み試験の一覧</H1>

	<%
		if ((datas2 == null) || (datas2.size() == 0)) {
	%>
	試験が解答されていません
	<%
		} else {
	%>
	<TABLE border=1 class="table table-striped table-hover ">
		<thead class="thead-dark">
			<TR>
				<TH>試験名</TH>
				<TH>問</TH>
				<TH>分野</TH>
				<TH>内容</TH>
				<TH>解答日</TH>
				<TH>正解率</TH>
			</TR>
		</thead>

		<%
			for (String[] s : datas2) {
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
			<td><%=s[4]%></td>
			<td align="right"><%=s[5]%></td>
		</tr>
		<%
			}
		%>
		<%
		}
		%>
	</TABLE>

</body>
<%@ include file="../common/footer.jsp"%>

</html>