package org.library.controller;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController
{
	/**
	 * 管理者注册
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
			result += "邮箱不可以为空! ";
		}
		if (null == name || "".equals(name))
		{
			result += "姓名不可以为空！";
		}
		if (null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		else if (username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		// 密码的长度均须在4---10之间
		if (!password.equals(repassword))
		{
			result += "密码不一致！";
		}
		if (password == null || password.length() < 4 || password.length() > 10)
		{
			result += "密码的长度须在4---10之间";
		}
		if (null == gender)
		{
			result += "性别必须要选！";
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
			user.setRole("admin");// 默认角色的参数

			UserService.register(user);// 注册

			model.addAttribute("result", "注册成功！");
			return "success";
		}

		model.addAttribute("result", result);

		return "result";
	}

	/**
	 * 图书录入
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
	 * @return "result";
	 */
	@RequestMapping(value = "/booksEntering.htm ", method = RequestMethod.POST)
	public String booksEntering(@RequestParam String title,
			@RequestParam String author, @RequestParam String publisher,
			@RequestParam String callNumber,
			@RequestParam String ISBNandPricing, @RequestParam String subject,
			@RequestParam String pages, @RequestParam String list,
			@RequestParam String content, @RequestParam String barcode,
			@RequestParam String condition, @RequestParam String lib,
			Model model)
	{
		String result = "";

		if (null == title || "".equals(title))
		{
			result += "题名必须要填写! ";
		}

		if (null == author || "".equals(author))
		{
			result += "责任者必须要填写！";
		}

		if (null == publisher || "".equals(publisher))
		{
			result += "出版社必须要填写！";
		}

		if (null == callNumber || "".equals(callNumber))
		{
			result += "索引号必须要填写! ";
		}

		if (null == ISBNandPricing || "".equals(ISBNandPricing))
		{
			result += "ISBN及定价必须要填写！";
		}

		if (null == subject || "".equals(subject))
		{
			result += "科学主题必须要填写！";
		}

		if (null == pages || "".equals(pages))
		{
			result += "载体形态项必须要填写！";
		}

		if (null == list || "".equals(list))
		{
			result += "书目录必须要填写！";
		}

		if (null == content || "".equals(content))
		{
			result += "内容简介必须要填写！";
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
			book.setPage(pages);
			book.setList(list);
			book.setContent(content);
			book.setBarcode(barcode);
			book.setCondition(condition);
			book.setLib(lib);

			BookDao.saveBook(book);// 将书目信息存入数据库

			model.addAttribute("result", "成功录入书目");
			return "result";
		}
		else
			model.addAttribute("result", result);

		return "result";
	}
}
