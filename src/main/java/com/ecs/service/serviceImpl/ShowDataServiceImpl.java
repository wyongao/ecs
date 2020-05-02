package com.ecs.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecs.common.DateUtil;
import com.ecs.common.JsonUtils;
import com.ecs.domain.College;
import com.ecs.domain.Major;
import com.ecs.service.CollegeService;
import com.ecs.service.DayStudentService;
import com.ecs.service.DayTeacherService;
import com.ecs.service.MajorService;
import com.ecs.service.ShowDataService;
import com.ecs.service.StudentService;
import com.ecs.service.TeacherService;
@Service
public class ShowDataServiceImpl implements ShowDataService{
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private DayStudentService dayStudentService;
	@Autowired
	private DayTeacherService dayTeacherService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private MajorService majorService;
	@Override
	//初始化学生数据
	public Map<String, Object> initStudentData(String school, String college) {
		String date=DateUtil.getDate();
		Integer dayStudentSum=dayStudentService.countDayStudent(school,college, date);
		System.out.println("学生打卡人数预计是5个而实际是====="+dayStudentSum);
		Integer studentSum=studentService.coutStudent(school, college);
		System.out.println("college学生"+college);
		System.out.println("school"+school);
		//今日打卡的人数
		Integer studentNoNum=studentSum-dayStudentSum;
		System.out.println("学生未打卡人数预计是31个而实际是====="+studentNoNum);
		String[] name= {"未打卡人数","打卡人数"};
		String[] data= {studentNoNum.toString(),dayStudentSum.toString()};
		//查询各个学院的学生总数,还有各个学院的打卡人数
		//存放所有的学院名字
		ArrayList<String> collegeNameList=new ArrayList<String>();
		ArrayList<Integer> collegeCountStudent=new ArrayList<Integer>();
		ArrayList<Integer> collegeDayStudent=new ArrayList<Integer>();
		//得到所有的学院根据学校
		List<College> collegeList=collegeService.findCollegeByParentName(school);
		for(int i=0;i<collegeList.size();i++) {
			String collegeName=collegeList.get(i).getCollegename();
			//学院名称数组
			collegeNameList.add(collegeName);
			//学院总人数
			collegeCountStudent.add(studentService.coutStudent(school, collegeName));
			//学院打卡人数
			collegeDayStudent.add(dayStudentService.countDayStudent(school, collegeName, date));
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("data", data);
		map.put("collegeNameList", collegeNameList);
		map.put("collegeCountStudent",collegeCountStudent);
		map.put("collegeDayStudent",collegeDayStudent);
		System.out.println(JsonUtils.objectToJson(map));
		
		return map;
	}
	@Override
	//初始化老师数据
	public Map<String, Object> initTeacherData(String school, String college) {
		String date=DateUtil.getDate();
		//今日打卡的人数
		System.out.println("college老师"+college);
		Integer teacherSum=teacherService.countTeachers(school, college);
		Integer dayTeacherSum=dayTeacherService.countDayTeachers(school,college, date);
		Integer teacherNoNum=teacherSum-dayTeacherSum;
		System.out.println("教师打卡人数预计是10个而实际是====="+dayTeacherSum);
		System.out.println("教师未打卡人数预计是11个而实际是====="+teacherNoNum);
		String[] name= {"未打卡人数","打卡人数"};
		String[] data= {teacherNoNum.toString(),dayTeacherSum.toString()};
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("data", data);
		System.out.println(JsonUtils.objectToJson(map));
		return map;
	}
	
	
	@Override
	public Map<String, Object> dynamicPie(String school, String college,Integer collegeId) {
		String date=DateUtil.getDate();
		Integer teacherSum=teacherService.countTeachers(school, college);
		Integer dayTeacherSum=dayTeacherService.countDayTeachers(school,college, date);
		Integer teacherNoNum=teacherSum-dayTeacherSum;
		System.out.println("college"+college);
		Integer dayStudentSum=dayStudentService.countDayStudent(school,college, date);
		Integer studentSum=studentService.coutStudent(school, college);
		Integer studentNoNum=studentSum-dayStudentSum;
		String[] name= {"未打卡人数","打卡人数"};
		String[] data= {teacherNoNum.toString(),dayTeacherSum.toString()};
		String[] name1={"未打卡人数","打卡人数"};
		String[] data1= {studentNoNum.toString(),dayStudentSum.toString()};
		
		  ArrayList<String> majorName= new ArrayList<String>(); //专业总人数 
		  ArrayList<Integer> majorCountStudent=new ArrayList<Integer>(); 
		  ArrayList<Integer> majorDayStudent=new ArrayList<Integer>();
		 
		//这一块是根据下拉框的college去展示是所有的专业的老师学生打卡人数情况
		//先得到全部的专业
		List<Major> majorList=majorService.findAllMajorByParentId(collegeId);
		System.out.println(majorList.toString());
		//得到每个专业的名字和对应的打卡情况人数
		for (int i = 0; i < majorList.size(); i++) {
			System.out.println(majorList.get(i).getMajorname());
			System.out.println(studentService.countMajorStudent(school,majorList.get(i).getMajorname()));
			  String major=majorList.get(i).getMajorname();
			  majorName.add(major); 
			  //得到每个专业的学生总数
			  majorCountStudent.add(studentService.countMajorStudent(school, major));
			 //每个专业打卡人数的总数
			  majorDayStudent.add(dayStudentService.countDayMajorStudent(school,major, date));
			 
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("data", data);
		map.put("name1", name1);
		map.put("data1", data1);
		map.put("majorName",majorName);
		map.put("majorCountStudent",majorCountStudent);
		map.put("majorDayStudent",majorDayStudent);
		 
		return map;
	}
	

}
