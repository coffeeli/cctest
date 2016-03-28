package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.UserDTO;
import service.LoginService;

@Controller  
@RequestMapping(value="login")
public class LoginController {
	Log L = LogFactory.getLog(TestController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="pass")
    public String pass(UserDTO user,HttpServletRequest request){  
		L.info("Login in:" + user.getUsername());
		return this.loginService.login(user);
    } 
	
	@RequestMapping(value="register")
    public String register(UserDTO user){  
		L.info("User Register:" + user.getUsername());
		this.loginService.saveUser(user);
		return "helloworld";
    } 

}
