package com.kh.app.cs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/detail")
public class InqueryDetailController extends HttpServlet{

	private final InqueryService is = new InqueryService();
	// 상세 조회
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			// 데꺼
			String no = req.getParameter("ino");
			
			// 데뭉 X
			
			// 서비스
			InqueryVo vo = is.selectInqueryOneByNo(no);
			
			
			// 화면
			if(vo != null) {
				req.setAttribute("loginMember", loginMember);
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/CScenter/inqueryDetail.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] inquery detail err");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "상세조회 실패..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	}
	
}
