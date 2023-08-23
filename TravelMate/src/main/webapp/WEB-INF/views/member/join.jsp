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
        bottom: 1500px;
        left: 300px;
        margin-top: 800px;  /*이녀석도*/
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
        height: 1000px;
        left: 430px;
        top: 230px;
        margin-top: 50px;
        display: grid;
    }
    

    #btn01 ,#btn02 , #btn00 , #btn03 {
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
        font-size: 2em;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   

    /* ----------------------------------------------------------------------------------------- */


	#agree label{
		
		margin-top: 19px;
		margin-left: 30px;
	}

	input[type="checkbox"]{
        display: none;
      }

	input[type="checkbox"] + label{
        display: inline-block;
        width: 30px;
        height: 30px;
        border:3px solid #707070;
        position: relative;
      }

     

	 input[id="check1"]:checked + label::after{
        content:'✔';
        font-size: 25px;
        width: 30px;
        height: 30px;
        text-align: center;
        position: absolute;
        left: 0;
        top:0;
      }

	  input[id="check2"]:checked + label::after{
        content:'✔';
        font-size: 25px;
        width: 30px;
        height: 30px;
        text-align: center;
        position: absolute;
        left: 0;
        top:0;
    } 

	  input[id="check3"]:checked + label::after{
        content:'✔';
        font-size: 25px;
        width: 30px;
        height: 30px;
        text-align: center;
        position: absolute;
        left: 0;
        top:0;
      }

	  input[id="check4"]:checked + label::after{
        content:'✔';
        font-size: 25px;
        width: 30px;
        height: 30px;
        text-align: center;
        position: absolute;
        left: 0;
        top:0;
      }

	  input[id="check5"]:checked + label::after{
        content:'✔';
        font-size: 25px;
        width: 30px;
        height: 30px;
        text-align: center;
        position: absolute;
        left: 0;
        top:0;
      }

	form {
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		align-items: center;
	}
    
	#agree{
		width: 500px;
		height: 300px;
		border-radius: 20px;
		background-color: rgb(168, 235, 171);
	}

 
</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	
	<!-- 내용영역 --> 
   
            <div id="content">
				
                <div id="first-content">
                    <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
                    <hr>
                    <a>회원가입</a>
                </div>
				
			<form action="${root}/join" method="POST" id="join-form">
       

                <div id="edit-area">  <span id="idSpan"></span>
                    아이디<input type="text" name="memberId" placeholder="아이디를 입력하세요" maxlength="100" size="35" > <input id="btn01" type="button" value="아이디 중복확인">
					<br>
					비밀번호<input type="password" name="memberPwd" placeholder="비밀번호를 입력하세요" maxlength="100" size="35" > <span id="passwordMessage"></span>
					<br>
					비밀번호 확인<input type="password" name="pwdChk" placeholder="비밀번호 확인" maxlength="100" size="35" > <input id="btn02" type="button" value="비밀번호 일치확인"> 
					<br>
					E-MAIL<input type="text" name="memberEmail" placeholder="이메일을 입력하세요" maxlength="100" size="35" >  <input id="btn00" type="button" value="이메일 확인"> <span id="message"></span>
					<br>
					닉네임<input type="text" name="memberNick" placeholder="닉네임을 입력하세요" maxlength="100" size="35" >  
					<br>
                    주소<input type="text" name="memberAddress" placeholder="주소를입력하세요" maxlength="100" size="35" >  
					<br>
          <select name="memberCategory" id="btn01">
            <option value="2">일반 회원</option>
            <option value="3">판매자 </option>
          </select>
					<div id="agree">
	
						<div>
							<input type="checkbox" id="check1"> 
							<label for="check1"></label>
							<span style="font-size: 1.5em;">[필수] 만 19세 이상입니다</span>
						</div>
	
						<div>
							<input type="checkbox" id="check2">
							<label for="check2"></label>
							<span style="font-size: 1.5em;">[필수]트레블 메이트 이용 약관 동의</span>
						</div>

						<div>
							<input type="checkbox" id="check3">
							<label for="check3"></label>
							<span style="font-size: 1.5em;">[필수]개인정보 수집에 대한 동의</span>
						</div>

						<div>
							<input type="checkbox" id="check4">
							<label for="check4"></label>
							<span style="font-size: 1.5em;">[필수]트레블 메이트 이용 약관 동의</span>
						</div>
	
						<div>
							<input type="checkbox" id="check5">
							<label for="check5"></label>
							<span style="font-size: 1.5em;">[선택] 광고성 정보 수신 동의</span>
						</div>
					  
					</div>
					
					<input id="btn01" type="submit" value="동의하고 가입하기">
                </div>

			</form>

		</div>


