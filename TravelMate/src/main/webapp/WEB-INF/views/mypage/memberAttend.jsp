<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- calendar를 위한 라이브러리들 지우면 안됨 -->
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js" integrity="sha256-VvQ+gcDf/XacyjwCHZZBxAGTsf6Xv3T3edd2YrkQDTs=" crossorigin="anonymous"></script>
<link href='/resources/fullcalendar-5.6.0/lib/main.css' rel='stylesheet' />
<script src='/resources/fullcalendar-5.6.0/lib/main.js'></script>
<style>

    #content{
         position: relative;
	    width: 1170px;
	    height: 1000px;
	    left: 350px;
	    bottom: 550px;
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

    #edit-area{
        position: absolute;
        width: 1300px;
        height: 1000px;
        left: 100px;
        top: 230px;
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

    #calendar{
        width: 1000px;
        height: 100%;
        display: flex;
    }

    .fc .fc-scrollgrid, .fc .fc-scrollgrid table {
        table-layout: auto;
        width: 100%;
    }

    .fc .fc-daygrid-day-frame{
        width: 140px;
    }
    
    .fc .fc-toolbar {
    display: flex;
    justify-content: space-between;
    margin-left: 20%;
}
   
.completedButton {
    background-color: #999999;
    color: #ffffff;
    cursor: not-allowed;
}

.fc-scrollgrid-sync-inner{
    width: 140px;
}



    
</style>
</head>
<body>

    <%@ include file="/WEB-INF/views/common/mypage-header.jsp" %>
    <!-- 내용영역 --> 
   <div id="content">
        <div id="first-content">
            <img src="${root}/static/img/사각형.png" alt="사각형" id="square">
            <hr>
            <a>출석현황</a>
        </div>
        <form action="" method="POST">
            <div id="edit-area">
                        <div id="calendar"></div>
            </div>
        </form>
    </div>
</body>

<script>
    document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        // FullCalendar 설정
        headerToolbar: {
            left: 'prev,next',
            center: 'title',
            right: 'myCustomButton'
        },
        customButtons: {
        myCustomButton: {
            text: '출석하기',
            
            click: function() {
                var today = new Date();
                var formattedDate = formatDate(today);
                
                //출석 정보를 가져와서 출석완료 보여주기
                $.ajax({
                    url: '${root}/mypage/attend', // 서버에서 출석 정보를 가져올 경로
                    type: 'GET',
                    success: function(list) {
                        // 서버에서 받아온 JSON 데이터 처리
                        // FullCalendar에 이벤트 데이터로 추가하는 로직 등을 작성
    
                        // 예시: 받아온 데이터를 FullCalendar에 추가하여 표시
                        var events = [];
                        for (var i = 0; i < list.length; i++) {
                            var event = {
                                title: '출석완료',
                                start: list[i].date // 출석한 날짜 정보
                            };
                            events.push(event);
                        }
                        calendar.addEvent(events);
                    },
                    error: function() {
                        console.log('출석 정보 가져오기 실패');
                    }
                });


                // AJAX를 사용하여 출석 정보를 서블릿에 전달
                $.ajax({
                    url: '${root}/mypage/attend',
                    type: 'POST',
                    data: { date: formattedDate },
                    success: function(data) {
                        // 출석 정보 저장 성공 시 처리할 로직 작성
                        console.log('출석 정보 저장 성공');

                        // 출석한 날짜에 표시할 스타일 추가
                        var today = new Date(); // FullCalendar에서 오늘 날짜 가져오기
                        today.setHours(0, 0, 0, 0); // 시간을 00:00:00으로 설정
                        var dateStr = formatDate(today);
                        var event = {
                            title: '출석완료',
                            start: dateStr
                        };
                        calendar.addEvent(event);

                        // 출석하기 버튼 비활성화
                        calendar.setOption('customButtons', {
                            myCustomButton: {
                                text: '출석하기',
                                click: function() {
                                    return false; // 클릭 이벤트를 무시하여 중복 클릭 방지
                                },
                                disabled: true
                            }
                        });
                    },
                    error: function() {
                        // 출석 정보 저장 실패 시 처리할 로직 작성
                        console.log('출석 정보 저장 실패');
                    }
                });
            }
        }
    },
});

    // 오늘 날짜 설정
    function formatDate(date) {
        var year = date.getFullYear();
        var month = String(date.getMonth() + 1).padStart(2, '0');
        var day = String(date.getDate()).padStart(2, '0');
        return year + '-' + month + '-' + day;
    }

    calendar.render();
});

</script>

</html>


