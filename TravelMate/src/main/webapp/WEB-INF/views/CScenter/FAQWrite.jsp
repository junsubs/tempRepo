<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


#content01{
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

    #content01>#first-content>hr:nth-child(2){
        height: 1px;
        width: 1300px;
        background-color: rgb(116, 116, 116);
        border: 0px solid rgb(65, 65, 65);
        position: absolute;
        left: 100px;
        top: 180px;
    }

    #content01>#first-content>a:nth-child(3){
        position: absolute;
        left: 160px;
        top: 135px;
        color: black;
        font-size: 28px;
        font-weight: bold;
    }

    #title-wrap{
        width: 60%;
        margin-left: 50px;
        display: grid;
        grid-template-columns: 1fr 3fr;
        font-size: 20px;
    }

    #word {
        width: 50px;
        display: flex;
        align-items: center;
        justify-self: center;
        text-align: center;
        font-weight: bold;
    }

    #inquery-input{
        position: absolute;
        width: 1300px;
        height: 1000px;
        left: 100px;
        top: 230px;
        display: grid;
        grid-template-rows: 1fr 3fr 1fr;
        justify-content: center;
        align-items: center;
        margin: auto;
        font-size: 30px;
    }

    #title-wrap > input[name=title] {
        width: 1200px;
        height: 50px;
        font-size: 20px;
        border: none;
        border-bottom: 1px solid black;
    }

    #content-wrap > textarea {
        width: 1200px;
        height: 700px;
        font-size: 20px;
        border: none;
        border-bottom: 1px solid black;
        border-top: 1px solid black;
        resize: none;
    }

    #content-wrap {
        width: 60%;
        margin-left: 50px;
        display: grid;
        grid-template-columns: 1fr 3fr;
        font-size: 20px;
    }

    #btn-area{
        margin: auto;
        margin-top: 5%;
        place-items: center center;
    }
    
    #btn-area > input{
        width: 180px;
        height: 40px;
        font-size: 20px;
        border-spacing: 20px;
    }


    #btn01 {
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
        width: 150px;
        height: 40px;
        font-size: 20px;
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

    <%@ include file="/WEB-INF/views/common/cs-header.jsp" %>
    
    <!-- 내용영역 --> 
   <div id="content01">
    <div id="first-content">
        <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
        <hr>
        <a>자주 묻는 질문 추가</a>
    </div>
        <form action="${root}/cs/faq/write" method="POST">
	        <div id="inquery-input">
	            <div id="title-wrap">
	                    <div id="word">질문</div>
	                    <input type="text" name="title" >
	                </div>
	                <div id="content-wrap">
	                    <span id="word">답변</span>
	                    <textarea name="answer" placeholder="답변을 작성해주세요."></textarea>
	                </div>
	                <div id="btn-area">
	                    <!-- 작성 버튼 누르면 자주묻는질문 목록 페이지로 보내주기 -->
	                    <input type="submit" id="btn01" value="작성하기"></input>
                    </div>
                </div>
            </div>
        </form>

</body>
<script>

</script>
</html>