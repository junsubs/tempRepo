package com.kh.app.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.service.AttendanceService;
import com.kh.app.mypage.vo.AttendanceVo;

@WebServlet("/mypage/attend")
public class MemberAttendController extends HttpServlet {

    
	private AttendanceService as; 
	
	// 출석정보 초기화
	 @Override
    public void init() throws ServletException {
        super.init();
        as = new AttendanceService(); // AttendanceService 인스턴스 생성 및 초기화
    }
	
	 // 출석 화면
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	
			try {
				req.getRequestDispatcher("/WEB-INF/views/mypage/memberAttend.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			}catch (IOException e) {
			e.printStackTrace();
			}
    }
 

    // 회원의 출석 정보 저장
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	HttpSession session = req.getSession();
    	MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
    	
        String mno = loginMember.getNo(); // 로그인한 회원의 번호를 가져옴

        // 출석 정보 INSERT
        int result;
		try {
			result = as.insertAttendance(mno);
			if(result == 1) {
				req.getRequestDispatcher("/WEB-INF/views/mypage/memberAttend.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMsg", "insert fail...");
				req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			}
			
		List<AttendanceVo> list = new ArrayList<>();
		// 출석 정보 가져오기(select)
		list = as.selectAttendance(mno);
		
		// ArrayList를 JSON 형식으로 변환
		Gson gson = new Gson();
		String json = gson.toJson(list);

		// JSON 데이터 응답
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
    
    }
}

