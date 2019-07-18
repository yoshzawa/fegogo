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
	<%
	AnswerSum ansSummary = (AnswerSum)request.getAttribute("ansSummary");
	Map<Integer,Answer> answer = ansSummary.getMapAnswer();
	String email = (String)request.getAttribute("email");
	%>
		<h4 align="right">
<%= email %>としてサインイン（<a href="/openidSignOut">Sign out</a>）
</h4>
<H1>解答を登録しました</H1>

	
	<%= ansSummary.getNoOfAnswer()  %>問中<%= ansSummary.getNoOfSeikai()  %>問正解<br />
	
	<% Set<Integer> keyset = answer.keySet();  %>
	<table border=1>
	<% for(Integer i : keyset){ %>
	<% Answer a =answer.get(i); %>
	<tr>
	<th> <%= a.getRefQuestion().get().getName() %> </th>
	<td> <%= a.getRefQuestion().get().getAnswers() %> </td>
	<td> <%= a.getAnswers() %> </td>
	</tr>
	<% }%>
	</table>
	
	<a href="/toi/list?parentId=<%= ansSummary.getRefToi().get().getParent().getId() %>">戻る</a>


</body>
</html>