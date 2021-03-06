package com.hong.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hong.dao.UserMapper;
import com.hong.vo.FileVO;

@Service
public class FileUploadService {
	private static final String SAVE_PATH = "/Users/yang/Desktop/yap/mfc/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/mulfc/resources/";
	
	@Autowired
	UserMapper userMapper;
	
	public void restore(MultipartFile multipartFile, String bno) {
		String url = null;
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName
				= originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();
			
			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("- 파일첨부 -");
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			writeFile(multipartFile, saveFileName);
			url = SAVE_PATH + saveFileName;
			System.out.println("saveFilePath : "+url);
			System.out.println("bbsno : "+bno);
			
			FileVO filevo = new FileVO();
			filevo.setBno(bno);
			filevo.setFpath(url);
			filevo.setFogname(originFilename);
			filevo.setFsvname(saveFileName);
			filevo.setFsize(size);
			
			userMapper.insertFile(filevo);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
	
	
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName)
								throws IOException{
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
	
	public List<FileVO> selectFile(String bno) {
		return userMapper.selectFile(bno);
	}
	public FileVO downloadFile(String fno) {
		return userMapper.downloadFile(fno);
	}
	public int deleteFile(String delbbsno) {
		return userMapper.deleteFile(delbbsno);
	}
}