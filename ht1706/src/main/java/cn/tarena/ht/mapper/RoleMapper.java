package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.tarena.ht.pojo.Role;

public interface RoleMapper {
	//@Insert("")
	//@Update("")
	//@Delete("")
	@Select(value="select * from role_p order by order_no")
	public List<Role> findAll();
	
	//批量删除
	public void deleteRole(String[] roleIds);
	
	//角色保存
	public void saveRole(Role role);
	
	//根据Id查询role信息
	@Select("select * from role_p where role_id = #{roleId}")
	public Role findRole(String roleId);
	
	//更新role信息
	public void updateRole(Role role);
	
	//保存角色和模块信息
	public void saveRoleModules(@Param("roleId")String roleId,@Param("moduleId")String moduleId);
	
	@Delete("delete from role_module_p where role_id  = #{roleId}")
	public void deleteRoleModule(String roleId);
	
	
	
	
	
	
	
	
}
