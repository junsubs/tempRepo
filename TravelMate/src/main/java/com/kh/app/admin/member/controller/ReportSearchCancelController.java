package com.kh.app.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;

@WebServlet("/admin/reportsearchCancel")
public class ReportSearchCancelController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String memberNick = req.getParameter("nick");
			String no = req.getParameter("no");

			//서비스
			int result = as.reportSearchCancel(memberNick);
			
			if(result != 1){
				throw new Exception();
			}
		}catch(Exception e) {
			req.setAttribute("errMsg", "회원제재취소 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-102] 회원제재취소 에러");
			e.printStackTrace();
		}
	}

}
