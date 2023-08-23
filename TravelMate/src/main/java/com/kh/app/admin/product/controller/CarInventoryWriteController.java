package com.kh.app.admin.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileUpload;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.util.AttachmentVo;
import com.kh.app.admin.util.FileUploader;
import com.kh.app.admin.vo.CarInventoryVo;

@MultipartConfig(
			maxFileSize = 1024 * 1024 * 50,
			maxRequestSize = 1024 * 1024 * 50 * 10
		)
@WebServlet("/admin/carinventoryWrite")
public class CarInventoryWriteController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/carinventoryWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String carKind = req.getParameter("carKind");
			String local = req.getParameter("local");
			String licensePlate = req.getParameter("licensePlate");
			String outDate = req.getParameter("outDate");
			String price = req.getParameter("price");
			
			String path = req.getServletContext().getRealPath("/static/img/carImg/");
			Part f = req.getPart("f");
			AttachmentVo attVo = FileUploader.saveFile(path, f);
			
			//데이터뭉치기
			CarInventoryVo vo = new CarInventoryVo();
			vo.setKind(carKind);
			vo.setName(local);
			vo.setLicensePlate(licensePlate);
			vo.setLicenseDate(outDate);
			vo.setPrice(price);
			vo.setChangeName(attVo.getChangeName());
			vo.setOriginName(attVo.getOriginName());
			
			//서비스
			int result = as.carInventoryWrite(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "정상적으로 등록되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/carinventory");
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "차량재고조회 글작성 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
}
