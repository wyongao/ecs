package com.ecs.service.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecs.domain.Application;
import com.ecs.domain.DayStudent;
import com.ecs.domain.DayTeacher;
import com.ecs.domain.Student;
import com.ecs.domain.Teacher;
import com.ecs.service.UploadService;
@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private TypeJudgeImpl typeJudgeImpl;
	@Override
	public List<Student> studentUpload(MultipartFile file) {
		//String contentType=file.getContentType();
		
		String fileName = file.getOriginalFilename();
		
		System.out.println("----->>"+fileName);
		List<Student> studentslist=new ArrayList<Student>();
		try {
			InputStream is=file.getInputStream();
			//文件扩展名
			String prefix=fileName.substring(fileName.lastIndexOf("."));
			Workbook wb=typeJudgeImpl.createWorkbook(is, prefix);
			Sheet sheet=wb.getSheetAt(0);
			Row row =null;
			System.out.println("----->>>>>数据长度"+sheet.getPhysicalNumberOfRows());
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				Student student=new Student();
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
				//姓名1 学号2 学校3 学院4 专业5 班级6 性别7 电话8
				student.setSname(row.getCell(1).getStringCellValue());
				student.setSnum(row.getCell(2).getStringCellValue());
				student.setSchool(row.getCell(3).getStringCellValue());
				student.setCollege(row.getCell(4).getStringCellValue());
				student.setMajor(row.getCell(5).getStringCellValue());
				student.setClasses(row.getCell(6).getStringCellValue());
				student.setSex(row.getCell(7).getStringCellValue());
				student.setTel(row.getCell(8).getStringCellValue());
				System.out.println(i);
				studentslist.add(student);
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return studentslist;
	}
	/**
	 * 上传老师的信息-
	 */
	@Override
	public List<Teacher> teacherUpload(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		
		System.out.println("----->>"+fileName);
		List<Teacher> teacherslist=new ArrayList<Teacher>();
		try {
			InputStream is=file.getInputStream();
			//文件扩展名
			String prefix=fileName.substring(fileName.lastIndexOf("."));
			Workbook wb=typeJudgeImpl.createWorkbook(is, prefix);
			Sheet sheet=wb.getSheetAt(0);
			Row row =null;
			System.out.println("----->>>>>数据长度"+sheet.getPhysicalNumberOfRows());
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				Teacher teacher=new Teacher();
				//姓名
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				//职工号
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				//学校
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				//学院
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				//性别
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				//电话
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				//姓名1 职工号2 密码3 学校4 学院5  性别6 电话7 身份8
				teacher.setTname(row.getCell(1).getStringCellValue());
				teacher.setTnum(row.getCell(2).getStringCellValue());
				teacher.setPassword(row.getCell(2).getStringCellValue());
				teacher.setSchool(row.getCell(3).getStringCellValue());
				teacher.setCollege(row.getCell(4).getStringCellValue());
				teacher.setSex(row.getCell(5).getStringCellValue());
				teacher.setTel(row.getCell(6).getStringCellValue());
				//1是管理员,0是超级管理员
				teacher.setIdentify("1");
				System.out.println(i);
				teacherslist.add(teacher);
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teacherslist;
	}
	
	//导出每日学生的信息
	@Override
	public ResponseEntity<byte[]> studentDailyDataExport(HttpServletRequest request, List<DayStudent> list) throws Exception  {
		try {
		String path = request.getSession().getServletContext().getRealPath("/")+"学生每日打卡信息导出表.xlsx";
		System.out.println("---->>>>>"+path);
		//创建新的excel表
		System.out.println("创建excel");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("output");
		XSSFRow row = sheet.createRow((short) 0);
		XSSFCell cell = row.createCell((short) 0);
		CellStyle cellStyle = workbook.createCellStyle();
		//设置excel的格式
		cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //定义单元格为字符串类型
        System.out.println("------>>>>存放数据");
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell = row.createCell((short) 0);
        cell.setCellValue("序号");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 1);
        cell.setCellValue("学号");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 2);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 3);
        cell.setCellValue("学院");
        cell.setCellStyle(cellStyle);

        cell = row.createCell((short) 4);
        cell.setCellValue("专业");
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell((short) 5);
        cell.setCellValue("班级");
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell((short) 6);
        cell.setCellValue("打卡地点");
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell((short) 7);
        cell.setCellValue("日期");
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell((short) 8);
        cell.setCellValue("是否有症状");
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell((short) 9);
        cell.setCellValue("上报体温");
        cell.setCellStyle(cellStyle);
        System.out.println(list.size());
		for(int i=0;i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			DayStudent dayStudent=(DayStudent)list.get(i);
			row.createCell((short) 0).setCellValue(i+1);
			row.createCell((short) 1).setCellValue(dayStudent.getSnum());
			row.createCell((short) 2).setCellValue(dayStudent.getSname());
			row.createCell((short) 3).setCellValue(dayStudent.getCollege());
			row.createCell((short) 4).setCellValue(dayStudent.getMajor());
			row.createCell((short) 5).setCellValue(dayStudent.getClasses());
			row.createCell((short) 6).setCellValue(dayStudent.getAddr());
			row.createCell((short) 7).setCellValue(dayStudent.getDate());
			row.createCell((short) 8).setCellValue(dayStudent.getSymptom());
			row.createCell((short) 9).setCellValue(dayStudent.getTemp());
			sheet.setColumnWidth((short) 0,(short)(8*2*256));
			sheet.setColumnWidth((short) 1,(short)(8*2*256));
			sheet.setColumnWidth((short) 2,(short)(8*2*256));
			sheet.setColumnWidth((short) 3,(short)(8*2*256));
			sheet.setColumnWidth((short) 4,(short)(8*2*256));
			sheet.setColumnWidth((short) 5,(short)(8*2*256));
			sheet.setColumnWidth((short) 6,(short)(8*4*256));
			sheet.setColumnWidth((short) 7,(short)(8*2*256));
			sheet.setColumnWidth((short) 8,(short)(8*2*256));
			sheet.setColumnWidth((short) 9,(short)(8*2*256));
		} 
			FileOutputStream fOut=new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file =new File(path);
			String filename = "学生每日打卡信息导出表.xlsx";
			HttpHeaders headers =new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        System.out.println("---->>>>>>处理完毕导出");
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		
		}
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
        return null;
		
		}
	//导出出入校的信息
	@Override
	public ResponseEntity<byte[]> applicationDataExport(HttpServletRequest request, List<Application> list)
			throws Exception {
		try {
			String path = request.getSession().getServletContext().getRealPath("/")+"学生出入申请表.xlsx";
			System.out.println("---->>>>>"+path);
			//创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			//设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //定义单元格为字符串类型
	        System.out.println("------>>>>存放数据");
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell = row.createCell((short) 0);
	        cell.setCellValue("序号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 1);
	        cell.setCellValue("学号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 2);
	        cell.setCellValue("姓名");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 3);
	        cell.setCellValue("学校");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 4);
	        cell.setCellValue("学院");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 5);
	        cell.setCellValue("专业");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 6);
	        cell.setCellValue("班级");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 7);
	        cell.setCellValue("日期");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 8);
	        cell.setCellValue("出入校门");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 9);
	        cell.setCellValue("目的地");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 10);
	        cell.setCellValue("申请原因");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 11);
	        cell.setCellValue("出入状态");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 12);
	        cell.setCellValue("审核状态");
	        cell.setCellStyle(cellStyle);
	        
	        System.out.println(list.size());
			for(int i=0;i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				Application application=(Application)list.get(i);
				//序号
				row.createCell((short) 0).setCellValue(i+1);
				//学号
				row.createCell((short) 1).setCellValue(application.getSnum());
				//姓名
				row.createCell((short) 2).setCellValue(application.getSname());
				//学校
				row.createCell((short) 3).setCellValue(application.getSchool());
				//学院
				row.createCell((short) 4).setCellValue(application.getCollege());
				//专业
				row.createCell((short) 5).setCellValue(application.getMajor());
				//班级
				row.createCell((short) 6).setCellValue(application.getClasses());
				//日期
				row.createCell((short) 7).setCellValue(application.getDate());
				//出入校门
				row.createCell((short) 8).setCellValue(application.getExit());
				//目的地
				row.createCell((short) 9).setCellValue(application.getDest());
				//申请原因
				row.createCell((short) 10).setCellValue(application.getReason());
				//出入
				String inoutString=application.getInout();
				if (inoutString.equals("1")) {
					row.createCell((short) 11).setCellValue("返校");
				}else {
					row.createCell((short) 11).setCellValue("出校");
				}
				//审核状态
				String status=application.getStatus();
				if (status.equals("2")) {
					row.createCell((short) 12).setCellValue("通过");
				}else {
					if (status.equals("1")) {
						row.createCell((short) 12).setCellValue("驳回");
					}else {
							row.createCell((short) 12).setCellValue("等待审核");
					}
				}
				sheet.setColumnWidth((short) 0,(short)(8*2*256));
				sheet.setColumnWidth((short) 1,(short)(8*2*256));
				sheet.setColumnWidth((short) 2,(short)(8*2*256));
				sheet.setColumnWidth((short) 3,(short)(8*2*256));
				sheet.setColumnWidth((short) 4,(short)(8*2*256));
				sheet.setColumnWidth((short) 5,(short)(8*2*256));
				sheet.setColumnWidth((short) 6,(short)(8*4*256));
				sheet.setColumnWidth((short) 7,(short)(8*2*256));
				sheet.setColumnWidth((short) 8,(short)(8*2*256));
				sheet.setColumnWidth((short) 9,(short)(8*2*256));
				sheet.setColumnWidth((short) 10,(short)(8*2*256));
				sheet.setColumnWidth((short) 11,(short)(8*2*256));
				sheet.setColumnWidth((short) 12,(short)(8*2*256));
			} 
				FileOutputStream fOut=new FileOutputStream(path);
				workbook.write(fOut);
				workbook.close();
				fOut.flush();
				fOut.close();
				System.out.println("导出成功");
				File file =new File(path);
				String filename = "学生出入申请表.xlsx";
				HttpHeaders headers =new HttpHeaders();
				String downLoadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
				System.out.println(downLoadFileName.toString());
				headers.setContentDispositionFormData("attachment", downLoadFileName);
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        System.out.println("---->>>>>>处理完毕导出");
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			
			}
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
	        return null;
	}
	//老师打卡信息的导出
	@Override
	public ResponseEntity<byte[]> teacherDailyDataExport(HttpServletRequest request, List<DayTeacher> list)
			throws Exception {
		try {
			String path = request.getSession().getServletContext().getRealPath("/")+"教师每日打卡信息导出表.xlsx";
			System.out.println("---->>>>>"+path);
			//创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			//设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //定义单元格为字符串类型
	        System.out.println("------>>>>存放数据");
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell = row.createCell((short) 0);
	        cell.setCellValue("序号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 1);
	        cell.setCellValue("职工号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 2);
	        cell.setCellValue("姓名");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 3);
	        cell.setCellValue("学院");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 4);
	        cell.setCellValue("打卡地点");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 5);
	        cell.setCellValue("日期");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 6);
	        cell.setCellValue("是否有症状");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 7);
	        cell.setCellValue("上报体温");
	        cell.setCellStyle(cellStyle);
	        
	        System.out.println(list.size());
			for(int i=0;i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				DayTeacher dayTeacher=(DayTeacher)list.get(i);
				row.createCell((short) 0).setCellValue(i+1);
				row.createCell((short) 1).setCellValue(dayTeacher.getTnum());
				row.createCell((short) 2).setCellValue(dayTeacher.getTname());
				row.createCell((short) 3).setCellValue(dayTeacher.getCollege());
				row.createCell((short) 4).setCellValue(dayTeacher.getAddr());
				row.createCell((short) 5).setCellValue(dayTeacher.getDate());
				row.createCell((short) 6).setCellValue(dayTeacher.getTemp());
				row.createCell((short) 7).setCellValue(dayTeacher.getSymptom());
				sheet.setColumnWidth((short) 0,(short)(8*2*256));
				sheet.setColumnWidth((short) 1,(short)(8*2*256));
				sheet.setColumnWidth((short) 2,(short)(8*2*256));
				sheet.setColumnWidth((short) 3,(short)(8*2*256));
				sheet.setColumnWidth((short) 4,(short)(8*2*256));
				sheet.setColumnWidth((short) 5,(short)(8*2*256));
				sheet.setColumnWidth((short) 6,(short)(8*4*256));
				sheet.setColumnWidth((short) 7,(short)(8*2*256));
			} 
				FileOutputStream fOut=new FileOutputStream(path);
				workbook.write(fOut);
				workbook.close();
				fOut.flush();
				fOut.close();
				System.out.println("导出成功");
				File file =new File(path);
				String filename = "教师每日打卡信息导出表.xlsx";
				HttpHeaders headers =new HttpHeaders();
				String downLoadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
				System.out.println(downLoadFileName.toString());
				headers.setContentDispositionFormData("attachment", downLoadFileName);
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        System.out.println("---->>>>>>处理完毕导出");
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			
			}
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
	        return null;
		
		
	}
	//学生信息模板的导出
	@Override
	public ResponseEntity<byte[]> studentTemplateExport(HttpServletRequest request) throws Exception {
		
		try {
			String path = request.getSession().getServletContext().getRealPath("/")+"学生基本信息模板表.xlsx";
			System.out.println("---->>>>>"+path);
			//创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			//设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //定义单元格为字符串类型
	        System.out.println("------>>>>存放数据");
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell = row.createCell((short) 0);
	        cell.setCellValue("序号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 1);
	        cell.setCellValue("姓名");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 2);
	        cell.setCellValue("学号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 3);
	        cell.setCellValue("学校");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 4);
	        cell.setCellValue("学院");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 5);
	        cell.setCellValue("专业");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 6);
	        cell.setCellValue("班级");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 7);
	        cell.setCellValue("性别");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 8);
	        cell.setCellValue("联系方式");
	        cell.setCellStyle(cellStyle);
	        
	       
				FileOutputStream fOut=new FileOutputStream(path);
				workbook.write(fOut);
				workbook.close();
				fOut.flush();
				fOut.close();
				System.out.println("导出成功");
				File file =new File(path);
				String filename = "学生基本信息模板表.xlsx";
				HttpHeaders headers =new HttpHeaders();
				String downLoadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
				System.out.println(downLoadFileName.toString());
				headers.setContentDispositionFormData("attachment", downLoadFileName);
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        System.out.println("---->>>>>>处理完毕导出");
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			
			}
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
	        return null;
	}
	//教师信息模板的导出
	@Override
	public ResponseEntity<byte[]> teacherTemplateExport(HttpServletRequest request) throws Exception {
	
		try {
			String path = request.getSession().getServletContext().getRealPath("/")+"教师基本信息模板表.xlsx";
			System.out.println("---->>>>>"+path);
			//创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			//设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        //定义单元格为字符串类型
	        System.out.println("------>>>>存放数据");
	        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	        cell = row.createCell((short) 0);
	        cell.setCellValue("序号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 1);
	        cell.setCellValue("姓名");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 2);
	        cell.setCellValue("职工号");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 3);
	        cell.setCellValue("学校");
	        cell.setCellStyle(cellStyle);

	        cell = row.createCell((short) 4);
	        cell.setCellValue("学院");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 5);
	        cell.setCellValue("性别");
	        cell.setCellStyle(cellStyle);
	        
	        cell = row.createCell((short) 6);
	        cell.setCellValue("联系方式");
	        cell.setCellStyle(cellStyle);
	        
	      
	        
	       
				FileOutputStream fOut=new FileOutputStream(path);
				workbook.write(fOut);
				workbook.close();
				fOut.flush();
				fOut.close();
				System.out.println("导出成功");
				File file =new File(path);
				String filename = "教师基本信息模板表.xlsx";
				HttpHeaders headers =new HttpHeaders();
				String downLoadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
				System.out.println(downLoadFileName.toString());
				headers.setContentDispositionFormData("attachment", downLoadFileName);
		        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		        System.out.println("---->>>>>>处理完毕导出");
		        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			
			}
			catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
	        return null;
	}
}

