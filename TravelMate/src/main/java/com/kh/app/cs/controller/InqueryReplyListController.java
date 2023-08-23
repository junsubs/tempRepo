package com.kh.app.cs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.cs.service.InqueryService;
import com.kh.app.cs.vo.InqueryReplyVo;

@WebServlet("/cs/reply/list")
public class InqueryReplyListController extends HttpServlet{

	// 댓글 
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			try {
			
				// 데꺼
				String inqueryNo = req.getParameter("InqueryNo");

				// 데뭉
				
				// 서비스
				InqueryService ns = new InqueryService();
				List<InqueryReplyVo> list = ns.selectReplyList(inqueryNo);
				
				// 자바객체를 JSON 형태의 문자열로 변환
				Gson gson = new Gson();
				String jsonStr = gson.toJson(list);
				
				// 화면 == 문자열 내보내기
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(jsonStr);
//				out.write("{\"k01\" : \"v01\"}");
				
			} catch (Exception e) {
				System.out.println("[ERROR] notice reply err");
				e.printStackTrace();
			}
			
			
		}
	
}
