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
				<form id="search_form" action="${pageContext.request.contextPath }/board/list?p=${page}" method="post">
					<select name ="option">
						<option value="title">제목</option>
						<option value="contents">내용</option>
						<!--<option value="user">글쓴이</option>-->
					</select>
					<input type="text" id="kwd" name="kwd" value="${map.kwd }">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일<p>
					
					</p></th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="listCount" value="${map.total }"></c:set>
					
					<c:forEach items="${map.list }" var="vo" varStatus="status">
						<tr>
							<td>${(listCount-((map.page-1)*5))-status.index }</td>
							  <c:choose>
								<c:when test="${vo.depth==0 }">
								<td style="text-align:left;">
								<a href="${pageContext.servletContext.contextPath }/board/view?n=${vo.no}&p=${map.page}">${vo.title }</a>
								</td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${vo.depth*20}px"><img src='/mysite02/assets/images/reply.png'>
									<a href="${pageContext.servletContext.contextPath }/board/view?n=${vo.no}&p=${map.page}">[답글]${vo.title }</a>
									</td>
								</c:otherwise>
								
							</c:choose> 
							
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							
								<td><a href="${pageContext.servletContext.contextPath }/board/deleteform?n=${vo.no}" class="del">삭제</a></td>
							
						</tr>
					
					</c:forEach>
				
				</table>
				
				<!-- pager 추가 -->
				
				<div class="pager">
					<ul>
						
						<li><a href="${pageContext.servletContext.contextPath }/board/list?p=${map.prevPage}&kwd=${map.kwd}">◀</a></li>
								
							<c:forEach var="i" begin="1" end="${map.listsize }">
								<c:choose>
									<c:when test="${map.page == (map.beginPage+(i-1)) }">
										<li class ="selected">
											<a href="${pageContext.servletContext.contextPath }/board/list?p=${map.beginPage+(i-1) }&kwd=${map.kwd}">${map.beginPage+(i-1) }</a>
										</li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.servletContext.contextPath }/board/list?p=${map.beginPage+(i-1) }&kwd=${map.kwd}">${map.beginPage+(i-1) }</a></li>
									</c:otherwise>
								
								</c:choose>
							</c:forEach>
							
						<li><a href="${pageContext.servletContext.contextPath }/board/list?p=${map.nextPage }&kwd=${map.kwd}">▶</a></li>	
							
							
					</ul>
				</div>
							
				<!-- pager 추가 -->
				
				<div class="bottom">
				 <c:choose>
				 	<c:when test="${login==true }">
				 		<a href="${pageContext.request.contextPath }/board/write" id="new-book">글쓰기</a>
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