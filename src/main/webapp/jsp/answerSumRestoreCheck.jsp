
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.AnswerSum"%>
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
	AnswerSum aSum = (AnswerSum)request.getAttribute("aSum");
	Toi copiedToi = (Toi)request.getAttribute("copiedToi");
	Toi orgToi = (Toi)request.getAttribute("orgToi");
	List<Question> copiedQuestion = (List<Question>)request.getAttribute("copiedQuestion");
	List<Question> orgQuestion = (List<Question>)request.getAttribute("orgQuestion");
	
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>複製された情報の確認</H1>

	<TABLE border=1>
<tr><th></th><TH>OLD</TH><TH>NEW</TH></tr>
<tr><th>Toi.id</th><Td><%= copiedToi.getId() %></Td><Td><%= orgToi.getId() %></Td></tr>
<tr><th>Toi.name</th><Td><%= copiedToi.getName() %></Td><Td><%= orgToi.getName() %></Td></tr>

<% for(int i=0 ; i<copiedQuestion.size();i++){%>
<tr><th> toiId: <%= i  %> </th>
<Td><%= copiedQuestion.get(i).getToiId() %>:<%= copiedQuestion.get(i).getName() %><br>(<%=  copiedQuestion.get(i).getId() %>)</Td>
<Td><%= orgQuestion.get(i).getToiId() %>:<%= orgQuestion.get(i).getName() %><br>(<%= orgQuestion.get(i).getId() %>)</Td></tr>
<% }%>

</table>

	<hr />
	<a href="/admin/answerSumRestore?answerSumId=<%= aSum.getId() %>">restore</a>

</body>
<%@ include file="common/footer.jsp"%>

</html>