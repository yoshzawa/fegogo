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
		Toi toi = (Toi) request.getAttribute("parent");
		Exam exam = (Exam) request.getAttribute("exam");
//		Map<Long, Question> qMap = (Map<Long, Question>) request.getAttribute("questionMap");
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");

	%>


<%@ include file="common/headerLogin.jsp"%>

	<H1>登録されている設問の一覧</H1>
	<p>選択された試験：
	<P>
		<%=exam.getName()%>
		問<%=toi.getNo()%>(<%=toi.getName()%>) <a
			href="/toi/list?parentId=<%=exam.getId()%>">(選択解除する)</a>
	</p>

	<%
		if ((datas == null) || (datas.size() == 0)) {
	%>
	設問が登録されていません
	<%
		} else {
	%>
	<form method="post" action="/answer">
		<input type="hidden" name="userId" value="<%= email %>" />
		<input type="hidden" name="toiId" value="<%= toi.getId() %>" />
	
			<TABLE border=1 class="table table-striped table-hover table-responsive">
			<thead class="thead-dark">
				<TR>
					<th>設問</th>
					<th>解答欄</th>
				</TR>
			</thead>

			<%
				for(String[] s : datas){
			%>
			<tr>
				<td><%=s[0]%></td>
				<td><%=s[1]%></td>
			
			</tr>
			<%
				}
			%>
		</table>
		
		<input type="submit" value="送信する" />
	</form>

	<%
		}
	%>


  </body>
　<%@ include file="common/footer.jsp"%>
</html>