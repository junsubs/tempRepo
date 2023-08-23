package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;

@WebServlet("/notice/edit")
public class BoardNoticeEditController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String no = req.getParameter("no");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		
		BoardService bs = new BoardService();
		int result = 0;
		try {
			result = bs.noticeEdit(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == 1) {
			req.setAttribute("alertMsg", "수정완료!!"); // alertMsg는 아직 화면쪽에서 처리안함
			resp.sendRedirect(req.getContextPath()+"/notice/list");
		}else {
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
		
	}

}
