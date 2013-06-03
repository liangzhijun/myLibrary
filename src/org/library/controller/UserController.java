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
	/** �û���¼��֤
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

			model.addAttribute("result", "��¼�����������");

			return "result";
		}

	/** ѧ���û�ע��
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
				user.setRole("student");//Ĭ�Ͻ�ɫ�Ĳ���

				UserService.register(user);// ע��

				model.addAttribute("result", "ע��ɹ���");
				return "success";
			}

			model.addAttribute("result", result);

			return "result";
		}

	/** �û��޸�����
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
				model.addAttribute("result", "ԭ������������");
				return "result";
			}
			else if (newPassword == null || newPassword.length() < 4
					|| newPassword.length() > 10)
			{
				model.addAttribute("result", "������ĳ�������4---10֮��");
				return "result";
			}
			else if (!newPassword.equals(repassword))
			{
				model.addAttribute("result", "�������벻һ��");
				return "result";
			}
			else
			{
				String username = user.getUsername();
				UserDao.changePasswd(username, repassword);

				user.setPassword(newPassword);
			}

			model.addAttribute("result", "�ɹ��޸����룡");
			return "success";
		}

	/** �û��޸ĸ�����Ϣ
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

			model.addAttribute("result", "������Ϣ�޸ĳɹ���");
			return "success";
		}
}
