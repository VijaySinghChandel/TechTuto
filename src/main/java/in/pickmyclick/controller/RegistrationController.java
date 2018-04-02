package in.pickmyclick.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import in.pickmyclick.modal.UserDto;
import in.pickmyclick.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public ModelAndView showRegistrationForm(WebRequest request, Model model) {
	   UserDto userDto = new UserDto();
	   model.addAttribute("user", userDto);
	   ModelAndView modelAndView = new ModelAndView("signUp");
	   return modelAndView;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDTO, 
	  BindingResult result, WebRequest request, Errors errors) {
	   // UserDto registered = new UserDto();
	   /* if (!result.hasErrors()) {
	        registered = createUserAccount(accountDto, result);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    if (result.hasErrors()) {
	        return new ModelAndView("signUp", "user", accountDto);
	    } 
	    else {
	        return new ModelAndView("login", "user", accountDto);
	    }*/
	    
	    logger.info("User "+userDTO);
	    userService.save(userDTO);
	   
	    return new ModelAndView("login", "userId", userDTO.getUserId());
	   
	    //return new ModelAndView("login", "userEmail", userDTO.getEmail());
	}
}
