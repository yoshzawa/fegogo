<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%@ include file="/jsp/common/headerAdmin.jsp"%><br>

	<H1>TioのImport</H1>

	<P>最初の行「"id,No,Name,Created,OnOfOption,ToiId,Answers"」を消してからコピペしてください</P>
	<form method="post" action="./question">
		<textarea name="question" rows="20" cols="100"></textarea>
		<input type="submit" value="送信">
	</form>






</body>
<%@ include file="/jsp/common/footer.jsp"%>
</html>