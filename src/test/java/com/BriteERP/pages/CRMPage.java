package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CRMPage {
    public CRMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@data-menu-xmlid='sale_crm.sale_order_menu_quotations_crm']")
    public WebElement crmQuotations;

    @FindBy(xpath = "//button[contains(text(),'Action')]")
    public WebElement actionButtonForDelete;

    @FindBy(partialLinkText = "Delete")
    public WebElement deleteUnderAction;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-primary']")
    public WebElement okButtonAfterDelete;


    public void clickQuotationNumber(String... clickQuotNumber){

        for(String each : clickQuotNumber ){
            Driver.getDriver().findElement(By.xpath("//td[contains(text(),'"+each+"')]/..//input")).click();
        }

    }

    public boolean quotationNumberIsDisplayed(String deletedQuNumber){
        try {
            return Driver.getDriver().findElement(By.xpath("//td[contains(text(),'"+deletedQuNumber+"')]/..//input")).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }

    }
}
