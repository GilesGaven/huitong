package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.UserInfoMapper;
import cn.tarena.ht.mapper.UserMapper;
import cn.tarena.ht.pojo.User;
import cn.tarena.ht.pojo.UserInfo;
import cn.tarena.ht.tool.MD5HashPassword;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public List<User> findAll() {
		
		return userMapper.findAll();
	}

	@Override
	public void updateState(String[] userIds, int state) {
		
		userMapper.updateState(userIds,state);
		
	}

	
	@Override
	public void deleteUser(String[] userIds) {
		
		userInfoMapper.deleteUserInfo(userIds); //关联删除先删从表
		userMapper.deleteUser(userIds);  //删除的主表信息
	}

	@Override
	public List<UserInfo> findParentList() {
		
		return userInfoMapper.findParentList();
	}

	@Override
	public void saveUser(User user) {
		
		UserInfo info = user.getUserInfo();
		//补齐属性值
		String id = UUID.randomUUID().toString();
		user.setUserId(id);
		user.setCreateTime(new Date());
		user.setUpdateTime(user.getCreateTime());
		//将密码进行加密处理
		String md5Password = 
				MD5HashPassword.getPassword(user.getUsername(), user.getPassword());
		
		user.setPassword(md5Password);
		
		info.setUserInfoId(id);
		info.setCreateTime(user.getCreateTime());
		info.setUpdateTime(user.getUpdateTime());
		
		
		//2张表同时新增操作
		userMapper.saveUser(user);
		userInfoMapper.saveUserInfo(info);
		
	}

	@Override
	public void saveUserRole(String userId, String[] roleIds) {
		
		//为了防止重复提交问题 先删除后插入
		userMapper.deleteUserRole(userId);
		
		for (String roleId : roleIds) {
			userMapper.saveUserRole(userId,roleId);
		}
		
		
	}

	@Override
	public List<String> findRoleIdList(String userId) {
		
		return userMapper.findRoleIdList(userId);
	}

	@Override
	public User findUserById(String userId) {
		
		return userMapper.findUserById(userId);
	}

	@Override
	public void updateUser(User user) {
		//准备userInfo数据
		UserInfo userInfo = user.getUserInfo();
		userInfo.setUserInfoId(user.getUserId());
		userInfo.setUpdateTime(new Date());
		
		//准备user数据
		user.setUpdateTime(userInfo.getUpdateTime());
		
		userMapper.updateUser(user);
		userInfoMapper.updateUserInfo(userInfo);
		
	}

	@Override
	public User findUserByUP(String userName, String password) {
		
		return userMapper.findUserByUP(userName,password);
	}
	
	
	
	
	
	
	
	
	
	

}
