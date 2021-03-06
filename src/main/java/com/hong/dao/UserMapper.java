package com.hong.dao;

import java.util.List;
import java.util.Map;

import com.hong.vo.BbsVO;
import com.hong.vo.FileVO;
import com.hong.vo.UserVO;

public interface UserMapper {

	public int insertUser(UserVO param);
	public UserVO loginUser(UserVO param);
	public List<BbsVO> selectAllBbs(int pno);
	public List<BbsVO> selectBbs(String bno);
	public int rowCount();
	public int insertBbs(BbsVO param); 
	public int updateBbs(BbsVO param);
	public int deleteBbs(String delbbsno);
	
	public int countArticle(Map<String, String> articleMap);
	public List<BbsVO> selectList(Map<String, Object> listMap);
	public boolean increaseViewcnt(String bno) throws Exception;
	
	public int insertFile(FileVO fileinfo);
	public List<FileVO> selectFile(String bno);
	public FileVO downloadFile(String fno);
	public int deleteFile(String delbbsno);
	
	public int emailchk(String useremail);
	public int namechk(String username);
}