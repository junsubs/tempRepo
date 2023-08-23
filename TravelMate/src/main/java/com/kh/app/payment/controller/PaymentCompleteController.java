package com.kh.app.payment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.product.service.RoomService;
import com.kh.app.product.vo.RoomVo;
@WebServlet("/payment/complete")
public class PaymentCompleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String reservationno = req.getParameter("reservationno");
			System.out.println(reservationno);
			RoomService rms = new RoomService();
			int result = rms.roomPayment(reservationno);
			if(result == 1) {
				resp.sendRedirect(req.getContextPath()+"/room/list");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
}
