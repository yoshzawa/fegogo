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
	<P>8月24日中に、指定された課題を解き、その結果をシステムに入力してください。
		その結果を基に分析を行い、対策期間のクラス分けなどを行います。</P>

	<P>システムの利用には、Microsoftアカウントが必要です。指定された方法でログインし、
		解答していない問題の解答を入力してください。</P>

	<H2>連絡</H2>
	<P>
		・対策期間中の名簿を作成しました。<a href="https://storage.googleapis.com/fegogo.appspot.com/documents/FE%E5%AF%BE%E7%AD%96%E5%90%8D%E7%B0%BF20190828.pdf">こちら</a>からダウンロードしてください。<br/>
		　月曜日と火曜日はAクラス(804教室)とBクラス(807教室)、水曜日から金曜日はCクラス(804教室)とDクラス(807/510教室)に集合してください。
	</P>
	
	<P>
		・8月23日（金曜）より、8月24日（土曜）にかけて、プログラムの不具合により登録時にエラーが発生していました。現在、エラーは修正されていますので、ご迷惑をおかけしますが、入力エラーだったデータの再入力を宜しくお願いいたします。
	</P>
	<P>・8月27日（月曜）最初の対策授業は、807教室に集合してください。ただし、事前にiPadにメールが届いている学生は、
		指定された教室に移動してください。</P>

</body>


<body></body>
<%@ include file="../common/footer.jsp"%>

</html>