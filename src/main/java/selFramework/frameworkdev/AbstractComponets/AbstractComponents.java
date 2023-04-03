package selFramework.frameworkdev.AbstractComponets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selFramework.frameworkdev.pageObject.MyCart;
import selFramework.frameworkdev.pageObject.YourOrders;

public class AbstractComponents {
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement yourOrders;
	
	By IteminCart = By.cssSelector(".itemNumber");
	By yourOrdersTitle = By.cssSelector("h1[class=ng-star-inserted]");
	public void waitForElementVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitForElementInvisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.invisibilityOf(ele));
				
	}
	public void waitforElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void scrollBy() {
		JavascriptExecutor js =  (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1200)");
	}
	public MyCart goToCart() {
		cartButton.click();
		waitForElementVisibility(IteminCart);
		return new MyCart(driver);
	}
	public YourOrders goToYourOrders() {
		yourOrders.click();
		waitForElementVisibility(yourOrdersTitle);
		return new YourOrders(driver);
	}
}
