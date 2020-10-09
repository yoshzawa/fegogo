<%@page import="jp.ac.jc21.t.yoshizawa.objectify.ImageSet"%>
<%@page import="java.util.Map"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Exam"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Question"%>
<%@page import="java.util.List"%>
<%@page import="jp.ac.jc21.t.yoshizawa.objectify.Toi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
  	<%
		String parentId = (String) request.getAttribute("parentId");
		Toi toi = (Toi) request.getAttribute("parent");
//		List<String[]> datas = (List<String[]>) request.getAttribute("datas");
		Map<Long,String[]> datas=(Map<Long,String[]>)request.getAttribute("datas");
		List<ImageSet> imageSetList = (List<ImageSet>)
		request.getAttribute("imageSetList");

	%>

<html>
<head>
<meta charset="UTF-8">
<title><%= toi.getExam().getYYYYMM()%> 問<%=toi.getNo()%> <%=toi.getName()%></title>
</head>
<body>
 	<div class="container">
			<div class="container-sm">
  
  
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="/">ホーム</a></li>
			<li class="breadcrumb-item"><a href="/exam/">試験 一覧</a></li>
			<li class="breadcrumb-item"><a href="/toi/list?parentId=<%=toi.getExamId()%>"><%=toi.getExamName()%> 試験</a></li>
			<li class="breadcrumb-item active" aria-current="page">問<%=toi.getNo()%> <%= toi.getGenreName() %>
			(<%=toi.getName()%>)</li>
		</ol>
	</nav>

<%@ include file="../common/headerLogin.jsp"%>

	    <main class="mb-5">

	<%
		if ((datas == null) || (datas.size() == 0)) {
	%>
	設問が登録されていません
	<%
		} else {
	%>
	<h2><%=toi.getExamName()%> 試験 <%= toi.getGenreName() %></h2>
	<h3>問<%=toi.getNo()%> <%=toi.getName()%></h3>
	<form method="post" action="/answer">
		<input type="hidden" name="userId" value="<%= email %>" />
		<input type="hidden" name="toiId" value="<%= toi.getId() %>" />
		<%
			for(ImageSet is : imageSetList){
				if(is.isImage()==true){
%>
							<p
								style="position:relative;
									background-image: url(<%=is.getUrl()%>);
									height: <%=is.getHeight()%>px;
									width:700px;
									background-position: 0  <%=is.getTop()%>px;
									background-size: 700px auto ;
									overflow: hidden;">
							</p>		
<% 					
				}else{
		%>
					<TABLE border=1 class="table table-striped table-hover table-responsive">
			<thead class="thead-dark">
				<TR>
					<th>設問</th>
					<th>解答欄</th>
				</TR>
			</thead>

			<%
				Long [] ids = is.getQuestionIds();
				for(Long id : ids){
//					System.out.println(id);
					String[] s = datas.get(id);
				
			%>
			<tr>
				<td><%=s[0]%></td>
				<td><%=s[1]%></td>
			
			</tr>
			<%
				}
			%>
		</table>
		<% 
				}
		%>
		<% 
		}
		%>
	

		
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