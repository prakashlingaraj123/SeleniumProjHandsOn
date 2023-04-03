package selFramework.frameworkdev.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selFramework.frameworkdev.AbstractComponets.AbstractComponents;

public class MyCart extends AbstractComponents {
	WebDriver driver;
	public MyCart(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));
	
	@FindBy(css=".cart h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".subtotal.cf.ng-star-inserted .btn.btn-primary")
	WebElement checkout;
	
	By mycartItem = By.cssSelector(".cart h3");
	
	public List<WebElement> productList() {
		waitForElementVisibility(mycartItem);
		return cartItems;
	}
	/*
	 * boolean match = cartItems.stream().anyMatch(it -> it.getText().equals(item));
	 * Assert.assertTrue(match);
	 */
	public boolean getCartItem(String prod) {
		boolean match = cartItems.stream().anyMatch(it -> it.getText().equals(prod));
		return match;
	}
	//driver.findElement(By.cssSelector(".subtotal.cf.ng-star-inserted .btn.btn-primary")).click();

	public Payment clickCheckout() {
		checkout.click();
		Payment payment = new Payment(driver);
		return payment;
	}
	
}
