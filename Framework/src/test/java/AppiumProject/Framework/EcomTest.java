package AppiumProject.Framework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AppiumProject.Framework.pageObjects.android.CartPage;
import AppiumProject.Framework.pageObjects.android.FormPage;
import AppiumProject.Framework.pageObjects.android.ProductsDisplayPage;
import AppiumProject.Framework.testUtils.AndroidBaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EcomTest extends AndroidBaseTest {

	// To execute it as a TestNG test
	@Test
	public void fillForm() throws InterruptedException {
		
		// Creating object of FormPage class and passing driver as an argument
		//FormPage formPageObj= new FormPage(driver);
		
		// We don't need to create object here, because we have created it in AndroidBaseTest class
		
		// Creating object of ProductsDisplayPage class and passing driver as an argument
		ProductsDisplayPage pdpPageObj= new ProductsDisplayPage(driver);
		
		// Creating object of ProductsDisplayPage class and passing driver as an argument
		//CartPage cartPageObj= new CartPage(driver);
		
		// Not creating cartPageObj because returning the same from goToCart() method in ProductsDisplayPage class		
		
		// Click on static dropdown to select the country
		//driver.findElement(By.id("android:id/text1")).click();
		
		// Click on option 'Angola', which is in the view
		//driver.findElement(By.xpath("//android.widget.TextView[@text='Angola']")).click();
		
		// Scroll down to view an option 'India' in the countries list
		
		// When you exactly know where to scroll, by providing web element
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
		
		// Click on option 'India', which is now in the view after scrolling
		//driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
		
		// Calling selectCountry() method and sending country as an argument
		formPageObj.selectCountry("Argentina");
		
		// Click on 'Lets Shop' button which will validate the form
		//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		// Calling shopClick() method in FormPage class
		formPageObj.shopClick();
		
		// Grabbing toast message
		//String toastMsg= driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		
		// Calling grabToastMsg() method
		String toastMsg= formPageObj.grabToastMsg();
		
		// Validating toast message
		AssertJUnit.assertEquals(toastMsg, "Please enter your name");
		
		// Enter name in the name field
		//driver.findElement(By.className("android.widget.EditText")).sendKeys("Chameli");
		
		// Calling setNameField() method and sending name as an argument
		formPageObj.setNameField("Chappal");
		
		// To hide the keyboard, in order to proceed with elements below.
		//driver.hideKeyboard();
		
		// Select radio button female
		//driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		// Calling setGender() method and sending gender as an argument string to validate for action to be performed
		formPageObj.setGender("Female");
		
		// Click on 'Lets Shop' button which will validate and submit the form
		//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		// Calling shopClick() method
		formPageObj.shopClick();
		
		// Add 2nd product to cart
		//driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();
		
		// Calling addSecondProductToCart() method in ProductsDisplayPage class
		pdpPageObj.addSecondProductToCart();
		
		// Scroll down to view product 'Jordan 6 Rings' in the products list
		String addProduct= "Jordan 6 Rings";
		
		// When you exactly know until where to scroll, by providing web element name
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
		
		// Calling scrollToTextAction() method in AndroidBaseTest class and passing product string as a n argument
		//scrollToTextAction(addProduct);
		
		// Calling addProduct() method and sending product string as an argument
		pdpPageObj.addProduct(addProduct);
		
		// Get products count from the list
		//int productCount= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		// Calling getProductsCount() method in ProductsDisplayPage class to get products count
		int productCount= pdpPageObj.getProductsCount();
		
		// Iterate through the list to find product 'Jordan 6 Rings'
		for(int i=0;i<productCount;i++) {
			//String productName= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			// Calling fetchProductNameByIndex() method in ProductsDisplayPage class to get products name based on index value
			String productName= pdpPageObj.fetchProductNameByIndex(i);
			
			if(productName.equalsIgnoreCase(addProduct)) {
				
				// Add to cart
				//driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(i).click();
				//driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				
				// Calling addProductToCartByIndex() method in ProductsDisplayPage class to get products name based on index value
				pdpPageObj.addProductToCartByIndex(i);
			}
		}
		
		// Click on Cart icon to go to the cart page
		//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		// Calling goToCart() method in ProductsDisplayPage class through CartPage object reference
		CartPage cartPageObj= pdpPageObj.goToCart();
		
		// Applying explicit wait for the cart page to load. Not required on this machine, but issue may occur because of slowness
		
		//WebElement cartPageTitle= driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
		
		//WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Waiting until 5 seconds for the 'Cart' title to appear
		//wait.until(ExpectedConditions.attributeContains(cartPageTitle, "text", "Cart"));
		
		// Calling applyWaitonElement() function in CartPage class and passing webElement as an argument
		cartPageObj.applyWaitonElement();
		
		// Validate if cart page has product 'Jordan 6 Rings'
		//int productCountCartPage= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		int productCountCartPage= cartPageObj.getProductsCountCartPage();
		
		// Iterate through the list to find product 'Jordan 6 Rings'
		for(int i=0;i<productCountCartPage;i++) {
			
			//String productNameCartPage= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			
			// Calling getProductsNameCartPageByIndex() method in CartPage class to get products name based on index value
			String productNameCartPage= cartPageObj.getProductsNameCartPageByIndex(i);
			
			if(productNameCartPage.equalsIgnoreCase(addProduct)) {
				// Validate with assertion
				AssertJUnit.assertEquals(productNameCartPage, addProduct);
			}
		}
		
		// Calculate total price of products in the cart
		double totalPrice=0;
		for(int i=0;i<productCountCartPage;i++) {
			//String productPriceString= driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
			
			//double productPrice = Double.parseDouble(productPriceString.substring(1).trim());
			
			// Calling function formatAmount() in AndroidBaseTest class
			//double productPrice = formatAmount(productPriceString);
			
			// Calling getFormattedProductsPriceCartPageByIndex() function in CartPage class to get formatted and parsed products price based on index value
			double productPrice = cartPageObj.getFormattedProductsPriceCartPageByIndex(i);
			
			totalPrice= totalPrice + productPrice;
		}
		
		//String totalAmountString= driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		
		//double totalAmount = Double.parseDouble(totalAmountString.substring(1).trim());
		
		// Calling function formatAmount() in BaseTest class
		//double totalAmount = formatAmount(totalAmountString);
		
		// Calling getFormattedTotalAmount() method in CartPage class to get formatted and parsed total amount string
		double totalAmount = cartPageObj.getFormattedTotalAmount();
		
		// Validate total of products in the cart
		AssertJUnit.assertEquals(totalPrice, totalAmount);
		
		// Click on checkbox before proceeding
		//driver.findElement(By.className("android.widget.CheckBox")).click();
		
		// Calling function clickCheckbox() in CartPage class
		cartPageObj.clickCheckbox();
		
		// Long press on terms and conditions to open it
		//WebElement termEle= driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		
		// Calling longPressAction() reusable method in BaseTest class
		//longPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
		
		// Calling function performLongPress() in CartPage class
		cartPageObj.performLongPress();
		
		
		// Validate alert title
		//String alertTitle= driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
		
		// Calling function getAlertTitle() in CartPage class
		String alertTitle= cartPageObj.getAlertTitle();
		
		AssertJUnit.assertEquals(alertTitle, "Terms Of Conditions");
		
		// Validate alert message body
		//String alertBody= driver.findElement(By.id("android:id/message")).getText();
		
		// Calling function getAlertBody() in CartPage class
		String alertBody= cartPageObj.getAlertBody();
		
		AssertJUnit.assertEquals(alertBody, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		
		// Close the terms and conditions alert dialog 
		//driver.findElement(AppiumBy.className("android.widget.Button")).click();
		
		// Calling function closeAlertBox() in CartPage class
		cartPageObj.closeAlertBox();
		
		// Click on Proceed button will open browser webpage to complete the purchase. It's a Hybrid app, which means it has both native and web view.
		//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		// Calling function proceedToCheckout() in CartPage class
		cartPageObj.proceedToCheckout();
		
		// Applying sleep for browser web page to render
		Thread.sleep(3000);
		// driver doesn't have knowledge of handling browser. We need to provide driver with the context of web page.
		
		// Grab all context names that developer has given in the app
		Set<String> contexts= driver.getContextHandles();
		
		// Printing all context names in console, in order to find context name for handling web view
		for(String contextName: contexts) {
			System.out.println(contextName); // It will return 2 contexts: NATIVE_APP and WEBVIEW_com.androidsample.generalstore
		}
		
		// Changing language from German to English
		driver.findElement(By.xpath("//android.view.View[@resource-id=\"vc3jof\"]")).click();
		
		driver.findElement(By.xpath("(//android.view.MenuItem[@text=\"‪English (United Kingdom)‬\"])[1]")).click();
		
		// Click on 'Read more'
		//driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"KByQx\"]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text=\"Read more\"]")).click();
		
		// Accepting cookies by click 'Accept all'
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"L2AGLb\"]")).click();
		
		// Switch to web view context, in order to handle browser page in mobile app
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		// On mobile browser, we can work just like Selenium, as we do for web browser. Infact, we can inspect elements from web browser itself. Mobile browser is no different
		driver.findElement(By.cssSelector("textarea[name=\"q\"]")).sendKeys("rahul dhawan");
		
		// Click enter
		driver.findElement(By.cssSelector("textarea[name=\"q\"]")).sendKeys(Keys.ENTER);
		
		// Click back button to go back to Native app
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		
		// Switch to native app context, in order to handle native app
		driver.context("NATIVE_APP");
	}

}
