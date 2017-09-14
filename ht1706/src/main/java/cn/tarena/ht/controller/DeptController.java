package cn.tarena.ht.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tarena.ht.pojo.Dept;
import cn.tarena.ht.service.DeptService;

@Controller
@RequestMapping("/sysadmin/dept")  //路径的拼接
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	
	@RequestMapping("/list")
	public String findAll(Model model){
		
		List<Dept> deptList = deptService.findAll();
		
		model.addAttribute("deptList", deptList);
		//跳转到部门列表页面
		return "/sysadmin/dept/jDeptList";
	}
	
	//部门删除   如果是同名提交问题,那么可以使用String 1,2,3,4,5/{1,2,3,4,5}
	//value="deptId"表示页面提交的参数名称  required=true 表示当前参数必须提交
	//defaultValue:默认值    即使没有提交参数会自动的添加默认数据
	@RequestMapping("/delete")
	public String toDelete(@RequestParam(value="deptId",required=true)String[] deptIds){
		
		deptService.deleteDept(deptIds);
		
		//删除完成之后,重定向到列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	
	//状态的启用
	@RequestMapping("/start")
	public String toStart(@RequestParam(value="deptId",required=true)String[] deptIds){
		int state = 1;  //1表示启用
		
		deptService.updateState(deptIds,state);
		
		//跳转到部门列表页面
		return "redirect:/sysadmin/dept/list";
	}

	//状态的停用
	@RequestMapping("/stop")
	public String toStop(@RequestParam(value="deptId",required=true)String[] deptIds){
		int state = 0;  //停用
		
		deptService.updateState(deptIds,state);
		
		//跳转到部门列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	//部门新增
	@RequestMapping("/tocreate")
	public String toCreate(Model model){
		
		List<Dept> parentList = deptService.findAll();
		model.addAttribute("parentList", parentList);
		return "/sysadmin/dept/jDeptCreate";
	}
	
	
	//新增部门
	@RequestMapping("/save")
	public String saveDept(Dept dept){
		
		deptService.saveDept(dept);
		
		//新增完成后,页面跳转回部门列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	//实现部门查看
	@RequestMapping("toview")
	public String toView(String deptId,Model model){
		
		Dept dept = deptService.findDeptById(deptId);
		
		model.addAttribute("dept", dept);
		return "/sysadmin/dept/jDeptView";
	}
	
	
	//跳转到部门修改页面
	@RequestMapping("/toupdate")
	public String toUpdate(@RequestParam(required=true)String deptId,Model model){
		//根据部门Id查询数据信息
		Dept dept = deptService.findDeptById(deptId);
		
		//查询上级部门列表信息其中不能包含自己(deptId);
		List<Dept> parentList = deptService.findParentList(deptId);
		
		
		model.addAttribute("dept", dept);
		model.addAttribute("parentList", parentList);
		//跳转到部门修改页面
		return "/sysadmin/dept/jDeptUpdate";
	}
	
	@RequestMapping("/update")
	public String updateDept(Dept dept){
		
		deptService.updateDept(dept);
		
		//跳转到部门列表页面
		return "redirect:/sysadmin/dept/list";
	}
	
	
	
	
	
	
}
 