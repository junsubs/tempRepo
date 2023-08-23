package com.kh.app.home.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.home.service.HomeService;

@WebServlet(urlPatterns = "/home/board-top-hit")
public class HomeRestController extends HttpServlet{
	
	//자유게시판 조회수 높은 순으로 상위 5개 만 추출
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HomeService hs = new HomeService();
		
		List<BoardVo> vo = null;
		
		try {
			vo = hs.getBoardTopHit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String str = gson.toJson(vo);
//		resp.getWriter().write(str);
		resp.getWriter().write("ddddd");
	}

}
