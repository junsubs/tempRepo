package com.kh.app.board.controller;

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
import javax.websocket.Session;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet( "/free/list")
public class BoardFreeListController extends HttpServlet{
	
	private final BoardService bs = new BoardService();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String searchValue = req.getParameter("searchValue");
			String searchType = req.getParameter("searchType");
			
			int cnt = bs.getBoardListCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 10);
			
			
			//tqt
			List<BoardVo> fvoList =null;
			if(searchValue == null || searchValue.equals("")) {
				fvoList = bs.freeList(pv);
			}else {
				fvoList = bs.freeList(pv, searchValue, searchType);
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchVo", searchValue);
			map.put("searchVo", searchType);
			
			
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("fvoList", fvoList);
			req.getRequestDispatcher("/WEB-INF/views/board/board-free-list.jsp").forward(req, resp);
			
		}catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
	}
	
	
}