<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<style>

    #content{
        position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 350px;
	    bottom: 450px;
    }

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 125px;
        width: 40px;
        height: 60px;
    }

    #addBtn {
      position: absolute;
      left: 1330px;
      top: 145px;
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

    #acco-area {
      position: absolute;
      width: 1300px;
      height: 1000px;
      left: 100px;
      top: 230px;
      
    }

    .accordion-header{
        text-align: center;
    }

    

</style>
</head>
<body>

  <%@ include file="/WEB-INF/views/common/cs-header.jsp" %>
  <!-- 내용영역 -->
  <div id="content">
      <div id="first-content">
          <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
          <hr>
          <a>자주 묻는 질문</a>
          <c:if test="${loginMember.id == 'ADMIN' }">
          <div id="addBtn"><a href="${root}/cs/faq/write"><img src="${root}/static/img/cs/추가.png" alt="추가아이콘" width="60" height="40"></a></div>
          </c:if>
      </div>
      <div id="acco-area">
        <div class="accordion" id="accordionExample">
        <c:forEach items="${voList }" var="vo">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    <strong>${vo.title }</strong>
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    ${vo.answer }
                </div>
              </div>
            </div>
        </c:forEach>
            
    </div>
  </div>
</div>
</body>

</html>