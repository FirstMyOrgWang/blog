package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.dao.ArticleDao;
import blog.dao.UserDao;
import blog.daoImpl.UserDaoImpl;
import blog.entity.Article;
import blog.entity.User;
import blog.service.ArticleService;
import blog.service.TagService;
import blog.service.UserService;
import blog.utils.LoginUtils;

 
/**
 * @description 登录
 * 
 * @author wry
 * 
 * @date 2021-02-04
 * 
 * Login->index.jsp->init data
 */
@Controller
@RequestMapping(value="/login")
public class LoginController{
	private static final Logger logger = org.apache.log4j.Logger.getLogger(User.class);
	@Autowired
	private UserService userService;

	@ModelAttribute
	public User  init(String id) {
	   User user =userService.get(id);
	   if(user!=null) {
		   return user;
	   }
		return new User();
		  	
	}
	
	
	@RequestMapping(value={"list",""})
	public String list(User user,HttpServletRequest request,HttpServletResponse response) {
		 logger.debug("登录成功！");
		 logger.info("登录成功！2");
		return "views/main";
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginUtils.login(request);

		// 初始化分类
		ArticleService as = ArticleService.getInstance();
		request.setAttribute("sort_count_map", as.getSortAndCount());
		// 初始化文章列表
		request.setAttribute("article_list", as.getArticle());
		// 初始化获取标签
		TagService ts = TagService.getInstance();
		request.setAttribute("tag_list", ts.getAllTag());

		// 初始化侧边栏 日志、分类、标签的个数
		request.setAttribute("article_number", as.getCount(ArticleDao.SEARCH_ARTICLE));
		request.setAttribute("sort_number", as.getCount(ArticleDao.SEARCH_SORT));
		request.setAttribute("tags_number", ts.getTagCount());

		// 阅读排行
		request.setAttribute("visit_rank", as.getVisitRank());


		// 转发到 博客主页 界面
		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
