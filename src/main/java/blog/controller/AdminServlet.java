package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import blog.service.ArticleService;
import blog.service.TagService;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 传所有的文章
		ArticleService as = ArticleService.getInstance();
		request.setAttribute("articles", as.getArticle());
		// 传所有的分类
		request.setAttribute("sort", as.getAllSort());
		// 传所有的标签
		TagService ts = TagService.getInstance();
		request.setAttribute("tags", ts.getAllTag());

		// 转发
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
