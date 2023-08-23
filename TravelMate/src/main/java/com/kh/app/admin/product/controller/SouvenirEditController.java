package com.kh.app.admin.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.SouvenirInventoryVo;

@WebServlet("/admin/souvenirEdit")
public class SouvenirEditController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");

			//서비스
			SouvenirInventoryVo vo = as.souvenirEdit(no);
			
			if(vo == null) {
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/souvenirEdit.jsp").forward(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "기념품재고조회 수정화면에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			String count = req.getParameter("count");
			
			Map<String, String> map = new HashMap<>();
			map.put("no", no);
			map.put("count", count);
			
			//서비스
			int result = as.souvenirCountEdit(map);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "수정이 완료되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/souvenirinventory");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "기념품재고조회 수정에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
}
