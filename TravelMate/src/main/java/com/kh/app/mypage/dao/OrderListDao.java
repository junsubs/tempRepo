package com.kh.app.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.mypage.vo.OrderListVo;

public class OrderListDao {

	// 주문내역 개수 (회원번호로)
	public int getOrderListCntByNo(Connection conn, String mno) throws Exception {

		// SQL
		String sql = "SELECT SUM(T.cnt) FROM ( SELECT COUNT(*) AS cnt FROM ( SELECT CP.NO, CP.TYPE, CP.PAY_DATE, CR.RENTCAR_NO, CR.PRICE, CR.MEMBER_NO FROM CAR_PAYMENT CP JOIN CAR_RESERVATION CR ON (CP.CAR_RESERVATION_CODE = CR.NO) WHERE CP.CANCEL_YN = 'N' AND CR.MEMBER_NO = ? ) car_payments UNION ALL SELECT COUNT(*) AS cnt FROM ( SELECT AP.NO, AP.TYPE, AP.PAY_DATE, AR.ACCOMODATION_NO, AR.PRICE, AR.MEMBER_NO FROM ACCOMODATION_PAYMENT AP JOIN ACCOMODATION_RESERVATION AR ON (AP.ACCOMODATION_RESERVATION_CODE = AR.NO) WHERE AP.CANCEL_YN = 'N' AND AR.MEMBER_NO = ? ) accomodation_payments UNION ALL SELECT COUNT(*) AS cnt FROM ( SELECT SP.NO, SP.TYPE, SP.PAY_DATE, SR.SOUVENIR_NO, SR.PRICE, SR.MEMBER_NO FROM SOUVENIR_PAYMENT SP JOIN SOUVENIR_RESERVATION SR ON (SP.SOUVENIR_RESERVATION_CODE = SR.NO) WHERE SP.CANCEL_YN = 'N' AND SR.MEMBER_NO = ? ) souvenir_payments ) T";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setString(2, mno);
		pstmt.setString(3, mno);
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return cnt;
				
	}


	// 주문내역 조회 (카테고리 값에 맞게 동적 쿼리 작성)
	public List<OrderListVo> getOrderListByNo(Connection conn, PageVo pv, String mno, String searchType, String searchValue) throws Exception {
		
		String sql = "";
		if("category".equals(searchType) && searchValue.equals("1")) {
			// sql (렌트카로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT CP.NO, CP.TYPE, CP.PAY_DATE, CR.RENTCAR_NO, CR.PRICE, CR.MEMBER_NO, CAST(CR.START_DATE AS DATE) AS START_DATE , CAST(CR.END_DATE AS DATE) AS END_DATE, CI.TITLE, CK.KIND AS GOODS_NAME FROM CAR_PAYMENT CP JOIN CAR_RESERVATION CR ON (CP.CAR_RESERVATION_CODE = CR.NO) JOIN RENTCAR R ON (R.NO = CR.RENTCAR_NO) JOIN CAR_IMG CI ON (CI.RENTCAR_NO = CR.RENTCAR_NO) JOIN CAR_KIND CK ON (CK.NO = R.CAR_KIND_NO) WHERE CP.CANCEL_YN = 'N' AND CR.MEMBER_NO = ? ORDER BY CP.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("category".equals(searchType) && searchValue.equals("2")) {
			// sql (숙소로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT AP.NO, AP.TYPE, AP.PAY_DATE, AR.ACCOMODATION_NO, AR.PRICE, AR.MEMBER_NO, CAST(AR.START_DATE AS DATE) AS START_DATE, CAST(AR.END_DATE AS DATE) AS END_DATE, AI.TITLE, A.NAME AS GOODS_NAME FROM ACCOMODATION_PAYMENT AP JOIN ACCOMODATION_RESERVATION AR ON (AP.ACCOMODATION_RESERVATION_CODE = AR.NO) JOIN ACCOMODATION A ON (A.NO = AR.ACCOMODATION_NO) JOIN ACCOMODATION_IMG AI ON (AI.ACCOMODATION_NO = AR.ACCOMODATION_NO) WHERE AP.CANCEL_YN = 'N' AND AR.MEMBER_NO = ? ORDER BY AP.NO DESC)T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("category".equals(searchType) && searchValue.equals("3")) {
			// sql (기념품으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT SP.NO, SP.TYPE, SP.PAY_DATE, SR.SOUVENIR_NO, SR.PRICE, SR.MEMBER_NO AS ONAME, SR.ADDRESS, SR.NAME, SR.CNT ,SI.TITLE, S.NAME AS GOODS_NAME FROM SOUVENIR_PAYMENT SP JOIN SOUVENIR_RESERVATION SR ON (SP.SOUVENIR_RESERVATION_CODE = SR.NO) JOIN SOUVENIR S ON (S.NO = SR.SOUVENIR_NO) JOIN SOUVENIR_IMG SI ON (SI.SOUVENIR_NO = SR.SOUVENIR_NO) WHERE SP.CANCEL_YN = 'N' AND SR.MEMBER_NO = ? ORDER BY SP.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			// 예외 던져주기
//			 throw new Exception();
			// 값이 이상하면 렌트카 목록 조회
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT CP.NO, CP.TYPE, CP.PAY_DATE, CR.RENTCAR_NO, CR.PRICE, CR.MEMBER_NO, CR.START_DATE, CR.END_DATE, CI.TITLE, CK.KIND AS GOODS_NAME FROM CAR_PAYMENT CP JOIN CAR_RESERVATION CR ON (CP.CAR_RESERVATION_CODE = CR.NO) JOIN RENTCAR R ON (R.NO = CR.RENTCAR_NO) JOIN CAR_IMG CI ON (CI.RENTCAR_NO = CR.RENTCAR_NO) JOIN CAR_KIND CK ON (CK.NO = R.CAR_KIND_NO) WHERE CP.CANCEL_YN = 'N' AND CR.MEMBER_NO = ?)T ) WHERE RNUM BETWEEN ? AND ?";
		}

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		
		// tx || rs
		List<OrderListVo> voList = new ArrayList<>();
		String startDate = null;
		String endDate = null;
		String address = null;
		String oderName = null;
		String cnt = null;
		while(rs.next()) {
			String no = rs.getString("NO");
			String payType = rs.getString("TYPE");
			String payDate = rs.getString("PAY_DATE");
			String price = rs.getString("PRICE");
			String img = rs.getString("TITLE");
			String goodsName = rs.getString("GOODS_NAME");
			if("3".equals(searchValue)) {
				address = rs.getString("ADDRESS");
				oderName = rs.getString("ONAME");
				cnt = rs.getString("CNT");
			}
			if(!"3".equals(searchValue)) {
				startDate = rs.getString("START_DATE");
				endDate = rs.getString("END_DATE");
			}
			
			OrderListVo vo = new OrderListVo();
			vo.setPayNo(no);
			vo.setPayType(payType);
			vo.setPayDate(payDate);
			vo.setPrice(price);
			vo.setImg(img);
			vo.setName(goodsName);
			if(!"3".equals(searchValue)) {
				vo.setStartDate(startDate);
				vo.setEndDate(endDate);
			}
			if("3".equals(searchValue)) {
				vo.setAddress(address);
				vo.setOderName(oderName);
				vo.setCnt(cnt);
			}
			
			voList.add(vo);
			
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);

		return voList;
	}


	

}
