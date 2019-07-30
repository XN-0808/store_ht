package daoimpl;/*
@author Shawn
@creat 2019-07-26-11:17
*/

import dao.CategoryDao;
import domain.CateGory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
	@Override
	public List<CateGory> findCategory(int i, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select * from category limit ?,?";
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class),i,pageSize);
		return list;
	}

	@Override
	public int findCount() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from category";
		Long l =(Long)qr.query(sql, new ScalarHandler());
		return l.intValue();
	}

	@Override
	public void deleteBycid(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from category where cid=?";
		qr.update(sql,cid);
	}

	@Override
	public void save(CateGory cateGory) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into category values(?,?)";
		qr.update(sql,cateGory.getCid(),cateGory.getCname());
	}

	@Override
	public void update(String cid, String cname) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		qr.update(sql,cname,cid);
	}
}
