package com.kh.app.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.CommentVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/notice/reply/write")
public class BoardNoticeReplyWriteController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String memberNo = loginMember.getNo();
			
			//데꺼
			String noticeNo = req.getParameter("boardNo");
			String content = req.getParameter("content");
			
			//데뭉
			CommentVo vo = new CommentVo();
			vo.setBoardNo(noticeNo);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			
			//서비스
			BoardService bs = new BoardService();
			int result = bs.NoticeReplyWrite(vo);
			
			System.out.println("작성 결과 : " + result);
			
			
			PrintWriter out = resp.getWriter();
			if(result == 1) {
				out.write("ok");
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] notice reply errr~~~");
			e.printStackTrace();
			
			//view ~~~
		}
}
	
}
