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
			href="/admin/question/list?parentId=<%= parent.getId() %>">(選択解除する)</a>
	</p>

	<%
		if (q == null) {
	%>
	エラーが発生しました。登録されていない問題の変更が行われました。

	<%
		} else {
	%>
	<form method='post' action='/admin/question/update'>

		<table border=1>
			<tr>
				<td></td>
				<td>Id</td>
				<td>No</td>
				<td>Name</td>
				<td>NoOfOption</td>
			</tr>

			<tr>
				<th>変更前</th>
				<td><%=q.getId()%></td>
				<td><%=q.getNo()%></td>
				<td><%=q.getName()%></td>
				<td><%="アイウエオカキクケコサシスセソタチツテト".charAt((int) q.getNoOfOption())%>
				</td>
				<td><%=q.isMulti()%></td>
				<td>
					<%
							for (int i : q.getAnswerSet()) {
					%> <%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
					<%
							
						}
					%>
				</td>

			</tr>
			<tr>
				<th>変更後</th>
				<td><%=q.getId()%><input type="hidden" name="parentId"
					value="<%=q.getId()%>" /></td>
				<td><input type="number" size="2" name="No"
					value="<%=q.getNo()%>" /></td>
				<td><input type="text" size="20" name="Qname"
					value="<%=q.getName()%>" /></td>
				<td><select name="noOfOption">
						<%
							for (int i = 0; i <= 19; i++) {
								if (q.getNoOfOption() == i) {
						%>
						<option value="<%=i%>" selected>
							<%
								} else {
							%>
						
						<option value="<%=i%>">
							<%
							}
							%>
													<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%></option>
							<%
							}
							%>
						
				</select></td>
				<td><%=q.isMulti()%></td>
				<td>
		<%
 	for (int i = 0; i <= 19; i++) {
 %>
						<%
							if (q.isCorrect(i) == true) {
						%>
		<input type="checkbox" name="correct" value="<%=i%>" checked/>
 <% } else {%>
		<input type="checkbox" name="correct" value="<%=i%>" />
 <% } %>
		<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
		<%
			}
		%>
		</td>	

		</table>
		<input type="submit">

	</form>
	<%
		}
	%>

</body>
</html>