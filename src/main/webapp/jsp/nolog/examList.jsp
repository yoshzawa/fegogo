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
	%>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
		</ol>
	</nav>

	<%@ include file="../common/header.jsp"%><br>

	<h1>登録されている試験の一覧</h1>

	<%
		if (datas == null || datas.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>

	<TABLE border="1" class="table table-striped table-hover">
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


</body>
<%@ include file="../common/footer.jsp"%>

</html>