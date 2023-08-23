package com.kh.app.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;

public class MemberService {
	
	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao(); 
	}

	

	// 회원 가입
	public int join(MemberVo vo) throws Exception {
		
		//comm
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.join(conn ,vo);
		
		//tx rs
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		return result;
	}

	//로그인
	public MemberVo login(MemberVo vo) throws Exception {
		
		//comm
		Connection conn = JDBCTemplate.getConnection();
		
		//sql 
		MemberVo loginMember = dao.login(conn ,vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
		
	}
	
	//아이디찾기
	public MemberVo findId(MemberVo vo) throws Exception {
		
		//comm
		Connection conn = JDBCTemplate.getConnection();
	
		MemberVo loginMember = dao.findId(conn ,vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	}

	//비밀번호 찾기
	public MemberVo findPwd(MemberVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo loginMember = dao.findPwd(conn ,vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	
	}
	
	//아이디 중복 처리
	public boolean isDuplicateId(String memberId) throws Exception {

		try(Connection conn = JDBCTemplate.getConnection();){
			//sql
			boolean result = dao.isDuplicateId(conn ,memberId);
			
			return result;
		}
	  
		
	}//method

	//닉네임 중복 처리
	public boolean isDuplicateNick(String memberNick) throws Exception {

		try(Connection conn = JDBCTemplate.getConnection();){
			//sql
			boolean result = dao.isDuplicateNick(conn ,memberNick);
			
			return result;
		}
		
	}

	// 강분-회원 상세조회(번호로 select)
	public MemberVo selectMemberOneByNo(String mno) throws Exception {

		MemberVo vo = null;
		// conn
		Connection conn = JDBCTemplate.getConnection(); 

		// select
		vo = dao.selectMemberOneByNo(conn,mno);
		
		JDBCTemplate.close(conn);
				
		return vo;
	}


	// 강분-회원정보 수정
	public MemberVo edit(MemberVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo updatedMember = null;
		try {
			// SQL
			int result = dao.edit(conn,vo);
			
			// rs || ts
			if(result == 1) {
				updatedMember = dao.selectOneByNoForEdit(conn, vo.getNo());
				if(updatedMember == null) {
					throw new Exception("updatedMember null..");
				}
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		
		}finally {
			// close
			JDBCTemplate.close(conn);
		}
		
		return updatedMember;
		
	}

	// 강분-회원 탈퇴
	public int quit(String no) throws Exception {

		// conn 
		Connection conn = JDBCTemplate.getConnection();
		// SQL
		int result = dao.quit(conn,no);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
		
	}


	// 회원 누적 출석 수 
	public MemberVo cntAttendByNo(String mno) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		String sql = "SELECT COUNT(*) AS TOTAL_ATTEND FROM ATTEND A JOIN MEMBER M ON (A.NO = M.NO) WHERE M.NO = ? AND M.STATUS='O' ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo vo = null;
		if(rs.next()) {
			String total_attend = rs.getString("TOTAL_ATTEND");
			
			vo = new MemberVo();
			vo.setTotalAttend(total_attend);
		}
		
		JDBCTemplate.close(conn);
		return vo;
	}



	//로그인시 아이디 체크
	public boolean loginIdCheck(String idInputValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idInputValue);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		}
		return false;
		
		
	}
	
}
