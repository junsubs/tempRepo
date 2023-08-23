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

    #fav-content-area{
        width: 1300px;
        height: 100%;
        left: 100px;
        top: 200px;
        position: absolute;
        display: grid;
        grid-template-rows: 2fr 1fr;
    }
    
    #fav-list-box{
        width: 1300px;
        height: 70%;
        border-radius: 30px;
        font-size: 23px;
        justify-content: center;
        align-content: center;
    }

    #car-area, #accomo-area, #souvenir-area{
        width: 100%;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        justify-items: center;
    }

    #delBtn, #delBtn > img{
        border: none;
        width: 30px;
        height: 30px;
        margin-bottom: 170px;
        background-color: transparent;
    }

    #car-area > div, #accomo-area > div, #souvenir-area > div {
        text-align: center;
    }

    #car-wrap > a >img {
        width: 200px;
        height: 200px;
    }

    #accomo-wrap > a > img {
        width: 200px;
        height: 200px;
    }

    #souvenir-wrap > a >img {
        width: 200px;
        height: 200px;
    }

    #small-title{
        font-size: 23px;
        font-weight: bold;
        display: flex;
        justify-content: center;
    }

    #page-area{
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 20px;
        margin-top: 60px;
    }

    #btn01, #btn02 {
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: black;
        border-radius: 10px;
        width: 100px;
        height: 30px;
        font-size: 25px;
        margin-left: 15px;
    }

    #btn02 {
        background-color: #73D38E;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 10px;
        width: 40px;
        height: 30px;
        font-size: 25px;
        margin-left: 15px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
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
            <a>관심상품</a>
        </div>
        <div id="fav-content-area">
            <div id="fav-list-box">
                <!-- 반복문 처리 -->
                <!-- 차량 -->
                <span id="small-title">차량</span>
                <hr>
                <div id="car-area">
                    <c:forEach var="cvo" items="${cvoList}">
                        <div id="car-wrap">
                            <input type="hidden" value="${cvo.no}" name="no">
                            <a href="${root}/order/car?name=${cvo.carKind}"><img src="${root}/static/img/carImg/${cvo.carImg}" alt="차량이미지"></a>
                            <button id="delBtn" onclick="cdel('${cvo.no}');"><img src="${root}/static/img/mypage/favorites/삭제버튼아이콘.png" alt="삭제버튼"></button>
                            <br>
                            <div>${cvo.carKind}</div>
                        </div>
                    </c:forEach>
                </div>                
                <hr>
                <!-- 숙소 -->
                <span id="small-title">숙소</span>
                <hr>
                <div id="accomo-area">
                    <c:forEach var="avo" items="${avoList}">
                        <div id="accomo-wrap">
                            <input type="hidden" value="${avo.no}" name="no">
                            <a href="${root}/order/room?name=${avo.accomodationName}"><img src="${root}/static/img/accomodation_img/${avo.accomodationImg}" alt="숙소이미지"></a>
                            <button id="delBtn" onclick="adel('${avo.no}');"><img src="${root}/static/img/mypage/favorites/삭제버튼아이콘.png" alt="삭제버튼"></button>
                            <br>
                            <div>${avo.accomodationName}</div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <!-- 기념품 -->
                <span id="small-title">기념품</span>
                <hr>
                <div id="souvenir-area">
                    <c:forEach var="svo" items="${svoList}">
                        <div id="souvenir-wrap">
                            <input type="hidden" value="${svo.no}" name="no">
                            <a href="${root}/order/souvenir?name=${svo.souvenirName}"><img src="${root}/static/img/souvenir/${svo.souvenirImg}" alt="기념품이미지"></a>
                            <button id="delBtn" onclick="sdel('${svo.no}');"><img src="${root}/static/img/mypage/favorites/삭제버튼아이콘.png" alt="삭제버튼"></button>
                            <br>
                            <div>상품명 : ${svo.souvenirName}</div>
                            <div>상품가격 : ${svo.souvenirPrice}원</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div id="page-area">
				<c:if test="${pv.currentPage > 1 }">
				<a id="btn01" href="${root}/mypage/favorites?page=${pv.currentPage-1}">이전</a>
				</c:if>
				<c:forEach begin="${pv.startPage}" end="${pv.endPage}" var="i">
					<a id="btn02" href="${root}/mypage/favorites?page=${i}">${i}</a>
				</c:forEach>
				<c:if test="${pv.currentPage < pv.maxPage}">
				<a id="btn01" href="${root}/mypage/favorites?page=${pv.currentPage+1}">다음</a>
				</c:if>
			</div>
        </div>
    </div>
</body>

<script>

function cdel(no) {
        const result = confirm("삭제하시겠습니까?");
        if (!result) {
            return;
        }
        location.href = '${root}/mypage/favorites/del?no=' + no;
    }

    function adel(no) {
        const result1 = confirm("삭제하시겠습니까?");
        if (!result1) {
            return;
        }
        location.href = '${root}/mypage/favorites/del?no=' + no;
    }
    function sdel(no){
        const result2 = confirm("삭제하시겠습니까?");
        if(!result2){
            return;
        }
        location.href='${root}/mypage/favorites/del?no=' + no;
    }

</script>

</html>