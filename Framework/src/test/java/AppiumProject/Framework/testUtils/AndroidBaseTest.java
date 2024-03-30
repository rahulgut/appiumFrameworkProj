package AppiumProject.Framework.testUtils;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import AppiumProject.Framework.pageObjects.android.FormPage;
import AppiumProject.Framework.utils.AppiumUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	// Creating object of FormPage class
	public FormPage formPageObj;
	
	@BeforeClass(alwaysRun=true)
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
		//String ipAddress= prop.getProperty("ipAddress");
		
		// If ipAddress is provided in maven command in terminal, fetch from there or else fetch from data.properties file
		String ipAddress= System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		
		int port= Integer.parseInt(prop.getProperty("port"));
		
		// Calling startAppiumServer() method in AppiumUtils
		//service= startAppiumServer();
		
		// Sending IP adress and port as parameters
		//service= startAppiumServer("127.0.0.1", 4723);
		
		// Sending IP adress and port fetched from global properties file data.properties as parameters 
		service= startAppiumServer(ipAddress, port);
				
		// Creating object for Android driver UiAutomator2Options
		UiAutomator2Options options = new UiAutomator2Options();
		
		// Desired capabilities for Android driver UiAutomator2Options
		
		//options.setDeviceName("RahulPixel");
		
		// Fetching androidDeviceName from global properties file data.properties
		String androidDeviceName= prop.getProperty("androidDeviceName");
		options.setDeviceName(androidDeviceName);
		
		options.setChromedriverExecutable("/Users/rahul/AppiumLearning/ChromeDriver/chromedriver103");
		
		//options.setApp("/Users/rahul/AppiumLearning/Framework/src/test/java/AppiumProject/Framework/resources/ApiDemos-debug.apk");
		//options.setApp("/Users/rahul/AppiumLearning/Framework/src/test/java/AppiumProject/Framework/resources/General-Store.apk");
		
		//options.setApp("/Users/rahul/AppiumLearning/Framework/src/test/java/AppiumProject/Framework/resources/ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"/src/test/java/AppiumProject/Framework/resources/General-Store.apk");
		
		//Android Driver
		//driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		
		// Instead of providing new URL with IP address and port, pass the service (that has URL with IP adress and port) where we started the server in AppiumUtils clss
		driver = new AndroidDriver(service.getUrl(), options);
		
		//Applying implicit wait Timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		// Returning object of FormPage class, instead of creating it in test file
		formPageObj=  new FormPage(driver);
		
	}
	
//	public void longPressAction(WebElement ele) {
//		//Long click
////		driver.executeScript("mobile: longClickGesture", 
////				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), 
////						"duration",2000));
//		
//		// We can cast this driver with JavascriptExecutor. However, it works without it as well.
//		
//		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
//				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), 
//						"duration",2000));
//	}
//	
//	public void scrollToEndAction() {
//		// When you want to scroll based on the coordinates. We can scroll to the end of the page by putting do while condition
//		boolean canScrollMore;
//		
//		do {
//			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//				    "left", 100, "top", 100, "width", 200, "height", 200,
//				    "direction", "down",
//				    "percent", 3.0
//				));
//		}while(canScrollMore);
//	}
//	
//	public void scrollToTextAction(String text) {
//		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
//	}
//	
//	public void swipeAction(WebElement firstImage, String dir) {
//		// Perform slide gesture
//		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
//				"elementId", ((RemoteWebElement) firstImage).getId(),
//				"direction", dir,
//				"percent", 0.75
//		));
//	}
//	
//	public void dragDropAction(WebElement dragElement, int xCord, int yCord) {
//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//			    "elementId", ((RemoteWebElement) dragElement).getId(),
//			    "endX", xCord,
//			    "endY", yCord
//		));
//	}
//	
//	public double formatAmount(String priceStr) {
//		return Double.parseDouble(priceStr.substring(1).trim());
//	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		//Close Android driver
		//driver.quit();
				
		//Stop Server
		//service.stop();
	}

}
