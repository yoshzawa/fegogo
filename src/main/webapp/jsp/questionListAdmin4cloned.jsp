<%@page import="java.util.Optional"%>
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
	Optional<Long> toiId = 	Optional.ofNullable(	(Long)request.getAttribute("toiId"));
	Toi parent = (Toi) request.getAttribute("parent");

	%>

	<%@ include file="common/headerAdmin.jsp"%><br>

	<H1>解答の一覧</H1>
		<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link" href="/admin/question/list?parentId=<%= parent.getId() %>">分野の変更</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/question/list2?parentId=<%= parent.getId() %>">問題の登録</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/question/list3?parentId=<%= parent.getId() %>">複製</a></li>
		<li class="nav-item"><a class="nav-link active"
			aria-current="page" >解答一覧</a></li>
	</ul>
	<%
		if (answerSumList == null || answerSumList.size() == 0) {
	%>
	解答されていませんされていません
	<a href="/admin/toi/delete?toiId=<%= toiId.get().toString() %>">Toiを削除</a>
	<%
		} else {
	%>

	<TABLE border="1">
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
			Toi toi = as.getOptToi().get();
			Ref<Member> member = as.getRefMember();
		%>
		<tr>
			<td><%=as.getId()%> 
			<% if(CloneToi.getByToiId(as.getToiId()).size() > 0){ %>
			( <a href="/admin/answerSumRestoreCheck?answerSumId=<%=as.getId()%>">複製元にマージ</a> )
			<% } else {%>
			( original )
			<% } %>
			
			</td>
			<td><%=as.getName()%></td>
			<td><%=as.getNoOfAnswer()%></td>
			<td><%=as.getNoOfSeikai()%></td>
			<td><%=sdf.format(as.getAnswered())%></td>
			<td>
				<%
					List<Answer> m = as.getAnswerList();
				%> <%
 	if (m != null) {
 %> <%
 	for (Answer rAns : m) {
 	if (rAns == null)
 		continue;
 %> <%=rAns.getId()%> <br /> <%
 	}
 %> <%
 	}
 %>
			</td>
			<td>
				<%
					Optional<Ref<Member>> refMem = Optional.ofNullable(as.getRefMember());
				boolean contain = false;
				if (refMem.isPresent() == true) {
					Optional<Member> mem = Optional.ofNullable(refMem.get().get());
					if (mem.isPresent()) {
						contain = mem.get().containsRefAnswerSum(as);
					}
				}
				%> <%
 	if ((member == null) || (contain == false)) {
 %> <a
				href='/admin/answerSum/reChain?answerSumId=<%=as.getId()%>&amp;memberId=<%=as.getName()%>&amp;redirectTo=<%=redirectTo%>'>
					reChain</a> <%
 	} else {
 %> <%=member.get().geteMail()%> <%
 	}
 %>
			</td>
			<%
				if ((toi != null) && (toi.getExam() != null)) {
			%>
			<td><%=toi.getExam().getName()%></td>
			<%
				} else {
			%>
			<td>null</td>
			<%
				}
			%>

			<%
				if ((toi != null)) {
			%>
			<td><%=toi.getNo()%></td>
			<%
				} else {
			%>
			<td>null</td>
			<%
				}
			%>
			<td>
				<%
					if ((toi != null)) {
				%> <%=toi.getName()%> <%
 	} else {
 %> null <%
 	}
 %> <%
 	if ((toi != null) && (toi.containsAnswerSum(as) == false)) {
 %> <a
				href='/admin/toi/addAnswerSum?toiId=<%=toi.getId()%>&amp;answerSumId=<%=as.getId()%>'>addAnswerSum</a>
				<%
					}
				%>
			</td>
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