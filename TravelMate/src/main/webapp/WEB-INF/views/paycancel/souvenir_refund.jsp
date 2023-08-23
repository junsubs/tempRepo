<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<style>

#shape{
	margin-top : 600px;
	position: relative;
    width: 1390px;
    height: 100%;
    bottom: 900px;
    left: 400px;
  
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
    line-height: 50px;   
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

#btn01{
  margin-top: 50px;
  margin-right: 400px;
  margin-left: 400px;
  margin-bottom: 50px;
  
}
#btn01, #btn02, #btn03, #btn04{
  background-color: #73D38E;
  border: 0;
  padding: 15px 25px; //버튼 크기 조절
  display: inline-block;
  text-align: center;
  color: white;
  border-radius: 6px;
}

#btn01:hover, #btn02:hover, #btn03:hover, #btn04:hover  {background-color: #80C49D;}
#btn01:active, #btn02:active, #btn03:active, #btn04:active {
  box-shadow: 0 0 0 1px #82c8a0 inset,
        0 0 0 2px rgba(255,255,255,0.15) inset,
        0 0 0 1px rgba(0,0,0,0.4);
}

#title-area{
	width: 800px;
	height: 50px;
}

#text-area{
	width: 800px;
	height: 300px;
}

#text-area2{
	width: 1115px;
	height: 150px;
}

#h1{
	font-size: xx-large;
	font: bold;
}

</style>
</head>
<body>
    
    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>

    <div id="shape">

      <div id="first">
        <div></div>
        <div>
          <h3>약관 동의 및 결제취소<hr></h3>
        </div>
      </div>
	<form action="${root}/souvenir/refund" method="post">
      <div id="first2">
        <h2>청약철회 약관 동의칸</h2>
        <br>
        <textarea id="text-area2" readonly="readonly">
        	주문 취소 및 반품
			 일반적으로 소비자는 자신이 체결한 전자상거래 계약에 대해 그 계약의 내용을 불문하고 그 청약철회 및 계약해제의 기간(통상 7일) 내에는 청약철회 등을 자유롭게 할 수 있습니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제1항).
			※ 소비자에게 불리한 규정(주문 취소나 반품 금지 등)이 포함된 구매계약은 효력이 없습니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제35조).
			 하지만, 다음 어느 하나에 해당하는 경우에는 인터넷쇼핑몰 사업자의 의사에 반(反)해서 주문 취소 및 반품을 할 수 없습니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제2항 본문 및 「전자상거래 등에서의 소비자보호에 관한 법률 시행령」 제21조).
			1. 소비자의 잘못으로 물건이 멸실(물건의 기능을 할 수 없을 정도로 전부 파괴된 상태)되거나 훼손된 경우(다만, 내용물을 확인하기 위해 포장을 훼손한 경우에는 취소나 반품이 가능)
			2. 소비자가 사용해서 물건의 가치가 뚜렷하게 떨어진 경우
			3. 시간이 지나 다시 판매하기 곤란할 정도로 물건의 가치가 뚜렷하게 떨어진 경우
			4. 복제가 가능한 물건의 포장을 훼손한 경우
			5. 용역 또는 「문화산업진흥 기본법」 제2조제5호의 디지털콘텐츠의 제공이 개시된 경우. 다만, 가분적 용역 또는 가분적 디지털콘텐츠로 구성된 계약의 경우에는 제공이 개시되지 않은 부분은 제외
			6. 소비자의 주문에 따라 개별적으로 생산되는 상품 또는 이와 유사한 상품 등의 청약철회 및 계약해제를 인정하는 경우 인터넷쇼핑몰 사업자에게 회복할 수 없는 중대한 피해가 예상되는 경우로서 사전에 주문 취소 및 반품이 되지 않는다는 사실을 별도로 알리고 소비자의 서면(전자문서 포함)에 의한 동의를 받은 경우
        </textarea>
        <div><input type="checkbox">약관에 동의합니다.</div> 
        <br>
        <br>
        <br>
        <br>
        </div>
        
        <div>
        
      </div>
			<br>
			<br>
      <div id="second">
        <div></div>
    
        
        <div>
        	상품결제번호 :
        	<input type="text" value=${spcvo.payNo} name="payNo" readonly="readonly">
        	<br>
        	<br>
        	<br>
        	<br>
        </div>
       </div>
       <div>
       <br>
       <br>
        	<h1 id="h1">※결제취소사유※</h1>
        	<br>
        	<br>
        	<input id="title-area" type="text" name="title" placeholder="제목을 입력하세요.">
        	<br>
        	<br>
        	
        	<textarea id="text-area" name="content" placeholder="내용을 입력하세요."></textarea>
        	<br>
        	<br>
       </div>
        	
        	
        	
        

      <b>총 결제 금액: ${spcvo.price}</b><br><br><br><br><br>

      <h2>결제를 취소 하시겠습니까?</h2>
      <hr>
        
        <button type="submit" value="submit" id="btn01">결제취소</button>
        <button id="btn02" onclick="backPage();">뒤로가기</button>
        </form>
      </div>
        

      
</body>

<script>

	function backPage() {
    	location.href="${root}/mypage/orderList";
  	}

	
</script>
</html>