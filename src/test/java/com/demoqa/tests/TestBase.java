package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.config.BrowserConfig;
import com.demoqa.pages.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    final static BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.resolution();
        Configuration.baseUrl = config.baseUrl();
        if (config.isRemote()) {
            Configuration.remote = config.remoteURL();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
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
        if (config.isRemote()) {
            Attach.addVideo(config.videoRemoteURL());
        }
        Selenide.closeWebDriver();
    }
}