package serviceimpl;/*
@author Shawn
@creat 2019-07-26-11:15
*/

import dao.CategoryDao;
import daoimpl.CategoryDaoImpl;
import domain.CateGory;
import domain.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
	@Override
	public String findCategory(int pageNumber, int pageSize) throws SQLException {
		CategoryDao categoryDao = new CategoryDaoImpl();
		//每页显示的数据
		List<CateGory> list = categoryDao.findCategory((pageNumber-1)*pageSize,pageSize);
		//总条数
		int count = categoryDao.findCount();
		//封装这两个个数据到pageBean
		// 封装2个数据到pageBean
		PageBean pb = new PageBean();
		pb.setTotal(count);
		pb.setRows(list);
		// 把pageBean的数据转换成json的数据
		JSONObject json = JSONObject.fromObject(pb);
		return json.toString();
	}

	@Override
	public void deleteBycid(String cid) throws SQLException {
		// 调用dao
		CategoryDao categoryDao=new CategoryDaoImpl();
		categoryDao.deleteBycid(cid);
	}

	@Override
	public void save(CateGory cateGory) throws SQLException {
		CategoryDao categoryDao=new CategoryDaoImpl();
		categoryDao.save(cateGory);
	}

	@Override
	public void update(String cid, String cname) throws SQLException {
		CategoryDao categoryDao=new CategoryDaoImpl();
		categoryDao.update(cid,cname);
	}
}
