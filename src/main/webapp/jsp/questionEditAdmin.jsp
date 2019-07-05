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
	<H1>登録されている設問の編集</H1>


	<%
		String parentId = (String) request.getAttribute("parentId");
		Toi parent = (Toi) request.getAttribute("parent");
		Question q = (Question) request.getAttribute("q");
		Exam exam = (Exam) request.getAttribute("exam");
	%>
	<p>選択された試験：
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
	</p>

	<%
		if (q == null)  {
	%>
	エラーが発生しました。登録されていない問題の変更が行われました。

	<%
		} else {
	%>
	<table border=1>
		<tr>
			<td></td>
			<td>Id</td>
			<td>No</td>
			<td>Name</td>
			<td>NoOfOption</td>
			<td>Multi</td>
			<td>Answer</td>
		</tr>

		<tr>
		<th>変更前</th>
			<td><%=q.getId()%></td>
			<td><%=q.getNo()%></td>
			<td><%=q.getName()%></td>
			<td><%="アイウエオカキクケコサシスセソタチツテト".charAt((int)q.getNoOfOption()) %>
			</td>
			<td><%=q.isMulti()%></td>
			<td>
				<% if(q.isMulti() == false){%> 
					<%="アイウエオカキクケコサシスセソタチツテト".charAt((int)q.getAnswer()) %>
				<% } else {%> 
					<% for(int i : q.getCorrect()){%> 
						<%="アイウエオカキクケコサシスセソタチツテト".charAt(i) %>
					<% }%> 
				<% }%>
			</td>
		</tr>
		<tr>
			<th>変更後</th>
			<td><%=q.getId()%></td>
			<td><%=q.getNo()%></td>
			<td><%=q.getName()%></td>
			<td><select name="noOfOption">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select></td>
					<td><%=q.isMulti()%></td>
		<td>
		<select name="answer">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select></td>
	</table>
	<%
		}
	%>
	<hr />
	<P>単一問題</P>
	<form method='post' action='/admin/question/add'>
		No<input type="text" name="No" /><br /> Name<input type="text"
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
		</select> <br /> Answer <select name="answer">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
			</option>
			<%
				}
			%>
		</select> <br /> <input type="hidden" name="parentId" value='<%=parentId%>' />
		<input type="submit">
	</form>
	<hr />


</body>
</html>