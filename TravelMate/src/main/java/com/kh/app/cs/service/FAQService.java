package com.kh.app.cs.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.dao.FAQDao;
import com.kh.app.cs.vo.FAQVo;
import com.kh.app.cs.vo.InqueryVo;

public class FAQService {

	private final FAQDao dao = new FAQDao();
	// faq 개수 세기
	public int getFAQListCnt() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getFAQListCnt(conn);
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}
	public List<FAQVo> getFAQList(PageVo pv) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<FAQVo> voList = dao.getFAQList(conn,pv);
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	// faq 작성
	public int write(FAQVo vo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.write(conn, vo);
		
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

}
