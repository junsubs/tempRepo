<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/sellRequest.css">
<script defer src="${root}/static/js/admin/sellRequest.js"></script>
<style>
    
</style>
</head>
<body>
    <%@ include file="/WEB-INF/views/admin/header.jsp" %>

      <!-- 내용영역 -->
      <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>판매등록요청조회</a>
            <div id="report-search">
                <form action="${root}/admin/sellrequest" method="get">
                    <input type="hidden" name="page" value="1"> 
                    <select name="searchType">
                        <option value="title">제목</option>
                        <option value="writer">작성자</option>
                    </select>
                    <input value="${searchVo.searchValue}" name="searchValue" type="text" placeholder="판매등록조회관련 검색">

                    <input type="submit" value="검색">
                </form>
            </div>
        </div>
     
        <div id="declaration">
            <div>게시글번호</div>
            <div>제목</div>
            <div>작성일</div>
            <div id="hr"><hr></div>

            <c:forEach var="voList" items="${voList}">
           
        
                </label>
                <div id="no">${voList.no}</div>
                <div><img src="${root}/static/img/sell-request-img/${voList.boardImgTitle}" width="100" height="70" alt=""></div>
                <div>${voList.enrollDate}</div>
                <div id="hr"><hr></div>
            </c:forEach>
            
            <div id="page-area">
                <c:if test="${pv.currentPage > 1}">
                    <a href="${root}/admin/sellrequest?page=${pv.currentPage - 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">◀ 이전</a>
                    </c:if>
                    <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                        <c:if test="${pv.currentPage != i}">
                            <a href="${root}/admin/sellrequest?page=${i}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>
                        </c:if>
                        <c:if test="${pv.currentPage == i}">
                            <a>${i}</a>
                        </c:if>
                    </c:forEach>
                <c:if test="${pv.currentPage < pv.maxPage}">
                    <a href="${root}/admin/sellrequest?page=${pv.currentPage + 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음 ▶</a>
                </c:if>
            </div>
        </div>


    </div>

${voList}

</body>
</html>
<script>
    //체크박스 하나만
    function handleCheckboxChange(checkbox) {
        var checkboxes = document.getElementsByName('stop');
        for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].disabled = checkboxes[i] !== checkbox && checkbox.checked;
        }
    }

    //게시글 상세조회
	function sellEnroll(no){
        location.href = "${root}/admin/sellrequestdetail?no=" + no;
    }

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

    //검색한 이후에 검색값 유지
    function initSearchValueSelect(){
        const optionTag = document.querySelector("option[value='"+ searchValue +"']");
        optionTag.selected = true;
    }
	
	setSearchValueTag();
	initSearchValueSelect();

</script>