<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Answer"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
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
<H1>解答を登録しました</H1>

	<%
	AnswerSum ansSummary = (AnswerSum)request.getAttribute("ansSummary");
	Map<Integer,Answer> answer = ansSummary.getMapAnswer();
	%>
	
	<%= ansSummary.getNoOfAnswer()  %>問中<%= ansSummary.getNoOfSeikai()  %>問正解<br />
	
	<% Set<Integer> keyset = answer.keySet();  %>
	<% for(Integer i : keyset){ %>
	<% Answer a =answer.get(i); %>
	<%= a.getRefQuestion().get().getName() %>
	<%= a.getRefQuestion().get().getAnswers() %>
	<%= a.getAnswers() %>
	<% }%>
	
	


</body>
</html>