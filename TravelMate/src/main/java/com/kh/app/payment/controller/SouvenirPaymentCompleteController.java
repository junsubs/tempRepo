package com.kh.app.payment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.product.service.RoomService;
import com.kh.app.product.service.SouvenirService;
import com.kh.app.product.vo.RoomVo;
@WebServlet("/souvenir/payment/complete")
public class SouvenirPaymentCompleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String reservationno = req.getParameter("reservationno");
			System.out.println(reservationno);
			SouvenirService ss = new SouvenirService();
			int result = ss.souvenirPayment(reservationno);
			if(result == 1) {
				resp.sendRedirect(req.getContextPath()+"/souvenir/list");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
}
