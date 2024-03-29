<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 메일함</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/794ac64f16.js" crossorigin="anonymous"></script>
<style>
   
    #wrap{
			width: 1920px;
			height: 900px;
			display: grid;
			grid-template-columns: 150px 1770px;
		}
	   
	    .main-area {
	       display: flex;
		flex-direction: column;
		width: 80%;
		margin: auto;
		padding: 20px;
		height: 100%;
	    }
	   
		/* .title-area {
				margin-top: 40px;
                text-align: center;
                display: flex;
                flex-direction: row;
                justify-content: center;
				margin-bottom: 30px;
            } */
            
            .title-area {
    margin-top: 40px;
    text-align: center;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    width: 80%;
    margin-left: 10%;
    padding-right: 10px;
    margin-bottom: 40px;
}

		#title {
			font-size: 30px;
			margin-right: 30px;
			
		}

        
	    .search-area {
                display: flex;
                align-items: center;
                margin-left: 80px;
            }



            .search-area input[type="text"] {
                padding: 5px;
                margin-right: 20px;
                width: 350px;
                height: 40px;
                border: 1px solid gray;
                border-radius: 10px;
            }





            .category-area {
                display: flex;
                align-items: center;

            }

            .category-area label {
                margin-right: 5px;
            }

            .category-area select {
                padding: 5px;
                border-radius: 10px;
                border: 1px solid gray;
                height: 40px;
            }

            .category-icon {
                margin-right: 5px;
                color: #555;
            }

            .category-icon::before {
                font-family: "Font Awesome 5 Free";
                content: "\f0a5";
                font-weight: 900;
            }

            #search-icon {
                color: gray;
            }


			.list-area {
			  margin: 20px auto;
			  width: 80%;
			  background: #FFFFFF;
			  border: 1px solid #C4C4C4;
			  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
			  border-radius: 20px;
			  min-height: 50%;
			}
			
			.list-area table {
			  border-collapse: collapse;
			  width: 100%;
			}
			
			.list-area th,
			.list-area td {
			  padding: 12px 20px;
			  border-bottom: 1px solid #ddd;
			  text-align: left;
			}
			
			.list-area th {
			  font-size: 20px;
			  text-align: center;
			  
			}
			
			.list-area td {
				font-size: 20px;
				text-align: center;
			}
			

			.list-area tr:hover:not(#top) {
			    background-color: #5A8CF2;
			    color: white;
			}
			
			
       .number-area {
        text-align: center;
        margin-top: 20px;
        margin-bottom: 20px;
    }

    .number-area a {
        display: inline-block;
        margin: 5px;
        padding: 8px 12px;
        text-decoration: none;
        border: none;
        color: inherit;
        font-size: 15px;
    }

    .number-area a:hover {
        color: #5A8CF2;
        cursor: pointer;
    }

    .currentPage{
        color: #5A8CF2 !important;
        pointer-events: none;
    }
       
       
	        
       
		  #sendMail {
		  		margin-left: 150px;
                font-size: 20px;
                color: white;
                background-color: #5A8CF2;
                size: 10px;
                border: none;
                padding: 10px 15px;
                cursor: pointer;
                border-radius: 5px;
                font-weight: bold;
            }

            #sendMail:hover {
                background-color: #555;
                transition: 0.7s;
            }
            
               #delete-button {
                color: #5A8CF2;
                font-weight: bold;
                font-size: 15px;
                margin-left: 190px;
                cursor: pointer;
            }
            
             .list-area th input[type="checkbox"] {
                appearance: none;
                width: 20px;
                height: 20px;
                border: 2px solid #C4C4C4;
                border-radius: 3px;
                outline: none;
                vertical-align: middle;
                position: relative;
                top: 2px;
                cursor: pointer;
            }

            .list-area td input[type="checkbox"] {
                appearance: none;
                width: 20px;
                height: 20px;
                border: 2px solid #C4C4C4;
                border-radius: 3px;
                outline: none;
                vertical-align: middle;
                position: relative;
                top: 2px;
                cursor: pointer;
            }

            .list-area td input[type="checkbox"]:checked {
                background-color: lightgray;
                border-color: lightgray;
            }

            .list-area th input[type="checkbox"]:checked {
                background-color: lightgray;
                border-color: lightgray;
            }
            
             .list-area th:first-child {
    border-top-left-radius: 20px;
}

