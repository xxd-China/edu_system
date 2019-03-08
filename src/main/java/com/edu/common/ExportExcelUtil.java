package com.edu.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.edu.pojo.StudentEx;


/**
 * @author xxd
 * 生产excel表格的工具类
 */
public class ExportExcelUtil {

	 List<StudentEx> list = null;
	
	 //HttpServletRequest request;
	
	 //HttpServletResponse response;

	public ExportExcelUtil(List<StudentEx> list,HttpServletRequest request,HttpServletResponse response) {
		this.list = list;
		//this.request = request;
		//this.response = response;
	}

	public  void exportExcel() throws IOException {
		// 1.在内存中创建一个excel文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 2.创建工作簿
		HSSFSheet sheet = hssfWorkbook.createSheet();
		// 3.创建标题行
		HSSFRow titlerRow = sheet.createRow(0);
		titlerRow.createCell(0).setCellValue("学号");
		titlerRow.createCell(1).setCellValue("姓名");
		titlerRow.createCell(2).setCellValue("性别");
		titlerRow.createCell(3).setCellValue("生日");
		titlerRow.createCell(4).setCellValue("入学年份");
		titlerRow.createCell(5).setCellValue("学院");

		// 4.遍历数据,创建数据行
		for (StudentEx student : list) {
			// 获取最后一行的行号
			int lastRowNum = sheet.getLastRowNum();
			HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
			dataRow.createCell(0).setCellValue(student.getUserid());
			dataRow.createCell(1).setCellValue(student.getUsername());
			dataRow.createCell(2).setCellValue(student.getSex());
			dataRow.createCell(3).setCellValue(student.getBirthyear());
			dataRow.createCell(4).setCellValue(student.getGrade());
			dataRow.createCell(5).setCellValue(student.getcollegeName());
		}
		// 5.创建文件名
		String fileName = "学生信息列表.xls"; 
		// 6.获取输出流对象
        FileOutputStream outputStream = new FileOutputStream("D:/" + fileName);
        
		// 7.写出文件,关闭流
		hssfWorkbook.write(outputStream);
		hssfWorkbook.close();
	}

}
