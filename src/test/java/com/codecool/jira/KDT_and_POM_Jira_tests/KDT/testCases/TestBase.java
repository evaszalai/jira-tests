package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.ReadObject;
import com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation.UIOperation;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static String username;
    protected static String password;
    protected static ReadObject object;
    protected static Properties allObjects;
    protected static UIOperation operation;


    @BeforeAll
    public static void setup() throws IOException {
        Properties browserProps = new Properties();
        username = browserProps.getProperty("username");
        password = browserProps.getProperty("password");
        object = new ReadObject();
        allObjects = object.getObjectRepository();
        operation = new UIOperation();
    }
}
