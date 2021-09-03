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
  <div class="container">
		    <div class="mb-5">

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
				・（2021/09/03）ver.4.02.0をデプロイしました。解答後10日間は、解答した内容を確認できます。そのかわり、その問題は答えられません。10日経過すると、解答できるようになりますが、何を答えたのかは見えなくなります。
			</P>
			<P>
		・（2021/09/02）現在、システムが例外処理で落ちるケースが報告されています。現在調査中です。→多分直りました
	</P>
	
<p></p>
<%@ include file="../common/footer.jsp"%>
</div>
</div>
</body>

</html>