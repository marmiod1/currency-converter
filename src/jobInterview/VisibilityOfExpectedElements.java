package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VisibilityOfExpectedElements {
	
	public static final String amountInt = "2593";

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http://www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide amount to convert
		driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amountInt);
		
		//expand the list
		WebElement source = driver.findElement(By.xpath("//*[@id='from']"));
		source.click();
		Thread.sleep(2000L);
				
		//select source currency from the dropdown
		source.sendKeys("pol");	
		Thread.sleep(2000L);
		
		//confirm by clicking 'enter'
		source.sendKeys(Keys.ENTER);
		Thread.sleep(1000L);
		
		//navigate to target currency
		source.sendKeys(Keys.TAB);
		driver.findElement(By.xpath("//*[@id='converterForm']/form/button[1]")).sendKeys(Keys.TAB);
			
		//expand the list with target currency
		WebElement target = driver.findElement(By.xpath("//*[@id='to']"));
		target.click();
		Thread.sleep(1000L);
		
		//select target currency from the dropdown 
		target.sendKeys("bri");
		Thread.sleep(1000L); 	
		
		//confirm by clicking 'enter'
		target.sendKeys(Keys.ENTER);
		Thread.sleep(1000L); 
		
	
		//click the arrow on the yellow background to convert
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		Thread.sleep(1000L);
		
		/**
		 * Check the visibility of elements:conversion amount, conversion rate, single unit for both currencies
		 */
		
		//print final amount after conversion
		WebElement conversionAmountForValue = driver.findElement(By.xpath("//*[@id='main-heading']"));
		System.out.println("Conversion amount for the value: " + conversionAmountForValue.getText());
		
		//print conversion amount with source amount
		WebElement conversionAmount = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]"));
		System.out.println("Conversion amount: " + conversionAmount.getText());
		
		//print conversion rate 
		WebElement conversionRate = driver.findElement(By.xpath("//*[@id='converterResult']/section/div[1]/div[1]"));
		System.out.println("Conversion Rate: " + conversionRate.getText());
		
		//print single unit for both currencies 
		WebElement sourceCurrency = driver.findElement(By.xpath("//*[@id='converterResult']/section/div[1]/div[2]"));
		System.out.println("Single unit for source currency: " + sourceCurrency.getText());
		System.out.println("Single unit fo target currency: " + conversionRate.getText());
				
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
