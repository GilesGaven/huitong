package cn.tarena.ht.service;

import java.util.List;

import cn.tarena.ht.pojo.Dept;

public interface DeptService {
	public List<Dept> findAll();

	public void deleteDept(String[] deptId);

	public void updateState(String[] deptIds, int state);

	public void saveDept(Dept dept);

	public Dept findDeptById(String deptId);

	public List<Dept> findParentList(String deptId);

	public void updateDept(Dept dept);
}
