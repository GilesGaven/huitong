package cn.tarena.ht.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptMapper deptMapper;
	
	
	public List<Dept> findAll() {
		
		return deptMapper.findAll();
	}

	
	public void deleteDept(String[] deptIds) {
		
		deptMapper.deletDept(deptIds);
	}

	public void updateState(String[] deptIds, int state) {
	
		deptMapper.updateState(deptIds,state);
		
	}

	
	public void saveDept(Dept dept) {
		
		//部门添加时间
		dept.setCreateTime(new Date());
		dept.setUpdateTime(dept.getCreateTime());
		
		deptMapper.saveDept(dept);
		
	}

	
	public Dept findDeptById(String deptId) {
		
		return deptMapper.findDeptById(deptId);
	}

	
	public List<Dept> findParentList(String deptId) {
		
		return deptMapper.findParentList(deptId);
	}

	
	public void updateDept(Dept dept) {
		dept.setUpdateTime(new Date());
		deptMapper.updateDept(dept);
	}

}
