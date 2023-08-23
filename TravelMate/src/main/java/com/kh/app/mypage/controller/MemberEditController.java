package com.kh.app.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/mypage/member/edit")
public class MemberEditController extends HttpServlet{
	
	// 회원정보 수정 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		if(loginMember != null) {
			req.getRequestDispatcher("/WEB-INF/views/mypage/memberEdit.jsp").forward(req, resp);
		}else {
			req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
	
	// 회원정보 수정
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String no = loginMember.getNo();
			
			// 데꺼 
			String memberPwd = req.getParameter("memberPwd");
			String memberNick = req.getParameter("memberNick");
			String address = req.getParameter("address");
			String email = req.getParameter("email");
			String memberGrade = req.getParameter("memberGrade");
			String totalAttend = req.getParameter("totalAttend");
			
			// 데뭉
			MemberVo vo = new MemberVo();
			vo.setNo(no);
			vo.setPwd(memberPwd);
			vo.setNick(memberNick);
			vo.setAddress(address);
			vo.setEmail(email);
			vo.setMemberGradeImg(memberGrade);
			vo.setTotalAttend(totalAttend);
			
			// 서비스
			MemberService ms = new MemberService();
			MemberVo updatedMember = ms.edit(vo);
			MemberVo vo2 = ms.cntAttendByNo(no);
			
			// 화면
			if(updatedMember != null) {
				req.getSession().setAttribute("alertMsg", "수정이 완료되었습니다.");
				req.setAttribute("loginMember", updatedMember);
				req.setAttribute("vo", vo2);
				req.getRequestDispatcher("/WEB-INF/views/mypage/memberDetail.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] 정보 수정 중 예외발생");
			e.printStackTrace();

			req.setAttribute("errorMsg", "edit fail..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	
	}
	
}
