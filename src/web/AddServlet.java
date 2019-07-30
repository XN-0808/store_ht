package web;


import domain.CateGory;
import domain.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import service.ProductService;
import serviceimpl.ProductServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/*
@author Shawn
@creat 2019-07-27-11:05
*/
@WebServlet(name = "AddServlet",urlPatterns = {"/addServlet"})
public class AddServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取页面的所有数据

		try {
			// 1 创建磁盘文件工厂 --生成上传对象
			DiskFileItemFactory disk = new DiskFileItemFactory();
			// 2 根据磁盘工厂创建上传对象 --解析请求体(request)
			ServletFileUpload sfu = new ServletFileUpload(disk);
			// 3解析request
			List<FileItem> list = sfu.parseRequest(request);
			// 4 创建Map对象 用来封装数据
			HashMap<String, Object> map = new HashMap<>();
			// 4 循环
			for (FileItem fileItem : list) {
				// 5 判断是普通的还是上传的 true:普通项 false:上传项
				if (fileItem.isFormField()){
					//普通项保存数据
					String name = fileItem.getFieldName();//cid
					String value = fileItem.getString("utf-8");//手机数码
					map.put(name,value);
				}else {
					//上传项
					//获取文件名称
					String filename = fileItem.getName();
					//获取文件内容
					InputStream is = fileItem.getInputStream();
					//获取当前项目早服务器根目录
					ServletContext context = getServletContext();
					String path = context.getRealPath("/upload");
					//创建输出流
					FileOutputStream os = new FileOutputStream(path + "/" + filename);
					//对流对拷
					IOUtils.copy(is,os);
					os.close();
					is.close();
					//把图片地址放在map   upload/测试.jpg
					map.put("pimage","upload/"+filename);
				}
			}

			// 把map数据给product
			Product product = new Product();
			BeanUtils.populate(product,map);
			product.setPid(UUID.randomUUID().toString().replace("-", ""));
			product.setPflag(0);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());
			product.setPdate(date);

			// 分类
			CateGory cateGory = new CateGory();
			cateGory.setCid(map.get("cid").toString());

			// 分类在给product
			product.setCategory(cateGory);
			// 调用service 传递参数
			ProductServiceImpl productService = new ProductServiceImpl();
			productService.saveProduct(product);
			// 给easyui反馈
			response.getWriter().print("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
