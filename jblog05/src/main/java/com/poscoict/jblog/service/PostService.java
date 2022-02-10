package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public boolean insertPost(PostVo postvo) {
		return postRepository.insert(postvo);
	}

	public List<PostVo> selectPostByCategory(Long categoryNo) {
		System.out.println(postRepository.selectPostByCategory(categoryNo));
		return postRepository.selectPostByCategory(categoryNo);
	}
	
	public PostVo selectPostByPostNo(Long postNo) {
		return postRepository.selectPostByPostNo(postNo);
	}
	
	public PostVo selectPostRecently(Long categoryNo) {
		return postRepository.selectPostRecently(categoryNo);
	}

	public Long recentCategoryNo() {
		return postRepository.recentCategoryNo();
	}
}
