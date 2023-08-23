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
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.vo.SouvenirVo;


public class RoomDao {
	
	public List<RoomVo> getProductList(Connection conn, PageVo pv) throws Exception {
		// SQL
	    String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND LC.NAME LIKE '%%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, pv.getBeginRow());
	    pstmt.setInt(2, pv.getLastRow());
	    ResultSet rs = pstmt.executeQuery();
	    
	    // tx || rs
	    List<RoomVo> voList = new ArrayList<>();

	    while (rs.next()) {
	    	RoomVo vo = new RoomVo();
	    	
	        String no = rs.getString("NO");
	        String localNo = rs.getString("LOCAL_NO");
	        String accomodationNo = rs.getString("ACCOMODATION_NO");
	        String name = rs.getString("NAME");
	        String maxPeople = rs.getString("MAX_PEOPLE");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String content = rs.getString("CONTENT");
	        String deleteYn = rs.getString("DELETE_YN");
	        String price = rs.getString("PRICE");
	        String title = rs.getString("TITLE");
	        String local = rs.getString("LOCAL");

	        vo.setNo(no);
	        vo.setLocalNo(localNo);
	        vo.setAccomodationNo(accomodationNo);
	        vo.setName(name);
	        vo.setEnrollDate(enrollDate);
	        vo.setMaxPeople(maxPeople);
	        vo.setContent(content);
	        vo.setDeleteYn(deleteYn);
	        vo.setPrice(price);
	        vo.setTitle(title);;
	        vo.setLocal(local);

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
	
	public int getProductListCnt(Connection conn, String searchType, String searchValue) throws Exception {
		//SQL
		String sql = "SELECT COUNT(*) FROM ( SELECT * FROM ACCOMODATION) WHERE DELETE_YN = 'N'";
		if("ACCOMODATION".equals(searchType)) {
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

	

	public List<RoomVo> getProductList(Connection conn, PageVo pv, String searchType, String searchValue, String local) throws Exception {
		String sql = "";
		
		if(searchType.equals("name")) {
			//SQL (제목	검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("price")) {
			//SQL (작성자	검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%%' ORDER BY TO_NUMBER(PRICE)  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gang")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%강원도%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%전남%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%전북%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경남%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경북%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("dae")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%대전%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyung")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경기도%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("daegu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%대구%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("bu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%부산%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("je")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT A.NO, A.LOCAL_NO, A.ACCOMODATION_NO, A.NAME, A.ENROLL_DATE, A.MAX_PEOPLE, A.CONTENT, A.DELETE_YN, A.PRICE, AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION A JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%제주도%' ORDER BY NO DESC  ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			//값이 이상함 => 기본 목록 조회
			return getProductList(conn, pv);
		}
		System.out.println(local);
		System.out.println(searchType);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, searchValue);
		pstmt.setInt	(2, pv.getBeginRow());
		pstmt.setInt	(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<RoomVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String localNo = rs.getString("LOCAL_NO");
			String accomodationNo = rs.getString("ACCOMODATION_NO");
			String name = rs.getString("NAME");
			String enrollDate = rs.getString("ENROLL_DATE");
			String maxPeople = rs.getString("MAX_PEOPLE");
			String content = rs.getString("CONTENT");
			String deleteYn = rs.getString("DELETE_YN");
			String price = rs.getString("PRICE");
			String title = rs.getString("TITLE");
			String lcName = rs.getString("LOCAL");
			
		
			RoomVo vo = new RoomVo();
			
			vo.setNo(no);
			vo.setLocalNo(localNo);
			vo.setAccomodationNo(accomodationNo);
			vo.setName(name);
			vo.setEnrollDate(enrollDate);
			vo.setMaxPeople(maxPeople);
			vo.setContent(content);
			vo.setDeleteYn(deleteYn);
			vo.setPrice(price);
			vo.setTitle(title);;
			vo.setLocal(lcName);
			
			
			voList.add(vo);
		}
		System.out.println(voList);
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	public RoomVo selectSouvenirOneByNo(Connection conn, String name) throws Exception {
		//SQL
		String sql = "SELECT A.NO ,A.LOCAL_NO ,A.ACCOMODATION_NO ,A.NAME ,A.ENROLL_DATE ,A.MAX_PEOPLE ,A.CONTENT ,A.DELETE_YN ,A.PRICE ,AK.KIND ,AI.TITLE, LC.NAME AS LOCAL FROM ACCOMODATION_IMG AI JOIN ACCOMODATION A ON AI.ACCOMODATION_NO = A.ACCOMODATION_NO JOIN ACCOMODATION_KIND AK ON AK.NO = A.ACCOMODATION_NO JOIN LOCAL_CATEGORY LC ON LC.NO = A.LOCAL_NO WHERE A.DELETE_YN = 'N' AND A.NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		//tx || rs
		RoomVo vo = null;
		if(rs.next()) {
			vo = new RoomVo();
			
			String no = rs.getString("NO");
	        String localNo = rs.getString("LOCAL_NO");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String deleteYn = rs.getString("DELETE_YN");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String price = rs.getString("PRICE");
			String lcName = rs.getString("LOCAL");
			String kind = rs.getString("KIND");
			
			
			vo.setNo(no);
	        vo.setLocalNo(localNo);
	        vo.setName(name);
	        vo.setEnrollDate(enrollDate);
	        vo.setDeleteYn(deleteYn);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setPrice(price);
			vo.setLocal(lcName);
			vo.setKind(kind);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
				
				
		return vo;
	}

	public int order(RoomVo vo, Connection conn, MemberVo loginMember) throws Exception {
		String sql = "INSERT INTO ACCOMODATION_RESERVATION ( NO, ACCOMODATION_NO, START_DATE, END_DATE,  PHONE, ADDRESS, MEMBER_NO, NAME, PRICE ) VALUES (SEQ_SOUVENIR_RESERVATION_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, (SELECT ABS(TO_DATE(TO_DATE(?,'MM/DD/YYYY')) - TO_DATE(?,'MM/DD/YYYY'))  * ? FROM DUAL))";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNo());
		pstmt.setString(2, vo.getDateStart());
		pstmt.setString(3, vo.getDateEnd());
		pstmt.setString(4, vo.getPh());
		pstmt.setString(5, vo.getAddress());
		pstmt.setString(6, loginMember.getNo());
		pstmt.setString(7, vo.getMname());
		pstmt.setString(8, vo.getDateStart());
		pstmt.setString(9, vo.getDateEnd());
		pstmt.setString(10, vo.getPrice());
		
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public RoomVo roomSelectOrder(Connection conn, MemberVo loginMember) throws Exception {
		//SQL
		String sql = "SELECT AR.NO, A.NAME ,AR.ACCOMODATION_NO ,AR.MEMBER_NO, AR.NAME AS MNAME ,AR.RESERVATION_YN ,AR.START_DATE ,AR.END_DATE ,AR.PRICE ,AR.PHONE ,AR.ADDRESS ,AI.TITLE FROM ACCOMODATION_RESERVATION AR JOIN ACCOMODATION A ON A.NO = AR.ACCOMODATION_NO JOIN ACCOMODATION_IMG AI ON AI.ACCOMODATION_NO = A.NO WHERE A.DELETE_YN = 'N' AND MEMBER_NO = ? ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		ResultSet rs = pstmt.executeQuery();
		//tx || rs
		RoomVo vo = null;
		if(rs.next()) {
			vo = new RoomVo();
			
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String mname = rs.getString("MNAME");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String phone = rs.getString("PHONE");
			String address = rs.getString("ADDRESS");
			String dateStart = rs.getString("START_DATE");
			String dateEnd = rs.getString("END_DATE");
			
			
			
			vo.setNo(no);
	        vo.setName(name);
	        vo.setMname(mname);
	        vo.setTitle(title);
			vo.setPrice(price);
			vo.setPh(phone);
			vo.setAddress(address);
			vo.setDateStart(dateStart);
			vo.setDateEnd(dateEnd);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return vo;
	}

	

	public int roomFavorite(Connection conn, String no, String name, MemberVo loginMember) throws Exception {
		String sql = "INSERT INTO FAVORITES ( NO ,MEMBER_NO ,ACCOMODATION_NO ) VALUES ( SEQ_FAVORITES_NO.NEXTVAL , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		pstmt.setString(2, no);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int roomPayment(String reservationno, Connection conn) throws Exception {
		String sql = "INSERT INTO ACCOMODATION_PAYMENT ( NO ,ACCOMODATION_RESERVATION_CODE ,TYPE ) VALUES(SEQ_ACCOMODATION_PAYMENT_NO.NEXTVAL, ?, '카드')";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reservationno);
		
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	

}
