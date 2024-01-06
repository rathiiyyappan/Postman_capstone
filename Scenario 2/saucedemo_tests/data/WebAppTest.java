import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WebAppTest {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "https://www.saucedemo.com/";

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedTitle) {
        driver.get(baseUrl);

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();

      
        if (driver.getTitle().equals(expectedTitle)) {
            // Perform actions for successful login
        } else {
            // Perform actions for unsuccessful login
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
           
        }

        driver.quit();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return readTestData("path/to/your/excel/file.xlsx", "Sheet1");
    }

    private Object[][] readTestData(String excelPath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(new File(excelPath));
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    Cell cell = sheet.getRow(i).getCell(j);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                            data[i - 1][j] = null;
                    }
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
