package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;

@WebServlet("/free/detail")
public class BoardFreeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		BoardService bs = new BoardService();
		
		BoardVo fvo = null;
		try {
			fvo =  bs.freeDetail(no);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(fvo != null) {
			req.setAttribute("fvo", fvo);
			req.getRequestDispatcher("/WEB-INF/views/board/board-free-detail.jsp").forward(req, resp);
		}
		
	}
	
}
