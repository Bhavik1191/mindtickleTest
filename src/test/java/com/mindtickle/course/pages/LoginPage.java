package com.mindtickle.course.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

    private WebDriver driver;

    @FindBy(id = "loginEmail")
    private WebElement emailTf;

    @FindBy(name = "password")
    private WebElement passwordTf;

    @FindBy(xpath = "//button[@value='Sign In']")
    private WebElement signInBtn;


    public LoginPage(WebDriver driver) {
        this.driver = driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }


    public void signIn(String email, String password) {

        emailTf.sendKeys(email);
        passwordTf.sendKeys(password);
        signInBtn.click();

    }


}
