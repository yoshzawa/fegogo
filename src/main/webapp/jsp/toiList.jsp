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


	<%
	String ExamName  = (String)request.getAttribute("ExamName");
		List<String[]> datas = (List<String[]>)request.getAttribute("datas");
	%>
	<%@ include file="common/header.jsp"%>

	<H1>登録されている問の一覧</H1>

	<p>
		選択された試験：<%= ExamName %>
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


</body>
<%@ include file="common/footer.jsp"%>

</html>