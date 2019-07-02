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
	<p>
		<P>選択された試験：<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentid=<%=parent.getId()%>">(選択解除する)</a></p>

		<%
			if ((list == null) || (list.size() == 0)) {
		%>
		設問が登録されていません
		<%
			} else {
		%>
		<table border=1>
		<%
				for (Question q : list) {
		%>
		<tr>
		<td><%= q.getId() %></td>
		<td><%= q.getNo() %></td>
		<td><%= q.getNoOfOption() %></td>
		<td><%= q.getAnswer() %></td>
		<td><%= q.isMulti() %></td>
		<td><%= q.getCorrect() %></td>
		</tr>
		<%
				}
		%>
		</table>
		<%
			}
		%>
		<hr />
		<P>単一問題</P>
	<form method='post' action='/admin/question/add'>
	No<input type="text" name="no" /><br />
	Name<input type="text" name="Qname" /><br />
	#ofOption<input type="text" name="noOfOption" /><br />
	Answer<input type="text" name="answer" /><br />
	<input type="submit">
	</form>
		<hr />

	<form method='post' action='/admin/question/addMulti'>
		<P>複数選択</P>
	No<input type="text" name="no" /><br />
	Name<input type="text" name="Qname" /><br />
	#ofOption<input type="text" name="noOfOption" /><br />
	Answer
	<% for (int i=0 ; i<=19 ; i++){ %>
	<input type="checkbox" name="correct" value="<%= i %>" />
	<%= "アイウエオカキクケコサシスセソタチツテト".charAt(i) %> 
		<%
			}
		%>
	<input type="submit">
	</form>
		<hr />
	
</body>
</html>