package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        //Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1280x960";
        Configuration.baseUrl = "https://demoqa.com";
    }

}