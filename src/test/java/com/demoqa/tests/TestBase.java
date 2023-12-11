package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        String[] subString = System.getProperty("browserAndVersion", "Chrome: 100.0").split(": ");
        Configuration.browser = subString[0];
        Configuration.browserVersion = subString[1];
        Configuration.browserSize = System.getProperty("browserSize", "1280x960");
        Configuration.baseUrl = "https://" + System.getProperty("baseUrl", "demoqa.com");
        Configuration.remote = "https://" + System.getProperty("selenoidUser", "user1:1234")
                + "@" + System.getProperty("selenoidUrl", "selenoid.autotests.cloud") + "/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last_screenshot");
        Attach.pageSource();
        if (!(Configuration.browser.equalsIgnoreCase("firefox"))) {
            Attach.browserConsoleLogs(); // excluding for Firefox due to issue with logs from Firefox
        }
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}