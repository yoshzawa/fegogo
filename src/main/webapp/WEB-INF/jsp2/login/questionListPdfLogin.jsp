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
					document.getElementById("view1").style.display = "block";
				}

				function qOn() {
					const frame = document.getElementById("topFrame");
					frame.src = "https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/R01_10_12.pdf";
					document.getElementById("view1").style.display = "none";
					document.getElementById("view2").style.display = "block";
				}
				function topOn() {
					const frame = document.getElementById("topFrame");
					frame.src = "https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/top.pdf";
					document.getElementById("view1").style.display = "block";
					document.getElementById("view2").style.display = "none";
				}
			</script>


			<table>
				<tr>
					<td><iframe id="topFrame" width="600" height="550"
							style='border: solid #000000 1px; display: inline;'
							src="https://storage.googleapis.com/fegogo.appspot.com/pdfjs-2.13.216-dist/web/viewer.html?file=https://storage.googleapis.com/fegogo.appspot.com/mogipdf/top.pdf">
						</iframe></td>
					<td>
						<div class="tab-wrap">
							<input id="TAB-01" type="radio" name="TAB" class="tab-switch"
								onclick="topOn();" checked="checked" /> <label class="tab-label" for="TAB-01">i</label>
							<input id="TAB-02" type="radio" name="TAB" class="tab-switch"
								onclick="qOn();" /> <label class="tab-label" for="TAB-02">12</label>
						</div>
					</td>
					<td width=500px>
						<div>
							<div class="tab-content" id="view1" 
							style="display: none; border-width: 1px; border-style: solid; border-color: gray gray gray white; border-image: initial; height: 550px; background-color: lightgrey;">
							
								本試験ではここにいろいろと表示されます<br>
								右クリックで回答に取り消し線が引けたりする操作はできません。iPadだと右クリックできないし。<br>
								選択問題にチェックしたりする操作はまだできていません
								
								</div>
							<div class="tab-content" id="view2" style="display: none;">


								<form method="post" action="/answer" id="mainForm"
									name="mainForm">
									<input type="hidden" name="userId" value="<%=email%>" /> <input
										type="hidden" name="toiId" value="<%=toi.getId()%>" /> 解答欄
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
									</TABLE>
									<input type="submit" value="回答を送信する" />
								</form>
							</div>
						</div>

					</td>
				</tr>
			</table>


			<%
				}
			%>

			<p></p>
		</main>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>