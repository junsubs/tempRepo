package com.kh.app.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.service.CarService;
import com.kh.app.product.service.RoomService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.CarVo;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;

@WebServlet("/pay/car")
public class PayCarController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new IllegalStateException("로그인 하고 오세요");
			}
			
			//캘린더
			String daterange = req.getParameter("daterange");
			String[] dateArr= daterange.split(" - ");
			String startDate = dateArr[0];
			String endDate = dateArr[1];
			
			//데꺼
			String no = req.getParameter("no");
			String mname = req.getParameter("mname");
			String phone = req.getParameter("phone");
			String price = req.getParameter("price");
			String email = req.getParameter("email");
			CarVo vo = new CarVo();
			vo.setNo(no);
			System.out.println(no);
			vo.setMname(mname);
			vo.setPhone(phone);
			vo.setAddress(email);
			vo.setPrice(price);
			vo.setStartDate(startDate);
			vo.setEndDate(endDate);

			CarService cs = new CarService();
			int result = cs.carOrder(vo, loginMember);
			vo = cs.carSelectOrder(loginMember); 
			if(vo != null) {
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/WEB-INF/views/pay/pay_car.jsp").forward(req, resp);
			}else {
				throw new Exception();
			}
			
			
		}catch (Exception e) {
			System.out.println("[ERROR] pay car errr....");
			e.printStackTrace();
			
			req.setAttribute("errorMsg", "pay car controller error");
			req.getRequestDispatcher("~").forward(req, resp);
		}
	}
	
}
