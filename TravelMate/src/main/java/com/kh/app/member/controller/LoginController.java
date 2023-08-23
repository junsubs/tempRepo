package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		if(loginMember != null){
			req.getSession().setAttribute("alertMsg", "이미 로그인 하셨습니다.");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			//eR
			String memberId = req.getParameter("memberId");
			String memberPwd = req.getParameter("memberPwd");
			
			//ea
			MemberVo vo = new MemberVo();
			vo.setId(memberId);
			vo.setPwd(memberPwd);
			
			//tqt
			MemberService ms = new MemberService();
			MemberVo loginMember = ms.login(vo);
			
			//wha
			if(loginMember != null && !loginMember.getId().equals("ADMIN")) {
				req.getSession().setAttribute("loginMember", loginMember);
				resp.sendRedirect(req.getContextPath()+"/home");
			}else if(loginMember != null && loginMember.getId().equals("ADMIN")){
				req.getSession().setAttribute("loginMember", loginMember);
				resp.sendRedirect(req.getContextPath()+"/admin/home");
				resp.sendRedirect(req.getContextPath()+"/home");
			}else {
				req.setAttribute("alertMsg", "아이디나 비밀번호를 확인해주세요");
				req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
				throw new Exception();
			}
			
		}catch(Exception e) {
			System.out.println("[ERROR] login...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "로그인에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	}

}