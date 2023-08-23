<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

    #content{
        position: relative;
        width: 1390px;
        height: 100%;
        bottom: 1150px;
        left: 300px;
        margin-top: 600px;
    }
    

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 70px;
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

    #btn01 {
        background-color: #73D38E;
        font-size: 2.5em;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   

    #write-area{ 
        padding-top: 100px;
        margin-left: 20%;
    }

    table{
        height: 500px;
        width: 1000px;
        background-color: #d8f1c7;
        border-radius: 20px;
    }

    table > thead > tr th , table > tbody > tr td {
        font-size: 2.5em;
        text-align: center;
        border: 2px solid black;
        height: 20px;
    }

    tbody > tr:hover {
		background-color: rgb(32, 229, 52);
		cursor: pointer;
	}

    input[name=searchValue]{
        font-size: 2em;
        width: 500px;
        height: 70px;
        background-color: #d8f1c7;
        border: none;
        border-radius: 20px;
        margin-top: 210px;
    }

    #page-area{
        margin-left: 250px;
        margin-top: 30px;
    }

 
    /* -------------------------------------------------- */

   
</style>
</head>
<body>


    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	
	<!-- 내용영역 -->
    <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>공지사항  목록</a>  
        </div>
       
        <div id="write-area">
            <form action="${root}/notice/list" method="post">
                <select id="btn01" name="searchType" >
                    <option value="title">제목</option>
                    <!-- <option value="writer">작성자</option> -->
                </select>
                    <input type="text" name="searchValue" value="${searchVo.searchValue}" placeholder="검색 할 내용을 입력하세요">
                    <input  id="btn01" type="submit" value="검색">
            </form>
            <br>
            <c:if test="${loginMember.id eq 'ADMIN' }">
	            <a  href="${root}/notice/write" id="btn01">글 작성하러 가기</a>
            </c:if>
            <br>
            <br>
            <table>
                <thead>
                    <tr>
                        <th>번호 </th>
                        <th>제목 </th>
                        <th>작성일시</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <hr>
                <tbody>
                	<c:forEach items="${bvoList}" var="bvoList">
	                    <tr>
	                        <td>${bvoList.no}</td>
	                        <td>${bvoList.title}</td>
	                        <td>${bvoList.enrollDate}</td>
	                        <td>${bvoList.hit}</td>
	                    </tr>
                	</c:forEach>

                </tbody>
            </table>
                <div id="page-area">
                     <c:if test="${pv.currentPage > 1}">
                        <a id="btn01" href="${root}/notice/list?page=${pv.currentPage-1}">이전</a>
                    </c:if>
                    <c:forEach begin="${pv.startPage}" end="${pv.endPage}" var="i">
                        <c:if test="${pv.currentPage ne i}">
                            <a id="btn01" href="${root}/notice/list?page=${i}">${i}</a>
                        </c:if>
                        <c:if test="${pv.currentPage == i}">
                            <a id="btn01" style="font-size: 0.5em;">${i}</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pv.currentPage < pv.maxPage}">
                        <a id="btn01" href="${root}/notice/list?page=${pv.currentPage+1}">다음</a>
                    </c:if> 
                </div>   
        </div>
    </div>


</body>
</html>

<script>

    //게시글 상세조회
	const tbody = document.querySelector("tbody");
	tbody.addEventListener("click" , function(e){
		const no = e.target.parentNode.children[0].innerText;
        console.log(no);
		location.href = "${root}/notice/detail?no=" + no;
	});
	
    // 검색 영역
    const searchType = '${searchVo.searchType}';
	const searchValue = '${searchVo.searchValue}';
	
	const searchValueSelectTag = document.querySelector("select[name='searchValue']");
	const searchValueInputTag = document.querySelector("input[name='searchValue']");

	if(searchType.length > 1){
		initSearchType();
	}
	
	// 검색 타입 초기 세팅
	function initSearchType(){
		const x = document.querySelector('select > option[value="' + searchType + '"]');
		x.selected = true;
	}
	
	// 서치타입 변경 시 함수 실행
	const searchTypeTag = document.querySelector('select[name=searchType]');
	searchTypeTag.addEventListener("change", setSearchValueTag);

	// function setSearchValueTag(){

	// 	// 현재 타입이 카테고리인지 구분
	// 	const searchType = searchTypeTag.value;
	// 	if(searchType == 'category'){
	// 		setSearchValueTagSelect();
	// 	}else{
	// 		setSearchValueTagInput();
	// 	}
	// }

	// 검색 값 영역 select 보이게 (타입이 카테고리 일 때)
	// function setSearchValueTagSelect(){
	// 	searchValueSelectTag.classList.add("active");
	// 	searchValueSelectTag.disabled = false;
	// 	searchValueInputTag.classList.remove("active");
	// 	searchValueInputTag.disabled = true;

	// 	searchValueInputTag.value = '';
	// }

	// // 검색 값 영역을 input 보이게 (타입이 카테고리 외)
	// function setSearchValueTagInput(){
	// 	searchValueInputTag.classList.add("active");
	// 	searchValueInputTag.disabled = false;
	// 	searchValueSelectTag.classList.remove("active");
	// 	searchValueSelectTag.disabled = true;
	// }

	// 카테고리로 검색한 이후에 검색 값이 유지되게 
	// function initSearchValueSelect(){
	// 	if(searchType != 'category'){
	// 		return;
	// 	}
	// 	const optionTag = document.querySelector("option[value='" + searchValue + "']");
	// 	optionTag.selected = true;	
	// }
	
	// setSearchValueTag();
	// initSearchValueSelect();


</script>