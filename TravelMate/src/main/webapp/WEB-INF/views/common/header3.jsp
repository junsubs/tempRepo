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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<c:if test="${not empty alertMsg}">
	<script>
		alert("${alertMsg}");
	</script>
</c:if>
<c:remove var="alertMsg" scope="session"/>
  <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<style>
	a{
		text-decoration: none;
	}
	
	#logoCategory a, .sidebar-category a{
		color: black;
	}
	
	nav>div:nth-child(2)>a, nav>div:nth-child(6)>a, nav>div:nth-child(13)>a,
	nav>div:nth-child(15)>a{
	    font-size: 22px;
	    font-weight: bold;
	    color: #08354D;
	    margin-left: 40px;
	}

	#ont{
		text-decoration: none;
		color: black;
		font-size: 27px;
	    font-weight: bold;
	    color: black;
	    
		margin-bottom: 0px;
		display: flex;
		height: 110px;
		justify-content: center;
	}
	
	#hr01, #hr03{margin-left: 20px;}

	#img2 ,#img3{
display: none;
}

#ransonImg4 , #ransonImg5 , #ransonImg6 {  
display: none; 
}

#btn03{
	margin-left: 200px;
}

</style>
</head>
<body bottommargin="0">
   
        <!-- 헤더영역 -->
       	<div id="realLogo">
            <a href="${root}/home">
                <img id="mainLogo" src="${root}/static/img/logo.png" alt="홈로고">
            </a>
        </div>
            
        <div id="logoCategory">
            <c:if test="${empty loginMember}">
            <a href="${root}/join">회원가입</a>
            <a>|</a>
             <a href="${root}/login">로그인</a>
            <a>|</a>
        	</c:if>
        	
        	<c:if test="${not empty loginMember}">
        	<a href="${root}/logout">로그아웃</a>
            <a>|</a>
            <a href="${root}/mypage/member/detail">마이페이지</a>
            <a>|</a>
        	</c:if>
			<a href="${root}/cs/faq">고객센터</a>
        </div>
        <nav class="menu align-center expanded text-center SMN_effect-45 menu align-center expanded text-center SMN_effect-23">
			<div></div>
            <div><a data-hover="예약">예약</a></div>
            <div><a href="${root}/room/list">숙소</a></div>
            <div><a href="${root}/car/list">차량</a></div>
            
            <div><a>|</a></div>
            <div><a data-hover="커뮤니티">커뮤니티</a></div>
            <div><a href="${root}/free/list">자유</a></div>
            <div><a href="${root}/review/list">리뷰</a></div>
            <div><a href="${root}/notice/list">공지</a></div>
            <div></div>
            <c:if test="${loginMember.memberCategoryNo == '3' || loginMember.id eq 'ADMIN' }">
            <div><a href="${root}/sell/request/write">판매요청</a></div>
            <div><a>|</a></div>
            </c:if>
            <div><a href="${root}/online/travel" data-hover="랜선여행">랜선여행</a></div>
            <div><a>|</a></div>
            <div><a href="${root}/souvenir/list" data-hover="기념품">기념품</a></div>
            <div><a>|</a></div>
            <div><a href="" data-hover="전체메뉴">전체메뉴</a></div>
            <div></div> 
        </nav>
         <!-- 왼쪽사이드바 -->
        <div id="side-bar">
            <img src="${root}/static/img/삼각형.png" alt="삼각형">
        </div>

		 <div id="adver" class="carousel slide" data-bs-ride="true">
              <div class="carousel-indicators">
                <button id="btn01" type="button" data-bs-target="#adver" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button id="btn02" type="button" data-bs-target="#adver" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button id="btn03" type="button" data-bs-target="#adver" data-bs-slide-to="2" aria-label="Slide 3"></button>
              </div>
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img src="${root}/static/img/자체광고01.png" class="d-block w-100" alt="광고배너1">
                </div>
                <div class="carousel-item">
                  <img src="${root}/static/img/자체광고02.png" class="d-block w-100" alt="광고배너2">
                </div>
                <div class="carousel-item">
                  <img src="${root}/static/img/자체광고03.png" class="d-block w-100" alt="광고배너3">
                </div>
              </div>
              <button class="carousel-control-prev" type="button" data-bs-target="#adver" data-bs-slide="prev">
                <span class="carousel-control-prev-icon1" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button class="carousel-control-next" type="button" data-bs-target="#adver" data-bs-slide="next">
                <span class="carousel-control-next-icon1" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
              </button>
            </div>
            
            <!-- 오른쪽 사이드바 -->
	        <div id="right-sidebar">
	            <ul>
	              <li id="cart">
	                <div>
	                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-cart-check-fill" viewBox="0 0 16 16">
	                    <path fill-rule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm-1.646-7.646-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708.708z"/>
	                    </svg>
	                </div>
	              </li>
	              <li id="uppage">
	                <div> 
	                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chevron-up" viewBox="0 0 16 16">
	                    <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/>
	                    </svg>
	                </div>
	              </li>
	              <li id="right-sidebar-category">
	                <div>
	                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
	                    <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
	                    </svg>
	                </div>
	              </li>
	              <li id="downpage">
	                <div>
	                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chevron-down" viewBox="0 0 16 16">
	                    <path fill-rule="evenodd" d="M1.646 4.646a.5.5 0 0 1 .708 0L8 10.293l5.646-5.647a.5.5 0 0 1 .708.708l-6 6a.5.5 0 0 1-.708 0l-6-6a.5.5 0 0 1 0-.708z"/>
	                    </svg>
	                </div>
	              </li>
	            </ul>
	        </div>
	        <div class="sidebar-category">
	            <div id="side01">
	                <button id="side-close" class="active2">
	                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
	                    <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
	                    </svg>
	                </button>
	            </div>
	            <div id="side02"><button id="insta" class="active2"></button></div>
	            <div id="side03"><button id="blog" class="active2"></button></div>
	            <div id="side04"><button id="facebook" class="active2"></button></div>
	            <div id="side05"><button id="youtube" class="active2"></button></div>
	            <div id="side06"><button id="notice" class="active2" onclick="goNotice();">NOTICE</button></div>
	            <div id="side07"><button id="faq" class="active2" onclick="goFaq();">FAQ</button></div>
	            <div id="side08"><button id="qna" class="active2" onclick="goQnA();">Q&A</button></div>
	            <div id="side09"><button id="info" class="active2" onclick="goOnline();">ONLINE</button></div>
	            <div id="side10"><button id="accom" class="active2" onclick="goAccom();">ACCOM</button></div>
	            <div id="side11"><button id="rentcar" class="active2" onclick="goCar();">RENTCAR</button></div>
	            <div id="side12">
	                <img id="img01" src="${root}/static/img/a.png" alt="사이드바" class="active2">
	            </div>
	            <div id="side13"><hr id="hr01" class="active2"></div>
	            <div id="side14"><a class="active2">고객지원센터</a></div>
	            <div id="side15"><a class="active2">123-4567</a></div>
	            <div id="side16"><hr class="active2" id="hr02"></div>
	            <div id="side17"><a class="active2">주말 및 공휴일은 cs센터 및</a></div>
	            <div id="side18"><a class="active2">물류가 운영되지 않습니다.</a></div>
	            <div id="side19"><a class="active2">AM 10:00 ~ PM 17:00</a></div>
	            <div id="side20"><a class="active2">(점심시간 12:00 ~ 13:10)</a></div>
	            <div id="side21"><hr class="active2" id="hr03"></div>
	      
	        </div>
	        
	        <!-- 광고배너영역 -->
	        <div class="adver-area01">
		        <div id="adver-area"><a>Benefits</a></div>
		        <div id="bannerImg01"><img src="${root}/static/img/광고배너1.png" alt="광고배너01"></div>
		        <div id="bannerImg02"><img src="${root}/static/img/광고배너2.png" alt="광고배너01"></div>
	        </div>

			<!-- 판매자 광고배너영역 -->
			<div class="adver-area02">
				<div id="adver-area1"><a>Souvenir AD</a></div>
				<div id="bannerImg03"><img src="${root}/static/img/배너1.png" alt="배너"></div>
				<div id="bannerImg04"><img src="${root}/static/img/배너2.png" alt="배너"></div>
				<div id="bannerImg05"><img src="${root}/static/img/배너3.png" alt="배너"></div>
			</div>

			<div id="ont"><a >ONLINE TRAVEL</a></div>
			
		<div id="online" style="width: 1000; height: 500; margin-left: 50px;" >

				<!-- 왼쪽화살표 -->
			<button id="btn03" >
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-square-fill" viewBox="0 0 16 16">
				<path d="M16 14a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12zm-4.5-6.5H5.707l2.147-2.146a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708-.708L5.707 8.5H11.5a.5.5 0 0 0 0-1z"/>
				</svg>
				</button>
			  
			  <a href="https://www.youtube.com/watch?v=dEnBXRkDBUE"><img style="width: 250; height: 250px;" src="${root}/static/img/ranson.png" alt="이미지" id="ransonImg1"></a>
			  <a href="https://www.youtube.com/watch?v=2BPLbTIpOZI"><img style="width: 250; height: 250px;" src="${root}/static/img/ranson2.png" alt="이미지" id="ransonImg2"></a>
			  <a href="https://www.youtube.com/watch?v=t9gVHZlb3vA"><img style="width: 250; height: 250px;" src="${root}/static/img/ranson3.png" alt="이미지" id="ransonImg3"></a> 
				
			  <a href="https://www.youtube.com/watch?v=gLatnL0R9Pg"><img style="width: 250; height: 250px;" id="ransonImg4" src="${root}/static/img/pari.png" alt="이미지"></a>  
			  <a href="https://www.youtube.com/watch?v=gLatnL0R9Pg"><img style="width: 250; height: 250px;" id="ransonImg5" src="${root}/static/img/런던.png" alt="이미지"></a>  
			  <a href="https://www.youtube.com/watch?v=lN7WZco3Avg"><img style="width: 250; height: 250px;" id="ransonImg6" src="${root}/static/img/충주.png" alt="이미지"></a>   
			  
			  <!-- 오른쪽화살표 -->
			  <button id="btn04">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right-square-fill" viewBox="0 0 16 16">7
				<path  d="M0 14a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v12zm4.5-6.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5a.5.5 0 0 1 0-1z"/>
				</svg>
			  </button>


		</div>

			
</body>
</html>
<script>
    //공지사항이동
    function goNotice(){
        location.href = '${root}/notice/list'
    }

    //faq
    function goFaq(){
        location.href = '${root}/cs/faq'
    }

    //qna
    function goQnA(){
        location.href = '${root}/cs/inquery'
    }
    
    //랜선여행
    function goOnline(){
        location.href = '${root}/online/travel'
    }

    //숙소예약
    function goAccom(){
        location.href = '${root}/room/list'
    }

    //차량예약
    function goCar(){
        location.href = '${root}/car/list'
    }

	let currentRansonImage = 1;
	
    const btn03 = document.querySelector('#btn03');
    const btn04 = document.querySelector('#btn04');
    

    const ransonImg1  = document.querySelector('#ransonImg1');
    const ransonImg2 = document.querySelector('#ransonImg2');
    const ransonImg3 = document.querySelector('#ransonImg3');

    const ransonImg4 = document.querySelector('#ransonImg4');
    const ransonImg5 = document.querySelector('#ransonImg5');
    const ransonImg6 = document.querySelector('#ransonImg6');

	
   

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