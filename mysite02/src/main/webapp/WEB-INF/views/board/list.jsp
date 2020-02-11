<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	pageContext.setAttribute("newLine", "\n");

%> 
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="listCount" value="${fn:length(list) }"></c:set>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${listCount-status.index }</td>
							  <c:choose>
								<c:when test="${vo.depth==0 }">
								<td style="text-align:left;">
								<a href="${pageContext.servletContext.contextPath }/board?a=view&n=${vo.no}">${vo.title }</a>
								</td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${vo.depth*20}px"><img src='/mysite02/assets/images/reply.png'>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&n=${vo.no}">${vo.title }</a>
									</td>
								</c:otherwise>
								
							</c:choose> 
							
							<td>${vo.userNo }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&n=${vo.no}" class="del">삭제</a></td>
						</tr>
					
					</c:forEach>
				
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>s
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
				 <c:choose>
				 	<c:when test="${login==true }">
				 		<a href="${pageContext.request.contextPath }/board?a=write" id="new-book">글쓰기</a>
				 	</c:when>
				 </c:choose>
					
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>