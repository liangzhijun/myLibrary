package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
	
	public static Set<String> findAll()
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
				set.add(rs.getString("URI"));
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
