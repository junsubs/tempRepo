<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
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

    #btn01 {
        background-color: #73d38e97;
        border: 0;
        padding: 0px 25px; 
        display: inline-block;
        text-align: center;
        color: black;
        border-radius: 6px;
    }

    #btn01:hover {background-color: #80C49D;}
    #btn01:active {
        box-shadow: 0 0 0 1px #82c8a0 inset,
                0 0 0 2px rgba(255,255,255,0.15) inset,
                0 0 0 1px rgba(0,0,0,0.4);
    }   

    #write-area{
        margin-left: 10%;
		font-size: 17px;
    }


    textarea{
        height: 500px;
        width: 800px;
        margin-top: 30px;
        resize: none;
        font-size: 2.5em;
        background-color: #d8f1c7;
        border: none;
        border-radius: 20px;
    }


    input[name=title]{
        font-size: 20px;
        width: 500px;
        height: 40px;
        border: none;
        margin-top: 250px;
		margin-bottom: 20px;
    }

	#hr{
		border: 3px groove black;
		margin-left: 120px;
		width: 1000px;
	}

	#reply-write-area{
		display: grid;
		grid-template-columns: 3fr 1fr;
	}

	#reply-write-area > input{
		margin-left: 120px;
		margin-right: 15px;
		width: 800px;
		font-size: 18px;
		height: 35px;
	}

	#reply-write-area > button {
		margin-top: 3px;
	}

	#btn01{
		font-size: 18px;
		height: 30px;
		width: 130px;
	}	

	#reply-list-area > table{
		margin-left: 200px;
		margin-top: 20px;
		width: 900px;
		font-size: 18px;
		border-spacing: 4px;
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
		margin-left: 500px;
		margin-top: 20px;
		display: grid;
		grid-template-columns:1fr 2fr;
	}

	#btn-area > input[type="submit"]{
		width: 100px;
		height: 30px;
		border: none;
		color: black;
		font-size: 18px;
		margin-left: 30px;
		text-align: center;
	}

	.btn {
		width: 800px;
		margin-left: 730px;
		margin-bottom: 20px;
	}

	#select-area {
		margin-left: 100px;
	}
</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/product-header.jsp" %>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
	<!-- 내용영역 -->
    <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>자유게시판 상세 조회</a>  
        </div>
		<form action="${root}/free/edit" method="POST">
			<div id="write-area">
					<input type="hidden" name="no" value="${fvo.no}"> 
					제목 : <input type="text" name="title" value="${fvo.title }" readonly="readonly">
					&nbsp;
					&nbsp;
					&nbsp;
					<span>작성자:${fvo.memberNick }</span>
					&nbsp;
					&nbsp;
					<span>작성일시:${fvo.enrollDate }</span>
					&nbsp;
					<span>조회수 :${fvo.hit }</span>
					<div class="btn">
						<button type="button" id="btn01" onclick="edit();">수정</button>
						<button type="button" id="btn01" onclick="del();">삭제</button>
					</div>
				<textarea name="content" id="summernote" >${fvo.content }</textarea>
			</div>
				
			<!-- 글 수정 삭제는 not empty loginMember처리  -->
			<div id="btn-area">
				<input type="submit" value="수정하기" id="btn01">
				<div id="select-area">
					<button type="button" id="btn01" onclick="f02();">신고하기</button>
					<select name="rep" id="btn01">
						<option value="1">욕설</option>
						<option value="2">허위사실</option>
						<option value="3">패드립 </option>
						<option value="4">스팸/도배글</option>
						<option value="5">명예훼손</option>
						<option value="6">성희롱</option>
						<option value="7">불쾌한닉네임</option>
						<option value="8">혐오발언</option>
						<option value="9">맘에안들어서</option>
					</select>
				</div>
			</div>

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


// summernote
$('#summernote').summernote({
	
	placeholder: '내용입력',
	tabsize: 2,
	height: 600,
	maxHeight:800,
	minHeight:500,
	width: 1000,
	callbacks : {
		onImageUpload : f01
	},
	toolbar: [
	  ['style', ['style']],
	  ['font', ['bold', 'underline', 'clear']],
	  ['color', ['color']],
	  ['para', ['ul', 'ol', 'paragraph']],
	  ['table', ['table']],
	  ['insert', ['link', 'picture', 'video']],
	  ['view', ['fullscreen', 'codeview', 'help']]
	]
  });

  function f01(FileList) {

	const fd = new FormData();
	for(let file of FileList){
		fd.append("f" , file);
	}

  $.ajax({
		url :'${root}/upload' ,
		type : 'post',
		data : fd,
		processData : false,
		contentType : false,
		dataType:'json',
		success : (changeNameList)=>{
			console.log(changeNameList);
			for(let changeName of changeNameList){
				$('#summernote').summernote('insertImage' , '${root}/static/img/board-img/' + changeName);
			}
		},
		error : (e)=>{
			alert(e);
		}
	});
}






//신고하기
function f02(){
		const value = document.querySelector('select[name=rep]').value;
		const optionList = document.querySelectorAll('select[name=rep] > option');
		for(temp of optionList){
			if(temp.value == value){
				console.log(temp.innerText);
			}
		}


			$.ajax({
			url : '${root}/free/board/report',
			type : 'POST',
			data : {
				'value' : value ,
				'no' : '${fvo.no}',
				'content' : temp.innerHTML
			},
			success : (data)=>{
				if(data === 'success'){
					alert("신고 완료");
				}
				// reportDel();
			},
			error : (e)=>{
				console.log(e);
			},

		});
	}
	
	
	
	//신고후에 삭제처리하기
	// function reportDel() {
	// 	location.href = '${root}/free/del?no=' + '${fvo.no}';
	// }



///////////////////////////////////////////////////////////////////////////////////



	
	function del(){
		const result = confirm("해당 게시글을 삭제 하시겠습니까?");
		if(!result){
			return;
		}
		location.href = '${root}/free/del?no=' + '${fvo.no}';
	}



	function edit(){
			document.querySelector('input[name=title]').readOnly = false;
			document.querySelector('#summernote').readOnly = false;
		} 






			//댓글 작성
	function writeComment(){
		const comment = document.querySelector("input[name=content]").value;
		$.ajax({
			url : "${root}/free/reply/write" ,
			type : "POST" ,
			data : {
				'boardNo' : '${fvo.no}' ,
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
			url : '${root}/free/reply/list' ,
			type : "GET" ,
			data : {
				no : '${fvo.no}'
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
					// str += '<td><button id="btn07" onclick="f03();"> 신고하기</button></td>';
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


	//댓글 신고하기
	// function f03() {
	// 	const value = document.querySelector('select[name=rep]').value;
	// 	const optionList = document.querySelectorAll('select[name=rep] > option');
	// 	for(temp of optionList){
	// 		if(temp.value == value){
	// 			console.log(temp.innerText);
	// 		}
	// 	}


		// 	$.ajax({
		// 	url : '${root}/free/board/report',
		// 	type : 'POST',
		// 	data : {
		// 		'memberNo' :'${fvo.memberNo}', 
		// 		'value' : value ,
		// 		'no' : '${fvo.no}',
		// 		'content' : temp.innerHTML
		// 	},
		// 	success : (data)=>{
		// 		if(data === 'success'){
		// 			alert("신고 완료");
		// 		}
		// 		reportDel();
		// 	},
		// 	error : (e)=>{
		// 		console.log(e);
		// 	},

		// });
	// }
</script>