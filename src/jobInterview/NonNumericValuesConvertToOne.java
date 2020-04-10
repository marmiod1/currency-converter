package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NonNumericValuesConvertToOne {
	
	public static final String nonNumeric = "dox";

	public static void main(String[] args) throws InterruptedException {
		
		//System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http:////www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide non numeric value
		WebElement amountBox = driver.findElement(By.xpath("//*[@id='amount']"));
		amountBox.sendKeys(nonNumeric);
		Thread.sleep(1000L);
		amountBox.sendKeys(Keys.ENTER);
		Thread.sleep(1000L);
	
		//click the arrow on the yellow background to convert
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		Thread.sleep(2000L);
				
		//print final amount after conversion
		WebElement conversionAmount = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]"));
		System.out.println(conversionAmount.getText());
		
		int conversionInputValue = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"converterResult\"]/div/div/div[1]/span[1]")).getText()); 
				
		//Compare conversion input value to '1'
		Assert.assertEquals(conversionInputValue, 1, "conversion input value equals '1'");
		
		//print conversion input value
		System.out.println("conversion input value: " + conversionInputValue);
		
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
