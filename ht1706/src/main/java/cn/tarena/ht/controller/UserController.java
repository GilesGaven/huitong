package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.service.DeptService;
import cn.tarena.ht.service.RoleService;
import cn.tarena.ht.service.UserService;

@Controller
@RequestMapping("/sysadmin/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private RoleService roleService;
	
	//查询用户全部信息
	@RequestMapping("/list")
	public String findAll(Model model){
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		//跳转到用户列表页面
		return "/sysadmin/user/jUserList";
	}
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 1;  //启用
		
		userService.updateState(userIds,state);
		
		//页面跳转到user列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="userId",required=true)String[] userIds){
		
		int state = 0;  //停用
		
		userService.updateState(userIds,state);
		
		//页面跳转到user列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	
	//删除用户信息
	@RequestMapping("/delete")
	public String toDelete(@RequestParam(value="userId",required=true)String[] userIds){
		
		userService.deleteUser(userIds);
		
		//删除完成之后，跳转到用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	//用户新增
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		//部门列表信息
		List<Dept> deptList = deptService.findAll();
		
		//上级领导列表
		List<UserInfo> parentList = userService.findParentList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("parentList", parentList);
		
		//跳转到用户新增页面
		return "/sysadmin/user/jUserCreate";
	}
	
	
	//保存用户信息
	@RequestMapping("/save")
	public String saveUser(User user){
		
		userService.saveUser(user);
		//跳转到用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	
	//跳转到角色分配页面
	@RequestMapping("/userRole")
	public String userRole(String userId,Model model) throws JsonProcessingException{
		
		//2-1.回显改造   根据userId查询全部的roleid
		List<String> uRoleList = userService.findRoleIdList(userId);

		
		//1.1角色列表信息  List集合 但是zTree树中有认JSON串
		List<Role> roleList = roleService.findAll();
		
		//2-2.实现角色信息的回显
		for (Role role : roleList) {
			if(uRoleList.contains(role.getRoleId())){
				role.setChecked(true);
			}
		}
		
		//1.2创建工具类对象
		ObjectMapper objectMapper = new ObjectMapper();
		
		//将list集合转化为JSON串
		String zTreeJSON = objectMapper.writeValueAsString(roleList);
		
		model.addAttribute("zTreeJSON", zTreeJSON);
		model.addAttribute("userId", userId);
		return "/sysadmin/user/jUserRole";
	}
	
	//保存用户角色信息
	@RequestMapping("/saveUserRole")
	public String saveUserRole(String userId,String[] roleIds){
		
		userService.saveUserRole(userId,roleIds);
		
		//跳转到用户列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	
	//跳转修改页面
	@RequestMapping("/toupdate")
	public String toUpdate(String userId,Model model){
		
		//查询需要修改的数据  表示当前需要修改的数据
		User user = userService.findUserById(userId);
		
		//准备部门列表信息
		List<Dept> deptList = deptService.findAll();
		
		//准备上级领导列表(包含了当前userId)
		List<UserInfo> parentList = userService.findParentList();
		
		model.addAttribute("user", user);
		model.addAttribute("deptList", deptList);
		model.addAttribute("parentList", parentList);
		return "/sysadmin/user/jUserUpdate";
	}
	
	@RequestMapping("/update")
	public String upateUser(User user){
		
		userService.updateUser(user);
		
		//页面应该跳转到列表页面
		return "redirect:/sysadmin/user/list";
	}
	
	
	
	
	
	
	
	
}
