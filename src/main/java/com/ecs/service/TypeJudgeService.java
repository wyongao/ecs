package com.ecs.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
/**
 * 判断导入文件的类型
 * @author xuluyang
 *
 * 2020年4月13日
 */

public interface TypeJudgeService {
	public  Workbook createWorkbook(InputStream is,String excelFileName) throws IOException;

}
