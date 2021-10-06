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
  <div class="container">


	<%
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");
		List<String[]> datas2 = (List<String[]>) request.getAttribute("datas2");
		String ExamName = (String) request.getAttribute("ExamName");
		Boolean isOpened = (Boolean)request.getAttribute("isOpened");

	%>
	
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
			<li class="breadcrumb-item active" aria-current="page"><%= ExamName %></li>
		</ol>
	</nav>
	
	<%@ include file="../common/headerLogin.jsp"%>

	<H1>[<%= ExamName %>]試験：問の一覧</H1>
	    <main class="mb-5">

	<%
	if (datas == null || datas.size() == 0 || isOpened==false) {
	%>
	解答可能な問がありません
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
<p></p>
</main>
<%@ include file="../common/footer.jsp"%>
</div>
</body>

</html>