package com.kh.app.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.AdBannerVo;
import com.kh.app.admin.vo.ReportListVo;
import com.kh.app.admin.vo.SellRequestVo;
import com.kh.app.common.page.PageVo;

@WebServlet(urlPatterns = "/admin/home")
public class AdminHomeController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//신고내역
			List<ReportListVo> reportList = as.reportList(new PageVo(6, 1, 1, 6));
			req.setAttribute("reportList", reportList);
			
			//판매등록요청
			List<SellRequestVo> sellRequestList = as.sellRequest(new PageVo(6, 1, 1, 6));
			req.setAttribute("sellRequestList", sellRequestList);
			
			//광고배너관리
			List<AdBannerVo> bannerList = as.adBanner(new PageVo(6, 1, 1, 6));	
			req.setAttribute("bannerList", bannerList);
			
			req.getRequestDispatcher("/WEB-INF/views/admin/home.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "홈화면조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	}
	
}
