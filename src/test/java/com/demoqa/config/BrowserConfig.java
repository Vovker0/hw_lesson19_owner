package com.demoqa.config;

import org.aeonbits.owner.Config;

@Config.Sources((
        "classpath:${environment}.properties"
        ))

public interface BrowserConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("120")
    String browserVersion();

    @Key("resolution")
    @DefaultValue("1920x1080")
    String resolution();

    @Key("isRemote")
    @DefaultValue("false")
    Boolean isRemote();

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String baseUrl();

    @Key("remoteURL")
    String remoteURL();

    @Key("videoRemoteURL")
    String videoRemoteURL();
}
