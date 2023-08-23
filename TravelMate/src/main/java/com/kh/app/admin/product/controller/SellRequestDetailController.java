package com.kh.app.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.SellRequestDetailVo;

@WebServlet("/admin/sellrequestdetail")
public class SellRequestDetailController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String no = req.getParameter("no");

			//서비스
			SellRequestDetailVo vo = as.sellRequestDetail(no);

			if(vo == null) {
				throw new IllegalStateException();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/sellrequestDetail.jsp").forward(req, resp);
		}catch(Exception e) {
			req.setAttribute("errMsg", "판매등록요청조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-204] 판매등록요청조회 에러");
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");

			//서비스
			int result = as.sellRequestEnroll(no);
			
			if(result != 1) {
				throw new IllegalStateException();
			}
			
			req.getSession().setAttribute("alertMsg", "상품이 정상적으로 등록되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/sellrequest");
		}catch(Exception e) {
			req.setAttribute("errMsg", "판매등록요청조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-204] 판매등록요청조회 에러");
			e.printStackTrace();
		}
	}
}
