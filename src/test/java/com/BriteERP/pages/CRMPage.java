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

    	@FindBy(xpath = "//*[@accesskey='c']")
	public WebElement createButton;

	@FindBy(css = "[data-original-title= 'Kanban']")
	public WebElement kanbanView;

	@FindBy(css = "[data-original-title= 'List']")
	public WebElement listView;

	@FindBy(id = "#o_field_input_62")
	public WebElement OpportunityTitleInput;
	
	@FindBy(id = "#o_field_input_63")
	public WebElement customerInput;
	
	@FindBy(id = "#o_field_input_64")
	public WebElement revenueInput;
	
	@FindBy(xpath = "//*[text()='Create a Customer']")
	public WebElement createACustomerTitle;
	
	@FindBy(xpath = "//*[text()='Create a Customer']/parent::div/following::div[2]/button[1]/span")
	public WebElement createCustomerSubMenu_CreateButton;
	
	@FindBy(xpath = "//*[@data-menu='274']/span")
	public WebElement sideMenu_Pipeline;
	
	@FindBy(css = ".o_required_modifier[placeholder='e.g. Product Pricing']")
	public WebElement opportunityNameInput;
	
	@FindBy(css = ".o_field_widget>input[class='o_input']")
	public WebElement opportunityExpectedRevenueInput;
	
	@FindBy(css = ".o_form_buttons_edit>button")
	public WebElement pipelineSaveButton;
	
	@FindBy(css = ".o_form_button_edit")
	public WebElement pipelinEditButton;
	
	public WebElement getPipelineFromKanbanList(int rowNumber) {
		String locator = "[data-id='1']>div:nth-child("+ (rowNumber+1)+ ")";
		System.out.println("locator = " + locator);
		WebElement pipeline = Driver.getDriver().findElement(By.cssSelector(locator));
		return pipeline;
	}


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
