package com.kh.app.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.product.service.RoomService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;

@WebServlet("/order/souvenir")
public class OrderSouvenirController extends HttpServlet{

	private final  SouvenirService ss = new SouvenirService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//데꺼
			String name = req.getParameter("name");
			//데뭉
			
			//서비스
			SouvenirVo vo = ss.selectSouvenirOneByNo(name);
			
			//화면
			if(vo != null) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/order/order_souvenir.jsp").forward(req, resp);
			}else { 
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("[ERROR] notice detail errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "상세조회 실패");
			req.getRequestDispatcher("~").forward(req, resp);
		}

}
	
}
