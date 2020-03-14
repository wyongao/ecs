package com.ecs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 学生的controller
 * @author xuluyang
 *
 * 2020年3月7日
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecs.constant.Constant;
import com.ecs.domain.Student;
import com.ecs.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 学生的Controller
 * @author xuluyang
 *
 * 2020年3月7日
 */
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping("/findAllStudent")
	@ResponseBody
	public String findAllStudent(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
		System.out.println("查询执行成功--------------------------->");
		//分页查询
		PageHelper.startPage(pageNum, Constant.PAGE_SIZE);
		List<Student> list=studentService.findAllStudent();
		//这里少个参数目前没有影响
		PageInfo<Student> pageInfo=new PageInfo<Student>(list);
		System.out.println("总页数"+pageInfo.getPages()+"当前页"+pageInfo.getPageNum()+"总记录数"+pageInfo.getTotal());
		return studentService.findAllStudent().toString();
	}

	@RequestMapping("/addStudent")
	@ResponseBody
	public String addStudent(Student student) {
		student.setClasses(1741);
		student.setCollege("计算机学院");
		student.setMajor("计算机科学与技术啊");
		student.setSchool("河南工程学院");
		student.setSex("男");
		student.setSname("许路洋");
		student.setSnum("201710913106");
		student.setTel("15238850050");
		System.out.println("添加成功------------------------->>>>");
		studentService.addStudent(student);
		return "添加成功";
	}

	@RequestMapping("/deleteStudentBySnum")
	@ResponseBody
	public String deleteStudent(String snum) {

		snum = "201710913105";
		studentService.deleteStudent(snum);
		System.out.println("删除成功---------------------------->>>>");
		return "删除成功";
	}
	@RequestMapping("/updateStudent")
	@ResponseBody
	public String updateStudent(Student student) {
		student.setClasses(1741);
		student.setCollege("理学院");
		student.setMajor("计算机科学与技术");
		student.setSchool("河南工程学院");
		student.setSex("男");
		student.setSname("许路洋");
		student.setSnum("201710913106");
		student.setTel("15238850050");
		System.out.println("修改成功----------------->>>");
		return "修改成功";
	}
}
