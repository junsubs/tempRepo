package com.kh.app.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.SellRequestVo;
import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/sell/request/list")
public class SellRequestListController extends HttpServlet{
	
	//	멤버 카테고리 "1" 번인 관리자의 신분으로 모든 판매등록글 리스트 조회
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String memberNo = loginMember.getNo();
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			BoardService bs = new BoardService();
			
			AdminService as = new AdminService();
			int cnt = as.sellRequestCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 7);
			
//			List<BoardVo> voList = bs.sellRequestList(pv);
			List<BoardVo>voList2 = bs.sellRequestList(pv , memberNo);
			
			//관리자
			if(voList2 !=null){
//				req.setAttribute("voList", voList);
//				req.setAttribute("pv", pv);
//				req.getRequestDispatcher("/WEB-INF/views/board/sell-request-list.jsp").forward(req, resp);
				
				//판매자
				req.setAttribute("voList", voList2);
				req.getRequestDispatcher("/WEB-INF/views/board/my-sell-request-list.jsp").forward(req, resp);
			}
			
				
			
			
			
			//화면 
//			if( voList !=null ){
//				req.setAttribute("lgoinMember", loginMember);
//				req.setAttribute("voList", voList);
//				req.getRequestDispatcher("/WEB-INF/views/board/admin-private-sell-request-list.jsp").forward(req, resp);
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
