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
import com.kh.app.admin.vo.ReportListVo;
import com.kh.app.common.page.PageVo;

@WebServlet("/admin/reportlist")
public class ReportListController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			
			int cnt = as.reportListCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 7);
			
			//서비스
			List<ReportListVo> reportVoList = null;
			if(searchType == null || searchType.equals("")) {
				reportVoList = as.reportList(pv);		
			}else {
				reportVoList = as.reportList(pv, searchType, searchValue);				
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			
			//화면보여주기
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("reportVoList", reportVoList);

			req.getRequestDispatcher("/WEB-INF/views/admin/reportList.jsp").forward(req, resp);
		}catch(Exception e) {
			req.setAttribute("errMsg", "신고내역 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-101] 신고내역 조회 에러");
			e.printStackTrace();
		}
	}
	
	//회원제재
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String enrollDate = req.getParameter("startData");
			String cancelEnrollDate = req.getParameter("endData");
			String no = req.getParameter("no");
			
			//데뭉
			Map<String, String> map = new HashMap<>();
			map.put("enrollDate", enrollDate);
			map.put("cancelEnrollDate", cancelEnrollDate);
			map.put("no", no);
			
			//서비스
			int result = as.memberStopDate(map);
			int result2 = as.memberStop(map);
			int count = as.sanctionCount(map);
			
			if(result != 1 || result2 != 1 || count != 1) {
				throw new IllegalStateException();
			}

		}catch(Exception e) {
			req.setAttribute("errMsg", "신고내역 조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-101] 신고내역 조회 에러");
			e.printStackTrace();
		}
	}
}
