<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/admin/reportList.css">
<script defer src="${root}/static/js/admin/reportList.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/admin/header.jsp" %>
	
	  <!-- 내용영역 -->
      <div id="content">
          <div id="first-content">
              <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
              <hr>
              <a>신고내역</a>
              <div id="report-search">
                  <form action="${root}/admin/reportlist" method="get">
                     <input type="hidden" name="page" value="1"> 
                     <select name="searchType">
                          <option value="memberNick">회원닉네임</option>
                          <option value="boardName">게시판이름</option>
                          <option value="reportReason">신고사유</option>
                      </select>
                      <input value="${searchVo.searchValue}" name="searchValue" type="text" placeholder="신고내역관련 검색">
                      <select name="searchValue" class="semiCategory">
                        <option value="욕설">욕설</option>
                        <option value="허위사실">허위사실</option>
                        <option value="부적절한언행">부적절한언행</option>
                        <option value="스팸/도배글">스팸/도배글</option>
                        <option value="명예훼손">명예훼손</option>
                        <option value="성희롱">성희롱</option>
                        <option value="불쾌한닉네임">불쾌한닉네임</option>
                        <option value="혐오발언">혐오발언</option>
                      </select>
                      <input type="submit" value="검색">
                  </form>
              </div>
          </div>
          
          <div id="declaration">
                <div></div>
                <div>신고번호</div>
                <div>회원닉네임</div>
                <div>게시판이름</div>
                <div>신고사유</div>
                <div>🚨🚨🚨</div>
                <div id="hr"><hr></div>

				<c:forEach var="reportVoList" items="${reportVoList}">
                <div id="report-content">
                    <label class="chk_box">
                    <input type="checkbox" value="${reportVoList.no}" name="stop" onchange="handleCheckboxChange(this)">
                    <span class="on"></span>
            
                    </label>
                </div>
	                <div id="no">${reportVoList.no}</div>
	                <div id="memberNick">${reportVoList.nick}</div>
	                <div>${reportVoList.categoryName}</div>
	                <div>${reportVoList.reasonName}</div>
                    <div><button id="report" onclick="memberStop(${reportVoList.no});">회원정지</button></div>
                    <div id="hr"><hr></div>
				</c:forEach>
                
                <div id="page-area">
                        <c:if test="${pv.currentPage > 1}">
                        <a href="${root}/admin/reportlist?page=${pv.currentPage - 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">◀ 이전</a>
                        </c:if>
                        <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                            <c:if test="${pv.currentPage != i}">
                                <a href="${root}/admin/reportlist?page=${i}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">${i}</a>
                            </c:if>
                            <c:if test="${pv.currentPage == i}">
                                <a>${i}</a>
                            </c:if>
                        </c:forEach>
                    <c:if test="${pv.currentPage < pv.maxPage}">
                        <a href="${root}/admin/reportlist?page=${pv.currentPage + 1}&searchType=${searchVo.searchType}&searchValue=${searchVo.searchValue}">다음 ▶</a>
                    </c:if>
                </div>
            </div>

          <div id="member-stop" class="stop-active1">
                <div id="stop-go" class="stop-active2">차단시작일 |</div>
                <div id="stop-back" class="stop-active3">차단종료일 |</div>

                    <div><input type="date" name="stopStart" id="stop-start" class="stop-active4"></div>
                    <div><input type="date" name="stopEnd" id="stop-end" class="stop-active5"></div>

                    <input type="submit" value="적용" id="btn01" class="stop-active6">

                <button id="btn02" class="stop-active7">닫기</button>
          </div>
      </div>
        
</body>
</html>
<script>

    let startData;
    let endData;

    //회원정지
    function memberStop(no) {
    const memberStop = document.querySelector('#member-stop');
    memberStop.style.display = 'block';
    document.body.classList.add("stop-scroll");

    document.getElementById("btn02").onclick = function() {
        document.getElementById("member-stop").style.display = "none";
        document.body.classList.remove("stop-scroll");
    };

    const btn01 = document.querySelector('#btn01');

    btn01.addEventListener("click", function() {
        const result = confirm("해당회원을 차단하시겠습니까?");

        if (!result) {
        	return;
        }

        startData = document.querySelector("#stop-start").value;
        endData = document.querySelector("#stop-end").value;
        memberNick = document.querySelector("#memberNick").innerText;

        $.ajax({
        url: '${root}/admin/reportlist',
        type: 'POST',
        data: {
            no: no,
            startData: startData,
            endData: endData,
            memberNick: memberNick
        },
        success: function(data) {
            alert(memberNick + " 님을 " + startData + " 부터 " + endData + " 까지 차단하였습니다.");
            location.reload();
        },
        error: function(error) {
            console.error(error);
        }
        });
    });
    }

    //체크박스 하나만
    function handleCheckboxChange(checkbox) {
    var checkboxes = document.getElementsByName('stop');
    for (var i = 0; i < checkboxes.length; i++) {
      checkboxes[i].disabled = checkboxes[i] !== checkbox && checkbox.checked;
    }
  }

	//게시글 상세조회
	const declaration = document.querySelector("#declaration");
	
	declaration.addEventListener("click", function(e) {
	  if (e.target.id === "no") {
	    const no = e.target.innerText;
	    location.href = "${root}/admin/reportlistdetail?no=" + no;
	  }
	});

    //서치타입 변경 시 함수 실행
    const searchType = '${searchVo.searchType}';
    const searchValue = '${searchVo.searchValue}';
    
    const searchValueSelectTag = document.querySelector("select[name='searchValue']");
	const searchValueInputTag = document.querySelector("input[name='searchValue']");
	const submit = document.querySelector("input[type='submit']");
    
   	if(searchType.length > 1){
   		initSearchType();
   	}
    
   	function initSearchType(){
   		const x = document.querySelector('select > option[value="' + searchType + '"]');
	    x.selected = true;   
   	}
   	
    //카테고리를 신고사유로 변경시 함수호출
    const searchTypeTag = document.querySelector('select[name="searchType"]');

    searchTypeTag.addEventListener("change" , setSearchValueTag);
    
	function setSearchValueTag(){
		const searchType = searchTypeTag.value;
		if(searchType == 'reportReason'){
			setSearchValueTagSelect();
		}else{
			setSearchValueTagInput();
		}
	}

	//검색값 영역을 셀렉트가 보이게
	function setSearchValueTagSelect(){
		searchValueSelectTag.classList.add("active");
        searchValueSelectTag.classList.remove("hidden");
        searchValueSelectTag.disabled = false;

		searchValueInputTag.classList.add("hidden");
        submit.classList.remove("hidden");
        searchValueInputTag.disabled = true;

	}

	//검색값 영역을 인풋이 보이게
	function setSearchValueTagInput(){
        searchValueSelectTag.classList.add("hidden");
        searchValueSelectTag.classList.remove("active");
        searchValueSelectTag.disabled = true;
        
		searchValueInputTag.classList.remove("hidden");
        submit.classList.remove("hidden");
        searchValueInputTag.disabled = false;
	}

    //신고사유 검색한 이후에 검색값 유지
    function initSearchValueSelect(){
        const optionTag = document.querySelector("option[value='"+ searchValue +"']");
        optionTag.selected = true;
    }
	
	setSearchValueTag();
	initSearchValueSelect();


</script>