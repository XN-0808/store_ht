package web;

import domain.CateGory;
import service.CategoryService;
import serviceimpl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.UUID;

/*
@author Shawn
@creat 2019-07-26-11:10
*/
@WebServlet(name = "CategoryServlet",urlPatterns = {"/category"})
public class CategoryServlet extends BaseServlet {
//	自己的方法
	public String findCategory(HttpServletRequest request, HttpServletResponse response){

		//分页
		// 获取当前页
		int pageNumber = Integer.parseInt(request.getParameter("page"));
		// 获取每页显示的条数
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		//调用serviceimpl
		response.setCharacterEncoding("utf-8");
		try {
			CategoryService categoryService = new CategoryServiceImpl();
			String value = categoryService.findCategory(pageNumber,pageSize);
//			System.out.println(value);
			//写给easyui
			response.getWriter().print(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//删除方法：
	public String deleteBycid(HttpServletRequest request, HttpServletResponse response){
		try {

			// 获取点击的分类cid
			String cid = request.getParameter("cid");
			// 调用service 传递cid
			CategoryService categoryService=new CategoryServiceImpl();
			categoryService.deleteBycid(cid);
			// 给easyui一个回馈
			response.getWriter().print("ok");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	//添加
	public String saveCategory(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取cname
			String cname = request.getParameter("cname");
			CateGory cateGory = new CateGory();
			cateGory.setCid(UUID.randomUUID().toString().replace("-",""));
			cateGory.setCname(cname);
			CategoryService categoryService=new CategoryServiceImpl();
			categoryService.save(cateGory);
			//插入数据成功给点反馈
			response.getWriter().print("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//修改
	public String updateCategory(HttpServletRequest request, HttpServletResponse response){
		try {

			//获取到cid
			String cid = request.getParameter("cid");
			//获取到cname
			String cname = request.getParameter("cname");
			CategoryService categoryService=new CategoryServiceImpl();
			categoryService.update(cid,cname);
			//插入数据成功给点反馈
			response.getWriter().print("ok");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
