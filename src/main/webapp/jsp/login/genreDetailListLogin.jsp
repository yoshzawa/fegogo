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
		String  genreName = (String) request.getAttribute("genreName");

	%>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item"><a href="/"genre/>分野 一覧</a></li>
			<li class="breadcrumb-item active" aria-current="page">[<%= genreName %>]分野 一覧</li>
		</ol>
	</nav>
	<%@ include file="../common/headerLogin.jsp"%><br>
	<h1>[<%= genreName %>]分野の一覧</h1>

	<%
		if (datas == null || datas.size() == 0) {
	%>
	分野が登録されていません
	<%
		} else {
	%>

	<TABLE border="1" class="table table-striped table-hover">
		<thead class="thead-dark">
			<tr>
				<TH>問題</TH>
				<TH>解答数</TH>
				<TH>平均正解率</TH>
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
			<td><%=s[4]%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
		</tr>
		<%
			}
		%>
	</TABLE>

	<%
		}
	%>


</body>
<%@ include file="../common/footer.jsp"%>

</html>