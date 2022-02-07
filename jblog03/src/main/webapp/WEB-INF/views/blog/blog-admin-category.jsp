<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/bheader.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/navigation.jsp" />
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${map.clist}" var="vo" varStatus="status">
                     <tr>
                        <td>${map.clist.size()-status.index}</td>
                        <td>${vo.name}</td>
                        <td>${map.noList.get(status.index)}</td>
                        <td>${vo.description}</td>
                        <td>
                           <c:if test = "${map.noList.get(status.index)==0}">
                              <a href="${pageContext.servletContext.contextPath}/blog/${authUser.id}/delete/${vo.no}">
                              <img src="${pageContext.servletContext.contextPath}/assets/images/delete.jpg"></a>
                           </c:if>
                        </td>
                     </tr>
                  </c:forEach>
				</table>
				<form action="${pageContext.request.contextPath }/${authUser.id}/admin/category/insert" method="post">
					<h4 class="n-c">새로운 카테고리 추가</h4>
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description">
							<input type= "hidden" name = "blogId" value = "${authUser.id}"></td>
						</tr>
						
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>