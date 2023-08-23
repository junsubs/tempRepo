package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;

@WebServlet("/review/detail")
public class BoardReviewDetailController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			String no = req.getParameter("no");
			
			BoardService bs = new BoardService();
			
			BoardVo vo = new BoardVo();
			
			BoardVo cvo  = bs.carReviewDetail(no);
			
			if(cvo != null) {
				req.setAttribute("cvo", cvo);
				req.getRequestDispatcher("/WEB-INF/views/board/board-car-review-detail.jsp").forward(req, resp);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
