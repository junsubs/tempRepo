<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/sellrequestDetail.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/header.jsp" %>
	
	 <div id="content">
         <form action="${root}/admin/sellrequestdetail" method="post">
            <div id="declaration">
                <div>게시글번호</div>
                <div><input id="bno" type="text" value="${vo.no}" name="no"></div>
                <div>작성자</div>
                <div><input type="text" value="${vo.writer}" name="writer"></div>
                <div>제목</div>
                <div><input type="text" value="${vo.title}" name="title"></div>
                <div>내용</div>
                <div><input type="text" value="${vo.content}" name="content"></div>
                <div>작성일</div>
                <div><input type="text" value="${vo.enrollDate}" name="enrollDate"></div>
                <div>이미지</div>
                <div id="img-area">
                    <img src="${root}/static/img/sell/${vo.changeName}" alt="배너이미지">
                </div>
                
                <input id="close" type="submit" value="등록하기">
            </div>
        </form>
    </div>  
    <button id="sellNo" onclick="sellNo()">등록거절</button>
        
</body>
</html>
<script>

    //등록거절
    const no = document.querySelector("#bno").value;
    function sellNo(){
        const result = confirm("등록요청을 거절하시겠습니까?");
        
        if(result){
            $.ajax({
            url : '${root}/admin/sellrequestRufuse',
            type : 'post',
            data : {
                no : no
            },
            success : function(data){
                alert("판매요청이 거절되었습니다. 조회화면으로 돌아갑니다.");
                location.href = '${root}/admin/sellrequest'
            },
            error : function(err){
                console.log(err);
            }
        });
        }

      
    }

</script>