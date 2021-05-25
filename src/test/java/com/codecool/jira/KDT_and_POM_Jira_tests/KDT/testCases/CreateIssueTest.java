package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateIssueTest extends TestBase{

    @BeforeAll
    public static void login() throws Exception {
        setup();
        operation.goToUrl("url");
        operation.setText("username", "id", username);
        operation.setText("password", "id", password);
        operation.click("loginbutton", "id");
    }

    @Test
    public void createIssueGeneral() throws Exception {
        String project = "Main Testing Project (MTP)";
        String issueType = "Bug";
        String summary = "Test create issue";
        operation.click( "createButton", "id");
        operation.click("projectField", "id");
        operation.setText("projectField", "id", "");
        operation.setText("projectField", "id", project);
        operation.click("dialogScreen", "xpath");
        operation.click("issueType", "id");
        operation.setText("issueType", "id", "");
        operation.setText("issueType", "id", issueType);
        operation.click("dialogScreen", "xpath");
        operation.click("summary", "id");
        operation.setText("summary", "id", summary);
        operation.click("createIssueSubmit", "id");
        operation.click("createdIssueNotification", "xpath");
        Assertions.assertEquals(summary, operation.getText("summaryValue", "id"));
        Assertions.assertEquals(issueType, operation.getText("issueTypeValue", "id"));
        Assertions.assertEquals("Main Testing Project", operation.getText("projectNameValue", "id"));
    }
}
