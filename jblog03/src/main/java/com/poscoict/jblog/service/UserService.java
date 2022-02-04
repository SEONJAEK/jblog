package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
		blogRepository.insert(userVo);
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	//update
//	public void updateUser(UserVo userVo) {
//		userRepository.update(userVo);
//	}

}
