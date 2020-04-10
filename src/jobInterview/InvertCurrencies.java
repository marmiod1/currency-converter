package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class InvertCurrencies {
	
	public static final String amount = "100";

	public static void main(String[] args) throws InterruptedException {
		
		//System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http://www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide amount to convert
		driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amount);
		
		//expand the list
		WebElement source = driver.findElement(By.xpath("//*[@id='from']"));
		source.click();
		Thread.sleep(3000L);
				
		//select source currency from the dropdown
		source.sendKeys("cub");	
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
		target.sendKeys("ils");
		Thread.sleep(3000L); 	
		
		//confirm by clicking 'enter'
		target.sendKeys(Keys.ENTER);
		Thread.sleep(3000L); 
		
		//click the arrow on the yellow background to convert
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		Thread.sleep(2000L);
		
		//get the rate
		String conversionRate = driver.findElement(By.xpath("//*[@id='converterResult']/section/div[1]/div[1]")).getText();
		int start = conversionRate.indexOf("=");
		int startRateValue = start + 2;
		String rateValue= conversionRate.substring(startRateValue, conversionRate.length() - 3);	
		System.out.println("rate: " + rateValue);
		Float rateValueFloat = Float.parseFloat(rateValue);
		
		//divide provided amount by the exchange rate
		Float amountFloat = Float.parseFloat(amount);
		float conversionAmountFirst = (amountFloat / rateValueFloat);
		System.out.println("calculated conversion value: " + conversionAmountFirst);
		
		//retrieve the result of exchange
		String conversionAmount = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]")).getText();
		String conversionAmountValueWeb = conversionAmount.substring(0, conversionAmount.length() - 3);
		System.out.println("conversion amount from website: " + conversionAmountValueWeb);
		Thread.sleep(2000L);
		
		//compare results between calculation and data from website
		String conversionAmountValueWebWithoutCommas = conversionAmountValueWeb.replaceAll(",", "");
		Float conversionAmountValueWebFloat = Float.parseFloat(conversionAmountValueWebWithoutCommas);
		Assert.assertEquals(conversionAmountFirst, conversionAmountValueWebFloat, 0.01);
		
		//use 'Invert Currencies' button 
		driver.findElement(By.xpath("//*[@id='converterForm']/form/button[1]")).click();
		Thread.sleep(2000L);
		
		//click the arrow to convert after inversion
		driver.findElement(By.cssSelector("button[aria-label='Convert']")).click();
		Thread.sleep(2000L);
		
		//after reversion amount should be multiplied by the rate (previously was divided)
		float conversionAmountAfterConversion = (amountFloat * rateValueFloat);
		System.out.println("calculated conversion value after inversion: " + conversionAmountAfterConversion);
		
		//retrieve the result of te second exchange
		String conversionAmountAfterInversion = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]")).getText();
		String conversionAmountAfterInversionWeb = conversionAmountAfterInversion.substring(0, conversionAmountAfterInversion.length() - 3);
		System.out.println("conversion amount from website after inversion: " + conversionAmountAfterInversionWeb);
		Thread.sleep(2000L);
		
		//compare results between calculation and data from website after inversion
		String conversionAmountAfterInversionWebWithoutCommas = conversionAmountAfterInversionWeb.replaceAll(",", "");
		Float conversionAmountAfterInvesionValueWebFloat = Float.parseFloat(conversionAmountAfterInversionWebWithoutCommas);
		Assert.assertEquals(conversionAmountAfterConversion, conversionAmountAfterInvesionValueWebFloat, 0.01);
				
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
