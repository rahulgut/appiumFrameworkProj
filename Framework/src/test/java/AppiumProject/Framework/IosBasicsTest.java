package AppiumProject.Framework;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import AppiumProject.Framework.pageObjects.ios.AlertViewsPage;
import AppiumProject.Framework.pageObjects.ios.HomePage;
import AppiumProject.Framework.testUtils.IosBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;

public class IosBasicsTest extends IosBaseTest{

	// To execute it as a TestNG test
	@Test
	public void iOSBasics() {
		// Calling configureAppium() method in BaseTest class
		//configureAppium();
		
		// Appium configuration will be called automatically because IosBaseTest class has been inherited. IosBaseTest class has iOS appium configuration and tear down methods, which gets gets called because of @BeforeClass and @AfterClass TestNG annotations.
		
		// Element locators in IOS - xpath, id, accessibiliyId, classname, iOSClassChain, iOSNsPredicateString
		
		
		// Creating object of HomePage class and passing driver as an argument
		HomePage hp= new HomePage(driver);
		
		// Creating object of HomePage class and passing driver as an argument
		AlertViewsPage avp= new AlertViewsPage(driver);
		
		
		// Click on 'Alert Views'
		driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		
		// Calling clickAlertViews() method in HomePage class
		hp.clickAlertViews();
		
		// Avoid using xpath in iOS because it is slow. xpath is based on XML and iOS native language is not designed on XML. So, there is lot of work in background for appium to scan and serialize iOS code to support XML and applying xpath to it and then deserialize XML to App source.
		//driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Text Entry']")).click();
		
		// iOSClassChain is faster than xpath
		
		// Click on 'Text Entry'
		//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label=='Text Entry'`]")).click();
		
		// Calling clickTextEntry() method in AlertViewsPage class
		avp.clickTextEntry();
		
		// Type Hello in text field on alert
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeTextField\"")).sendKeys("Hello");
		
		// Calling writeAlertText() method in HomePage class
		avp.writeAlertText("Hello Mars from Elon..");
		
		// Click OK on alert
		//driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		// Calling acceptAlert() method in AlertViewsPage class
		avp.acceptAlert();
		
		// Click on 'Confirm / Cancel'
		
		// We can locate an element using iOSNsPredicateString by aggregating multiple string matches as well
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label == 'Confirm / Cancel'")).click();
		
		// In iOSNsPredicateString, we can use regular expression BEGINSWITH as well
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label BEGINSWITH 'Confirm'")).click();
		
		// In iOSNsPredicateString, we can use regular expression BEGINSWITH along with [c] telling it is case sensitive
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label BEGINSWITH[c] 'Confirm'")).click();

		// In iOSNsPredicateString, similarly we can use regular expression ENDSWITH as well
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label ENDSWITH 'Cancel'")).click();
		
		// In iOSNsPredicateString, similarly we can use regular expression ENDSWITH along with [c] telling it is case sensitive
		//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label ENDSWITH[c] 'Cancel'")).click();

		// Calling clickConfirmCancel() method in AlertViewsPage class
		avp.clickConfirmCancel();
		
		// Locate, grab and print in console text on Action sheet
		//String textMsg= driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH 'A message'")).getText();
		
		// Calling getActionSheetMsg() method in AlertViewsPage class
		String textMsg= avp.getActionSheetMsg();
		
		System.out.println(textMsg);
		
		// Click 'Confirm' on Action sheet
		//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Confirm\"`]")).click();
		
		// Calling confirmActionSheet() method in AlertViewsPage class
		avp.confirmActionSheet();
	}
}
