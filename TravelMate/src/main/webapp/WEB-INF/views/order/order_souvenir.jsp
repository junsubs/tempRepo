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



#first, #second, #third, #third2{
  display: flex;
  height: 50px;
}

#first > :nth-child(1), #second >:nth-child(1), #third >:nth-child(1){
    background-color: #39ec93;
    width: 25px;
    height: 50px;
}

#first > :nth-child(2), #second > :nth-child(2), #third > :nth-child(2){
    width: 100%;
    line-height: 50px;   
}

#second2, #first2, #third2{
  width: 100%;
  height: 200px;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: lightgray;
}

#first2, #third2{
  display: grid;
  grid-template-columns: 1fr 5fr;
}

#third2{height: 100px;}

#btn01{
  margin-top: 50px;
  margin-right: 250px;
  margin-left: 250px;
  margin-bottom: 50px;
}

#btn02{margin-right: 300px;}

table{
    height: 200px;
}

#btn01, #btn02, #btn03, #btn04{
  background-color: #73D38E;
  border: 0;
  padding: 15px 25px; 
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

</style>
</head>
<body>
   <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
        
        
	<div id="shape">

      <div id="first">
        <div></div>
        <div>
          <h3>상품정보<hr></h3>
        </div>
      </div>

      <div id="first2">
        <div>
          <h2><img src="${root}/static/img/souvenir/${vo.title}" alt="ASD" width="220px" height="200px"  ></h2>
        </div>
        <div>
        <br>
          <h2>상품 이름: ${vo.name}</h2><br>
          <h2>상품 상세: ${vo.content}</h2><br>
          <h2>상품 가격: ${vo.price}원</h2><br>
          <h2>상품 지역: ${vo.local}</h2>
        </div>
      </div>

      <div id="second">
        <div></div>
        <div>
          <h3>주문 정보<hr></h3>
        </div>
      </div>

	  <form action="${root}/pay/souvenir" method="post">
	      <div id="second2">
	      <br>
	        <table>
	            <tr>
	                <td>예약자 명:</td>
	                <td><input type="text" name="mname" placeholder="이름입력" >
	            </tr>
	            <tr>
	                <td>휴대폰 번호:</td>
	                <td><input type="text" name="phone" placeholder=" ‘ - ’ 제외하고 입력">
	            </tr>
	            <tr>
	                <td>배송 받을 주소:</td>
	                <td><input type="text" name="address" placeholder="ex)경기도 용인시 수지구 동천로 27번길 5" style="width: 400px;"></td>
	            </tr>
	        </table>
	      </div>
	
	      <div id="third">
	        <div></div>
	        <div>
	          <h3>주문 정보<hr></h3>
	        </div>
	      </div>
	  
	      <div id="third2">
	        <div>
	            구성 
	        </div>
	        <div>
	            <input type="number" name="cnt" value="1" id="num" onclick="cal();">
	        </div>
	    
	      </div>
	      <b>총 결제 금액:  <input type="number" id="num2" name="totalPrice" value="${vo.price}"> 원</b>
	      <c:if test="${loginMember != null }">
			<button id="btn03" type="submit">결제하기</button>
	      </c:if>
	      <br><br><br>
		
		<input type="hidden" value="${vo.no}" name="no">
	
	      <hr>
		</form>
	        <button id="btn02" onclick="favorite();">관심상품 담기</button>
	        <button id="btn01" onclick="backPage()">뒤로가기</button>

        
    </div>
        
        
  

   

    
</body>
</html>

<script>

  const arr = document.querySelectorAll('input[id=num]');
  const arr2 = document.querySelectorAll('input[id=num2]');
  
  function cal(){
    arr2[0].value = arr[0].value * ${vo.price};
  }
  
  function backPage() {
    location.href="${root}/souvenir/list";
  }
  
  function favorite(){
	  if(${loginMember == null}){
			alert("로그인 하고 오세요")	  
			return;
		  }else{
			  alert("상품이 등록되었습니다.");
		  }
	  location.href="${root}/favorite?no=${vo.no}&name=${vo.name}";
	 
  }

  
</script>

