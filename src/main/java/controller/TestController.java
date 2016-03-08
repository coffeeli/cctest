package controller;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.TestDTO;
import service.TestService;
import utils.LogUtils;


@Controller  
@RequestMapping(value="test")
public class TestController {
	Log L = LogFactory.getLog(TestController.class);
	
	@Autowired
	protected TestService testService;

	@RequestMapping(value="helloworld")
    public String index_jsp(Model model){  
		L.warn("test log");
        System.out.println("success");
		return "helloworld";
    }  
	
	@RequestMapping(value="getUser",method=RequestMethod.GET)
	public ModelAndView getUser(String id){
		final TestDTO user = this.testService.getUserById(id);
		System.out.println(user.getId() + "," + user.getName() + "," + user.getPassword());
		
		return new ModelAndView("user",new HashMap<String, Object>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -1985278583628144189L;

			{
				this.put("user",user);
				this.put("test","testestestestestest");
			}
		});
	}	
	
	@RequestMapping(value="getUser2",method=RequestMethod.GET)
	public @ResponseBody TestDTO getUser2(String id){
		TestDTO user = this.testService.getUserById(id);
		System.out.println(user.getId() + "," + user.getName() + "," + user.getPassword());
		return user;
	}
	
	@RequestMapping(value="getUser3",method=RequestMethod.GET)
	public String getUser3(String id,Model model){
		TestDTO user = this.testService.getUserById(id);
		model.addAttribute("users", user);
		return "user";
	}
	
	@RequestMapping(value="saveUser",method=RequestMethod.GET)
	public ModelAndView saveUser(String name,String password){
		TestDTO user = new TestDTO();
		user.setName(name);
		user.setPassword(password);
		this.testService.saveUser(user);
		return new ModelAndView("user","user",user);
	}
	
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String test(String id){
		return "redirect:http://www.baidu.com";
	}
	
	@RequestMapping(value="getMessageBoard",method=RequestMethod.GET)
	public String messageboard(String id){
		return "test/messageboard";
	}
}
