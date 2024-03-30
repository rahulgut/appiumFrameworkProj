package AppiumProject.Framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActionsGestures extends AppiumUtils {
	
	AndroidDriver driver;
	
	public AndroidActionsGestures(AndroidDriver driver) {
		this.driver= driver;
	}
	
	public void longPressAction(WebElement ele) {
		//Long click
//		driver.executeScript("mobile: longClickGesture", 
//				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), 
//						"duration",2000));
		
		// We can cast this driver with JavascriptExecutor. However, it works without it as well.
		
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), 
						"duration",2000));
	}
	
	public void scrollToEndAction() {
		// When you want to scroll based on the coordinates. We can scroll to the end of the page by putting do while condition
		boolean canScrollMore;
		
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
		}while(canScrollMore);
	}
	
	public void scrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public void swipeAction(WebElement firstImage, String dir) {
		// Perform slide gesture
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) firstImage).getId(),
				"direction", dir,
				"percent", 0.75
		));
	}
	
	public void dragDropAction(WebElement dragElement, int xCord, int yCord) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) dragElement).getId(),
			    "endX", xCord,
			    "endY", yCord
		));
	}
}
