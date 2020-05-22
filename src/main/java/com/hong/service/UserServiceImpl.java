package com.hong.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hong.dao.UserMapper;
import com.hong.vo.BbsVO;
import com.hong.vo.UserVO;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int insertUser(UserVO param) {
		return userMapper.insertUser(param);
	}
	@Override
	public UserVO loginUser(UserVO param) {
		return userMapper.loginUser(param);
	}
	@Override
	public List<BbsVO> selectList(int start, int end, String searchOption, String keyword) throws Exception {
		// 검색옵션, 키워드 맵에 저장
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("searchOption", searchOption);
		listMap.put("keyword", keyword);
		// BETWEEN #{start}, #{end}에 입력될 값
		listMap.put("start", start);
		listMap.put("end", end);
		return userMapper.selectList( listMap );
	}
	
	@Override
	public int countArticle(String searchOption, String keyword) {
		// 검색옵션, 키워드 맵에 저장
		Map<String, String> articleMap = new HashMap<String, String>();
		articleMap.put("searchOption", searchOption);
		articleMap.put("keyword", keyword);
		return userMapper.countArticle( articleMap );
	}
	
	public List<BbsVO> selectAllBbs(int pNum){
		return userMapper.selectAllBbs(pNum);
	}
	@Override
	public int rowCount() {
		return userMapper.rowCount();
	}
	
	@Override
	public List<BbsVO> selectBbs(int bNum) {
		return userMapper.selectBbs(bNum);
	}

	@Override
	public int insertBbs(BbsVO param) {
		return userMapper.insertBbs(param);
	}

	@Override
	public int updateBbs(BbsVO param) {
		return userMapper.updateBbs(param);
	}

	@Override
	public int deleteBbs(String delbbsno) {
		return userMapper.deleteBbs(delbbsno);
	}
	
	@Override
	public boolean increaseViewcnt(int bNum) throws Exception{
		return userMapper.increaseViewcnt(bNum);
	}
}//

