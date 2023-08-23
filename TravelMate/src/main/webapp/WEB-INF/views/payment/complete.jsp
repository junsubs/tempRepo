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
        margin-top: 1000px;
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

#btns > button{
    width: 200px;
    height: 40px;
    
}

#btns{
  display: flex;
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
			  <h1 id="h1">결제가 완료되었습니다</h1>
			</div>
		  </div>
		  <br>
		  <br>
		  <br>
		  <br>
		  <div id="btns">
			<button type="button" id="btn01" class="btn btn-success">홈으로</button>
			<button type="button" id="btn02" class="btn btn-success">주문내역 확인하기</button>
			<button type="button" id="btn03" class="btn btn-success">쇼핑 계속하기</button>
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

	//주문내역 확인 버튼 클릭시 마이페이지->주문내역
	const btn02 = document.querySelector('#btn02');
	btn02.addEventListener('click' , (event) => {
		location.href = '${root}/mypage/orderList';
	});
	
	//쇼핑 계속하기 버튼 클릭시 상품 리스트
	const btn03 = document.querySelector('#btn03');
	btn03.addEventListener('click' , (event) => {
		location.href = '${root}/souvenir/list';
	});

</script>