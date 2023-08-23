package com.kh.app.cs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.common.page.PageVo;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryVo;
import com.kh.app.member.vo.MemberVo;

@WebServlet("/cs/inqueryList")
public class InqueryListController extends HttpServlet{

	private final InqueryService is = new InqueryService();
	
	// 문의내역 목록
	// SELECT Q.NO, Q.MEMBER_NO, Q.TITLE, Q.CONTENT, TO_CHAR(Q.ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, M.NICK FROM QNA Q JOIN MEMBER M ON (Q.MEMBER_NO = M.NO) WHERE M.NO='2' AND DELETE_YN='N'
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MemberVo loginMember = (MemberVo)req.getSession().getAttribute("loginMember");
			
			if(loginMember == null) {
				req.getSession().setAttribute("alertMsg", "로그인을 먼저 해주세요");
				resp.sendRedirect("/TravelMate/login");
			}
					
			String searchType = req.getParameter("searchType");
			String searchValue = req.getParameter("searchValue");
			String no = req.getParameter("no");
			String mno = loginMember.getNo();
			String mId = loginMember.getId();
			InqueryVo vo = new InqueryVo();
			vo.setNo(no);
			
			// 데이터 준비
			int cnt = is.getInqueryListCnt(searchType, searchValue);
			String page_ = req.getParameter("page");
			if(page_ == null) {
				page_ = "1";
			}
			int page = Integer.parseInt(page_);
			PageVo pv = new PageVo(cnt, page, 5, 10);
			int replyCnt = is.getReplyCnt(vo);
			System.out.println(replyCnt);
			
			// 서비스
			// 전체 목록과 검색 결과 목록과 관리자 로그인 시, 전체 회원의 내역 목록 나눠주기
			
			List<InqueryVo> voList = null;
			// 관리자가 아니고 검색창이 비어있을 경우, 해당 회원의 글 목록 다 보여줘야함
			if (!mId.equals("ADMIN") && (searchType == null || searchValue.equals(""))) {
			    voList = is.getInqueryList(pv, mno);
			    
			// 관리자이고 검색 결과 창 비어있을 경우, 모든 회원의 목록 다 보여줌    
			} else if (mId.equals("ADMIN") && (searchType == null || searchValue.equals(""))) {
				voList = is.getInqueryListAll(pv);
				
			// 관리자이고 검색 결과 입력했을 때, 모든 회원의 검색 결과 화면 보여줌	
			} else if (mId.equals("ADMIN") && (searchType != null || !searchValue.equals(""))) {
				voList = is.getInqueryListAll(pv, searchType, searchValue);
				
			// 관리자 아니고 검색 결과 입력했을 때, 해당 회원의 검색 결과 화면 보여줌	
			} else if(!mId.equals("ADMIN") && (searchType != null || !searchValue.equals(""))){
				voList = is.getInqueryList(pv, searchType, searchValue, mno);
			}
			
			Map<String, String> map = new HashMap<>();
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);
			
			// 화면
			req.setAttribute("replyCnt", replyCnt);
			req.setAttribute("searchVo", map);
			req.setAttribute("pv", pv);
			req.setAttribute("voList", voList);
			req.getRequestDispatcher("/WEB-INF/views/CScenter/inqueryList.jsp").forward(req, resp);
			
			} catch (Exception e) {
				System.out.println("[ERROR] inquery list controller err");
				e.printStackTrace();
				
				req.setAttribute("errorMsg", "문의내역 목록 조회 실패..");
				req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
			}
		}
		
	
	}
	

