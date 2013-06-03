package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.library.model.User;

public class UserDao
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
	 * �����û�ע����Ϣ���浽���ݿ⣻
	 * 
	 * @param user
	 */
	public static void saveUser(User user)
	{
		PreparedStatement pstmt = null;

		try
		{

			pstmt = conn
					.prepareStatement("insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getGender());
			pstmt.setString(6, user.getUnit());
			pstmt.setString(7, user.getPhone());
			pstmt.setString(8, new java.util.Date().toLocaleString());
			pstmt.setString(9, "");
			pstmt.setString(10, "");
			pstmt.setString(11, "2000030536");
			pstmt.setString(12, "������");
			pstmt.setString(13, user.getRole());
			
			
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
					pstmt = null;
				}

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����һ���û����������û��Ƿ�������ݿ�������ڷ��ض�Ӧ�û����ϵ�һ��User���ϣ����򷵻ؿգ�
	 * 
	 * @param myUsername
	 * @return
	 */
	public static User findUser(String myUsername)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user WHERE username='" + myUsername + "'");

			while (rs.next())
			{
				// �����ݿ�����û�
				String username = rs.getString("username");

				if (myUsername.equals(username))
				{
					User user = new User();
					
					user.setUsername(username);
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setGender(rs.getString("gender"));
					user.setUnit(rs.getString("unit"));
					user.setPhone(rs.getString("phone"));
					user.setMobilePhone(rs.getString("mobilePhone"));
					user.setAddress(rs.getString("address"));
					user.setIdCare(rs.getString("idCare"));
					user.setType(rs.getString("type"));
					user.setTime(rs.getString("date"));
					user.setRole(rs.getString("role"));
					
					return user;
				}
			}

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
	 * 
	 * @param changePasswd 
	 * @return
	 */
	public static User changePasswd(String myUsername, String newPassword)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery("select * from user WHERE username='" + myUsername + "'");

			rs.next();
				
			//����һ������
			rs.updateString("password", newPassword);
			rs.updateRow();
			
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
	 * �����ݿ���������Ӧ�û����޸��û�������Ϣ��Ȼ��������ݣ�
	 * 
	 * @param modifyUserinfo 
	 * @return
	 */
	public static User modifyUserinfo(String myUsername, String mobilePhone, String phone, String address, String email)
	{
		ResultSet rs = null;
		Statement stmt = null;

		try
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from user WHERE username='" + myUsername + "'");

			while (rs.next())
			{		
				//����һ������
				rs.updateString("mobilePhone", mobilePhone);
				rs.updateString("phone", phone);
				rs.updateString("address", address);
				rs.updateString("email", email);
				rs.updateRow();
			}

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
