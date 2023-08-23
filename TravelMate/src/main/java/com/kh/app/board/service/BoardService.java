package com.kh.app.board.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import com.kh.app.board.dao.BoardDao;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.board.vo.CategoryVo;
import com.kh.app.board.vo.CommentVo;
import com.kh.app.board.vo.ReviewBoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.dao.MemberDao;
import com.kh.app.report.vo.ReportVo;
import com.kh.app.util.BoardImgVo;

public class BoardService {
	
private final BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao(); 
	}

	//카테고리 리스트
	public List<CategoryVo> getCategoryList() throws Exception {
		
		// 커넥션
		try(Connection conn = JDBCTemplate.getConnection();){
			List<CategoryVo> cvoList = dao.getCategoryList(conn);

			return cvoList;
			}
		}

	//글쓰기
	public int write(BoardVo bvo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int result = dao.write(conn ,bvo);
		//tx rs
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}


	// 강분 -회원별 게시글 개수
	public int getMyBoardListCntByNo(String searchType, String searchValue, String mno) throws Exception {

		// connn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getMyBoardListCntByNo(conn,searchType, searchValue, mno);
		
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	// 강분 -회원별 전체 게시글 목록 조회
	public List<BoardVo> getMyBoardListByNo(PageVo pv, String mno) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<BoardVo> voList = dao.getMyBoardListByNo(conn,pv,mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
		
	}

	// 강분 -회원별 검색 게시글 목록 조회
	public List<BoardVo> getMyBoardListByNo(PageVo pv, String searchType, String searchValue, String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<BoardVo> voList = dao.getMyBoardListByNo(conn,pv, searchType, searchValue, mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
		
	}

	//도연 - 공지사항 게시글 목록 조회
	//전체조회
	public List<BoardVo> noticeList(PageVo pv ) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<BoardVo>bvoList = dao.noticeList(conn ,pv);
		
		JDBCTemplate.close(conn);
	
		return bvoList;
	}
	
	// 검색해서 글목록조회
	public List<BoardVo> noticeList(PageVo pv, String searchValue, String searchType) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<BoardVo>bvoList = dao.noticeList(conn ,pv ,searchValue ,searchType);
		
		JDBCTemplate.close(conn);
	
		return bvoList;
	}

	//board cnt
	public int selectCnt() throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.selectCnt(conn);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	//review select Cnt
	public int reviewSelectCnt() throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.reviewSelectCnt(conn);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	//공지사항 상세조회 + 조회수 처리까지
	public BoardVo noticeDetail(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		BoardVo vo = null;
		//                조회수 증가
		int result = dao.updateHit(conn , no);
		
		if(result ==1) {
			 vo = dao.noticeDetail(conn ,no);
		}else {
			throw new Exception();
		}
		JDBCTemplate.close(conn);
		return vo;
	}

	// 도연 - 공지사항 삭제
	public int noticeDelete(String no) throws Exception {
		
		try(Connection conn = JDBCTemplate.getConnection();){
			
			int result = dao.noticeDelete(conn , no);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else{
				JDBCTemplate.rollback(conn);
			}
			return result;
		}
	}

	// 도연 - 공지사항 수정하기
	public int noticeEdit(BoardVo vo) throws Exception {
		
		try(Connection conn = JDBCTemplate.getConnection();){
			
			int result = dao.noticeEdit(conn , vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else{
				JDBCTemplate.rollback(conn);
			}
			return result;
		}
	}//method

	//공지사항 댓글달기
	public int NoticeReplyWrite(CommentVo vo) throws Exception {
		
		try(Connection conn = JDBCTemplate.getConnection();){
			
			int result = dao.NoticeReplyWrite(conn , vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else{
				JDBCTemplate.rollback(conn);
			}
			return result;
		}
		
	}
	
	// 자유게시판 목록 조회
		public List<BoardVo> freeList(PageVo pv) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo>fvoList= dao.freeList(conn ,pv);
			
			JDBCTemplate.close(conn);
			
			return fvoList;
		}

		//공지사항 댓글보여주기
		public List<CommentVo> noticeReplyList(String boardNo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			List<CommentVo> replyList = dao.noticeReplyList(conn ,boardNo);
			
			JDBCTemplate.close(conn);
			
			return replyList;
			
		}

		//자유게시판 상세조회 + 조회수
		public BoardVo freeDetail(String no) throws Exception {

			try(Connection conn = JDBCTemplate.getConnection();){
				
				int result = dao.freeUpdateHit(conn , no);
				BoardVo fvo = null;
				if (result ==1) {
					fvo = dao.freeDetail(conn ,no);
				}else {
					throw new Exception();
				}
				return fvo;
			}
		}

		//자유게시판 삭제
		public int freeDelete(String no) throws Exception {
			
			Connection conn =JDBCTemplate.getConnection();
			
			int result = dao.freeDelete(conn ,no);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
			
		}

		//자유게시판 수정
		public int freeEdit(BoardVo vo) throws Exception {
			
			Connection conn =JDBCTemplate.getConnection();
			
			int result = dao.freeEdit(conn,vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			
			return result;
		}

		//자유게시판 작성하기
		public int freeWrite(BoardVo bvo) throws Exception {

			Connection conn =JDBCTemplate.getConnection();
			
			int result = dao.freeWrite(conn,bvo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			
			return result;
		}

		//자유게시판 댓글달기
		public int freeBoardRplyWrite(CommentVo cvo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.freeBoardRplyWrite(conn ,cvo);
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			return result;
		}

		//자유게시판 댓글 보여주기이이
		public List<CommentVo> freeReplyList(String boardNo) throws Exception {
		
			Connection conn = JDBCTemplate.getConnection();
			
			List<CommentVo> relpyList = dao.freeReplyList(conn ,boardNo);
			
			JDBCTemplate.close(conn);
			
			return relpyList;
		}

		//관리자 신분(멤버 카테고리 번호 1번) 으로 모든 판매요청 리스트 조회
		public List<BoardVo> sellRequestList(PageVo pv) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo> voList = dao.sellRequestList(conn ,pv);
			
			JDBCTemplate.close(conn);
			
			return voList;
		
		}
		
		//판매자 자신이 쓴 판매요청 리스트 조회
		public List<BoardVo> sellRequestList(PageVo pv, String memberNo) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo> voList = dao.sellRequestList(conn ,pv ,memberNo);
			
			JDBCTemplate.close(conn);
			
			return voList;
			
		}
		
		
		//판매 요청글 쓰기 멤버카테고리 3번(판매자)       
		public int sellRequestWrite(BoardVo vo, BoardImgVo biVo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.sellRequestWrite(conn, vo,biVo);
			if(result == 2) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			return result;
		}

		public int getBoardListCnt(String searchType, String searchValue) throws Exception {

			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			int cnt = dao.getBoardListCnt(conn , searchType , searchValue);
			
			//close
			JDBCTemplate.close(conn);
			
			return cnt;
		}

		//차량 리뷰 글쓰기
		public int carReviewWrite(BoardVo vo) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.carReviewWrite(conn ,vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
			
		}

		//차량 리뷰 리스트 보여주기
		public List<BoardVo> carReviewList(PageVo pv) throws Exception {
		
			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo> rvoList = dao.carReviewList(conn,pv);
			
			JDBCTemplate.close(conn);
		
			return rvoList;
			
		}

		//차량 리뷰 상세조회
		public BoardVo carReviewDetail(String no) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.carReviewupdateHit(conn , no);
			
			BoardVo vo = null; 
			if(result ==1) {
				vo = dao.carReviewDetail(conn ,no);
			}else {
				throw new Exception();
			}
			JDBCTemplate.close(conn);
			
			return vo;
		}

		//검색으로 자유게시판 목록 조회
		public List<BoardVo> freeList(PageVo pv, String searchValue, String searchType) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo>bvoList = dao.freeList(conn ,pv ,searchValue ,searchType);
			
			JDBCTemplate.close(conn);
		
			return bvoList;
			
		}

		//
		public int getFreeBoardListCnt(String searchType, String searchValue) throws Exception {
			
			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			int cnt = dao.getFreeBoardListCnt(conn , searchType , searchValue);
			
			//close
			JDBCTemplate.close(conn);
			
			return cnt;
		}

		// 숙소 리뷰 이벤트
		public int roomReviewWrite(BoardVo vo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.roomReviewWrite(conn ,vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
		}

		//숙소리뷰 게시글 조회
		public List<BoardVo> roomReviewList(PageVo pv) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo> rvoList = dao.roomReviewList(conn,pv);
			
			JDBCTemplate.close(conn);
		
			return rvoList;
			
		}

		//공지사항 신고 하기
		public int noticeBoardReport(ReportVo vo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.noticeBoardReport(conn ,vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			return result;
		}

		//자유게시판 신고하기
		public int freeBoardReport(ReportVo vo) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.freeBoardReport(conn ,vo);
			
			if(result ==1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			return result;
		}

		//검색으로 리뷰게시판 조회
		public List<BoardVo> carReviewList(PageVo pv, String searchValue, String searchType) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			List<BoardVo>bvoList = dao.carReviewList(conn ,pv ,searchValue ,searchType);
			
			JDBCTemplate.close(conn);
		
			return bvoList;
		}

		public int sellSelectCnt() throws Exception {

			//conn
			Connection conn = JDBCTemplate.getConnection();
			
			int cnt = dao.sellSelectCnt(conn);
			
			JDBCTemplate.close(conn);
			
			return cnt;
		
		}

		public  String getBoardImgNo() throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			String sql = "SELECT F01 FROM DUAL";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
//			System.out.println("ffffff");
//			System.out.println(rs);
			String imgNo = "";
			if(rs.next()) {
				 imgNo = rs.getString("F01");
			}
			
			
			JDBCTemplate.close(pstmt);
			
			return imgNo;
		}

		

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class

