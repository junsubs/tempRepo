package com.kh.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.util.BoardImgVo;
import com.kh.app.util.FileUploader;

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50 ,
		maxRequestSize = 1024 * 1024 * 50 * 10
	)
@WebServlet("/free/edit")
public class BoardFreeEditController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			//데꺼
			String no = req.getParameter("no");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
//			Part f = req.getPart("f");
			
			//데뭉
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			
//			if(f.getSize() > 0) {
//				String path = req.getServletContext().getRealPath("/static/free-board-img/");
//				BoardImgVo biVo = FileUploader.saveFile(path, f);
//				biVo.setOriginName(biVo.getOriginName());
//				biVo.setChangeName(biVo.getChangeName());
//			}
//			
			//서비스
			BoardService bs = new BoardService();
			int result = bs.freeEdit(vo);
			
			//화면 == 결과 == 문자열보내기
			if(result != 1) {
				new IllegalStateException();
			}
			resp.sendRedirect(req.getContextPath() + "/free/detail?no=" + no);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", " 수정 실패");
			req.getRequestDispatcher("/WEB-INF/views/common/error-page.jsp").forward(req, resp);
		}
	
	}
}
















































