package AppiumProject.Framework.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AppiumProject.Framework.utils.IosActionsGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViewsPage extends IosActionsGestures{
	
	IOSDriver driver;
	
	public AlertViewsPage(IOSDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Click on 'Text Entry'
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label=='Text Entry'`]")).click();
	
	@iOSXCUITFindBy(iOSClassChain= "**/XCUIElementTypeStaticText[`label=='Text Entry'`]")
	private WebElement textEntryEle;
	
	// Type Hello in text field on alert
	//driver.findElement(AppiumBy.iOSNsPredicateString("type == \"XCUIElementTypeTextField\"")).sendKeys("Hello");
	
	@iOSXCUITFindBy(iOSNsPredicate= "type == \"XCUIElementTypeTextField\"")
	private WebElement alertTextField;
	
	// Click OK on alert
	//driver.findElement(AppiumBy.accessibilityId("OK")).click();
	
	@iOSXCUITFindBy(accessibility= "OK")
	private WebElement alertAccept;
	
	// Click on 'Confirm / Cancel'
	//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND label BEGINSWITH[c] 'Confirm'")).click();

	@iOSXCUITFindBy(iOSNsPredicate= "type == 'XCUIElementTypeStaticText' AND label BEGINSWITH[c] 'Confirm'")
	private WebElement confirmCancelEle;
	
	// Locate, grab and print in console text on Action sheet
	//String textMsg= driver.findElement(AppiumBy.iOSNsPredicateString("name BEGINSWITH 'A message'")).getText();
	
	@iOSXCUITFindBy(iOSNsPredicate= "name BEGINSWITH 'A message'")
	private WebElement actionSheetEle;
	
	// Click 'Confirm' on Action sheet
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Confirm\"`]")).click();
		
	@iOSXCUITFindBy(iOSClassChain= "**/XCUIElementTypeButton[`name == \"Confirm\"`]")
	private WebElement confirmAction;
	
	// Methods to perform operations on web elements
	
	public void clickTextEntry() {
		textEntryEle.click();
	}
	
	public void writeAlertText(String txt) {
		alertTextField.sendKeys(txt);
	}
	
	public void acceptAlert() {
		alertAccept.click();
	}
	
	public void clickConfirmCancel() {
		confirmCancelEle.click();
	}
	
	public String getActionSheetMsg() {
		return actionSheetEle.getText();
	}
	
	public void confirmActionSheet() {
		confirmAction.click();
	}
}
