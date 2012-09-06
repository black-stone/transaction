package test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import test.persistence.User;
import test.persistence.UserName;
import test.service.UsefulService;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	private UsefulService usefulService;

	public void setUsefulService(UsefulService usefulService) {
		this.usefulService = usefulService;
	}

	@RequestMapping(method = RequestMethod.PUT,value="update")
	public ModelAndView update() {
		List<User> users = usefulService.doInTrancation();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(users);
		LOGGER.debug("Output parameter modelAndView=[{}]", modelAndView);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="update2")
	public ModelAndView update2() {
		List<UserName> userNames = usefulService.doInTrancation2();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(userNames);
		LOGGER.debug("Output parameter modelAndView=[{}]", modelAndView);
		return modelAndView;
	}

}
