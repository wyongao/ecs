package com.ecs.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ecs.domain.Application;
import com.ecs.domain.DayStudent;
import com.ecs.domain.Student;
import com.ecs.domain.Teacher;

public interface UploadService {
	//上传学生的基本信息
	public List<Student> studentUpload(MultipartFile file);
	//上传老师的基本信息
	public List<Teacher> teacherUpload(MultipartFile file);
	//导出信息
	public ResponseEntity<byte []> studentDailyDataExport(HttpServletRequest request,List<DayStudent> list) throws Exception;
	//导出出入校信息
	public ResponseEntity<byte []> applicationDataExport(HttpServletRequest request,List<Application> list) throws Exception;
}
