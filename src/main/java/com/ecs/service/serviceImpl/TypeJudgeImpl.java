package com.ecs.service.serviceImpl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ecs.service.TypeJudgeService;
@Service
public class TypeJudgeImpl implements TypeJudgeService{

	@Override
	public Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
		  if (excelFileName.endsWith(".xls")) {
	            return new HSSFWorkbook(is);
	        }else if (excelFileName.endsWith(".xlsx")) {
	            return new XSSFWorkbook(is);
	        }
		  return null;
	}

}
