package utilities;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class ServerManger {
    private static final Logger LOGGER = LogManager.getLogger(ServerManger.class);
    AppiumDriverLocalService server;

    public void startAppiumServer() {

        // Appium Server Config Values
        // Integer.parseInt uses to convert string value to integer
        int port_number = Integer.parseInt(ReadConfigFiles.getPropertyValues("appiumServerPort"));
        String appium_node_path = ReadConfigFiles.getPropertyValues("appiumNodePath");
        String appium_js_path = ReadConfigFiles.getPropertyValues("appiumJsPath");
        // log created for the server. we need to store log in case any error occur.
        // File.separator uses to work on Windows and Macbook
        String server_log = "logs" + File.separator + ReadConfigFiles.getPropertyValues("appiumServerLog");

        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingPort(port_number);
        serviceBuilder.usingDriverExecutable(new File(appium_node_path));
        serviceBuilder.withAppiumJS(new File(appium_js_path));
        serviceBuilder.withLogFile(new File(server_log));

        // if the server not(!) running then server will start
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        if (!server.isRunning()) {
            server.start();
        }
            // (==, !, ||) meanings are (equal, not, or)
            LOGGER.debug(" Starting Appium server..........");
            if (server == null || !server.isRunning()) {
                throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started !!!");
            }
            LOGGER.info(" Appium Server is started");

        // Clearing out console output so it will clear unwanted logins
        server.clearOutPutStreams();
    }
    public void stopAppiumServer() {
        LOGGER.debug(".........Stopping Appium Server.......");
         if (server.isRunning()) {
            server.stop();
        }
        LOGGER.info("Appium Server Stopped");
    }
}

