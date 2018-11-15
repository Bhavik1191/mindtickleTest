package com.mindtickle.course.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class AddQuestions {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'Add Content, Questions and Polls')]")
    private WebElement addContentBtn;

    @FindBy(xpath = "//div[div[text()='Question']]//div[text()='Multiple Choice Question']")
    private WebElement mcqQuestion;

    @FindBy(xpath = "//div[@class='options']/div[1]//input[@id='testing11']")
    private WebElement option1;

    @FindBy(xpath = "//div[@class='options']/div[2]//input[@id='testing11']")
    private WebElement option2;

    @FindBy(xpath = "//div[@class='options']/div[1]/div[contains(@class,'chBoxArea')]")
    private WebElement rightAns1;

    @FindBy(xpath = "//div[@class='options']/div[2]/div[contains(@class,'chBoxArea')]")
    private WebElement rightAns2;

    @FindBy(xpath = "//div[contains(@class,'addNewTickle')]")
    private WebElement addQuestion;

    @FindBy(xpath = "//div[@mtitle='Back']")
    private WebElement backBtn;




    public AddQuestions(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnAddQuestion()
    {
        addContentBtn.click();
    }

    public void clickOnMcqQuestion()
    {
        mcqQuestion.click();
    }

    public void writeQuestion(String question) throws InterruptedException {

        Thread.sleep(2000);
        List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));

        Iterator<WebElement> itr = iframeList.iterator();
        while(itr.hasNext()) {

            String idName = itr.next().getAttribute("id");
            if(idName.contains("tiny_mceQview"))
            {
                driver.switchTo().frame(idName);
                driver.findElement(By.xpath("html/body")).click();
                WebDriverWait wait = new WebDriverWait(driver,10);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body"))).sendKeys(question);
                driver.switchTo().defaultContent();
                break;
            }
        }
    }

    public void addOption1(String option )
    {
        option1.sendKeys(option);
    }

    public void addOption2(String option)
    {
        option2.sendKeys(option);
    }

    public void makeRightAns1()
    {
        rightAns1.click();
    }

    public void makeRightAns2()
    {
        rightAns2.click();
    }

    public void addMoreQuestion()
    {
        addQuestion.click();
    }

    public void clickOnBack()
    {
        backBtn.click();
    }

}
