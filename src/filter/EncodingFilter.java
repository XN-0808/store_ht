package filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "EncodingFilter",urlPatterns = {"/*"})
public class EncodingFilter implements  Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override// 增强request的getParameter
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 过滤方法
		final HttpServletRequest request=(HttpServletRequest)req;
		
		// 直接增强getParameter
		HttpServletRequest hsr =(HttpServletRequest)Proxy.newProxyInstance(request.getClass().getClassLoader(),request.getClass().getInterfaces(),new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				if("getParameter".equals(method.getName()))
				{
					String m = request.getMethod();
					if("get".equalsIgnoreCase(m))
					{
						// 之前方法执行一次
						String s =(String)method.invoke(request, args);
						// 增强
						s=new String(s.getBytes("iso8859-1"),"utf-8");
						return s;
					}
					if("post".equalsIgnoreCase(m))
					{
						// 增强
						request.setCharacterEncoding("utf-8");
						// 之前方法执行一次
						return method.invoke(request, args);
					}
						
				}
				return method.invoke(request, args);
			}
		});
		
		//放行
		chain.doFilter(hsr, response);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
