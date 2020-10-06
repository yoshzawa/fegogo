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
  <div class="container">
  
  
  	<%
		String parentId = (String) request.getAttribute("parentId");
		Toi toi = (Toi) request.getAttribute("parent");
		List<String[]> datas = (List<String[]>) request.getAttribute("datas");

	%>
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
	<H1>登録されている設問の一覧</H1>
	設問が登録されていません
	<%
		} else {
	%>
	<form method="post" action="/answer">
		<input type="hidden" name="userId" value="<%= email %>" />
		<input type="hidden" name="toiId" value="<%= toi.getId() %>" />
	
		<picture>
	<source type="image/webp"
		srcset="/image/2019/04/2019h31h_fe_pm_qs_03_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/2019h31h_fe_pm_qs_04_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_39_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_40_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_41_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_42_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_43_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_44_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_45_web.webp" />
	<img src="/image/unsupported.png" /> </picture>
	<picture>
	<source type="image/webp"
		srcset="/image/2019/04/08/2019h31h_fe_pm_qs_46_web.webp" />
	<img src="/image/unsupported.png" /> </picture>

	
		<H1>登録されている設問の一覧</H1>
	
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

<p></p>

</main>
</div>
　<%@ include file="../common/footer.jsp"%>
  </body>
</html>