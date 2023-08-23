package com.kh.app.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.app.admin.util.CarAttachmentVo;
import com.kh.app.admin.vo.AccommodationInventoryVo;
import com.kh.app.admin.vo.AdBannerVo;
import com.kh.app.admin.vo.CarInventoryVo;
import com.kh.app.admin.vo.MemberDetailVo;
import com.kh.app.admin.vo.MemberSearchVo;
import com.kh.app.admin.vo.ReportListDetailVo;
import com.kh.app.admin.vo.ReportListVo;
import com.kh.app.admin.vo.ReportSearchDetailVo;
import com.kh.app.admin.vo.ReportSearchVo;
import com.kh.app.admin.vo.SellRequestDetailVo;
import com.kh.app.admin.vo.SellRequestVo;
import com.kh.app.admin.vo.SouvenirInventoryVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;

public class AdminDao {

	//신고내역 페이징처리
	public int reportListCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("memberNick".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT R.NO, R.MEMBER_NO, M.NICK, B.NAME AS CATEGORY_NAME, E.NAME FROM REPORT_LIST R JOIN MEMBER M ON R.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON R.BOARD_NO = B.NO JOIN REPORT_REASON E ON R.SANCTION_REASON_NO = E.NO WHERE M.STATUS = 'O' AND M.NICK LIKE '%" + searchValue + "%')";
		}else if("boardName".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT R.NO, R.MEMBER_NO, M.NICK, B.NAME AS CATEGORY_NAME, E.NAME FROM REPORT_LIST R JOIN MEMBER M ON R.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON R.BOARD_NO = B.NO JOIN REPORT_REASON E ON R.SANCTION_REASON_NO = E.NO WHERE M.STATUS = 'O' AND B.NAME LIKE '%" + searchValue + "%')";
		}else if("reportReason".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT R.NO, R.MEMBER_NO, M.NICK, B.NAME AS CATEGORY_NAME, E.NAME FROM REPORT_LIST R JOIN MEMBER M ON R.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON R.BOARD_NO = B.NO JOIN REPORT_REASON E ON R.SANCTION_REASON_NO = E.NO WHERE M.STATUS = 'O' AND E.NAME ='" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT R.NO, R.MEMBER_NO, M.NICK, B.NAME, E.NAME FROM REPORT_LIST R JOIN MEMBER M ON R.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON R.BOARD_NO = B.NO JOIN REPORT_REASON E ON R.SANCTION_REASON_NO = E.NO WHERE M.STATUS = 'O')";			
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//제재이력조회 페이징처리
	public int reportSearchCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
	
		if("memberNick".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.SANCTION_TYPE_CODE, R.MEMBER_NO, B.NICK FROM MEMBER_SANCTIONS M JOIN REPORT_LIST R ON M.REPORT_LIST_NO = R.NO JOIN MEMBER B ON R.MEMBER_NO = B.NO WHERE B.STATUS = 'X' AND B.NICK LIKE '%" + searchValue + "%')";
		}else if("sanctionName".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, T.NAME, R.MEMBER_NO, B.NICK FROM MEMBER_SANCTIONS M JOIN REPORT_LIST R ON M.REPORT_LIST_NO = R.NO JOIN MEMBER B ON R.MEMBER_NO = B.NO JOIN SANCTION_TYPE T ON M.SANCTION_TYPE_CODE = T.CODE WHERE B.STATUS = 'X' AND T.NAME ='" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.SANCTION_TYPE_CODE, R.MEMBER_NO, B.NICK FROM MEMBER_SANCTIONS M JOIN REPORT_LIST R ON M.REPORT_LIST_NO = R.NO JOIN MEMBER B ON R.MEMBER_NO = B.NO WHERE B.STATUS = 'X')";			
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//회원조회 페이징처리
	public int memberSearchCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("memberId".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.ID, M.NICK, M.MEMBER_GRADE_NO, M.STATUS FROM MEMBER M WHERE M.ID LIKE '%" + searchValue + "%')";
		}else if("memberNick".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.ID, M.NICK, M.MEMBER_GRADE_NO, M.STATUS FROM MEMBER M WHERE M.NICK LIKE '%" + searchValue + "%')";
		}else if("status".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.ID, M.NICK, M.MEMBER_GRADE_NO, M.STATUS FROM MEMBER M WHERE M.STATUS ='" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT M.NO, M.ID, M.NICK, M.MEMBER_GRADE_NO, M.STATUS FROM MEMBER M)";			
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//차량재고조회 페이징처리
	public int carInventroy(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("name".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT C.NO, K.KIND, C.LICENSE_PLATE, R.NAME, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.COUNT FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY R ON C.LOCAL_NO = R.NO WHERE K.KIND LIKE '%" + searchValue + "%')";
		}else if("license".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT C.NO, K.KIND, C.LICENSE_PLATE, R.NAME, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.COUNT FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY R ON C.LOCAL_NO = R.NO WHERE C.LICENSE_PLATE LIKE '%" + searchValue + "%')";
		}else if("countYn".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT C.NO, K.KIND, C.LICENSE_PLATE, R.NAME, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.COUNT FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY R ON C.LOCAL_NO = R.NO WHERE C.COUNT ='" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT C.NO, K.KIND, C.LICENSE_PLATE, R.NAME, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.COUNT FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY R ON C.LOCAL_NO = R.NO)";
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}

	//숙소재고조회 페이징처리
	public int accommodationInventoryCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("name".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO WHERE A.NAME LIKE '%" + searchValue + "%')";
		}else if("countYn".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO WHERE A.COUNT_YN = '" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO)";
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//기념품재고조회 페이징처리
	public int souvenirInventory(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
	
		if("souvenirName".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT S.NO, S.NAME, L.NAME AS LOCAL_NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO WHERE S.NAME LIKE '%" + searchValue + "%')";
		}else if("areaName".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT S.NO, S.NAME, L.NAME AS LOCAL_NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO WHERE L.NAME = '" + searchValue + "')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT S.NO, S.NAME, L.NAME, S.PRICE, S.ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO)";
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()){
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return cnt;
	}
	
	//판매등록요청 페이징처리
	public int sellRequestCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("writer".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.TITLE, B.ENROLL_DATE, M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N' AND M.NICK LIKE '%" + searchValue + "%')";
		}else if("title".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.TITLE, B.ENROLL_DATE, M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N' AND B.TITLE LIKE '%" + searchValue + "%')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.TITLE, B.ENROLL_DATE, M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N')";			
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//광고배너관리 페이징처리
	public int adBannerCnt(Connection conn, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("bannerName".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.NAME, B.IMAGE, M.NICK FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE DELETE_YN = 'N' AND B.NAME LIKE '%" + searchValue + "%')";
		}else if("memberNick".equals(searchType)) {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.NAME, B.IMAGE, M.NICK FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE DELETE_YN = 'N' AND M.NICK LIKE '%" + searchValue + "%')";
		}else {
			s = "SELECT COUNT(*) FROM ( SELECT B.NO, B.NAME, B.IMAGE, M.NICK FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE DELETE_YN = 'N')";			
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}
	
	//신고내역화면
	public List<ReportListVo> reportList(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM (SELECT L.NO, L.MEMBER_NO, L.BOARD_NO, L.SANCTION_REASON_NO, L.CONTENT, R.NAME AS REASON_NAME, M.NICK, M.STATUS, B.NAME AS CATEGORY_NAME FROM REPORT_LIST L JOIN REPORT_REASON R ON L.SANCTION_REASON_NO = R.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON L.BOARD_NO = B.NO WHERE M.STATUS = 'O' ORDER BY L.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<ReportListVo> reportVoList = new ArrayList<>(); 
		while(rs.next()) {
			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String boardNo = rs.getString("BOARD_NO");
			String reasonNo = rs.getString("SANCTION_REASON_NO");
			String content = rs.getString("CONTENT");
			String reasonName = rs.getString("REASON_NAME");
			String nick = rs.getString("NICK");
			String categoryName = rs.getString("CATEGORY_NAME");
			
			ReportListVo vo = new ReportListVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setBoardNo(boardNo);
			vo.setReasonNo(reasonNo);
			vo.setContent(content);
			vo.setReasonName(reasonName);
			vo.setNick(nick);
			vo.setCategoryName(categoryName);
			
			reportVoList.add(vo);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return reportVoList;
	}
	
	//신고내역 카테고리 설정
	public List<ReportListVo> reportList(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {	
		String s = "";
		
		if("memberNick".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT L.NO, L.MEMBER_NO, L.BOARD_NO, L.SANCTION_REASON_NO, L.CONTENT, R.NAME AS REASON_NAME, M.NICK, M.STATUS, B.NAME AS CATEGORY_NAME FROM REPORT_LIST L JOIN REPORT_REASON R ON L.SANCTION_REASON_NO = R.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON L.BOARD_NO = B.NO WHERE M.STATUS = 'O' AND M.NICK LIKE '%'||?||'%' OR M.NICK LIKE '%?' OR M.NICK LIKE '?%' ORDER BY L.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else if("boardName".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT L.NO, L.MEMBER_NO, L.BOARD_NO, L.SANCTION_REASON_NO, L.CONTENT, R.NAME AS REASON_NAME, M.NICK, M.STATUS, B.NAME AS CATEGORY_NAME FROM REPORT_LIST L JOIN REPORT_REASON R ON L.SANCTION_REASON_NO = R.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON L.BOARD_NO = B.NO WHERE M.STATUS = 'O' AND B.NAME LIKE '%'||?||'%' OR B.NAME LIKE '%?' OR B.NAME LIKE '?%' ORDER BY L.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";		
		}else if("reportReason".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT L.NO, L.MEMBER_NO, L.BOARD_NO, L.SANCTION_REASON_NO, L.CONTENT, R.NAME AS REASON_NAME, M.NICK, M.STATUS, B.NAME AS CATEGORY_NAME FROM REPORT_LIST L JOIN REPORT_REASON R ON L.SANCTION_REASON_NO = R.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON L.BOARD_NO = B.NO WHERE M.STATUS = 'O' AND R.NAME = ? ORDER BY L.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else {
			return reportList(conn, pv);
		}
		
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<ReportListVo> reportVoList = new ArrayList<>(); 
		while(rs.next()) {
			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String boardNo = rs.getString("BOARD_NO");
			String reasonNo = rs.getString("SANCTION_REASON_NO");
			String content = rs.getString("CONTENT");
			String reasonName = rs.getString("REASON_NAME");
			String nick = rs.getString("NICK");
			String categoryName = rs.getString("CATEGORY_NAME");
			
			ReportListVo vo = new ReportListVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setBoardNo(boardNo);
			vo.setReasonNo(reasonNo);
			vo.setContent(content);
			vo.setReasonName(reasonName);
			vo.setNick(nick);
			vo.setCategoryName(categoryName);
			
			reportVoList.add(vo);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return reportVoList;
	}

	//제재이력조회
	public List<ReportSearchVo> reportSearch(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT S.COUNT, S.NO, TO_CHAR(S.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, TO_CHAR(S.CANCEL_ENROLL_DATE, 'YY-MM-DD') AS CANCEL_ENROLL_DATE, M.NICK, ST.NAME FROM MEMBER_SANCTIONS S JOIN REPORT_LIST L ON S.REPORT_LIST_NO = L.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN SANCTION_TYPE ST ON S.SANCTION_TYPE_CODE = ST.CODE WHERE M.STATUS = 'X' ORDER BY NO DESC )T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx||rs
		List<ReportSearchVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String cancelEnrollDate = rs.getString("CANCEL_ENROLL_DATE");
			String memberNick = rs.getString("NICK");
			String sanctionName = rs.getString("NAME");
			String count = rs.getString("COUNT");
			
			ReportSearchVo vo = new ReportSearchVo();
			vo.setNo(no);
			vo.setEnrollDate(enrollDate);
			vo.setCancelEnrollDate(cancelEnrollDate);
			vo.setMemberNick(memberNick);
			vo.setSanctionName(sanctionName);
			vo.setCount(count);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//제재이력조회 카테고리 설정
	public List<ReportSearchVo> reportSearch(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("memberNick".equals(searchType)) {
			//닉네임으로 검색
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT S.COUNT, S.NO, TO_CHAR(S.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, TO_CHAR(S.CANCEL_ENROLL_DATE, 'YY-MM-DD') AS CANCEL_ENROLL_DATE, M.NICK, ST.NAME FROM MEMBER_SANCTIONS S JOIN REPORT_LIST L ON S.REPORT_LIST_NO = L.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN SANCTION_TYPE ST ON S.SANCTION_TYPE_CODE = ST.CODE WHERE M.STATUS = 'X' AND M.NICK LIKE '%'||?||'%' OR M.NICK LIKE '%?' OR M.NICK LIKE '?%' ORDER BY NO DESC )T ) WHERE RNUM BETWEEN ? AND ?";		
		}else if("sanctionName".equals(searchType)) {
			//제재종류로 검색
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT S.COUNT, S.NO, TO_CHAR(S.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, TO_CHAR(S.CANCEL_ENROLL_DATE, 'YY-MM-DD') AS CANCEL_ENROLL_DATE, M.NICK, ST.NAME FROM MEMBER_SANCTIONS S JOIN REPORT_LIST L ON S.REPORT_LIST_NO = L.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO JOIN SANCTION_TYPE ST ON S.SANCTION_TYPE_CODE = ST.CODE WHERE M.STATUS = 'X' AND ST.NAME = ? ORDER BY NO DESC )T ) WHERE RNUM BETWEEN ? AND ?";		
		}else {
			return reportSearch(conn, pv);	
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx||rs
		List<ReportSearchVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String cancelEnrollDate = rs.getString("CANCEL_ENROLL_DATE");
			String memberNick = rs.getString("NICK");
			String sanctionName = rs.getString("NAME");
			String count = rs.getString("COUNT");
			
			ReportSearchVo vo = new ReportSearchVo();
			vo.setNo(no);
			vo.setEnrollDate(enrollDate);
			vo.setCancelEnrollDate(cancelEnrollDate);
			vo.setMemberNick(memberNick);
			vo.setSanctionName(sanctionName);
			vo.setCount(count);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//회원조회
	public List<MemberSearchVo> memberSearch(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT M.NO, M.ID, M.NICK, M.STATUS, G.NAME FROM MEMBER M JOIN MEMBER_GRADE G ON M.MEMBER_GRADE_NO = G.NO ORDER BY M.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<MemberSearchVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String status = rs.getString("STATUS");
			String gradeName = rs.getString("NAME");
			
			MemberSearchVo vo = new MemberSearchVo();
			vo.setNo(no);
			vo.setId(id);
			vo.setNick(nick);
			vo.setStatus(status);
			vo.setGradeName(gradeName);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//회원조회 카테고리 설정
	public List<MemberSearchVo> memberSearch(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		if("memberId".equals(searchType)) {
			//아이디검색
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT M.NO, M.ID, M.NICK, M.STATUS, G.NAME FROM MEMBER M JOIN MEMBER_GRADE G ON M.MEMBER_GRADE_NO = G.NO WHERE M.ID LIKE '%'||?||'%' OR M.ID LIKE '%?' OR M.ID LIKE '?%' ORDER BY M.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";		
		}else if("memberNick".equals(searchType)) {
			//닉네임검색
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT M.NO, M.ID, M.NICK, M.STATUS, G.NAME FROM MEMBER M JOIN MEMBER_GRADE G ON M.MEMBER_GRADE_NO = G.NO WHERE M.NICK LIKE '%'||?||'%' OR M.NICK LIKE '%?' OR M.NICK LIKE '?%' ORDER BY M.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else if("status".equals(searchType)) {
			//회원등급검색
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT M.NO, M.ID, M.NICK, M.STATUS, G.NAME FROM MEMBER M JOIN MEMBER_GRADE G ON M.MEMBER_GRADE_NO = G.NO WHERE M.STATUS = ? ORDER BY M.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else {
			return memberSearch(conn, pv);
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<MemberSearchVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String status = rs.getString("STATUS");
			String gradeName = rs.getString("NAME");
			
			MemberSearchVo vo = new MemberSearchVo();
			vo.setNo(no);
			vo.setId(id);
			vo.setNick(nick);
			vo.setStatus(status);
			vo.setGradeName(gradeName);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//차량재고관리
	public List<CarInventoryVo> carInventory(Connection conn, PageVo pv) throws Exception {
		//sql
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT C.NO, K.KIND, C.COUNT, L.NAME, C.LICENSE_PLATE, C.PRICE, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.CHANGE_NAME, C.ORIGIN_NAME FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY L ON C.LOCAL_NO = L.NO WHERE DELETE_YN = 'N' ORDER BY C.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<CarInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String kind = rs.getString("KIND");
			String count = rs.getString("COUNT");
			String name = rs.getString("NAME");
			String licensePlate = rs.getString("LICENSE_PLATE");
			String price = rs.getString("PRICE");
			String licenseDate = rs.getString("LICENSE_DATE");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			CarInventoryVo vo = new CarInventoryVo();
			vo.setNo(no);
			vo.setKind(kind);
			vo.setCount(count);
			vo.setName(name);
			vo.setLicensePlate(licensePlate);
			vo.setPrice(price);
			vo.setLicenseDate(licenseDate);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
		
	//차량재고관리 카테고리설정
	public List<CarInventoryVo> carInventory(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("name".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT C.NO, K.KIND, C.COUNT, L.NAME, C.LICENSE_PLATE, C.PRICE, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.CHANGE_NAME, C.ORIGIN_NAME FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY L ON C.LOCAL_NO = L.NO WHERE DELETE_YN = 'N' AND K.KIND LIKE '%'||?||'%' OR K.KIND LIKE '%?' OR K.KIND LIKE '?%' ORDER BY C.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("license".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT C.NO, K.KIND, C.COUNT, L.NAME, C.LICENSE_PLATE, C.PRICE, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.CHANGE_NAME, C.ORIGIN_NAME FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY L ON C.LOCAL_NO = L.NO WHERE DELETE_YN = 'N' AND C.LICENSE_PLATE LIKE '%'||?||'%' OR C.LICENSE_PLATE LIKE '%?' OR C.LICENSE_PLATE LIKE '?%' ORDER BY C.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("countYn".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT C.NO, K.KIND, C.COUNT, L.NAME, C.LICENSE_PLATE, C.PRICE, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.CHANGE_NAME, C.ORIGIN_NAME FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY L ON C.LOCAL_NO = L.NO WHERE DELETE_YN = 'N' AND C.COUNT = ? ORDER BY C.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return carInventory(conn, pv);
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<CarInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String kind = rs.getString("KIND");
			String count = rs.getString("COUNT");
			String name = rs.getString("NAME");
			String licensePlate = rs.getString("LICENSE_PLATE");
			String price = rs.getString("PRICE");
			String licenseDate = rs.getString("LICENSE_DATE");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			CarInventoryVo vo = new CarInventoryVo();
			vo.setNo(no);
			vo.setKind(kind);
			vo.setCount(count);
			vo.setName(name);
			vo.setLicensePlate(licensePlate);
			vo.setPrice(price);
			vo.setLicenseDate(licenseDate);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//숙소재고조회
	public List<AccommodationInventoryVo> accommodationInventory(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO ORDER BY A.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<AccommodationInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String kind = rs.getString("KIND");
			String maxPeople = rs.getString("MAX_PEOPLE");
			String countYn = rs.getString("COUNT_YN");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			AccommodationInventoryVo vo = new AccommodationInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setKind(kind);
			vo.setMaxPeople(maxPeople);
			vo.setCountYn(countYn);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
	
		return voList;
	}
	
	//숙소재고조회 카테고리 설정
	public List<AccommodationInventoryVo> accommodationInventory(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception{
		String s = "";
	
		if("name".equals(searchType)){
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO WHERE DELETE_YN = 'N' AND A.NAME LIKE '%'||?||'%' OR A.NAME LIKE '%?' OR A.NAME LIKE '?%' ORDER BY A.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("countYn".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT A.CHANGE_NAME, A.ORIGIN_NAME, A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO WHERE DELETE_YN = 'N' AND A.COUNT_YN = ? ORDER BY A.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return accommodationInventory(conn, pv);
		}
		
		PreparedStatement pstmt = conn.prepareStatement(s);	
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<AccommodationInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String kind = rs.getString("KIND");
			String maxPeople = rs.getString("MAX_PEOPLE");
			String countYn = rs.getString("COUNT_YN");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			AccommodationInventoryVo vo = new AccommodationInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setKind(kind);
			vo.setMaxPeople(maxPeople);
			vo.setCountYn(countYn);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//기념품재고조회
	public List<SouvenirInventoryVo> souvenirInventory(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM (SELECT ROWNUM RNUM, T.* FROM (SELECT S.CHANGE_NAME, S.ORIGIN_NAME, S.NO, S.NAME, L.NAME AS LOCAL_NAME, S.PRICE, TO_CHAR(S.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO ORDER BY S.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<SouvenirInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String price = rs.getString("PRICE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String count = rs.getString("COUNT");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			SouvenirInventoryVo vo = new SouvenirInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setPrice(price);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setCount(count);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//기념품재고조회 카테고리 설정
	public List<SouvenirInventoryVo> souvenirInventory(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		
		if("souvenirName".equals(searchType)) {
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT S.CHANGE_NAME, S.ORIGIN_NAME, S.NO, S.NAME, L.NAME AS LOCAL_NAME, S.PRICE, TO_CHAR(S.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO WHERE S.NAME LIKE '%'||?||'%' OR S.NAME LIKE '%?' OR S.NAME LIKE '?%' ORDER BY S.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";	
		}else if("areaName".equals(searchType)){
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT S.CHANGE_NAME, S.ORIGIN_NAME, S.NO, S.NAME, L.NAME AS LOCAL_NAME, S.PRICE, TO_CHAR(S.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, S.DELETE_YN, S.COUNT FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO WHERE L.NAME = ? ORDER BY S.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return souvenirInventory(conn, pv);
		}
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<SouvenirInventoryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String price = rs.getString("PRICE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String count = rs.getString("COUNT");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			
			SouvenirInventoryVo vo = new SouvenirInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setPrice(price);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setCount(count);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//판매등록요청조회
	public List<SellRequestVo> sellRequest(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.TITLE, TO_CHAR(B.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, M.NICK, I.TITLE AS IMG FROM BOARD B JOIN MEMBER M ON M.NO = B.MEMBER_NO JOIN BOARD_IMG I ON B.BOARD_IMG_NO = I.NO WHERE B.BOARD_CATEGORY_NO = '2' AND B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<SellRequestVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String writer = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String img = rs.getString("IMG");
			
			SellRequestVo vo = new SellRequestVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setEnrollDate(enrollDate);
			vo.setImg(img);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//판매등록요청조회 카테고리 설정
	public List<SellRequestVo> sellRequest(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		if("title".equals(searchType)) {
			//제목조회
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.TITLE, TO_CHAR(B.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, M.NICK, I.TITLE AS IMG FROM BOARD B JOIN MEMBER M ON M.NO = B.MEMBER_NO JOIN BOARD_IMG I ON B.BOARD_IMG_NO = I.NO WHERE B.BOARD_CATEGORY_NO = '2' AND B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N' AND B.TITLE LIKE '%'||?||'%' OR B.TITLE LIKE '%?' OR B.TITLE LIKE '?%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else if("writer".equals(searchType)) {
			//작성자조회
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.TITLE, TO_CHAR(B.ENROLL_DATE, 'YY-MM-DD') AS ENROLL_DATE, M.NICK, I.TITLE AS IMG FROM BOARD B JOIN MEMBER M ON M.NO = B.MEMBER_NO JOIN BOARD_IMG I ON B.BOARD_IMG_NO = I.NO WHERE B.BOARD_CATEGORY_NO = '2' AND B.UPLOAD_YN = 'Y' AND B.DELETE_YN = 'N' AND M.NICK LIKE '%'||?||'%' OR M.NICK LIKE '%?' OR M.NICK LIKE '?%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else {
			return sellRequest(conn, pv);
		}
		PreparedStatement pstmt = conn.prepareStatement(s);			
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<SellRequestVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String writer = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String img = rs.getString("IMG");
			
			SellRequestVo vo = new SellRequestVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setEnrollDate(enrollDate);
			vo.setImg(img);
			
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	//광고배너관리
	public List<AdBannerVo> adBanner(Connection conn, PageVo pv) throws Exception {
		String s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM (SELECT B.NO, B.NAME, B.IMAGE, M.NICK FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.DELETE_YN = 'N' ORDER BY B.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<AdBannerVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String image = rs.getString("IMAGE");
			String nick = rs.getString("NICK");

			AdBannerVo vo = new AdBannerVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setImage(image);
			vo.setNick(nick);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//광고배너관리 카테고리 검색
	public List<AdBannerVo> adBanner(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {
		String s = "";
		if("bannerName".equals(searchType)) {
			//배너이름
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.NAME AS NAME, B.IMAGE, M.NICK, S.NAME AS SOUVENIR_NAME FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN SOUVENIR S ON B.SOUVENIR_NO = S.NO WHERE B.DELETE_YN = 'N' AND B.NAME LIKE '%'||?||'%' OR B.NAME LIKE '%?' OR B.NAME LIKE '?%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";			
		}else if("memberNick".equals(searchType)) {
			//닉네임			
			s = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.NAME AS NAME, B.IMAGE, M.NICK, S.NAME AS SOUVENIR_NAME FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN SOUVENIR S ON B.SOUVENIR_NO = S.NO WHERE B.DELETE_YN = 'N' AND M.NICK LIKE '%'||?||'%' OR M.NICK LIKE '%?' OR M.NICK LIKE '?%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return adBanner(conn, pv);
		}
		PreparedStatement pstmt = conn.prepareStatement(s);		
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<AdBannerVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String name = rs.getString("NAME");
			String image = rs.getString("IMAGE");
			String nick = rs.getString("NICK");
			String souvenirName = rs.getString("SOUVENIR_NAME");
			
			AdBannerVo vo = new AdBannerVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setImage(image);
			vo.setNick(nick);
			vo.setSouvenirName(souvenirName);
			
			voList.add(vo);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	//신고내역 상세조회
	public ReportListDetailVo reportListDetail(Connection conn, String no) throws Exception {
		String s = "SELECT R.NO, R.MEMBER_NO, M.ID, M.NICK, B.NAME, R.CONTENT, N.NAME AS REPORT_REASON FROM REPORT_LIST R JOIN MEMBER M ON R.MEMBER_NO = M.NO JOIN BOARD_CATEGORY B ON R.BOARD_NO = B.NO JOIN REPORT_REASON N ON R.SANCTION_REASON_NO = N.NO WHERE R.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		ReportListDetailVo vo = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String boardName = rs.getString("NAME");
			String content = rs.getString("CONTENT");
			String reportReason = rs.getString("REPORT_REASON");
			
			vo = new ReportListDetailVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setMemberId(id);
			vo.setMemberNick(nick);
			vo.setBoardName(boardName);
			vo.setContent(content);
			vo.setReasonName(reportReason);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	//제재이력조회 상세조회
	public ReportSearchDetailVo reportSearchDetail(Connection conn, String no) throws Exception {
		String s = "SELECT S.NO, S.REPORT_LIST_NO, R.NAME, M.ID, TO_CHAR(S.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, TO_CHAR(S.CANCEL_ENROLL_DATE, 'YYYY-MM-DD') AS CANCEL_ENROLL_DATE, S.COUNT FROM MEMBER_SANCTIONS S JOIN REPORT_LIST L ON S.REPORT_LIST_NO = L.NO JOIN REPORT_REASON R ON L.SANCTION_REASON_NO = R.NO JOIN MEMBER M ON L.MEMBER_NO = M.NO WHERE S.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		ReportSearchDetailVo vo = null;
		if(rs.next()) {
			String reportListNo = rs.getString("REPORT_LIST_NO");
			String name = rs.getString("NAME");
			String id = rs.getString("ID");
			String enrollDate = rs.getString("ENROLL_DATE");
			String cancelEnrollDate = rs.getString("CANCEL_ENROLL_DATE");
			String count = rs.getString("COUNT");
			
			vo = new ReportSearchDetailVo();
			vo.setNo(no);
			vo.setReportListNo(reportListNo);
			vo.setName(name);
			vo.setMemberId(id);
			vo.setEnrollDate(enrollDate);
			vo.setCancelEnrollDate(cancelEnrollDate);
			vo.setCount(count);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	//회원상세조회
	public MemberDetailVo memberSearchDetail(Connection conn, String no) throws Exception {
		String s = "SELECT M.NO, M.ID, M.NICK, M.EMAIL, M.ADDRESS, C.NAME, TO_CHAR(M.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, TO_CHAR(M.UPDATE_DATE, 'YYYY-MM-DD') AS UPDATE_DATE, M.STATUS, M.WITHDRAWAL_YN FROM MEMBER M JOIN MEMBER_CATEGORY C ON M.MEMBER_CATEGORY_NO = C.NO WHERE M.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		MemberDetailVo vo = null;
		if(rs.next()) {
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String email = rs.getString("EMAIL");
			String address = rs.getString("ADDRESS");
			String name = rs.getString("NAME");
			String enrollDate = rs.getString("ENROLL_DATE");
			String updateDate = rs.getString("UPDATE_DATE");
			String status = rs.getString("STATUS");
			String withdrawalYn = rs.getString("WITHDRAWAL_YN");
			
			vo = new MemberDetailVo();
			vo.setNo(no);
			vo.setId(id);
			vo.setNick(nick);
			vo.setEmail(email);
			vo.setAddress(address);
			vo.setName(name);
			vo.setEnrollDate(enrollDate);
			vo.setUpdateDate(updateDate);
			vo.setStatus(status);
			vo.setWithdrawalYn(withdrawalYn);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	
	//회원제재하기
	public int memberStopDate(Connection conn, Map<String, String> map) throws Exception {
		String s = "INSERT INTO MEMBER_SANCTIONS(NO, SANCTION_TYPE_CODE, REPORT_LIST_NO, ENROLL_DATE, CANCEL_ENROLL_DATE, COUNT) VALUES(SEQ_MEMBER_SANCTIONS_NO.NEXTVAL, 2, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);		
		pstmt.setString(1, map.get("no"));
		pstmt.setString(2, map.get("enrollDate"));
		pstmt.setString(3, map.get("cancelEnrollDate"));
		
		int count = 0;
		count ++;
		
		pstmt.setInt(4, count);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//회원제재하기(member테이블)
	public int memberStop(Connection conn, Map<String, String> map) throws Exception {
		String s = "UPDATE MEMBER SET STATUS = 'X' WHERE NO IN ( SELECT M.NO FROM MEMBER M JOIN REPORT_LIST L ON M.NO = L.MEMBER_NO WHERE L.NO = ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, map.get("no"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//회원제재 제재횟수처리
	public int sanctionCount(Connection conn, Map<String, String> map) throws Exception {
		String s = "UPDATE MEMBER_SANCTIONS SET COUNT = COUNT + 1 WHERE REPORT_LIST_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, map.get("no"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//회원제재취소
	public int reportSearchCancel(Connection conn, String memberNick) throws Exception {
		String s = "UPDATE MEMBER SET STATUS = 'O' WHERE NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, memberNick);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//차량재고조회 글작성
	public int carInventoryWrite(Connection conn, CarInventoryVo vo) throws Exception {
		String s = "INSERT INTO RENTCAR(NO, CAR_KIND_NO, LOCAL_NO, COUNT, ENROLL_DATE, MAX, LICENSE_PLATE, LICENSE_DATE, PRICE, CHANGE_NAME, ORIGIN_NAME) VALUES(SEQ_RENTCAR_NO.NEXTVAL, ?, ?, 1, SYSDATE, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, vo.getKind());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, "4");
		pstmt.setString(4, vo.getLicensePlate());
		pstmt.setString(5, vo.getLicenseDate());
		pstmt.setString(6, vo.getPrice());
		pstmt.setString(7, vo.getChangeName());
		pstmt.setString(8, vo.getOriginName());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//차량재고조회 재고수정 화면조회
	public CarInventoryVo carInventoryEdit(Connection conn, String no) throws Exception {
		String s = "SELECT C.COUNT, C.NO, K.KIND, L.NAME, C.LICENSE_PLATE, TO_CHAR(C.LICENSE_DATE, 'YYYY-MM-DD') AS LICENSE_DATE, C.MAX, C.PRICE, C.CHANGE_NAME, C.ORIGIN_NAME FROM RENTCAR C JOIN CAR_KIND K ON C.CAR_KIND_NO = K.NO JOIN LOCAL_CATEGORY L ON C.LOCAL_NO = L.NO WHERE C.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		CarInventoryVo vo = null;
		if(rs.next()) {
			String kind = rs.getString("KIND");
			String name = rs.getString("NAME");
			String licensePlate = rs.getString("LICENSE_PLATE");
			String licenseDate = rs.getString("LICENSE_DATE");
			String max = rs.getString("MAX");
			String price = rs.getString("PRICE");
			String changeName = rs.getString("CHANGE_NAME");
			String originName = rs.getString("ORIGIN_NAME");
			String count = rs.getString("COUNT");
			
			vo = new CarInventoryVo();
			vo.setKind(kind);
			vo.setNo(no);
			vo.setName(name);
			vo.setLicensePlate(licensePlate);
			vo.setLicenseDate(licenseDate);
			vo.setMax(max);
			vo.setPrice(price);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
			vo.setCount(count);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	
	//차량재고조회 재고수정
	public int carCountEdit(Connection conn, String no, String count) throws Exception {
		String s = "UPDATE RENTCAR SET COUNT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, count);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//숙소재고조회 글작성
	public int accommodationInventoryWrite(Connection conn, AccommodationInventoryVo vo) throws Exception {
		String s = "INSERT INTO ACCOMODATION(NO, LOCAL_NO, ACCOMODATION_NO, NAME, MAX_PEOPLE, COUNT_YN, CHANGE_NAME, ORIGIN_NAME, CONTENT, PRICE) VALUES(SEQ_ACCOMODATION_NO.NEXTVAL, ?, ?, ?, ?, 'Y', ?, ?, '숙소', ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, vo.getLocalName());
		pstmt.setString(2, vo.getKind());
		pstmt.setString(3, vo.getName());
		pstmt.setString(4, vo.getMaxPeople());
		pstmt.setString(5, vo.getChangeName());
		pstmt.setString(6, vo.getOriginName());
		pstmt.setString(7, vo.getPrice());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//숙소재고조회 수정화면
	public AccommodationInventoryVo accommodationEdit(Connection conn, String no) throws Exception {
		String s = "SELECT A.NO, A.NAME, L.NAME AS LOCAL_NAME, K.KIND, A.MAX_PEOPLE, A.COUNT_YN, A.PRICE, A.CHANGE_NAME, A.ORIGIN_NAME FROM ACCOMODATION A JOIN LOCAL_CATEGORY L ON A.LOCAL_NO = L.NO JOIN ACCOMODATION_KIND K ON A.ACCOMODATION_NO = K.NO WHERE A.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		AccommodationInventoryVo vo = null;
		if(rs.next()) {
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String kind = rs.getString("KIND");
			String maxPeople = rs.getString("MAX_PEOPLE");
			String countYn = rs.getString("COUNT_YN");
			String price = rs.getString("PRICE");
			String changeName = rs.getString("CHANGE_NAME");
			String originName= rs.getString("ORIGIN_NAME");
			
			vo = new AccommodationInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setKind(kind);
			vo.setMaxPeople(maxPeople);
			vo.setCountYn(countYn);
			vo.setPrice(price);
			vo.setChangeName(changeName);
			vo.setOriginName(originName);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
	}
	
	//숙소재고조회 재고수정
	public int accommodationCountEdit(Connection conn, Map<String, String> map) throws Exception {
		String s = "UPDATE ACCOMODATION SET COUNT_YN = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, map.get("count"));
		pstmt.setString(2, map.get("no"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//기념품재고조회 게시글작성
	public int souvenirInventoryWrite(Connection conn, SouvenirInventoryVo vo) throws Exception {
		String s = "INSERT INTO SOUVENIR(NO, LOCAL_NO, NAME, PRICE, CONTENT, CHANGE_NAME, ORIGIN_NAME, COUNT) VALUES(SEQ_SOUVENIR_NO.NEXTVAL, ?, ?, ?, '내용', ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, vo.getLocalName());
		pstmt.setString(2, vo.getName());
		pstmt.setString(3, vo.getPrice());
		pstmt.setString(4, vo.getChangeName());
		pstmt.setString(5, vo.getOriginName());
		pstmt.setString(6, vo.getCount());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//기념품재고조회 수정화면
	public SouvenirInventoryVo souvenirEdit(Connection conn, String no) throws Exception {
		String s = "SELECT S.NO, S.NAME, S.CONTENT, TO_CHAR(S.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, L.NAME AS LOCAL_NAME, S.PRICE, S.COUNT, S.ORIGIN_NAME, S.CHANGE_NAME FROM SOUVENIR S JOIN LOCAL_CATEGORY L ON S.LOCAL_NO = L.NO WHERE S.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		SouvenirInventoryVo vo = null;
		if(rs.next()) {
			String name = rs.getString("NAME");
			String localName = rs.getString("LOCAL_NAME");
			String price = rs.getString("PRICE");
			String count = rs.getString("COUNT");
			String originName = rs.getString("ORIGIN_NAME");
			String changeName = rs.getString("CHANGE_NAME");
			String enrollDate = rs.getString("ENROLL_DATE");
			String content = rs.getString("CONTENT");
			
			vo = new SouvenirInventoryVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setLocalName(localName);
			vo.setPrice(price);
			vo.setCount(count);
			vo.setOriginName(originName);
			vo.setChangeName(changeName);
			vo.setEnrollDate(enrollDate);
			vo.setContent(content);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
	
	//기념품 재고수정
	public int souvenirCountEdit(Connection conn, Map<String, String> map) throws Exception {
		String s = "UPDATE SOUVENIR SET COUNT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, map.get("count"));
		pstmt.setString(2, map.get("no"));
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//판매등록요청 상세조회
	public SellRequestDetailVo sellRequestDetail(Connection conn, String no) throws Exception {
		String s = "SELECT B.NO, M.NICK, B.TITLE, B.CONTENT, TO_CHAR(B.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, I.TITLE AS IMG_TITLE FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN BOARD_IMG I ON B.BOARD_IMG_NO = I.NO WHERE B.BOARD_CATEGORY_NO = '2' AND UPLOAD_YN = 'Y' AND B.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		SellRequestDetailVo vo = null;
		if(rs.next()) {
			String nick = rs.getString("NICK");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String changeName = rs.getString("IMG_TITLE");

			vo = new SellRequestDetailVo();
			vo.setNo(no);
			vo.setWriter(nick);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setChangeName(changeName);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	//판매등록요청 등록
	public int sellRequestEnroll(Connection conn, String no) throws Exception {
		String s = "UPDATE BOARD SET UPLOAD_YN = 'N' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//판매등록요청 거절
	public int sellRequestRefuse(Connection conn, String no) throws Exception {
		String s = "UPDATE BOARD SET DELETE_YN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//광고배너 삭제
	public int bannerDelete(Connection conn, String no) throws Exception {
		String s = "UPDATE SOUVENIR_BANNER SET DELETE_YN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//광고배너 글작성
	public int AdbannerWrite(Connection conn, AdBannerVo vo) throws Exception {
		String s = "INSERT INTO SOUVENIR_BANNER(NO, MEMBER_NO, SOUVENIR_NO, NAME, IMAGE, DELETE_YN) VALUES(SEQ_SOUVENIR_BANNER_NO.NEXTVAL, ?, ?, ?, ?, 'N')";
		String memberNo = bannerMemberNo(conn, vo);
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, memberNo);
		pstmt.setString(2, vo.getSouvenirNo());
		pstmt.setString(3, vo.getName());
		pstmt.setString(4, vo.getImage());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//광고배너 글작성 회원번호 받아오기
	public String bannerMemberNo(Connection conn, AdBannerVo vo) throws Exception {
		String s = "SELECT NO FROM MEMBER WHERE NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, vo.getNick());
		ResultSet rs = pstmt.executeQuery();
		
		String memberNo = "";
		if(rs.next()) {
			memberNo = rs.getString("no");
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return memberNo;
	}

	//광고배너 수정화면
	public AdBannerVo AdBannerEdit(Connection conn, String no) throws Exception {
		String s = "SELECT B.NO, B.NAME, B.IMAGE, M.NICK, B.SOUVENIR_NO, S.NAME AS SOUVENIR_NAME FROM SOUVENIR_BANNER B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN SOUVENIR S ON B.SOUVENIR_NO = S.NO WHERE B.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		AdBannerVo vo = null;
		if(rs.next()) {
			String image = rs.getString("IMAGE");
			String name = rs.getString("NAME");
			String nick = rs.getString("NICK");
			String souvenirNo = rs.getString("SOUVENIR_NO");
			String souvenirName = rs.getString("SOUVENIR_NAME");
			
			vo = new AdBannerVo();
			vo.setNo(no);
			vo.setImage(image);
			vo.setName(name);
			vo.setNick(nick);
			vo.setSouvenirNo(souvenirNo);
			vo.setSouvenirName(souvenirName);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}

	//광고배너 게시글수정
	public int adBannerContentEdit(Connection conn, AdBannerVo vo, String memberNo) throws Exception {
		String s = "UPDATE SOUVENIR_BANNER SET NAME = ?, IMAGE = ?";

		if(vo.getNick() != null && vo.getNick().length() > 0) {
			s += ", MEMBER_NO = ?";
		}
		s += " WHERE NO = ? AND DELETE_YN = 'N'";
		
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getImage());
		
		if(vo.getNick() != null && vo.getNick().length() > 0) {
			pstmt.setString(3, memberNo);
			pstmt.setString(4, vo.getNo());
		}
		pstmt.setString(3, vo.getNo());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//배너수정 회원번호 받아오기
	public String bannerGetNo(Connection conn, String memberNick) throws Exception {
		String s = "SELECT NO FROM MEMBER WHERE NICK = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, memberNick);
		ResultSet rs = pstmt.executeQuery();
		
		String memberNo = "";
		if(rs.next()) {
			memberNo = rs.getString("NO");
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return memberNo;
	}

	//기념품이름으로 번호 가져오기
	public String souvenirGetNo(Connection conn, String souvenirName) throws Exception {
		String s = "SELECT NO FROM SOUVENIR WHERE NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(s);
		pstmt.setString(1, souvenirName);
		ResultSet rs = pstmt.executeQuery();
		
		String souvenirNo = "";
		if(rs.next()) {
			souvenirNo = rs.getString("NO");
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return souvenirNo;
	}
	

}
