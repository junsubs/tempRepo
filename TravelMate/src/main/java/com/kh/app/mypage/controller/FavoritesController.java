package com.kh.app.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.mypage.service.FavoriteService;
import com.kh.app.mypage.vo.FavoriteVo;

@WebServlet("/mypage/favorites")
public class FavoritesController extends HttpServlet{

	// 관심상품 
	// 회원번호로 select 해서 차량, 숙소, 기념품 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			FavoriteService fs = new FavoriteService();

			// 데이터 준비
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			String mno = loginMember.getNo();
			int cnt = fs.getFavListCntByNo(mno);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 2, 3);
			
			// 서비스
			List<FavoriteVo> cvoList = null;
			List<FavoriteVo> avoList = null;
			List<FavoriteVo> svoList = null;
			cvoList = fs.getCarFavListByNo(pv,mno);
			avoList = fs.getAccomFavListByNo(pv, mno);
			svoList = fs.getSouvenirFavListByNo(pv, mno);
			
			// 결과 화면
			req.setAttribute("pv", pv);
			req.setAttribute("cvoList", cvoList);
			req.setAttribute("avoList", avoList);
			req.setAttribute("svoList", svoList);
			
			req.getRequestDispatcher("/WEB-INF/views/mypage/favorites.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
		
	
	}
	
	
}
