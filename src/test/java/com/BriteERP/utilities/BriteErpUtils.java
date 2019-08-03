package com.BriteERP.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BriteErpUtils {
    //we don't want to access these variables outside
    private static String usernamelocator = "input[id=\"login\"]";
    private static String passwordLocator = "input[id=\"password\"]";
    private static String loaderMaskLocator = "//div[contains(text(),\"Loading\")]";
    private static String pageSubTitleLocator = "//body//div//div//li[text()=\"#Inbox\"]";


    /**
     * Login into vytrack application
     *
     * @param driver
     * @param username
     * @param password
     */
    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.cssSelector(usernamelocator)).sendKeys(username);
        //Keys.ENTER means click enter after entering password
        //in this way, we don't need to click login button
        driver.findElement(By.cssSelector(passwordLocator)).sendKeys(password, Keys.ENTER);
        BrowserUtils.waitPlease(3);
    }

    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param driver
     * @param tab
     * @param module
     */
    public static void navigateToModule(WebDriver driver, String tab, String module) {
        String tabLocator = "//span[contains(text(),\""+tab+"\") and contains(@class,\"oe_menu_text\")]";
        String moduleLocator = "//body//div//div//div//div//span[contains(text(),\""+module+"\")]";
//        driver.findElement(By.xpath(tabLocator)).click();
        BrowserUtils.clickWithWait(driver, By.xpath(tabLocator), 5);
//        SeleniumUtils.waitPlease(1);
        driver.findElement(By.xpath(moduleLocator)).click();

//        SeleniumUtils.clickWithWait(driver, By.xpath(moduleLocator), 5);

//        SeleniumUtils.waitPlease(2);
    }

    /**
     * This method will navigate user to the specific module in BriteERP application.
     * For example: if tab is equals to CRM, and module equals to Pipeline,
     * Then method will navigate user to this page: http://34.220.250.213/web?#view_type=kanban&model=crm.lead&menu_id=274&action=366
     *
     * @param tab
     * @param module
     */
    public static void navigateToModule(String tab, String module) {
        String tabLocator = "//span[contains(text(),\""+tab+"\") and contains(@class,\"oe_menu_text\")]";
        String moduleLocator = "//body//div//div//div//div//span[contains(text(),\""+module+"\")]";
        BrowserUtils.clickWithWait(Driver.getDriver(), By.xpath(tabLocator), 5);
        Driver.getDriver().findElement(By.xpath(moduleLocator)).click();
    }

    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     *
     * @param driver
     */
    public static void waitUntilLoaderScreenDisappear(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(loaderMaskLocator))));
        } catch (Exception e) {
            System.out.println(e + " :: Loader mask doesn't present.");
        }
    }

    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     */
    public static void waitUntilLoaderScreenDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
            wait.until(ExpectedConditions.invisibilityOf(Driver.getDriver().findElement(By.xpath(loaderMaskLocator))));
        } catch (Exception e) {
            System.out.println(e + " :: Loader mask doesn't present.");
        }
    }

    /**
     * @return page name, for example: Dashboard
     */
    public static String getPageSubTitle() {
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear(Driver.getDriver());
        return Driver.getDriver().findElement(By.cssSelector(pageSubTitleLocator)).getText();
    }
}
