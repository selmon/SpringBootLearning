package com.cml.learn.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cml.learn.jpa.db.bean.User;
import com.cml.learn.jpa.db.write.UserRepository;

@Service
@Transactional()
public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public void testTransaction(long id, long second) throws Exception {
		User user = userRepository.findOne(id);
		log.info("firstUserNickname:" + user.getNickname());

		user.setNickname("modifiedByFirst!");
		userRepository.save(user);

		log.info("update user 1 success!!!");

		User secondUser = userRepository.findOne(second);
		log.info("secondUserNickname:" + secondUser.getNickname());

		secondUser.setNickname("modified by Senond!!");
		userRepository.save(secondUser);

		log.info("update user 2 success!!!");

		// throw new RuntimeException("");
	}

	public void modifyUser(long id, String nickname) throws Exception {
		User user = userRepository.findOne(id);
		if (null == user) {
			return;
		}

		log.info("firstUserNickname:" + user.getNickname());

		user.setNickname(nickname);
		userRepository.save(user);
	}
}
