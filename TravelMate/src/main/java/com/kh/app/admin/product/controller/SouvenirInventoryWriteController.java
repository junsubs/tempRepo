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
import com.kh.app.admin.vo.SouvenirInventoryVo;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 10
	)
@WebServlet("/admin/souvenirinventoryWrite")
public class SouvenirInventoryWriteController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/souvenirinventoryWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String name = req.getParameter("name");
			String local = req.getParameter("local");
			String content = req.getParameter("content");
			String price = req.getParameter("price");
			String count = req.getParameter("count");
			
			String path = req.getServletContext().getRealPath("/static/img/souvenir/");	
			Part f = req.getPart("f");
			AttachmentVo attVo = FileUploader.saveFile(path, f);
			
			//데뭉
			SouvenirInventoryVo vo = new SouvenirInventoryVo();
			vo.setName(name);
			vo.setLocalName(local);
			vo.setPrice(price);
			vo.setChangeName(attVo.getChangeName());
			vo.setOriginName(attVo.getOriginName());
			vo.setCount(count);
			
			//서비스
			int result = as.souvenirInventoryWrite(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			resp.sendRedirect(req.getContextPath() + "/admin/souvenirinventory");		
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "기념품재고조회 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	}

}
