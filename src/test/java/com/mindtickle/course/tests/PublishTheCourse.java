package com.mindtickle.course.tests;

import com.mindtickle.course.BaseClass;
import com.mindtickle.course.UtilsFunctions;
import com.mindtickle.course.pages.*;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

public class PublishTheCourse extends BaseClass {


    UtilsFunctions utilsFunctions = new UtilsFunctions();
    String screenshotPath;
    final String inputSheetPath = "src/main/resources";
    final String fileName = "InputDataSheet.xlsx";
    final String sheetName = "Sheet1";
    String email = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,2);
    String password = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,3);
    String userName = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,4);
    String courseName = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,5);
    String courseDescription = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,6);
    String courseSeries = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,7);
    String topicName = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,8);
    String question1 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,9);
    String option11 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,10);
    String option12 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,1,11);
    String question2 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,2,9);
    String option21 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,2,10);
    String option22 = utilsFunctions.readFromExcelFile(inputSheetPath,fileName,sheetName,2,11);


    /**
     * Bhavik
     *
     * Verifying All the actions given in the Email
     * Do the following actions:
     1. On the content page create a course
     2. Create a topic in the course.
     3. Edit the topic and add MCQ learning content
     4. Navigate back to course home page
     5. Publish the course
     6. On the next page invite a new learner to Course only.
     * @throws InterruptedException
     */
    @Test
    public void publishCourse() throws InterruptedException {
        //Login

        System.out.println();
        LoginPage loginPage = new LoginPage(wd);
        loginPage.signIn(email,password);

        HomePage homePage = new HomePage(wd);
        boolean result = homePage.verifyPresentElement(userName);
        if(result==false)
        {
            Assert.fail("Login Unsuccessful");
        }


        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"login"));
        test.log(LogStatus.INFO,"Login Successfully"+screenshotPath);


        homePage.clickOnCreate();
        homePage.clickOnCourse();

        CreateNewCourse createNewCourse = new CreateNewCourse(wd);
        createNewCourse.verifyHeading();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"newCourse"));
        test.log(LogStatus.INFO,"Creating new course"+screenshotPath);


        createNewCourse.enterCourseName(courseName+utilsFunctions.getCurrentTime());
        createNewCourse.enterCourseDescription(courseDescription);
        createNewCourse.selectDropdown(courseSeries);
        createNewCourse.clickOnCreateBtn();

        EditCourse editCourse = new EditCourse(wd);
        editCourse.verifyAddTopic();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"courseCreated"));
        test.log(LogStatus.INFO,"Course Successfully created "+screenshotPath);


        editCourse.clickOnAddTopic();
        editCourse.giveTopicName(topicName);
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"topicCreated"));
        test.log(LogStatus.INFO,"New Topic created and adding questions "+screenshotPath);
        editCourse.clickOnEditContent();


        AddQuestions addQuestions = new AddQuestions(wd);
        addQuestions.clickOnAddQuestion();
        addQuestions.clickOnMcqQuestion();
        addQuestions.writeQuestion(question1);
        addQuestions.addOption1(option11);
        addQuestions.addOption2(option12);
        addQuestions.makeRightAns1();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"firstQuestion"));
        test.log(LogStatus.INFO,"Created First Question "+screenshotPath);

        addQuestions.addMoreQuestion();
        addQuestions.clickOnMcqQuestion();
        addQuestions.writeQuestion(question2);
        addQuestions.addOption1(option21);
        addQuestions.addOption2(option22);
        addQuestions.makeRightAns1();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"secondQuestion"));
        test.log(LogStatus.INFO,"Created Second Question "+screenshotPath);

        addQuestions.clickOnBack();
        editCourse.clickOnPublish();
        editCourse.verifyConfirmPublish();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"publish"));
        test.log(LogStatus.INFO,"Publishing the course and Send invitation "+screenshotPath);

        editCourse.clickOnConfirmPublish();
        editCourse.verifyOkBtn();
        screenshotPath = test.addScreenCapture(utilsFunctions.takeScreenshot(wd,"publish"));
        test.log(LogStatus.INFO,"Yor Course is successfully published"+screenshotPath);

        editCourse.clickOnOkBtn();
    }

        public PublishTheCourse() throws IOException {
    }
}
