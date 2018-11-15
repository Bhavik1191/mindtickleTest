package com.mindtickle.course.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class CreateNewCourse {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Create New Course')]")
    private WebElement headinTxt;

    @FindBy(id = "trngName")
    private WebElement courseNameTf;

    @FindBy(xpath = "//p")
    private WebElement courseDescriptionTxtArea;

    @FindBy(xpath = "//span[text()='Select a Series']")
    private WebElement seriesDropdown;

    @FindBy(xpath = "//div[contains(@class,'btnCreate')]")
    private WebElement createBtn;


    public CreateNewCourse(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean verifyHeading()
    {
        return headinTxt.isDisplayed();
    }

    public void enterCourseDescription(String courseDescription) throws InterruptedException {

        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));

        Iterator<WebElement> itr = iframeList.iterator();
        while(itr.hasNext()) {

            String idName = itr.next().getAttribute("id");
            if(idName.contains("tiny_mceview"))
            {
                driver.switchTo().frame(idName);
                driver.findElement(By.xpath("html/body")).click();
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body"))).sendKeys(courseDescription);
                //driver.findElement(.sendKeys(courseDescription);
                driver.switchTo().defaultContent();
                break;
            }
        }
    }

    public void enterCourseName(String courseName)
    {
        courseNameTf.sendKeys(courseName);
    }

    public void selectDropdown(String seriesName)
    {
        seriesDropdown.click();
        driver.findElement(By.xpath("//a[contains(text(),'"+seriesName+"')]")).click();
    }

    public void clickOnCreateBtn()
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(createBtn).click().perform();
    }
}
