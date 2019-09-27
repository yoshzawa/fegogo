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
	%>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>

		</ol>
	</nav>

	<%@ include file="../common/header.jsp"%><br>

	<P>システムの利用には、Microsoftアカウントが必要です。指定された方法でログインし、
		解答していない問題の解答を入力してください。</P>

	<H2>連絡</H2>
	<P>
	　・9月27日、対策期間中の名簿を更新しました。10月2日の対策授業の教室を指示しています。ログインして名簿を確認してください。
	なお、該当者にはメールで連絡していますので、ご確認いただきますよう、宜しくお願いいたします。
	</P>
	<P>
	　・8月30日、対策期間中の名簿を更新しました。9月2日の対策授業の教室を支持しています。ログインして名簿を確認してください。
	なお、該当者にはメールで連絡していますので、ご確認いただきますよう、宜しくお願いいたします。
	</P>
	<P>
		・8月23日（金曜）より、8月24日（土曜）にかけて、プログラムの不具合により登録時にエラーが発生していました。現在、エラーは修正されていますので、ご迷惑をおかけしますが、入力エラーだったデータの再入力を宜しくお願いいたします。
	</P>

</body>
<%@ include file="../common/footer.jsp"%>

</html>