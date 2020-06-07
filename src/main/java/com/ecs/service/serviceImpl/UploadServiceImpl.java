package com.ecs.service.serviceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ecs.domain.Building;
import com.ecs.domain.Campus;
import com.ecs.domain.Class;
import com.ecs.domain.College;
import com.ecs.domain.DayStudent;
import com.ecs.domain.DayTeacher;
import com.ecs.domain.Major;
import com.ecs.domain.School;
import com.ecs.domain.Student;
import com.ecs.domain.Teacher;
import com.ecs.service.BuildingService;
import com.ecs.service.CampusService;
import com.ecs.service.ClassService;
import com.ecs.service.CollegeService;
import com.ecs.service.MajorService;
import com.ecs.service.SchoolService;
import com.ecs.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	private TypeJudgeImpl typeJudgeImpl;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private CollegeService collegeService;
	@Autowired
	private MajorService majorService;
	@Autowired
	private ClassService classService;
	@Autowired
	private CampusService campusService;
	@Autowired
	private BuildingService buildingService;

	@Override
	public List<Student> studentUpload(MultipartFile file) {

		String fileName = file.getOriginalFilename();
		List<Student> studentslist = new ArrayList<Student>();
		try {
			InputStream is = file.getInputStream();
			// 文件扩展名
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			Workbook wb = typeJudgeImpl.createWorkbook(is, prefix);
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			System.out.println("----->>>>>数据长度" + sheet.getPhysicalNumberOfRows());

			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j)==null){ 
						 return Collections.emptyList(); 
				}
				}
				Student student = new Student();
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
				// 姓名1 学号2 学校3 学院4 专业5 班级6 性别7 电话8
				student.setSname(row.getCell(1).getStringCellValue());
				student.setSnum(row.getCell(2).getStringCellValue());
				student.setSchool(row.getCell(3).getStringCellValue());
				student.setCollege(row.getCell(4).getStringCellValue());
				student.setMajor(row.getCell(5).getStringCellValue());
				student.setClasses(row.getCell(6).getStringCellValue());
				student.setSex(row.getCell(7).getStringCellValue());
				student.setTel(row.getCell(8).getStringCellValue());
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

		System.out.println("----->>" + fileName);
		List<Teacher> teacherslist = new ArrayList<Teacher>();
		try {
			InputStream is = file.getInputStream();
			// 文件扩展名
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			Workbook wb = typeJudgeImpl.createWorkbook(is, prefix);
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			System.out.println("----->>>>>数据长度" + sheet.getPhysicalNumberOfRows());
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j)==null){ 
						 return Collections.emptyList(); 
				}
				}
				System.out.println("导入的文件是否为空++++++" + row);
				Teacher teacher = new Teacher();
				// 姓名
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				// 职工号
				row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
				// 学校
				row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
				// 学院
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				// 性别
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				// 电话
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				// 姓名1 职工号2 密码3 学校4 学院5 性别6 电话7 身份8
				teacher.setTname(row.getCell(1).getStringCellValue());
				teacher.setTnum(row.getCell(2).getStringCellValue());
				teacher.setPassword(row.getCell(2).getStringCellValue());
				teacher.setSchool(row.getCell(3).getStringCellValue());
				teacher.setCollege(row.getCell(4).getStringCellValue());
				teacher.setSex(row.getCell(5).getStringCellValue());
				teacher.setTel(row.getCell(6).getStringCellValue());
				// 1是管理员,0是超级管理员
				teacher.setIdentify("1");
				System.out.println(i);
				teacherslist.add(teacher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return teacherslist;
	}

	// 导出每日学生的信息
	@Override
	public ResponseEntity<byte[]> studentDailyDataExport(HttpServletRequest request, List<DayStudent> list)
			throws Exception {
		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "学生每日打卡信息导出表.xlsx";
			System.out.println("---->>>>>" + path);
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("学生每日打卡信息表");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
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
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				DayStudent dayStudent = (DayStudent) list.get(i);
				row.createCell((short) 0).setCellValue(i + 1);
				row.createCell((short) 1).setCellValue(dayStudent.getSnum());
				row.createCell((short) 2).setCellValue(dayStudent.getSname());
				row.createCell((short) 3).setCellValue(dayStudent.getCollege());
				row.createCell((short) 4).setCellValue(dayStudent.getMajor());
				row.createCell((short) 5).setCellValue(dayStudent.getClasses());
				row.createCell((short) 6).setCellValue(dayStudent.getAddr());
				row.createCell((short) 7).setCellValue(dayStudent.getDate());
				row.createCell((short) 8).setCellValue(dayStudent.getSymptom());
				row.createCell((short) 9).setCellValue(dayStudent.getTemp());
				sheet.setColumnWidth((short) 0, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 1, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 3, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 4, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 5, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 6, (short) (8 * 4 * 256));
				sheet.setColumnWidth((short) 7, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 8, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 9, (short) (8 * 2 * 256));
			}
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file = new File(path);
			String filename = "学生每日打卡信息导出表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("---->>>>>>处理完毕导出");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;

	}

	// 导出出入校的信息
	@Override
	public ResponseEntity<byte[]> applicationDataExport(HttpServletRequest request, List<Application> list)
			throws Exception {
		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "学生出入申请表.xlsx";
			System.out.println("---->>>>>" + path);
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
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
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				Application application = (Application) list.get(i);
				// 序号
				row.createCell((short) 0).setCellValue(i + 1);
				// 学号
				row.createCell((short) 1).setCellValue(application.getSnum());
				// 姓名
				row.createCell((short) 2).setCellValue(application.getSname());
				// 学校
				row.createCell((short) 3).setCellValue(application.getSchool());
				// 学院
				row.createCell((short) 4).setCellValue(application.getCollege());
				// 专业
				row.createCell((short) 5).setCellValue(application.getMajor());
				// 班级
				row.createCell((short) 6).setCellValue(application.getClasses());
				// 日期
				row.createCell((short) 7).setCellValue(application.getDate());
				// 出入校门
				row.createCell((short) 8).setCellValue(application.getExit());
				// 目的地
				row.createCell((short) 9).setCellValue(application.getDest());
				// 申请原因
				row.createCell((short) 10).setCellValue(application.getReason());
				// 出入
				String inoutString = application.getInout();
				if (inoutString.equals("1")) {
					row.createCell((short) 11).setCellValue("出校");
				} else {
					row.createCell((short) 11).setCellValue("返校");
				}
				// 审核状态
				String status = application.getStatus();
				if (status.equals("2")) {
					row.createCell((short) 12).setCellValue("通过");
				} else {
					if (status.equals("1")) {
						row.createCell((short) 12).setCellValue("驳回");
					} else {
						row.createCell((short) 12).setCellValue("等待审核");
					}
				}
				sheet.setColumnWidth((short) 0, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 1, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 3, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 4, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 5, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 6, (short) (8 * 4 * 256));
				sheet.setColumnWidth((short) 7, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 8, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 9, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 10, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 11, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 12, (short) (8 * 2 * 256));
			}
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file = new File(path);
			String filename = "学生出入申请表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("---->>>>>>处理完毕导出");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	// 老师打卡信息的导出
	@Override
	public ResponseEntity<byte[]> teacherDailyDataExport(HttpServletRequest request, List<DayTeacher> list)
			throws Exception {
		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "教师每日打卡信息导出表.xlsx";
			System.out.println("---->>>>>" + path);
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("output");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
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
			cell.setCellValue("上报体温");
			cell.setCellStyle(cellStyle);

			cell = row.createCell((short) 7);
			cell.setCellValue("是否有症状");
			cell.setCellStyle(cellStyle);

			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				DayTeacher dayTeacher = (DayTeacher) list.get(i);
				row.createCell((short) 0).setCellValue(i + 1);
				row.createCell((short) 1).setCellValue(dayTeacher.getTnum());
				row.createCell((short) 2).setCellValue(dayTeacher.getTname());
				row.createCell((short) 3).setCellValue(dayTeacher.getCollege());
				row.createCell((short) 4).setCellValue(dayTeacher.getAddr());
				row.createCell((short) 5).setCellValue(dayTeacher.getDate());
				row.createCell((short) 6).setCellValue(dayTeacher.getTemp());
				row.createCell((short) 7).setCellValue(dayTeacher.getSymptom());
				sheet.setColumnWidth((short) 0, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 1, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 3, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 4, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 5, (short) (8 * 2 * 256));
				sheet.setColumnWidth((short) 6, (short) (8 * 4 * 256));
				sheet.setColumnWidth((short) 7, (short) (8 * 2 * 256));
			}
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file = new File(path);
			String filename = "教师每日打卡信息导出表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("---->>>>>>处理完毕导出");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;

	}

	// 学生信息模板的导出
	@Override
	public ResponseEntity<byte[]> studentTemplateExport(HttpServletRequest request) throws Exception {

		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "学生基本信息模板表.xlsx";
			System.out.println("---->>>>>" + path);
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("学生基本信息表");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
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
			row=sheet.createRow(1);
			row.createCell((short) 0).setCellValue(1);
			row.createCell((short) 1).setCellValue("张三");
			row.createCell((short) 2).setCellValue("20171091****");
			row.createCell((short) 3).setCellValue("河南工程学校");
			row.createCell((short) 4).setCellValue("计算机学院");
			row.createCell((short) 5).setCellValue("计算机科学与技术");
			row.createCell((short) 6).setCellValue("1941");
			row.createCell((short) 7).setCellValue("男");
			row.createCell((short) 8).setCellValue("13211111111");
			sheet.setColumnWidth((short) 0, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 1, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 3, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 4, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 5, (short) (8 * 3 * 256));
			sheet.setColumnWidth((short) 6, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 7, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 8, (short) (8 * 2 * 256));
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file = new File(path);
			String filename = "学生基本信息模板表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("---->>>>>>处理完毕导出");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	// 教师信息模板的导出
	@Override
	public ResponseEntity<byte[]> teacherTemplateExport(HttpServletRequest request) throws Exception {

		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "教师基本信息模板表.xlsx";
			System.out.println("---->>>>>" + path);
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("教师基本信息表");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
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
			row=sheet.createRow(1);
			row.createCell((short) 0).setCellValue(1);
			row.createCell((short) 1).setCellValue("张三");
			row.createCell((short) 2).setCellValue("20171091****");
			row.createCell((short) 3).setCellValue("河南工程学校");
			row.createCell((short) 4).setCellValue("计算机学院");
			row.createCell((short) 5).setCellValue("男");
			row.createCell((short) 6).setCellValue("152********");
			sheet.setColumnWidth((short) 0, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 1, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 3, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 4, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 5, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 6, (short) (8 * 2 * 256));
			
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			System.out.println("导出成功");
			File file = new File(path);
			String filename = "教师基本信息模板表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			System.out.println("---->>>>>>处理完毕导出");
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	// 学校基础信息模板的导出
	@Override
	public ResponseEntity<byte[]> schoolTemplateExport(HttpServletRequest request) throws Exception {

		try {
			String path = request.getSession().getServletContext().getRealPath("/") + "学校基础信息模板表.xlsx";
			// 创建新的excel表
			System.out.println("创建excel");
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("学校教学单位信息表");
			XSSFSheet sheet1 = workbook.createSheet("学校建筑物信息表");
			XSSFRow row = sheet.createRow((short) 0);
			XSSFRow row1 = sheet1.createRow((short) 0);
			XSSFCell cell = row.createCell((short) 0);
			XSSFCell cell1 = row1.createCell((short) 0);
			CellStyle cellStyle = workbook.createCellStyle();
			// 设置excel的格式
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell = row.createCell((short) 0);
			cell.setCellValue("序号");
			cell.setCellStyle(cellStyle);

			cell = row.createCell((short) 1);
			cell.setCellValue("学校");
			cell.setCellStyle(cellStyle);

			cell = row.createCell((short) 2);
			cell.setCellValue("学院");
			cell.setCellStyle(cellStyle);

			cell = row.createCell((short) 3);
			cell.setCellValue("专业");
			cell.setCellStyle(cellStyle);

			cell = row.createCell((short) 4);
			cell.setCellValue("班级");
			cell.setCellStyle(cellStyle);
			row=sheet.createRow(1);
			row.createCell((short) 0).setCellValue(1);
			row.createCell((short) 1).setCellValue("河南工程学院");
			row.createCell((short) 2).setCellValue("计算机学院");
			row.createCell((short) 3).setCellValue("计算机科学与技术");
			row.createCell((short) 4).setCellValue("1741");
			sheet.setColumnWidth((short) 0, (short) (8 * 1 * 256));
			sheet.setColumnWidth((short) 1, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 2, (short) (8 * 2 * 256));
			sheet.setColumnWidth((short) 3, (short) (8 * 3 * 256));
			sheet.setColumnWidth((short) 4, (short) (8 * 1 * 256));
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell1 = row1.createCell((short) 0);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue("序号");
			cell1.setCellStyle(cellStyle);

			cell1 = row1.createCell((short) 1);
			cell1.setCellValue("学校");
			cell1.setCellStyle(cellStyle);

			cell1 = row1.createCell((short) 2);
			cell1.setCellValue("校区");
			cell1.setCellStyle(cellStyle);

			cell1 = row1.createCell((short) 3);
			cell1.setCellValue("建筑物");
			cell1.setCellStyle(cellStyle);
			row1=sheet1.createRow(1);
			row1.createCell((short) 0).setCellValue("1");
			row1.createCell((short) 1).setCellValue("河南工程学院");
			row1.createCell((short) 2).setCellValue("西校区");
			row1.createCell((short) 3).setCellValue("操场");
			sheet1.setColumnWidth((short) 0, (short) (8 * 1 * 256));
			sheet1.setColumnWidth((short) 1, (short) (8 * 2 * 256));
			sheet1.setColumnWidth((short) 2, (short) (8 * 2 * 256));
			sheet1.setColumnWidth((short) 3, (short) (8 * 2 * 256));
			FileOutputStream fOut = new FileOutputStream(path);
			workbook.write(fOut);
			workbook.close();
			fOut.flush();
			fOut.close();
			File file = new File(path);
			String filename = "学校基础信息模板表.xlsx";
			HttpHeaders headers = new HttpHeaders();
			String downLoadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			System.out.println(downLoadFileName.toString());
			headers.setContentDispositionFormData("attachment", downLoadFileName);
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	// 学校基本信息的上传
	@Override
	public Map<String, Object> schoolUpload(MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();
		String fileName = file.getOriginalFilename();

		try {
			InputStream is = file.getInputStream();
			// 文件扩展名
			String prefix = fileName.substring(fileName.lastIndexOf("."));
			Workbook wb = typeJudgeImpl.createWorkbook(is, prefix);
			Sheet sheet = wb.getSheetAt(0);
			Sheet sheet1 = wb.getSheetAt(1);
			Row row = null;

			Integer sheetNumberOfRows = sheet.getPhysicalNumberOfRows() - 1;
			Integer sheet1NumberOfRows = sheet1.getPhysicalNumberOfRows() - 1;
			System.out.println("----->>>>>sheet数据长度" + (sheet.getPhysicalNumberOfRows() - 1));
			System.out.println("----->>>>>sheet1数据长度" + (sheet1.getPhysicalNumberOfRows() - 1));
			if (sheetNumberOfRows == 0 && sheet1NumberOfRows == 0) {
				// 导入失败
				map.put("res", "1");
				return map;
			}
			if (sheetNumberOfRows != 0 && sheet1NumberOfRows == 0) {
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						if (row.getCell(j)==null){ 
							map.put("res", "4"); 
							return map;
					}
					}
					School school = new School();
					College college = new College();
					Major major = new Major();
					Class classes = new Class();
					// 学校
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					// 学院
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					// 专业
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					// 班级
					row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

					String schoolName = row.getCell(1).getStringCellValue();
					String collegeName = row.getCell(2).getStringCellValue();
					String majorName = row.getCell(3).getStringCellValue();
					String classesName = row.getCell(4).getStringCellValue();
					Integer schoolId;
					Integer collegeId;
					Integer majorId;

					school = schoolService.findSchoolByName(schoolName);
					if (school == null) {
						schoolService.addSchool(schoolName);
						schoolId = schoolService.findSchoolId(schoolName);
					} else {
						schoolId = school.getId();
					}

					college = collegeService.findOnlyCollege(collegeName, schoolId);
					if (college == null) {
						collegeService.addCollege(collegeName, schoolId);
						collegeId = collegeService.findCollegeId(collegeName, schoolId);
					} else {
						collegeId = college.getId();
					}

					major = majorService.findMajarByName(majorName, collegeId);
					if (major == null) {
						majorService.addMajor(majorName, collegeId);
						majorId = majorService.findMajorId(majorName, collegeId);
					} else {
						majorId = major.getId();
					}

					classes = classService.findClassByName(classesName, majorId);
					if (classes == null) {
						classService.addClass(classesName, majorId);
					}
					System.out.println(i);

				}
				// 建筑物信息表为空
				System.out.println("建筑物信息表为空");
				map.put("res", "2");
				return map;
			}
			if (sheetNumberOfRows == 0 && sheet1NumberOfRows != 0) {
				for (int i = 1; i < sheet1.getPhysicalNumberOfRows(); i++) {
					row = sheet1.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						if (row.getCell(j)==null){ 
							map.put("res", "5"); 
							return map;
					}
					}
					School school = new School();
					Campus campus = new Campus();
					Building building = new Building();
					// 学校
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					// 校区
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					// 建筑物
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);

					String schoolName = row.getCell(1).getStringCellValue();
					String campusName = row.getCell(2).getStringCellValue();
					String buildingName = row.getCell(3).getStringCellValue();

					school = schoolService.findSchoolByName(schoolName);
					// campus=campusService.
					Integer schoolId;
					Integer campusId;
					// 如果学校不存在,把学校存进去并且得到学校的ID
					school = schoolService.findSchoolByName(schoolName);
					if (school == null) {
						schoolService.addSchool(schoolName);
						schoolId = schoolService.findSchoolId(schoolName);
					} else {
						schoolId = school.getId();
					}

					campus = campusService.findOnlyCampus(campusName, schoolId);
					if (campus == null) {
						campusService.addCampus(campusName, schoolId);
						campusId = campusService.findCampusId(campusName, schoolId);
					} else {
						campusId = campus.getId();
					}

					building = buildingService.findOnlyBuilding(buildingName, campusId);
					if (building == null) {
						buildingService.addBuilding(buildingName, campusId);
					}
					System.out.println(i);

				}
				// 教学单位信息表为空
				System.out.println("教学单位信息表为空");
				map.put("res", "3");
				return map;
			}
			if (sheetNumberOfRows != 0 && sheet1NumberOfRows != 0) {
				
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					row = sheet.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
						if (row.getCell(j)==null){ 
							map.put("res", "4");
							return map;
					}
					}
					School school = new School();
					College college = new College();
					Major major = new Major();
					Class classes = new Class();
					// 学校
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					// 学院
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					// 专业
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					// 班级
					row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);

					String schoolName = row.getCell(1).getStringCellValue();
					String collegeName = row.getCell(2).getStringCellValue();
					String majorName = row.getCell(3).getStringCellValue();
					String classesName = row.getCell(4).getStringCellValue();
					Integer schoolId;
					Integer collegeId;
					Integer majorId;

					school = schoolService.findSchoolByName(schoolName);
					if (school == null) {
						schoolService.addSchool(schoolName);
						schoolId = schoolService.findSchoolId(schoolName);
					} else {
						schoolId = school.getId();
					}

					college = collegeService.findOnlyCollege(collegeName, schoolId);
					if (college == null) {
						collegeService.addCollege(collegeName, schoolId);
						collegeId = collegeService.findCollegeId(collegeName, schoolId);
					} else {
						collegeId = college.getId();
					}

					major = majorService.findMajarByName(majorName, collegeId);
					if (major == null) {
						majorService.addMajor(majorName, collegeId);
						majorId = majorService.findMajorId(majorName, collegeId);
					} else {
						majorId = major.getId();
					}

					classes = classService.findClassByName(classesName, majorId);
					if (classes == null) {
						classService.addClass(classesName, majorId);
					}
					System.out.println(i);

				}
				for (int i = 1; i < sheet1.getPhysicalNumberOfRows(); i++) {
					row = sheet1.getRow(i);
					for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j)==null){ 
							map.put("res", "5"); 
							return map;
					}
					}
					School school = new School();
					Campus campus = new Campus();
					Building building = new Building();
					// 学校
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					// 校区
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					// 建筑物
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);

					String schoolName = row.getCell(1).getStringCellValue();
					String campusName = row.getCell(2).getStringCellValue();
					String buildingName = row.getCell(3).getStringCellValue();

					school = schoolService.findSchoolByName(schoolName);
					// campus=campusService.
					Integer schoolId;
					Integer campusId;
					// 如果学校不存在,把学校存进去并且得到学校的ID
					school = schoolService.findSchoolByName(schoolName);
					if (school == null) {
						schoolService.addSchool(schoolName);
						schoolId = schoolService.findSchoolId(schoolName);
					} else {
						schoolId = school.getId();
					}

					campus = campusService.findOnlyCampus(campusName, schoolId);
					if (campus == null) {
						campusService.addCampus(campusName, schoolId);
						campusId = campusService.findCampusId(campusName, schoolId);
					} else {
						campusId = campus.getId();
					}

					building = buildingService.findOnlyBuilding(buildingName, campusId);
					if (building == null) {
						buildingService.addBuilding(buildingName, campusId);
					}
					System.out.println(i);

				}
				// 导入成功
				System.out.println("导入成功");
				map.put("res", "0");
				return map;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
