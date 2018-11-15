package com.mindtickle.course.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;


    @FindBy(xpath = "//div[text()='Create']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[contains(@class,'mt-available-modules')]//div[text()='Course']")
    private WebElement courseBtn;


    public HomePage(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean verifyPresentElement(String userName)
    {
        if(driver.findElement(By.xpath("//div[text()='"+userName+"']"))!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void clickOnCreate()
    {
        createBtn.click();
    }

    public void clickOnCourse()
    {
        courseBtn.click();
    }
}
