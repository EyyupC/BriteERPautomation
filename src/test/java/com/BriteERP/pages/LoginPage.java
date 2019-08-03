package com.BriteERP.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="login")
    public WebElement username;

    @FindBy(id="password")
    public WebElement password;

    @FindBy(css = ".btn.btn-primary")
    public WebElement loginButton;

    public void login(String user , String pass){
        username.sendKeys(user);
        password.sendKeys(pass);
        loginButton.click();
    }
}
