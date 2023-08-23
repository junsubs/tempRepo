package com.kh.app.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;

@WebServlet("/admin/sellrequestRufuse")
public class SellRequestRefuseController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			
			//서비스
			int result = as.sellRequestRefuse(no);
			
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("왕");
		}catch(Exception e) {
			req.setAttribute("errMsg", "판매등록요청 거절 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-206] 판매등록요청 거절 에러");
			e.printStackTrace();
		}
	}
	
}
