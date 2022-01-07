<%@page import="java.util.Optional"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
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
	Map<Long, Question> qMap = (Map<Long, Question>) request.getAttribute("questionMap");
	Exam exam = (Exam) request.getAttribute("exam");
	List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
	Long genreId = 0L;
	if (parent.getGenre() != null) {
		genreId = parent.getGenre().getId();
	}
	CloneToi cloneToi = (CloneToi) request.getAttribute("cloneToi");
	Toi orgToi = (Toi) request.getAttribute("orgToi");
	Map<Long, CloneQuestion> cloneQuestionMap = (Map<Long, CloneQuestion>) request.getAttribute("cloneQuestionMap");
	Map<Long, Question> qMap2 = (Map<Long, Question>) 	request.getAttribute("questionMap2");
	%>

	<p>選択された試験：</p>
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(<%=exam.getName()%>の問一覧に戻る)
		</a>
	</p>

	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link"
			href="/admin/question/list?parentId=<%=parent.getId()%>">分野の変更</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/question/list2?parentId=<%=parent.getId()%>">問題の登録</a></li>
		<li class="nav-item"><a class="nav-link active"
			aria-current="page">複製</a></li>
	</ul>

	<h3>複製元とのリンク</h3>

	<table border=1>
		<tr>
			<th></th>
			<th>複製</th>
			<th>オリジナル</th>
		</tr>
		<tr>
			<th>toiId</th>
			<td><%=parent.getId()%></td>
			<td><%=orgToi.getId()%></td>
		</tr>
		<tr>
			<th>toiName</th>
			<td><%=parent.getName()%></td>
			<td><%=orgToi.getName()%></td>
		</tr>
		<tr>
			<th>toiName</th>
			<td><%=parent.getExamName()%></td>
			<td><%=orgToi.getExamName()%></td>
		</tr>
		<% for(long key :  qMap.keySet()){ 
			Question q = qMap.get(key);
			Optional<CloneQuestion> optCQ = Optional.ofNullable( cloneQuestionMap.get(q.getId()));
			Optional<Question> orgQ;
			if(optCQ.isPresent()){
				 orgQ = Optional.ofNullable( Question.getById(optCQ.get().getOriginalQuestionId()));
			}else{
				orgQ=Optional.empty();
			}
			if(orgQ.isPresent()){
		%>
		<tr>
			<th><%= q.getNo() %></th>
			<td><%= q.getName() %><br /><%=q.getId()%><br />( <%= q.getToiId() %>
				)</td>
			<td><%= orgQ.get().getName() %><br /><%=orgQ.get().getId()%><br />(
				<%= orgQ.get().getToiId() %> )
				<a href="/admin/question/clone/remove?id=<%=optCQ.get().getId() %>">remove</a>
				</td>
		</tr>
		<% } else {%>
		<tr>
			<th><%= q.getNo() %></th>
			<td><%= q.getName() %> <br /> <%= q.getId() %> <br /> ( <%= q.getToiId().toString() %>
				)</td>
			<td>未登録
				<form method="get" action="/admin/question/clone/add">
					<input type="hidden" name="cloneQuestionId"
						value="<%= q.getId() %>" /> <select name="orgQuestionId">
						<%for(Long qKey : qMap2.keySet()){ 

			%>
						<option value="<%= qMap2.get(qKey).getId()%>">
							<%= qMap2.get(qKey).getName() %>
						</option>

						<%} %>
					</select> <input type="submit" value="登録" />
				</form>
			</td>
		</tr>
		<% } %>

		<% } %>
	</table>


</body>
<%@ include file="common/footer.jsp"%>
</html>