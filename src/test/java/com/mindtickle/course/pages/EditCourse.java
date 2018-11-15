package com.mindtickle.course.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.reflect.annotation.ExceptionProxy;

public class EditCourse {

    private WebDriver driver;


    @FindBy(xpath = "//div[contains(@class,'addModular')]/div[2]")
    private WebElement addTopic;

    @FindBy(xpath = "//div[contains(@class,'Name truncate')]")
    private WebElement topicName;

    @FindBy(xpath = "//div[contains(@class,'ddMenuBtn')]//li[contains(@class,'edit')]")
    private WebElement editContent;

    @FindBy(xpath = "//div[contains(@class,'ddMenuBtn')]")
    private WebElement menuBtn;

    @FindBy(xpath = "//li[contains(@class,'rename')]")
    private WebElement rename;

    @FindBy(xpath = "//div[contains(@class,'btnPublish ')]")
    private WebElement publishBtn;

    @FindBy(xpath = "//div[contains(@class,'yesBtn')]")
    private WebElement confirmPublish;

    @FindBy(xpath = "//div[contains(@class,'okbtn')]")
    private WebElement okBtn;



    public EditCourse(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnAddTopic()
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(addTopic)).click();
    }

    public void giveTopicName(String topic)
    {

        Actions actions = new Actions(driver);
        actions.moveToElement(topicName).sendKeys(topic).perform();

    }

    public void clickOnEditContent() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(menuBtn)).click();
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(editContent)).click();
        }
        catch (Exception e)
        {
            System.out.println("not from menu");
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@mtitle='Edit Content'][2]/div[2]"))).click();

        }
    }

    public void clickOnPublish()
    {
        publishBtn.click();
    }

    public void clickOnConfirmPublish()
    {
       WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.elementToBeClickable(confirmPublish)).click();
    }

    public void verifyAddTopic() {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(addTopic));
    }

    public void verifyOkBtn()
    {
        okBtn.isDisplayed();
    }

    public void clickOnOkBtn()
    {
        okBtn.click();
    }

    public void verifyConfirmPublish() {
        confirmPublish.isDisplayed();
    }
}
