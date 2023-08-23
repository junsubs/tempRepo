<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/souvenirinventoryWrite.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

    <!-- 내용영역 -->
    <div id="content">

        <form action="${root}/admin/souvenirinventoryWrite" method="POST" enctype="multipart/form-data">
            <div id="declaration">
            
                <div>기념품명</div>
                <div><input type="text" name="name"></div>
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
                <div>내용</div>
                <div><input type="text" name="content"></div>
                <div>가격</div>
                <div><input type="text" name="price"></div>
                <div>재고</div>
                <div><input type="number" name="count" min="0" max="100"></div>
                <div>이미지</div>
                <div><input type="file" name="f"></div>
                <div id="img-area">
                 
                </div>

                <input type="submit" value="등록하기">
            </div>
        </form>

    </div>  

</body>
</html>
<script>
    //미리보기
    const fileTag = document.querySelector("input[type=file]");
    const imgArea = document.querySelector("#img-area");
    

    fileTag.onchange = function(e){
        
        if(fileTag.files.length == 0){		//취소누른상태
            imgArea.innerHTML = '';
            return;
        }

        for(let i = 0 ; i < fileTag.files.length; i++){
            const fr = new FileReader();
            fr.readAsDataURL(fileTag.files[i]);

            fr.onload = function(e){
                const imgTag = document.createElement('img');
                imgTag.src = e.target.result;
                imgTag.alt = "미리보기이미지사진";
                imgTag.width = 100;
                imgTag.height = 100;
                imgArea.appendChild(imgTag);
            };
        }
    };
</script>