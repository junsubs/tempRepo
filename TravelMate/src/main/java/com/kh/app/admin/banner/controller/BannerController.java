package com.kh.app.admin.banner.controller;

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
import com.kh.app.admin.vo.AdBannerVo;
import com.kh.app.common.page.PageVo;

@WebServlet("/admin/banner")
public class BannerController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			int cnt = as.adBannerCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 7);
			
			//서비스
			List<AdBannerVo> voList = null;
			if(searchType == null || searchType.equals("")) {
				voList = as.adBanner(pv);			
			}else {
				voList = as.adBanner(pv, searchType, searchValue);				
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			
			if(voList != null) {
				req.setAttribute("searchVo", map);
				req.setAttribute("voList", voList);
				req.setAttribute("pv", pv);
				req.getRequestDispatcher("/WEB-INF/views/admin/adBanner.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			req.setAttribute("errMsg", "판매등록요청조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-301] 광고배너관리 에러");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
