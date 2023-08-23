<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/sellequestDetail.css">
<script defer src="${root}/static/js/sellRequest.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>

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
            <div></div>
            <div>게시글번호</div>
            <div>작성자</div>
            <div>제목</div>
            <div>작성일</div>
            <div></div>
            <div id="hr"><hr></div>

            <c:forEach var="voList" items="${voList}">
            <div id="report-content">
                <label class="chk_box">
                <input type="checkbox" value="${voList.no}" name="stop">
                <span class="on"></span>
        
                </label>
            </div>
                <div id="no">${voList.no}</div>
                <div>${voList.writer}</div>
                <div>${voList.title}</div>
                <div>${voList.enrollDate}</div>
                <!-- <div><button id="sell" onclick="sellEnroll(${voList.no});">판매등록</button></div> -->
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

        <div id="enrollMore" class="stop-active1">
            
            <form action="" method="post">
                <div id="modal-content">
                  <div>게시글번호</div>                                               
                  <div>▶</div>                                               
                  <div><input type="text" value="1" name="boardNo" readonly></div>   
                  <div>작성자</div> 
                  <div>▶</div>                                            
                  <div><input type="text" value="성심당" name="writer" readonly></div>                                            
                  <div>제목</div> 
                  <div>▶</div>                                            
                  <div><input type="text" value="슈크림빵 판매등록 요청합니다." name="title" readonly></div>                                            
                  <div>내용</div>   
                  <div>▶</div> 
                  <div><input type="text" value="슈크림빵 판매등록 요청드립니다. 등록해주세요." name="content" readonly></div>
                  <div>작성일</div>                              
                  <div>▶</div>                              
                  <div><input type="date" value="2023-01-01" name="enrollDate" readonly></div>                              
                  <div>이미지</div>                              
                  <div id="img-area">
                    <img src="${root}/static/img/adBanner/adBanner02.jpg" alt="배너이미지">
                    </div>                     
                  <div id="submitDiv"><input type="submit" value="등록"></input></div>
                  <div id="buttonDiv"><button type="button" id="close">취소</button></div>
                </div>
            </form>

        </div>
    </div>

</body>
</html>
<script>
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