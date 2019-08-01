<%@page import="com.googlecode.objectify.Ref"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.*"%>
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
		List<AnswerSum> answerSumList = (List<AnswerSum>) request.getAttribute("answerSumList");
		String redirectTo = (String) request.getAttribute("redirectTo");
	%>

	<%@ include file="common/headerAdmin.jsp"%><br>
	
<H1>登録されている解答の一覧</H1>
	<%
		if (answerSumList == null || answerSumList.size() == 0) {
	%>
	試験が解答されていませんされていません
	<%
		} else {
	%>
	<a href="/admin/answerSum/dumpAnswerSummary.csv">解答情報をCSVでダウンロード</a>
	<a href="/admin/answer/dumpAnswer.csv">解答詳細をCSVでダウンロード</a>
	<TABLE border=1>
		<TR>
			<TD>getId</TD>
			<TD>getName</TD>
			<TD>getNoOfAnswer</TD>
			<TD>getNoOfSeikai</TD>
			<TD>getAnswered</TD>
			<TD>getMapAnswer</TD>
			<TD>getRefMember</TD>
			<TD>EXAM</TD>
			<TD>NO</TD>
			<TD>TOI</TD>
		</TR>
		<%
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			for (AnswerSum as : answerSumList) {
				Toi toi = as.getRefToi().get();
				Ref<Member> member=as.getRefMember();
		%>
		<tr>
			<td><%= as.getId() %>
			<% if(member != null){%>
			<br /><a href="/admin/answerSum/delete?memberId=<%= member.get().geteMail() %>&AnswerSumId=<%= as.getId()%>">delete
						<% }%>
			</td>
			<td><%= as.getName() %></td>
			<td><%= as.getNoOfAnswer() %></td>
			<td><%= as.getNoOfSeikai() %></td>
			<td><%= sdf.format(as.getAnswered()) %></td>
			<td>
				<% Map<String,Ref<Answer>> m = as.getMapRefAnswer(); %>
				<% for(String k : m.keySet()){%>
					<%= m.get(k).get().getId() %><br />
				<% } %>
			</td>
			<td>
			<%  if ((member == null )||(as.getRefMember().get().containsRef(as) == false)) {%>
			<a href='/admin/answerSum/reChain?answerSumId=<%= as.getId() %>&memberId=<%= as.getName() %>&redirectTo=<%= redirectTo %>'>
			reChain</a></td>
			<% } else {%>
			<%= member.get().geteMail() %>
			<% } %>
			<td><%= toi.getParent().getName() %></td>
			<td><%= toi.getNo() %></td>
			<td><%= toi.getName() %></td>
		</tr>
		<%
			}
		%>
	</TABLE>
	<%
		}
	%>

	<hr />

</body>
　<%@ include file="common/footer.jsp"%>
</html>