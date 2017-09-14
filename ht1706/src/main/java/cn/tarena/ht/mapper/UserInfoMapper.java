package cn.tarena.ht.mapper;

import java.util.List;

import cn.tarena.ht.pojo.UserInfo;

public interface UserInfoMapper {
	
	public void deleteUserInfo(String[] userInfoIds);
	
	//查询user列表信息
	public List<UserInfo> findParentList();
	
	//新增UserInfo
	public void saveUserInfo(UserInfo info);
	
	//修改userInfo数据
	public void updateUserInfo(UserInfo userInfo);
}
