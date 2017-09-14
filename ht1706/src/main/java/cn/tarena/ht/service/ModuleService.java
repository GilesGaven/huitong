package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Module;

public interface ModuleService {
	
	public List<Module> findAll();

	//修改状态
	public void updateState(String[] moduleIds, int state);
	
	//保存模块信息
	public void saveModule(Module module);
	
	public List<String> findModuleListByRoleId(String roleId);
}
