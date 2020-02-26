<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");

%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${fn:replace(vo.contents,newLine,"<br>") }
							</div>
						</td>
					</tr>
						
				</table>
				
				
				<div class="bottom">
				<c:choose>
					<c:when test="${login==true }">
						<a href="${pageContext.request.contextPath }/board/reply?n=${vo.no}&p=${page}" id="new-book">답글</a>
					</c:when>
				</c:choose>
					<a href="${pageContext.request.contextPath }/board/list?p=${page}">글목록</a>
					<c:choose>
						<c:when test="${user==true }">
							<a href="${pageContext.request.contextPath }/board/modify?n=${vo.no}&p=${page}">글수정</a>
						</c:when>
					</c:choose>
					
				</div>
			</div>
		</div>
	
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
		</div>
</body>
</html>