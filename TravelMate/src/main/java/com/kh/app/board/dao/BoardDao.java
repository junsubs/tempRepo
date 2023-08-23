package com.kh.app.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.board.vo.CategoryVo;
import com.kh.app.board.vo.CommentVo;
import com.kh.app.board.vo.ReviewBoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.report.vo.ReportVo;
import com.kh.app.util.BoardImgVo;

public class BoardDao {

	public List<CategoryVo> getCategoryList(Connection conn) throws Exception {
			
			// SQL
			String sql = "SELECT * FROM CATEGORY";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			// tx || rs
			List<CategoryVo> cvoList = new ArrayList<>();
			while(rs.next()) {
				String no = rs.getString("NO");
				String name = rs.getString("NAME");
				
				CategoryVo vo = new CategoryVo();
				vo.setNo(no);
				vo.setName(name);
				
				cvoList.add(vo);
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return cvoList;
		}

	public int write(Connection conn, BoardVo bvo) throws Exception {
		
		String sql = "INSERT INTO BOARD ( NO , BOARD_CATEGORY_NO , MEMBER_NO  , TITLE , CONTENT ) VALUES ( SEQ_BOARD_NO.NEXTVAL ,1 , ? , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bvo.getMemberNo());
		pstmt.setString(2, bvo.getTitle());
		pstmt.setString(3, bvo.getContent());
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}


	// 강분 -회원별 게시글 목록 개수
	public int getMyBoardListCntByNo(Connection conn, String searchType, String searchValue, String mno) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM ( SELECT B.NO ,B.TITLE ,B.CONTENT ,B.MEMBER_NO ,B.BOARD_CATEGORY_NO ,B.ENROLL_DATE ,B.DELETE_YN ,B.MODIFY_DATE ,B.HIT ,M.NICK FROM BOARD B JOIN MEMBER M ON (B.MEMBER_NO = M.NO)) WHERE DELETE_YN = 'N' AND MEMBER_NO=?";
		if("title".equals(searchType)) {
			sql += "AND TITLE LIKE '%" + searchValue + "%'";
		}else if("category".equals(searchType)) {
			sql += "AND BOARD_CATEGORY_NO = " + searchValue;
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
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

	// 강분 -회원별 전체 작성 게시글 조회
	public List<BoardVo> getMyBoardListByNo(Connection conn, PageVo pv, String mno) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.BOARD_CATEGORY_NO , B.ENROLL_DATE , B.DELETE_YN , B.MODIFY_DATE , B.HIT , M.NICK , BC.NAME AS CATEGORY_NAME FROM BOARD B JOIN MEMBER M ON(B.MEMBER_NO = M.NO) JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) WHERE B.DELETE_YN = 'N' AND B.MEMBER_NO= ? ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<BoardVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String boardCategoryNo = rs.getString("BOARD_CATEGORY_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String hit = rs.getString("HIT");
			String memberNick = rs.getString("NICK");
			String categoryName = rs.getString("CATEGORY_NAME");
	
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setBoardCategoryNo(boardCategoryNo);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
			vo.setMemberNick(memberNick);
			vo.setCategoryName(categoryName);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	
	}

	// 강분 -회원별 검색 게시글 목록 조회
	public List<BoardVo> getMyBoardListByNo(Connection conn, PageVo pv, String searchType, String searchValue,String mno) throws Exception {

		// SQL
		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.BOARD_CATEGORY_NO , B.ENROLL_DATE , B.DELETE_YN , B.MODIFY_DATE , B.HIT , M.NICK , BC.NAME AS CATEGORY_NAME FROM BOARD B JOIN MEMBER M ON(B.MEMBER_NO = M.NO) JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) WHERE B.DELETE_YN = 'N' AND B.TITLE LIKE '%' || ? || '%' AND B.MEMBER_NO= ? ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if("category".equals(searchType)) {
			// sql (카테고리로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.BOARD_CATEGORY_NO , B.ENROLL_DATE , B.DELETE_YN , B.MODIFY_DATE , B.HIT , M.NICK , BC.NAME AS CATEGORY_NAME FROM BOARD B JOIN MEMBER M ON(B.MEMBER_NO = M.NO) JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) WHERE B.DELETE_YN = 'N' AND B.BOARD_CATEGORY_NO LIKE '%' || ? || '%' AND B.MEMBER_NO= ? ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?\r\n"
					+ "";
		}else {
			// 예외 던져주기
			// throw new Exception();
			// 값이 이상하면 기본 목록 조회
			return getMyBoardListByNo(conn, pv, mno);
		}

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString	(1, searchValue);
		pstmt.setString	(2, mno);
		pstmt.setInt	(3, pv.getBeginRow());
		pstmt.setInt	(4, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<BoardVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String boardCategoryNo = rs.getString("BOARD_CATEGORY_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String hit = rs.getString("HIT");
			String memberNick = rs.getString("NICK");
			String categoryName = rs.getString("CATEGORY_NAME");
	
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setBoardCategoryNo(boardCategoryNo);
			vo.setEnrollDate(enrollDate);
			vo.setDeleteYn(deleteYn);
			vo.setModifyDate(modifyDate);
			vo.setHit(hit);
			vo.setMemberNick(memberNick);
			vo.setCategoryName(categoryName);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	
	}
	
	//전체공지사항ㅈ회
	public List<BoardVo> noticeList(Connection conn , PageVo pv ) throws Exception {

		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT * FROM BOARD B JOIN BOARD_CATEGORY BC  ON(B.BOARD_CATEGORY_NO = BC.NO) WHERE BC.NO=1  AND B.DELETE_YN='N' ORDER BY B.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> bvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String memberNo = rs.getString("MEMBER_NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setTitle(title);
			vo.setEnrollDate(enrollDate);
			vo.setHit(hit);
			
			bvoList.add(vo);
			
		}
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
			return bvoList;
	}

		// board Cnt
		public int selectCnt(Connection conn) throws Exception {
		//SQL
		String sql = "SELECT COUNT(*) FROM BOARD WHERE DELETE_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
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
		
		// board Cnt
		public int reviewSelectCnt(Connection conn) throws Exception {
		//SQL
		String sql = "SELECT COUNT(*) FROM BOARD WHERE DELETE_YN = 'N' AND BOARD_CATEGORY_NO=4";
		PreparedStatement pstmt = conn.prepareStatement(sql);
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

	public BoardVo noticeDetail(Connection conn, String no) throws Exception {

		//이거 쿼리문 MEMBER_CATEGORY 조인후 nick이 보이게 수정해야함  --완료
		String sql = "SELECT B.NO ,B.BOARD_CATEGORY_NO ,B.MEMBER_NO ,B. BOARD_IMG_NO ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE ,'YYYY-MM-DD') AS ENROLL_DATE ,B.DELETE_YN ,B.HIT ,B.UPLOAD_YN ,B.MODIFY_DATE ,M.NICK AS MEMBER_NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.NO =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo  vo = null;
		if(rs.next()) {
			String bcn = rs.getString("BOARD_CATEGORY_NO");
			String mn = rs.getString("MEMBER_NO");
			String memberNick = rs.getString("MEMBER_NICK");
			String bin = rs.getString("BOARD_IMG_NO");
			String t = rs.getString("TITLE");
			String c = rs.getString("CONTENT");
			String e = rs.getString("ENROLL_DATE");
			String d = rs.getString("DELETE_YN");
			String h = rs.getString("HIT");
			String u = rs.getString("UPLOAD_YN");
			String m = rs.getString("MODIFY_DATE");
			
			vo = new BoardVo();
			vo.setNo(no);
			vo.setBoardCategoryNo(bcn);
			vo.setMemberNo(mn);
			vo.setMemberNick(memberNick);
			vo.setBoardImgNo(bin);
			vo.setTitle(t);
			vo.setContent(c);
			vo.setEnrollDate(e);
			vo.setDeleteYn(d);
			vo.setHit(h);
			vo.setUploadYn(u);
			vo.setModifyDate(m);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return vo;
	}

	//조회수 처리
	public int updateHit(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE BOARD SET HIT = HIT+1 WHERE NO = ? AND DELETE_YN = 'N' AND BOARD_CATEGORY_NO=1";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
		
	}
	
	//조회수 처리
		public int carReviewupdateHit(Connection conn, String no) throws Exception {
			
			String sql = "UPDATE BOARD SET HIT = HIT+1 WHERE NO = ? AND DELETE_YN = 'N' AND BOARD_CATEGORY_NO=4";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			int result = pstmt.executeUpdate();
			JDBCTemplate.close(pstmt);
			return result;
			
		}

		public int freeUpdateHit(Connection conn, String no) throws Exception {
			
			String sql = "UPDATE BOARD SET HIT = HIT+1 WHERE NO = ? AND DELETE_YN = 'N' AND BOARD_CATEGORY_NO=5";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			int result = pstmt.executeUpdate();
			JDBCTemplate.close(pstmt);
			return result;
			
		}
		
	// 공지사항 삭제 처리
	public int noticeDelete(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE BOARD SET DELETE_YN = 'Y' WHERE NO =?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);

		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
			
	}

	//도연 -공지사항 수정 처리
	public int noticeEdit(Connection conn, BoardVo vo) throws Exception {
		
		String sql = "UPDATE BOARD SET TITLE = ? , CONTENT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getNo());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//공지사항 댓글쓰기
	public int NoticeReplyWrite(Connection conn, CommentVo vo) throws Exception {
		
		//SQL
		String sql = "INSERT INTO \"COMMENT\" ( NO , MEMBER_NO , BOARD_NO , CONTENT ) VALUES ( SEQ_COMMENT_NO.NEXTVAL , ? , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getBoardNo());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	//자유게시판 리스트
	public List<BoardVo> freeList(Connection conn, PageVo pv) throws Exception {
		
		String sql = "SELECT NO, TITLE, TO_CHAR(ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, HIT, NICK FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO, B.TITLE, B.ENROLL_DATE, B.HIT, M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.DELETE_YN = 'N' AND B.BOARD_CATEGORY_NO = 5 ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> fvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String enrollDate = rs.getString("ENROLL_DATE");
			String memberNick = rs.getString("NICK");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setEnrollDate(enrollDate);
			vo.setMemberNick(memberNick);
			vo.setHit(hit);
			
			fvoList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return fvoList;
	}

	//댓글 보여주기
	public List<CommentVo> noticeReplyList(Connection conn, String boardNo) throws Exception {
		
		String sql = "SELECT M.NICK AS MEMBER_NICK, B.NO, B.BOARD_CATEGORY_NO, C.NO, C.MEMBER_NO, C.BOARD_NO, C.QNA_NO, C.CONTENT, TO_CHAR(C.ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE, C.MODIFY_DATE, C.DELETE_YN FROM \"COMMENT\" C JOIN BOARD B ON C.BOARD_NO = B.NO JOIN MEMBER M ON C.MEMBER_NO = M.NO WHERE B.NO = ? ORDER BY C.NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		ResultSet rs = pstmt.executeQuery();
		
		List<CommentVo> replyList = new ArrayList<>();
		while(rs.next()) {
			String no =  rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modiFyDate = rs.getString("MODIFY_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("MEMBER_NICK");
			
			CommentVo vo = new CommentVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setBoardNo(boardNo);
			vo.setContent(content);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modiFyDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			replyList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return replyList;
		
	}

	//자유게시판 상세조회
	public BoardVo freeDetail(Connection conn, String no) throws Exception {

		String sql = "SELECT B.NO ,B.BOARD_CATEGORY_NO ,B. MEMBER_NO ,B. BOARD_IMG_NO ,B. TITLE ,B. CONTENT ,TO_CHAR(B. ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE ,B. DELETE_YN ,B. HIT ,B. UPLOAD_YN ,B. MODIFY_DATE ,M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.BOARD_CATEGORY_NO =5 AND B.NO = ? AND DELETE_YN='N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo fvo = null;
		if(rs.next()) {
			String boardCategoryNo = rs.getString("BOARD_CATEGORY_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String memberNick = rs.getString("NICK");
			String boardImgNo = rs.getString("BOARD_IMG_NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String hit = rs.getString("HIT");
			String uploadYn = rs.getString("UPLOAD_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			
			fvo = new BoardVo();
			
			fvo.setNo(no);
			fvo.setBoardCategoryNo(boardCategoryNo);
			fvo.setMemberNo(memberNo);
			fvo.setMemberNick(memberNick);
			fvo.setBoardImgNo(boardImgNo);
			fvo.setTitle(title);
			fvo.setContent(content);
			fvo.setEnrollDate(enrollDate);
			fvo.setDeleteYn(deleteYn);
			fvo.setHit(hit);
			fvo.setUploadYn(uploadYn);
			fvo.setModifyDate(modifyDate);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return fvo;
	}

	//자유게시판 삭제
	public int freeDelete(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE BOARD SET DELETE_YN='Y' WHERE NO = ? AND BOARD_CATEGORY_NO=5";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	//자유게시판 수정
	public int freeEdit(Connection conn, BoardVo vo) throws Exception {
		
		String sql = "UPDATE BOARD SET TITLE = ? , CONTENT=? WHERE BOARD_CATEGORY_NO = 5 AND NO=?"; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getNo());
		int result = pstmt.executeUpdate();
		
		return result;
	}

	public List<BoardVo> noticeList(Connection conn, PageVo pv, String searchValue, String searchType) throws Exception {

		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , TO_CHAR(B.ENROLL_DATE ,'YYYY-MM-DD')AS ENROLL_DATE , B.DELETE_YN , B.HIT FROM BOARD B  JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) WHERE B.BOARD_CATEGORY_NO= 1 AND B.DELETE_YN='N' AND B.TITLE LIKE '%'||?||'%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("writer")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.ENROLL_DATE,B.HIT , B.DELETE_YN ,M.NICK AS MEMBER_NICK FROM BOARD B  JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.CATEGORY_NO= 1 AND B.DELETE_YN='N' AND M.NICK LIKE '%' || ? || '%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return noticeList(conn, pv);
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> bvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String enrolldate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setEnrollDate(enrolldate);
			vo.setDeleteYn(deleteYn);
			vo.setHit(hit);
			
			bvoList.add(vo);
		}

		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return bvoList;
	}

	//자유게시판 작성
	public int freeWrite(Connection conn, BoardVo bvo) throws Exception {
		
		String sql = "INSERT INTO BOARD ( NO , BOARD_CATEGORY_NO , MEMBER_NO  , TITLE , CONTENT ) VALUES ( SEQ_BOARD_NO.NEXTVAL ,5 , ? , ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bvo.getMemberNo());
		pstmt.setString(2, bvo.getTitle());
		pstmt.setString(3, bvo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	
	//자유게시판  댓글 달기
	public int freeBoardRplyWrite(Connection conn, CommentVo cvo) throws Exception {

		String sql = "INSERT INTO \"COMMENT\" ( NO , MEMBER_NO , BOARD_NO , CONTENT ) VALUES ( SEQ_COMMENT_NO.NEXTVAL , ? , ? , ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cvo.getMemberNo());
		pstmt.setString(2, cvo.getBoardNo());
		pstmt.setString(3, cvo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//자유게시판 댓글 보여주기
	public List<CommentVo> freeReplyList(Connection conn, String boardNo) throws Exception {

		String sql = "SELECT M.NICK AS MEMBER_NICK, B.NO, B.BOARD_CATEGORY_NO, C.NO, C.MEMBER_NO, C.BOARD_NO, C.QNA_NO, C.CONTENT, TO_CHAR(C.ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE ,C.MODIFY_DATE ,C.DELETE_YN FROM \"COMMENT\" C JOIN BOARD B ON C.BOARD_NO = B.NO JOIN MEMBER M ON C.MEMBER_NO = M.NO WHERE B.NO = ? ORDER BY C.NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardNo);
		ResultSet rs = pstmt.executeQuery();

		List<CommentVo> replyList = new ArrayList<>();
		while(rs.next()) {

			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modiFyDate = rs.getString("MODIFY_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String memberNick = rs.getString("MEMBER_NICK");
			
			CommentVo vo = new CommentVo();
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setContent(content);
			vo.setBoardNo(boardNo);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modiFyDate);
			vo.setDeleteYn(deleteYn);
			vo.setMemberNick(memberNick);
			
			replyList.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return replyList;
	}

	//판매 요청글
	public int sellRequestWrite(Connection conn, BoardVo vo , BoardImgVo biVo) throws Exception {

		String sql = "INSERT ALL INTO BOARD ( NO , BOARD_CATEGORY_NO , MEMBER_NO , TITLE , CONTENT ) VALUES ( SEQ_BOARD_NO.NEXTVAL , '2', ? , ? , ? ) INTO BOARD_IMG(NO ,TITLE) VALUES(SEQ_BOARD_IMG_NO.NEXTVAL , ?) SELECT *FROM DUAL";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, biVo.getChangeName());
		int result = pstmt.executeUpdate();
		return result;
		
	}

	//이거 모르겠다
	public int getBoardListCnt(Connection conn, String searchType, String searchValue) throws Exception {

		//SQL
		String sql = "SELECT COUNT(*) FROM ( SELECT B.NO ,B.TITLE ,B.CONTENT ,B.BOARD_CATEGORY_NO ,B.ENROLL_DATE ,B.DELETE_YN ,B.MODIFY_DATE ,B.HIT ,M.NICK FROM BOARD B JOIN MEMBER M ON (B.MEMBER_NO = M.NO) ) WHERE DELETE_YN = 'N' AND BOARD_CATEGORY_NO =1";
		if("title".equals(searchType)) {
			sql += "AND TITLE LIKE '%" + searchValue + "%'";
		}else if("writer".equals(searchType)) {
			sql += "AND NICK LIKE '%" + searchValue + "%'";
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

	
	//차량 리뷰
	public int carReviewWrite(Connection conn, BoardVo vo) throws Exception {
		
		String sql = "INSERT INTO BOARD ( NO , BOARD_CATEGORY_NO , MEMBER_NO , TITLE , CONTENT ) VALUES ( SEQ_BOARD_NO.NEXTVAL , 4, ? , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

	//차량 리뷰 게시글 리스트
	public List<BoardVo> carReviewList(Connection conn, PageVo pv) throws Exception {

		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.* , M.NICK FROM BOARD B JOIN BOARD_CATEGORY BC  ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO =M.NO WHERE BC.NO=4  AND B.DELETE_YN='N' ORDER BY B.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> rvoList = new ArrayList<>();
		while(rs.next()) {
			
			String no = rs.getString("NO");
			String boardCategoryNo = rs.getString("BOARD_CATEGORY_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String boardImgNo = rs.getString("BOARD_IMG_NO");
			String content = rs.getString("CONTENT");
			String deletYn = rs.getString("DELETE_YN");
			String uploadYn = rs.getString("UPLOAD_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String title = rs.getString("TITLE");
			String memberNick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String hit = rs.getString("HIT");

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setEnrollDate(enrollDate);
			vo.setHit(hit);
			vo.setBoardCategoryNo(boardCategoryNo);
			vo.setBoardImgNo(boardImgNo);
			vo.setMemberNo(memberNo);
			vo.setContent(content);
			vo.setMemberNick(memberNick);
			vo.setDeleteYn(deletYn);
			vo.setUploadYn(uploadYn);
			vo.setModifyDate(modifyDate);
			
			rvoList.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return rvoList;
	}

	//차량 리뷰 상세조회
	public BoardVo carReviewDetail(Connection conn, String no) throws Exception {

		String sql = "SELECT B.NO ,B.TITLE ,B.CONTENT ,TO_CHAR(B.ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE ,B.HIT ,M.NICK AS MEMBER_NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE BOARD_CATEGORY_NO =4 AND B.NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);

		ResultSet rs = pstmt.executeQuery();
		
		BoardVo vo = null;
		if(rs.next()) {
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String hit = rs.getString("HIT");
			String nick = rs.getString("MEMBER_NICK");
			
			 vo = new BoardVo();
			 vo.setTitle(title);
			 vo.setContent(content);
			 vo.setEnrollDate(enrollDate);
			 vo.setHit(hit);
			 vo.setMemberNick(nick);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
	
	}

	//관리자 신분으로 모든 판매요청 리스트 조회
	public List<BoardVo> sellRequestList(Connection conn, PageVo pv) throws Exception {

		String sql = "SELECT NO , TO_CHAR(ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE ,NICK ,BITITLE FROM (SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO ,B.ENROLL_DATE ,BI.TITLE AS BITITLE ,M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN BOARD_IMG BI ON B.NO = BI.NO WHERE B.DELETE_YN = 'N' AND B.BOARD_CATEGORY_NO=2  ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String memberNick = rs.getString("NICK");
			String title = rs.getString("BITITLE");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setBoardImgTitle(title);
			vo.setEnrollDate(enrollDate);
			vo.setMemberNick(memberNick);
			
			voList.add(vo);
		}
		System.out.println(voList);
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return voList;
	
	}

	//판매자 자신이 쓴 판매요청 리스트 조회
	public List<BoardVo> sellRequestList(Connection conn, PageVo pv, String memberNo) throws Exception {
		

		String sql = "SELECT NO , TO_CHAR(ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE ,BITITLE FROM (SELECT ROWNUM RNUM, T.* FROM ( SELECT B.NO ,B.ENROLL_DATE ,BI.TITLE AS BITITLE FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO JOIN BOARD_IMG BI ON B.BOARD_IMG_NO = BI.NO WHERE B.DELETE_YN = 'N' AND B.MEMBER_NO = ? ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt= conn.prepareStatement(sql);
		pstmt.setString(1, memberNo);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String enrollDate = rs.getString("ENROLL_DATE");
			String title = rs.getString("BITITLE");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setBoardImgTitle(title);
			vo.setEnrollDate(enrollDate);
			
			voList.add(vo);
		}
		System.out.println(voList);
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return voList;

//		String sql = "SELECT NO , TITLE , TO_CHAR(ENROLL_DATE , 'YYYY-MM-DD') AS ENROLL_DATE FROM (SELECT ROWNUM RNUM, T.* FROM ( SELECT B.* ,M.NICK FROM BOARD B JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.DELETE_YN = 'N' AND B.BOARD_CATEGORY_NO=2 AND B.MEMBER_NO=? ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
//		PreparedStatement pstmt= conn.prepareStatement(sql);
//		pstmt.setString(1, memberNo);
//		pstmt.setInt(2, pv.getBeginRow());
//		pstmt.setInt(3, pv.getLastRow());
//		ResultSet rs = pstmt.executeQuery();
//		
//		 List<BoardVo> voList = new ArrayList<>();
//		while(rs.next()) {
//			String no = rs.getString("NO");
//			String title = rs.getString("TITLE");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			
//			BoardVo vo = new BoardVo();
//			vo.setNo(no);
//			vo.setTitle(title);
//			vo.setEnrollDate(enrollDate);
//			voList.add(vo);
//		}
//		
//		JDBCTemplate.close(pstmt);
//		JDBCTemplate.close(rs);
//		return voList;
	}

	public int getFreeBoardListCnt(Connection conn, String searchType, String searchValue) throws Exception {
	
		//SQL
		String sql = "SELECT COUNT(*) FROM ( SELECT B.NO ,B.TITLE ,B.CONTENT ,B.BOARD_CATEGORY_NO ,B.ENROLL_DATE ,B.DELETE_YN ,B.MODIFY_DATE ,B.HIT ,M.NICK FROM BOARD B JOIN MEMBER M ON (B.MEMBER_NO = M.NO) ) WHERE DELETE_YN = 'N' AND BOARD_CATEGORY_NO =5";
		if("title".equals(searchType)) {
			sql += "AND TITLE LIKE '%" + searchValue + "%'";
		}else if("writer".equals(searchType)) {
			sql += "AND NICK LIKE '%" + searchValue + "%'";
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

	//검색으로 자유게시판 목록 조회
	public List<BoardVo> freeList(Connection conn, PageVo pv, String searchValue, String searchType) throws Exception {

		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql ="SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.ENROLL_DATE , B.DELETE_YN ,B.HIT ,M.NICK FROM BOARD B  JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.BOARD_CATEGORY_NO= 5 AND B.DELETE_YN='N' AND B.TITLE LIKE '%'||?||'%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("writer")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.ENROLL_DATE , B.DELETE_YN ,B.HIT ,M.NICK  FROM BOARD B  JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.BOARD_CATEGORY_NO= 5 AND B.DELETE_YN='N' AND M.NICK LIKE '%'|| ? ||'%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return noticeList(conn, pv);
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> fvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String memberNick = rs.getString("NICK");
			String enrolldate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String hit = rs.getString("hit");
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setNo(no);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberNick(memberNick);
			vo.setEnrollDate(enrolldate);
			vo.setDeleteYn(deleteYn);
			vo.setHit(hit);
			
			fvoList.add(vo);
	}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return fvoList;
	}

	public int roomReviewWrite(Connection conn, BoardVo vo) throws Exception {

		String sql = "INSERT INTO BOARD ( NO , BOARD_CATEGORY_NO , MEMBER_NO , TITLE , CONTENT ) VALUES ( SEQ_BOARD_NO.NEXTVAL , 4, ? , ? , ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

	//숙소 리뷰 리스트 조회
	public List<BoardVo> roomReviewList(Connection conn, PageVo pv) throws Exception {
		
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.* , M.NICK FROM BOARD B JOIN BOARD_CATEGORY BC  ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO =M.NO WHERE BC.NO=4  AND B.DELETE_YN='N' ORDER BY B.NO DESC) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> rvoList = new ArrayList<>();
		while(rs.next()) {
			
			String no = rs.getString("NO");
			String boardCategoryNo = rs.getString("BOARD_CATEGORY_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String boardImgNo = rs.getString("BOARD_IMG_NO");
			String content = rs.getString("CONTENT");
			String deletYn = rs.getString("DELETE_YN");
			String uploadYn = rs.getString("UPLOAD_YN");
			String modifyDate = rs.getString("MODIFY_DATE");
			String title = rs.getString("TITLE");
			String memberNick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String hit = rs.getString("HIT");

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setEnrollDate(enrollDate);
			vo.setHit(hit);
			vo.setBoardCategoryNo(boardCategoryNo);
			vo.setBoardImgNo(boardImgNo);
			vo.setMemberNo(memberNo);
			vo.setContent(content);
			vo.setMemberNick(memberNick);
			vo.setDeleteYn(deletYn);
			vo.setUploadYn(uploadYn);
			vo.setModifyDate(modifyDate);
			
			rvoList.add(vo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return rvoList;
	}

	public int noticeBoardReport(Connection conn, ReportVo vo) throws Exception {

		String sql = "INSERT INTO REPORT_LIST (NO, MEMBER_NO, BOARD_NO, SANCTION_REASON_NO, CONTENT) VALUES (SEQ_REPORT_LIST_NO.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getBoardNo());
		pstmt.setString(3, vo.getSanctionReasonNo());
		pstmt.setString(4, vo.getContent());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	//자유게시판 신고
	public int freeBoardReport(Connection conn, ReportVo vo) throws Exception {
		String sql = "INSERT INTO REPORT_LIST (NO, MEMBER_NO, BOARD_NO, SANCTION_REASON_NO, CONTENT) VALUES (SEQ_REPORT_LIST_NO.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getBoardNo());
		pstmt.setString(3, vo.getSanctionReasonNo());
		pstmt.setString(4, vo.getContent());
		
		int result = pstmt.executeUpdate();
		JDBCTemplate.close(pstmt);
		return result;
	}

	//검색으로 리뷰 조회
	public List<BoardVo> carReviewList(Connection conn, PageVo pv, String searchValue, String searchType) throws Exception {

		String sql = "";
		if(searchType.equals("title")) {
			// sql (제목으로 검색)
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , TO_CHAR(B.ENROLL_DATE ,'YYYY-MM-DD')AS ENROLL_DATE , B.DELETE_YN , B.HIT , M.NICK FROM BOARD B JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.BOARD_CATEGORY_NO= 4 AND B.DELETE_YN='N' AND B.TITLE LIKE '%'||?||'%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else if(searchType.equals("writer")) {
			sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.MEMBER_NO , B.ENROLL_DATE,B.HIT , B.DELETE_YN ,M.NICK FROM BOARD B  JOIN BOARD_CATEGORY BC ON(B.BOARD_CATEGORY_NO = BC.NO) JOIN MEMBER M ON B.MEMBER_NO = M.NO WHERE B.BOARD_CATEGORY_NO= 4 AND B.DELETE_YN='N' AND M.NICK LIKE '%'||?||'%' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		}else {
			return noticeList(conn, pv);
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, searchValue);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> bvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String memberNo = rs.getString("MEMBER_NO");
			String memberNick = rs.getString("NICK");
			String enrolldate = rs.getString("ENROLL_DATE");
			String deleteYn = rs.getString("DELETE_YN");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemberNo(memberNo);
			vo.setMemberNick(memberNick);
			vo.setEnrollDate(enrolldate);
			vo.setDeleteYn(deleteYn);
			vo.setHit(hit);
			
			bvoList.add(vo);
		}

		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return bvoList;
	}

	public int sellSelectCnt(Connection conn) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM BOARD WHERE DELETE_YN = 'N' AND BOARD_CATEGORY_NO=2";
		PreparedStatement pstmt = conn.prepareStatement(sql);
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

	public int sellRequestImg(Connection conn, BoardImgVo biVo) throws Exception {
		
		String sql = "INSERT INTO BOARD_IMG(NO,TITLE) VALUES(SEQ_BOARD_IMG_NO.NEXTVAL ,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, biVo.getTitle());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		return result;
	}

	public String selectImgTitle(Connection conn ,BoardVo vo) throws Exception {

		String sql = "SELECT TITLE FROM BOARD_IMG WHERE NO = F01";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		String title = "";
		if(rs.next()) {
			 title = rs.getString("TITLE");
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return title;
	}

}//class
