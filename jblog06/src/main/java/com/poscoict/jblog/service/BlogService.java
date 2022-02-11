package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {
	//getInfo
	
	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getInfo(String id) {
		return blogRepository.findById(id);
	}

	public boolean update(BlogVo blog) {
		return blogRepository.update(blog) == 1; 
	}
	
}
