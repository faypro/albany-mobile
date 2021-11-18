package step_definitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.CapabilitiesManger;
import utilities.ReadConfigFiles;
import utilities.ServerManger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {
    static AppiumDriver driver;
    ServerManger serverManger = new ServerManger();

    @Before
    public void initialize() throws MalformedURLException {
        serverManger.startAppiumServer();

        CapabilitiesManger caps = new CapabilitiesManger();
        URL url = new URL(ReadConfigFiles.getPropertyValues("appiumURL"));
        driver = new AndroidDriver(url, caps.getCaps());
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @After
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
        serverManger.stopAppiumServer();
    }
}

