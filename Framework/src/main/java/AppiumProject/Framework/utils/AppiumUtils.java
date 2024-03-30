package AppiumProject.Framework.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startAppiumServer(String ipAdress, int port) {
		// Appium server start
		service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
						.withIPAddress(ipAdress).usingPort(port).build();
		//service.start();
		return service;
	}
	
	public double formatAmount(String priceStr) {
		return Double.parseDouble(priceStr.substring(1).trim());
	}
	
	public void waitForElementToAppear(WebElement ele, String txt, String title, AppiumDriver driver) {
		
		// Applying explicit wait for the cart page to load. Not required on this machine, but issue may occur because of slowness
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// Waiting until 5 seconds for the 'Cart' title to appear
		wait.until(ExpectedConditions.attributeContains(ele, txt, title));
		
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		
		// JSON file path
		//System.getProperty("user.dir")+ "/src/test/java/AppiumProject/Framework/testData/eComData.json"
		
		// Convert JSON file to JSON string
		String jsonContent= FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		// Convert JSON string to HashMap
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			
		});
		
		return data;
	}
	
	public String getScreenshotPath(AppiumDriver driver, String testCaseName) throws IOException {
		
		// How screenshot need to be generated
		File source= driver.getScreenshotAs(OutputType.FILE);
		
		// File name along with path
		String destinationFile= System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		
		// Copy file from source to destination
		FileUtils.copyFile(source, new File(destinationFile));
		
		// Extent report has to pick this file and attach it to the report
		return destinationFile;
		
	}
}
