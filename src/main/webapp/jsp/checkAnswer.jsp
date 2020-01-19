<%@page import="java.util.Optional"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
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
	Optional<Answer> optAnswer =
	(Optional<Answer>)request.getAttribute("optAnswer");
	
	// answer -> answerSum
	Optional<AnswerSum> optAnswerSum = (Optional<AnswerSum> )
	request.getAttribute("optAnswerSum");

	// answer -> answerSum
	Optional<AnswerSum> optAnswerSumReal = (Optional<AnswerSum> )
	request.getAttribute("optAnswerSumReal");

	// answer -> Question
	Optional<Question> optQuestion = 
	(Optional<Question>)request.getAttribute("optQuestion");
	
	// answer -> Question
	Optional<Question> optQuestionReal = 
	(Optional<Question>)request.getAttribute("optQuestionReal");
	
	
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>解答詳細に関するチェック</H1>
	<%
	if (!optAnswer.isPresent()) {
	%>
	解答詳細が登録されていません
	<%
		} else {
	%>
	<table border="1">
	<tr>
	<td>Answer id</td><td><%= optAnswer.get().getId() %></td>
	<td>Answer date</td><td><%= Answer.getDateString( optAnswer.get().getAnswered()) %></td>
	
	</tr>
	</table>
	<table border="1">
	<tr>	<td>answer->AnswerSumへのリンク</td><td><%= optAnswerSum.get().getId() %></td>	</tr>
	<% if(optAnswerSumReal.isPresent()){%>
	<tr>	<td>AnswerSum確認 </td><td>存在する（ID=<%= optAnswerSumReal.get().getId() %>)</td>	</tr>
	<tr>	<td>AnswerSum.mapAnswerに含まれる </td><td><%= optAnswerSumReal.get().containAnswer(optAnswer.get().getId()) %></td>	</tr>
	<% } else {%>
	<tr> 	<td>AnswerSum確認 </td><td>存在しない</td>	</tr>
	<% }%>
	</table>
	<table border="1">
	<tr>	<td>answer->Questionへのリンク</td><td><%= optQuestion.get().getId() %></td>	</tr>
	<% if(optQuestionReal.isPresent()){%>
	<tr>	<td>Question確認</td><td><%= optQuestionReal.get().getId() %></td>	</tr>
	
	<% } else {%>
	<tr>	<td>Question確認</td><td>存在しない</td>	</tr>
	<% }%>
	</table>
	<%
		} 
	%>

	<hr />
</body>
　<%@ include file="common/footer.jsp"%>
</html>