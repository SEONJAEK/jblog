package com.poscoict.jblog.service;

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
		List<CategoryVo> clist = null;
		clist = categoryRepository.findCategoryAll(id);
		map.put("clist", clist);
		return map;
	}
}
