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
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");
		List<String[]> datas2 = (List<String[]>) request.getAttribute("datas2");
	%>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
		</ol>
	</nav>

	<%@ include file="../common/headerLogin.jsp"%>

	<h1>基本情報技術者試験 過去問題評価システム</h1>

	<P>システムの利用には、Microsoftアカウントが必要です。指定された方法でログインし、
		解答していない問題の解答を入力してください。</P>

	<H2>連絡</H2>
	<P>
	・現在、ほぼすべてのデータを削除しています。復旧までしばらくお待ちください。
	</P>

</body>


<body></body>
<%@ include file="../common/footer.jsp"%>

</html>