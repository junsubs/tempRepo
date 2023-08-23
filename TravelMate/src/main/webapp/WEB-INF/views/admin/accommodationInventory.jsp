<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/accommodationInventory.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/admin/header.jsp" %>
    
        <!-- 내용영역 -->
        <div id="content">
            <div id="first-content">
                <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
                <hr>
                <a>숙소재고조회</a>
                <div id="report-search">
                    <form action="${root}/admin/accommodationinventory" method="get">
                        <input type="hidden" name="page" value="1"> 
                        <select name="searchType">
                            <option value="name">숙소명</option>
                            <option value="countYn">재고여부</option>
                        </select>
                        <input name="searchValue" type="text" placeholder="숙소재고관련 검색">
                        <select name="searchValue" class="semiCategory">
                            <option value="Y">빈방있음</option>
                            <option value="N">빈방없음</option>
                        </select>
                        <input type="submit" value="검색">
                    </form>
                </div>
            </div>
        
            <div id="declaration">
    
                <c:forEach var="voList" items="${voList}">
                    <div id="cbx">
                        <label class="chk_box">
                        <input type="checkbox" value="${voList.no}" name="accomNo" onchange="handleCheckboxChange(this)">
                        <span class="on"></span>
                
                        </label>
                    </div>
                    <div id="img-area01"><img id='img-area' src="${root}/static/img/accommodationImg/${voList.changeName}" alt="${voList.changeName}"></div>
                    <div id="name1">숙소코드</div>
                    <div>|</div>
                    <div id="code"><input type="text" value="${voList.no}" name="no" readonly></div>
                    <div id="name2">숙소명</div>
                    <div>|</div>
                    <div id="dName"><input type="text" value="${voList.name}" name="accomName" readonly></div>
                    <div id="name3">지역</div>
                    <div>|</div>
                    <div id="localArea"><input type="text" value="${voList.localName}" name="local" readonly></div>
                    <div id="name4">숙소종류</div>
                    <div>|</div>
                    <div id="dPrice"><input type="text" value="${voList.kind}" name="kind" readonly></div>
                    <div id="name5">최대수용인원</div>
                    <div>|</div>
                    <div id="dEnroll"><input type="text" value="${voList.maxPeople}명" name="maxPeople" readonly></div>
                    <div id="name7">재고여부</div>
                    <div>|</div>
                    <div id="dCount">
                        <c:if test="${voList.countYn == 'Y'}">
                            <input type="text" name='countYn' value='빈방있음'>
                        </c:if>
                        <c:if test="${voList.countYn == 'N'}">
                            <input type="text" name='countYn' value='빈방없음'>
                        </c:if>
                    </div>
                    <div><button id="edit" onclick="carEdit(${voList.no});">재고수정</button></div>
                    <div id="hr011"></div>
                </c:forEach>
                    
                <div id="page-area">
                        <c:if test="${pv.currentPage > 1}">
                        <a href="${root}/admin/accommodationinventory?page=${pv.currentPage - 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">◀ 이전</a>
                        </c:if>
                        <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                            <c:if test="${pv.currentPage != i}">
                                <a href="${root}/admin/accommodationinventory?page=${i}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>
                            </c:if>
                            <c:if test="${pv.currentPage == i}">
                                <a>${i}</a>
                            </c:if>
                        </c:forEach>
                    <c:if test="${pv.currentPage < pv.maxPage}">
                        <a href="${root}/admin/accommodationinventory?page=${pv.currentPage + 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음 ▶</a>
                    </c:if>
                </div>
            </div>
            <div><button id="write" onclick="accomWrite();">재고등록</button></div>
</body>
</html>
</body>
</html>
<script>
    //빈방없으면 품절
    const images = document.querySelectorAll("#img-area");
    const countYnInputs = document.querySelectorAll("input[name='countYn']");

    images.forEach((img, index) => {
    const countYn = countYnInputs[index].value;

        if (countYn === '빈방없음') {
            img.src = '${root}/static/img/accommodationImg/SOLDOUT.png';
            img.alt = '품절';
        }
    });

    //체크박스 하나만
    function handleCheckboxChange(checkbox) {
        var checkboxes = document.getElementsByName('accomNo');
        for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].disabled = checkboxes[i] !== checkbox && checkbox.checked;
        }
    }
  
    //재고수정
     function carEdit(no){
        location.href = '${root}/admin/accommodationEdit?no=' + no;
    }

    //재고등록
    function accomWrite(){
        window.location.href = '${root}/admin/accommodationWrite';
    };
    
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
		if(searchType == 'countYn'){
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

    //검색값 유지
    function initSearchValueSelect(){
        const optionTag = document.querySelector("option[value='"+ searchValue +"']");
        optionTag.selected = true;
    }
	
	setSearchValueTag();
	initSearchValueSelect();
</script>