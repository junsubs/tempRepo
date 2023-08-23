<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


    /* 내용영역 */
    #content{
        position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 350px;
	    bottom: 450px;
    }

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 125px;
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
    
    #content > table {
    	position: absolute;
        width: 1300px;
        height: 500px;
        left: 100px;
        top: 230px;
		margin: auto;
		border: none;
        border-radius: 20%;
		border-collapse: collapse;
		text-align: center;
		line-height: 60px;
	}


    #content > table th {
        font-size: 25px;
        font-weight: bold;
    }

	#content > table td {
		border-bottom: 1px solid black;
	}

    #content > table > tbody > tr:nth-last-child(1) {border-bottom: none;}


	#content > table > tbody tr {
		height: 27px;
	}

	#content > table > thead > tr > th:nth-child(1){width: 20%;}
	#content > table > thead > tr > th:nth-child(2){width: 15%;}
	#content > table > thead > tr > th:nth-child(3){width: 35%;}
	#content > table > thead > tr > th:nth-child(4){width: 20%;}
	#content > table > thead > tr > th:nth-child(5){width: 10%;}

	#page-area{
        position: absolute;
        width: 1300px;
        height: 30px;
        left: 100px;
        top: 920px;
		margin: auto;
		display : flex;
		justify-content: space-evenly;
	}
	
	tbody > tr:hover {
		background-color: lightgray;
		cursor: pointer;
	}


    #btn01 {
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        text-align: center;
        color: white;
        border-radius: 6px;
        width: 100px;
        height: 40px;
        font-size: 20px;
        margin-left: 15px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }
    
    #btn02{
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
        width: 50px;
        height: 30px;
        font-size: 20px;
    }

    /* 카테고리 및 검색 */
    #report-search input[type="submit"]{
        /* background-image: url('./img/검색이미지.png'); */
        /* background-repeat: no-repeat; */
        background-color: rgba(84, 190, 128, 0.562);
        border: 0px;
        cursor:pointer;
        outline: 0;
        color: rgb(4, 4, 4);
        position: absolute;
        left: 400px;
        top: 5px;
    }

    #report-search{
        position: absolute;
        right: 50px;
        top: 141px;
    }

    #report-search>form>input[type="text"]{
        width: 250px;
        height: 30px;
        font-size: 14px;
    }

    select[name="searchType"]{
        width: 110px;
        height: 30px;
        font-size: 15px;
    }

    .searchValueElem{
        display: none;
        width: 250px;
        height: 30px;
        font-size: 14px;
    }

	.active{display: inline-block;}

    


</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/mypage-header.jsp" %>
    <!-- 내용영역 -->
    <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>게시글 조회</a>
        </div>
            <!-- 게시판 카테고리 검색 -->
            <div id="report-search">
                <form action="${root}/mypage/myBoardList" method="POST">
                    <select name="searchType">
						<option value="title">제목</option>
						<option value="category">카테고리</option>
					</select>
					<select name="searchValue" class="searchValueElem">
						<option value="5">자유</option>
                        <option value="4">리뷰</option>
                        <option value="2">판매</option>
                    </select>
                    <input type="text" class="searchValueElem" name="searchValue" value="${searchVo.searchValue}" placeholder="검색 할 내용을 입력하세요">
                    <input type="submit" value="검색">
                </form>
            </div>
        <table>
				<thead>
					<tr>
						<th>게시판 카테고리</th>
						<th>게시글 번호</th>
						<th>제목</th>
						<th>작성일시</th>
						<th>조회수</th>
					</tr>
				</thead>

				<tbody>
				
					<c:forEach items="${ voList }" var="myBoard">
					<tr>
						<td>${ myBoard.categoryName }</td>
						<td>${ myBoard.no }</td>
						<td>${ myBoard.title }</td>
						<td>${ myBoard.enrollDate}</td>
						<td>${ myBoard.hit}</td>
					</tr>
					</c:forEach>
					
				</tbody>

			</table>
			<div id="page-area">
				<c:if test="${pv.currentPage > 1 }">
				<a id="btn01" href="${root}/mypage/myBoardList?page=${pv.currentPage-1}">이전</a>
				</c:if>
				<c:forEach begin="${pv.startPage}" end="${pv.endPage}" var="i">
					<a id="btn02" href="${root}/mypage/myBoardList?page=${i}">${i}</a>
				</c:forEach>
				<c:if test="${pv.currentPage < pv.maxPage}">
				<a id="btn01" href="${root}/mypage/myBoardList?page=${pv.currentPage+1}">다음</a>
				</c:if>
			</div>
        </div>
    </div>


</body>

<script>
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

	function setSearchValueTag(){

		// 현재 타입이 카테고리인지 구분
		const searchType = searchTypeTag.value;
		if(searchType == 'category'){
			setSearchValueTagSelect();
		}else{
			setSearchValueTagInput();
		}
	}

	// 검색 값 영역 select 보이게 (타입이 카테고리 일 때)
	function setSearchValueTagSelect(){
		searchValueSelectTag.classList.add("active");
		searchValueSelectTag.disabled = false;
		searchValueInputTag.classList.remove("active");
		searchValueInputTag.disabled = true;

		searchValueInputTag.value = '';
	}

	// 검색 값 영역을 input 보이게 (타입이 카테고리 외)
	function setSearchValueTagInput(){
		searchValueInputTag.classList.add("active");
		searchValueInputTag.disabled = false;
		searchValueSelectTag.classList.remove("active");
		searchValueSelectTag.disabled = true;
	}

	// 카테고리로 검색한 이후에 검색 값이 유지되게 
	function initSearchValueSelect(){
		if(searchType != 'category'){
			return;
		}
		const optionTag = document.querySelector("option[value='" + searchValue + "']");
		optionTag.selected = true;	
	}
	

	setSearchValueTag();
	initSearchValueSelect();


	// 테이블 행 클릭 시. 상세조회
	const tbody = document.querySelector('tbody');
	tbody.addEventListener('click', (event)=>{
		
        // 카테고리 이름 가져오기
        const categoryName = event.target.parentNode.children[0].innerText;
		console.log(categoryName);
        // 글 번호 가져와서 
		const no = event.target.parentNode.children[1].innerText;
        console.log(no);

		// 요청 보내기
        if(categoryName == '자유'){
            location.href = '${root}/free/detail?no=' + no;
        }else if (categoryName == '리뷰'){
            location.href = '${root}/review/detail?no=' + no;
        }else if(categoryName == '판매등록요청'){
            location.href = '${root}/?/detail?no=' + no;
        }else if (categoryName == '랜선여행'){
            location.href = '${root}/online/travel/detail?no=' + no;
        }

	});


</script>

</html>