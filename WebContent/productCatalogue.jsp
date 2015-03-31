<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
a{text-decoration:none;}
</style>
<title></title>
</head>
<body>
<h1>COMP5347 Assignment 1</h1>
<h2>Overall Product List Page</h2>
<hr/>
<h2>Catalogue</h2>
<table border="1">
  <c:forEach var="pro" items="${products}" varStatus="productCount">
  	<c:if test="${productCount.count%2!=0}">
  		<tr>
  	</c:if>
		<td>
		<p><a href="ProductDetail.do?proid=${productCount.count-1}">${pro.name}</a></p>
		<p><a href="ProductDetail.do?proid=${productCount.count-1}"><img src="resources/${pro.imgFile}" width="383" height="500"/></a></p>
		<p>Price: $${pro.price}</p>
		<c:if test="${currentUser.productRating[productCount.count-1]!=0}">
			<p>Your Rating: ${currentUser.productRating[productCount.count-1]+1}</p>
		</c:if>
		<p>Average Rating: ${pro.averageRating}</p>
		<p>Total number of Ratings: ${pro.totalRating}</p>
		<input type="text" id="proidrrr" value="${productCount.count-1}" hidden/>
		</td>
	<c:if test="${productCount.count%2==0 || productCount.count==total}">
  		</tr>
  	</c:if>
</c:forEach>
</table>
</body>
</html>