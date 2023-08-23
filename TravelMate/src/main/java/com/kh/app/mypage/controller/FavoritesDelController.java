package com.kh.app.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.service.FavoriteService;
import com.kh.app.mypage.vo.FavoriteVo;

@WebServlet("/mypage/favorites/del")
public class FavoritesDelController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new IllegalStateException("로그인 먼저 하세요");
			}
			
			// 데준
			String no = req.getParameter("no");
			String mno = loginMember.getNo();

			// 데뭉
			FavoriteVo vo = new FavoriteVo();
			vo.setNo(no);
			vo.setMemberNo(mno);
			
			// 서비스
			FavoriteService fs = new FavoriteService();
			int result = fs.delete(vo);
			
			// 화면
			if(result == 1) {
				resp.sendRedirect(req.getContextPath() + "/mypage/favorites");
			}else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "favorites del fail");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
		
	}
	
}
