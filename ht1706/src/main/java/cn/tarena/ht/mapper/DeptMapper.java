package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> findAll();  //查询全部部门信息
	
	public void deletDept(String[] deptIds); //批量删除
	
	//Mybatis提供了Map封装工具类     @Param("deptIds")String[] deptIds 自动的封装为Map
	//key:deptIds  values:String[] deptIds
	public void updateState(@Param("deptIds")String[] deptIds,@Param("state")int state);
	
	//新增部门信息
	public void saveDept(Dept dept);
	
	//根据Id查询部门信息
	public Dept findDeptById(String deptId);
	
	//查询排除自己之外的数据
	public List<Dept> findParentList(String deptId);
	
	//更新部门信息
	public void updateDept(Dept dept);
}	
