package com.kh.app.admin.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.app.admin.dao.AdminDao;
import com.kh.app.admin.vo.AccommodationInventoryVo;
import com.kh.app.admin.vo.AdBannerVo;
import com.kh.app.admin.vo.CarInventoryVo;
import com.kh.app.admin.vo.MemberDetailVo;
import com.kh.app.admin.vo.MemberSearchVo;
import com.kh.app.admin.vo.ReportListDetailVo;
import com.kh.app.admin.vo.ReportListVo;
import com.kh.app.admin.vo.ReportSearchDetailVo;
import com.kh.app.admin.vo.ReportSearchVo;
import com.kh.app.admin.vo.SellRequestDetailVo;
import com.kh.app.admin.vo.SellRequestVo;
import com.kh.app.admin.vo.SouvenirInventoryVo;
import com.kh.app.common.db.JDBCTemplate;
import com.kh.app.common.page.PageVo;

public class AdminService {

	private final AdminDao dao;
	
	public AdminService() {
		dao = new AdminDao();
	}
	
	//신고내역 페이징처리
	public int reportListCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.reportListCnt(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	//제재이력조회 페이징처리
	public int reportSearchCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.reportSearchCnt(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	//회원조회 페이징처리
	public int memberSearchCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.memberSearchCnt(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
	
		return cnt;
	}
	
	//차량재고조회 페이징처리
	public int carInventoryCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.carInventroy(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	//숙소재고조회 페이징처리
	public int accommodationInventoryCnt(String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.accommodationInventoryCnt(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}
	
	//기념품재고조회 페이징처리
	public int souvenirInventoryCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.souvenirInventory(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}


	//판매등록요청조회 페이징처리
	public int sellRequestCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.sellRequestCnt(conn,searchType, searchValue);
		
		JDBCTemplate.close(conn);
	
		return cnt;
	}
	
	//광고배너관리 페이징처리
	public int adBannerCnt(String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		int cnt = dao.adBannerCnt(conn, searchType, searchValue);
		
		JDBCTemplate.close(conn);
	
		return cnt;
	}
		
	//신고내역조회
	public List<ReportListVo> reportList(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<ReportListVo> reportVoList = dao.reportList(conn, pv);
		
		//close
		JDBCTemplate.close(conn);
		
		return reportVoList;
	}
	
	//신고내역 카테고리 검색 조회
	public List<ReportListVo> reportList(PageVo pv, String searchType, String SearchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<ReportListVo> reportVoList = dao.reportList(conn, pv, searchType, SearchValue);
		
		//close
		JDBCTemplate.close(conn);
		
		return reportVoList;
	}
	
	//제재이력조회
	public List<ReportSearchVo> reportSearch(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<ReportSearchVo> voList = dao.reportSearch(conn, pv);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//제재이력 카테고리 검색조회
	public List<ReportSearchVo> reportSearch(PageVo pv, String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<ReportSearchVo> voList = dao.reportSearch(conn, pv, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//회원조회
	public List<MemberSearchVo> memberSearch(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<MemberSearchVo> voList = dao.memberSearch(conn, pv);
				
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//회원 카테고리 검색조회
	public List<MemberSearchVo> memberSearch(PageVo pv, String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<MemberSearchVo> voList = dao.memberSearch(conn, pv, searchType, searchValue);
				
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//차량재고조회
	public List<CarInventoryVo> carInventory(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<CarInventoryVo> voList = dao.carInventory(conn, pv);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//차량재고조회 검색조회
	public List<CarInventoryVo> carInventory(PageVo pv, String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<CarInventoryVo> voList = dao.carInventory(conn, pv, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//숙소재고조회
	public List<AccommodationInventoryVo> accommodationInventory(PageVo pv) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<AccommodationInventoryVo> voList = dao.accommodationInventory(conn, pv);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//숙소재고조회 검색조회
	public List<AccommodationInventoryVo> accommodationInventory(PageVo pv, String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<AccommodationInventoryVo> voList =  dao.accommodationInventory(conn, pv, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//기념품재고조회
	public List<SouvenirInventoryVo> souvenirInventory(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<SouvenirInventoryVo> voList = dao.souvenirInventory(conn, pv);
	
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//기념품재고조회 검색조회
	public List<SouvenirInventoryVo> souvenirInventory(PageVo pv, String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<SouvenirInventoryVo> voList = dao.souvenirInventory(conn, pv, searchType, searchValue);
	
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//판매등록요청
	public List<SellRequestVo> sellRequest(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<SellRequestVo> voList = dao.sellRequest(conn, pv);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//판매등록요청 검색조회
	public List<SellRequestVo> sellRequest(PageVo pv, String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<SellRequestVo> voList = dao.sellRequest(conn, pv, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	//광고배너관리
	public List<AdBannerVo> adBanner(PageVo pv) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<AdBannerVo> voList = dao.adBanner(conn, pv);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	//광고배너관리 검색조회
	public List<AdBannerVo> adBanner(PageVo pv, String searchType, String searchValue) throws Exception {
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//sql
		List<AdBannerVo> voList = dao.adBanner(conn, pv, searchType, searchValue);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	//신고내역상세조회
	public ReportListDetailVo reportListDetail(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		ReportListDetailVo vo = dao.reportListDetail(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//제재이력조회 상세조회
	public ReportSearchDetailVo reportSearchDetail(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		ReportSearchDetailVo vo = dao.reportSearchDetail(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}
	
	//회원제재하기
	public int memberStopDate(Map<String, String> map) throws Exception {
		Connection conn = JDBCTemplate.getConnection();

		int result = dao.memberStopDate(conn, map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//회원제재하기(member테이블)
	public int memberStop(Map<String, String> map) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.memberStop(conn, map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//회원제재횟수 쿼리
	public int sanctionCount(Map<String, String> map) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.sanctionCount(conn, map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//회원제재취소
	public int reportSearchCancel(String memberNick) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.reportSearchCancel(conn, memberNick);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//회원상세조회
	public MemberDetailVo memberSearchDetail(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		MemberDetailVo vo = dao.memberSearchDetail(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}
	
	//차량재고조회 글작성
	public int carInventoryWrite(CarInventoryVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.carInventoryWrite(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//차량재고수정전 화면
	public CarInventoryVo carInventoryEdit(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		CarInventoryVo vo = dao.carInventoryEdit(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}
	
	//차량재고수정
	public int carCountEdit(String no, String count) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.carCountEdit(conn, no, count);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//숙소재고수정전 화면
	public AccommodationInventoryVo accommodationEdit(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		AccommodationInventoryVo vo = dao.accommodationEdit(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}
	
	//숙소재고조회 게시글작성
	public int accommodationInventoryWrite(AccommodationInventoryVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.accommodationInventoryWrite(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//숙소재고수정
	public int accommodationCountEdit(Map<String, String> map) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.accommodationCountEdit(conn, map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//기념품재고조회 글작성
	public int souvenirInventoryWrite(SouvenirInventoryVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.souvenirInventoryWrite(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//기념품재고조회 수정화면
	public SouvenirInventoryVo souvenirEdit(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		SouvenirInventoryVo vo = dao.souvenirEdit(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//기념품재고조회 재고수정
	public int souvenirCountEdit(Map<String, String> map) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.souvenirCountEdit(conn, map);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//판매등록요청 상세조회
	public SellRequestDetailVo sellRequestDetail(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		SellRequestDetailVo vo = dao.sellRequestDetail(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//판매등록요청 등록
	public int sellRequestEnroll(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.sellRequestEnroll(conn, no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//판매등록요청 거절
	public int sellRequestRefuse(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.sellRequestRefuse(conn, no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	//배너삭제
	public int bannerDelete(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.bannerDelete(conn, no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//배너 글작성
	public int AdbannerWrite(AdBannerVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.AdbannerWrite(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.close(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//배너 글작성 회원번호 받아오기
	public String bannerMemberNo(Connection conn, AdBannerVo vo) throws Exception {		
		String memberNo = dao.bannerMemberNo(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return memberNo;
	}

	//배너 게시글수정화면
	public AdBannerVo AdBannerEdit(String no) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		AdBannerVo vo = dao.AdBannerEdit(conn, no);
		
		JDBCTemplate.close(conn);
		
		return vo;
	}

	//배너 게시글수정
	public int adBannerContentEdit(AdBannerVo vo, String memberNo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.adBannerContentEdit(conn, vo, memberNo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	//배너 게시글 수정 회원번호받아오기
	public String bannerGetNo(String memberNick) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		String memberNo = dao.bannerGetNo(conn, memberNick);
		
		JDBCTemplate.close(conn);
		
		return memberNo;
	}

	//배너 기념품이름으로 번호받아오기
	public String souvenirGetNo(String souvenirName) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		String souvenirNo = dao.souvenirGetNo(conn, souvenirName);
		
		JDBCTemplate.close(conn);
		
		return souvenirNo;
	}
	
}
