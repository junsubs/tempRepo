package com.kh.app.home.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;

public class HomeDao {

	public List<BoardVo> getBoardTopHit(Connection conn) throws Exception {

		//SQL
		String sql = "SELECT TITLE, CONTENT,ENROLL_DATE, HIT FROM ( SELECT TITLE,CONTENT,HIT ,TO_CHAR(ENROLL_DATE,'YYYY-MM-DD') AS ENROLL_DATE FROM BOARD WHERE BOARD_CATEGORY_NO=5  AND DELETE_YN='N'  ORDER BY HIT DESC ) WHERE ROWNUM <= 5";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//tx || rs
		List<BoardVo> vo = new ArrayList<>();
		if(rs.next()) {
			
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String enroll_date = rs.getString("ENROLL_DATE");
			String hit = rs.getString("HIT");
			
			BoardVo bvo = new BoardVo();
			
			bvo.setTitle(title);
			bvo.setContent(content);
			bvo.setEnrollDate(enroll_date);
			bvo.setHit(hit);
			
			vo.add(bvo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);		
		
		return vo;
	
		
	}

}
