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
		%>

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>




		<H1>解答済み試験の一覧</H1>
		<P>
			解答済み試験の一覧 <a href="./report2">分野別回答状況一覧</a>
		</P>

		<main class="mb-5">



			<%
				if ((datas == null) || (datas.size() < 1)) {
			%>
			試験が解答されていません
			<%
				} else {
			%>
			<TABLE border="1" class="table table-striped table-hover ">
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
					for (String[] s : datas) {
				%>
				<tr>
					<td><%=s[0]%></td>
					<td><%=s[1]%></td>
					<td><%=s[2]%></td>
					<td><%=s[3]%></td>
					<td><%=s[4]%></td>
					<td align='right'><%=s[5]%></td>
				</tr>
				<%
					}
				%>
			</TABLE>
			<%
				}
			%>

			<p></p>
		</main>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>

</html>