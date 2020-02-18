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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="hidden" name="a" value="list">
					<input type="hidden" name="p" value="${p }">
					<input type="hidden" name="searchflag" value="true">
					<select name ="option">
						<option value="title">제목</option>
						<option value="contents">내용</option>
						<!--<option value="user">글쓴이</option>-->
					</select>
					<input type="text" id="kwd" name="kwd" value="">
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
					<c:set var="listCount" value="${total }"></c:set>
					
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td>${(listCount-((p-1)*5))-status.index }</td>
							  <c:choose>
								<c:when test="${vo.depth==0 }">
								<td style="text-align:left;">
								<a href="${pageContext.servletContext.contextPath }/board?a=view&n=${vo.no}&p=${p}">${vo.title }</a>
								</td>
								</c:when>
								<c:otherwise>
									<td style="text-align:left; padding-left:${vo.depth*20}px"><img src='/mysite02/assets/images/reply.png'>
									<a href="${pageContext.servletContext.contextPath }/board?a=view&n=${vo.no}&p=${p}">${vo.title }</a>
									</td>
								</c:otherwise>
								
							</c:choose> 
							
							<td>${vo.userNo }</td>
							<td>${vo.hit }</td>
							<td>${vo.reg_date }</td>
							
								<td><a href="${pageContext.servletContext.contextPath }/board?a=deleteform&n=${vo.no}" class="del">삭제</a></td>
							
						</tr>
					
					</c:forEach>
				
				</table>
				
				<!-- pager 추가 -->
				
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${total%5==0 }">
								
								<fmt:parseNumber var="next" integerOnly="true" value="${total/5 }"/>
							</c:when>
							<c:otherwise>
								<fmt:parseNumber var="next" integerOnly="true" value="${total/5+1 }"/>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${next-(start*5)<5 }">
								<c:set var="end" value="${next-(start*5) }"/>
							</c:when>
							<c:otherwise>
								<c:set var="end" value="5"/>
							</c:otherwise>
						</c:choose>
						
					
					
						<c:choose>
							<c:when test="${p<=5 }">
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=1&kwd=${kwd}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${((start-1)*5)+1}&kwd=${kwd}">◀</a></li>
							</c:otherwise>
						</c:choose>
						
						
								<c:forEach var="i" begin="1" end="${end }">
									<c:choose>
										<c:when test="${p == ((start*5)+i) }">
											<li class="selected">
												<a href="${pageContext.servletContext.contextPath }/board?a=list&p=${(start*5)+i}&kwd=${kwd}">${(start*5)+i }</a>
											</li>
										
										</c:when>
											<c:otherwise>
											<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${(start*5)+i}&kwd=${kwd}">${(start*5)+i }</a></li>
										</c:otherwise>	
									
									</c:choose>
								
								
								</c:forEach>
							
							<c:choose>
								<c:when test="${end+5>=next }">
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${next}&kwd=${kwd}">▶</a></li>	
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?a=list&p=${((start+1)*5)+1}&kwd=${kwd}">▶</a></li>	
								</c:otherwise>
							</c:choose>
							
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