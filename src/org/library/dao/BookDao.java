package org.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.library.model.Book;

public class BookDao
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
	 * ����һ��book���󣬽�book��Ϣ���浽һ��library���ݿ��
	 * 
	 * @param book
	 */
	public static void saveBook(Book book)
	{
		PreparedStatement pstmt = null;

		try
		{

			pstmt = conn.prepareStatement("insert into library values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setString(4, book.getCallNumber());
			pstmt.setString(5, book.getISBNandPricing());
			pstmt.setString(6, book.getSubject());
			pstmt.setString(7, book.getPage());
			pstmt.setString(8, book.getList());
			pstmt.setString(9, book.getContent());
			pstmt.setString(10, book.getLib());
			pstmt.setString(11, book.getBarcode());
			pstmt.setString(12, book.getCondition());
			
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
	 * �����ݿ�����鼮�����鼮��һ��book��������ŵ�һ��List�ļ���,Ȼ��List����
	 * 
	 * @return
	 */
	public static List<Book> findBooks()
	{
		ResultSet rs = null;
		Statement stmt = null;
		List<Book> list = new ArrayList<Book>();
			
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from library");

			while (rs.next())
			{
				// �����ݿ�����鼮	
				Book book = new Book();
				String title = rs.getString("title");
				System.out.println(title);
				
				book.setTitle(title);
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				
				list.add(book);
			}
			
			return list;	
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
	 * ����һ��String���͵���������������������library���ݿ�����鼮������һ���鼮����Ϣ
	 * @param title
	 * @return
	 */
	public static Book getBookinfo(String title)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Book book = new Book();
			
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from library WHERE title='" + title + "'");

			if(rs.next())
			{
				// �����ݿ�����鼮	
				
				book.setTitle(title);
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
			}
			
			return book;	
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
	 * �����ݿ�����鼮������������һ���鼮����Ϣ
	 * @return
	 */
	public static List<Book> search(String strText, String strSearchType)
	{
		ResultSet rs = null;
		Statement stmt = null;
		List<Book> list = new ArrayList<Book>();
		
			
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from library WHERE " + strSearchType + " LIKE '" + "%" + strText + "%" + "'");

			while(rs.next())
			{
				// �����ݿ�����鼮	
				Book book = new Book();
				
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setCallNumber(rs.getString("callNumber"));
				book.setISBNandPricing(rs.getString("ISBNandPricing"));
				book.setSubject(rs.getString("subject"));
				book.setPage(rs.getString("page"));
				book.setList(rs.getString("list"));
				book.setContent(rs.getString("content"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				list.add(book);;
			}
			
			return list;
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
	 * �����ݿ�����鼮������һ��Ŀ��������״̬����Ϣ
	 * @return
	 */
	public static List<Book> bookinfos(String title)
	{
		ResultSet rs = null;
		Statement stmt = null;
		List<Book> list = new ArrayList<Book>();
			
		try
		{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from library WHERE title='" + title + "'");

			while (rs.next())
			{
				Book book = new Book();
				
				// �����ݿ�����鼮	
				String callNumber = rs.getString("callNumber");
				System.out.println(callNumber);
				book.setCallNumber(rs.getString("callNumber"));
				book.setLib(rs.getString("lib"));
				book.setBarcode(rs.getString("barcode"));
				book.setCondition(rs.getString("condition"));
				
				list.add(book);
			}
			
			return list;	
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
