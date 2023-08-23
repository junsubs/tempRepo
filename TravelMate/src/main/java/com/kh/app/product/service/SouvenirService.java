package com.kh.app.product.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.dao.SouvenirDao;
import com.kh.app.product.vo.SouvenirVo;

public class SouvenirService {
	
	private final SouvenirDao dao = new SouvenirDao();
	
	public int getSouvenirListCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getSouvenirListCnt(conn , searchType , searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}


	public List<SouvenirVo> getSouvenirList(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<SouvenirVo> voList = dao.getSouvenirList(conn , pv);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}


	public List<SouvenirVo> getSouvenirList(PageVo pv, String searchType, String searchValue, String local) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<SouvenirVo> voList = dao.getSouvenirList(conn , pv , searchType, searchValue, local);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}


	//기념품 상세페이지
	public SouvenirVo selectSouvenirOneByNo(String name) throws Exception {
		SouvenirVo vo = null;
		//conn
		try (Connection conn = JDBCTemplate.getConnection();){
			vo = dao.selectSouvenirOneByNo(conn , name);
		}
		return vo;
	}


	public int order(SouvenirVo vo, MemberVo loginMember) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.order(vo, conn, loginMember);
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	public SouvenirVo selectOrder(MemberVo loginMember) throws Exception {
		SouvenirVo vo = null;
		//conn
		try (Connection conn = JDBCTemplate.getConnection();){
			vo = dao.selectOrder(conn , loginMember);
		}
		return vo;
	}


	public int souvenirFavorite(String no, String name, MemberVo loginMember) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.souvenirFavorite(conn, no, name, loginMember);
		
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
			
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	public int souvenirPayment(String reservationno) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.souvenirPayment(reservationno, conn);
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}




}
