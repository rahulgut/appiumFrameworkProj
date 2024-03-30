package AppiumProject.Framework.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import AppiumProject.Framework.utils.AndroidActionsGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActionsGestures{

	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
		
		// Concatenate element locator statement (For ex: driver.findElements(By.id("com.androidsample.generalstore:id/productName"))) to locate elements
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//WebElement cartPageTitle= driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartPageTitle;
	
	// Validate if cart page has product 'Jordan 6 Rings'
	//int productCountCartPage= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/productName")
	private List<WebElement> productsName;
	
	//String productNameCartPage= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
	
	//String productPriceString= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsPrice;
	
	//String totalAmountString= driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	// Click on checkbox before proceeding
	//driver.findElement(By.className("android.widget.CheckBox")).click();
	
	@AndroidFindBy(className= "android.widget.CheckBox")
	private WebElement checkboxEle;
	
	// Calling longPressAction() reusable method in BaseTest class
	//longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/termsButton")
	private WebElement termsButton;
	
	// Validate alert title
	//String alertTitle= driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/alertTitle")
	private WebElement alertTitle;
	
	// Validate alert message body
	//String alertBody= driver.findElement(By.id("android:id/message")).getText();
	
	@AndroidFindBy(id= "android:id/message")
	private WebElement alertBody;
	
	// Close the terms and conditions alert dialog 
	//driver.findElement(AppiumBy.className("android.widget.Button")).click();
	
	@AndroidFindBy(className= "android.widget.Button")
	private WebElement closeAlert;
	
	// Click on Proceed button will open browser webpage to complete the purchase. It's a Hybrid app, which means it has both native and web view.
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/btnProceed")
	private WebElement checkoutButton;
	
	
	// Methods to perform operations on web elements
	
	public int getProductsCountCartPage() {
		return productsName.size();
	}
	
	public String getProductsNameCartPageByIndex(int index) {
		return productsName.get(index).getText();
	}
	
	public double getFormattedProductsPriceCartPageByIndex(int index) {
		String priceStr= productsPrice.get(index).getText();
		return formatAmount(priceStr);
	}
	
	public double getFormattedTotalAmount() {
		String totalStr= totalAmount.getText();
		return formatAmount(totalStr);
	}
	
	public void clickCheckbox() {
		checkboxEle.click();
	}
	
	public void performLongPress() {
		longPressAction(termsButton);
	}
	
	public String getAlertTitle() {
		return alertTitle.getText();
	}
	
	public String getAlertBody() {
		return alertBody.getText();
	}
	
	public void closeAlertBox() {
		closeAlert.click();
	}
	
	public void proceedToCheckout() {
		checkoutButton.click();
	}
	
	public void applyWaitonElement() {
		waitForElementToAppear(cartPageTitle, "text", "Cart", driver);
	}
}
