package AppiumProject.Framework.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import AppiumProject.Framework.utils.AndroidActionsGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ProductsDisplayPage extends AndroidActionsGestures{

	AndroidDriver driver;
	
	public ProductsDisplayPage(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Add 2nd product to cart
	//driver.findElement(By.xpath("(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")).click();
	
	@AndroidFindBy(xpath= "(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])[2]")
	private WebElement secondProduct;
	
	// When you exactly know until where to scroll, by providing web element name
	//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
	
	// Get products count from the list
	//int productCount= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
	
	//String productName= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/productName")
	private List<WebElement> productsName;
	
	// Add to cart
	//driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")).get(i).click();
	
	@AndroidFindBy(xpath= "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']")
	private List<WebElement> addToCartButton;
	
	// Click on Cart icon to go to the cart page
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartIcon;
			
	public void addSecondProductToCart() {
		secondProduct.click();
	}
	
	public void addProduct(String productName) {
		scrollToText(productName);
	}
	
	public int getProductsCount() {
		return productsName.size();
	}
	
	public String fetchProductNameByIndex(int index) {
		return productsName.get(index).getText();
	}
	
	public void addProductToCartByIndex(int index) {
		addToCartButton.get(index).click();
	}
	
	public CartPage goToCart() {
		cartIcon.click();
		
		// Returning CartPage class object from here only instead of creating an object in EcomTest class. This is another way of creating an object
		return new CartPage(driver);
	}
}
