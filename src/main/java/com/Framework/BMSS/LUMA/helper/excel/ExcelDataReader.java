package com.Framework.BMSS.LUMA.helper.excel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {
public static void main(String[] args) {
  WebDriverManager.chromedriver().setup();
  WebDriver driver = new ChromeDriver();
  driver.get("https://www.google.com/");

  // Path to your Excel file
  String filePath = "src/main/resources/configfile/testData.xlsx";

  try {
    FileInputStream fis = new FileInputStream(filePath);

    // Create a workbook instance for XLSX file
    Workbook workbook = new XSSFWorkbook(fis);

    // Get the first sheet in the workbook
    Sheet sheet = workbook.getSheetAt(0);

    // Iterate through rows
    for (Row row : sheet) {
      // Assuming your data is in the first column
      Cell cell = row.getCell(0);
      if (cell != null) {

        String data = cell.getStringCellValue();
        System.out.println("Data from Excel: " + data);

        driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).sendKeys(data, Keys.ENTER);
        Thread.sleep(1000);
      }

    }

    // Close the workbook and file input stream
    workbook.close();
    fis.close();

  } catch (IOException e) {
    e.printStackTrace();
  } catch (InterruptedException e) {
    throw new RuntimeException(e);
  }

  driver.close();
  driver.quit();
}
}
