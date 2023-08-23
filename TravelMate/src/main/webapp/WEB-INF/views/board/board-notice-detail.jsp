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
        width: 1390px;
        height: 100%;
        bottom: 1300px;
        left: 300px;
		margin-top: 500px;
    }
    

    #first-content>img{
        position: absolute;
        left: 100px;
        top: 70px;
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

    #edit-area{
        position: absolute;
        width: 1300px;
        height: 1000px;
        left: 130px;
        top: 230px;
        display: grid;
        grid-template-rows: 3fr 1fr;
    }
    
    #edit-input-box{
        width: 1300px;
        border-radius: 30px;
        border: 1px dashed black;
        height: 90%;
        margin: auto;
        font-size: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
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
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   

    #write-area{
        margin-left: 20%;
    }

    textarea{
        height: 900px;
        width: 1000px;
        margin-top: 30px;
        resize: none;
        font-size: 2.5em;
        background-color: #d8f1c7;
        border: none;
        border-radius: 20px;
    }


    input[name=title]{
        font-size: 2em;
        width: 500px;
        height: 70px;
        background-color: #d8f1c7;
        border: none;
        border-radius: 20px;
        margin-top: 210px;
    }

	#hr{
		border: 3px groove black;
		margin-left: 200px;
	}

	#reply-write-area{
		display: grid;
		grid-template-columns: 5fr 1fr;
	}

	#reply-write-area > input{
		margin-left: 280px;
		width: 900px;
		border-radius: 20px;
		font-size: 1.5em;
		background-color: #d8f1c7;
		height: 60px;
	}

	#btn01{
		font-size: 1.5em;
		height: 60px;
	}	

	#reply-list-area > table{
		margin-left: 280px;
		margin-top: 20px;
		width: 900px;
		border-radius: 20px;
		font-size: 1.5em;
		border-spacing: 4px;
		background-color: #d8f1c7;
	}

	#reply-list-area > table > tbody > tr > td{
		text-align: center;
	}

	#reply-list-area > table > thead > tr > th , #reply-area > table > tbody > tr > td{
 		 border: 3px solid black;
		border-radius: 10px;
		box-sizing: border-box;
	}

	#btn-area{
		box-sizing: border-box;
		margin-left: 300px;
	}

	select[name='rep']{
		font-size: 1.5em;
	}
</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	
	<!-- 내용영역 -->
    <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>공지사항 상세 조회</a>  
        </div>
		<form action="${root}/notice/edit" method="POST">
			<div id="write-area">
				<input type="hidden" name="no" value="${nvo.no}"> 
				<input type="text" name="title" value="${nvo.title }" readonly="readonly">
				&nbsp;
				&nbsp;
				&nbsp;
				<span style="font-size: 1.5em;">작성자:${nvo.memberNick }</span>
				&nbsp;
				&nbsp;
				<span style="font-size: 1.5em;">작성일시:${nvo.enrollDate }</span>
				&nbsp;
				<span style="font-size: 1.5em;">조회수 :${nvo.hit }</span>
				<textarea name="content" readonly>${nvo.content }</textarea>
			</div>
				
			<c:if test="${loginMember.id eq 'ADMIN' }">
			<div id="btn-area">
				<button type="button" id="btn01" onclick="edit();">수정</button>
				<button type="button" id="btn01" onclick="del();">삭제</button>
				<input type="submit" value="수정하기">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- <button type="button" id="btn01">신고하기</button> -->
				<!-- <select name="rep" id="btn01">
					<option value="1">욕설</option>
					<option value="2">허위사실</option>
					<option value="3">패드립 </option>
					<option value="4">스팸/도배글</option>
					<option value="5">명예훼손</option>
					<option value="6">성희롱</option>
					<option value="7">불쾌한닉네임</option>
					<option value="8">혐오발언</option>
					<option value="9">자수</option>
					<option value="10">맘에안들어서</option>
				</select> -->
			</div>
			</c:if>

		</form>

		

		<hr id="hr">
		
		<!-- 	댓글구역 -->

        <div id="reply-write-area">
				<input type="text" name="content" placeholder="댓글쓰기"> <button id="btn01" onclick="writeComment()">작성하기</button>
        </div>

		<div id="reply-list-area">
			<table > 
				<thead>
					<tr>
						<th>댓글내용</th>
						<th>작성일시</th>
						<th>글쓴이</th>
					</tr>
					</thead>
				<tbody>
						
				</tbody>
			</table>
		</div>
		

    </div>

</body>
</html>
<script>

	// //신고하기
	// function f01(){
	// 	const value = document.querySelector('select[name=rep]').value;
	// 	const optionList = document.querySelectorAll('select[name=rep] > option');
	// 	for(temp of optionList){
	// 		if(temp.value == value){
	// 			console.log(temp.innerText);
	// 		}
	// 	}


	// 		$.ajax({
	// 		url : '${root}/notice/board/report',
	// 		type : 'POST',
	// 		data : {
	// 			'memberNo' :'${nvo.memberNo}', 
	// 			'value' : value ,
	// 			'no' : '${nvo.no}',
	// 			'content' : temp.innerHTML
	// 		},
	// 		success : (data)=>{
	// 			if(data === 'success'){
	// 				alert("신고 완료");
	// 			}
	// 			reportDel();
	// 		},
	// 		error : (e)=>{
	// 			console.log(e);
	// 		},

	// 	});
	// }
	
	
	
	// //신고후에 삭제처리하기
	// function reportDel() {
	// 	location.href = '${root}/notice/del?no=' + '${nvo.no}';
	// }
		

	

	//삭제하기
	function del(){
		const result = confirm("해당 게시글을 삭제 하시겠습니까?");
		if(!result){
			return;
		}
		location.href = '${root}/notice/del?no=' + '${nvo.no}';
	}



	//수정하기
	function edit(){
			document.querySelector('input[name=title]').readOnly = false;
			document.querySelector('textarea').readOnly = false;
		} 


			//댓글 작성
	function writeComment(){
		const comment = document.querySelector("input[name=content]").value;
		$.ajax({
			url : "${root}/notice/reply/write" ,
			type : "POST" ,
			data : {
				'boardNo' : '${nvo.no}' ,
				'content' : comment ,
			} ,
			success : (x)=>{
				console.log(x);
				if(x == 'ok'){
					alert("댓글이 등록되었습니다.");
					document.querySelector("input[name=content]").value = '';
					loadComment();

				}else{
					alert("로그인 후 작성 가능합니다...");
				}
			} ,
			error : (x)=>{
				console.log(x);
			} ,
		});
	}

	//댓글 불러오기
	function loadComment(){
		const replyListArea = document.querySelector("#reply-list-area");
		
		$.ajax({
			url : '${root}/notice/reply/list' ,
			type : "GET" ,
			data : {
				'no' : '${nvo.no}'
			} ,
			success : function(data){
				console.log(data);
				
				const x = JSON.parse(data);
				console.log(x);
				const tbody = document.querySelector('#reply-list-area > table tbody');
				tbody.innerHTML = "";
				let str = "";
				for(let i = 0; i < x.length; i++){
					str += '<tr>';
					str += '<td>' + x[i].content + '</td>';
					str += '<td>' + x[i].enrollDate + '</td>';
					str += '<td>' + x[i].memberNick + '</td>';
					str += '</tr>';
				}
				tbody.innerHTML += str;
			} ,
			error : function(e){
				console.log(e);
			} ,
		});
	}

	loadComment();
</script>