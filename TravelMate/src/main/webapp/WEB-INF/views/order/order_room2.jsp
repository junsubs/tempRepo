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
    
    #start{
        width: 1390px;    
        margin: auto;
    }

    #btns{
        width: 50%;
        height: 40px;
    }

    #supbox{
        margin: auto;
        
        height: 175px;
        display: grid;
        grid-template-columns: 1fr 2fr;
    }

    #boxx{
        margin-left: 50px;
    }

    #box{
        margin-left: 0px;
        width: 600px;
        height: 280px;
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
        margin-left: 50px;
        
    }

    #agree{
        margin-left: 50px;
        border: 1px solid black;
    }

    #agree_detail{
        margin-left: 50px;
        border: 1px solid black;
    }

    #btns > button{
        width: 200px;
        height: 40px;
    }

    #btns{
        margin: auto;
    }

    #name{
        margin: auto;
    }

    #pricetotal{
        width: 500px;
        height: 100px;
        margin-left: 50px;
    }
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
<div id="content">
	<h1 id="h1">숙소 정보<hr></h1>
	<div id="supbox">
        <div id="boxx">
            <img width="380px" src="data:image/jpeg;base64,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" alt="hiltonpusan">
        </div>
        
        <div>
            <div id="box">

                <div class="boxtitle">
                    <h2 id="h2">아난티 힐튼 부산</h1>
                    <br>
                    <br>
                    <br>
                    <h4 id="h4">·이용일 2023.05.01(월) - 2023.05.02(화)</h3>
                    <br>                    
                    <h4 id="h4">·선택 객실 트윈 프리미엄 룸 (마운틴 뷰)</h3>
                    <br>                    
                    <h4 id="h4">·객실 수 1</h3>
                    <br>                    
                    <h4 id="h4">·객실 인원 2</h3>
                    <br>
                    <br>               
                </div>

                <div id="btns">
                    <button type="button" class="btn btn-success">후기 보러가기</button>
                </div>
                <br>
            </div>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1 id="h1">예약자 정보<hr></h1>
    <br>
    <div id="ctinfo">
        <h3 id="auto">예약과 관련된 정보를 연락처로 보내드립니다.</h3>
        <h4>회원정보로 예약자 정보 채워넣기</h4>
        <input type="checkbox" id="getInfo">
        <br>

        <h2 id="name">예약자 명</h2>
        <input type="text" id="name" placeholder="이름 입력">

        <br>

        <h2>휴대폰 번호</h2>
        <input type="text" id="phoneno" placeholder="'-' 제외하고 입력">

        <br>

        <h2>이메일 주소</h2>
        <input type="email" id="email" placeholder="example@mail.com">
    </div>
    <br>

    <h1 id="h1">최종 결제 정보<hr></h1>
    <br>
    <div id="pricetotal">
        <h2 id="h2">총 결제 금액</h2><h2>KRW 354,000<hr></h2>
    </div>

    <div id="agree">
        <span>약관 동의칸</span>
        <div id="dirrhks">약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용약관상세내용</div>
        <input type="checkbox" name="ruleagree" id="ruleagree">
    </div>

    <div id="agree_detail">
        <span>기타 안내사항(특정 기간에 추가 요금 발생 등)</span>
        <div id="rlxk">기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용기타안내사항내용</div>
        <input type="checkbox" name="ruleplusagree" id="ruleplusagree">
    </div>
    <br>
    <br>
    <hr>
    <div id="btns">
        <button type="button" class="btn btn-success">뒤로가기</button>
        <button type="button" class="btn btn-success">관심상품 담기</button>
        <button type="button" class="btn btn-success">결제하기</button>
    </div>
</div>
</body>
</html>