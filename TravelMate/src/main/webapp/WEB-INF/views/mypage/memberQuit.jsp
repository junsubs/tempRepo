<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<style>

    
    #content{
         position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 420px;
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

    #del-info{
        position: absolute;
        width: 1300px;
        height: 1000px;
        left: 100px;
        top: 230px;
        display: grid;
        grid-template-rows: 3fr 1fr;
    }
    
    #del-info-area-box{
        width: 1300px;
        border-radius: 30px;
        border: 1px dashed black;
        height: 90%;
        margin: auto;
        font-size: 30px;
    }

    #del-input-info {
        margin-left: 30px;
        margin-top: 20px;
        font-size: 40px;
        font-weight: bold;
    }

    #del-wrap {
        margin-top: 10px;
        width: 1300px;
        height: 500px;
    }

    #del-info02{
        font-size: 30px;
        margin-top: 10%;
        margin-left: 30px;
    }

    #btn-area{
        margin: auto;
        margin-top: 10%;
        place-items: center center;
    }
    
    #btn-area > input{
        width: 200px;
        height: 60px;
        font-size: 30px;
        border-spacing: 20px;
    }


    #btn01 {
        background-color: #73D38E;
        padding: 0px 25px; 
        border: 0;
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
        width: 140px;
        height: 50px;
        font-size: 20px;
        margin-top: 50px;
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
            <a>회원탈퇴</a>
        </div>
        <div id="del-info">
            <div id="del-info-area-box">
                    <div id="del-wrap">
                    <div id="del-input-info" class="bi bi-exclamation-square">  회원 탈퇴 신청 전 아래 안내사항을 꼭 확인해주세요.</div>
                    <div id="del-info-wrap">
                        <div class="bi bi-exclamation-square" id="del-info02">  사용한 아이디는 재사용 및 복구가 불가능합니다.</div>
                        <div class="bi bi-exclamation-square" id="del-info02"> 탈퇴 시, 데이터는 지워지지 않으니 미리 삭제하시길 바랍니다. <br>   (게시글, 구매내역 등)</div>
                    </div>
                    </div>
            </div>
            <div id="btn-area">
                <input type="submit" id="btn01" value="확인" onclick="quit();"></input>
                <input type="button" id="btn01" value="취소"></input>
            </div>
        </div>
    </div>
        

</body>
<script>

    // 탈퇴
    function quit() {
            const result = confirm('탈퇴하시겠습니까?');
            if(result){
                location.href='${root}/mypage/member/quit';
            }
        }
</script>
</html>