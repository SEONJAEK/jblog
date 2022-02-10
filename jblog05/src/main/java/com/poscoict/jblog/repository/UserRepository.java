package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.UserVo;
import com.poscoict.jblog.exception.UserRepositoryException;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	//update
//	public boolean update(UserVo vo) {
//		int count = sqlSession.update("user.update", vo);
//		return count == 1;
//		
//	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	public UserVo findByEmailAndPassword(String id, String password) throws UserRepositoryException {
			
			Map<String, String> map = new HashMap<>();
			map.put("i", id);
			map.put("p", password);
			
			return sqlSession.selectOne("user.findByIdAndPassword",map);
	}
}
