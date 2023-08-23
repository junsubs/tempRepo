package com.kh.app.mypage.service;

import java.sql.Connection;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.mypage.dao.FavoriteDao;
import com.kh.app.mypage.dao.OrderListDao;
import com.kh.app.mypage.vo.FavoriteVo;
import com.kh.app.mypage.vo.OrderListVo;

public class FavoriteService {

	FavoriteDao dao = new FavoriteDao();
	// 관심상품 조회 개수(회원별)
	public int getFavListCntByNo(String mno) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getFavListCntByNo(conn, mno);
		
		// tx || rs
		
		// close
		JDBCTemplate.close(conn);
		
		return cnt;
		
	}
	

	// 차량 관심상품 조회
	public List<FavoriteVo> getCarFavListByNo(PageVo pv, String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<FavoriteVo> cvoList = dao.getCarFavListByNo(conn,pv,mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return cvoList;
	}


	// 숙소 관심상품 조회
	public List<FavoriteVo> getAccomFavListByNo(PageVo pv, String mno) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<FavoriteVo> avoList = dao.getAccomFavListByNo(conn,pv,mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return avoList;
	
	}


	// 기념품 관심상품 조회 
	public List<FavoriteVo> getSouvenirFavListByNo(PageVo pv, String mno) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<FavoriteVo> svoList = dao.getSouvenirFavListByNo(conn,pv,mno);
		
		// close
		JDBCTemplate.close(conn);
		
		return svoList;
	}


	// 관심상품 삭제
	public int delete(FavoriteVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.delete(conn,vo);
		
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
