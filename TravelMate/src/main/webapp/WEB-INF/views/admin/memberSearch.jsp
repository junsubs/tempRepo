<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/memberSearch.css">
<script defer src="${root}/static/admin/js/memberSearch.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>

     <!-- 내용영역 -->
     <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>회원조회</a>
            <div id="report-search">
                <form action="${root}/admin/memberSearch" method="GET">
                    <input type="hidden" name="page" value="1"> 
                    <select name="searchType">
                        <option value="memberId">회원아이디</option>
                        <option value="memberNick">회원닉네임</option>
                        <option value="status">회원상태</option>
                    </select>
                    <input value="${searchVo.searchValue}" name="searchValue" type="text" placeholder="회원관련 검색">
                    <select name="searchValue" class="semiCategory">
                        <option value="O">활동중</option>
                        <option value="X">활동정지</option>
                      </select>
                    <input type="submit" value="검색">
                </form>
            </div>
        </div>
        <div id="declaration">
            <div></div>
            <div>회원번호</div>
            <div>아이디</div>
            <div>닉네임</div>
            <div>회원등급</div>
            <div>상태</div>
            <div id="hr"><hr></div>

            <c:forEach var="voList" items="${voList}">
                <div>💚</div>
                <div id="no">${voList.no}</div>
                <div>${voList.id}</div>
                <div>${voList.nick}</div>
                <div>${voList.gradeName}</div>
                <div>${voList.status}</div>
                <div id="hr"><hr></div>
            </c:forEach>
        
            <div id="page-area">
                <c:if test="${pv.currentPage > 1}">
                    <a href="${root}/admin/memberSearch?page=${pv.currentPage - 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">◀ 이전</a>
                    </c:if>
                    <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                        <c:if test="${pv.currentPage != i}">
                            <a href="${root}/admin/memberSearch?page=${i}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>
                        </c:if>
                        <c:if test="${pv.currentPage == i}">
                            <a>${i}</a>
                        </c:if>
                    </c:forEach>
                <c:if test="${pv.currentPage < pv.maxPage}">
                    <a href="${root}/admin/memberSearch?page=${pv.currentPage + 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음 ▶</a>
                </c:if>
            </div>
        </div>

        <div id="memberMore" class="stop-active1">
              
            <div id="modal-content">
                  <div id="close">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                      <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                      </svg>
                  </div>                                                    
                  <div>회원번호</div>                             
                  <div>▶</div>                             
                  <div>${vo.no}</div>                             
                  <div>아이디</div>                             
                  <div>▶</div>                             
                  <div>${vo.id}</div>                             
                  <div>닉네임</div>                             
                  <div>▶</div>                             
                  <div>nick01</div>                             
                  <div>이메일</div>                             
                  <div>▶</div>                             
                  <div>sfsdf@naver.com</div>                             
                  <div>주소</div>                             
                  <div>▶</div>                             
                  <div>서울시 강남구 kh정보교육원</div>                             
                  <div>회원등급</div>                             
                  <div>▶</div>                             
                  <div>실버</div>                             
                  <div>가입일</div>                             
                  <div>▶</div>                             
                  <div>2023-01-01</div>                             
                  <div>정보수정일</div>                             
                  <div>▶</div>                             
                  <div>2023-01-02</div>                             
                  <div>상태</div>                             
                  <div>▶</div>                             
                  <div>O</div>                             
                  <div>탈퇴여부</div>                             
                  <div>▶</div>                             
                  <div>X</div>                             
              </div>

            </div>
        </div>  
    
</body>
</html>
<script>
    //회원 상세조회
	const declaration = document.querySelector("#declaration");
	
	declaration.addEventListener("click", function(e) {
	  if (e.target.id === "no") {
          const no = e.target.innerText;
	    location.href = "${root}/admin/membersearchdetail?no=" + no;
	  }
	});

    //서치타입 변경 시 함수 실행
    const searchType = '${searchVo.searchType}';
    const searchValue = '${searchVo.searchValue}';
    
    const searchValueSelectTag = document.querySelector("select[name='searchValue']");
	const searchValueInputTag = document.querySelector("input[name='searchValue']");
	const submit = document.querySelector("input[type='submit']");
    
   	if(searchType.length > 1){
   		initSearchType();
   	}
    
   	function initSearchType(){
   		const x = document.querySelector('select > option[value="' + searchType + '"]');
	    x.selected = true;   
   	}
   	
    //카테고리를 신고사유로 변경시 함수호출
    const searchTypeTag = document.querySelector('select[name="searchType"]');

    searchTypeTag.addEventListener("change" , setSearchValueTag);
    
	function setSearchValueTag(){
		const searchType = searchTypeTag.value;
		if(searchType == 'status'){
			setSearchValueTagSelect();
		}else{
			setSearchValueTagInput();
		}
	}

	//검색값 영역을 셀렉트가 보이게
	function setSearchValueTagSelect(){
		searchValueSelectTag.classList.add("active");
        searchValueSelectTag.classList.remove("hidden");
        searchValueSelectTag.disabled = false;

		searchValueInputTag.classList.add("hidden");
        submit.classList.remove("hidden");
        searchValueInputTag.disabled = true;

	}

	//검색값 영역을 인풋이 보이게
	function setSearchValueTagInput(){
        searchValueSelectTag.classList.add("hidden");
        searchValueSelectTag.classList.remove("active");
        searchValueSelectTag.disabled = true;
        
		searchValueInputTag.classList.remove("hidden");
        submit.classList.remove("hidden");
        searchValueInputTag.disabled = false;
	}

    //신고사유 검색한 이후에 검색값 유지
    function initSearchValueSelect(){
        const optionTag = document.querySelector("option[value='"+ searchValue +"']");
        optionTag.selected = true;
    }
	
	setSearchValueTag();
	initSearchValueSelect();

</script>