package test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import test.persistence.UserName;
import test.repository.UserNameRepository;

public class UserNameService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserNameService.class);
	
	private UserNameRepository userNameRepository;
	
	public void setUserNameRepository(UserNameRepository userNameRepository) {
		this.userNameRepository = userNameRepository;
	}
	
	@Transactional(readOnly=true)
	public List<UserName> findAll() {
		List<UserName> userNames = userNameRepository.findAllEntities();
		LOGGER.debug("Output parameter userNames=[{}]", userNames);
		return userNames;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public UserName update(UserName userName) {
		LOGGER.debug("input parameters userName: [{}]", userName);
		userName = userNameRepository.save(userName);
		LOGGER.debug("Output parameter userName=[{}]", userName);
		return userName;
	}

}
