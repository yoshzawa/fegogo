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
	・（2020/09/23）CBT対策で、問題文付きの解答サイトを作成しました。完成し次第徐々に公開していきます。　<br />
	  <a href="/toi/list?parentId=5679095853613056">平成31年度春期</a>と
	  <a href="/toi/list?parentId=5640825748848640">平成30年度秋期</a>の必須問題のCBT化が終わっています。
	  
	
	
	</P>
<p></p>
</main>
</div>
<%@ include file="../common/footer.jsp"%>
</body>




</html>