package test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import test.persistence.User;
import test.repository.UserRepository;

public class UserService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);
	
	private UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		List<User> users = userRepository.findAllUsers();
		LOGGER.debug("Output parameter users=[{}]", users);
		return users;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User updateInNewTransaction(final Long userId) {
		LOGGER.debug("input parameters userId: [{}]", userId);
		User user = userRepository.findOne(userId); 
		user.setPassword("aa");
		user = userRepository.save(user);
		LOGGER.debug("Output parameter user=[{}]", user);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User update(User user) {
		LOGGER.debug("input parameters user: [{}]", user);		
		user = userRepository.save(user);
		LOGGER.debug("Output parameter user=[{}]", user);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateName(Long userId, String name) {
		LOGGER.debug("input parameters userId, name: [{}], [{}]", userId, name);
		userRepository.updateName(name, userId);
		
		boolean isUpdated=true;
		LOGGER.debug("Output parameter isUpdated=[{}]", isUpdated);
		return isUpdated;
	}

}
