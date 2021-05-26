package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.testCases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BrowseIssueTest extends TestBase {
    @BeforeAll
    public static void start(){
        setup();
    }

    @BeforeEach
    public void openBrowser() throws Exception {
        launchBrowser();
        operation.login(username, password);
    }

    @Test
    public void browseIssue() throws Exception {
        operation.browseIssue("MTP-146");
    }
}
