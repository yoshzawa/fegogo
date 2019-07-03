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
		選択された試験：<P>
	<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
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
			<td>Id</td>
			<td>No</td>
			<td>Name</td>
			<td>NoOfOption</td>
			<td>Multi</td>
			<td>Answer</td>
			<td>Correct</td>
		</tr>
	
		<%
			for (Question q : list) {
		%>
		<tr>
			<td><%=q.getId()%></td>
			<td><%=q.getNo()%></td>
			<td><%=q.getName()%></td>
			<td>
			<%="アイウエオカキクケコサシスセソタチツテト".charAt((int)q.getNoOfOption()) %>
			</td>
			<td><%=q.isMulti()%></td>
			<td>
			<%="アイウエオカキクケコサシスセソタチツテト".charAt((int)q.getAnswer()) %>
			</td>
			<td><%=q.getCorrect()%></td>
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
		No<input type="text" name="No" /><br /> 
		Name<input type="text"
			name="Qname" /><br /> 
			#ofOption <select name="noOfOption">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select> <br /> 
		Answer <select name="answer">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select> <br /> 
				<input type="hidden"
			name="parentId" value='<%=parentId%>' /> <input type="submit">
	</form>
	<hr />

	<form method='post' action='/admin/question/addMulti'>
		<P>複数選択</P>
		No<input type="text" name="no" /><br /> Name<input type="text"
			name="Qname" /><br /> #ofOption <select name="noOfOption">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select> <br /> Answer
		<%
 	for (int i = 0; i <= 19; i++) {
 %>
		<input type="checkbox" name="correct" value="<%=i%>" />
		<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
		<%
			}
		%>		<input type="hidden"
			name="parentId" value='<%=parentId%>' /> 
		<input type="submit">
	</form>
	<hr />

</body>
</html>