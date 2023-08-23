<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    
    #content{
        position: relative;
        margin-top: 400px;
        width: 1390px;
        height: 100%;
        bottom: 900px;
        left: 400px;
    }

    #h3{
    	font: bold;
    	font-size: larger;
    }
    
    #start{
        width: 1300px;    
        margin: auto;
    }

    #supbox{
        margin: auto;
        
        height: 175px;
        display: grid;
        grid-template-columns: 1fr 2fr;
    }

    #boxx{
        margin-right: 0px;
    }

    #box{
        margin-left: 50px;
        width: 600px;
        height: 200px;
        background-color: rgba(0, 0, 0, 0.288);
        border: 1px solid black;
        
        display: grid;
        grid-template-rows: 5fr 1fr;

    }

    #rebtn{
        height: 50px;
        width: 200px;
        margin-right: 0px;    	
    }

    #agree{
        width: 90%;
        height: 200px;
        margin: auto;
    }

    #agree_detail{
        width: 90%;
        height: 80px;
        margin: auto;
    }
    
    #ctinfo{
        width: 500px;
        height: 200px;
        margin: auto;
        
    }

    #agree{
        border: 1px solid black;
    }

    #agree_detail{
        border: 1px solid black;
    }

    #btns{
        width: 50%;
        height: 40px;
    }

    #carinclude{
        font: bolder;
        font-size: x-large;
    }
    #name{
        margin: auto;
    }

    #pricetotal{
        width: 500px;
        height: 100px;
        margin: auto;
    }

	#calender{
		width: 160px;
        height: 50px;
		margin-left: 50px;
	}
	
	#btns > button{
    	width : 200px;
    	height: 40px;
    	margin: auto;
    }
    
    #btns{
    	margin: auto;
    }
    
    #px{
    	height: 45px;
    }

    #searchBtn{
        height: 45px;
    }
    
    #maxPeopleSelect{
        height: 45px;
    }
    
    .dlsdnjstn{
        height: 30px;
        margin: auto;
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
	
	<!-- 내용영역 -->
    <div id="content">
	
	<div id="start">
      <div id="first">
        <div></div>
        <div>
          <h3>숙소정보<hr></h3>
        </div>
      </div>

      <div id="first2">
        <div>
			<img src="${root}/static/img/accomodation_img/${vo.title}" alt="ASD" width="100%" height="200px"  >	
        </div>
        <div>
      	  <h2>체크인 : 3:00 PM ~ midnight // 체크아웃:11:00PM</h2><br>
	      <h2 >이름 : ${vo.name}</h2> 
	      <br>                    
	      <h2 >내용 : ${vo.content}</h2> 
          <br>                    
	      <h2>숙소 종류 : ${vo.kind}</h2>
	      <br>
	      <h2>1일 가격 : ${vo.price}</h2>
	      <br>
	      <br>
        </div>
      </div>
	
        </div>
         <div id="start">
    <div id="second">
        <div></div>
        <div>
          <h3>시간 선택<hr></h3>
        </div>
      </div>
    <br>
   <form action="${root}/pay/room" method="post">
	    <div>
			<input type="text" id="calender" name="daterange" value="06/01/2023 - 06/05/2023" />
	    </div>
	    
	    <div id="second">
	        <div></div>
	        <div>
	          <h3>주문 정보<hr></h3>
	        </div>
	      </div>
	
		  
	      <div id="second2">
	        <table>
	            <tr>
	                <td>예약자 명:</td>
	                <td><input type="text" name="mname" placeholder="이름입력" ><br><br>
	            </tr>
	            <tr>
	                <td>휴대폰 번호:</td>
	                <td><input type="text" name="phone" placeholder=" ‘ - ’ 제외하고 입력"><br><br>
	            </tr>
	            <tr>
	                <td>이메일:</td>
	                <td><input type="text" name="address" placeholder="example@mail.com" style="width: 400px;"></td><br><br>
	            </tr>
	        </table>
	      </div>
			
		<input type="hidden" value="${vo.no}" name="no">
		<input type="hidden" value="${vo.price}" name="price">
		<c:if test="${loginMember != null }">
			<button id="btn03" type="submit">결제하기</button>
		</c:if>
	</form>
    
    <hr>
    
    <button id="btn02" onclick="favorite();">관심상품 담기</button>
    <button id="btn01" onclick="backPage()">뒤로가기</button>
    
    </div>
    
   
	</div>
	
</body>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
	
	<script>
	let x;
	
	$(function() {
  		$('input[name="daterange"]').daterangepicker({
    	opens: 'left'
	  	}, function(start, end, label) {
    	console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
    	x = start.format('YYYY-MM-DD') 
    	+ end.format('YYYY-MM-DD');
	console.log(x);
	  });
	});
	
	
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
		  location.href="${root}/room/favorite?no=${vo.no}&name=${vo.name}";
	 
  	 }
</script>
</html>