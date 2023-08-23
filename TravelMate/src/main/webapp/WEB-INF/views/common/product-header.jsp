<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${root}/static/css/header2.css">
<script defer src="${root}/static/js/header3.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body bottommargin="0">
    
    <div id="wrap">
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
        	</c:if>
        	
            <a href="${root}/mypage/member/detail">마이페이지</a>
            <a>|</a>
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
            <div><a href="${root}/reivew/list">리뷰</a></div>
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
            <div id="small-logo">
                <img src="${root}/static/img/TM small logo.png" alt="프로필사진">
            </div>
            <div id="side">
                <div id="side-content">
                     <div class="topbar" style="position: absolute; top:0;">
                        <div class="left_sub_menu">
                            <div id="sub-img">
                                 <img id="triangle2" src="${root}/static/img/삼각형2.png" alt="삼각형2">
                            </div>
                        </div>        
                        <div class="sub_menu">
                            <h2 id="admin-private" data-hover="회원" class="SMN_effect-23-2">회원</h2>
                            <ul class="big_menu">
                                     <li>상품목록<i class="arrow fas fa-angle-right"></i></li>
                                     <ul class="small_menu">
                                         <li><a href="${root}/car/list">차량</a></li>
                                         <li><a href="${root}/room/list">숙소</a></li>
                                         <li><a href="${root}/souvenir/list">기념품</a></li>
                                     </ul>
                                 </ul>
                                 <ul class="big_menu">
                                     <li>게시판 <i class="arrow fas fa-angle-right"></i></li>
                                     <ul class="small_menu">
                                         <li><a href="${root}/notice/list">공지게시판</a></li>
                                         <li><a href="${root}/free/list">자유게시판</a></li>
                                         <li><a href="${root}/online/travel">랜선여행</a></li>
                                         <li><a href="${root}/review/list">리뷰게시판</a></li>
                                     </ul>
                                 
                        </div>
                         </div>
                         <div class="overlay"></div>
                     </div>
                </div>
            </div>
        </div>

           <!-- 오른쪽사이드바 -->
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
    </div>

</body>
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
</script>
</html>