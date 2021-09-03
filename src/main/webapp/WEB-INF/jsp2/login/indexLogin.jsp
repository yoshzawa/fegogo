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

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
		</ol>
	</nav>

	<%@ include file="../common/headerLogin.jsp"%>

	<h1>基本情報技術者試験 過去問題評価システム</h1>
	    <main class="mb-5">

	<P>システムの利用には、Microsoftアカウントが必要です。指定された方法でログインし、
		解答していない問題の解答を入力してください。</P>

	<H2>連絡</H2>
		<P>
		・（2021/09/02）現在、システムが例外処理で落ちるケースが報告されています。現在調査中です。問に答えた後の画面でエラーになるようですが、解答自体はされていることが多いようです。<BR />
		お手数をおかけしますが、修正完了までしばらくお待ちください。
	</P>
	
	<P>
	・（2020/12/24）
	  <a href="/toi/list?parentId=5751404605997056">令和元年秋期</a>、
	  <a href="/toi/list?parentId=5679095853613056">平成31年度春期</a>、
	  <a href="/toi/list?parentId=5640825748848640">平成30年度秋期</a>、
	  <a href="/toi/list?parentId=5680529164730368">平成30年度春期</a>、
	  <a href="/toi/list?parentId=5717649963089920">平成29年度秋期</a>、
	  <a href="/toi/list?parentId=5686220801703936">平成29年度春期</a>、
	  <a href="/toi/list?parentId=5684489393012736">平成28年度秋期</a>、
	  <a href="/toi/list?parentId=5477550042120192">平成28年度春期</a>、
	  の問題のCBT化が完了しています。<br />
	</P>
	・（2020/12/24）
	  <a href="/report2">個人結果分析</a>
	が可能になっています。各自参照し、苦手な問題を繰り返し解いてください。
	
	</P>
	<P>
	・（2020/09/23）CBT対策で、問題文付きの解答サイトを作成しました。完成し次第徐々に公開していきます。　現在、いくつかの必須問題のCBT化が終わっています。
	</P>
<p></p>
</main>
</div>
<%@ include file="../common/footer.jsp"%>
</body>




</html>