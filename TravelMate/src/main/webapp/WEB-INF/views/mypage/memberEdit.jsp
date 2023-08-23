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

    #edit-area{
        position: absolute;
        width: 1300px;
        height: 1000px;
        left: 100px;
        top: 230px;
        display: grid;
        grid-template-rows: 3fr 1fr;
    }
    
    #edit-input-box{
        width: 1100px;
        border-radius: 30px;
        border: 1px solid black;
        height: 80%;
        margin: auto;
        font-size: 20px;
        display: flex;
        justify-content:center;
        align-items: center;
    }

    .edit-input-area {
	    width: 1100px;
        border-collapse: separate;
        border-spacing: 50px;
        padding-left: 20px;
    }
    
    .edit-input-area input {
        width: 500px;
        height: 50px;
        background-color: #d8f1c7;
        border: none;
        margin-left: 30px;
        font-size: 20px;
    }

    .edit-input-area button {
        margin-top: 10px;
        font-size: 22px;
        width: 200px;
        height: 50px;
    }
    

	#btn02 {
		background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: black;
        border-radius: 6px;
        width: 150px;
        height: 40px;
        font-size: 18px;
        margin-left: 80px;
        vertical-align: middle;
	} 

    #btn-area{
        width: 1300px;
        margin: auto;
        margin-top: 5%;
        place-items: center center;
        display:grid;
        grid-template-columns: 9fr 1fr;
    }
    
    /* #btn-area > input{
        width: 200px;
        height: 60px;
        font-size: 30px;
        border-spacing: 40px;
    } */


    #btn01 {
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: black;
        border-radius: 6px;
        width: 140px;
        height: 40px;
        font-size: 18px;
        margin-left: 80px;
        position: absolute;
        left: 500px;
        top: 650px;
        vertical-align: middle;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   
    
    
    input {
        font-size: 30px;
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
            <a>회원정보수정</a>
        </div>
        <div id="edit-area">
            <form action="${root}/mypage/member/edit" method="POST">
                <div id="edit-input-box">
                        <!-- table 로 만들기 -->
                        <table class="edit-input-area">
                            <tr>
                              <td><span>아이디</span></td>
                              <td><input type="text" value="${loginMember.id}" readonly></td>
                            </tr>
                            <tr>
                              <td><span>닉네임</span></td>
                              <td>
                                <input type="text" name="memberNick" value="${loginMember.nick}"> 
                            </td>
                            </tr>
                                                        <tr>
                              <td><span>주소</span></td>
                              <td><input type="text" name="address" value="${loginMember.address}"></td>
                            </tr>
                            <tr>
                              <td><span>이메일</span></td>
                              <td>
                              <input type="text" name="email" value="${loginMember.email}">
                              <button id="btn02" type="button">이메일 확인</button>
                              <br>
                              	<span id="message"></span>
                              	</td>
                            </tr>
                        </table>
                </div>
                  <div id="btn-area">
                       <input type="submit" id="btn01" value="수정하기"></input>
                   </div>
            </form>
        </div>
    </div>
            
</body>

<script>
	
    
  //이메일체크
    const emailInput = document.querySelector('input[name="email"]');
    const messageElement = document.getElementById('message');
    const checkEmailBtn = document.getElementById('btn02');
    const submitBtn = document.getElementById('btn01');

    // 이메일 체크 
    function validateEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
    }

    // 버튼 클릭 이벤트 처리
    checkEmailBtn.addEventListener('click', function checkEmail() {
    const email = emailInput.value;

    if (validateEmail(email)) {
        messageElement.textContent = '유효한 이메일 주소입니다.';
        messageElement.style.color = 'green';
        submitBtn.disabled=false;
        } 
        else {
        messageElement.textContent = '유효하지 않은 이메일 주소입니다.';
        messageElement.style.color = 'red';
        submitBtn.disabled=true;
    }
    });


</script>


</html>