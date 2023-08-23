<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<style>


    /* 내용영역 */
    #content{
        position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 350px;
	    bottom: 500px;
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
    

    #orderList-area {
        height: 1000px;
        left: 100px;
        top: 230px;
        font-size: 18px;
        position: absolute;
        width: 1300px;
        height: 80%;
        text-align: center;
        border-collapse: separate;
        border-spacing: 2px;
        margin-top: 20px;
    }


    #orderList-area tbody td {
        border-bottom: 1px solid black; /* 선 스타일 및 색상 설정 */
        text-align: center;
        vertical-align: middle;
    }

    #orderList-area thead th {
        font-weight: 600;
        font-size: 22px;
        border-bottom: 2px solid black; /* 선 스타일 및 색상 설정 */
    }

    img {
        width: 150px;
        height: 150px;
        margin-right: 0;
    }

    #page-area{
        position: absolute;
        top: 1100px;
        left: 100px;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 20px;
    }

    #btn01 {
        background-color: #73d38e75;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: black;
        border-radius: 6px;
        width: 100px;
        height: 30px;
        font-size: 16px;
        margin-left: 80px;
        vertical-align: middle;
    }


    #review-btn {
        background-color: rgba(150, 237, 162, 0.508);
        color: black;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        border-radius: 6px;
        width: 220px;
        height: 30px;
        font-size: 18px;
        margin-left: 15px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
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
        left: 350px;
        top: 5px;
        width: 50px;
        height: 30px;
        font-size: 17px;
    }

    select[name=searchType] {
        height: 40px;
        font-size: 17px;
    }

    #report-search{
        position: absolute;
        right: 200px;
        top: 141px;
        width: 150px;
        height: 40px;
    }

	.searchValueElem {
		position: absolute;
        left: 90px;
        width: 120px;
        height: 40px;
        font-size: 20px;
	}

    #goods_detail {
        display: grid;
        grid-template-columns: 1fr 3fr;
    }

    #btn02 {
        background-color: rgba(150, 237, 162, 0.508);
        color: black;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        border-radius: 6px;
        width: 80px;
        height: 30px;
        font-size: 15px;
        margin-left: 10px;
    }


</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/mypage-header.jsp" %>
    <!-- 내용영역 -->
    <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>주문내역</a>
        </div>
        <!-- 검색 -->
        <div id="report-search">
            <form action="${root}/mypage/orderList" method="GET">
            <select name="searchType">
                <option value="category">카테고리</option>
            </select>
            <select name="searchValue" class="searchValueElem">
                <option value="1">렌트카</option>
                <option value="2">숙소</option>
                <option value="3">기념품</option>
            </select>
                <input type="submit" value="검색">
            </form>
        </div>
        <!-- <div id="order-list-box"> -->
            <!-- 반복문 처리 -->
            <table id="orderList-area" align="center">
                <thead>
                    <tr>
                        <th>주문번호</th>
                        <th>주문상세내용</th>
                        <th>주문날짜</th>
                        <th>결제수단</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${voList}" var="vo">
                    <tr>
                        <td>${vo.payNo}</td>
                        <td>
                            <div id="goods_detail">
                                <div id="goods_img">
                                    <c:choose> 
                                        <c:when test="${searchVo.searchValue eq '2' }">
                                            <img src="${root}/static/img/accomodation_img/${vo.img}" alt="숙소이미지">
                                        </c:when> 
                                        <c:when test="${searchVo.searchValue eq '1' }">
                                            <img src="${root}/static/img/carImg/${vo.img}" alt="차량이미지">
                                        </c:when> 
                                        <c:otherwise>
                                            <img src="${root}/static/img/souvenir/${vo.img}" alt="기념품이미지">
                                        </c:otherwise> 
                                    </c:choose> 
                                </div>
                                <div id="content_area">
                                    상품명 : ${vo.name}
                                    <br>
                                    상품 가격 : ${vo.price}
                                    <br>
                                <c:if test="${searchVo.searchValue eq '1' || searchVo.searchValue eq '2' }">
                                    예약 시작 날짜 : ${vo.startDate}
                                    <br>
                                    예약 종료 날짜 : ${vo.endDate}
                                    <br>
                                </c:if>
                                <c:if test="${searchVo.searchValue eq '3'}">
                                    주문 주소 : ${vo.address}
                                    <br>
                                    주문자명 : ${vo.oderName}
                                    <br>
                                    주문 수량 : ${vo.cnt}
                                </c:if>
                                <br>
                                <button id="review-btn" onclick="writeReview(event);">리뷰 쓰러가기!</button>
                                </div>
                            </div>
                        </td>
                        <td>${vo.payDate}</td>
                        <td>
                            ${vo.payType}
                            <br>
                            <br>
                            <button id="btn02" onclick="refund(event);">환불</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                </table>
                <div id="page-area">
                    <c:if test="${pv.currentPage > 1 }">
                    <a id="btn01" href="${root}/mypage/orderList?page=${pv.currentPage-1}">이전</a>
                    </c:if>
                    <c:forEach begin="${pv.startPage}" end="${pv.endPage}" var="i">
                        <a id="btn01" href="${root}/mypage/orderList?page=${i}">${i}</a>
                    </c:forEach>
                    <c:if test="${pv.currentPage < pv.maxPage}">
                    <a id="btn01" href="${root}/mypage/orderList?page=${pv.currentPage+1}">다음</a>
                    </c:if>
                </div>
        <!-- </div> -->
      </div>
    </div>
    
    <script>

	const searchType = '${searchVo.searchType}';
	const searchValue = '${searchVo.searchValue}';
	
	const searchValueSelectTag = document.querySelector("select[name='searchValue']");
	const searchValueInputTag = document.querySelector("input[name='searchValue']");


	// 카테고리로 검색한 이후에 검색 값이 유지되게 
	function initSearchValueSelect(){
		if(searchType != 'category'){
			return;
		}
		const optionTag = document.querySelector("option[value='1']");
		optionTag.selected = true;	
	}
	

	// setSearchValueTag();
	initSearchValueSelect();

    const payNo01 = '${vo.payNo}';

    function writeReview(event) {

        const target = event.target.parentElement;
        const payNo = target.closest("tr").querySelector("td:first-child").textContent;

    
    let reviewPageUrl = 'root/reivew/write?payNo=payNo';
    
    if (searchValue === '1') {
        reviewPageUrl = '${root}/review/write?payNo=' + payNo;
    } else if (searchValue === '2') {
        reviewPageUrl = '${root}/review/write?payNo=' + payNo;
    } else if (searchValue === '3') {
        reviewPageUrl = '${root}/review/write?payNo=' + payNo;
    }
    
    window.location.href = reviewPageUrl;
    }

    function refund(event) {

        const target = event.target.parentElement;
        const payNo = target.closest("tr").querySelector("td:first-child").textContent;


        let refundPageUrl = 'root/refund/payNo=payNo';

        if (searchValue === '1') {
        refundPageUrl = '${root}/car/refund?payNo=' + payNo;
        } else if (searchValue === '2') {
        refundPageUrl = '${root}/room/refund?payNo=' + payNo;
        } else if (searchValue === '3') {
        refundPageUrl = '${root}/souvenir/refund?payNo=' + payNo;
        }

        window.location.href = refundPageUrl;
        }

	
</script>

</body>

</html>