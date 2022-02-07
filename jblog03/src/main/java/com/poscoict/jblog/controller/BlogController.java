package com.poscoict.jblog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!images)(?!assets).*}") //asset을 !(제외하고) ?(있거나 말거나)
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private PostService postService;
	   
	@Autowired
	private ServletContext servletContext;
	
	//선생님 코드
		@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
		public String index1(
				@PathVariable("id") String id, 
				@PathVariable("pathNo1") Optional<Long> pathNo1,
				@PathVariable("pathNo2") Optional<Long> pathNo2,
				Model model, BlogVo blogVo) {
			
			Long categoryNo = 0L;
			Long postNo = 0L;
			
			//2가 있다라는 이야기는 1도 있다는 이야기
			if(pathNo2.isPresent()) {
				categoryNo = pathNo1.get();
				postNo = pathNo2.get();
			//1만 존재한다면
			}else if(pathNo1.isPresent()) {
				categoryNo = pathNo1.get();
			}
			
			BlogVo blogvo = blogService.getInfo(id);
			model.addAttribute("blogVo", blogVo);
			return "blog/blog-main";
		}
	
	@RequestMapping("/admin/basic")
	public String blogbasic() {
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/admin/category")
	public String blogcategory(@PathVariable("id") String id, Model model) {
		Map<String, Object> map  = categoryService.select(id);
		model.addAttribute("map",map);
		return "blog/blog-admin-category";
	}
	
	
	@RequestMapping("/admin/write")
	public String blogwrite(@PathVariable("id") String id, Model model) {
		//servletContext.setAttribute("blogvo", blogService.getInfo(id));//나중에 해당 사람만 접근 가능하도록 
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
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
	
	@RequestMapping(value = "/admin/category/insert", method = RequestMethod.POST)
	public String blogcategoryInsert(@PathVariable("id") String id, CategoryVo categoryVo) {
		categoryService.insertCategory(categoryVo);//categoryVo를 어디에서 세팅해주는지?
		return "redirect:/{id}/admin/category";
	}
	
	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String blogCategoryDelete(@PathVariable("id") String id,@PathVariable("no") Long no ) {
		categoryService.deleteCategory(no);
		return "redirect:/{id}/admin/category";
	}
	
	@RequestMapping(value = "/admin/post/insert", method = RequestMethod.POST)
	public String blogPostInsert(@PathVariable("id") String id, PostVo postvo) {
		postService.insertPost(postvo);
		return "redirect:/{id}";
	}
	
	
	
}