</body>
</html>

<script>
    

    //아이디 중복 확인

    $(document).ready(function() {
  $('#btn01').click(function() {
    var memberId = $('input[name="memberId"]').val();

    const idSpan = document.querySelector('#idSpan');

    $.ajax({
      url: '${root}/join',  
      method: 'POST',
      data: { 'memberId' : memberId },
      success: function(response) {
        // 서버로부터의 응답 처리
        if (response === 'duplicate') {
          // 중복된 아이디
          idSpan.textContent = '이미 사용중인 아이디 입니다.';
          idSpan.style.color = 'red';
        } else {
          // 사용 가능한 아이디
          idSpan.textContent = '사용가능한 아이디 입니다.';
          idSpan.style.color = 'green';
        }
      },
      error: function(xhr, status, error) {
       
        console.error(error);
      }
    });
  });
});

    // //닉네임 중복 확인
    // $(document).ready(function() {
    // $('#btn03').click(function() {
    //     var memberNick = $('input[name="memberNick"]').val();

    //     const nickSpan = document.querySelector('#nickSpan');

    //     $.ajax({
    //     url: '${root}/join',  
    //     method: 'POST',
    //     data: { 'memberNick' : memberNick },
    //     success: function(response) {
    //         // 서버로부터의 응답 처리
    //         if (response === 'duplicate') {
    //         // 중복된 아이디인 경우 처리 로직
    //         nickSpan.textContent = '이미 사용중인 닉네임 입니다.';
    //         nickSpan.style.color = 'red';
    //         } else {
    //         // 사용 가능한 아이디인 경우 처리 로직
    //         nickSpan.textContent = '사용 가능한 닉네임 입니다.';
    //         nickSpan.style.color = 'green';
    //         }
    //     },
    //     error: function(xhr, status, error) {
           
    //         console.error(error);
    //     }
    //     });
    // });
    // });


    //이메일체크
    const emailInput = document.querySelector('input[name="memberEmail"]');
    const checkButton = document.getElementById('btn00');
    const messageElement = document.getElementById('message');

    // 이메일 체크 
    function validateEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
    }

    // 버튼 클릭 이벤트 처리
    checkButton.addEventListener('click', function() {
    const email = emailInput.value;

    if (validateEmail(email)) {
        messageElement.textContent = '유효한 이메일 주소입니다.';
        messageElement.style.color = 'green';
    } else {
        messageElement.textContent = '유효하지 않은 이메일 주소입니다.';
        messageElement.style.color = 'red';
    }
    });

    //비밀번호 일치확인
    const passwordInput = document.querySelector('input[name="memberPwd"]');
    const passwordConfirmInput = document.querySelector('input[name="pwdChk"]');
    const pwdCheckButton = document.getElementById('btn02');
    const pwdMessageElement = document.getElementById('passwordMessage');

    // 비밀번호 일치 확인 함수
    function checkPasswordMatch() {
    const password = passwordInput.value;
    const confirmPassword = passwordConfirmInput.value;

    if (password === confirmPassword) {
        pwdMessageElement.textContent = '비밀번호가 일치합니다.';
        pwdMessageElement.style.color = 'green';
    } else {
        pwdMessageElement.textContent = '비밀번호가 일치하지 않습니다.';
        pwdMessageElement.style.color = 'red';
    }
    }

    pwdCheckButton.addEventListener('click', checkPasswordMatch);









</script>