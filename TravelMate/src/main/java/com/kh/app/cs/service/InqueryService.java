package com.kh.app.cs.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.dao.InqueryDao;
import com.kh.app.cs.vo.InqueryReplyVo;
import com.kh.app.cs.vo.InqueryVo;

public class InqueryService {
	
	private final InqueryDao dao = new InqueryDao();

	// 문의글 작성
	public int write(InqueryVo ivo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.write(conn, ivo);
		
		// tx || rs
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}


	// 문의글 상세조회
	public InqueryVo selectInqueryOneByNo(String no) throws Exception {

		InqueryVo vo = null;
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		vo = dao.selectInqueryOneByNo(conn,no);

		return vo;
	}

	// 글 개수
	public int getInqueryListCnt(String searchType, String searchValue) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getInqueryListCnt(conn, searchType, searchValue);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}

	// 검색해서 문의글 조회
	public List<InqueryVo> getInqueryList(PageVo pv, String searchType, String searchValue, String mno) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<InqueryVo> voList = dao.getInqueryList(conn,pv, searchType, searchValue, mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	// 전체 문의글 조회
	public List<InqueryVo> getInqueryList(PageVo pv, String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<InqueryVo> voList = dao.getInqueryList(conn,pv, mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
		
	
	}


	// 1대1 문의 답변 작성
	public int replyWrite(InqueryReplyVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.replyWrite(conn,vo);
		
		// tx || rs
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 1:1 문의 답변 조회
	public List<InqueryReplyVo> selectReplyList(String inqueryNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<InqueryReplyVo> list = dao.selectReplyList(conn, inqueryNo);
		
		// close
		JDBCTemplate.close(conn);
		
		return list;
	}

	// 관리자 로그인 시 전체 회원의 문의 내역 조회(검색 ver)
	public List<InqueryVo> getInqueryListAll(PageVo pv, String searchType, String searchValue) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<InqueryVo> voList = dao.getInqueryListAll(conn,pv, searchType, searchValue);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}


	// 관리자 로그인 시 전체 회원의 문의 내역 조회
	public List<InqueryVo> getInqueryListAll(PageVo pv) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<InqueryVo> voList = dao.getInqueryListAll(conn,pv);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
		
	}


	// 문의글 번호로 답변 개수가 카운팅
	public int getReplyCnt(InqueryVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getReplyCnt(conn,vo);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
		
}
