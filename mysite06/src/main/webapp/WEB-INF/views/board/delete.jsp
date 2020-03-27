<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp">
			<c:param name="title" value="${siteVo.title }"></c:param>
		</c:import>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.request.contextPath }/board">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="no" value="${no}"  >
					
					<c:choose>
						<c:when test="${user == true }">
							<div style="padding-left:80px">
						<p >글을 삭제하시겠습니까?</p>
							<div style="padding-left:25px; margin:10px">
								<input type="submit" name="evt" value="확인">
								<input type="submit" name="evt" value="취소">
							</div>
					</div>
					
						</c:when>
						<c:otherwise>
							<p style="padding-left:80px">권한이 없습니다.</p>
						</c:otherwise>
					</c:choose>
					
					
				</form>
				<div style ="padding-left:90px">
					<a href="${pageContext.request.contextPath }/board?a=list&p=1">목록</a>
				</div>
			</div>
		</div>
			<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>