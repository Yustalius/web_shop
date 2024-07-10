package qa.guru.config;

import org.aeonbits.owner.Config;

public class PROP {

    @Config.Sources({
            "classpath:test.properties"
    })
    public interface PropInterface extends Config {

        @Key("browser")
        String getBrowser();

        @Key("browserSize")
        String getBrowserSize();

        @Key("remoteOrLocal")
        String getRemoteOrLocal();

        @Key("remoteUrl")
        String getRemoteUrl();

        @Key("baseUrl")
        String getBaseUrl();

        @Key("browserstack.user")
        String getBrowserStackUser();

        @Key("browserstack.password")
        String getBrowserStackPassword();

        @Key("androidVersion")
        String getAndroidversion();

        @Key("androidDevice")
        String getAndroidDevice();

        @Key("androidApp")
        String getAndroidApp();

        @Key("buildName")
        String getBuildName();

        @Key("mobilePlatform")
        String getMobilePlatform();

        @Key("local.url")
        String getLocalUrl();

        @Key("android.local.version")
        String getLocalAndroidVersion();

        @Key("android.local.device")
        String getLocalAndroidDevice();

        @Key("android.local.app.path")
        String getLocalAndroidAppPath();

        @Key("appPackage")
        String getLocalAndroidAppPackage();

        @Key("appActivity")
        String getLocalAndroidAppActivity();
    }
}
