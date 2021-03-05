<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${list5}" var="item" varStatus= "status">
	<p>${item.c_id}님->${item.c_comment}(${item.b_date})</p>
</c:forEach>


