package com.ecs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.ecs.domain.Application;
/**
 * applicationDao
 * @author xuluyang
 *
 * 2020年3月9日
 */

@Mapper
public interface ApplicationDao {
	//上报自己的申报申请
	@Insert("insert into application(snum,sname,date,`exit`,dest,reason,`inout`,`status`,school,college,major,classes) "
			+ "values(#{snum},#{sname},#{date},#{exit},#{dest},#{reason},#{inout},#{status},#{school},#{college},#{major},#{classes})")
	public void addApplicationInfo(Application application);
	
	//查找自己的申请根据学号(用户)
	//@Select("")
	
	//修改申请状态(管理员)
	@Update("UPDATE application SET `status`=#{status} WHERE snum=#{snum} AND date=#{date}")
	public void updateStatus(String status,String snum,String date);
}
