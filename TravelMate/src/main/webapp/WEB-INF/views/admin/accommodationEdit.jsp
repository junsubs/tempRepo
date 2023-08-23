<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/accommodationEdit.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

    <!-- 내용영역 -->
    <div id="content">

        <form action="${root}/admin/accommodationEdit" method="POST">
            <div id="declaration">
            
                <div>숙소명 </div>
                <div><input type="text" name="name" value="${vo.name}" readonly></div>
                <div>지역</div>
                <div><input type="text" name="local" value="${vo.localName}" readonly></div>
                <div>숙소종류</div>
                <div><input type="text" name="kind" value="${vo.kind}" readonly></div>
                <div>최대숙박인원</div>
                <div><input type="number" value="${vo.maxPeople}" readonly></div>
                <div>가격</div>
                <div><input type="text" name="price" value="${vo.price}" readonly></div>
                <div id="count">재고</div>
                <select name="count">
                    <option value="Y">빈방있음</option>
                    <option value="N">빈방없음</option>
                </select>
                <div>이미지</div>
                <div id="img-area">
                    <img src="${root}/static/img/accommodationImg/${vo.changeName}" alt="${vo.originName}">
                </div>

                <input type="hidden" name="no" value="${vo.no}">
                <input type="submit" value="등록하기">
            </div>
        </form>

    </div>  

</body>
</html>