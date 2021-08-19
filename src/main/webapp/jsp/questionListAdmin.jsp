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
			href="/admin/toi/list?parentId=<%=exam.getId()%>">(<%=exam.getName()%>の問一覧に戻る)</a>
	</p>

	<form method="post" action="/admin/toi/changeGenre">
	<p>分野
	<select name="genreId">
	<% for (Genre g : genreList){ %>
	<% if(g.getId() == genreId){ %>
	<option value="<%= g.getId()%>" selected="selected"><%=g.getName() %></option>
	<% } else {%>
	<option value="<%= g.getId()%>"><%=g.getName() %></option>
	<% } %>
	<% } %>
	</select> 
	<input type="hidden" name="toiId" value="<%= parentId%>" />
	<input type="submit" value="分野変更" />
	</p>
	</form>
	



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
			<td><%=q.getKana((int)q.getNoOfOption()) %>
			</td>
			<td>
					<% for(int i : q.getAnswerSet()){%> 
						<%=q.getKana(i) %>
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

	<form method='post' action='/admin/question/addMulti'>
		No<input type="text" name="No" /><br /> Name<input type="text"
			name="Qname" /><br /> #ofOption <select name="noOfOption">
			<%
				for (int i = 0; i <= 19; i++) {
			%>
			<option value="<%=i%>">
				<%=Question.getKana(i)%>
			</option>
			<%
				}
			%>
		</select> <br /> Answer
		<%
 	for (int i = 0; i <= 19; i++) {
 %>
		<input type="checkbox" name="correct" value="<%=i%>" />
		<%=Question.getKana(i)%>
		<%
			}
		%>
		<br /><input type="hidden" name="parentId" value='<%=parentId%>' /> <input
			type="submit">
	</form>
	<hr />

</body>
　<%@ include file="common/footer.jsp"%>
</html>