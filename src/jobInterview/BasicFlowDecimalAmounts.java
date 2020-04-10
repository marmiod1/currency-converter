package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicFlowDecimalAmounts {
	
	public static final String amountDecimal  = "695.2459";

	public static void main(String[] args) throws InterruptedException {
		
		//System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http:////www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide amount to convert
		driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amountDecimal);
		
		//click the arrow on the yellow background to convert
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		
		Thread.sleep(2000L);
				
		//print final amount after conversion
		System.out.println(driver.findElement(By.xpath("//*[@class='converterresult-conversionTo']")).getText());
		
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
