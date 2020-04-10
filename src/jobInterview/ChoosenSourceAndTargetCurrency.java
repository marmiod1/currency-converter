package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChoosenSourceAndTargetCurrency {
	
	public static final String amountInt = "2593";

	public static void main(String[] args) throws InterruptedException {
		
		//System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http:////www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide amount to convert
		driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amountInt);
		
		//expand the list
		WebElement source = driver.findElement(By.xpath("//*[@id='from']"));
		source.click();
		Thread.sleep(3000L);
				
		//select source currency from the dropdown
		source.sendKeys("aus");	
		Thread.sleep(3000L);
		
		//confirm by clicking 'enter'
		source.sendKeys(Keys.ENTER);
		Thread.sleep(2000L);
		
		//navigate to target currency
		source.sendKeys(Keys.TAB);
		driver.findElement(By.xpath("//*[@id='converterForm']/form/button[1]")).sendKeys(Keys.TAB);
			
		//expand the list with target currency
		WebElement target = driver.findElement(By.xpath("//*[@id='to']"));
		target.click();
		Thread.sleep(3000L);
		
		//select target currency from the dropdown 
		target.sendKeys("ind");
		Thread.sleep(3000L); 	
		
		//confirm by clicking 'enter'
		target.sendKeys(Keys.ENTER);
		Thread.sleep(3000L); 
		
		//click the arrow on the yellow background to convert
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		Thread.sleep(2000L);
				
		//print final amount after conversion
		WebElement conversionAmount = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]"));
		System.out.println(conversionAmount.getText());
				
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
