package com.kh.app.mypage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/mypage/myBoardList")
public class MyBoardListController extends HttpServlet{

	private final BoardService bs = new BoardService();

	// 게시글 목록
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
		 	MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
		 	String mno = loginMember.getNo();
		 	
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			BoardService bs = new BoardService();
			// 데이터 준비
			int cnt = bs.getMyBoardListCntByNo(searchType, searchValue, mno);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 10);
			
			// 서비스
			List<BoardVo> voList = null;
			if(searchType == null || searchValue.equals("")) {
				voList = bs.getMyBoardListByNo(pv, mno);
			}else {
				voList = bs.getMyBoardListByNo(pv, searchType, searchValue, mno);
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			// 화면
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/mypage/myBoardList.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[ERROR] MyboardList err");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "목록 조회 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	}
	
}
