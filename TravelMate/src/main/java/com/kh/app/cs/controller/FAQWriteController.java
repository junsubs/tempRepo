package com.kh.app.cs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.cs.service.FAQService;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.FAQVo;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/faq/write")
public class FAQWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/WEB-INF/views/CScenter/FAQWrite.jsp").forward(req, resp);
	}
	

	// faq 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try{
	
			// 데꺼
			String title = req.getParameter("title");
			String answer = req.getParameter("answer");
			
			// 데뭉
			FAQVo vo = new FAQVo();
			vo.setTitle(title);
			vo.setAnswer(answer);
			
			// 서비스 로직
			FAQService fs = new FAQService();
			int result = fs.write(vo);
			
			// 화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() +"/cs/faq");
			}else {
				throw new IllegalStateException("게시글 작성 결과 1 아님..");
			}
			
	} catch (Exception e) {
		e.printStackTrace();
		req.setAttribute("errorMsg", "게시글 작성 실패,,");
		req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		
	}		

	
	}
	
}
