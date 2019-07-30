package dao;/*
@author Shawn
@creat 2019-07-26-22:10
*/

import domain.CateGory;
import domain.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProductDao {
	int count() throws SQLException;

	List<Map<String, Object>> findProduct(int i, int pageSize) throws SQLException;

	List<CateGory> findCategory() throws SQLException;

	void saveProduct(Product product) throws SQLException;
}
