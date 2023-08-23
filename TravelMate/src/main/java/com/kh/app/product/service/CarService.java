package com.kh.app.product.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.member.vo.MemberVo;
import com.kh.app.product.vo.CarVo;
import com.kh.app.product.vo.RoomVo;
import com.kh.app.product.dao.CarDao;
import com.kh.app.product.dao.RoomDao;

public class CarService {
	
	private final CarDao dao = new CarDao();
	
	public int getCarListCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.getCarListCnt(conn , searchType , searchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return cnt;
	}


	public List<CarVo> getCarList(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<CarVo> voList = dao.getCarList(conn , pv);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	

	public List<CarVo> getCarList(PageVo pv, String searchType, String searchValue, String local) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		List<CarVo> voList = dao.getCarList(conn , pv , searchType, searchValue, local);
		
		//close
		JDBCTemplate.close(conn);
		
		return voList;
	}


	


	public int order(String carKindKind, MemberVo loginMember, CarVo cvo) throws Exception {
		
		CarDao dao = new CarDao();
		
		Connection conn = JDBCTemplate.getConnection();
	
		int result = dao.order(conn, carKindKind, loginMember , cvo);
		
		JDBCTemplate.close(conn);
		
		return result;
	
	}


	public int pay(CarVo cvo) throws Exception {
		//가격 cvo에 넣기
		Connection conn = JDBCTemplate.getConnection();
		
		String price = dao.getPrice(cvo, conn);
		cvo.setPrice(price);
		
		JDBCTemplate.close(conn);
		
		//결제 진행하기
		conn = JDBCTemplate.getConnection();
		
		int result = dao.pay(cvo, conn);
				
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int updateReservation(CarVo cvo, MemberVo loginMember) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result2 = dao.updateReservation(cvo, loginMember, conn);
		
		JDBCTemplate.close(conn);
		
		return result2;
		
	}


	public CarVo selectCarOneByName(String name) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		CarVo vo = dao.selectCarOneByName(conn , name);
		return vo;
	}


	public int carOrder(CarVo vo, MemberVo loginMember) throws Exception {
	
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


	public CarVo carSelectOrder(MemberVo loginMember) throws Exception {

		CarVo vo = null;
		//conn
		try (Connection conn = JDBCTemplate.getConnection();){
			vo = dao.carSelectOrder(conn , loginMember);
		}
		return vo;
	
	}


	public int carFavorite(String no, String name, MemberVo loginMember) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.carFavorite(conn, no, name, loginMember);
		
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
			
		JDBCTemplate.close(conn);
		
		
		return result;
	}


	public int carPayment(String reservationno) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.carPayment(reservationno, conn);
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		
		
		return result;
	}
	
	
	
	

}
