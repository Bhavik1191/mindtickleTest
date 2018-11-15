package com.mindtickle.course;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsFunctions {


    /**
     * This function will return current system Time in "yyyy/MM/dd HH:mm:ss" format
     * @return
     */
    public String getCurrentTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     *
     * @param filePath - File location
     * @param fileName - Name of the Excel Sheet
     * @param sheetName - Name of the Sheet
     * @param rowNumber - Row Number (It will start from 0 from top to bottom)
     * @param cellNumeer - Cell Number (It will start from 0 from Left to right)
     * @return - Value from the Cell
     * @throws IOException
     */
    public String readFromExcelFile(String filePath,String fileName,String sheetName, int rowNumber, int cellNumeer) throws IOException {

        File file =    new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

            //If it is xlsx file then create object of XSSFWorkbook class

            try {
                workbook = new XSSFWorkbook(inputStream);
            }
            catch( EOFException e )
            {
                e.printStackTrace();

            }
        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of XSSFWorkbook class
            workbook = new HSSFWorkbook(inputStream);
        }

        //Read sheet inside the workbook by its name

        Sheet sheet = workbook.getSheet(sheetName);

        //Find number of rows in excel file

        Row row = sheet.getRow(rowNumber);
        Cell cell = row.getCell(cellNumeer);
        System.out.println(cell.toString());
        return cell.toString();

    }


    /**
     * This function will capture the screenshot and store in to the given location
     * @param wd - WebDriver
     * @param name - Image name
     * @return - Image location
     */
    public String takeScreenshot(WebDriver wd, String name) {
        File src= ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        try {

            String imgPath = "/target/extent/screenshots/"+name+".png";
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File(imgPath));
            return imgPath;

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

    }
}
