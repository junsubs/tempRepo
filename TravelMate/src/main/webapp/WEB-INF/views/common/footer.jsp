<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .footer{
      width: 100%;
      height: 150px;
      box-sizing: border-box;
    }

    .info-section{
      width: 400px;
      height: 100%;
      border-right: 1px solid black;
    }

    .small-logo {
      width: 50%;
      height: 40px;
    }

    p ,i {
      margin-left: 10%;
      display: block;
      font-size: 12px;
      margin: 0;
      padding: 0;
    }

    #tit_cs {
      margin-right: 12px;
      position: relative;
    }
    
</style>
</head>
<body>

    <footer class="footer">
        <section class="info-section">
          <img src="./TM small logo.png" alt="작은로고" class="small-logo">
          <p>대표이사 : 심원용 | 
          <i>서울시 강남구 테헤란로 45번길 58, 상경빌딩 8층 |</i>
          <i>사업자등록번호 104-81-64440 |</i>
          <i>관광사업자등록증번호 제2006-000003호</i>
        </p>
        </section>

        <section class="cs_info">
          <div id="tit_cs">
            <a href="">고객센터</a>
          </div>

        </section>

      </footer>

</body>
</html>