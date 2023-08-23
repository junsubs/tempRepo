package com.kh.app.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.board.vo.CommentVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/notice/reply/list")
public class BoardNoticeReplyListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String boardNo = req.getParameter("no");
		
		BoardService bs = new BoardService();
		
		List<CommentVo> replyList = null;
		try {
			replyList = bs.noticeReplyList(boardNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
			Gson gson = new Gson();
			String str = gson.toJson(replyList);
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(str);
	}
}
