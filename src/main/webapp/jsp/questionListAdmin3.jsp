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
			Map<Long,Toi> toiMap = parent.getGenre().getToiMap(1);
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
		<li class="nav-item"><a class="nav-link" href="/admin/question/list2?parentId=<%= parent.getId() %>">問題の登録</a></li>
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" >複製</a></li>
					<li class="nav-item"><a class="nav-link"
			href="/admin/question/list4?parentId=<%=parent.getId()%>">解答一覧</a></li>
			
	</ul>

	<form method="post" action="/admin/ToiCopyServlet">
		<input type="hidden" name="fromToi" value="<%= parent.getId() %>" />
		<%= parent.getName() %>を、 EXAM ID<input type="text" name="toExam"
			value="" placeholder="作成するEXAMのID" />に、 問番号 <input type="text"
			size="2" name="toToiNo" value="<%= parent.getNo()%>" />に <input
			type="submit" value="作成する" />
	</form>
	<hr />
	<form method="post" action="/admin/toi/clone/add">
		<input type="hidden" name="fromToi" value="<%= parent.getId() %>" />
		この問題「<%= parent.getName() %>」を、
		<select name="toiId">
		<%
		for(Long key :toiMap.keySet()){
			Toi t = toiMap.get(key);
			if(!t.getId().equals(parent.getId())){
		%>
		<option value="<%= t.getId() %>"><%= t.getName() %>[<%= t.getExamName() %>]</option>
		<% }%>
		<% }%>
		</select>
		の複製にする
		<input type="submit" value="実行" />
	
	


</body>
<%@ include file="common/footer.jsp"%>
</html>