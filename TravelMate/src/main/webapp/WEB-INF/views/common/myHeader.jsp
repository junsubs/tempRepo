<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/header3.css">
<script defer src="${root}/static/js/header3.js"></script>
<style>
 

</style>
</head>
<body>
<!-- <%@ include file="/WEB-INF/views/common/header3.jsp" %>  -->


  
		<!-- <button id="btn04">
		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">7
		  <path  d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"/>
		  </svg>
		</button>
	   
		</div>
	  
		<div id="wrap02">
	  
		<div id="pr">
		 <a href=""><h2 align="center">지인에게 특산품을 선물해보세요!</h2></a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/배너1.png" alt="이미지"> </a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/배너2.png" alt="이미지"> </a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/배너3.png" alt="이미지"> </a>
		</div> 
		 
		<div id="br">
		
      		로딩중
      
    	</div>
</div>
</div>
</body>
</html>
<script>

  f01();
		setInterval(f01 , 1000 * 3);

		function f01(){

			$.ajax({
			url : "${root}/home/board-top-hit" ,
			type : 'post' ,
			dataType : 'json' ,
			success : function(data){
        console.log(data);
				// console.log(vo);
				// const div = document.querySelector('#br');
				// // 여기서 반복문으로 리스트들 읽어와야함생각좀해야겠음
				// let str = '';
				// str += '<div>'
				// str += '갱신 시각 : ' + new Date().toLocaleTimeString();
				// str += '</div>';
				// str += '<h1>';
				// str += vo.title;
				// str += '</h1>';
				// str += '<div>';
				// str += vo.content;
				// str += vo.enroll_date;
				// str += vo.hit;
				// str += '</div>';

				// div.innerHTML = str;
			} ,
			error : function(er){
				console.log(er);
			} ,
		});

		}



    const btn01 = document.querySelector('#btn01');
    const btn02 = document.querySelector('#btn02');
    const btn03 = document.querySelector('#btn03');
    const btn04 = document.querySelector('#btn04');
    const img = document.querySelector('#img');
    const img2 = document.querySelector('#img2');
    const img3 = document.querySelector('#img3');

    const ransonImg1  = document.querySelector('#ransonImg1');
    const ransonImg2 = document.querySelector('#ransonImg2');
    const ransonImg3 = document.querySelector('#ransonImg3');

    const ransonImg4 = document.querySelector('#ransonImg4');
    const ransonImg5 = document.querySelector('#ransonImg5');
    const ransonImg6 = document.querySelector('#ransonImg6');
  
    let currentImage = 1;
    let currentRansonImage = 1;
  
    btn01.addEventListener('click', function() {
      if (currentImage === 1) {
        img.style.display = 'none';
        img2.style.display = 'block';
        currentImage = 2;
      } else if (currentImage === 2) {
        img2.style.display = 'none';
        img3.style.display = 'block';
        currentImage = 3;
      } else if (currentImage === 3) {
        img3.style.display = 'none';
        img.style.display = 'block';
        currentImage = 1;
      }
    });

  
  
    btn02.addEventListener('click', function() {
      if (currentImage === 1) {
        img.style.display = 'none';
        img2.style.display = 'block';
        currentImage = 2;
      } else if (currentImage === 2) {
        img2.style.display = 'none';
        img3.style.display = 'block';
        currentImage = 3;
      } else if (currentImage === 3) {
        img3.style.display = 'none';
        img.style.display = 'block';
        currentImage = 1;
      }
    });

    btn03.addEventListener('click', function(){
      if (currentRansonImage === 1) {
        ransonImg1.style.display = 'none';
        ransonImg4.style.display = 'block';
        ransonImg2.style.display = 'none';
        ransonImg5.style.display = 'block';
        ransonImg3.style.display = 'none';
        ransonImg6.style.display = 'block';
        currentRansonImage = 2;
      }else if(currentRansonImage ===2){
        ransonImg1.style.display = 'block';
        ransonImg4.style.display = 'none';
        ransonImg2.style.display = 'block';
        ransonImg5.style.display = 'none';
        ransonImg3.style.display = 'block';
        ransonImg6.style.display = 'none';
        currentRansonImage =1;
      }
      });


      btn04.addEventListener('click', function(){
      if (currentRansonImage === 1) {
        ransonImg1.style.display = 'none';
        ransonImg4.style.display = 'block';
        ransonImg2.style.display = 'none';
        ransonImg5.style.display = 'block';
        ransonImg3.style.display = 'none';
        ransonImg6.style.display = 'block';
        currentRansonImage = 2;
      }else if(currentRansonImage ===2){
        ransonImg1.style.display = 'block';
        ransonImg4.style.display = 'none';
        ransonImg2.style.display = 'block';
        ransonImg5.style.display = 'none';
        ransonImg3.style.display = 'block';
        ransonImg6.style.display = 'none';
        currentRansonImage =1;
      }
      });

  </script>

</body>
</html>