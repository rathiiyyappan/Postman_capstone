import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

def excelPath = 'path/to/your/excel/file.xlsx';
def sheetName = 'Sheet1';

def workbook = WorkbookFactory.create(new File(excelPath));
def sheet = workbook.getSheet(sheetName);

def data = [:];
data.name = sheet.getRow(1).getCell(0).getStringCellValue();
data.salary = sheet.getRow(1).getCell(1).getStringCellValue();
data.age = sheet.getRow(1).getCell(2).getStringCellValue();

pm.variables.set("name", data.name);
pm.variables.set("salary", data.salary);
pm.variables.set("age", data.age);
