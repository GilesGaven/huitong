package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.RoleMapper;
import cn.tarena.ht.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findAll() {
		
		return roleMapper.findAll();
	}

	@Override
	public void deleteRole(String[] roleIds) {
		
		roleMapper.deleteRole(roleIds);
		
	}

	@Override
	public void saveRole(Role role) {
		//补全数据
		role.setRoleId(UUID.randomUUID().toString());
		role.setCreateTime(new Date());
		role.setUpdateTime(role.getCreateTime());
		
		roleMapper.saveRole(role);
		
	}

	@Override
	public Role findRole(String roleId) {
		
		return roleMapper.findRole(roleId);
	}

	@Override
	public void updateRole(Role role) {
		
		role.setUpdateTime(new Date());
		
		roleMapper.updateRole(role);
		
	}

	@Override
	public void saveRoleModules(String roleId, String[] moduleIds) {
		
		//防止重复提交先删除
		roleMapper.deleteRoleModule(roleId);
		
		for (String  moduleId: moduleIds) {
			roleMapper.saveRoleModules(roleId,moduleId);
		}
		
		
	}

}
