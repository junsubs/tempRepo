package com.kh.app.admin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

public class FileUploader {
	
	/**
	 * 파일들을 서버에 저장 하고,
	 * 저장된 파일명들이 담긴 리스트를 반환한다.
	 * 
	 * @param 파일 저장할 경로
	 * @param 파일객체배열
	 * @return List<AttachmentVo>
	 * @throws Exception
	 */
	public static List<AttachmentVo> saveFile(String path, List<Part> fList) throws Exception{
		
		List<AttachmentVo> list = new ArrayList<>();
		
		for(Part f : fList) {
			AttachmentVo attVo = saveFile(path, f);
			list.add(attVo);
		}
		
		return list;
	}
	
	/**
	 * 파일을 서버에 저장 하고,
	 * 저장된 파일명을 반환한다.
	 * 
	 * @param 파일 저장할 경로
	 * @param 파일객체
	 * @return AttachmentVo
	 * @throws Exception
	 */
	public static AttachmentVo saveFile(String path , Part f) throws Exception {
		String randomName = UUID.randomUUID().toString();
		String originName = f.getSubmittedFileName();
		String ext = originName.substring( originName.lastIndexOf(".") ); 
		String changeName = randomName + ext;
		File target = new File(path + changeName);
		
		
		try (
				FileOutputStream fos = new FileOutputStream(target);
				InputStream is = f.getInputStream();
			) 
		{
			byte[] buf = new byte[1024];
			int size = 0;
			while((size = is.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
		}
		
		AttachmentVo vo = new AttachmentVo();
		vo.setOriginName(originName);
		vo.setChangeName(changeName);
		return vo;
	}//method

}//class