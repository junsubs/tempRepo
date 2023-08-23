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
        margin-top: 1100px;
        width: 1390px;
        height: 100%;
        bottom: 1300px;
        left: 400px;
    }

    #h1{
    	font: bolder;
    	font-size: xx-large;
    }
    #h2{
    	font: bolder;
    	font-size: x-large;
    }
    #h3{
    	font: bold;
    	font-size: larger;
    }
    #h4{
    	font: bold;
    	font-size: large;
    }

#shape{
  width: 1300px;
  height: 100%;
  margin: auto;
  border: 0px solid black;
}

#top{
    position: fixed;
    right: 5%;
    
}

#first, #second{
  display: flex;
  height: 50px;
}

#first > :nth-child(1), #second >:nth-child(1){
    background-color: #39ec93;
    width: 25px;
    height: 50px;
}

#first > :nth-child(2), #second > :nth-child(2){
    width: 100%;
    line-height: 20px;   
}

#first2{
  width: 80%;
  height: 150px;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: lightgray;
}

#second2{
  width: 80%;
  height: 200px;
  display: grid;
  grid-template-columns: 1fr 5fr;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: lightgray;
}



#pricetotal{
        width: 500px;
        height: 100px;
    }



#selectbx{
	display: flex;
	margin: auto;
	height: 50px;
	width: 320px;
}

#btn02, #btn01{
	  margin-top: 50px;
	  margin-right: 250px;
	  margin-left: 250px;
	  margin-bottom: 50px;
	}
	
	
	#btn01, #btn02{
  background-color: #73D38E;
  border: 0;
  padding: 15px 25px; 
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
  margin-left: 500px;
}
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/common/product-header.jsp" %>

	<div id="content">

 
		<div id="shape">
	
		  <div id="first">
			<div></div>
			<div>
			  <h1 id="h1">계좌이체 결제<hr></h1>
			</div>
		  </div>

		  <select id="selectbx">
			  <option value="농협">농협 123456-22-123456 예금주:(주)트메</option>
			  <option value="하나은행">하나은행 123-123456-12345 예금주:(주)트메</option>
			  <option value="신한은행">신한은행 123-123-123456 예금주:(주)트메</option>
			  <option value="국민은행">국민은행 123456-12-123456 예금주:(주)트메</option>
			  <option value="우리은행">우리은행 1234-123-123456 예금주:(주)트메</option>
		  </select>
		  
		  <div id="btns">
		  	<div>
			<button type="button" id="btn01" class="btn btn-success">홈으로</button>
			</div>
			<form action="${root}/payment/complete" method="post">
			<input type="hidden" name="reservationno" value="${vo.no}">
			<button type="submit" id="btn02" class="btn btn-success" onclick="success();">결제하기</button>
			</form>
		  </div>
	
		  
		</div>
	  </div>

</body>
</html>
<script>

	//뒤로가기 버튼 클릭시 홈으로 돌아가기
	const btn01 = document.querySelector('#btn01');
  	btn01.addEventListener('click' , (event) => {
      location.href = '${root}/home';
  	});


	function success() {
		alert("결제 완료되었습니다.");
	}
</script>