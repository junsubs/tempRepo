package com.kh.app.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.product.service.CarService;
import com.kh.app.product.service.RoomService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.CarVo;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;
@WebServlet("/order/car")
public class OrderCarController extends HttpServlet{
	
	private final CarService cs = new CarService();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//데꺼
			String name = req.getParameter("name");
			//데뭉
			
			//서비스
			CarVo vo = cs.selectCarOneByName(name);
			
			//화면
			if(vo != null) {
				req.setAttribute("vo", vo);
				System.out.println(vo);
				req.getRequestDispatcher("/WEB-INF/views/order/order_car.jsp").forward(req, resp);
			}else { 
				throw new Exception();
			}
		}catch(Exception e) {
			System.out.println("[ERROR] order car errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "order car error ...");
			req.getRequestDispatcher("~").forward(req, resp);
		}
	}
	
	
}//class
