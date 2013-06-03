package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UriDao
{
	private static Connection conn = null;

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			// new com.mysql.jdbc.Driver();

			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mylibrary", "root", "root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * �����ݿ��filteruri�����ÿ�н���uriΪkey��role(��ɫ)Ϊvalue�ŵ�map,Ȼ�󷵻�һ��map
	 * @return map
	 */
	public static Map<String, String> findAll()
	{
		ResultSet rs = null;
		Statement stmt = null;
		
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from filteruri");

			while (rs.next())
			{
				map.put(rs.getString("URI"), rs.getString("role"));
				
			}
			
			return map;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * �����ݿ��filteruri�����ÿ�н�shareֵΪtrue���뵽set��Ȼ�󷵻�һ��set
	 * @return set
	 */
	public static Set<String> findShareUris()
	{
		ResultSet rs = null;
		Statement stmt = null;
		
		Set<String> set = new HashSet<String>();
		
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from filteruri");

			while (rs.next())
			{
				 if(rs.getString("share").equals("true"))
				 {
					 set.add(rs.getString("URI"));
				 }
			}
			
			return set;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}

				if (stmt != null)
				{
					stmt.close();
					stmt = null;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
}
