package test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.persistence.User;
import test.persistence.UserName;

public class UsefulService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UsefulService.class);

	private UserService userService;
	private UserNameService userNameService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setUserNameService(UserNameService userNameService) {
		this.userNameService = userNameService;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> doInTrancation() {
		List<User> users = userService.findAll();
		
		User user1 = users.get(0);
		user1.setName("ff");
//		
		userService.update(user1);
		//userService.updateName(user1.getId(), "ff");

		String newPassword="aaaa";
		for (User user : users) {
			try {
				LOGGER.info("ggg");
				//userService.updateInNewTransaction(user.getId());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}finally{
				newPassword=newPassword.substring(2);
			}
		}
		LOGGER.debug("Output parameter users=[{}]", users);
		return users;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserName> doInTrancation2() {
		List<UserName> userNames = userNameService.findAll();
		
		UserName userName1 = userNames.get(0);
		userName1.setName("ff");
		
		userNameService.update(userName1);


		String newPassword="aaaa";
		for (UserName userName : userNames) {
			try {
				userService.updateInNewTransaction(userName.getId());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}finally{
				newPassword=newPassword.substring(2);
			}
		}
		LOGGER.debug("Output parameter userNames=[{}]", userNames);
		return userNames;
	}

}
