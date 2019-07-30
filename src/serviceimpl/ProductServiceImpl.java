package serviceimpl;/*
@author Shawn
@creat 2019-07-26-22:08
*/

import dao.ProductDao;
import daoimpl.ProductDaoImpl;
import domain.CateGory;
import domain.PageBean;
import domain.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
	@Override
	public String findProduct(int pageNumbe, int pageSize) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		//总条数
		int total = productDao.count();
		//每页显示的数据
		List<Product> rows = new ArrayList<>();
		//所有数据
		List<Map<String, Object>> mlist = productDao.findProduct((pageNumbe - 1) * pageSize, pageSize);

		//把map给list
		for (Map<String, Object> map : mlist) {
			//有一个product
			Product product = new Product();
			BeanUtils.populate(product,map);
			//有一个category
			CateGory cateGory = new CateGory();
			BeanUtils.populate(cateGory,map);
			//把封装好的category给product
			product.setCategory(cateGory);
			//把每一个封装好的数据给list
			rows.add(product);
		}

		//封装
		PageBean pb = new PageBean();
		pb.setTotal(total);
		pb.setRows(rows);
		//转换成json
		JSONObject json = JSONObject.fromObject(pb);
		//返回json数据
		return json.toString();
	}

	@Override
	public String findCategory() throws SQLException {
		ProductDao productDao = new ProductDaoImpl();
		List<CateGory> list = productDao.findCategory();
	//将java转换成json格式
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	@Override
	public void saveProduct(Product product) throws SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.saveProduct(product);
	}
}
