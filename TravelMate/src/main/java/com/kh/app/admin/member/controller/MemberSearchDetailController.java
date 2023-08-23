package com.kh.app.admin.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.vo.MemberDetailVo;

@WebServlet("/admin/membersearchdetail")
public class MemberSearchDetailController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			
			MemberDetailVo vo = as.memberSearchDetail(no);
			
			if(vo == null) {
				throw new IllegalStateException();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/memberSearchDetail.jsp").forward(req, resp);
		}catch(Exception e) {
			req.setAttribute("errMsg", "회원조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			System.out.println("[ERR-103] 회원조회 에러");
			e.printStackTrace();
		}
	}
	
}
