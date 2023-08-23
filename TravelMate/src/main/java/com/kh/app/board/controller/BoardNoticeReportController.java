package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
import com.kh.app.report.vo.ReportVo;

@WebServlet("/notice/board/report")
public class BoardNoticeReportController extends HttpServlet{
	
	//REPORT_LIST 에 인서트
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String memberNo= req.getParameter("memberNo");
			String value = req.getParameter("value");
			String no = req.getParameter("no");
			String content= req.getParameter("content");
			
			ReportVo vo = new ReportVo();
			vo.setBoardNo(no);
			vo.setMemberNo(memberNo);
			vo.setContent(content);
			vo.setSanctionReasonNo(value);
			
			BoardService bs = new BoardService();
			int result = bs.noticeBoardReport(vo);
			
			if(result == 1) {
				resp.getWriter().write("success");
				resp.sendRedirect("");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
