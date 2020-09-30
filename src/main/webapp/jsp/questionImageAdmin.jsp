<%@page import="jp.ac.jc21.t.yoshizawa.objectify.ImageSet"%>
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
		Map<Long, Question> qMap = (Map<Long, Question>) request.getAttribute("questionMap");
		Exam exam = (Exam) request.getAttribute("exam");
		List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
		Long genreId = 0L;
		if (parent.getRefGenre() != null) {
			genreId = parent.getRefGenre().get().getId();
		}
		List<ImageSet> imageSet = (List<ImageSet>) request.getAttribute("imageSet");
	%>

<div style="overflow: auto;">
<table border=1>
	<% 
		for (ImageSet is : imageSet){
			if(is.isImage()==true){
	%>
	<tr><td>
	<p  style="position:relative;
		width: 1000px;
		height: <%= is.getHeight() %>px;
		top:  <%= is.getTop() %>px;
		overflow: hidden;">
		<img alt="" src="<%= is.getUrl() %>" height=1000>
	</p></td></tr> 
	<% } else { %>
	<tr><td>Question <%= is.getQuestionIds()+"" %></td></tr> 
	<% } %>
	<% } %>
</table>
</div>
<div style="float:right;overflow: auto;" >

<% int no=1;%>
<table border=1>
	<% 
		for (ImageSet is : imageSet){
			if(is.isImage()==true){
	%>
	<% } else { %>
		<tr><td><%= no %></td><td>
	<% 	for(Long id : is.getQuestionIds()){
	%>
		Question <%= id %><br>
	<% } %>
	</td></tr>
	<% } no++;%>
	<% } %>
		<tr><td></td><td>
		<form method="post" action="./imageAdd">
		画像を追加する<br>
		位置：<input type="number" value="1" name="no">の前<br>
		URL：http://～～image/<input type="url" value="" name="url"><br>
		<input type="submit" value="追加する">
		</form>
		</td></tr>
	
</table>
</div>

	
</body>
<%@ include file="common/footer.jsp"%>
</html>