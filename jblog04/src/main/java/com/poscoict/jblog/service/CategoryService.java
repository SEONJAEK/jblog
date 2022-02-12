package com.poscoict.jblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public boolean insertCategory(CategoryVo categoryVo) {
		 return categoryRepository.insert(categoryVo);
	}
	
	public Map<String, Object> select(String id){
		HashMap<String, Object> map = new HashMap<>();
		List<CategoryVo> clist = categoryRepository.findCategoryAll(id);
		List<Long> noList = new ArrayList<>();
		for(CategoryVo vo : clist) {
			Long postCnt = categoryRepository.postCntByCategory(vo.getNo());
			noList.add(postCnt);
		}
		map.put("clist", clist);
		map.put("noList", noList);
		return map;
	}

	public boolean deleteCategory(Long no) {
		return categoryRepository.deleteCategory(no) == 1;
	}
	

	
	
//	public Long getPostCntByCategory(Long categoryNo) {
//		return categoryRepository.postCntByCategory(categoryNo);
//	}
}
