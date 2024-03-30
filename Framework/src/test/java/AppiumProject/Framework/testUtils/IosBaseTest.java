package AppiumProject.Framework.testUtils;

import org.testng.annotations.BeforeClass;

import AppiumProject.Framework.utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IosBaseTest extends AppiumUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void configureAppium() throws URISyntaxException, IOException {
		
		// Appium server start
//		service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//						.withIPAddress("127.0.0.1").usingPort(4723).build();
//		//service.start();
		
		// Accessing global properties file
		Properties prop= new Properties();
		
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/AppiumProject/Framework/resources/data.properties");
		
		prop.load(fis);
		
		// Fetching IP address and port from global properties file data.properties
		String ipAddress= prop.getProperty("ipAddress");
		int port= Integer.parseInt(prop.getProperty("port"));
		
		// Calling startAppiumServer() method in AppiumUtils
		//service= startAppiumServer();
		
		// Sending IP adress and port as parameters
		//service= startAppiumServer("127.0.0.1", 4723);
		
		// Sending IP adress and port fetched from global properties file data.properties as parameters 
		service= startAppiumServer(ipAddress, port);
				
		// Creating object for iOS driver XCUIOptions
		XCUITestOptions options= new XCUITestOptions();
		
		// Desired capabilities for iOS driver XCUIOptions
		//options.setDeviceName("iPhone 14 Pro");
		
		// Fetching IP address and port from global properties file data.properties
		String iOSDeviceName= prop.getProperty("iOSDeviceName");
		options.setDeviceName(iOSDeviceName);
		
		// For invoking inbuilt app (like Photos), we don't need to provide the app path for apps we used for testing
		
		//options.setApp("/Users/rahul/AppiumLearning/Framework/src/test/java/AppiumProject/Framework/resources/UIKitCatalog.app");
		//options.setApp("/Users/rahul/AppiumLearning/Framework/src/test/java/AppiumProject/Framework/resources/SliderDemo.app");
		
		options.setApp(System.getProperty("user.dir")+"/src/test/java/AppiumProject/Framework/resources/UIKitCatalog.app");
		//options.setApp(System.getProperty("user.dir")+"/src/test/java/AppiumProject/Framework/resources/SliderDemo.app");
		
		options.setPlatformVersion("16.4");
		
		// Appium install WebDriver agent on iOS devices in order to access iOS apps
		// So, we are providing launch timeout, so that it can install WebDriver agent
		options.setWdaLaunchTimeout(Duration.ofSeconds(3000));
		
		//iOS Driver object creation and passing desired capabilities
		//driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		
		// Instead of providing new URL with IP address and port, pass the service (that has URL with IP adress and port) where we started the server in AppiumUtils clss
		driver = new IOSDriver(service.getUrl(), options);
		
		//Applying implicit wait Timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
//	public void longPressAction(WebElement ele) {
//		// Create map object params
//		Map<String,Object> params= new HashMap<>();
//		
//		// Add params to the object
//		params.put("element", ((RemoteWebElement) ele).getId());
//		params.put("duration", 5);
//		
//		// Call executeScript() method by providing what needs to be performed and params as arguments
//		driver.executeScript("mobile: touchAndHold", params);
//	}
//	
//	public void launchInbuiltApp() {
//		// Create map object params
//		Map<String,String> params= new HashMap<>();
//		
//		// Add param to the object. Providing bundleId for Photos app
//		params.put("bundleId", "com.apple.mobileslideshow");
//		
//		// Call executeScript() method by providing what needs to be performed and params as arguments. It will launch Photos app
//		driver.executeScript("mobile: launchApp", params);
//	}
//	
//	
//	public void scrollToElement(WebElement ele) {
//		
//		// Create map object params
//		Map<String,Object> params= new HashMap<>();
//		
//		// Add params to the object
//		params.put("element", ((RemoteWebElement)ele).getId());
//		params.put("direction", "down");
//		
//		// Call executeScript() method by providing what needs to be performed and params as arguments
//		driver.executeScript("mobile: scroll", params);
//	}
//	
//	public void swipeAction(List<WebElement> allPhotos, String dir) {
//		// Create map object paramObj
//		Map<String,Object> paramObj= new HashMap<String,Object>();
//		
//		// Add params to the object
//		paramObj.put("direction", dir);
//		
//		// Let's perform swipes equal to the count of photos
//		for(int i=0;i<allPhotos.size();i++) {
//			
//			// Print each photo name/ timestamp on console
//			System.out.println(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name"));
//			
//			// Call executeScript() method by providing what needs to be performed and params as arguments
//			driver.executeScript("mobile: swipe", paramObj);
//		}
//	}
	
	@AfterClass
	public void tearDown() {
		//Close Android driver
		//driver.quit();
				
		//Stop Server
		//service.stop();
	}
}
