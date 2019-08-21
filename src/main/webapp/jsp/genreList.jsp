<%@page import="com.googlecode.objectify.Ref"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
<%@page import="java.util.Map"%>
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
//		List<Genre> genreList = 	(List<Genre>)request.getAttribute("genreList");
	List<String[]> datas = (List<String[]>) request.getAttribute("datas");


	%>
<%@ include file="common/header.jsp"%><br>
    <h1>登録されている分野の一覧</h1>

	<%
		if (datas == null || datas.size() == 0) {
	%>
	分野が登録されていません
	<%
		} else {
	%>

		<TABLE border="1" class="table table-striped table-hover " align="center" >
		<thead class="thead-dark"><tr>
			<TH>分野名</TH>
			<TH>問題</TH>
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
　<%@ include file="common/footer.jsp"%>

</html>