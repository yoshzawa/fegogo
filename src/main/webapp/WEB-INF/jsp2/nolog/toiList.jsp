<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.*"%>
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
  <div class="container">


	<%
	String ExamName  = (String)request.getAttribute("ExamName");
		List<String[]> datas = (List<String[]>)request.getAttribute("datas");
	%>
	
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
			<li class="breadcrumb-item active" aria-current="page"><%= ExamName %></li>
		</ol>
	</nav>

	<%@ include file="../common/header.jsp"%>

	<H1>登録されている問の一覧</H1>
    <main role="main" class="container">

	<%
		if (datas == null || datas.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>

	<TABLE border=1 class="table table-striped table-hover ">
		<thead class="thead-dark">
			<tr>
				<TH>問番号</TH>
				<TH>ジャンル</TH>

				<TH>テーマ</TH>
				<TH>設問数</TH>
			</TR>
		</thead>
		<%
		for(String[] s : datas){
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
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
<p></p>
</main>
<%@ include file="../common/footer.jsp"%>
</div>
</body>

</html>