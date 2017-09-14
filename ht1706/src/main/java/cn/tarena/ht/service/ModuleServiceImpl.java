package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.ModuleMapper;
import cn.tarena.ht.pojo.Module;
@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleMapper moduleMapper;
	
	@Override
	public List<Module> findAll() {

		return moduleMapper.findAll();
	}

	@Override
	public void updateState(String[] moduleIds, int state) {
		
		moduleMapper.updateState(moduleIds,state);
	}

	@Override
	public void saveModule(Module module) {
		module.setModuleId(UUID.randomUUID().toString());
		module.setCreateTime(new Date());
		module.setUpdateTime(module.getCreateTime());
		
		moduleMapper.saveModule(module);	
	}

	@Override
	public List<String> findModuleListByRoleId(String roleId) {
		
		return moduleMapper.findModuleListByRoleId(roleId);
	}

}
