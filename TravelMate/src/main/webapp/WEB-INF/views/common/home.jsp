<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${root}/static/css/header3.css">
<meta charset="UTF-8">
<title>Travel_Mate</title>

<style>
  #search-area {
  display:flex;
  justify-content: space-evenly;
  margin-left: 20px;
}

#bn{
width: 900px;
height: 400px;
}

#search >div{
font-size: 2em;
justify-content: center;
  padding: 30px;
}

/* 여행지검색 버튼 */
#search  button{
border-radius: 30px;
width: 200px;
}

/* 여름 인기 휴양지 버튼 hover */
.btn {
 border-radius: 100px;
 background-color: rgb(13, 126, 231);
}
.btn:hover{
background-color: blueviolet;
}

#banner{
display: grid;
grid-template-columns: 0.5fr 6fr 0.5fr;
}

#img ,#img2 ,#img3  {
align-items: center;
width: 70%;
height: 250px;
margin-left: 240px;
}

#ranson{
 display: flex;
 justify-content: space-between;
 
}

/* body{
background-color: rgb(204, 231, 162);
} */

#wrap02{
width: 100%;
height: 500px;
background-color: rgb(204, 231, 162);
display: grid;
grid-template-columns:1.5fr 1fr ;
border: 3px solid black;
}

#wrap02 > div{
border: 3px solid black;
}

#pr > a {
text-decoration: none;
}

#pr > a img{
margin-left: 150px;
}

#pr > a > h2{
margin-top: 50px;
}

#br > a:not(:first-of-type){
text-decoration: none;
margin-left: 50px;      
}

#br > a > h2{
margin-top: 50px;
}
#br > a {
text-decoration: none;
}

.bi{
width: 100px;
height: 100px;
}

#btn01 {
  background-color: #73D38E;
  border: 0;
  padding: 0px 25px; 
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
}

#btn02 {
  background-color: #73D38E;
  border: 0;
  padding: 0px 25px; 
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
}

#btn03 {
  background-color: #73D38E;
  border: 0;
  padding: 0px 25px; 
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
}

#btn04 {
  background-color: #73D38E;
  border: 0;
  padding: 0px 25px; 
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
}

#img2 ,#img3{
display: none;
}

#ransonImg4 , #ransonImg5 , #ransonImg6 {  
display: none; 
width: 850px; 
height: 450px;
}

</style>
</head>
<body>

  	 <%@ include file="/WEB-INF/views/common/header.jsp" %> 

  
   <!-- 내용영역 -->
  <div id="content">
	<div id="write-area" >
	  <div id="banner">
		<!--왼쪽화살표 -->
		<button id="btn01">
		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-square-fill" viewBox="0 0 16 16">
		  <path d="M16 14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12zm-4.5-6.5H5.707l2.147-2.146a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708-.708L5.707 8.5H11.5a.5.5 0 0 0 0-1z"/>
		  </svg>
		</button>
	  
		  <a href="${root}/"><img id="img" src="${root}/static/img/banner.png" alt="이미지"></a>
		  <!-- <img id="img2" src="${root}/static/img/d오메기떡.jpg" alt="이미지">
		  <img id="img3" src="${root}/static/img/마음샌드.jpg" alt="이미지"> -->
	  
		<!-- 오른쪽화살표 -->
		<button id="btn02">
		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">
		   <path  d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"/>
		   </svg>
		</button>
	  
		</div>  
	  
		<hr>
	  
		<div id="search-area" >
      <a href="">
      <div>
        <img id="bn" src="${root}/static/img/광고배너1.png" alt="이미지">
        <img id="bn" src="${root}/static/img/광고배너2.png" alt="이미지"> 
        <button class="btn btn-primary" > 여름인기 여행지검색 click! </button>
      </div>
		  </a>
		</div>
	  
		  <a href=""><h1 align="center" style="background-color:rgb(139, 207, 139); margin: auto;">랜선으로 여행을 즐겨보세요</h1></a>
		 <div id="ranson">
		  
		  <!-- 왼쪽화살표 -->
		  <button id="btn03">
		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-square-fill" viewBox="0 0 16 16">
		  <path d="M16 14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12zm-4.5-6.5H5.707l2.147-2.146a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708-.708L5.707 8.5H11.5a.5.5 0 0 0 0-1z"/>
		  </svg>
		  </button>
		
		<a href="https://www.youtube.com/watch?v=dEnBXRkDBUE"><img style="width: 450; height: 450px;" src="${root}/static/img/ranson.png" alt="이미지" id="ransonImg1"></a>
		<a href="https://www.youtube.com/watch?v=2BPLbTIpOZI"><img src="${root}/static/img/ranson2.png" alt="이미지" id="ransonImg2"></a>
		<a href="https://www.youtube.com/watch?v=t9gVHZlb3vA"><img src="${root}/static/img/ranson3.png" alt="이미지" id="ransonImg3"></a> 
		
		  
		<a href="https://www.youtube.com/watch?v=gLatnL0R9Pg"><img id="ransonImg4" src="${root}/static/img/pari.png" alt="이미지"></a>  
		<a href="https://www.youtube.com/watch?v=gLatnL0R9Pg"><img id="ransonImg5" src="${root}/static/img/런던.png" alt="이미지"></a>  
		<a href="https://www.youtube.com/watch?v=lN7WZco3Avg"><img id="ransonImg6" src="${root}/static/img/충주.png" alt="이미지"></a>   
		
		<!-- 오른쪽화살표 -->
		<button id="btn04">
		  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">7
		  <path  d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"/>
		  </svg>
		</button>
	   
		</div>
	  
		<div id="wrap02">
	  
		<div id="pr">
		 <a href=""><h2 align="center">지인에게 특산품을 선물해보세요!</h2></a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/pr.png" alt="이미지"> </a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/pr2.png" alt="이미지"> </a>
		 <a href=""> <img width="350px" height="300px" src="${root}/static/img/prr.png" alt="이미지"> </a>
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
		// setInterval(f01 , 1000 * 3);

		function f01(){

			$.ajax({
			url : "${root}/home/board-top-hit" ,
			type : 'post' ,
			dataType : 'json' ,
			success : function(vo){
				console.log(vo);
				const div = document.querySelector('#br');
				// 여기서 반복문으로 리스트들 읽어와야함생각좀해야겠음
				let str = '';
				str += '<div>'
				str += '갱신 시각 : ' + new Date().toLocaleTimeString();
				str += '</div>';
				str += '<h1>';
				str += vo.title;
				str += '</h1>';
				str += '<div>';
				str += vo.content;
				str += vo.enroll_date;
				str += vo.hit;
				str += '</div>';

				div.innerHTML = str;
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