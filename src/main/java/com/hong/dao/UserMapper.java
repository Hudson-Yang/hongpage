package com.hong.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hong.vo.BbsVO;
import com.hong.vo.FileVO;
import com.hong.vo.UserVO;

public interface UserMapper {

	public int insertUser(UserVO param);
	public UserVO loginUser(UserVO param);
	public List<BbsVO> selectAllBbs(int pNum);
	public List<BbsVO> selectBbs(int bNum);
	public int rowCount();
	public int insertBbs(BbsVO param);
	public int updateBbs(BbsVO param);
	public int deleteBbs(String delbbsno);
	
	public int countArticle(Map<String, String> articleMap);
	public List<BbsVO> selectList(Map<String, Object> listMap);
	public boolean increaseViewcnt(int bNum) throws Exception;
	
	public int insertFile(String fname);
}