package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("")
	public String blogmain(@PathVariable("id") String id, Model model, BlogVo blogVo ) {
		//위치 매핑만 해줘
		BlogVo blogvo = blogService.getInfo(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic")
	public String blogbasic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/category")
	public String blogcategory() {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/write")
	public String blogwrite() {
		return "blog/blog-admin-write";
	}
	
	

}
