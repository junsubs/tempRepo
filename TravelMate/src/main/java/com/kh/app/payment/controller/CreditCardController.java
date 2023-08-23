package com.kh.app.payment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.service.RoomService;
import com.kh.app.product.vo.RoomVo;
@WebServlet("/payment/creditCard")
public class CreditCardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String no = req.getParameter("no");
			
			RoomVo vo = new RoomVo();
			RoomService rms = new RoomService();
			vo = rms.roomSelectOrder(loginMember); 
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/payment/creditCard.jsp").forward(req, resp);
		}catch (Exception e) {
			
			// TODO: handle exception
		}
	
	}
	
}
