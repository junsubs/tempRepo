package com.kh.app.admin.banner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;

@WebServlet("/admin/bannerdelete")
public class BannerDeleteController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			
			int result = as.bannerDelete(no);
			
			if(result != 1) {
				throw new IllegalStateException();
			}
			
			req.getSession().setAttribute("alertMsg", "삭제가 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "광고배너삭제 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
}
