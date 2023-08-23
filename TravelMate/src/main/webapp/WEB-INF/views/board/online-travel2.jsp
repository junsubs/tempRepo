<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

 /* 내용영역 */
 #content{
    position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 350px;
	    bottom: 450px;
    }

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 125px;
        width: 40px;
        height: 60px;
    }

    #content>#first-content>hr:nth-child(2){
        height: 1px;
        width: 1300px;
        background-color: rgb(116, 116, 116);
        border: 0px solid rgb(65, 65, 65);
        position: absolute;
        left: 100px;
        top: 180px;
    }

    #content>#first-content>a:nth-child(3){
        position: absolute;
        left: 160px;
        top: 135px;
        color: black;
        font-size: 28px;
        font-weight: bold;
    }

	/* //////////////////// */
	#online-area{
		margin-top: 500px;
		display: grid;
		grid-template-columns: repeat(3 ,1fr);
		place-items: center center;
		gap: 100px;
	}

	#online-area a img{
		width: 600px;
		height: 300px;
	}
</style>
</head>
<body>


	<%@ include file="/WEB-INF/views/common/product-header.jsp" %>

	 <!-- 내용영역 -->
	 <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>랜선 여행</a>
		</div>
			<div id="online-area">
				
				<a href="https://www.youtube.com/watch?v=sZg_6RP5zU8"><img  src="${root}/static/img/철도여행.PNG" alt="철도여행" id="철도여행.PNG"></a>
				<a href="https://www.youtube.com/watch?v=fFQH-w5UHxQ"><img src="${root}/static/img/런던.PNG" alt="런던여행" id="런던.PNG"></a>
				<a href="https://www.youtube.com/watch?v=hIj9VovDYDQ"><img src="${root}/static/img/하와이.PNG" alt="하와이" id="하와이.PNG"></a> 
				
				
				<a href="https://www.youtube.com/watch?v=XTC7o7Il8Sk"><img id="ransonImg4" src="${root}/static/img/대전.PNG" alt="대전.PNG"></a>  
				<a href="https://www.youtube.com/watch?v=4jR-Hb3eYPg"><img id="ransonImg5" src="${root}/static/img/이탈리아.PNG" alt="이탈리아.PNG"></a>  
				<a href="https://www.youtube.com/watch?v=UsxZbJpTMw4"><img id="ransonImg6" src="${root}/static/img/일본.PNG" alt="일본.PNG"></a>   
				
			</div>

            <div id="page-area">
              <button id="btn01"><a href="${root}/online/travel">1</a></button>
              <button id="btn01"><a href="${root}/online/travel?page=2">2</a></button>
            </div>
			
			
</body>
</html>