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
import com.kh.app.admin.vo.ReportSearchVo;
import com.kh.app.common.page.PageVo;

@WebServlet("/admin/reportSearch")
public class ReportSearchController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//페이징처리 데이터 뭉치기
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
		
			int cnt = as.reportSearchCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 7);
			
			//서비스
			List<ReportSearchVo> voList = null;
			if(searchType == null || searchType.equals("")) {
				voList = as.reportSearch(pv);
			}else {
				voList = as.reportSearch(pv, searchType, searchValue);				
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			//화면
			if(voList != null) {
				req.setAttribute("searchVo", map);
				req.setAttribute("pv", pv);
				req.setAttribute("voList", voList);
				req.getRequestDispatcher("/WEB-INF/views/admin/reportSearch.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("[ERR-102] 제재이력조회 에러");
			e.printStackTrace();
			
			req.setAttribute("errMsg", "제재이력조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
