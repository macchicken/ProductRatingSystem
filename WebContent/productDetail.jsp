<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<h1>${product.name}</h1><br/>
<table border="1">
<tr>
  <td>
  <p><img src="resources/${product.imgFile}" width="383" height="500"/></p>
  <c:choose>
  <c:when test="${yourRating==0}">
  <form action="ProductDetail.do" method="post">
  	Rating:
  	<select name="selectedrate">
	  <c:forEach var="i" begin="1" end="5" step="1">
		<option value ="${i}">${i}</option>
		</c:forEach>
	</select>
	<input type="text" id="proid" name="proid" value="${productId}" hidden/>
	<input type="submit" value="submit">
  </form>
  </c:when>
  <c:otherwise>  
    <h4>Your Rating: ${yourRating}</h4>
  </c:otherwise>
  </c:choose>
  <h4>Average Rating: ${product.averageRating}</h4>
	<ul>
	  <c:forEach var="rate" items="${product.ratings}" varStatus="rCount">
	  	<li>rate ${rCount.count}: ${rate}</li>
	  </c:forEach>
	</ul>
  </td>
  <td>
  	<p><Strong>Authors:</Strong> ${product.authors}</p>
  	<p><Strong>Price:</Strong> ${product.price}</p>
  	<p><Strong>Paperback:</Strong> ${product.paperBack} pages</p>
  	<p><Strong>Publisher:</Strong> ${publisherInfo}</p>
  	<p><Strong>Language:</Strong> ${product.language}</p>
  	<p><Strong>ISBN-10:</Strong> ${product.isbn10}</p>
  	<p><Strong>ISBN-13:</Strong> ${product.isbn13}</p>
  	<p><a href="ProductCatalogue.do">back</a></p>
  </td>
</tr>
</table>
</body>
</html>