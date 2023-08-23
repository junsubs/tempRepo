package com.kh.app.product.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.vo.CarVo;
import com.kh.app.product.vo.RoomVo;


public class CarDao {
	
	public int getCarListCnt(Connection conn, String searchType, String searchValue) throws Exception {
		//SQL -수정완료
				String sql = "SELECT COUNT(*) FROM ( SELECT * FROM RENTCAR) WHERE DELETE_YN = 'N'";
				if("RENTCAR".equals(searchType)) {
					sql += "AND NAME LIKE '%" + searchValue + "%'";
				}else if("writer".equals(searchType)) {
					sql += "AND NICK LIKE '%" + searchValue + "%'";
				}else if("category".equals(searchType)) {
					sql += "AND CATEGORY_NO = " + searchValue;
				}
				
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				//tx || rs
				int cnt = 0;
				if(rs.next()) {
					cnt = rs.getInt(1);
				}
				
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return cnt;
	}



	public List<CarVo> getCarList(Connection conn, PageVo pv) throws Exception {
		
		// SQL -수정완료
	    String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO, R.CAR_KIND_NO, R.LOCAL_NO, R.COUNT, R.ENROLL_DATE, R.DELETE_YN, R.MAX, R.LICENSE_PLATE, R.LICENSE_DATE, R.PRICE, CI.TITLE, LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND LC.NAME LIKE '%%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, pv.getBeginRow());
	    pstmt.setInt(2, pv.getLastRow());
	    ResultSet rs = pstmt.executeQuery();
	    
	    // tx || rs
	    List<CarVo> voList = new ArrayList<>();

	    while (rs.next()) {
	    	CarVo vo = new CarVo();
	    	
	    	String carKindKind = rs.getString("KIND");
	    	String title = rs.getString("TITLE");
	    	String no = rs.getString("NO");
	    	String carKindNo = rs.getString("CAR_KIND_NO");
	    	String localNo = rs.getString("LOCAL_NO");
	    	String count = rs.getString("COUNT");
	    	String enrollDate = rs.getString("ENROLL_DATE");
	    	String deleteYn = rs.getString("DELETE_YN");
	    	String max = rs.getString("MAX");
	    	String licensePlate = rs.getString("LICENSE_PLATE");
	    	String licenseDate = rs.getString("LICENSE_DATE");
	    	String lcname = rs.getString("LOCAL");
	    	String price = rs.getString("PRICE");
	    	
	    	
	    	 
	    	vo.setLcname(lcname);
	        vo.setNo(no);
	        vo.setCarKindNo(carKindNo);
	        vo.setLocalNo(localNo);
	        vo.setCount(count);
	        vo.setEnrollDate(enrollDate);
	        vo.setDeleteYn(deleteYn);
	        vo.setMax(max);
	        vo.setLicensePlate(licensePlate);
	        vo.setLicenseDate(licenseDate);
	        vo.setTitle(title);
	        vo.setName(carKindKind);
	        vo.setPrice(price);
	        voList.add(vo);
	        
	    }

	    JDBCTemplate.close(rs);
	    JDBCTemplate.close(pstmt);

	    // 결과가 없는 경우 예외 처리
	    if (voList.isEmpty()) {
	        throw new Exception("No data found");
	    }

	    return voList;
		
	}

