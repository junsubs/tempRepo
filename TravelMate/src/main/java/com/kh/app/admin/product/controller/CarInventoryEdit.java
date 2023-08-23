package com.kh.app.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.CarInventoryVo;

@WebServlet("/admin/carinventoryEdit")
public class CarInventoryEdit extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			
			CarInventoryVo vo = as.carInventoryEdit(no);
			
			if(vo == null) {
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/carInventoryEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "차량재고조회 재고수정 화면조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String no = req.getParameter("carNo");
			String count = req.getParameter("count");
			
			//서비스
			int result = as.carCountEdit(no, count);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "수정되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/carinventory");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "차량재고조회 재고수정 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
}
