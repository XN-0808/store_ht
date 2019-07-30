package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCUtils
{
	
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	// 自动会去src目录下找c3p0.properties或则c3p0-config.xml
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	// 创建数据库连接
	
	
	// 获取连接池
	public static DataSource  getDataSource()
	{
		return ds;
	}
	
	
	// 获取连接
	public static Connection  getConnection() throws Exception
	{
	
		return ds.getConnection();
	}
	
	// 获取threadlocal
		public static ThreadLocal  getTl() throws Exception
		{
		
			return tl;
		}
	
	
	// 关闭资源
	public static void closeZY(Connection con,Statement ps,ResultSet rs)
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null)
		{
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
