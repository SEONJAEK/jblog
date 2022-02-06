package com.poscoict.jblog.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	   
	@Autowired
	private ServletContext servletContext;
	
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
	
	@RequestMapping("/admin/category/insert")
	public String blogcategoryInsert(@PathVariable("id") String id, CategoryVo categoryVo) {
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/admin/write")
	public String blogwrite() {
		return "blog/blog-admin-write";
	}
	
	@RequestMapping("/main/update")
	public String main(@PathVariable("id") String id, BlogVo blog, @RequestParam("logo-file") MultipartFile file) {
		String logo = fileUploadService.restore(file);
		
		if(logo != null) {
			blog.setLogo(logo);
			blog.setUserId(id);
		}
		blogService.update(blog);
		servletContext.setAttribute("blog", blog);
		return "redirect:/"+ id;
	}
	

}
