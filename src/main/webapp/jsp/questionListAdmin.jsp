<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
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
//		List<Question> list = (List<Question>) request.getAttribute("questionList");
		Map<Long,Question> qMap = (Map<Long,Question>) request.getAttribute("questionMap");
		Exam exam = (Exam) request.getAttribute("exam");
		UserService userService = (UserService)
				request.getAttribute("userService");
	%>
		<%
		if ((userService!=null) && (userService.isUserAdmin() == true) ) {
	%>
	<h4 align="right">login as <%= userService.getCurrentUser().getNickname() %>(Admin)
	(<a href="<%= userService.createLogoutURL("/")%>">logout</a>)</h4>
	<%
		} 
	%>
	<p>選択された試験：
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
	</p>

	<%
		if ((qMap == null) || (qMap.size() == 0)) {
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
			<td>Answer</td>
		</tr>

		<%
			
			Set<Long> toiKeySet = qMap.keySet();
				for (Long l : toiKeySet) {
					Question q = qMap.get(l);
		
		%>
		<tr>
			<td><%=q.getId()%></td>
			<td><%=q.getNo()%></td>
			<td><%=q.getName()%></td>
			<td><%="アイウエオカキクケコサシスセソタチツテト".charAt((int)q.getNoOfOption()) %>
			</td>
			<td>
					<% for(int i : q.getAnswerSet()){%> 
						<%="アイウエオカキクケコサシスセソタチツテト".charAt(i) %>
				<% }%>
			</td>
			<td><a href="/admin/question/edit?id=<%=q.getId()%>">edit</a></td>
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

	<form method='post' action='/admin/question/addMulti'>
		<P>複数選択</P>
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
		</select> <br /> Answer
		<%
 	for (int i = 0; i <= 19; i++) {
 %>
		<input type="checkbox" name="correct" value="<%=i%>" />
		<%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
		<%
			}
		%>
		<input type="hidden" name="parentId" value='<%=parentId%>' /> <input
			type="submit">
	</form>
	<hr />

</body>
</html>