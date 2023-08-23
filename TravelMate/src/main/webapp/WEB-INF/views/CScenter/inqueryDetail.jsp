<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


	#content{
         position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 420px;
	    bottom: 450px;
    }

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 125px;
        width: 40px;
        height: 60px;
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

    #first-content>#btn01{
        position: absolute;
        right: 0px;
        top: 130px;
    }

    #title-wrap{
        width: 60%;
        margin-left: 50px;
        display: grid;
        grid-template-columns: 1fr 3fr;
        font-size: 20px;
    }

    #word {
        width: 50px;
        display: flex;
        align-items: center;
        justify-self: center;
        text-align: center;
        font-weight: bold;
    }

    #inquery-input{
        position: absolute;
        width: 1300px;
        height: 1100px;
        left: 100px;
        top: 230px;
        display: grid;
        grid-template-rows: 1fr 3fr 1fr;
        justify-content: center;
        align-items: center;
        margin: auto;
        font-size: 30px;
        border: 1px solid black;
    }

    input[name=title] {
        width: 1200px;
        height: 50px;
        font-size: 20px;
        border: none;
        border-bottom: 1px solid black;
        background: transparent;
    }

    textarea {
        width: 1180px;
        height: 700px;
        font-size: 20px;
        border-bottom: 1px solid black;
        border: none;
        resize: none;
        border-radius: 20px;
    }

    #content-wrap {
        width: 60%;
        margin-left: 50px;
        display: grid;
        grid-template-columns: 1fr 3fr;
        font-size: 20px;
    }

    #btn-area{
        margin: auto;
        margin-top: 10%;
        place-items: center center;
    }
    
    #btn-area > input{
        width: 200px;
        height: 60px;
        font-size: 30px;
        border-spacing: 20px;
    }


    #btn01 {
        background-color: #73D38E;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: white;
        border-radius: 6px;
        width: 150px;
        height: 50px;
        font-size: 20px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   

    #reply-area{
        justify-self: center;
        align-items: center;
    }

    #reply-form-area{
        margin-left: 100px;
    } 

    #reply-form-area > input[name=content] {
        width: 1000px;
        height: 50px;
        font-size: 20px;
    }

    #reply-list-area {
        margin-top: 20px;
        font-size: 20px;
        margin-left: 50px;
        display: grid;
        grid-template-columns: 1fr 3fr;
        font-size: 20px;
    }

    .align{
        width: 1200px;
        margin-left: 100px;
    }

    .reply-table{
        width: 1180px;
        border-collapse: separate;
        border-spacing: 20px;
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
        <a>문의상세</a>
        <button id="btn01" onclick="back();">목록으로</button>
    </div>
        <div id="inquery-input">
            <div id="title-wrap">
                    <div id="word">제목</div>
                    <input type="text" name="title" value="${vo.title}">
                </div>
                <div id="content-wrap">
                    <span id="word">내용</span>
                    <textarea name="content">${vo.content}</textarea>
                </div>
                <div id="reply-area">
                <hr class="align">
                <input type="hidden" name="InqueryNo" value="${vo.no}">
                <div id="reply-form-area">
                <c:if test="${ loginMember.id == 'ADMIN'}">
	                <input type="text" name="content" placeholder="답변을 입력하세요">
    	            <input type="button" id="btn01" name="writeComment" value="답변 쓰기" onclick="writeComment();">
                </c:if>
                </div>
                <div id="reply-list-area">
                    <table class="reply-table">
                        <span id="word">답변</span>
                        <tbody>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
<script>


    // 댓글 admin, 작성자 둘 다 볼 수 o, 쓰는 건 admin만
    // 댓글 쓰기
    function writeComment(){
		const comment = document.querySelector("input[name=content]").value;
		$.ajax({
			url : "${root}/cs/reply/write",
			type : "POST",
			data : {
				InqueryNo : "${vo.no}",
				content : comment
			},
			success : (x)=>{
				if(x == "ok"){
					alert("댓글 작성 성공!");
					document.querySelector("input[name=content]").value = '';
					loadComment();
				}else{
					alert("댓글 작성 실패,,");
				}
			},
			error : (x)=>{
				console.log(x);
			}
		})
	}


	// 댓글 목록 보여주기
	function loadComment(){
		const replyListArea = document.querySelector("#reply-list-area");

		// 댓글 객체로 내용, 작성자 뽑아내기
		$.ajax({
			url : "${root}/cs/reply/list",
			type : "GET",
			data : {
				InqueryNo : '${vo.no}'
			},
			success : function(data){
				console.log(data);
				// JSON 형태로 받아서, 화면에 보여주기
				const x = JSON.parse(data);
				console.log(x);

				const tbody = document.querySelector('#reply-list-area tbody');
				tbody.innerHTML = "";
				let str = "";
				for(let i = 0; i < x.length; i ++){
					str += '<tr>';
					str += '<td>' + x[i].content + '</td>';
					str += '<td>' + x[i].enrollDate + '</td>';
					str += '</tr>';

				}
				tbody.innerHTML += str;

			},
			error : (e)=>{
				console.log(e);				
			} 
		});

	}

	loadComment();

    function back(){
        history.back();
    }
    

</script>
</html>