.list-area th:last-child {
    border-top-right-radius: 20px;
}

       
		#top {
			background-color: #5A8CF2;
			color: white;
			font-weight: bold;
		}	
      

</style>
</head>
<body>
    <header>
      <%@include file="/WEB-INF/views/common/member/header.jsp" %>
   </header>
   
   <main id="wrap">
   		<div>
   			<%@ include file="/WEB-INF/views/common/member/side-bar.jsp" %>
   		</div>
      
		
		<div class="main-area">
			<div class="title-area">
                <span id="title">받은 메일함</span>

				<form action="" class="search-area">
					<label for="search" class="category-area">
						<select name="searchType" id="search">
							<option value="name">발신자명</option>

						</select>


					</label>
					<input type="text" id="search-input" name="searchValue">
					<a href="" id="search-icon"><i class="fa-solid fa-magnifying-glass"></i></a>
				</form>
				
				<button id="sendMail">메일 보내기</button>
           	</div>
			<br>

			<span id="delete-button">삭제</span>
           	

			   <div class="list-area">
				<table>
					<tr id="top">
						<th><input type="checkbox" name="choose" value="selectAll" onclick="selectAll(this)" class="not-clickable"></th>
						<th>발신자</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					
					<c:forEach items="${receiveList}" var="list">
						<tr class="receiveList-row" data-receiveMail-no="${list.no}">
							<td><input type="checkbox" name="choose" class="not-clickable"></td>
							<td>${list.senderName}</td>
							<td>${list.mailTitle}</td>
							<td>${list.mailEnrollDate}</td>
							
							
							
						</tr>
					</c:forEach>
	
					<!-- <tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					<tr>
						<td>송일섭</td>
						<td>104동 201호</td>
						<td>2023-03-09 12:00:37</td>
						<td>2023-04-17 13:00:22</td>
						<td>O</td>
					</tr>
					
					 -->
					
					
	
	
				</table>
			</div>
			
			<br>
			

  <div class="number-area">
                <c:if test="${pv.listCount > 10}">
                    <c:if test="${pv.currentPage > 1}">
                        <a href="receiveList?page=1&searchType=${svo.getSearchType()}&searchValue=${svo.getSearchValue()}">&laquo;</a>
                        <a href="receiveList?page=${pv.currentPage - 1}&searchType=${svo.getSearchType()}&searchValue=${svo.getSearchValue()}">&lt;</a>
                    </c:if>      
                    <c:set var="finalEndPage" value="${pv.endPage > pv.maxPage ? pv.maxPage : pv.endPage}" />
                    <c:forEach var="i" begin="${pv.startPage}" end="${finalEndPage}" step="1">
                        <c:choose>
                            <c:when test="${i == pv.currentPage}">
                                <a class="currentPage">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="receiveList?page=${i}&searchType=${svo.getSearchType()}&searchValue=${svo.getSearchValue()}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pv.maxPage > pv.currentPage}">
                        <a href="receiveList?page=${pv.currentPage + 1}&searchType=${svo.getSearchType()}&searchValue=${svo.getSearchValue()}">&gt;</a>
                        <a href="receiveList?page=${pv.maxPage}&searchType=${svo.getSearchType()}&searchValue=${svo.getSearchValue()}">&raquo;</a>
                    </c:if>
                </c:if>
            </div>
          	
          	

           	
           	
        </div>

		



        
		
    </main>

    



   <footer>
      <%@ include file="/WEB-INF/views/common/member/footer.jsp" %>
   </footer>
   



	
	<script type="text/javascript">

		//사이드바 조정
		const sideBar = document.querySelector("#side-bar")
	    const subMenus = document.querySelectorAll(".sub-menu");
	    const thirdSidebars = document.querySelectorAll(".third-sidebar");
	
	    subMenus.forEach(subMenu => {
	        subMenu.style.height = sideBar.offsetHeight + 'px';
	    });
	
	    thirdSidebars.forEach(thirdSidebar => {
	        thirdSidebar.style.height = sideBar.offsetHeight + 'px';
	    });
	    
	    
	    //메일 디테일 폼으로 보내기
	    $(document).ready(function () {
	        $('.receiveList-row').click(function (e) {
	            // 클릭한 요소가 not-clickable 클래스를 가진 요소가 아니라면
	            if (!$(e.target).hasClass('not-clickable')) {
	            	const receiveMailNo = $(this).attr('data-receiveMail-no');
	            	console.log("넘어갈 번호: " + receiveMailNo);

	                window.location.href = "/app/mail/mailDetail?no=" + receiveMailNo;
	            }
	        });
	    });


	    
	    //페이징 처리
	    const pageBtn = document.querySelectorAll('.pageBtn');
		
		   for (let btn of pageBtn) {
		       if (btn.innerHTML == '${pv.currentPage}') {
		           btn.style.color = '#d9d9d9';
		       }
		   }
		
		   function pageMove(pageNumber) {
		       let url = new URL(window.location.href);
		       url.searchParams.set('page', pageNumber);
		       window.location.href = url.href;
		   }
		   
		   
		   //검색 결과 유지
			const searchValueTag = document.querySelector("input[name=searchValue]");
			
			
			searchValueTag.value = '${paramMap.searchValue}';
			
			
			const searchTypeTagArr = document.querySelectorAll("select[name=searchType] > option");
			
			const x = '${paramMap.searchType}';
			
			if(x == 'name') {
				searchTypeTagArr[0].selected = true;
			}
			
			//모두 선택
			 function selectAll(selectAll) {
                const checkboxes = document.getElementsByName('choose');

                checkboxes.forEach((checkbox) => {
                    checkbox.checked = selectAll.checked;
                })
            }
			
			//메일 보내기 버튼 활성화
			const sendMailBtn = document.querySelector("#sendMail");
			sendMailBtn.addEventListener("click", function(){
				location.href = "/app/mail/writeMail";
			});
			
			
		
			// 삭제 버튼 클릭 이벤트.
			document.querySelector('#delete-button').addEventListener('click', function() {
			    // 체크박스 선택된 행들 확인.
			    const checkboxes = document.querySelectorAll('.list-area input[type="checkbox"]:checked');
			    // 체크박스를 선택한 상태에서 삭제버튼을 누르면 삭제 기능 실행.
			    if (checkboxes.length > 0) {
			        // 선택된 메일 번호들을 저장할 배열 생성.
			        const selectedMailNos = [];
			        checkboxes.forEach(checkbox => {
			            if (checkbox.value !== "selectAll") {
			                // 선택된 체크박스의 행에서 메일 번호 추출.
			                const mailNo = checkbox.closest('.receiveList-row').getAttribute('data-receiveMail-no');
			                selectedMailNos.push(mailNo);        
			            }
			        });
					
			        console.log(selectedMailNos);
			        
			        // 메일 번호들을 사용하여 서버에 삭제 요청을 보내는 함수.
			        deleteMails(selectedMailNos);
			    } else {
			        alert('삭제할 메일을 선택해주세요.');
			    }
			});

			// 메일 삭제 요청 함수.
			function deleteMails(mailNos) {
			    // 서버에 메일 삭제 요청을 보내는 코드 작성.
			    
			   
			      $.ajax({
			        url: '/app/mail/deleteReceiveMail',
			        type: 'POST',
			        data: {
			            mailNos: mailNos
			        },
			        dataType: 'json',
			       
			        success: function(response) {
			            if (response.success) {
			       			alert("선택한 메일 삭제 성공");
			       			location.reload();
			                
			            } else {
			                alert('메일 삭제 중 오류가 발생했습니다.');
			            }
			        },
			        error: function() {
			            alert('서버 오류로 인해 메일 삭제에 실패했습니다.');
			        }
			      });
			}
	</script>
  
</body>
</html>
