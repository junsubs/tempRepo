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
import com.kh.app.admin.vo.AccommodationInventoryVo;

@WebServlet("/admin/accommodationEdit")
public class AccommodationEditController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");

			//서비스
			AccommodationInventoryVo vo = as.accommodationEdit(no);
			
			if(vo == null) {
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/accommodationEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "숙소조회 수정화면에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String count = req.getParameter("count");
			String no = req.getParameter("no");
			
			//데뭉
			Map<String, String> map = new HashMap<>();
			map.put("count", count);
			map.put("no", no);
			
			//서비스
			int result = as.accommodationCountEdit(map);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "수정되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/accommodationinventory");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "숙소조회 수정에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}

}
