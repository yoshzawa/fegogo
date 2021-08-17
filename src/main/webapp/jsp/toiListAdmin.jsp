<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Genre"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="java.util.*"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
		Exam parentExam = (Exam) request.getAttribute("parent");
		TreeMap<Long, Toi> toiMap = (TreeMap<Long, Toi>) request.getAttribute("toiMap");
		String parentId = (String) request.getAttribute("parentId");
		List<Genre> genreList =		(List<Genre>)request.getAttribute("genreList");

	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
	<H1>登録されている問の一覧</H1>
	
	<p>選択された試験： <%= parentExam.getName() %><a href="/admin/exam/list">（EXAM一覧に戻る)</a>
	</p>

	<%
		if (toiMap == null || toiMap.size() == 0) {
	%>
	試験が登録されていません
	<%
		} else {
	%>
	<TABLE border=1>
		<TR>
			<TD>ToiId</TD>
			<TD>No</TD>
			<TD>Genre</TD>
			<TD>Name</TD>
			<TD>問題数</TD>
			<TD>回答者</TD>
			<TD>出題</TD>
		</TR>
		<%
			Set<Long> toiKeySet = toiMap.keySet();
				for (Long l : toiKeySet) {
					Toi t = toiMap.get(l);
		%>
		<tr>
			<td><%=t.getId()%></td>
			<td><%=t.getNo()%></td>
			<td><%
				if(t.getGenre() == null){
			%>
				no Genre
			<%
				} else {
			%>
				<%=t.getGenre().getName()%>
			<% } %>
			</td>
			<td><a href="/admin/question/list?parentId=<%=t.getId()%>"><%=t.getName()%></a></td>
			<td><%=t.getQuestionListSize()%></td>
			<td><%= t.getAnswerSumCount() %></td>
			<td><a href="/admin/question/image?parentId=<%=t.getId()%>">
			<% if(t.getImageSet() != null){out.print("(CBT)"); }%>
			画像調整</a></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<hr />
	
	<form method='post' action='/admin/toi/add'>
		<label>No</label> <input type="text" name="No" /> 
		<label>ToiName</label>
		<input type="text" name="toiName" /> 
		<select name="genreId" >
		<% for (Genre g : genreList){ %>
		<option value="<%= g.getId()%>"><%= g.getName() %></option>
		
		<% } %>
		</select>
		<input type="hidden"
			name="parentId" value='<%=parentId%>' /> 
			<input type="submit"
			name="追加" />
	</form>
</body>
　<%@ include file="common/footer.jsp"%>

</html>