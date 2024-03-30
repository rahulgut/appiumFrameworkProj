package AppiumProject.Framework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import AppiumProject.Framework.pageObjects.android.CartPage;
import AppiumProject.Framework.pageObjects.android.FormPage;
import AppiumProject.Framework.pageObjects.android.ProductsDisplayPage;
import AppiumProject.Framework.testUtils.AndroidBaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FillFormEcomTest extends AndroidBaseTest {

	//To execute it before running tests in this class test
	@BeforeMethod(alwaysRun=true)
	public void preSetup() {
		
		// We need to open home screen of Ecom app, that is form page in order to execute both tests
		
		// Opening internal app page directly. Using app package and app activity
		
		// On terminal run this command to get the app package and activity name, adb shell dumpsys window | grep -E 'mCurrentFocus'
		// We will get this, mCurrentFocus=Window{238da87 u0 com.androidsample.generalstore/com.androidsample.generalstore.MainActivity}
		
		// For opening home screen (Form page) on the Ecom app directly, we have App package: com.androidsample.generalstore and App Activity: com.androidsample.generalstore.MainActivity
		
		// Passing package name and activity name as arguments
		//Activity activityObj = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		//driver.startActivity(activityObj);
		
		// driver.startActivity() is deprecated in current version of Appium ^2.0. 
		
		//Instead use, mobile: startActivity through javascript, as we did for gestures
		
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
//				"intent", "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"
//		));
		
		// For some reason start activity code is not working
		
		// Calling setActivity() method in FormPage class
		formPageObj.setActivity();
		
	}
	
	// To execute it as a TestNG test
	@Test
	public void fillFormNegativeFlow() throws InterruptedException {
		
		// Creating object of FormPage class and passing driver as an argument
		//FormPage formPageObj= new FormPage(driver);
	
		// We don't need to create object here, because we have created it in AndroidBaseTest class
		
		// Calling selectCountry() method and sending country as an argument
		//formPageObj.selectCountry("Angola");
		
		// Calling setNameField() method and sending name as an argument
		//formPageObj.setNameField("Chappal");
		
		// Calling setGender() method and sending gender as an argument string to validate for action to be performed
		formPageObj.setGender("Female");
		
		// Calling shopClick() method
		formPageObj.shopClick();
		
		// Calling grabToastMsg() method
		//String toastMsg= formPageObj.grabToastMsg();
		
		// Validating toast message
		//Assert.assertEquals(toastMsg, "Please enter your name");

	}
	
	
	// To execute it as a TestNG test
	// Using @DataProvider in the test to provide data
	
	@Test(dataProvider= "getData", groups= {"Smoke"})
	//public void fillFormHappyFlow(String country, String name, String gender) throws InterruptedException {
	
	// Creating method with HasMap input as an argument which will fetch data from JSON file
	public void fillFormHappyFlow(HashMap<String, String> input) throws InterruptedException {
			
		// Creating object of FormPage class and passing driver as an argument
		//FormPage formPageObj= new FormPage(driver);
		
		// We don't need to create object here, because we have created it in AndroidBaseTest class
		
		// Calling selectCountry() method and sending country as an argument
		//formPageObj.selectCountry("Argentina");
		
		// Calling selectCountry() method and sending country received from DataProvider as an argument
		formPageObj.selectCountry(input.get("country"));

		// Calling setNameField() method and sending name as an argument
		//formPageObj.setNameField("Chappal");
		
		// Calling setNameField() method and sending name received from DataProvider as an argument
		formPageObj.setNameField(input.get("name"));
		
		// Calling setGender() method and sending gender as an argument string to validate for action to be performed
		//formPageObj.setGender("Female");
		
		// Calling setGender() method and sending gender received from DataProvider as an argument
		formPageObj.setGender(input.get("gender"));
		
		// Calling shopClick() method
		formPageObj.shopClick();
		
		// Grabbing toast message
		//int toastMsgCount= driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size();
		
		// Calling toastMsgCount() method in FormPage class
		int toastMsgCount= formPageObj.toastMsgCount();
		
		// Validating toast message
		Assert.assertTrue(toastMsgCount<1);
		
		// For some reason, setActivity() code is not working, it is unable to launch activity screen. So, navigating back as a workaround
		driver.navigate().back();
		
	}
	
	// Using TestNG annotation to provide data to our test
	@DataProvider
	public Object[][] getData() throws IOException {
		
		// Calling getJsonData() function in AppiumUtils class
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+ "/src/test/java/AppiumProject/Framework/testData/eComData.json");
		
		//return new Object[][] { {"Australia","Joota", "Male"} , {"Austria","Boot", "Female"}};
		
		// Passing JSON data to the object
		return new Object[][] { {data.get(0)} , {data.get(1)} };
		
	}

}
