<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/memberSearchDetail.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>
	
	 <div id="content">
            <div id="declaration">
            
                <div>회원번호</div>
                <div><input type="text" value="${vo.no}"></div>
                <div>아이디</div>
                <div><input type="text" value="${vo.id}"></div>
                <div>닉네임</div>
                <div><input type="text" value="${vo.nick}"></div>
                <div>이메일</div>
                <div><input type="text" value="${vo.email}"></div>
                <div>주소</div>
                <div><input type="text" value="${vo.address}"></div>
                <div>회원종류</div>
                <div><input type="text" value="${vo.name}"></div>
                <div>가입일</div>
                <div><input type="text" value="${vo.enrollDate}"></div>
                <div>정보수정일</div>
                <div><input type="text" value="${vo.updateDate}"></div>
                <div>상태</div>
                <div><input type="text" value="${vo.status}"></div>
                <div>탈퇴여부</div>
                <div><input type="text" value="${vo.withdrawalYn}"></div>

                <button id="close" onclick="goHome();">닫기</button>
            </div>
    </div>  
        
</body>
</html>
<script>
    function goHome(){
        window.location.href = '${root}/admin/memberSearch?page=1';
    };
</script>