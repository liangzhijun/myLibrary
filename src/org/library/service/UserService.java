package org.library.service;

import org.library.dao.UserDao;
import org.library.model.User;

public class UserService
{
	/**
	 * ע��
	 * @param user �û���Ϣ
	 */
	public static void register(User user)
	{
		UserDao.saveUser(user);//��Ԫ�ش������ݿ�
	}

}
