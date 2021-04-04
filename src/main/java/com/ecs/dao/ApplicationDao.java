package com.ecs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.ecs.dao.provider.ApplicationProvider;
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
	
	//修改申请信息
	@Update("update application set snum=#{snum},sname=#{sname},school=#{school},college=#{college},major=#{major},classes=#{classes} where snum=#{snum1}")
	public void updateApplicationInfo(String snum,String sname,String school,String college,String major,String classes,String snum1);

	// 修改申请状态(管理员)
	@Update("UPDATE application SET `status`=#{status} WHERE id=#{id}")
	public void updateStatus(String id, String status);
	
	//动态查询
	@SelectProvider(type = ApplicationProvider.class,method = "selectWithParam")
	public List<Application> applicationDynamic(String school,String college,String major,String classes, String inout, String status);
		
	/**
	 * 这是一个混合查询
	 * 可以根据学号进行查询(非模糊),也可以根据姓名进行模糊拆查询
	 * @param snum
	 * @param sname
	 * @return list
	 */
	@SelectProvider(type = ApplicationProvider.class,method = "fuzzyApplications")
	public List<Application> fuzzyAppliacation(String school,String college,String snum,String sname,String inout);
	
	// 根据学号查找查找
	@Select("select date,`exit`,reason,`status` from application where snum=#{snum} order by date desc")
	public List<Application> findBySnumForwx(String snum);
	
	@Select("select * from application where snum=#{snum} and `status`=#{status}")
	public List<Application> findBySnumAndStatusForwx(String snum, String status);
	
}
