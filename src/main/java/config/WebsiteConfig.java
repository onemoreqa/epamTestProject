package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/websites_config.properties")
public interface WebsiteConfig extends Config {
    @Key("otus_url")
    String otusUrl();
    @Key("otus_login_page_url")
    String otusLoginPageUrl();
    @Key("otus_personal_page_url")
    String otusPersonalPageUrl();

    @Key("browser")
    String browser();
}