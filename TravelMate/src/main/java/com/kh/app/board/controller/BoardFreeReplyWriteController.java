package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.CommentVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/free/reply/write")
public class BoardFreeReplyWriteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
		
		String memberNo = loginMember.getNo();
		String boardNo = req.getParameter("boardNo");
		String content = req.getParameter("content");
		
		CommentVo cvo = new CommentVo();
		cvo.setBoardNo(boardNo);
		cvo.setContent(content);
		cvo.setMemberNo(memberNo);
		
		BoardService bs = new BoardService();
		int result =0;
		try {
			result = bs.freeBoardRplyWrite(cvo);
			if(result ==1) {
				resp.getWriter().write("ok");
			}
		}catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
}
