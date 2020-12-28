<%@page import="java.util.Calendar"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
<%@page import="java.util.Map"%>
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

		<%
			Map<Long, List<AnswerSum>> resultMap = (Map<Long, List<AnswerSum>>) request.getAttribute("resultMap");
		List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
		int[] seikai = new int[13];
		int[] answer = new int[13];
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -90);

		%>

		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="/">ホーム</a></li>
				<li class="breadcrumb-item active" aria-current="page">試験 一覧</li>
			</ol>
		</nav>

		<%@ include file="../common/headerLogin.jsp"%>

		<H1>分野別回答状況一覧（過去90日)</H1>
		<P>
			<a href="./report">解答済み試験の一覧</a> 分野別回答状況一覧
		</P>

		<main class="mb-5">

			<%
				if ((resultMap == null) || (resultMap.size() < 1)) {
			%>
			試験が解答されていません
			<%
				} else {
			%>
			<TABLE border="1" class="table table-striped table-hover ">
				<thead class="thead-dark">
					<TR>
						<TH>分野</TH>
						<TH>分野名</TH>
						<TH>問題</TH>
						<TH>正解率</TH>
					</TR>
				</thead>

				<%
					for (Genre g : genreList) {
				%>
				<tr>
					<td><%=g.getNo()%></td>
					<td><a href="/genreDetail/list?id=<%=g.getId()%>"><%=g.getName()%></a></td>
					<td>
						<%
							List<AnswerSum> asList = resultMap.get(g.getId());
						if (asList == null) {
							System.out.println("NULL!:" + g.getId());
							continue;
						}
						int a = 0, s = 0;
						for (AnswerSum as : asList) {
							Calendar answered = Calendar.getInstance();
							answered.setTime(as.getAnswered());

							if (answered.after(cal)) {
								%> 
						<p><%=as.getToi().get().getExamName()%>(<%=dateFormat(as.getAnswered())%>)
							:
							<%=as.getNoOfSeikai()%>問正解/<%=as.getNoOfAnswer()%>問中</p> <% 
							s += as.getNoOfSeikai();
							a += as.getNoOfAnswer();

							} else {
						%> 
						<p><s><small><%=as.getToi().get().getExamName()%>(<%=dateFormat(as.getAnswered())%>)
							:
							<%=as.getNoOfSeikai()%>問正解/<%=as.getNoOfAnswer()%>問中</small></s></p> <% 
							}
						%> <%
 }
 %>
					</td>
					<td><%=Math.round(s * 1000.0 / a) / 10.0%>%</td>
				</tr>
				<%
					seikai[g.getNo() - 1] += s;
				answer[g.getNo() - 1] += a;
				}
				%>
			</TABLE>
			<%
				}
			double sec = round(seikai[1 - 1], answer[1 - 1]);
			double alg = round(seikai[8 - 1], answer[8 - 1]);
			double gengo = round((seikai[12 - 1] + seikai[13 - 1]), (answer[12 - 1] + answer[13 - 1]));

			double[] sentaku = new double[5];
			sentaku[0] = round(seikai[2 - 1], answer[2 - 1]);
			sentaku[1] = round(seikai[3 - 1], answer[3 - 1]);
			sentaku[2] = round(seikai[4 - 1], answer[4 - 1]);
			sentaku[3] = round(seikai[5 - 1], answer[5 - 1]);
			sentaku[4] = round((seikai[6 - 1] + seikai[7 - 1]), (answer[6 - 1] + answer[7 - 1]));

			//	double[] sentaku={1,3,3,4,5};
			double[] yuusen = checkMax2(sentaku);
			%>
			<h2>必須</h2>
			<table border="1">
				<tr>
					<th></th>
					<th>セキュリティ</th>
					<th>アルゴリズム</th>
					<th>言語</th>
					<th>合計点</th>
				</tr>
				<tr>
					<th>正解率</th>
					<td><%=sec%>%</td>
					<td><%=alg%>%</td>
					<td><%=gengo%>%</td>
					<td></td>
				</tr>
				<tr>
					<th>得点</th>
					<td><%=Math.floor(20 * sec / 10.0) / 10.0%>点/20</td>
					<td><%=Math.floor(25 * alg / 10.0) / 10.0%>点/25</td>
					<td><%=Math.floor(25 * gengo / 10.0) / 10.0%>点/25</td>
					<td><%=Math.floor(20 * sec / 10.0 + 25 * alg / 10.0 + 25 * gengo / 10.0) / 10.0%>点/70</td>
				</tr>

			</table>
			<h2>選択（5問中2問）</h2>
			<table border="1">
				<tr>
					<th></th>
					<th>ハード<br />ソフト
					</th>
					<th>データベース</th>
					<th>ネットワーク</th>
					<th>システム開発</th>
					<th>ストラテジ<br />マネジメント
					</th>
					<th>合計点</th>
				</tr>
				<tr>
					<th>正解率</th>
					<% for(int i=0 ; i<=4 ; i++){
					%>
					<td><%=sentaku[i]%>%</td>
					<% 
					}
					%>
					<td></td>
				</tr>
				<tr>
					<th>得点</th>
					<% for(int i=0 ; i<=4 ; i++){
					%>
					<td <%=(yuusen[i] < 2.0) ? "bgcolor='CHARTREUSE'" : ""%>><%=Math.floor(15 * sentaku[i] / 10.0) / 10.0%>点/15</td>
					<% 
					}
					%>
					<%
						double sum = 0;
						for(int i=0 ; i<=4 ; i++){	
							if ((yuusen[i] < 2.0)){
								sum += Math.floor(15 * sentaku[i] / 10.0) / 10.0;
							}
						}
					%>
					<td><%=Math.floor(sum * 10) / 10%>点/30</td>
				</tr>
			</table>

		</main>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
<%!double round(int seikai, int answer) {
		double result = seikai * 1000.0;
		result /= answer;
		result = Math.round(result) / 10;
		return result;
	}

	double[] checkMax2(double[] sentaku) {
		double t[] = new double[sentaku.length];
		for (int i = 0; i < sentaku.length; i++) {
			for (int j = 0; j < sentaku.length; j++) {
				if (sentaku[i] < sentaku[j]) {
					t[i] += 1;
				}
			}
		}
		for (int i = 0; i < sentaku.length; i++) {
			double temp1 = t[i];
			double temp2 = t[i];
			for (int j = i + 1; j < sentaku.length; j++) {
				if (t[j] == temp1) {
					t[j] = ++temp2;
				}
			}
		}
		return t;
	}%>

</html>