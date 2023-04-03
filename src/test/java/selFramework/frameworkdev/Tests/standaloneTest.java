package selFramework.frameworkdev.Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(option);
		// String[] items= {"ZARA COAT 3","ADIDAS ORIGINAL"};
		String item = "ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("prakash@lp.com");
		driver.findElement(By.cssSelector("[id='userPassword']")).sendKeys("Prakash3@");
		// List<String> itemlist = Arrays.asList(items);
		driver.findElement(By.cssSelector("[id='login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		/*
		 * for(int i=0; i<products.size();i++) { WebElement prod = products.get(i);
		 * String ele = prod.findElement(By.cssSelector("b")).getText();
		 * System.out.println(ele); if(ele.equals("ADIDAS ORIGINAL")) {
		 * prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 * break; } }
		 */

		WebElement ele = products.stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(item))
				.findFirst().orElse(null);
		ele.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".la-ball-scale-multiple"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".itemNumber")));

		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));

		boolean match = cartItems.stream().anyMatch(it -> it.getText().equals(item));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".subtotal.cf.ng-star-inserted .btn.btn-primary")).click();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		List<WebElement> countries = driver
				.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted button"));
		//System.out.println(countries.get(1).getText());
		//driver.findElement(By.xpath("//*[text()=' India']")).click();
		countries.stream().filter(cn -> cn.getText().equals("India")).findAny().orElse(null).click();
		driver.findElement(By.xpath("//div[contains(text(),'CVV Code')]/following-sibling::input")).sendKeys("789");
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input"))
				.sendKeys("Prakash");

		driver.findElement(By.xpath("//div[contains(text(),'Apply Coupon')]/following-sibling::input")).sendKeys("ABC");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary.mt-1")));
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-tns-c31-2.ng-star-inserted"))));
		driver.findElement(By.cssSelector(".btn.btn-primary.mt-1")).click();
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner[contains(@class,'ng-star-inserted')]/div"))));
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
