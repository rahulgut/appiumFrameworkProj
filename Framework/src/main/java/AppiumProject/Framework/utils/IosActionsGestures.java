package AppiumProject.Framework.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.ios.IOSDriver;

public class IosActionsGestures extends AppiumUtils {
	
	IOSDriver driver;
	
	public IosActionsGestures(IOSDriver driver) {
		this.driver= driver;
	}

	public void longPressAction(WebElement ele) {
		// Create map object params
		Map<String,Object> params= new HashMap<>();
		
		// Add params to the object
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		
		// Call executeScript() method by providing what needs to be performed and params as arguments
		driver.executeScript("mobile: touchAndHold", params);
	}
	
	public void launchInbuiltApp() {
		// Create map object params
		Map<String,String> params= new HashMap<>();
		
		// Add param to the object. Providing bundleId for Photos app
		params.put("bundleId", "com.apple.mobileslideshow");
		
		// Call executeScript() method by providing what needs to be performed and params as arguments. It will launch Photos app
		driver.executeScript("mobile: launchApp", params);
	}
	
	
	public void scrollToElement(WebElement ele) {
		
		// Create map object params
		Map<String,Object> params= new HashMap<>();
		
		// Add params to the object
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("direction", "down");
		
		// Call executeScript() method by providing what needs to be performed and params as arguments
		driver.executeScript("mobile: scroll", params);
	}
	
	public void swipeAction(List<WebElement> allPhotos, String dir) {
		// Create map object paramObj
		Map<String,Object> paramObj= new HashMap<String,Object>();
		
		// Add params to the object
		paramObj.put("direction", dir);
		
		// Let's perform swipes equal to the count of photos
		for(int i=0;i<allPhotos.size();i++) {
			
			// Print each photo name/ timestamp on console
			System.out.println(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name"));
			
			// Call executeScript() method by providing what needs to be performed and params as arguments
			driver.executeScript("mobile: swipe", paramObj);
		}
	}
}
