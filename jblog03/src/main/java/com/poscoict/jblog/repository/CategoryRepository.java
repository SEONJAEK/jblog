package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.insert", categoryVo);
		return count == 1;
	}
	
	public List<CategoryVo> findCategoryAll(String id) {
		return sqlSession.selectList("category.findCategoryAll", id);
	}
	
	public Long postCntByCategory(Long categoryNo) {
		return sqlSession.selectOne("category.postCntByCategory", categoryNo);
	}
}
