package daoimpl;/*
@author Shawn
@creat 2019-07-26-22:10
*/

import dao.ProductDao;
import domain.CateGory;
import domain.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
	@Override
	public int count() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from product";
		Long list = (Long) qr.query(sql, new ScalarHandler());
		return list.intValue();
	}

	@Override
	public List<Map<String, Object>> findProduct(int i, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product p,category c where p.cid=c.cid limit ?,?";
		//没有对象，先用map封装把数据拿到手。再想办法
		List<Map<String, Object>> mlist = qr.query(sql, new MapListHandler(), i, pageSize);
		return mlist;
	}

	@Override
	public List<CateGory> findCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class));
		return list;
	}

	@Override
	public void saveProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),
				product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
	}
}
