package com.kh.app.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.ReportListDetailVo;
import com.kh.app.admin.vo.ReportSearchDetailVo;

@WebServlet("/admin/reportsearchdetail")
public class ReportSearchDetailController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//서비스
			String no = req.getParameter("no");

			//화면보여주기
			ReportSearchDetailVo vo = as.reportSearchDetail(no);
			
			if(vo != null) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/admin/reportSearchDetail.jsp").forward(req, resp);			
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			req.setAttribute("errMsg", "신고내역상세조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-101] 신고내역상세조회 에러");
			e.printStackTrace();
		}
	
	}

}
