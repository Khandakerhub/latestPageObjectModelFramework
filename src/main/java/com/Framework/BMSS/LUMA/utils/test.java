package com.Framework.BMSS.LUMA.utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Objects;

public class test {

public static void main(String[] args) throws Exception {
  FileInputStream file = new FileInputStream(Objects.requireNonNull(test.class.getClassLoader().getResource("configfile/testData.xlsx")).getFile());

  XSSFWorkbook wb = new XSSFWorkbook(file);
  XSSFSheet sheet = wb.getSheet("LoginData");
  int totalRow = sheet.getLastRowNum();
  System.out.println(totalRow);
}
}



/*public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  System.out.println("Enter line number: ");
  int n = input.nextInt();
  // Print the increasing sequence
  for (int row = 1; row <= n; row++) {
    for (int col = 1; col <= row; col++) {
      System.out.print(col);
    }
    System.out.println();
  }
  // Print the decreasing sequence
  for (int row = n - 1; row >= 1; row--) {
    for (int col = 1; col <= row; col++) {
      System.out.print(col);
    }
    System.out.println();
  }
}*/

/*public static void main(String[] args) {

  Scanner input = new Scanner(System.in);
  System.out.println("Enter line number: ");
  int n = input.nextInt();

  for (int row = 1; row <= n; row++) {
    for (int col = 1; col <= row; col++) {
      System.out.print(col);
    }
    System.out.println();
  }
  for (int row = n - 1; row >= 1; row--) {
    for (int col = 1; col <= row; col++) {
      System.out.print(col);
    }
    System.out.println();
  }
}*/









