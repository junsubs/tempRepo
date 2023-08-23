package com.kh.app.cs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/inquery")
public class InqueryController extends HttpServlet{

	// 1:1 문의 게시판
	// 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		if(loginMember != null) {
			req.getRequestDispatcher("/WEB-INF/views/CScenter/inquery.jsp").forward(req, resp);
		}else {
			req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
	

	// 문의게시글 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try{
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
	
			// 데꺼
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String memberNo = loginMember.getNo();
			
			// 데뭉
			InqueryVo ivo = new InqueryVo();
			ivo.setTitle(title);
			ivo.setContent(content);
			ivo.setMemberNo(memberNo);
			
			// 서비스 로직
			InqueryService is = new InqueryService();
			int result = is.write(ivo);
			
			// 화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() +"/cs/inqueryList");
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
