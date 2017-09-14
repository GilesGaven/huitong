package cn.tarena.ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
/*	<property name="prefix" value="/WEB-INF/pages/" />
	<property name="suffix" value=".jsp" />*/
	
	//url:home.action
	//转向欢迎页面
	@RequestMapping("/home")
	public String home(){
		//经过视图解析器拼接路径   真实路径:/WEB-INF/pages/home/fmain.jsp
		return "/home/fmain";
	}
	
	
	
	//title.action
	//转向tilte标题栏页面
	@RequestMapping("/title")
	public String title(){
		return "/home/title";
	}
	
	
	//homeLeft.action
	//转向home的左侧页面
	@RequestMapping("/homeLeft")
	public String homeLeft(){
		return "/home/left";
	}
	
	//转向home的操作页面
	@RequestMapping("/homeMain")
	public String homeMain(){
		return "/home/main";
	}
	
	//sysadminLeft.action
	@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
	
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "/sysadmin/main";
	}
	
}
