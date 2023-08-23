package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
@WebServlet( "/notice/del")
public class BoardNoticeDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		BoardService bs = new BoardService();
		
		int result = 0;
		try {
			result = bs.noticeDelete(no);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if( result == 1 ) {
			resp.sendRedirect(req.getContextPath()+"/notice/list");
		}else {
			req.getRequestDispatcher("/WEB-INF/views/erro").forward(req, resp);
		}
	}

}
