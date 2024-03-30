package AppiumProject.Framework.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AppiumProject.Framework.utils.IosActionsGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends IosActionsGestures{

	IOSDriver driver;
	
	public HomePage(IOSDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Click on 'Alert Views'
	//driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	
	@iOSXCUITFindBy(accessibility= "Alert Views")
	private WebElement alertViewEle;
	
	
	// Methods to perform operations on web elements
	
	public void clickAlertViews() {
		alertViewEle.click();
	}
	
}
