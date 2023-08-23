package com.kh.app.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.util.AttachmentVo;
import com.kh.app.admin.util.FileUploader;
import com.kh.app.admin.vo.AccommodationInventoryVo;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 10
	)
@WebServlet("/admin/accommodationWrite")
public class AccommodationWriteController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/accommodationWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String name = req.getParameter("name");
			String local = req.getParameter("local");
			String kind = req.getParameter("kind");
			String maxPeople = req.getParameter("maxPeople");
			String price = req.getParameter("price");
			
			String path = req.getServletContext().getRealPath("/static/img/accommodationImg/");	
			Part f = req.getPart("f");
			AttachmentVo attVo = FileUploader.saveFile(path, f);
				
			AccommodationInventoryVo vo = new AccommodationInventoryVo();
			vo.setName(name);
			vo.setLocalName(local);
			vo.setKind(kind);
			vo.setMaxPeople(maxPeople);
			vo.setChangeName(attVo.getChangeName());
			vo.setOriginName(attVo.getOriginName());
			vo.setPrice(price);
			
			int result = as.accommodationInventoryWrite(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "정상적으로 등록되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/accommodationinventory");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "숙소조회에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}

}
