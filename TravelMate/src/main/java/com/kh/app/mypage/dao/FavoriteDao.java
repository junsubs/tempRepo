package com.kh.app.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;
import com.kh.app.mypage.vo.FavoriteVo;

public class FavoriteDao {

	// 관심상품 개수 조회
	public int getFavListCntByNo(Connection conn, String mno) throws Exception {

		// SQL
		String sql = "SELECT COUNT(*) FROM ( SELECT F.NO ,F.MEMBER_NO ,F.RENTCAR_NO ,F.ACCOMODATION_NO ,F.SOUVENIR_NO ,F.DEL_YN FROM FAVORITES F JOIN MEMBER M ON (F.MEMBER_NO = M.NO)) WHERE DEL_YN = 'N' AND MEMBER_NO=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
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

	// 차량 관심상품 조회
	public List<FavoriteVo> getCarFavListByNo(Connection conn, PageVo pv, String mno) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT F.NO AS NO, F.MEMBER_NO AS MEMBER_NO,F.RENTCAR_NO AS RENTCAR_NO, CK.KIND AS KIND, CI.TITLE AS IMG FROM FAVORITES F JOIN RENTCAR R ON (F.RENTCAR_NO = R.NO) JOIN CAR_KIND CK ON (CK.NO = R.NO) JOIN CAR_IMG CI ON (CI.NO = R.NO) WHERE F.DEL_YN='N' AND F.MEMBER_NO=? ORDER BY F.NO DESC) T) WHERE RNUM BETWEEN ? AND ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<FavoriteVo> cvoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String cno = rs.getString("RENTCAR_NO");
			String img = rs.getString("IMG");
			String kind = rs.getString("KIND");
	
			FavoriteVo vo = new FavoriteVo();
			vo.setNo(no);
			vo.setMemberNo(mno);
			vo.setCarNo(cno);
			vo.setCarKind(kind);
			vo.setCarImg(img);
			
			cvoList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cvoList;
	}

	// 숙소 관심상품 조회
	public List<FavoriteVo> getAccomFavListByNo(Connection conn, PageVo pv, String mno) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT F.NO AS NO, F.MEMBER_NO AS MEMBER_NO, F.ACCOMODATION_NO AS ACCOMODATION_NO ,A.NAME AS NAME , AI.TITLE AS IMG FROM FAVORITES F JOIN ACCOMODATION A ON (F.ACCOMODATION_NO = A.NO) JOIN ACCOMODATION_IMG AI ON (AI.ACCOMODATION_NO = A.NO) WHERE F.DEL_YN='N' AND F.MEMBER_NO=? ORDER BY F.NO DESC) T) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<FavoriteVo> avoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String ano = rs.getString("ACCOMODATION_NO");
			String name = rs.getString("NAME");
			String img = rs.getString("IMG");
			
	
			FavoriteVo vo = new FavoriteVo();
			vo.setNo(no);
			vo.setMemberNo(mno);
			vo.setAccomodationNo(ano);
			vo.setAccomodationImg(img);
			vo.setAccomodationName(name);
			
			avoList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return avoList;
	}

	// 기념품 관심상품 조회
	public List<FavoriteVo> getSouvenirFavListByNo(Connection conn, PageVo pv, String mno) throws Exception {

		// sql
		String sql = "SELECT * FROM ( SELECT ROWNUM RNUM, T.* FROM ( SELECT F.NO AS NO, F.MEMBER_NO AS MEMBER_NO, F.SOUVENIR_NO AS SOUVENIR_NO, S.NAME AS NAME ,S.PRICE AS PRICE , SI.TITLE AS IMG FROM FAVORITES F JOIN SOUVENIR S ON (F.SOUVENIR_NO = S.NO) JOIN SOUVENIR_IMG SI ON (SI.SOUVENIR_NO = S.NO) WHERE F.DEL_YN='N' AND F.MEMBER_NO=? ORDER BY F.NO DESC) T) WHERE RNUM BETWEEN ? AND ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mno);
		pstmt.setInt(2, pv.getBeginRow());
		pstmt.setInt(3, pv.getLastRow());
		ResultSet rs = pstmt.executeQuery();
		
		// tx || rs
		List<FavoriteVo> svoList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String sno = rs.getString("SOUVENIR_NO");
			String name = rs.getString("NAME");
			String price = rs.getString("PRICE");
			String img = rs.getString("IMG");
	
			FavoriteVo vo = new FavoriteVo();
			vo.setNo(no);
			vo.setSouvenirNo(sno);
			vo.setSouvenirName(name);
			vo.setSouvenirPrice(price);
			vo.setSouvenirImg(img);
			
			svoList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return svoList;
	
	}

	public int delete(Connection conn, FavoriteVo vo) throws Exception {

		// sql 
		String sql = "UPDATE FAVORITES SET DEL_YN ='Y' WHERE NO=? AND MEMBER_NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNo());
		pstmt.setString(2, vo.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

}
