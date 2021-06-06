package com.codecool.jira.KDT_and_POM_Jira_tests.POM.Test;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import com.codecool.jira.KDT_and_POM_Jira_tests.POM.Pages.JiraLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class TestBase {
    public static WebDriver driver;
    public static String username;
    public static String password;
    public static Properties browserProps;
    public JiraLogin login;

    public static void setup() {
        browserProps = new Properties();
        try {
            String browserConfigPath = "settings.properties";
            browserProps.load(new FileInputStream(browserConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = browserProps.getProperty("username");
        password = browserProps.getProperty("password");
    }

    public void launchBrowser(){
        if (browserProps.getProperty("browser").equals("Firefox")){
            String webdriverPath = browserProps.getProperty("firefoxDriver");
            System.setProperty("webdriver.gecko.driver", webdriverPath);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("marionette", true);
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserProps.getProperty("browser").equals("Chrome")){
            String webdriverPath = browserProps.getProperty("chromedriver");
            System.setProperty("webdriver.chrome.driver", webdriverPath);
            driver = new ChromeDriver();
        }

    }

    public void login(){
        driver.get("https://jira-auto.codecool.metastage.net");
        login = new JiraLogin(driver);
        login.login(username, password);
    }

}