	public List<CarVo> getCarList(Connection conn, PageVo pv, String searchType, String searchValue, String local) throws Exception {

		String sql = "";
		
		if(searchType.equals("carKind")) {
			//SQL (차량이름으로 검색) -수정완료
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO, R.CAR_KIND_NO, R.LOCAL_NO, R.COUNT, R.ENROLL_DATE, R.DELETE_YN, R.MAX, R.LICENSE_PLATE, R.LICENSE_DATE, R.PRICE, CI.TITLE, LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("price")) {
			//SQL (가격으로 검색) - 수정완료
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM (SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND PRICE BETWEEN 0 AND ? ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gang")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%강원도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%전남%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%전븍%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%경남%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%경북%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("dae")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%대전%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyung")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%경기도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("daegu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%대구%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("bu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%부산%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("je")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT R.NO ,R.CAR_KIND_NO ,R.LOCAL_NO ,R.COUNT ,R.ENROLL_DATE ,R.DELETE_YN ,R.MAX ,R.PRICE ,CI.TITLE ,LC.NAME AS LOCAL, CK.KIND FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO WHERE R.DELETE_YN = 'N' AND CK.KIND LIKE '%'||?||'%' AND LC.NAME LIKE '%제주도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			//값이 이상함 => 기본 목록 조회
			return getCarList(conn, pv);
		}
		System.out.println(local);
		System.out.println(searchType);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, searchValue);
		pstmt.setInt	(2, pv.getBeginRow());
		pstmt.setInt	(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<CarVo> voList = new ArrayList<>();
		while(rs.next()) {
			
			String no = rs.getString("NO");
			String kind = rs.getString("KIND");
	    	String localNo = rs.getString("LOCAL_NO");
	    	String price = rs.getString("PRICE");
	    	String title = rs.getString("TITLE");
			String lcName = rs.getString("LOCAL");
	    	
			
		
			CarVo vo = new CarVo();
			
			vo.setNo(no);
			vo.setName(kind);
	        vo.setLocalNo(localNo);
	        vo.setPrice(price);
	        vo.setTitle(title);;
	        vo.setLcname(lcName);;
	        
			
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//car_reservation table insert
	public int order(Connection conn, String carKindKind, MemberVo loginMember, CarVo cvo) throws Exception {
		
		String sql = "INSERT INTO CAR_RESERVATION (NO , RENTCAR_NO , MEMBER_NO , RESERVATION_YN , START_DATE , END_DATE , PRICE) VALUES (SEQ_CAR_RESERVATION_NO.NEXTVAL , ? , ? , 'Y' , ? , ? , ?);";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cvo.getNo());
		pstmt.setString(2, loginMember.getNo());
		pstmt.setString(3, cvo.getStartDate());
		pstmt.setString(4, cvo.getEndDate());
		int endDate = Integer.valueOf(cvo.getEndDate()); 
		int startDate = Integer.valueOf(cvo.getStartDate()); 
		int totalDay = endDate - startDate;
		int price = Integer.valueOf(cvo.getPrice());
		int totalPrice = price * totalDay;
		pstmt.setInt(5, totalPrice);
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else { 
			JDBCTemplate.rollback(conn);
		}
	
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}


	//car_payment table insert
	public int pay(CarVo cvo, Connection conn) throws Exception {
			
		
		String sql = "INSERT INTO CAR_PAYMENT (NO , CAR_RESERVATION_CODE , TYPE) VALUES (SEQ_CAR_PAYMENT_NO.NEXTVAL , ? , '카드' );";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cvo.getCarReservationCode());
		
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}


	//결제완료시 car_reservation table의 reservation_yn 칼럼값 'Y'로 UPDATE
	public int updateReservation(CarVo cvo, MemberVo loginMember, Connection conn) throws Exception {
		
		String sql = "UPDATE CAR_RESERVATION SET RESERVATION_YN = 'Y' WHERE RENTCAR_NO = ? AND MEMBER_NO = ? AND START_DATE = ? AND END_DATE = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cvo.getNo());
		pstmt.setString(2, loginMember.getNo());
		pstmt.setString(3, cvo.getStartDate());
		pstmt.setString(4, cvo.getEndDate());
		
		int result2 = pstmt.executeUpdate();
		if(result2 == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
			
		JDBCTemplate.close(pstmt);
		
		return result2;
	
	}



	public String getPrice(CarVo cvo, Connection conn) throws Exception {

		String price = null;
		String sql = "SELECT PRICE FROM CAR_RESERVATION";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			price = rs.getString("PRICE");
			
		}

			JDBCTemplate.close(rs);
			return price;
	
	}


	public CarVo selectCarOneByName(Connection conn, String name) throws Exception {
		System.out.println("dao > name  : " + name);
		//SQL
		String sql = "SELECT CK.KIND, R.NO, R.CAR_KIND_NO, R.LOCAL_NO, R.COUNT, R.ENROLL_DATE, R.DELETE_YN, R.MAX, R.LICENSE_PLATE, R.LICENSE_DATE, R.PRICE, CI.TITLE, LC.NAME AS LOCAL FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO WHERE CK.KIND like '%' || ? || '%'";
//		String sql = "SELECT CK.KIND, R.NO, R.CAR_KIND_NO, R.LOCAL_NO, R.COUNT, R.ENROLL_DATE, R.DELETE_YN, R.MAX, R.LICENSE_PLATE, R.LICENSE_DATE, R.PRICE, CI.TITLE, LC.NAME AS LOCAL FROM RENTCAR R JOIN CAR_IMG CI ON CI.RENTCAR_NO = R.NO JOIN CAR_KIND CK ON CK.NO = R.CAR_KIND_NO JOIN LOCAL_CATEGORY LC ON LC.NO = R.LOCAL_NO WHERE CK.KIND = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		CarVo vo = new CarVo();
		if( rs.next() ) {
			System.out.println("if 통과");
//			CK.KIND, R.NO, R.CAR_KIND_NO, R.LOCAL_NO, R.COUNT
//			, R.ENROLL_DATE, R.DELETE_YN, R.MAX, R.LICENSE_PLATE
//			, R.LICENSE_DATE, R.PRICE,
//			CI.TITLE, LC.NAME AS LOCAL FROM RENTCAR R 
			
			String carKindKind = rs.getString("KIND");
			String no = rs.getString("NO");
			String carKindNo = rs.getString("CAR_KIND_NO");
			String localNo = rs.getString("LOCAL_NO");
			String count = rs.getString("COUNT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String max = rs.getString("MAX");
			String licensePlate = rs.getString("LICENSE_PLATE");
			String licenseDate = rs.getString("LICENSE_DATE");
			String price = rs.getString("PRICE");
			String title = rs.getString("TITLE");
			String lcname = rs.getString("LOCAL");
			
			vo.setName(carKindKind);
			vo.setNo(no);
			vo.setCarKindNo(carKindNo);
			vo.setLocalNo(localNo);
			vo.setCount(count);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMax(max);
			vo.setLicensePlate(licensePlate);
			vo.setLicenseDate(licenseDate);
			vo.setPrice(price);
			vo.setTitle(title);
			vo.setLcname(lcname);
		}
		
		System.out.println("if 이후 : " + vo);
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
				
		return vo;
	}



	public int order(CarVo vo, Connection conn, MemberVo loginMember) throws Exception {

		String sql = "INSERT INTO CAR_RESERVATION ( NO, RENTCAR_NO, START_DATE, END_DATE,  PHONE, ADDRESS, MEMBER_NO, NAME, PRICE ) VALUES (SEQ_SOUVENIR_RESERVATION_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, (SELECT ABS(TO_DATE(TO_DATE(?,'MM/DD/YYYY')) - TO_DATE(?,'MM/DD/YYYY'))  * ? FROM DUAL))";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNo());
		pstmt.setString(2, vo.getStartDate());
		pstmt.setString(3, vo.getEndDate());
		pstmt.setString(4, vo.getPhone());
		pstmt.setString(5, vo.getAddress());
		pstmt.setString(6, loginMember.getNo());
		pstmt.setString(7, vo.getMname());
		pstmt.setString(8, vo.getStartDate());
		pstmt.setString(9, vo.getEndDate());
		pstmt.setString(10, vo.getPrice());
		
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}



	public CarVo carSelectOrder(Connection conn, MemberVo loginMember) throws Exception {

		//SQL
		String sql = "SELECT AR.NO ,AR.RENTCAR_NO ,AR.MEMBER_NO, AR.NAME AS MNAME ,AR.RESERVATION_YN ,AR.START_DATE ,AR.END_DATE ,AR.PRICE ,AR.PHONE ,AR.ADDRESS ,AI.TITLE FROM CAR_RESERVATION AR JOIN RENTCAR A ON A.NO = AR.RENTCAR_NO JOIN CAR_IMG AI ON AI.RENTCAR_NO = A.NO WHERE A.DELETE_YN = 'N' AND MEMBER_NO = ? ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		ResultSet rs = pstmt.executeQuery();
		//tx || rs
		CarVo vo = null;
		if(rs.next()) {
			vo = new CarVo();
			
			String no = rs.getString("NO");
			String mname = rs.getString("MNAME");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String phone = rs.getString("PHONE");
			String address = rs.getString("ADDRESS");
			String startDate = rs.getString("START_DATE");
			String endDate = rs.getString("END_DATE");
			
			
			
			vo.setNo(no);
	        vo.setMname(mname);
	        vo.setTitle(title);
			vo.setPrice(price);
			vo.setPhone(phone);
			vo.setAddress(address);
			vo.setStartDate(startDate);
			vo.setEndDate(endDate);
			
		}
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return vo;
	
	}



	public int carFavorite(Connection conn, String no, String name, MemberVo loginMember) throws Exception {

		String sql = "INSERT INTO FAVORITES ( NO ,MEMBER_NO ,RENTCAR_NO ) VALUES ( SEQ_FAVORITES_NO.NEXTVAL , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		pstmt.setString(2, no);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}



	public int carPayment(String reservationno, Connection conn) throws Exception {
		String sql = "INSERT INTO CAR_PAYMENT ( NO , CAR_RESERVATION_CODE ,TYPE ) VALUES(SEQ_CAR_PAYMENT_NO.NEXTVAL, ?, '카드')";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reservationno);
		
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	

}
