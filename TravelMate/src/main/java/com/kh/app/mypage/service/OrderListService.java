package com.kh.app.mypage.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.mypage.dao.OrderListDao;
import com.kh.app.mypage.vo.OrderListVo;

public class OrderListService {

	OrderListDao dao = new OrderListDao();
	// 주문 내역 개수(회원번호로)
	public int getOrderListCntByNo(String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getOrderListCntByNo(conn, mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	

	// 주문 내역 조회
	public List<OrderListVo> getOrderListByNo(PageVo pv, String mno, String searchType, String searchValue) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<OrderListVo> voList = dao.getOrderListByNo(conn,pv,mno, searchType, searchValue);

		JDBCTemplate.close(conn);
		
		
		return voList;
	}


	

}
