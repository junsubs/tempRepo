package com.kh.app.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.util.BoardImgVo;
import com.kh.app.util.FileUploader;

@WebServlet(urlPatterns = "/free/write")
public class BoardFreeWriteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		로그인 안하면 에러페이지로 보내기
		HttpSession session = req.getSession();
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
		if(loginMember == null) {
			req.setAttribute("errorMsg", "로그인을 먼저 해주세요");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("/WEB-INF/views/board/board-free-write.jsp").forward(req, resp);
	}
	
	@Override
	//
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			// 데꺼
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String memberNo = loginMember.getNo();
			
			// 데뭉
			BoardVo bvo = new BoardVo();
			bvo.setTitle(title);
			bvo.setContent(content);
			bvo.setMemberNo(memberNo);
			
			// 서비스
			BoardService bs = new BoardService();
			int result = bs.freeWrite(bvo);
			// 화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() + "/free/list");
			}else {
				throw new IllegalStateException("게시글 작성 결과 1 아님 ..."); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}