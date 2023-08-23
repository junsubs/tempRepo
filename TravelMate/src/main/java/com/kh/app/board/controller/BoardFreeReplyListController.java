package com.kh.app.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.board.vo.CommentVo;

@WebServlet(urlPatterns = "/free/reply/list")
public class BoardFreeReplyListController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String boardNo = req.getParameter("no");
			
			BoardService bs = new BoardService();
			
			List<CommentVo> replyList = bs.freeReplyList(boardNo); 			
			
			if(replyList != null) {
				Gson gson = new Gson();
				String str = gson.toJson(replyList);
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(str);
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
