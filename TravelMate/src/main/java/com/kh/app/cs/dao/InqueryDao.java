package com.kh.app.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.vo.InqueryReplyVo;
import com.kh.app.cs.vo.InqueryVo;

public class InqueryDao {

	// 문의하기 게시글 작성
	public int write(Connection conn, InqueryVo ivo) throws Exception {

		// sql
		String sql = "INSERT INTO QNA(NO, MEMBER_NO, TITLE, CONTENT) VALUES(SEQ_QNA_NO.NEXTVAL, ? , ?,  ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ivo.getMemberNo());
		pstmt.setString(2, ivo.getTitle());
		pstmt.setString(3, ivo.getContent());
		int result =  pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 문의글 목록 select
	public List<InqueryVo> selectInqueryList(Connection conn, PageVo pv) throws Exception {

		// SQL
		String sql = "SELECT Q.NO , Q.MEMBER_NO , Q.TITLE , Q.CONTENT , TO_CHAR(Q.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE , Q.DELETE_YN ,M.NICK FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT * FROM QNA WHERE DELETE_YN='N' ORDER BY NO DESC ) T )Q JOIN MEMBER M ON (Q.MEMBER_NO = M.NO) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<InqueryVo> list = new ArrayList<>();
		
		while(rs.next()) {
			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("NICK");
			
			InqueryVo vo = new InqueryVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			list.add(vo);
			
		}

		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return list;
		
	}

	// 문의글 상세 조회
	public InqueryVo selectInqueryOneByNo(Connection conn, String no) throws Exception {

		// SQL
		String sql = "SELECT Q.NO , Q.MEMBER_NO , Q.TITLE , Q.CONTENT , TO_CHAR(Q.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE , Q.DELETE_YN, M.NICK FROM QNA Q JOIN MEMBER M ON(Q.MEMBER_NO = M.NO) WHERE Q.NO = ? AND Q.DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		InqueryVo vo = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("NICK");
			
			vo = new InqueryVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);

		return vo;
		
	}

	
	// 글 개수 세기
	public int getInqueryListCnt(Connection conn, String searchType, String searchValue) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM (SELECT Q.NO ,Q.TITLE ,Q.CONTENT ,Q.MEMBER_NO ,Q.ENROLL_DATE ,Q.DELETE_YN ,M.NICK FROM QNA Q JOIN MEMBER M ON (Q.MEMBER_NO = M.NO))  WHERE DELETE_YN='N'";
		if("title".equals(searchType)) {
			sql += "AND TITLE LIKE '%" + searchValue + "%'";
		}else if("writer".equals(searchType)) {
			sql += "AND NICK LIKE '%" + searchValue + "%'";
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
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

	// 전체 문의글 조회
	public List<InqueryVo> getInqueryList(Connection conn, PageVo pv, String mno) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT Q.NO , Q.TITLE , Q.CONTENT , Q.MEMBER_NO , Q.ENROLL_DATE , Q.DELETE_YN , M.NICK FROM QNA Q JOIN MEMBER M ON( Q.MEMBER_NO = M.NO) WHERE Q.DELETE_YN = 'N' AND M.NO= ? ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<InqueryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("NICK");
	
			InqueryVo vo = new InqueryVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
		
	}

	// 검색해서 문의글 조회
	public List<InqueryVo> getInqueryList(Connection conn, PageVo pv, String searchType, String searchValue, String mno) throws Exception {

		// sql
		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT Q.NO , Q.TITLE , Q.CONTENT , Q.MEMBER_NO , Q.ENROLL_DATE , Q.DELETE_YN , M.NICK FROM QNA Q JOIN MEMBER M ON(Q.MEMBER_NO = M.NO) WHERE Q.DELETE_YN= 'N' AND M.NO=? AND Q.TITLE LIKE '%' || ? || '%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			// 예외 던져주기
			// throw new Exception();
			// 값이 이상하면 기본 목록 조회
			return getInqueryList(conn, pv, mno);
		}
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setString	(2, searchValue);
		pstmt.setInt	(3, pv.getBeginRow());
		pstmt.setInt	(4, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<InqueryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("NICK");
	
			InqueryVo vo = new InqueryVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	
	
	}

