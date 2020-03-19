package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ecs.domain.Application;

/**
 * applicationDao
 * 
 * @author xuluyang
 *
 *         2020年3月9日
 */

@Mapper
public interface ApplicationDao {
	// 上报自己的申报申请
	@Insert("insert into application(snum,sname,date,`exit`,dest,reason,`inout`,`status`,school,college,major,classes) "
			+ "values(#{snum},#{sname},#{date},#{exit},#{dest},#{reason},#{inout},#{status},#{school},#{college},#{major},#{classes})")
	public void addApplicationInfo(Application application);

	// 查找所有的申请根据学院和专业(管理员)
	@Select("select * from application where college=#{college} and major=#{major}")
	public List<Application> findAllByCollegeAndMajor(String college, String major);

	// 查找所有的申请根据学院和专业和班级(管理员)
	@Select("select * from application where college=#{college} and major=#{major} and classes=#{classes}")
	public List<Application> findAllByCollegeAndMajorAndClasses(String college, String major, String classes);

	// 查找所有的申请根据学院(管理员)
	@Select("select * from application where college=#{college}")
	public List<Application> findAllByCollege(String college);

	// 修改申请状态(管理员)
	@Update("UPDATE application SET `status`=#{status} WHERE snum=#{snum} AND date=#{date}")
	public void updateStatus(String status, String snum, String date);
	
	//查找出校申请
	@Select("select * from application where `inout`=#{inout}")
	public List<Application> findOutData(String inout);
	
	
	//查找入校申请
	@Select("select * from application where `inout`=#{inout}")
	public List<Application> findInData(String inout);

}
