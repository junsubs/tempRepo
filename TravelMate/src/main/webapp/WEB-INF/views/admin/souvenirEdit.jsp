<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/souvenirEdit.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

    <!-- 내용영역 -->
    <div id="content">

        <form action="${root}/admin/souvenirEdit" method="post">
            
            <div id="declaration">
                <div>기념품명</div>
                <div><input type="text" name="name" value="${vo.name}" readonly></div>
                <div>지역</div>
                <div><input type="text" name="localName" value="${vo.localName}" readonly></div>
                <div>가격</div>
                <div><input type="text" name="price" value="${vo.price}" readonly></div>
                <div>등록일</div>
                <div><input type="date" name="enrollDate" value="${vo.enrollDate}" readonly></div>
                <div>내용</div>
                <div><input type="text" name="content" value="${vo.content}" readonly></div>
                <div id="souvenirCount">재고</div>
                <div><input type="number" name="count" min="0" max="100" placeholder="${vo.count}"></div>
                <div>이미지</div>
                <div id="img-area">
                    <img src="${root}/static/img/souvenir/${vo.changeName}" alt="${vo.originName}">
                </div>
                <input type="hidden" name="no" value="${vo.no}">  
                <input type="submit" value="수정하기" onclick="imgDown(${vo.count})">
            </div>

        </form>

    </div>  

</body>
</html>
