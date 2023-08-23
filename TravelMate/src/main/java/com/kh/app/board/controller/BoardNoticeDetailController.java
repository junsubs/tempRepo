package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/notice/detail")
public class BoardNoticeDetailController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		BoardService bs = new BoardService();
		BoardVo vo = new BoardVo();
		BoardVo nvo = null;
		try {
			nvo = bs.noticeDetail(no);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		req.setAttribute("nvo", nvo);     
		req.getRequestDispatcher("/WEB-INF/views/board/board-notice-detail.jsp").forward(req, resp); //댓글작업해야함 
	}
	
}
