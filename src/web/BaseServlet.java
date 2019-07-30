package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/*
@author Shawn
@creat 2019-07-15-23:13
*/
@WebServlet(name = "BaseServlet",urlPatterns = {"/base"})
public class BaseServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//点击 user?method=registerUI，转到UserServlet中(它继承了BaseServlet)，
		// 尝试找自己的doGet和doPost方法，找不到来到这里找doGet和doPost方法
		try {
			//谁来调用代表谁....（UserServlet/CategoryServlet/ProductServlet）
			Class clazz = this.getClass();
			//传什么，获取什么方法
			//通过字节码对象获取到指定的方法
			//参数一：传过来的方法的名字
			//参数二：方法的参数类型
			Method method = clazz.getMethod(request.getParameter("method"), HttpServletRequest.class, HttpServletResponse.class);
			//让获取到的方法执行(注意下面自己的方法必须是public 类型的，若为private则使用反射会报错)
			String value = (String)method.invoke(clazz.getDeclaredConstructor().newInstance(), request, response);
			//做统一的请求转发
			if (value != null){
				request.getRequestDispatcher(value).forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
