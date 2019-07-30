package service;/*
@author Shawn
@creat 2019-07-26-11:15
*/

import domain.CateGory;

import java.sql.SQLException;

public interface CategoryService {
	String findCategory(int pageNumber, int pageSize) throws SQLException;

	void deleteBycid(String cid) throws SQLException;

	void save(CateGory cateGory) throws SQLException;

	void update(String cid, String cname) throws SQLException;
}
