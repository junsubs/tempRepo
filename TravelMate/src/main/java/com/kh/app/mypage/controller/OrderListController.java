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

import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.service.OrderListService;
import com.kh.app.mypage.vo.OrderListVo;

@WebServlet("/mypage/orderList")
public class OrderListController extends HttpServlet{

	
	// 주문 내역 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			HttpSession session = req.getSession();
		 	MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
		 	String mno = loginMember.getNo();
		 	
			OrderListService ols = new OrderListService();
			
			// 데이터 준비
			int cnt = ols.getOrderListCntByNo(mno);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 10);
			
			// 서비스
			List<OrderListVo> voList = null;
			voList = ols.getOrderListByNo(pv, mno, searchType, searchValue);
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			// 화면
			req.setAttribute("searchVo", map);	
			req.setAttribute("pv", pv);
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/mypage/orderList.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("[ERROR] orderList err");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "목록 조회 실패...");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	}
}
	
	

