package com.poscoict.jblog.service;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ServletContext servletContext;

	public void join(UserVo userVo) {
		boolean result = userRepository.insert(userVo);
		String id = userVo.getId();
		if (result) {
			blogRepository.insert(userVo);
			BlogVo blogvo = blogRepository.findById(id);
			servletContext.setAttribute("blog", blogvo);
		}
	}

	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	//update
//	public void updateUser(UserVo userVo) {
//		userRepository.update(userVo);
//	}

}
