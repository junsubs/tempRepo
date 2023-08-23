package com.kh.app.favorite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.service.CarService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.CarVo;
import com.kh.app.product.vo.SouvenirVo;

@WebServlet("/car/favorite")
public class CarFavoriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new IllegalStateException("로그인 하고 오세요");
			}
			
			
			String no = req.getParameter("no");
			System.out.println(no);
			String name = req.getParameter("name");
			String mno = req.getParameter("mno");
			
			CarService cs = new CarService();
			CarVo vo = cs.selectCarOneByName(name);
			
			int result = cs.carFavorite(no, name, loginMember);
			
			if(result == 1) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/order/order_car.jsp").forward(req, resp);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	
}
