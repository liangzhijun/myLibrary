package org.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.library.dao.BookDao;
import org.library.dao.UserDao;
import org.library.model.Book;
import org.library.model.User;
import org.library.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class LibraryController
{
	
	@RequestMapping(value="/myLibrary.htm")
	public String myLibrary()
	{
		return "myLibrary";
	}
	
	//用户登录验证
	@RequestMapping(value="/checkingUser.htm", method=RequestMethod.POST)
	public String chickingUser(@RequestParam String username, @RequestParam String password, Model model)
	{
		System.out.println("started chickingUser");
		
		User user = UserDao.findUser(username);
		
		if (user != null && password.equals(user.getPassword()))
		{
			model.addAttribute("user", user);
			
			if(user.getRole().equals("student"))
			{
				return "redirect:/myLibrary.htm";
			}
			else
			{
				return "redirect:/adminLibrary.jsp";
			}
		}
		
		model.addAttribute("result", "登录名或密码错误");
		
		return "error";
	}
	

	//用户注册验证
	@RequestMapping(value="/register.htm")
	public String register(@RequestParam String username, @RequestParam String password, @RequestParam String repassword, 
			@RequestParam String email, @RequestParam String name,@RequestParam String gender, @RequestParam String unit,
			@RequestParam String phone, Model model)
	{
		String result = "";
		
		System.out.println("started register");
		if(null == email || "".equals(email))
		{
			result += "邮箱不可以为空! ";
		}
		if(null == name || "".equals(name))
		{
			result += "姓名不可以为空！";
		}
		if(null == username || "".equals(username))
		{
			result += " 用户名不可以为空！";
		}
		else if(username.length() < 4 || username.length() > 10)
		{
			result += " 用户名长度应该是4和10之间！";
		}
		//密码的长度均须在4---10之间
		if(!password.equals(repassword))
		{
			result += "密码不一致！";
		}
		if(password == null || password.length() < 4 || password.length() > 10)
		{
			result += "密码的长度须在4---10之间";
		}
		if(null == gender)
		{
			result += "性别必须要选！";
		}
			
		if(result == "")
		{	
			User user = new User();
			
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setGender(gender);
			user.setUnit(unit);
			user.setPhone(phone);
			
			UserService.register(user);//注册
			
			model.addAttribute("result", "注册成功！");
			return "success";
		}
		
		model.addAttribute("result", result);
		
		return "error";
	}
	
	//修改密码
	@RequestMapping(value="/changePasswd.htm", method=RequestMethod.POST)
	public String changePasswd(@RequestParam String password, @RequestParam String newPassword, @RequestParam String repassword, ModelMap model)
	{
		User user = (User)model.get("user");
		
		System.out.println(user.getPassword());
		
		if(!password.equals(user.getPassword()))
		{
			model.addAttribute("result", "原密码输入有误");
			return"error";
		}
		else if(newPassword == null || newPassword.length() < 4 || newPassword.length() > 10)
		{
			model.addAttribute("result", "新密码的长度须在4---10之间");
			return "error";
		}
		else if(!newPassword.equals(repassword))
		{
			model.addAttribute("result", "两次密码不一致");
			return "error";
		}
		else
		{
			String username = user.getUsername();
			UserDao.changePasswd(username, repassword);
			
			user.setPassword(newPassword);
		}
		
		model.addAttribute("result", "成功修改密码！");
		return "success";
	}
	
	//修改用户个人信息
	@RequestMapping(value="/modifyUserinfo.htm", method=RequestMethod.POST)
	public String modifyUserinfo(@RequestParam String mobilePhone, @RequestParam String phone, @RequestParam String address,
			@RequestParam String email, ModelMap model, HttpServletResponse resp)
	{
		User user = (User) model.get("user");
		
		String username = user.getUsername();
		
		UserDao.modifyUserinfo(username, mobilePhone, phone, address, email);
		user.setMobilePhone(mobilePhone);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		
		model.addAttribute("result", "个人信息修改成功，");
		return "success";
	}
	
	//图书检索
	@RequestMapping(value="/searchBooks.htm")
	public String searchBooks(@RequestParam String strText, @RequestParam String strSearchType, Model model)
	{
		List<Book> bookList = BookDao.search(strText, strSearchType);
		
		if(bookList != null)
		{
			model.addAttribute("bookList", bookList);
			return "SearchResult";
		}
		else
			model.addAttribute("result", "图书馆没有你要找的"+ strText + "书刊");
		
			return "result";
	}
	
	//取得书籍的集合
	@RequestMapping(value="/getBooks.htm")
	public String getBooks(Model model)
	{
		List<Book> list = BookDao.findBooks();
		
		model.addAttribute("list", list);
		return "books";
	}
	
	//取得图书信息内容
	@RequestMapping(value="/bookinfo.htm")
	public String bookinfo(@RequestParam String title, Model model)
	{
		Book book = BookDao.getBookinfo(title);
		List<Book> list =  BookDao.bookinfos(title);
		
		model.addAttribute("book", book);
		model.addAttribute("list", list);
		
		return "bookinfo";
	}
	
	//图书录入
	
	public String booksEntering(Model model, @RequestParam String title, @RequestParam String author,
			@RequestParam String publicsher,@RequestParam String callNumber, @RequestParam String ISBNandPricing, 
			@RequestParam String subject, @RequestParam String pages, @RequestParam String list, @RequestParam 
			String content, @RequestParam String barcode, @RequestParam String condition, @RequestParam String lib) 
	{
		model.addAttribute("result", "成功录入书目");
		return "result";
	}
	
}

