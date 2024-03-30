package AppiumProject.Framework.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import AppiumProject.Framework.utils.AndroidActionsGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FormPage extends AndroidActionsGestures{
	
	AndroidDriver driver;
	public FormPage(AndroidDriver driver) {
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// Click on static dropdown to select the country
	//driver.findElement(By.id("android:id/text1")).click();
	
	@AndroidFindBy(id= "android:id/text1")
	private WebElement countryDropDown;
	
	// Scroll down to view an option 'India' in the countries list
	
	// When you exactly know where to scroll, by providing web element
	//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));"));
	
	// Click on option 'India', which is now in the view after scrolling
	//driver.findElement(By.xpath("//android.widget.TextView[@text='India']")).click();
	
	// Enter name in the name field
	//driver.findElement(By.className("android.widget.EditText")).sendKeys("Chameli");
	
	@AndroidFindBy(className= "android.widget.EditText")
	private WebElement nameField;
	
	// Select radio button female
	//driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement genderFemale;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement genderMale;
	
	// Click on 'Lets Shop' button which will validate the form
	//driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
			
	@AndroidFindBy(id= "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	// Grabbing toast message
	//String toastMsg= driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	
	@AndroidFindBy(xpath= "//android.widget.Toast[1]")
	private WebElement toastEle;
	
	// Grabbing toast message
	//int toastMsgCount= driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size();
	
	@AndroidFindBy(xpath= "//android.widget.Toast[1]")
	private List<WebElement> toastEleList;
	
	public void selectCountry(String countryName) {
		countryDropDown.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
	}
	
	public void setGender(String gender) {
		if(gender.contains("Female")) {
			genderFemale.click();
		}
		else genderMale.click();
	}
	
	public void shopClick() {
		shopButton.click();
	}
	
	public String grabToastMsg() {
		return toastEle.getAttribute("name");
	}
	
	public int toastMsgCount() {
		return toastEleList.size();
	}
	
	public void setActivity() {
		
		// For opening home screen (Form page) on the Ecom app directly, we have App package: com.androidsample.generalstore and App Activity: com.androidsample.generalstore.MainActivity
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
				"intent", "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"
		));
	}
	
}
