<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H1>登録されている設問の一覧</H1>


	<%
		String parentId = (String) request.getAttribute("parentId");
		Toi parent = (Toi) request.getAttribute("parent");
		List<Question> list = (List<Question>) request.getAttribute("questionList");
		Exam exam = (Exam) request.getAttribute("exam");
	%>
	<p>選択された試験：
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
	</p>

	<%
		if ((list == null) || (list.size() == 0)) {
	%>
	設問が登録されていません
	<%
		} else {
	%>
	<table border=1>
		<tr>
			<td>Name</td>
			<td>NoOfOption</td>
			<td>Multi</td>
		</tr>

		<%
			for (Question q : list) {
		%>
		<tr>
			<td><%=q.getName()%></td>
			<td>
				<% for(int i= 0 ; i<(int)q.getNoOfOption() ; i++) {%> 
				<input
				type="radio" name="<%= q.getNo() %>" value="<%= i %> %>" /> <%="アイウエオカキクケコサシスセソタチツテト".charAt(i) %>
				<%} %>
			</td>
			<td><%=q.isMulti()%></td>

		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>

</body>
</html>