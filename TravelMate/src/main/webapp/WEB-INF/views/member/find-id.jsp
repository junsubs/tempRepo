<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        @import url("http://fonts.googleapis.com/earlyaccess/nanumgothic.css");
	
	
	
	body {
	    width:100%;
	    height:100%;
	    margin: 0;
  		padding-top: 200px;
  		padding-bottom: 40px;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
	}

    #content{
        position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 220px;
	    bottom: 250px;
    }
	
    .card {
        margin: 0 auto;
        float: none; 
        margin-bottom: 10px; 
	}

    .input-box{
        position:relative;
        margin:10px 0;
        margin-left: 100px;
    }

    .input-box > input{
        background:transparent;
        border:none;
        border-bottom: solid 1px #ccc;
        padding:20px 0px 5px 0px;
        font-size:14pt;
        width:100%;
    }

    input:placeholder-shown + label{
        color:#aaa;
        font-size:14pt;
        top:15px;
    }

    input:focus + label, label{
        color:#8aa1a1;
        font-size:10pt;
        pointer-events: none;
        position: absolute;
        left:0px;
        top:0px;
        transition: all 0.2s ease ;
        -webkit-transition: all 0.2s ease;
        -moz-transition: all 0.2s ease;
        -o-transition: all 0.2s ease;
    }

    input:focus, input:not(:placeholder-shown){
        border-bottom: solid 1px #8aa1a1;
        outline:none;
    }
    #btn-Yes{
        background-color: #6ba94494;
        border: none;
        width: 637px;
        height: 50px;
        font-size: 20px;
        margin-left: 100px;
        margin-top: 30px;
    }
	
	.login{
  		position: relative;
  		height: auto;
  		-webkit-box-sizing: border-box;
     	-moz-box-sizing: border-box;
        	 box-sizing: border-box;
  		padding: 10px;
  		font-size: 16px;
        width: 757px;
	}
   
    .card-title{
        margin-left: 30px;
    }

    .links{
        width: 757px;
        height: 40px;
        text-align: center;
        margin-top: 20px;
        margin-bottom: 10px;
        margin-left: 230px;
    }
    a{ 
    	color: #f58b34; text-decoration: none; 
    }
    .check{
    	color : red;
    }

 
</style>
</head>
<body >

    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	
	<!-- 내용영역 --> 
            <div id="content">
                
				
                    <div class="card align-middle" style="width:25rem;">
                        <div class="card-title" style="margin-top:30px;">
                            
                            <h2 class="card-title" style="color:#75ef6dae;"><img src="${root}/static/img/loginImg.png"/></h2>
                        </div>
                      <form action="${root}/find-id" class="login" method="POST">
                        
                        <div class="card-body">
                  
                            <div class="input-box">
                                <input id="usernick" type="text" name="memberNick" >
                                <label for="usernick">닉네임</label>
                            </div>
                
                            <div class="input-box">
                                <input id="password" type="password" name="memberPwd">
                                <label for="password">비밀번호</label>
                            </div>

                            <div class="input-box">
                                <input id="email" type="email" name="memberEmail">
                                <label for="password">이메일</label>
                            </div>
                        <input id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" value="아이디 찾기">
                      </form>
                    
                        </div>
                        <div class="links">
                            <a href="${root}/find-pwd">비밀번호 찾기</a> | <a href="${root}/login">로그인</a> | <a href="${root}/join">회원가입</a>
                
                        </div>
                    </div>
		</div>


</body>
</html>

 <c:if test="${not empty loginMember }">
	<script>
	  alert('${loginMember.id}');
	</script>
 </c:if>
