package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;

public interface UserService {
	public List<User> findAll();
	
	//修改用户状态
	public void updateState(String[] userIds, int state);
	
	//删除用户信息
	public void deleteUser(String[] userIds);
	
	//表示查询userInfo信息
	public List<UserInfo> findParentList();
	
	//用户新增
	public void saveUser(User user);
	
	//新增用户角色信息
	public void saveUserRole(String userId, String[] roleIds);
	
	//根据userId查询roleIds
	public List<String> findRoleIdList(String userId);
	
	//根据userId查询用户数据
	public User findUserById(String userId);
	
	//更新用户表-userInfo表
	public void updateUser(User user);
	
	//根据用户名和密码查询数据
	public User findUserByUP(String userName, String password);
}
