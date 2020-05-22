package com.hong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hong.vo.BbsVO;
import com.hong.vo.UserVO;

@Service
public interface UserService {

	public int insertUser(UserVO param);
	public UserVO loginUser(UserVO param);
	public List<BbsVO> selectAllBbs(int pNum);
	public List<BbsVO> selectBbs(int bNum);
	public int insertBbs(BbsVO param);
	public int updateBbs(BbsVO param);
	public int rowCount();
	public int deleteBbs(String delbbsno);
	
	public List<BbsVO> selectList(int start,int end, String searchOption, String keyword) throws Exception;
	public int countArticle(String searchOption, String keyword);
	public boolean increaseViewcnt(int bNum) throws Exception;
	
}