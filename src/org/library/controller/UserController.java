package org.library.controller;

import javax.servlet.http.HttpServletResponse;

import org.library.dao.UserDao;
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
public class UserController
{
	/** 用户登录验证
	 *  
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
		@RequestMapping(value = "/checkingUser.htm", method = RequestMethod.POST)
		public String chickingUser(@RequestParam String username,
				@RequestParam String password, Model model)
		{
			System.out.println("started chickingUser");

			User user = UserDao.findUser(username);

			if (user != null && password.equals(user.getPassword()))
			{
				model.addAttribute("user", user);

				if (user.getRole().equals("student"))
				{
					return "redirect:/myLibrary.htm";
				}
				else
				{
					return "redirect:/adminLibrary.jsp";
				}
			}

			model.addAttribute("result", "登录名或密码错误");

			return "result";
		}

	/** 学生用户注册
	 * 
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
		@RequestMapping(value = "/register.htm")
		public String register(
				@RequestParam String username,
				@RequestParam String password, 
				@RequestParam String repassword,
				@RequestParam String email, 
				@RequestParam String name,
				@RequestParam String gender, 
				@RequestParam String unit,
				@RequestParam String phone, 
				Model model)
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
				user.setRole("student");//默认角色的参数

				UserService.register(user);// 注册

				model.addAttribute("result", "注册成功！");
				return "success";
			}

			model.addAttribute("result", result);

			return "result";
		}

	/** 用户修改密码
	 *  
	 * @param password
	 * @param newPassword
	 * @param repassword
	 * @param model
	 * @return
	 */
		@RequestMapping(value = "/changePasswd.htm", method = RequestMethod.POST)
		public String changePasswd(@RequestParam String password,
				@RequestParam String newPassword, @RequestParam String repassword,
				ModelMap model)
		{
			User user = (User) model.get("user");

			System.out.println(user.getPassword());

			if (!password.equals(user.getPassword()))
			{
				model.addAttribute("result", "原密码输入有误");
				return "result";
			}
			else if (newPassword == null || newPassword.length() < 4
					|| newPassword.length() > 10)
			{
				model.addAttribute("result", "新密码的长度须在4---10之间");
				return "result";
			}
			else if (!newPassword.equals(repassword))
			{
				model.addAttribute("result", "两次密码不一致");
				return "result";
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

	/** 用户修改个人信息
	 * 
	 * @param mobilePhone
	 * @param phone
	 * @param address
	 * @param email
	 * @param model
	 * @param resp
	 * @return
	 */
		@RequestMapping(value = "/modifyUserinfo.htm", method = RequestMethod.POST)
		public String modifyUserinfo(@RequestParam String mobilePhone,
				@RequestParam String phone, @RequestParam String address,
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
}
