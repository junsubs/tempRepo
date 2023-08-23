package com.kh.app.cs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryReplyVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/reply/write")
public class InqueryReplyWriteController extends HttpServlet{

	// 1대1 문의 댓글 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String writerNo = loginMember.getNo();
			
			// 데꺼
			String InqueryNo = req.getParameter("InqueryNo");
			String content = req.getParameter("content");
			
			// 데뭉
			InqueryReplyVo vo = new InqueryReplyVo();
			vo.setMemberNo(writerNo);
			vo.setQnaNo(InqueryNo);
			vo.setContent(content);
			
			// 서비스
			InqueryService is = new InqueryService();
			int result = is.replyWrite(vo);
			
			System.out.println("답글 작성 결과 : " + result);
			// 화면 == 문자열 내보내기
			PrintWriter out = resp.getWriter();
			if(result == 1) {
				out.write("ok");
			}
			
		} catch (Exception e) {
			System.out.println("[ERROR] noticeReply err");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "댓글 작성 살패,,");
			req.getRequestDispatcher("").forward(req, resp);
		}
	
	}
	
}
