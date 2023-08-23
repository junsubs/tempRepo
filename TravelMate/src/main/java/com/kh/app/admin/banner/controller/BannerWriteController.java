package com.kh.app.admin.banner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.kh.app.admin.service.AdminService;
import com.kh.app.admin.util.AttachmentVo;
import com.kh.app.admin.util.FileUploader;
import com.kh.app.admin.vo.AdBannerVo;
import com.kh.app.admin.vo.SouvenirInventoryVo;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 10
	)
@WebServlet("/admin/bannerwrite")
public class BannerWriteController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/admin/bannerWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//데꺼
			String bannerName = req.getParameter("bannerName");
			String memberNick = req.getParameter("memberNick");
			String souvenirName = req.getParameter("souvenirName");
			
			String path = req.getServletContext().getRealPath("/static/img/adBanner/");	
			Part f = req.getPart("f");
			AttachmentVo attVo = FileUploader.saveFile(path, f);
			
			String souvenirNo = as.souvenirGetNo(souvenirName);
			System.out.println(souvenirNo);

			AdBannerVo vo = new AdBannerVo();
			vo.setName(bannerName);
			vo.setNick(memberNick);
			vo.setSouvenirName(souvenirName);
			vo.setImage(attVo.getChangeName());
			vo.setSouvenirNo(souvenirNo);;
			
			int result = as.AdbannerWrite(vo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "게시글이 등록되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/banner");
		}catch(Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("alertMsg", "광고배너 등록에 실패하였습니다. 기념품명을 확인하세요.");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}

}
