<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
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
	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>登録されている設問の一覧</H1>


	<%
		String parentId = (String) request.getAttribute("parentId");
			Toi parent = (Toi) request.getAttribute("parent");
			Map<Long,Question> qMap = (Map<Long,Question>) request.getAttribute("questionMap");
			Exam exam = (Exam) request.getAttribute("exam");
			List<Genre>genreList = (List<Genre>)request.getAttribute("genreList");
			Long genreId=0L;
			if(parent.getGenre() != null){
			genreId = parent.getGenre().getId();
			}
	%>

	<p>選択された試験：</p>
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(<%=exam.getName()%>の問一覧に戻る)
		</a>
	</p>

	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link" href="/admin/question/list?parentId=<%= parent.getId() %>">分野の変更</a></li>
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" >問題の登録</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/question/list3?parentId=<%= parent.getId() %>">複製</a></li>

	</ul>

	<%
		if ((qMap == null) || (qMap.size() == 0)) {
	%>
	設問が登録されていません
	<%
		} else {
	%>
	<table border="1">
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
			<td><%=q.getKana((int)q.getNoOfOption()) %></td>
			<td>
				<% for(int i : q.getAnswerSet()){%> <%=q.getKana(i) %> <% }%>
			</td>
			<td>edit不可</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	<hr />
	複製された問題の変更はできません


</body>
<%@ include file="common/footer.jsp"%>
</html>