package com.kh.app.cs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.common.page.PageVo;
import com.kh.app.cs.service.FAQService;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.FAQVo;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/faq")
public class FAQListcontroller extends HttpServlet{

		private final FAQService fs = new FAQService();
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			try {
						
				String no = req.getParameter("no");
				FAQVo vo = new FAQVo();
				vo.setNo(no);
				
				// 데이터 준비
				int cnt = fs.getFAQListCnt();
				String page_ = req.getParameter("page");
				if(page_ == null) {
					page_ = "1";
				}
				int page = Integer.parseInt(page_);
				PageVo pv = new PageVo(cnt, page, 5, 10);
				
				// 서비스
				
				List<FAQVo> voList = null;
			    voList = fs.getFAQList(pv);
				
				// 화면
				req.setAttribute("pv", pv);
				req.setAttribute("voList", voList);
				req.getRequestDispatcher("/WEB-INF/views/CScenter/FAQ.jsp").forward(req, resp);
				
				} catch (Exception e) {
					System.out.println("[ERROR] FAQ list controller err");
					e.printStackTrace();
					
					req.setAttribute("errorMsg", "FAQ 목록 조회 실패..");
					req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
				}
			}
		
}

