package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

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

	public int deleteCategory(Long no) {
		return sqlSession.delete("category.deleteCategory", no);
	}
	
	// 카테고리 데이터 추가
	public boolean insertCategory(UserVo vo) {
		// TODO Auto-generated method stub
		int count = sqlSession.insert("category.insertCategory",vo);
		return count==1;
	}
}
