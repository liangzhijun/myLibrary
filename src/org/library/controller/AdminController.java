package org.library.controller;

import java.util.List;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController
{
	/**
	 * ������ע��
	 * @param username
	 * @param password
	 * @param repassword
	 * @param email
	 * @param name
	 * @param gender
	 * @param unit
	 * @param phone
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminRegister.htm")
	public String adminRegister(@RequestParam String username,
			@RequestParam String password, @RequestParam String repassword,
			@RequestParam String email, @RequestParam String name,
			@RequestParam String gender, @RequestParam String unit,
			@RequestParam String phone, Model model)
	{
		String result = "";

		System.out.println("started register");
		if (null == email || "".equals(email))
		{
			result += "���䲻����Ϊ��! ";
		}
		if (null == name || "".equals(name))
		{
			result += "����������Ϊ�գ�";
		}
		if (null == username || "".equals(username))
		{
			result += " �û���������Ϊ�գ�";
		}
		else if (username.length() < 4 || username.length() > 10)
		{
			result += " �û�������Ӧ����4��10֮�䣡";
		}
		// ����ĳ��Ⱦ�����4---10֮��
		if (!password.equals(repassword))
		{
			result += "���벻һ�£�";
		}
		if (password == null || password.length() < 4 || password.length() > 10)
		{
			result += "����ĳ�������4---10֮��";
		}
		if (null == gender)
		{
			result += "�Ա����Ҫѡ��";
		}

		if (result == "")
		{
			User user = new User();

			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setUnit(unit);
			user.setPhone(phone);
			user.setRole("admin");// Ĭ�Ͻ�ɫ�Ĳ���

			UserService.register(user);// ע��

			model.addAttribute("result", "ע��ɹ���");
			return "success";
		}

		model.addAttribute("result", result);

		return "result";
	}

	/**
	 * ͼ��¼��
	 * 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param callNumber
	 * @param ISBNandPricing
	 * @param subject
	 * @param page
	 * @param list
	 * @param content
	 * @param barcode
	 * @param condition
	 * @param lib
	 * @param model
	 * @return "result";
	 */
	@RequestMapping(value = "/booksEntering.htm ", method = RequestMethod.POST)
	public String booksEntering(@RequestParam String title,
			@RequestParam String author, @RequestParam String publisher,
			@RequestParam String callNumber,
			@RequestParam String ISBNandPricing, @RequestParam String subject,
			@RequestParam String page, @RequestParam String list,
			@RequestParam String content, @RequestParam String barcode,
			@RequestParam String condition, @RequestParam String lib,
			Model model)
	{
		String result = "";

		if (null == title || "".equals(title))
		{
			result += "��������Ҫ��д! ";
		}

		if (null == author || "".equals(author))
		{
			result += "�����߱���Ҫ��д��";
		}

		if (null == publisher || "".equals(publisher))
		{
			result += "���������Ҫ��д��";
		}

		if (null == callNumber || "".equals(callNumber))
		{
			result += "�����ű���Ҫ��д! ";
		}

		if (null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN�����۱���Ҫ��д��";
		}

		if (null == subject || "".equals(subject))
		{
			result += "��ѧ�������Ҫ��д��";
		}

		if (null == page || "".equals(page))
		{
			result += "������̬�����Ҫ��д��";
		}

		if (null == list || "".equals(list))
		{
			result += "��Ŀ¼����Ҫ��д��";
		}

		if (null == content || "".equals(content))
		{
			result += "���ݼ�����Ҫ��д��";
		}

		if (result == "")
		{
			Book book = new Book();

			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCallNumber(callNumber);
			book.setISBNandPricing(ISBNandPricing);
			book.setSubject(subject);
			book.setPage(page);			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);

			BookDao.modifyBook(book);// ����Ŀ��Ϣ�������ݿ�

			model.addAttribute("result", "�ɹ�¼����Ŀ");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
	
	/**
	 * ɾ��ͼ��
	 * @param model
	 * @return  "books.htm";
	 */
	@RequestMapping("deleteBooks.htm")
	public String deleteBooks(Model model)
	{
		List<Book> list = BookDao.findBooks();
		model.addAttribute("list", list);
		
		return "deleteBooks";
	}
	
	/** ɾ��ͼ�������
	 * 
	 * @param strText
	 * @param strSearchType
	 * @param model
	 * @return "result.jsp"
	 */
	@RequestMapping(value = "/SearchDeleteBooks.htm")
	public String SearchDeleteBooks(@RequestParam String strText,
			@RequestParam String strSearchType, Model model)
	{
		List<Book> list = BookDao.search(strText, strSearchType);

		if (list != null)
		{
			model.addAttribute("list", list);
			return "deleteBooks";
		}
		else
			model.addAttribute("result", "ͼ���û����Ҫ�ҵ�" + strText + "�鿯");

		return "result";
	}
	
	/** ɾ���鱾
	 * 
	 * @param barcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="deleteBook.htm", method = RequestMethod.GET)
	public String deleteBook(@RequestParam String barcode, Model model)
	{
		BookDao.deleteBook(barcode);
		
		return "redirect:/deleteBooks.htm";
	}
	
	/**
	 * �޸�ͼ����Ϣ
	 * @param barcode
	 * @param model
	 * @return
	 */
	@RequestMapping(value="modifyBooks.htm")
	public String modifyBooks(@RequestParam String barcode, ModelMap model)
	{
		Book book = BookDao.getBookinfo(barcode);
		model.addAttribute("book", book);
		
		return "modifyBook";
	}
	
	/**
	 * 
	 * @param title
	 * @param author
	 * @param publisher
	 * @param callNumber
	 * @param ISBNandPricing
	 * @param subject
	 * @param pages
	 * @param list
	 * @param content
	 * @param barcode
	 * @param condition
	 * @param lib
	 * @param model
	 * @return
	 */
	@RequestMapping(value="modifyBook.htm", method = RequestMethod.POST)
	public String modifyBook(@RequestParam String title,
			@RequestParam String author, @RequestParam String publisher,
			@RequestParam String callNumber,
			@RequestParam String ISBNandPricing, @RequestParam String subject,
			@RequestParam String page, @RequestParam String list,
			@RequestParam String content, @RequestParam String barcode,
			@RequestParam String condition, @RequestParam String lib,
			Model model)
	{
		String result = "";

		if (null == title || "".equals(title))
		{
			result += "��������Ҫ��д! ";
		}

		if (null == author || "".equals(author))
		{
			result += "�����߱���Ҫ��д��";
		}

		if (null == publisher || "".equals(publisher))
		{
			result += "���������Ҫ��д��";
		}

		if (null == callNumber || "".equals(callNumber))
		{
			result += "�����ű���Ҫ��д! ";
		}

		if (null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN�����۱���Ҫ��д��";
		}

		if (null == subject || "".equals(subject))
		{
			result += "��ѧ�������Ҫ��д��";
		}

		if (null == page || "".equals(page))
		{
			result += "������̬�����Ҫ��д��";
		}

		if (null == list || "".equals(list))
		{
			result += "��Ŀ¼����Ҫ��д��";
		}

		if (null == content || "".equals(content))
		{
			result += "���ݼ�����Ҫ��д��";
		}

		if (result == "")
		{
			Book book = new Book();

			book.setTitle(title);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setCallNumber(callNumber);
			book.setISBNandPricing(ISBNandPricing);
			book.setSubject(subject);
			book.setPage(page);
			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);

			BookDao.saveBook(book);// ����Ŀ��Ϣ�������ݿ�

			model.addAttribute("result", "ͼ���޸ĳɹ�");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
}
