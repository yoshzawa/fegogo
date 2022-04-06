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
		%>

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>


		<H1>登録されている試験の一覧</H1>
		<main class="mb-5">

			<%
				if (datas == null || datas.size() == 0) {
			%>
			試験が登録されていません
			<%
				} else {
			%>
			<TABLE border="1" class="table table-striped table-hover ">
				<thead class="thead-dark">
					<tr>
						<TH>試験名</TH>
						<TH>問題登録</TH>
					</TR>
				</thead>
				<%
					for (String[] s : datas) {
				%>
				<tr>
					<td><%=s[0]%></td>
					<td><%=s[1]%></td>
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