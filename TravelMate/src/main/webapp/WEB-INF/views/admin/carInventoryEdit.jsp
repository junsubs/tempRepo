<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/carInventoryEdit.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

    <!-- 내용영역 -->
    <div id="content">

        <form action="${root}/admin/carinventoryEdit" method="post">
            
            <div id="declaration">
                <div>차종 </div>
                <div><input type="text" name="carKind" value="${vo.kind}" readonly></div>
                <div>지역</div>
                <div><input type="text" name="local" value="${vo.name}" readonly></div>
                <div>차량번호판</div>
                <div><input type="text" name="licensePlate" value="${vo.licensePlate}" readonly></div>
                <div>차량출고일</div>
                <div><input type="date" name="outDate" value="${vo.licenseDate}" readonly></div>
                <div>최대탑승인원</div>
                <div><input type="number" name="maxPeople" min="1" value="${vo.max}" readonly></div>
                <div>가격</div>
                <div><input type="text" name="price" value="${vo.price}" readonly></div>
                <div id="carCount">재고</div>
                <select name="count" id="">
                    <option value="1">재고있음</option>
                    <option value="0">재고없음</option>
                </select>
                <div>이미지</div>
                <div id="img-area">
                    <img src="${root}/static/img/carImg/${vo.changeName}" alt="${vo.originName}">
                </div>
                <input type="hidden" name="carNo" value="${vo.no}">  
                <input type="submit" value="수정하기" onclick="imgDown(${vo.count})">
            </div>

        </form>

    </div>  

</body>
</html>
