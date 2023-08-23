package com.kh.app.home.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.home.dao.HomeDao;

public class HomeService {

	public List<BoardVo> getBoardTopHit() throws Exception {

		Connection conn = JDBCTemplate.getConnection();

		HomeDao dao = new HomeDao();
		List<BoardVo> vo = dao.getBoardTopHit(conn);
		
		JDBCTemplate.close(conn);
		return vo;
	}

}
