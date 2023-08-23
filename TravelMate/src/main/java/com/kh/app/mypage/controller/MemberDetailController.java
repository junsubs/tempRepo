package com.kh.app.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/mypage/member/detail")
public class MemberDetailController extends HttpServlet{

	
	private final MemberService ms = new MemberService();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 데꺼
			MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
			String mno = loginMember.getNo();
			
			// 데뭉
			
			// 서비스
			loginMember = ms.selectMemberOneByNo(mno);
		 	MemberVo vo = ms.cntAttendByNo(mno);
			
			// 화면
			if(loginMember != null) {
				req.setAttribute("loginMember", loginMember);
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/mypage/memberDetail.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] member detail err");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "상세조회 실패..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
}
