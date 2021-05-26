package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UIOperation {
    private static WebDriver driver;
    private static Properties allObjects;

    public UIOperation() throws IOException {
        Properties browserProps = new Properties();
        try {
            String browserConfigPath = "settings.properties";
            browserProps.load(new FileInputStream(browserConfigPath));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        String webdriverPath = browserProps.getProperty("webdriver");
        System.setProperty("webdriver.gecko.driver", webdriverPath);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        ReadObject object = new ReadObject();
        allObjects = object.getObjectRepository();
    }

    public static WebElement waitTime(By by) {
        WebDriverWait wait = new WebDriverWait(driver,4);
        WebElement seleniumlink;
        seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return seleniumlink;
    }

    public static void quitDriver() {
        driver.close();
    }

    public void click(String objectName, String objectType) throws Exception {
        waitTime((this.getObject(allObjects,objectName,objectType))).click();
    }

    public void setText(String objectName, String objectType, String value) throws Exception {
        waitTime((this.getObject(allObjects,objectName,objectType))).sendKeys(value);
    }

    public void goToUrl(String url){
        driver.get(allObjects.getProperty(url));
    }

    public String getText(String objectName, String objectType) throws Exception {
        return waitTime((this.getObject(allObjects,objectName,objectType))).getText();
    }

    public void openCreateIssueScreen(String project, String issueType, String summary) throws Exception {
        click( "createButton", "id");
        click("projectField", "id");
        setText("projectField", "id", "");
        setText("projectField", "id", project);
        click("dialogScreen", "xpath");
        click("issueType", "id");
        setText("issueType", "id", "");
        setText("issueType", "id", issueType);
        click("dialogScreen", "xpath");
        click("summary", "id");
        setText("summary", "id", summary);
    }


    /**
     * Find element BY using object type and value
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p, String objectName, String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){

            return By.xpath(p.getProperty(objectName));
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){

            return By.className(p.getProperty(objectName));

        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){

            return By.name(p.getProperty(objectName));

        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){

            return By.cssSelector(p.getProperty(objectName));

        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){

            return By.linkText(p.getProperty(objectName));

        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){

            return By.partialLinkText(p.getProperty(objectName));

        }
        //find by id
        else if(objectType.equalsIgnoreCase("ID")){
            return By.id(p.getProperty(objectName));
        }
        else
        {
            throw new Exception("Wrong object type");
        }
    }
}
