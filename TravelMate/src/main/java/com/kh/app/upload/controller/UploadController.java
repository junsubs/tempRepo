package com.kh.app.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

@MultipartConfig(
		fileSizeThreshold = 1024 *1024*50,
		maxFileSize = 1024 * 1024 * 50,
		maxRequestSize = 1024 * 1024 * 50 *10
		)

@WebServlet(urlPatterns = "/upload")
public class UploadController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<String>changeNameList = new ArrayList<>();
		
		Collection<Part> fileList = req.getParts();
		String path = req.getServletContext().getRealPath("/static/img/board-img/");
		
		for(Part f : fileList) {
			if(!f.getName().equals("f")) {
				continue;
			}
			String randomName= UUID.randomUUID().toString();
			String originName = f.getSubmittedFileName();
			String ext = originName.substring(originName.lastIndexOf("."));
			String changeName = randomName + ext;
			File target =  new File(path ,changeName);
			FileOutputStream os = new FileOutputStream(target);
			
			InputStream is =  f.getInputStream();
			
			byte[]buf = new byte[1024];
			int size = 0;
			while((size = is.read(buf)) != -1) {
				os.write( buf ,0 ,size);
			}
			changeNameList.add(changeName);
		}
		
		Gson gson = new Gson();
		String str = gson.toJson(changeNameList);
		resp.getWriter().write(str);
	}
	
}
