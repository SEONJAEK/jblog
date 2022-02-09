package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(PostVo postVo) {
		int count = sqlSession.insert("post.insert", postVo);
		return count == 1;
	}

	public List<PostVo> selectPostByCategory(Long categoryNo) {
		return sqlSession.selectList("post.selectPostByCategory", categoryNo);
	}

	public PostVo selectPostByPostNo(Long postNo) {
		return sqlSession.selectOne("post.selectPostByPostNo", postNo);
	}

	public PostVo selectPostRecently(Long categoryNo) {
		return sqlSession.selectOne("post.selectPostRecently", categoryNo);
	}

	public Long recentCategoryNo() {
		return sqlSession.selectOne("post.recentCategoryNo");
	}
	
	
	
}
