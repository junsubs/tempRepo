package com.kh.app.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

@WebServlet(urlPatterns = "/join")
public class JoinController extends HttpServlet {
    
    private MemberService ms;
    
    @Override
    public void init() throws ServletException {
        super.init();
        ms = new MemberService();
    }
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
    }
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        try {
        	
        	//데이터꺼내기
            String memberId = req.getParameter("memberId");
            String memberPwd = req.getParameter("memberPwd");
            String memberEmail = req.getParameter("memberEmail");
            String memberNick = req.getParameter("memberNick");
            String memberAddress = req.getParameter("memberAddress");
            String memberCategory = req.getParameter("memberCategory");

            //           아이디 중복 체크               닉네임 중복체크
            if (ms.isDuplicateId(memberId) || ms.isDuplicateNick(memberNick)) { //false 를 받음    
            	//중복된아이디인경우
            	resp.getWriter().write("duplicate"); // <- client측 ajax가 받아줌
                return;
            }
			
            //뭉치기VO
            MemberVo vo = new MemberVo();
            vo.setId(memberId);
            vo.setPwd(memberPwd);
            vo.setEmail(memberEmail);
            vo.setNick(memberNick);
            vo.setAddress(memberAddress);
            vo.setMemberCategoryNo(memberCategory);
			
            int result = ms.join(vo);
			
            //monit
            if (result == 1) {
            	req.setAttribute("alertMsg", "성공 회원가입");
            	req.getRequestDispatcher("/WEB-INF/views/common/header3.jsp").forward(req, resp);
            } else {
            	throw new Exception();
            }
            
        } catch (Exception e) {
            System.out.println("[ERROR] join error...");
            e.printStackTrace();
			
            req.setAttribute("errorMsg", "회원가입 에러 ㅋㅋ");
            req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
        }
    }
}
