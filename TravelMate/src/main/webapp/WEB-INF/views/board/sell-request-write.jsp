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
</head>
<body>

	<%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	
    <div id="content">
        <form action="${root}/sell/request/write" method="post" enctype="multipart/form-data">
           <div id="declaration">
               <div>제목</div>
               <div><input type="text" name="title"></div>
               <div>내용</div>
               <div><input type="text" name="content"></div>
               <div>이미지</div>
               <div><input type="file" name="f"></div>  &nbsp;
                   <div id="img-area">
                                       		
                   </div>
               <input id="close" type="submit" value="등록하기">
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
        
        if(fileTag.files.length == 0){		
            previewArea.innerHTML = '';
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
