<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
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
		String parentId = (String) request.getAttribute("parentId");
		Toi parent = (Toi) request.getAttribute("parent");
		//		List<Question> list = (List<Question>) request.getAttribute("questionList");
		Exam exam = (Exam) request.getAttribute("exam");
		Map<Long, Question> qMap = (Map<Long, Question>) request.getAttribute("questionMap");
	%>
<%@ include file="common/header.jsp"%>

	<H1>登録されている設問の一覧</H1>


	<p>選択された試験：
	<P>
		<%=exam.getName()%>
		問<%=parent.getNo()%>(<%=parent.getName()%>) <a
			href="/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
	</p>

	<%
		if ((qMap == null) || (qMap.size() == 0)) {
	%>
	設問が登録されていません
	<%
		} else {
	%>
		<input type="hidden" name="qId" value="<%= parent.getId() %>" />
		
		
	<TABLE border=1 class="table table-striped table-hover">
		<thead class="thead-dark"><tr>
				<th>設問</th>
				<th>解答欄</th>
			</TR>
		</thead>

			<%
				//			for (Question q : list) {
					Set<Long> toiKeySet = qMap.keySet();
					for (Long l : toiKeySet) {
						Question q = qMap.get(l);
			%>
			<tr>
				<td><%=q.getName()%></td>
				<td>
					<%
						if (q.isMulti() == true) {
					%> <input type="checkbox"
					name="<%=q.getId()%>" value="-1" checked="checked" disabled="disabled"/> 解けない <%
 	} else {
 %>
					<input type="radio" name="<%=q.getId()%>" value="-1"
					checked="checked" disabled="disabled"/> 解けない <%
						}
					%> <%
 	for (int i = 0; i <= (int) q.getNoOfOption(); i++) {
 %>
					<%
						if (q.isMulti() == true) {
					%> <input type="checkbox"
					name="<%=q.getId()%>" value="<%=i%>" disabled="disabled"/> <%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%>
					<%
						} else {
					%> <input type="radio" name="<%=q.getId()%>"
					value="<%=i%>" disabled="disabled"/> <%="アイウエオカキクケコサシスセソタチツテト".charAt(i)%> <%
 	}
 %> <%
 	}
 %>
				</td>

			</tr>
			<%
				}
			%>
		</table>
		<input type="submit" value="送信するにはログインしてください"　disabled="disabled" />

	<%
		}
	%>


</body>
　<%@ include file="common/footer.jsp"%>

</html>