package com.codecool.jira.KDT_and_POM_Jira_tests.KDT.operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {
    Properties p = new Properties();

    public Properties getObjectRepository() throws IOException {
        InputStream stream = new FileInputStream(new File("src/test/java/com/codecool/jira/KDT_and_POM_Jira_tests/KDT/object.properties"));
        p.load(stream);
        return p;
    }
}
