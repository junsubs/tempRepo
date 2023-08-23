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
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;
@WebServlet("/souvenir/payment/creditCard")
public class SouvenirCreditCardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
			String no = req.getParameter("no");
			
			SouvenirVo vo = new SouvenirVo();
			SouvenirService ss = new SouvenirService();
			vo = ss.selectOrder(loginMember); 
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/WEB-INF/views/payment/SouvenirCreditCard.jsp").forward(req, resp);
		}catch (Exception e) {
			
			// TODO: handle exception
		}
	
	}
	
}
