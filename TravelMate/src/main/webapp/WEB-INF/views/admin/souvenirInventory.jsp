<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/souvenirInventory.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>

	<%@ include file="/WEB-INF/views/admin/header.jsp" %>
	
	  <!-- 내용영역 -->
      <div id="content">
          <div id="first-content">
              <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
              <hr>
              <a>기념품재고조회</a>
              <div id="report-search">
                  <form action="${root}/admin/souvenirinventory" method="get">
                      <input type="hidden" name="page" value="1"> 
                      <select name="searchType">
                          <option value="souvenirName">기념품명</option>
                          <option value="areaName">지역명</option>
                      </select>
                      <input value="${searchVo.searchValue}" name="searchValue" type="text" placeholder="기념품재고관련 검색">
                      <select name="searchValue" class="semiCategory">
                        <option value="강원도">강원도</option>
                        <option value="제주도">제주도</option>
                        <option value="전남">전남</option>
                        <option value="전북">전북</option>
                        <option value="경남">경남</option>
                        <option value="경북">경북</option>
                        <option value="수도권">수도권</option>
                        <option value="대전">대전</option>
                        <option value="대구">대구</option>
                        <option value="부산">부산</option>
                      </select>
                      <input type="submit" value="검색">
                  </form>
              </div>
          </div>
          
          <div id="declaration">
              <c:forEach var="voList" items="${voList}">
                <div id="cbx">
                    <label class="chk_box">
                    <input type="checkbox" value="${voList.no}" name="souvenirNo" onchange="handleCheckboxChange(this)">
                    <span class="on"></span>
            
                    </label>
                </div>
                 <div id="img-area01"><img id="img-area" src="${root}/static/img/souvenir/${voList.changeName}" alt="${voList.originName}"></div>
                 <div id="name1">기념품코드</div>
                 <div>|</div>
                 <div id="code"><input type="text" value="${voList.no}" name="no" readonly></div>
                 <div id="name2">품명</div>
                 <div>|</div>
                 <div id="dName"><input type="text" value="${voList.name}" name="souvenirName" readonly></div>
                 <div id="name3">지역</div>
                 <div>|</div>
                 <div id="localArea"><input type="text" value="${voList.localName}" name="local" readonly></div>
                 <div id="name4">가격</div>
                 <div>|</div>
                 <div id="dPrice"><input type="text" value="KRW ${voList.price}" name="price" readonly></div>
                 <div id="name5">상품등록일</div>
                 <div>|</div>
                 <div id="dEnroll"><input type="date" value="${voList.enrollDate}" name="enrollDate" readonly></div>
                 <div id="name7">재고</div>
                 <div>|</div>
                 <div id="dCount"><input type="number" value="${voList.count}" name="count" readonly></div>
                 <div><button id="edit" onclick="souvenirEdit(${voList.no});">재고수정</button></div>
                 <div id="hr011"></div>
			 </c:forEach>

              <div id="page-area">
                        <c:if test="${pv.currentPage > 1}">
                        <a href="${root}/admin/souvenirinventory?page=${pv.currentPage - 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">◀ 이전</a>
                        </c:if>
                        <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                            <c:if test="${pv.currentPage != i}">
                                <a href="${root}/admin/souvenirinventory?page=${i}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>
                            </c:if>
                            <c:if test="${pv.currentPage == i}">
                                <a>${i}</a>
                            </c:if>
                        </c:forEach>
                    <c:if test="${pv.currentPage < pv.maxPage}">
                        <a href="${root}/admin/souvenirinventory?page=${pv.currentPage + 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음 ▶</a>
                    </c:if>
                </div>

            </div>
            <div><button id="write" onclick="souvenirWrite();">재고등록</button></div>

</body>
</html>
<script>
    //재고없으면 품절
    const images = document.querySelectorAll("#img-area");
    const countYnInputs = document.querySelectorAll("input[name='count']");

    images.forEach((img, index) => {
    const count = countYnInputs[index].value;

        if (count === '0') {
            img.src = '${root}/static/img/sell/SOLDOUT.png';
            img.alt = '품절';
        }
    });

    //체크박스 하나만
    function handleCheckboxChange(checkbox) {
        var checkboxes = document.getElementsByName('souvenirNo');
        for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].disabled = checkboxes[i] !== checkbox && checkbox.checked;
        }
    }

    //재고수정
    function souvenirEdit(no){
        location.href = '${root}/admin/souvenirEdit?no=' + no;
    };

    //재고등록
    function souvenirWrite(){
        window.location.href = '${root}/admin/souvenirinventoryWrite';
    };

    //가격에 콤마찍기
    const prices = document.querySelectorAll('input[name="price"]');

    prices.forEach(input => {
    const value = input.value;
    const commaValue = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    input.value = commaValue;
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
		if(searchType == 'areaName'){
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