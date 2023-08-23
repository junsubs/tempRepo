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
	position: relative;
    width: 1390px;
    height: 100%;
    bottom: 500px;
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
  width: 100%;
  height: 150px;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: lightgray;
}

#second2{
  width: 100%;
  height: 200px;
  display: grid;
  grid-template-columns: 1fr 5fr;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: lightgray;
}

#btn01, #btn03{
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

textarea{
	background: lightgray;
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
   <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
    

    <div id="shape">

      <div id="first">
        <div></div>
        <div>
          <h3>여행자 확인 및 결제<hr></h3>
        </div>
      </div>

      <div id="first2">
        <textarea readonly="readonly">제1조(목적)
이 약관은 주식회사SEMI2조(전자상거래 사업자)가 운영하는 TRAVELMATE(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리.의무 및 책임사항을 규정함을 목적으로 합니다.
※「PC통신, 무선 등을 이용하는 전자상거래에 대해서도 그 성질에 반하지 않는 한 이 약관을 준용합니다.」

제2조(정의)
① “몰”이란 주식회사 SEMI2조가 재화 또는 용역(이하 “재화 등”이라 함)을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 등을 거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버몰을 운영하는 사업자의 의미로도 사용합니다.
② “이용자”란 “몰”에 접속하여 이 약관에 따라 “몰”이 제공하는 서비스를 받는 회원 및 비회원을 말합니다.
③ ‘회원’이라 함은 “몰”에 회원등록을 한 자로서, 계속적으로 “몰”이 제공하는 서비스를 이용할 수 있는 자를 말합니다.
④ ‘비회원’이라 함은 회원에 가입하지 않고 “몰”이 제공하는 서비스를 이용하는 자를 말합니다.

제3조 (약관 등의 명시와 설명 및 개정)
① “몰”은 이 약관의 내용과 상호 및 대표자 성명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 전화번호.모사전송번호.전자우편주소, 사업자등록번호, 통신판매업 신고번호, 개인정보보호책임자등을 이용자가 쉽게 알 수 있도록 00 사이버몰의 초기 서비스화면(전면)에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.
② “몰은 이용자가 약관에 동의하기에 앞서 약관에 정하여져 있는 내용 중 청약철회.배송책임.환불조건 등과 같은 중요한 내용을 이용자가 이해할 수 있도록 별도의 연결화면 또는 팝업화면 등을 제공하여 이용자의 확인을 구하여야 합니다.
③ “몰”은 「전자상거래 등에서의 소비자보호에 관한 법률」, 「약관의 규제에 관한 법률」, 「전자문서 및 전자거래기본법」, 「전자금융거래법」, 「전자서명법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「방문판매 등에 관한 법률」, 「소비자기본법」 등 관련 법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
④ “몰”이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 몰의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 “몰“은 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다.
⑤ “몰”이 약관을 개정할 경우에는 그 개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이 그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의 공지기간 내에 “몰”에 송신하여 “몰”의 동의를 받은 경우에는 개정약관 조항이 적용됩니다.
⑥ 이 약관에서 정하지 아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률, 약관의 규제 등에 관한 법률, 공정거래위원회가 정하는 전자상거래 등에서의 소비자 보호지침 및 관계법령 또는 상관례에 따릅니다.
여러분의 정보는 제껍니다. 제가 마음대로 할 수 있는겁니다.</textarea>
<div><input type="checkbox">약관에 동의합니다.</div>
      </div>

      <div id="second">
        <div></div>
        
        <div>
          <h3>여행자 확인 및 결제<hr></h3>
        </div>
      </div>

      <div id="second2">
        <div>
          <img src="${root}/static/img/accomodation_img/${vo.title}" alt="ASD" width="400px" height="200px"  >
        </div>
        <div>
        <br>
          <h4>예약자 이름: ${vo.mname}</h4><br>
          <h4>예약자 연락처: ${vo.ph}</h4><br>
          <h4>이메일: ${vo.address }</h4>
        </div>
      </div>

      <b>총 결제 금액: ${vo.price} 원</b><br><br><br><br><br>

      <div id="second">
        <div></div>
        
        <div>
          <h2>결제수단</h2>
        </div>
      </div>

      <hr>
      
      <div>
      	
        <button id="btn01" class="btn1" >신용카드</button>
        <button id="btn02" class="btn2" >무통장입금</button>
        <button id="btn03" class="btn3" >카카오톡결제</button>
        <button id="btn04" class="btn4" >상품권결제</button>
      </div>

      <hr>

  
    </div>
</body>
</html>

<script>

	const btn01 = document.querySelector(".btn1");
  btn01.addEventListener('click' , (event) => {
    location.href = '${root}/payment/creditCard';
  });
	
  const btn02 = document.querySelector(".btn2");
  btn02.addEventListener('click' , (event) => {
    location.href = '${root}/payment/bankAccount';
  });
	
  const btn03 = document.querySelector(".btn3");
  btn03.addEventListener('click' , (event) => {
    location.href = '${root}/payment/kakaoTalk';
  });
	
  const btn04 = document.querySelector(".btn4");
  btn04.addEventListener('click' , (event) => {
    location.href = '${root}/payment/giftCard';
  });


</script>