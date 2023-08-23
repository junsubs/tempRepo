package com.kh.app.paycancel.controller;


import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.paycancel.dao.AccomodationPayCancelDao;
import com.kh.app.paycancel.dao.CarPayCancelDao;
import com.kh.app.paycancel.vo.PayCancelVo;
import com.kh.app.paycancel.vo.SelectPayCancelVo;
import com.kh.app.product.vo.CarVo;

@WebServlet("/room/refund")
public class AccomodationRefundController extends HttpServlet{

	//결제취소화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection conn3 = null;
		SelectPayCancelVo spcvo = new SelectPayCancelVo();
		try {
			//주문내역에서 가져온 payNo로 결제번호/가격 가져오기
			String payNo = req.getParameter("payNo");
			conn3 = JDBCTemplate.getConnection();
			String sql = "SELECT PRICE FROM ACCOMODATION_RESERVATION AR JOIN ACCOMODATION_PAYMENT AP ON AR.NO = AP.ACCOMODATION_RESERVATION_CODE WHERE AP.NO = ?";
			PreparedStatement pstmt = conn3.prepareStatement(sql);
			pstmt.setString(1, payNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				spcvo.setPayNo(payNo);
				spcvo.setPrice(rs.getString("PRICE"));
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn3);
		}
		catch(Exception e) {
				e.printStackTrace();
		}
		
		req.setAttribute("spcvo", spcvo);
		req.getRequestDispatcher("/WEB-INF/views/paycancel/room_refund.jsp").forward(req, resp);
	
	}

	//결제취소
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccomodationPayCancelDao acdao = new AccomodationPayCancelDao();
		
		//마이페이지>주문내역 > 환불하기 > 환불컨트롤러 > 환불화면 > 환불진행(결제 테이블 yn 업데이트 / 취소사유 테이블 인서트)
		
		Connection conn = null;
		Connection conn2 = null;
		
			
			
		//payment table cancelyn column 'Y' update --완료
		String payNo = req.getParameter("payNo");
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			
			result = acdao.accomodationPaymentUpdate(conn, payNo);
		}catch(Exception e) {
			e.printStackTrace();
		}
					
			
			
		//paycancelreason insert -- 완료
		int result2 = 0;
			try {
				String AccomodationPaymentNo = req.getParameter("payNo");
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				
				PayCancelVo pcvo = new PayCancelVo();
				pcvo.setAccomodationPaymentNo(AccomodationPaymentNo);
				pcvo.setTitle(title);
				pcvo.setContent(content);
				
				conn2 = JDBCTemplate.getConnection();
				
				result2 = acdao.accomodationPayCancelReason(conn2, pcvo);
						
				JDBCTemplate.close(conn);
				JDBCTemplate.close(conn2);
			}catch(Exception e) {
				e.printStackTrace();
			}
	
			if(result == 1 && result2 == 1) {
				resp.sendRedirect(req.getContextPath() + "/mypage/orderList");
			}else {
				req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			}
			
		
	}



}


