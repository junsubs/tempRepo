package com.kh.app.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.vo.FAQVo;
import com.kh.app.cs.vo.InqueryVo;

public class FAQDao {

	public int getFAQListCnt(Connection conn) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM FAQ F WHERE DELETE_YN='N'";
		
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

	public List<FAQVo> getFAQList(Connection conn, PageVo pv) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM , T.* FROM ( SELECT F.NO , F.TITLE , F.ANSWER , F.MEMBER_NO , F.DELETE_YN FROM FAQ F ORDER BY NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pv.getBeginRow());
		pstmt.setInt(2, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<FAQVo> voList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String answer = rs.getString("ANSWER");
			String memberNo = rs.getString("MEMBER_NO");
			String deleteYn = rs.getString("DELETE_YN");
	
			FAQVo vo = new FAQVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setAnswer(answer);
			vo.setMemberNo(memberNo);
			vo.setDeleteYn(deleteYn);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
				
	
	}

	// 자주 묻는 질문 작성
	public int write(Connection conn, FAQVo vo) throws Exception {

		// sql
		String sql = "INSERT INTO FAQ(NO, MEMBER_NO, TITLE, ANSWER) VALUES(SEQ_FAQ_NO.NEXTVAL, ? , ?,  ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,"1");
		pstmt.setString(2, vo.getTitle());
		pstmt.setString(3, vo.getAnswer());
		int result =  pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	
	}

}
