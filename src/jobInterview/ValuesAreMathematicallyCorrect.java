package jobInterview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ValuesAreMathematicallyCorrect {
	
	public static final String amount = "26.12";

	public static void main(String[] args) throws InterruptedException {
		
		//System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");
		
		//Instantiate a ChromeDriver class
		WebDriver driver = new ChromeDriver();
		driver.get("Http:////www.xe.com/currencyconverter");
		
		//close 'Cookies' popup
		driver.findElement(By.xpath("//*[@id=\"__tealiumGDPRecModal\"]/div/div/div[2]/button")).click();
		
		//provide amount to convert
		driver.findElement(By.xpath("//*[@id='amount']")).sendKeys(amount);
	
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
		float conversionAmountAssertion = (amountFloat / rateValueFloat);
		System.out.println("calculated conversion amount: " + conversionAmountAssertion);
		
		//retrieve the result of exchange
		String conversionAmount = driver.findElement(By.xpath("//*[@id='converterResult']/div/div/div[2]")).getText();
		String conversionAmountValueWeb = conversionAmount.substring(0, conversionAmount.length() - 3);
		
		//Remove commas from amount
		String conversionAmountValueWebWithoutCommas = conversionAmountValueWeb.replaceAll(",", "");
		Float conversionAmountValueWebFloat = Float.parseFloat(conversionAmountValueWebWithoutCommas);
		System.out.println("conversion amount obtained from the website: " + conversionAmountValueWebFloat);
		
		//compare the result obtained by mathematical operations with the one obtained from the website
		Assert.assertEquals(conversionAmountAssertion, conversionAmountValueWebFloat, 0.01);
				
		System.out.println("Test Passed");      
        
        driver.close();

	}

}
