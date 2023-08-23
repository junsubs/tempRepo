package com.kh.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {

		//sql
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND STATUS ='O'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String memberCategoryNo = rs.getString("MEMBER_CATEGORY_NO");
			String memberGradeNo = rs.getString("MEMBER_GRADE_NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
			String withdrawalYn = rs.getString("WITHDRAWAL_YN");
			String updateDate = rs.getString("UPDATE_DATE");
			String email = rs.getString("EMAIL");
			String address = rs.getString("ADDRESS");
			String status = rs.getString("STATUS");
			
			loginMember = new MemberVo();
			
			loginMember.setNo(no);
			loginMember.setMemberCategoryNo(memberCategoryNo);
			loginMember.setMemberGradeNo(memberGradeNo);
			loginMember.setId(id);
			loginMember.setPwd(pwd);
			loginMember.setNick(nick);
			loginMember.setEnrollDate(enrollDate);
			loginMember.setWithdrawalYn(withdrawalYn);
			loginMember.setUpdateDate(updateDate);
			loginMember.setEmail(email);
			loginMember.setAddress(address);
			loginMember.setStatus(status);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return loginMember;
	}

	
	public int join(Connection conn, MemberVo vo) throws Exception {
		
		//sql 
		String sql = "INSERT INTO MEMBER (NO, MEMBER_CATEGORY_NO, MEMBER_GRADE_NO, ID, PWD,NICK, EMAIL, ADDRESS) VALUES ( SEQ_MEMBER_NO.NEXTVAL , ?, 1, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberCategoryNo());
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		pstmt.setString(4, vo.getNick());
		pstmt.setString(5, vo.getEmail());
		pstmt.setString(6, vo.getAddress());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	//아이디 찾기
	public MemberVo findId(Connection conn, MemberVo vo) throws Exception {
		
		//sql
		String sql = "SELECT ID FROM MEMBER WHERE NICK =? AND PWD = ? AND EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getEmail());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember = null;
		if(rs.next()) {
			String dbId = rs.getString("ID");
			
			loginMember = new MemberVo();
			loginMember.setId(dbId);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return loginMember;
		}

	//비밀번호 찾기
	public MemberVo findPwd(Connection conn, MemberVo vo) throws Exception {

		//sql
		String sql = "SELECT PWD FROM MEMBER WHERE ID =? AND EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getEmail());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember = null;
		if(rs.next()) {
			String dbId = rs.getString("PWD");
			
			loginMember = new MemberVo();
			loginMember.setPwd(dbId);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return loginMember;
		}

	//아이디 중복확인
	public boolean isDuplicateId(Connection conn, String memberId) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		}
		return false;
	}

	//닉네임 중복확인
	public boolean isDuplicateNick(Connection conn, String memberNick) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM MEMBER WHERE ID = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNick);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int count = rs.getInt(1);
			return count > 0;
		}
		return false;
	}
	

	// 회원상세조회
	public MemberVo selectMemberOneByNo(Connection conn, String mno) throws Exception {

		//sql
		// 아이디, 닉네임, 주소, 이메일, 가입일, 회원등급
		String sql = "SELECT M.NO, M.MEMBER_GRADE_NO, M.ID, M.NICK, M.ADDRESS, M.EMAIL, TO_CHAR(M.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, MG.NAME FROM MEMBER M JOIN MEMBER_GRADE MG ON M.MEMBER_GRADE_NO = MG.NO WHERE M.NO=? AND M.STATUS='O'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo vo = null;
		if(rs.next()) {
			
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("EMAIL");
			String enrollDate = rs.getString("ENROLL_DATE");
			String memberGradeNo = rs.getString("MEMBER_GRADE_NO");
			String memberGradeName = rs.getString("NAME");
			
			vo = new MemberVo();
			vo.setNo(mno);
			vo.setId(id);
			vo.setNick(nick);
			vo.setAddress(address);
			vo.setEmail(email);
			vo.setEnrollDate(enrollDate);
			vo.setMemberGradeNo(memberGradeNo);
			vo.setMemberGradeName(memberGradeName);
	}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
		
	}

	// 강분-회원정보 수정
	public int edit(Connection conn, MemberVo vo) throws Exception {
		
		// 동적 쿼리
		String sql = "UPDATE MEMBER SET NICK = ?, ADDRESS = ?, EMAIL = ?";
		if(vo.getPwd() != null && vo.getPwd().length() > 0) {
			sql += " , PWD = ?";
		}
		sql += ", UPDATE_DATE = SYSDATE WHERE NO = ? AND STATUS = 'O'";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getAddress());
		pstmt.setString(3, vo.getEmail());
		if(vo.getPwd() != null && vo.getPwd().length() > 0) {
			pstmt.setString(4, vo.getPwd());
			pstmt.setString(5, vo.getNo());
		}else {
			pstmt.setString(4, vo.getNo());
		}
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	// 강분-회원 수정 select 문
	public MemberVo selectOneByNoForEdit(Connection conn, String no) throws Exception {

		String sql = "SELECT * FROM MEMBER M JOIN MEMBER_GRADE MG ON (M.MEMBER_GRADE_NO = MG.NO) WHERE M.NO = ? AND M.STATUS = 'O'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo vo = null;
		if(rs.next()) {
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			String address = rs.getString("ADDRESS");
			String email = rs.getString("EMAIL");
			String memberGradeName = rs.getString("NAME");
			String memberGradeImg = rs.getString("IMG");
			
			vo = new MemberVo();
			vo.setNo(no);
			vo.setId(id);
			vo.setNick(nick);
			vo.setAddress(address);
			vo.setEmail(email);
			vo.setMemberGradeName(memberGradeName);
			vo.setMemberGradeImg(memberGradeImg);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
		
		
	}
	
	// 강분-회원 탈퇴
		public int quit(Connection conn, String no) throws Exception {
			
			String sql = "UPDATE MEMBER SET STATUS='X' WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			int result = pstmt.executeUpdate();
			
			JDBCTemplate.close(pstmt);
			
			return result;
			
		}
	
}