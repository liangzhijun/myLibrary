package org.library.controller;

import java.util.List;


import org.library.dao.BookDao;
import org.library.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibraryController
{
	@RequestMapping(value = "/myLibrary.htm")
	public String myLibrary()
	{
		return "myLibrary";
	}

	/** ͼ�����
	 * 
	 * @param strText
	 * @param strSearchType
	 * @param model
	 * @return "result.jsp"
	 */
	@RequestMapping(value = "/searchBooks.htm")
	public String searchBooks(@RequestParam String strText,
			@RequestParam String strSearchType, Model model)
	{
		List<Book> bookList = BookDao.search(strText, strSearchType);

		if (bookList != null)
		{
			model.addAttribute("bookList", bookList);
			return "SearchResult";
		}
		else
			model.addAttribute("result", "ͼ���û����Ҫ�ҵ�" + strText + "�鿯");

		return "result";
	}

	/** ȡ���鼮�ļ���
	 * 
	 * @param model
	 * @return "books.jsp"
	 */
	@RequestMapping(value = "/getBooks.htm")
	public String getBooks(Model model)
	{
		List<Book> list = BookDao.findBooks();
		model.addAttribute("list", list);
		return "books";
	}

	/** ȡ��ͼ����Ϣ����
	 * 
	 * @param title
	 * @param model
	 * @return "bookinfo.jsp"
	 */
	@RequestMapping(value = "/bookinfo.htm")
	public String bookinfo(@RequestParam String barcode,@RequestParam String title, Model model)
	{
		Book book = BookDao.getBookinfo(barcode);
		List<Book> list = BookDao.bookinfos(title);

		model.addAttribute("book", book);
		model.addAttribute("list", list);
		
		return "bookinfo";
	}
}
