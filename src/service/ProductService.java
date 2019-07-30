package service;/*
@author Shawn
@creat 2019-07-26-22:07
*/

import domain.Product;

import java.sql.SQLException;

public interface ProductService {
	String findProduct(int pageNumbe, int pageSize) throws Exception;

	String findCategory() throws SQLException;

	void saveProduct(Product product) throws SQLException;
}
