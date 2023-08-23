package com.kh.app.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.mypage.vo.AttendanceVo;

public class AttendanceDao {

	public int insertAttendance(Connection conn, String mno) throws Exception {

		// sql
		String sql = "INSERT INTO ATTEND(\"DATE\", NO) VALUES(SYSDATE, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public List<AttendanceVo> selectAttendance(Connection conn, String mno) throws Exception {

		String sql = "SELECT TO_CHAR(\"DATE\" , 'yyyy-mm-dd') AS ATTEND_DATE FROM ATTEND WHERE NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		ResultSet rs = pstmt.executeQuery();
		
		List<AttendanceVo> list = new ArrayList<>();
		while(rs.next()) {
			String date = rs.getString("ATTEND_DATE");
			
			AttendanceVo vo = new AttendanceVo();
			vo.setDate(date);
			vo.setNo(mno);
			
			list.add(vo);
		}
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return list;
	}

}
