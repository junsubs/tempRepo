package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

@WebServlet(urlPatterns = "/find-pwd")
public class FindPwdController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/find-pwd.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			try {
			
			//e
			String memberId = req.getParameter("memberId");
			String memberEmail = req.getParameter("memberEmail");
			
			//a
			MemberVo vo = new MemberVo();
			vo.setId(memberId);
			vo.setEmail(memberEmail);
			
			//s
			MemberService ms = new MemberService();
			MemberVo loginMember = ms.findPwd(vo);
			
			//g
			if(loginMember !=null) {
				req.setAttribute("loginMember", loginMember);
				req.getRequestDispatcher("/WEB-INF/views/member/find-pwd.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		}catch (Exception e) {
			System.out.println("[ERROR]  find-id..");
			e.printStackTrace();
		}
	
	}

}

