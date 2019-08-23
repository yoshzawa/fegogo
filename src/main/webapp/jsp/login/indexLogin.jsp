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

		List<String[]> datas = (List<String[]>)request.getAttribute("datas");
		List<String[]> datas2 = (List<String[]>)request.getAttribute("datas2");

	%>
	
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
		</ol>
	</nav>
	
	<%@ include file="../common/headerLogin.jsp"%>

	<h1>基本情報技術者試験　過去問題評価システム</h1>
<P>
8月24日中に、指定された課題を解き、その結果をシステムに入力してください。
その結果を基に分析を行い、対策期間のクラス分けなどを行います。
</P>

<P>
システムの利用には、Microsoftアカウントが必要です。指定された方法でログインし、
解答していない問題の解答を入力してください。
</P>

<H2>連絡</H2>
<P>
・8月27日（月曜）最初の対策授業は、807教室に集合してください。ただし、事前にiPadにメールが届いている学生は、
指定された教室に移動してください。
</P>

</body>


</body>
<%@ include file="../common/footer.jsp"%>

</html>