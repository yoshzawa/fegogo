<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	Toi toi = (Toi) request.getAttribute("parent");
List<String[]> datas = (List<String[]>) request.getAttribute("datas");
%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/mogi.css" />

<title><%=toi.getExam().getYYYYMM()%> 問<%=toi.getNo()%> <%=toi.getName()%></title>
</head>
<body>
	<div class="container-fluid">


		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
				<li class="breadcrumb-item"><a
					href="/toi/list?parentId=<%=toi.getExamId()%>"><%=toi.getExamName()%>
						試験</a></li>
				<li class="breadcrumb-item active" aria-current="page">問<%=toi.getNo()%>
					<%=toi.getGenreName()%> (<%=toi.getName()%>)
				</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>

		<main>

			<%
				if ((datas == null) || (datas.size() == 0)) {
			%>
			設問が登録されていません
			<%
				} else {
			%>

			<script>
				window.onload = function() {
					document.getElementById("mainForm").onsubmit = function() {
						return confirm("この内容で登録しますか?");
					}
				}

				function qOn() {
					const frame = document.getElementById("topFrame");
					frame.src = "https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/R01_10_12.pdf";
				}
				function topOn() {
					const frame = document.getElementById("topFrame");
					frame.src = "https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/top.pdf";
				}
			</script>

			<form method="post" action="/answer" id="mainForm" name="mainForm">
				<input type="hidden" name="userId" value="<%=email%>" /> <input
					type="hidden" name="toiId" value="<%=toi.getId()%>" />

				<table>
					<tr>
						<td><iframe id="topFrame" width="600" height="550"
								style='border: solid #000000 1px; display: inline;'
								src="https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/top.pdf">
							</iframe></td>
						<td>
<div class="tab-wrap">
    <input id="TAB-01" type="radio" name="TAB" class="tab-switch" checked="checked" /><label class="tab-label" for="TAB-01">ボタン 1</label>
    <div class="tab-content">
        コンテンツ 1
    </div>
    <input id="TAB-02" type="radio" name="TAB" class="tab-switch" /><label class="tab-label" for="TAB-02">ボタン 2</label>
    <div class="tab-content">
        コンテンツ 2
    </div>
    <input id="TAB-03" type="radio" name="TAB" class="tab-switch" /><label class="tab-label" for="TAB-03">ボタン 3</label>
    <div class="tab-content">
        コンテンツ 3
    </div>
</div>
							<div class="col-sm-6">

								<div class="col-xs-3">
									<!-- required for floating -->
									<!-- Nav tabs -->
									<ul class="nav nav-tabs tabs-left">
										<li class="active"><a href="#home" data-toggle="tab"
											onclick="topOn()">i</a></li>
										<li><a href="#profile" data-toggle="tab" onclick="qOn()">12</a></li>
									</ul>
								</div>

								<div class="col-xs-9">
									<!-- Tab panes -->
									<div class="tab-content">
									<!-- panes1 -->
										<div class="tab-pane active" id="home">information</div>
									<!-- panes2 -->
										<div class="tab-pane" id="profile">
											profile
											<TABLE border=1
												class="table table-striped table-hover table-responsive">
												<thead class="thead-dark">
													<TR>
														<th>設問</th>
														<th>解答欄</th>
													</TR>
												</thead>

												<%
													for (String[] s : datas) {
												%>
												<tr>
													<td><%=s[0]%></td>
													<td><%=s[1]%></td>

												</tr>
												<%
													}
												%>
											</table>
										</div>
									</div>
								</div>

								<div class="clearfix"></div>

							</div>

						</td>
					</tr>

				</table>
				<input type="submit" value="送信する" />
			</form>


			<%
				}
			%>

			<p></p>
		</main>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>