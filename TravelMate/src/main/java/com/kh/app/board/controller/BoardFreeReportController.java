package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.report.vo.ReportVo;

@WebServlet("/free/board/report")
public class BoardFreeReportController extends HttpServlet{
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			
			String memberNo= loginMember.getNo();
			String value = req.getParameter("value");
			String no = req.getParameter("no");
			String content= req.getParameter("content");
			
			ReportVo vo = new ReportVo();
			vo.setBoardNo(no);
			vo.setMemberNo(memberNo);
			vo.setContent(content);
			vo.setSanctionReasonNo(value);
			
			BoardService bs = new BoardService();
			int result = bs.freeBoardReport(vo);
			
			if(result == 1) {
				resp.getWriter().write("success");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
