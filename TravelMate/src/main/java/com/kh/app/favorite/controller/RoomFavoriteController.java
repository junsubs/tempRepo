package com.kh.app.favorite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.service.RoomService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;

@WebServlet("/room/favorite")
public class RoomFavoriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new IllegalStateException("로그인 하고 오세요");
			}
			
			
			String no = req.getParameter("no");
			String name = req.getParameter("name");
			String mno = req.getParameter("mno");
			
			RoomService rs = new RoomService();
			RoomVo vo = rs.selectRoomOneByNo(name);
			
			int result = rs.roomFavorite(no, name, loginMember);
			
			if(result == 1) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/order/order_room.jsp").forward(req, resp);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
}
