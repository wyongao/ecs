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

	// 查找所有的申请根据学院和专业(管理员)(没用了)
	@Select("select * from application where college=#{college} and major=#{major}")
	public List<Application> findAllByCollegeAndMajor(String college, String major);

	// 查找所有的申请根据学院和专业和班级(管理员)(没用了)
	@Select("select * from application where college=#{college} and major=#{major} and classes=#{classes}")
	public List<Application> findAllByCollegeAndMajorAndClasses(String college, String major, String classes);

	// 动态sql测试用
	@SelectProvider(type = ApplicationProvider.class,method = "selectWithParam")
	public List<Application> findAllApplications(@Param("college")String college, @Param("major") String major,@Param("classes") String classes);
	//根据学院查找(没用了)
	@Select("select * from application where college=#{college}")
	public List<Application> findAllByCollege(String college);

	// 修改申请状态(管理员)
	@Update("UPDATE application SET `status`=#{status} WHERE id=#{id}")
	public void updateStatus(String id, String status);
	
	//动态查询
	@SelectProvider(type = ApplicationProvider.class,method = "selectWithParam")
	public List<Application> applicationDynamic(String college,String major,String classes, String inout);
	
	//根据学院名查询出校申请或入校申请
	@Select("select * from application where `inout`=#{inout} and college=#{college}")
	public List<Application> findDataByCollege(String inout, String college);
	
	//查找出校申请
	@Select("select * from application where `inout`=#{inout}")
	public List<Application> findOutData(String inout);
	
	
	//查找入校申请
	@Select("select * from application where `inout`=#{inout}")
	public List<Application> findInData(String inout);
	
	/**
	 * 这是一个混合查询
	 * 可以根据学号进行查询(非模糊),也可以根据姓名进行模糊拆查询
	 * @param snum
	 * @param sname
	 * @return list
	 */
	@SelectProvider(type = ApplicationProvider.class,method = "fuzzyApplications")
	public List<Application> fuzzyAppliacation(String snum,String sname,String inout);
	
	// 根据学号查找查找
	@Select("select date,`exit`,reason,`status` from application where snum=#{snum}")
	public List<Application> findBySnumForwx(String snum);
}
