package com.kh.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.service.MemberService;

@WebServlet("/login/id/check")
public class LoginIdCheckController extends HttpServlet{

private MemberService ms;
    
    @Override
    public void init() throws ServletException {
        super.init();
        ms = new MemberService();
    }
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String idInputValue = req.getParameter("memberId");
			
			if(ms.loginIdCheck(idInputValue)) {
				resp.getWriter().write("duplicate");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
