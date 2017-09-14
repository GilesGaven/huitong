package cn.tarena.ht.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Module;
import cn.tarena.ht.pojo.Role;
import cn.tarena.ht.service.ModuleService;
import cn.tarena.ht.service.RoleService;

@Controller
@RequestMapping("/sysadmin/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Role> roleList = roleService.findAll();
		model.addAttribute("roleList", roleList);
		
		//转向列表页面
		return "/sysadmin/role/jRoleList";
		
	}
	
	
	//角色删除
	@RequestMapping("/delete")
	public String toDelete(@RequestParam("roleId")String[] roleIds){
		
		roleService.deleteRole(roleIds);
		
		return "redirect:/sysadmin/role/list";
	}
	
	
	//角色新增
	@RequestMapping("/tocreate")
	public String toCreate(){
		
		//角色新增页面
		return "/sysadmin/role/jRoleCreate";
	}
	
	
	//角色保存
	@RequestMapping("/save")
	public String saveRole(Role role){
		
		roleService.saveRole(role);
		return "redirect:/sysadmin/role/list";
	}
	
	
	@RequestMapping("/toview")
	public String toView(String roleId,Model model){
		Role role = roleService.findRole(roleId);
		
		model.addAttribute("role", role);
		return "/sysadmin/role/jRoleView";
	}
	
	//跳转到修改页面
	@RequestMapping("/toupdate")
	public String toUpdate(String roleId,Model model){
		
		Role role = roleService.findRole(roleId);
		model.addAttribute("role", role);
		
		return "/sysadmin/role/jRoleUpdate";
	}
	
	//角色修改
	@RequestMapping("/update")
	public String updateRole(Role role){
		
		roleService.updateRole(role);
		
		return "redirect:/sysadmin/role/list";
	}
	
	@RequestMapping("/roleModule")
	public String roleModule(String roleId,Model model) throws JsonProcessingException{
		
		//2.根据roleId查询全部模块信息
		List<String> rModuleList = moduleService.findModuleListByRoleId(roleId);
		
		//查询全部的模块信息
		List<Module> moduleList = moduleService.findAll();
		
		//全部模块信息列表
		for (Module module : moduleList) {
			
			if(rModuleList.contains(module.getModuleId())){
				module.setChecked(true);
			}
			
		}

		//将数据转化为JSON串
		ObjectMapper objectMapper = new ObjectMapper();
		String zTreeJSON = objectMapper.writeValueAsString(moduleList);
		
		
		model.addAttribute("zTreeJSON", zTreeJSON);
		model.addAttribute("roleId", roleId);
		//跳转到模块展现页面
		return "/sysadmin/role/jRoleModule";
	}
	
	
	@RequestMapping("/saveRoleModule")
	public String saveRoleModule(String roleId,String[] moduleIds){
		
		roleService.saveRoleModules(roleId,moduleIds);
		
		//跳转到角色列表页面
		return "redirect:/sysadmin/role/list";
	}
	
	
	
	
	
	
	
	
	
	
}
