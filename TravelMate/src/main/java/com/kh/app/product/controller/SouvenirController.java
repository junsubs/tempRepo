package com.kh.app.product.controller;

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
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;

@WebServlet("/souvenir/list")
public class SouvenirController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			String local = req.getParameter("local");
			
			SouvenirService ss = new SouvenirService();
			
			int cnt = ss.getSouvenirListCnt(searchType , searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 5);
			
			//서비스
			List<SouvenirVo> voList = null;
			if(searchType == null || searchType.equals("")) {
				voList = ss.getSouvenirList(pv);
			}else {
				voList = ss.getSouvenirList(pv, searchType, searchValue, local);
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			map.put("local", local);
			
			//화면
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/product/souvenirList.jsp").forward(req, resp);
		}catch (Exception e) {
			System.out.println("[ERROR] 상품 목록 조회 에러 ...");
			e.printStackTrace();
			
			req.setAttribute("errorMsg" , "목록 조회 실패 ..");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	
	
	}
	
}
