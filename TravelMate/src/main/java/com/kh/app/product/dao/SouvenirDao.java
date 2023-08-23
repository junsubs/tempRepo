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


public class SouvenirDao {
	
	public List<SouvenirVo> getSouvenirList(Connection conn, PageVo pv) throws Exception {
		// SQL
	    String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND LC.NAME LIKE '%%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setInt(1, pv.getBeginRow());
	    pstmt.setInt(2, pv.getLastRow());
	    ResultSet rs = pstmt.executeQuery();
	    
	    // tx || rs
	    List<SouvenirVo> voList = new ArrayList<>();

	    while (rs.next()) {
	    	SouvenirVo vo = new SouvenirVo();
	    	
	        String no = rs.getString("NO");
	        String localNo = rs.getString("LOCAL_NO");
	        String name = rs.getString("NAME");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String content = rs.getString("CONTENT");
	        String deleteYn = rs.getString("DELETE_YN");
	        String price = rs.getString("PRICE");
	        String title = rs.getString("TITLE");
	        String lcName = rs.getString("LOCAL");
	        
	        vo.setNo(no);
	        vo.setLocalNo(localNo);
	        vo.setName(name);
	        vo.setEnrollDate(enrollDate);
	        vo.setContent(content);
	        vo.setDeleteYn(deleteYn);
	        vo.setPrice(price);
	        vo.setTitle(title);;
	        vo.setLocal(lcName);
	        
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
	
	public int getSouvenirListCnt(Connection conn, String searchType, String searchValue) throws Exception {
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

	

	public List<SouvenirVo> getSouvenirList(Connection conn, PageVo pv, String searchType, String searchValue, String local) throws Exception {
		String sql = "";
		System.out.println(local);
		if(searchType.equals("name")) {
			//SQL (제목	검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("price")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND LC.NAME LIKE '%'||?||'%' ORDER BY TO_NUMBER(PRICE) ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gang")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%강원도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%전남%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("junbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%전북%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungnam")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경남%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyungbuk")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경북%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("dae")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%대전%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("gyung")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%경기도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("daegu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%대구%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("bu")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%부산%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(local.equals("je")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME LIKE '%'||?||'%' AND LC.NAME LIKE '%제주도%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			//값이 이상함 => 기본 목록 조회
			return getSouvenirList(conn, pv);
		}
		
		System.out.println(searchType);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, searchValue);
		pstmt.setInt	(2, pv.getBeginRow());
		pstmt.setInt	(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<SouvenirVo> voList = new ArrayList<>();
		while(rs.next()) {
			SouvenirVo vo = new SouvenirVo();
			
			String no = rs.getString("NO");
	        String localNo = rs.getString("LOCAL_NO");
	        String name = rs.getString("NAME");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String content = rs.getString("CONTENT");
	        String deleteYn = rs.getString("DELETE_YN");
	        String price = rs.getString("PRICE");
	        String title = rs.getString("TITLE");
	        String lcName = rs.getString("LOCAL");
	        
	        vo.setNo(no);
	        vo.setLocalNo(localNo);
	        vo.setName(name);
	        vo.setEnrollDate(enrollDate);
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

	//기념품 상세조회
	public SouvenirVo selectSouvenirOneByNo(Connection conn, String name) throws Exception {
		//SQL
		String sql = "SELECT S.NO, S.LOCAL_NO, S.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT, S.CONTENT, SI.TITLE, LC.NAME AS LOCAL FROM SOUVENIR S JOIN SOUVENIR_IMG SI ON SI.SOUVENIR_NO = S.NO JOIN LOCAL_CATEGORY LC ON LC.NO = S.LOCAL_NO WHERE S.DELETE_YN = 'N' AND S.NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		//tx || rs
		SouvenirVo vo = null;
		if(rs.next()) {
			vo = new SouvenirVo();
			
			String no = rs.getString("NO");
	        String localNo = rs.getString("LOCAL_NO");
	        String enrollDate = rs.getString("ENROLL_DATE");
	        String deleteYn = rs.getString("DELETE_YN");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String price = rs.getString("PRICE");
			String lcName = rs.getString("LOCAL");
			
			System.out.println(no);
			
			vo.setNo(no);
	        vo.setLocalNo(localNo);
	        vo.setName(name);
	        vo.setEnrollDate(enrollDate);
	        vo.setDeleteYn(deleteYn);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setPrice(price);
			vo.setLocal(lcName);
			
			System.out.println(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return vo;
	}

	public int order(SouvenirVo vo, Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "INSERT INTO SOUVENIR_RESERVATION ( NO, SOUVENIR_NO, CNT, PRICE, NAME, PHONE, ADDRESS, MEMBER_NO ) VALUES (SEQ_SOUVENIR_RESERVATION_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNo());
		pstmt.setString(2, vo.getCnt());
		pstmt.setString(3, vo.getTotalPrice());
		pstmt.setString(4, vo.getMname());
		pstmt.setString(5, vo.getPh());
		pstmt.setString(6, vo.getAddress());
		pstmt.setString(7, loginMember.getNo());
		
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public SouvenirVo selectOrder(Connection conn, MemberVo loginMember) throws Exception {
		//SQL
		String sql = "SELECT SR.NO, S.NAME, SR.PRICE, SR.CNT, SR.NAME AS MNAME, SR.PHONE, SR.ADDRESS,SR.MEMBER_NO, SI.TITLE FROM SOUVENIR_RESERVATION SR JOIN SOUVENIR S ON S.NO = SR.SOUVENIR_NO JOIN SOUVENIR_IMG SI  ON SI.SOUVENIR_NO = S.NO WHERE S.DELETE_YN = 'N' AND MEMBER_NO = ? ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		ResultSet rs = pstmt.executeQuery();
		//tx || rs
		SouvenirVo vo = null;
		if(rs.next()) {
			vo = new SouvenirVo();
			
			
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String cnt = rs.getString("CNT");
			String mname = rs.getString("MNAME");
			String phone = rs.getString("PHONE");
			String address = rs.getString("ADDRESS");
			
			System.out.println(phone);
			
			vo.setNo(no);
	        vo.setName(name);
			vo.setTitle(title);
			vo.setTotalPrice(price);
			vo.setCnt(cnt);
			vo.setMname(mname);
			vo.setPh(phone);
			vo.setAddress(address);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return vo;
	}

	public int souvenirFavorite(Connection conn, String no, String name, MemberVo loginMember) throws Exception {
		String sql = "INSERT INTO FAVORITES ( NO ,MEMBER_NO ,SOUVENIR_NO ) VALUES ( SEQ_FAVORITES_NO.NEXTVAL , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getNo());
		pstmt.setString(2, no);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int souvenirPayment(String reservationno, Connection conn) throws Exception {
		String sql = "INSERT INTO SOUVENIR_PAYMENT ( NO , SOUVENIR_RESERVATION_CODE ,TYPE ) VALUES(SEQ_SOUVENIR_PAYMENT_NO.NEXTVAL, ?, '카드')";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, reservationno);
		
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	
	

}
