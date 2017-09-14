package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tarena.ht.pojo.User;

public interface UserMapper {
	public List<User> findAll();		//查询全部用户信息
	
	//修改用户的状态   将参数自动的封装为map
	public void updateState(@Param("userIds")String[] userIds,@Param("state")int state);
	
	//删除用户信息
	public void deleteUser(String[] userIds);
	
	//新增用户
	public void saveUser(User user);
	
	//新增用户和角色信息
	@Insert("insert into role_user_p(role_id,user_id) values(#{roleId},#{userId})")
	public void saveUserRole(@Param("userId")String userId,@Param("roleId")String roleId);
	
	@Delete("delete from role_user_p where user_id = #{userId}")
	public void deleteUserRole(String userId);
	
	@Select("select role_id from role_user_p where user_id = #{userId}")
	public List<String> findRoleIdList(String userId);

	public User findUserById(String userId);
	
	//修改用户信息
	public void updateUser(User user);
	
	//通过用户名和密码查询
	public User findUserByUP(@Param("username")String userName,@Param("password")String password);
	
	
	
	
	
	
	
	
	
	
	
}
