package com.kh.app.mypage.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.mypage.dao.AttendanceDao;
import com.kh.app.mypage.vo.AttendanceVo;

public class AttendanceService {

	public int insertAttendance(String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		AttendanceDao dao = new AttendanceDao();
		int result = dao.insertAttendance(conn, mno);
		
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

	// 출석 정보 select
	public List<AttendanceVo> selectAttendance(String mno) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		AttendanceDao dao = new AttendanceDao();
		List<AttendanceVo> list = new ArrayList<>();
		list = dao.selectAttendance(conn, mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

}
