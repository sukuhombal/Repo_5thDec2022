package methods;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import driver.DriverScript;

public class Datatable extends DriverScript{
	/*******************************
	 * Method Name	: getExcelData()
	 * purpose		:
	 * Author		:
	 *******************************/
	public Map<String, String> getExcelData(String sheetName, String logicalName){
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row1 = null;
		Row row2 = null;
		Cell cell1 = null;
		Cell cell2 = null;
		Map<String, String> dataMap = null;
		String strKey = null;
		String strValue = null;
		int rowNum = 0;
		int colNum = 0;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		Calendar cal = null;
		try {
			dataMap = new HashMap<String, String>();
			fin = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\UserModule.xlsx");
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			
			if(sh==null) {
				reports.writeReport(null, "Fail", "The sheetName '"+sheetName+"' doesnot exist");
				return null;
			}
			
			//Find the row number of the given logical name
			int row = sh.getPhysicalNumberOfRows();
			for(int r=0; r<row; r++) {
				row1 = sh.getRow(r);
				cell1 = row1.getCell(0);
				if(cell1.getStringCellValue().equalsIgnoreCase(logicalName)) {
					rowNum = r;
					break;
				}
			}
			
			
			if(rowNum > 0) {
				row1 = sh.getRow(0);
				row2 = sh.getRow(rowNum);
				colNum = row1.getPhysicalNumberOfCells();
				
				for(int c=0; c<colNum; c++) {
					cell1 = row1.getCell(c);
					strKey = cell1.getStringCellValue();
					
					cell2 = row2.getCell(c);
					if(cell2==null || cell2.getCellType()==CellType.BLANK) {
						strValue = "";
					}
					else if(cell2.getCellType()==CellType.BOOLEAN) {
						strValue = String.valueOf(cell2.getBooleanCellValue());
					}
					else if(cell2.getCellType()==CellType.STRING) {
						strValue = cell2.getStringCellValue();
					}
					else if(cell2.getCellType()==CellType.NUMERIC) {
						if(DateUtil.isCellDateFormatted(cell2)==true) {
							double dt = cell2.getNumericCellValue();
							cal = Calendar.getInstance();
							cal.setTime(DateUtil.getJavaDate(dt));
							
							//if date is <10, then prefix with zero
							if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
								sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
							}else {
								sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
							}
							
							
							//if month is <10, then prefix with zero
							if((cal.get(Calendar.MONTH)+1) < 10) {
								sMonth = "0" + (cal.get(Calendar.MONTH)+1);
							}else {
								sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
							}
							
							sYear = String.valueOf(cal.get(Calendar.YEAR));
							strValue = sDay + "/" + sMonth + "/" + sYear;
						}else {
							strValue = String.valueOf(cell2.getNumericCellValue());
						}
					}
					dataMap.put(strKey, strValue);
				}
				return dataMap;
			}else {
				reports.writeReport(null, "Fail", "The logical name '"+logicalName+"' doesnot exist");
				return null;
			}
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'getExcelData()' method. " + e.getMessage());
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell1 = null;
				cell2 = null;
				row1 = null;
				row2 = null;
				sh = null;
				wb.close();
				wb = null;
				dataMap = null;
			}catch(Exception e) {
				return null;
			}
		}
	}
	
	
	
	/*******************************
	 * Method Name	: getCellData()
	 * purpose		:
	 * Author		:
	 *******************************/
	public String getCellData(String filePath, String sheetName, String columnName, int rowNum) {
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		String strData = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		Calendar cal = null;
		int colNum = 0;
		try {
			fin = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeReport(null, "Fail", "The sheetName '"+sheetName+"' doesnot exist");
				return null;
			}
			
			//find the column number  based on the column name
			row = sh.getRow(0);
			int columns = row.getPhysicalNumberOfCells();
			for(int c=0; c<columns; c++) {
				cell = row.getCell(c);
				if(cell.getStringCellValue().equalsIgnoreCase(columnName)) {
					colNum = c;
					break;
				}
			}
			
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);
			if(cell==null) {
				cell = row.createCell(rowNum);
			}
			
			//format and read the cell data
			if(cell==null || cell.getCellType()==CellType.BLANK) {
				strData = "";
			}
			else if(cell.getCellType()==CellType.BOOLEAN) {
				strData = String.valueOf(cell.getBooleanCellValue());
			}
			else if(cell.getCellType()==CellType.STRING) {
				strData = cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC) {
				if(DateUtil.isCellDateFormatted(cell)==true) {
					double dt = cell.getNumericCellValue();
					cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(dt));
					
					//if date is <10, then prefix with zero
					if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
						sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
					}else {
						sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
					}
					
					
					//if month is <10, then prefix with zero
					if((cal.get(Calendar.MONTH)+1) < 10) {
						sMonth = "0" + (cal.get(Calendar.MONTH)+1);
					}else {
						sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
					}
					
					sYear = String.valueOf(cal.get(Calendar.YEAR));
					strData = sDay + "/" + sMonth + "/" + sYear;
				}else {
					strData = String.valueOf(cell.getNumericCellValue());
				}
			}
			return strData;
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'getCellData()' method. " + e.getMessage());
			return null;
		}finally
		{
			try {
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
				strData = null;
				sDay = null;
				sMonth = null;
				sYear = null;
				cal = null;
			}catch(Exception e) {
				return null;
			}
		}
	}
	
	
	
	/*******************************
	 * Method Name	: createDataProvider()
	 * purpose		:
	 * Author		:
	 *******************************/
	public Object[][] createDataProvider(String fileName, String sheetName){
		FileInputStream fin = null;
		Workbook wb = null;
		Sheet sh = null;
		Row row = null;
		Cell cell = null;
		int rowCount = 0;
		int colCount = 0;
		int executionCount = 0;
		Object data[][] = null;
		List<String> colNames = null;
		Map<String, String> cellData = null;
		String sDay = null;
		String sMonth = null;
		String sYear = null;
		Calendar cal = null;
		String strValue = null;
		int actualRows = 0;
		try {
			fin = new FileInputStream(System.getProperty("user.dir") + "\\ExecutionController\\"+fileName+".xlsx");
			wb =  new XSSFWorkbook(fin);
			sh = wb.getSheet(sheetName);
			if(sh==null) {
				reports.writeReport(null, "Fail", "The sheetName '"+sheetName+"' doesnot exist");
				return null;
			}
			
			rowCount = sh.getPhysicalNumberOfRows();
			row = sh.getRow(0);
			colCount = row.getPhysicalNumberOfCells();
			
			//loop number of rows to find the count of the test cases selected for execution
			for(int rows=0; rows<rowCount; rows++) {
				row = sh.getRow(rows);
				cell = row.getCell(4);
				if(cell.getStringCellValue().equalsIgnoreCase("Yes")) {
					executionCount++;
				}
			}
			
			data = new Object[executionCount][1];
			colNames = new ArrayList<String>();
			for(int col=0; col<colCount; col++) {
				row = sh.getRow(0);
				cell = row.getCell(col);
				colNames.add(col, cell.getStringCellValue());
			}
			
			for(int r=0; r<rowCount; r++) {
				row = sh.getRow(r);
				cell = row.getCell(4);
				if(cell.getStringCellValue().equalsIgnoreCase("Yes")) {
					cellData = new HashMap<String, String>();
					
					for(int col=0; col<colCount; col++) {
						cell = row.getCell(col);
						
						//format and read the cell data
						if(cell==null || cell.getCellType()==CellType.BLANK) {
							strValue = "";
						}
						else if(cell.getCellType()==CellType.BOOLEAN) {
							strValue = String.valueOf(cell.getBooleanCellValue());
						}
						else if(cell.getCellType()==CellType.STRING) {
							strValue = cell.getStringCellValue();
						}
						else if(cell.getCellType()==CellType.NUMERIC) {
							if(DateUtil.isCellDateFormatted(cell)==true) {
								double dt = cell.getNumericCellValue();
								cal = Calendar.getInstance();
								cal.setTime(DateUtil.getJavaDate(dt));
								
								//if date is <10, then prefix with zero
								if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
									sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
								}else {
									sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
								}
								
								
								//if month is <10, then prefix with zero
								if((cal.get(Calendar.MONTH)+1) < 10) {
									sMonth = "0" + (cal.get(Calendar.MONTH)+1);
								}else {
									sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
								}
								
								sYear = String.valueOf(cal.get(Calendar.YEAR));
								strValue = sDay + "/" + sMonth + "/" + sYear;
							}else {
								strValue = String.valueOf(cell.getNumericCellValue());
							}
						}
						cellData.put(colNames.get(col), strValue);
					}
					data[actualRows][0] = cellData;
					actualRows++;
				}
			}
			return data;
		}catch(Exception e) {
			reports.writeReport(null, "Exception", "Exception in 'createDataProvider()' method. " + e.getMessage());
			return null;
		}
		finally
		{
			try {
				fin.close();
				fin = null;
				cell = null;
				row = null;
				sh = null;
				wb.close();
				wb = null;
				colNames = null;
				cellData = null;
			}catch(Exception e) {}
		}
	}

}
