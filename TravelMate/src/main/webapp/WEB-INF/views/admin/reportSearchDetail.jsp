<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/reportSearchDetail.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>
	
	 <div id="content">
            <div id="declaration">
            
                <div>제재내역번호</div>
                <div><input type="text" value="${vo.no}"></div>
                <div>신고내역번호</div>
                <div><input type="text" value="${vo.reportListNo}"></div>
                <div>제재사유</div>
                <div><input type="text" value="${vo.name}"></div>
                <div>회원아이디</div>
                <div><input type="text" value="${vo.memberId}"></div>
                <div>회원차단일</div>
                <div><input type="text" value="${vo.enrollDate}"></div>
                <div>차단종료일</div>
                <div><input type="text" value="${vo.cancelEnrollDate}"></div>
                <div>제재횟수</div>
                <div><input type="text" value="${vo.count}"></div>

                <button id="close" onclick="goHome();">닫기</button>
            </div>
    </div>  
        
</body>
</html>
<script>
    function goHome(){
        window.location.href = '${root}/admin/reportSearch?page=1';
    };
</script>