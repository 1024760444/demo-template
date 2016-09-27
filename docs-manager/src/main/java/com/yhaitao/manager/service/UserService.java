package com.yhaitao.manager.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.yhaitao.manager.dao.mapper.UserMapper;
import com.yhaitao.manager.dao.pojo.User;
import com.yhaitao.manager.util.TimerUtil;

@Component
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	public void service() {
		User user = new User();
		user.setUserName("yanghaitao");
		user.setUserPasswd(DigestUtils.md5DigestAsHex("woshi500242".trim().getBytes()));
		user.setRoleId(5);
		user.setCreateDate(TimerUtil.getSystemDate());
		try {
			userMapper.insert(user);
		} catch (Exception e) {
			// System.err.println(e.getLocalizedMessage());
		}
	}
}
