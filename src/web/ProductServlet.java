package web;

import service.ProductService;
import serviceimpl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
@author Shawn
@creat 2019-07-26-21:58
*/
@WebServlet(name = "ProductServlet",urlPatterns = {"/product"})
public class ProductServlet extends BaseServlet {
	//自己的方法
	public String findProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		try {
			//获取当前页
			int pageNumbe  = Integer.parseInt(request.getParameter("page"));
			//获取每页的条数
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			//调用service
			ProductService productService = new ProductServiceImpl();
			//查询所有商品
			String value = productService.findProduct(pageNumbe, pageSize);
			System.out.println(value);
			response.getWriter().print(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//	商品添加时，点击所属分类下拉框，从数据库查找所有的商品分类，显示在下拉框选项中
	public String findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		try {
			//调用Service
			ProductService productService = new ProductServiceImpl();
			String value = productService.findCategory();
			System.out.println(value);
			response.getWriter().print(value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
