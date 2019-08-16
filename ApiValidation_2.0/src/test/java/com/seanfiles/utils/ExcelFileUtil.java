package com.seanfiles.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

import com.seanfiles.helper.TestConfig;
import com.seanfiles.helper.TestScenario;

public class ExcelFileUtil {
	
	public static final String ExcelFileName="ExcelFileName";
	public static final String ExcelSheetName="ExcelSheetName";
	public static final String ExcelRowNum="ExcelRowNum";

	public static Map<String, String> ConvertExcelDataToMap(Map<String, String> excelDataMap) {
		String excelFileName=excelDataMap.get(ExcelFileName);
		assertTrue("No Excel File Name provided", excelFileName != null);	
		String excelFilePath=System.getProperty("user.dir").concat(TestConfig.getProperty("ACEAPI20ReqFiles").concat(excelFileName));
		String excelSheetName=excelDataMap.get(ExcelSheetName);
		assertTrue("No Excel Sheet Name provided", excelSheetName != null);	
		String excelRowNum=excelDataMap.get(ExcelRowNum);
		assertTrue("No Excel Row Number provided", excelRowNum != null);	
		TestScenario.writeToScenario("Excel File Name: "+excelFileName+ " Sheet Name: "+excelSheetName+" Row Number: "+excelRowNum);
		return ConvertExcelDataToMap(excelFilePath, excelSheetName, excelRowNum);
	}

	private static Map<String, String> ConvertExcelDataToMap(String excelFilePath, String excelSheetName,
			String excelRowNum) {
		Workbook workbook=null;
        try {
			workbook = WorkbookFactory.create(new File(excelFilePath));
		} catch (Exception e) {
		}
		assertTrue("Not able to open Excel File", workbook != null);
		Sheet sheet =workbook.getSheet(excelSheetName);
		assertTrue("Sheet name not found in the Excel file", sheet != null);
		Row headerRow=sheet.getRow(0);
		assertTrue("No header row found in the Excel file", headerRow != null);
		Row dataRow=sheet.getRow(Integer.parseInt(excelRowNum));
		assertTrue("Row "+ excelRowNum + " not found in the Excel file", dataRow != null);
		return ConvertExcelDataToMap(headerRow, dataRow);
	}

	private static Map<String, String> ConvertExcelDataToMap(Row headerRow, Row dataRow) {
		Map<String, String> dataMap=new HashMap<String, String>();
		for(int i=0;i<headerRow.getPhysicalNumberOfCells();i++) {
			String elementName=headerRow.getCell(i).getStringCellValue();
			if(elementName != null) {
				elementName=elementName.trim();
				String elementValue=getCellValueInString(dataRow.getCell(i));
				if(elementValue != null) {
					elementValue=elementValue.trim();
				}
				dataMap.put(elementName, elementValue);
			}
		}
		return dataMap;
	}
	
	private static String getCellValueInString(Cell cell) {
		String cellValueInStr=null;
		if(cell == null) {
			return cellValueInStr;
		}
	    switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_BLANK:
	        	cellValueInStr="";
	            break;
	        case Cell.CELL_TYPE_BOOLEAN:
	            cellValueInStr=Boolean.toString(cell.getBooleanCellValue());
	            break;
	        case Cell.CELL_TYPE_STRING:
	            cellValueInStr=cell.getStringCellValue();
	            break;
	        case Cell.CELL_TYPE_NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
		            cellValueInStr=cell.getDateCellValue().toString();
	                System.out.print(cell.getDateCellValue());
	            } else {
	            	Double cellVal=cell.getNumericCellValue();
	            	DecimalFormat format = new DecimalFormat("0.#");
	            	cellValueInStr=format.format(cellVal);
	            }	            
	            break;
	        case Cell.CELL_TYPE_FORMULA:
	            cellValueInStr=cell.getCellFormula().toString();
	            break;
	        default:
	        	cellValueInStr="";
	    }
	    return cellValueInStr;
	}

}
