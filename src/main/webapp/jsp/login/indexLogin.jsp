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
		・9月27日、対策期間中の名簿を更新しました。
		月～水曜の名簿は<a href="https://storage.googleapis.com/fegogo.appspot.com/documents/FE%E5%AF%BE%E7%AD%96%E5%90%8D%E7%B0%BF%20%E6%9C%88%E7%81%AB%E6%B0%B42.pdf">こちら</a>、
		木・金曜の名簿は<a href="https://storage.googleapis.com/fegogo.appspot.com/documents/FE%E5%AF%BE%E7%AD%96%E5%90%8D%E7%B0%BF%20%E6%9C%A8%E9%87%912.pdf">こちら</a>からダウンロードしてください。<br/>
			なお、該当者にはメールで連絡していますので、ご確認いただきますよう、宜しくお願いいたします。
		
	</P>
	<P>
		・8月30日、対策期間中の名簿を更新しました。
		月曜日と火曜日の名簿は<a href="https://storage.googleapis.com/fegogo.appspot.com/documents/FE%E5%AF%BE%E7%AD%96%E5%90%8D%E7%B0%BF%20%E6%9C%88%E6%9B%9C%E3%83%BB%E7%81%AB%E6%9B%9C.pdf">こちら</a>、
		水曜日・木曜日・金曜日の名簿は<a href="https://storage.googleapis.com/fegogo.appspot.com/documents/FE%E5%AF%BE%E7%AD%96%E5%90%8D%E7%B0%BF%20%E6%B0%B4%E6%9B%9C%E3%83%BB%E6%9C%A8%E6%9B%9C%E3%83%BB%E9%87%91%E6%9B%9C%20.pdf">こちら</a>からダウンロードしてください。<br/>
			なお、該当者にはメールで連絡していますので、ご確認いただきますよう、宜しくお願いいたします。
		
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