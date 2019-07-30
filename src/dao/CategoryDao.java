package dao;/*
@author Shawn
@creat 2019-07-26-11:17
*/

import domain.CateGory;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
	List<CateGory> findCategory(int i, int pageSize) throws SQLException;

	int findCount() throws SQLException;

	void deleteBycid(String cid) throws SQLException;

	void save(CateGory cateGory) throws SQLException;

	void update(String cid, String cname) throws SQLException;
}
