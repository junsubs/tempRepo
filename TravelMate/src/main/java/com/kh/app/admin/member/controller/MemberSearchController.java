package com.kh.app.admin.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.MemberDetailVo;
import com.kh.app.admin.vo.MemberSearchVo;
import com.kh.app.common.page.PageVo;

@WebServlet("/admin/memberSearch")
public class MemberSearchController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {	
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			int cnt = as.memberSearchCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 7);
			
			//서비스
			AdminService as = new AdminService();
			List<MemberSearchVo> voList = null;
			if(searchType == null || searchType.equals("")) {
				voList = as.memberSearch(pv);	
			}else {
				voList = as.memberSearch(pv, searchType, searchValue);			
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			//화면
			if(voList != null) {
				req.setAttribute("searchVo", map);
				req.setAttribute("pv", pv);
				req.setAttribute("voList", voList);
				req.getRequestDispatcher("/WEB-INF/views/admin/memberSearch.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			req.setAttribute("errMsg", "회원조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-103] 회원조회 에러");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
