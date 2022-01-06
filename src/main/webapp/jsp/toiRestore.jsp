
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
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>登録されている問の一覧</H1>


	<hr />

</body>
<%@ include file="common/footer.jsp"%>

</html>