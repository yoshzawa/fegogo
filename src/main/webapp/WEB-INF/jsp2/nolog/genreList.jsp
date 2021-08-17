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
			<li class="breadcrumb-item active" aria-current="page">分野 一覧</li>
		</ol>
	</nav>

	<%@ include file="../common/header.jsp"%><br>
	
	<h1>登録されている分野の一覧</h1>
	    <main class="mb-5">
	

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
				<TH>試験</TH>
				<TH>問題</TH>
				<TH>解答数</TH>
			</TR>
		</thead>
		<%
			for (String[] s : datas) {
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
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