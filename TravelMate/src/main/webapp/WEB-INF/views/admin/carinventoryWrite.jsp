<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/carinventoryWrite.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

    <!-- 내용영역 -->
    <div id="content">

        <form action="${root}/admin/carinventoryWrite" method="POST" enctype="multipart/form-data">
            <div id="declaration">
    
                <div>차종 </div>
                <div>
                    <select name="carKind" id="carKind">
                        <option value="1">레이</option>
                        <option value="2">마티즈</option>
                        <option value="3">캐스퍼</option>
                        <option value="4">그랜져</option>
                        <option value="5">아반떼</option>
                        <option value="6">소렌토</option>
                        <option value="7">말리부</option>
                        <option value="8">산타페</option>
                        <option value="9">K7</option>
                        <option value="10">제네시스G90</option>
                    </select>
                </div>
                <div>지역</div>
                <div>
                    <select name="local" id="local">
                        <option value="1">강원도</option>
                        <option value="2">제주도</option>
                        <option value="3">전남</option>
                        <option value="4">전북</option>
                        <option value="5">경남</option>
                        <option value="6">경북</option>
                        <option value="7">수도권</option>
                        <option value="8">대전</option>
                        <option value="9">대구</option>
                        <option value="10">부산</option>
                    </select>
                </div>
                <div>차량번호판</div>
                <div><input type="text" name="licensePlate"></div>
                <div>차량출고일</div>
                <div><input type="date" name="outDate"></div>
                <div>최대탑승인원</div>
                <div><input type="number" name="maxPeople" min="1"></div>
                <div>가격</div>
                <div><input type="text" name="price"></div>
                <div>이미지</div>
                <div><input type="file" name="f"></div>
                <input type="submit" value="등록하기">
            </div>
        </form>

    </div>  

</body>
</html>