	// 1:1문의 답변 작성
	public int replyWrite(Connection conn, InqueryReplyVo vo) throws Exception {

		// SQL
		String sql = "INSERT INTO \"C##SEMI\".\"COMMENT\" ( NO , MEMBER_NO  , CONTENT , QNA_NO ) VALUES ( SEQ_COMMENT_NO.NEXTVAL , '1' , ? , ? )";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getQnaNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	// 1:1문의 답변 조회
	public List<InqueryReplyVo> selectReplyList(Connection conn, String inqueryNo) throws Exception {

		// SQL
		String sql = "SELECT * FROM \"COMMENT\" WHERE QNA_NO = ?";
//		String sql2 = "SELECT NICK FROM MEMBER JOIN NOTICE_REPLY ON MEMBER.NO = NOTICE_REPLY.WRITER_NO WHERE MEMBER.NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
//		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt.setString(1, inqueryNo);
//		pstmt2.setString(1, );
		ResultSet rs = pstmt.executeQuery();
		
		List<InqueryReplyVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
//			String qnaNo = rs.getString("QNA_NO");
			String deleteYn = rs.getString("DELETE_YN");
			
			InqueryReplyVo vo = new InqueryReplyVo();
			vo.setNo(no);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setEnrollDate(enrollDate);
//			vo.setQnaNo(qnaNo);
			vo.setDeleteYn(deleteYn);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return voList;
	
	}

	// 관리자 로그인 시 전체 회원 문의 내역 조회(검색 ver)
	public List<InqueryVo> getInqueryListAll(Connection conn, PageVo pv, String searchType, String searchValue) throws Exception {

		// sql
		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT Q.NO , Q.TITLE , Q.CONTENT , Q.MEMBER_NO , Q.ENROLL_DATE , Q.DELETE_YN , M.NICK FROM QNA Q JOIN MEMBER M ON(Q.MEMBER_NO = M.NO) WHERE Q.DELETE_YN= 'N' AND Q.TITLE LIKE '%' || ? || '%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("writer")) {
			// sql (작성자로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT Q.NO , Q.TITLE , Q.CONTENT , Q.MEMBER_NO , Q.ENROLL_DATE , Q.DELETE_YN , M.NICK FROM QNA Q JOIN MEMBER M ON(Q.MEMBER_NO = M.NO) WHERE Q.DELETE_YN= 'N' AND M.NICK LIKE '%' || ? || '%' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			// 예외 던져주기
			// throw new Exception();
			// 값이 이상하면 기본 목록 조회
			return getInqueryListAll(conn, pv);
		}
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, searchValue);
		pstmt.setInt	(2, pv.getBeginRow());
		pstmt.setInt	(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<InqueryVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("NICK");
	
			InqueryVo vo = new InqueryVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
		
	
	}

	// 관리자 로그인 시 전체 회원의 문의 내역 조회
	public List<InqueryVo> getInqueryListAll(Connection conn, PageVo pv) throws Exception {
		
		// sql
				String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT Q.NO , Q.TITLE , Q.CONTENT , Q.MEMBER_NO , Q.ENROLL_DATE , Q.DELETE_YN , M.NICK FROM QNA Q JOIN MEMBER M ON( Q.MEMBER_NO = M.NO) WHERE Q.DELETE_YN = 'N' ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pv.getBeginRow());
				pstmt.setInt(2, pv.getLastRow());
				ResultSet rs = pstmt.executeQuery();
				
				// tx || rs
				List<InqueryVo> voList = new ArrayList<>();
				while(rs.next()) {
					String no = rs.getString("NO");
					String title = rs.getString("TITLE");
					String content = rs.getString("CONTENT");
					String memberNo = rs.getString("MEMBER_NO");
					String enrollDate = rs.getString("ENROLL_DATE");
					String deleteYn = rs.getString("DELETE_YN");
					String memberNick = rs.getString("NICK");
			
					InqueryVo vo = new InqueryVo();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setMemberNo(memberNo);
					vo.setEnrollDate(enrollDate);
					vo.setDeleteYn(deleteYn);
					vo.setMemberNick(memberNick);
					
					voList.add(vo);
				}
				
				JDBCTemplate.close(rs);
				JDBCTemplate.close(pstmt);
				
				return voList;
		
		
	}

	public int getReplyCnt(Connection conn, InqueryVo vo) throws Exception {
		
		// SQL
		String sql = "SELECT COUNT(*) FROM \"COMMENT\" C JOIN QNA Q ON (Q.NO = C.QNA_NO) WHERE Q.NO =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNo());
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

}
