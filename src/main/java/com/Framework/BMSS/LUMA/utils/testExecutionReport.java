package com.Framework.BMSS.LUMA.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class testExecutionReport {
public static int indexSI = 1;

public static void testHTMLReport(int indexSI, String testCaseName, String testCaseStatus, String scriptName) throws IOException {
  String startDateTime = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss").format(new GregorianCalendar().getTime());
  String userDirector = System.getProperty("user.dir");
  System.out.println(userDirector);
  String resultFile = userDirector + "/src/main/resources/reports/updateReport.html";
  File file = new File(resultFile);
  System.out.println(file.exists());

  if (!file.exists()) {
    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
    BufferedWriter bw1 = new BufferedWriter(fw);
    bw1.write("<html>" + "\n");
    bw1.write("<head><title>" + "Test Update Report" + "</title" + "\n");
    bw1.write("</head>" + "\n");
    bw1.write("<body bgcolor='#a3a3a3'>" + "\n");
    bw1.write("<font face='Tahoma' size='3'>" + "\n");
    bw1.write("<h1 align='center' color='#ccff04' bgcolor='#b6570e'><u>" + "Test Update Report" + "</u></h>" + "\n");
    bw1.flush();
    bw1.close();
  }

  BufferedWriter bw2 = new BufferedWriter(new FileWriter(file, true));
  if (indexSI == 1) {
    bw2.write("<table align='center' border='0' width='90%' height='20'>");
    bw2.write("<tr><td width='90%' </td></tr>");
    bw2.write("<table align='center' border='1' width='90%' height='60'>");
    bw2.write("<tr>");
    bw2.write("<td colspan='2' align='center' bgcolor='#171717'><b><font color='#ccff04' face='Tahoma' size='3'>Script Name:&nbsp;&nbsp;&nbsp;</font></b><font bgcolor='#030303' color='#ec50b2' face='Tahoma' size='3'>" + scriptName + "</font></td>");
    bw2.write("<td colspan='1' align='left' bgcolor='#171717'><b><font bgcolor='#030303' color='#ccff04' face='Tahoma' size='3'>Start Time:&nbsp;</font></b><font color='#ec50b2' face='Tahoma' size='3'>" + startDateTime + "</font></td>");
    bw2.write("</tr>");
    bw2.write("</tr>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>SL No.</font></b></td>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>Test Case ID / Test Case Description</font></b></td>");
    bw2.write("<td bgcolor='#ccccff' align='center'><b><font color='#000000' face='Tahoma' size='3'>Result</font></b></td>");
  }
  bw2.write("<tr>" + "\n");
  bw2.write("<td bgcolor='#ffffdc' align='center'><font color='#000000' face='Tahoma' size='3'>" + indexSI + "</font></td>" + "\n");
  bw2.write("<td bgcolor='#ffffdc' align='left' valign='middle'><font color='#000000' face='Tahoma' size='3'>" + testCaseName + "</font></td>" + "\n");
  if (testCaseStatus == "Pass") {
    bw2.write("<td bgcolor='#ffffdc' align='center' valign='middle'><b><font color='#00ff00' face='Tahoma' size='3'>" + testCaseStatus + "</font></b></td>" + "\n");
  } else {
    bw2.write("<td bgcolor='#ffffdc' align='center' valign='middle'><b><font color='#ff0000' face='Tahoma' size='3'>" + testCaseStatus + "</font></b></td>" + "\n");
  }
  bw2.write("</tr>" + "\n");
  bw2.write("</body>" + "\n");
  bw2.write("</html>");
  bw2.flush();
  bw2.close();
}

public static void main(String[] args) throws IOException {
  testHTMLReport(indexSI, "Sample Test", "PASS", "Test for Update Report is working or not");
}
}
