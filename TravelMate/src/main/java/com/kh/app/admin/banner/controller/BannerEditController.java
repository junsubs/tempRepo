package com.kh.app.admin.banner.controller;

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
import com.kh.app.admin.vo.AdBannerVo;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 * 10
	)
@WebServlet("/admin/banneredit")
public class BannerEditController extends HttpServlet{
	private final AdminService as = new AdminService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String no = req.getParameter("no");
			
			AdBannerVo vo = as.AdBannerEdit(no);
			
			if(vo == null) {
				throw new Exception();
			}
			
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/admin/bannerEdit.jsp").forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "광고배너 수정화면 에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String bannerName = req.getParameter("bannerName");
			String memberNick = req.getParameter("memberNick");
			String souvenirName = req.getParameter("souvenirName");
			String no = req.getParameter("no");
			String memberNo = as.bannerGetNo(memberNick);
			
			String path = req.getServletContext().getRealPath("/static/img/adBanner/");	
			Part f = req.getPart("f");
			AttachmentVo attVo = FileUploader.saveFile(path, f);
			
			AdBannerVo vo = new AdBannerVo();
			vo.setName(bannerName);
			vo.setNick(memberNick);
			vo.setImage(attVo.getChangeName());
			vo.setSouvenirName(souvenirName);
			vo.setNo(no);
			
			int result = as.adBannerContentEdit(vo, memberNo);
			
			if(result != 1) {
				throw new Exception();
			}
			
			req.getSession().setAttribute("alertMsg", "게시글이 수정되었습니다.");
			resp.sendRedirect(req.getContextPath() + "/admin/banner");
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errMsg", "광고배너 수정에러");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	}


}
